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

            id = "gradle"
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
                name = this.name
                description = this.name
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