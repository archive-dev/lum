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

            id = "lum.gradle"
            tags = listOf("jvm", "programming language")

            implementationClass = "lum.gradle.LumPlugin"
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("pluginMaven") {
            repositories {
                maven {
                    name = "GitHubPackages"
                    url = uri("https://maven.pkg.github.com/archive-dev/lum")
                    credentials {
                        username = System.getenv("USERNAME")
                        password = System.getenv("TOKEN")
                    }
                }
            }
            pom {
                name = "gradle-plugin"
                description = "Lum Gradle Plugin"
                url = "https://github.com/archive-dev/lum"

                licenses {

                }

                scm {
                    connection = "scm:git:git://github.com/archive-dev/lum.git"
                    developerConnection = "scm:git:ssh://github.com:archive-dev/lum.git"
                    url = "https://github.com/archive-dev/lum/tree/main"
                }
            }
        }
    }
}

repositories {
    mavenCentral()
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