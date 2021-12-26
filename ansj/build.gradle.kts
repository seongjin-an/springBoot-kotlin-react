import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    war
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.10"
}

group = "com.kotlinReact"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
val webappDir = "$projectDir/src/main/web"

sourceSets {
    main {
        resources {
            srcDirs(listOf("$webappDir/build", "$projectDir/src/main/resources"))
        }
    }
}


tasks {
    processResources {
        dependsOn("buildReact")
        duplicatesStrategy = org.gradle.api.file.DuplicatesStrategy.EXCLUDE
    }
}

tasks.register("buildReact", Exec::class) {
    dependsOn("installReact")
    workingDir(webappDir)
    inputs.dir(webappDir)
    group = BasePlugin.BUILD_GROUP
    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
        commandLine("npm.cmd", "run-script", "build")
    } else {
        commandLine("npm", "run-script", "build")
    }
}

tasks.register("installReact", Exec::class) {
    workingDir(webappDir)
    inputs.dir(webappDir)
    group = BasePlugin.BUILD_GROUP
    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
        commandLine("npm.cmd", "audit", "fix")
        commandLine("npm.cmd", "install")
    } else {
        commandLine("npm", "audit", "fix")
        commandLine("npm", "install")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
