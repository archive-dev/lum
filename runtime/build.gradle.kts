plugins {
    id("java-library")
//    id("io.github.whoisamyy.lum.gradle")
}

group = "io.github.archivedev.lum"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    sourceCompatibility = JavaVersion.VERSION_24
    targetCompatibility = JavaVersion.VERSION_24
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }

    modularity.inferModulePath = true

    withJavadocJar()
    withSourcesJar()
}

tasks.javadoc {
    (options as CoreJavadocOptions).addStringOption("source", "24")
    (options as CoreJavadocOptions).addBooleanOption("-enable-preview", true)
}

tasks.test {
    useJUnitPlatform()
}