plugins {
    id("java")
    antlr
}

group = "io.github.archivedev.lum"

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.13.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.compileJava {
    dependsOn(tasks.generateGrammarSource)
}

tasks.generateGrammarSource {
    arguments.addAll(arrayOf("-package", "lum.antlr4"))
    arguments.add("-no-listener")
    arguments.add("-no-visitor")
    outputDirectory = File("src/main/java")
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}