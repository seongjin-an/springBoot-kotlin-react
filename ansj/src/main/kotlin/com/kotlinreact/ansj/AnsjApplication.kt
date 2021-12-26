package com.kotlinreact.ansj

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@SpringBootApplication
@ConfigurationPropertiesScan
class AnsjApplication

fun main(args: Array<String>) {
    val dashboardHome = System.getenv("DASHBOARD_HOME")

    SpringApplicationBuilder(AnsjApplication::class.java)
        .profiles("development")
        .bannerMode(Banner.Mode.CONSOLE)
        .headless(false)
        .properties(
            "spring.config.location=" +
                    "file:$dashboardHome/properties/application.yml"
        ).application().run(*args)
}

