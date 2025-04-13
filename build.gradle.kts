plugins {
    id("java")
    `maven-publish`
}

group = "io.github.archivedev.lum"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":compiler"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    sourceCompatibility = JavaVersion.VERSION_24
    targetCompatibility = JavaVersion.VERSION_24
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.compileJava {
    options.compilerArgs.add("--enable-preview")
}

subprojects {
    group = "io.github.archivedev.lum"

    apply<JavaPlugin>()
    // gradle-plugin project already has settings for publishing plugin to gradle plugin portal
    if (this != project(":gradle-plugin")) {
        apply(plugin = "maven-publish")

        publishing {
            publications {
                create<MavenPublication>("mavenJava") {
                    from(components["java"])

                    pom {
                        name = this@subprojects.name
                        description = this@subprojects.name
                        url = "https://github.com/archive-dev/lum"

                        licenses {

                        }

                        scm {
                            connection = "scm:git:git://github.com/archive-dev/lum.git"
                            developerConnection = "scm:git:ssh://github.com:archive-dev/lum.git"
                            url = "https://github.com/archive-dev/lum/tree/main"
                        }
                    }

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
                }
            }
        }
    }

    tasks.compileJava {
        options.compilerArgs.add("--enable-preview")
    }

    tasks.compileTestJava {
        options.compilerArgs.add("--enable-preview")
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

    val mockitoAgent = configurations.create("mockitoAgent")
    dependencies {
        testImplementation("org.mockito:mockito-core:5.14.2")
        mockitoAgent("org.mockito:mockito-core:5.14.2") { isTransitive = false }
    }

    tasks {
        test {
            options {
                jvmArgs("--enable-preview", "-javaagent:${mockitoAgent.asPath}")
            }
        }
    }
}