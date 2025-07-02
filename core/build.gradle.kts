plugins {
    id("java-library")
    id("org.javamodularity.moduleplugin") version "1.8.15"
}

group = "io.github.archivedev.lum"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.0.0")

    implementation(project(":runtime"))
//    apiElements(project(":grammar"))
    api(project(":grammar"))

    // logging
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.3")
    implementation("org.slf4j:log4j-over-slf4j:2.0.17")

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

val mockitoAgent = configurations.create("mockitoAgent")
dependencies {
    testImplementation("org.mockito:mockito-core:5.14.2")
    mockitoAgent("org.mockito:mockito-core:5.14.2") { isTransitive = false }
}

tasks.test {
    useJUnitPlatform()
    options {
        jvmArgs("--enable-preview", "-javaagent:${mockitoAgent.asPath}")
    }
}