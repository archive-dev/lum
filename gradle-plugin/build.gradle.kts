plugins {
    kotlin("jvm") version "2.1.10"
    `kotlin-dsl`
    id("com.gradleup.shadow") version "8.3.6"
    id("com.gradle.plugin-publish") version "1.3.1"
}

gradlePlugin {
    plugins {
        website = "https://github.com/archive-dev/lum"
        vcsUrl = "https://github.com/archive-dev/lum.git"
        create("lumPlugin") {
            displayName = "Lum Gradle Plugin"

            id = "io.github.archivedev.lum.gradle"
            tags = listOf("jvm", "programming language")

            implementationClass = "lum.gradle.LumPlugin"
        }
    }
}

group = "io.github.archivedev.lum"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":compiler"))

    testImplementation(kotlin("test"))
}

tasks.shadowJar {
    archiveClassifier = ""
}

tasks.test {
    dependsOn(tasks.shadowJar)
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(24)
}