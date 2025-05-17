plugins {
    id("java-library")
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