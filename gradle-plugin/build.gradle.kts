plugins {
    kotlin("jvm") version "2.1.10"
    `kotlin-dsl`
    id("com.gradleup.shadow") version "8.3.6"
    `maven-publish`
}

gradlePlugin {
    website = "https://github.com/archive-dev/lum"
    vcsUrl = "https://github.com/archive-dev/lum.git"
    plugins {
        create("lumPlugin") {
            displayName = "Lum Gradle Plugin"
            description = "Lum Gradle Plugin"

            id = "io.github.archivedev.lum.gradle"
            tags = listOf("jvm", "programming language")

            implementationClass = "lum.gradle.LumPlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/archive-dev/lum")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user") as String?
                password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key") as String?
            }
        }
    }
    publications {
        register<MavenPublication>("pluginMaven") {}
    }
}

group = "io.github.archivedev.lum"
version = "1.0"

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