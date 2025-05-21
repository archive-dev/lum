plugins {
    kotlin("jvm") version "2.1.10"
    `kotlin-dsl`
    id("com.gradleup.shadow") version "8.3.6"
    id("com.gradle.plugin-publish") version "1.2.1"
}

repositories {
    mavenCentral()
}

version = "1.0"
group = "io.github.archive-dev"

gradlePlugin {
    website = "https://github.com/archive-dev/lum"
    vcsUrl = "https://github.com/archive-dev/lum.git"
    plugins {
        create("lumPlugin") {
            id = "io.github.archive-dev.lum.gradle"

            displayName = "Lum Gradle Plugin"
            description =
                """Gradle plugin for Lum programming language. Includes compilation tasks.
                    | Write lum code in src/main/lum directory and compile.
                """.trimMargin()
            tags = listOf("jvm", "programming language")

            implementationClass = "lum.gradle.LumPlugin"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":compiler"))

    testImplementation(kotlin("test"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_24
    targetCompatibility = JavaVersion.VERSION_24
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }

    withJavadocJar()
    withSourcesJar()
}

tasks.javadoc {
    (options as CoreJavadocOptions).addStringOption("source", "24")
    (options as CoreJavadocOptions).addBooleanOption("-enable-preview", true)
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