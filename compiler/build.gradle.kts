plugins {
    id("java")
    application
}

group = "org.lum"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":runtime"))

    implementation("org.jetbrains:annotations:24.0.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass = "lum.compiler.Compiler"
    applicationDefaultJvmArgs = listOf("--enable-preview")
}

tasks.test {
    useJUnitPlatform()
}