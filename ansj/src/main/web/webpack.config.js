const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack');
const mode = process.env.NODE_ENV || 'development';
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin')
module.exports = {
    mode,
    devServer: {
        historyApiFallback: true,
        // inline: true,
        port: 3000,
        hot: true,
        // publicPath: '/',
    },
    entry: {
        app: path.join(__dirname,  './src/index.tsx'),
    },

    resolve: {
        extensions: ['.js', '.jsx', '.ts', '.tsx'],
    },

    module: {
        rules: [
            {
                test: /\.(ts|tsx)$/,
                use: ['babel-loader', 'ts-loader'],
                // exclude: path.join(__dirname, "/node_modules/")
            },
            {
                test: /\.(png|jpe?g|gif)$/,
                use: [
                    {
                        loader: 'file-loader',
                        options: {
                            name: 'assets/[contenthash].[ext]'
                        }
                    }
                ]
            },
            {
                test: /\.(scss|css)$/,
                use: [{
                    loader: MiniCssExtractPlugin.loader,
                    options: {
                        publicPath: ''
                    }
                }, 'css-loader', 'sass-loader']
            }
        ],
    },

    output: {
        path: path.join(__dirname, '/build/static'),
        filename: 'bundle.js',
    },

    plugins: [
        // new webpack.ProvidePlugin({
        //   React: 'react',
        // }),
        new HtmlWebpackPlugin({
            template: './public/index.html',
        }),
        // new webpack.HotModuleReplacementPlugin(),
        new MiniCssExtractPlugin(),
        new CleanWebpackPlugin({
            cleanAfterEveryBuildPatterns: ['dist']
        })
    ],
};
