plugins {
    kotlin("jvm") version "2.1.10"
    `kotlin-dsl`
    id("com.gradleup.shadow") version "8.3.6"
}

gradlePlugin {
    plugins {
        create("lumPlugin") {
            id = "org.lum.gradle"

            implementationClass = "lum.gradle.LumPlugin"
        }
    }
}

group = "org.lum"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":compiler"))

    testImplementation(kotlin("test"))
}

tasks.test {
    dependsOn(tasks.shadowJar)
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}