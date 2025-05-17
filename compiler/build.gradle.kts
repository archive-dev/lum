plugins {
    id("java")
    application
}

group = "io.github.archivedev.lum"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.antlr:antlr4:4.13.2")
    implementation(project(":core"))
    implementation(project(":runtime"))

    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.3")
    implementation("org.slf4j:log4j-over-slf4j:2.0.17")
    implementation("org.jetbrains:annotations:24.0.0")

    implementation("org.jcommander:jcommander:2.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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

application {
    mainClass = "lum.compiler.Compiler"
    applicationDefaultJvmArgs += "--enable-preview"
    applicationName = "lumc"
}

tasks.test {
    useJUnitPlatform()
}