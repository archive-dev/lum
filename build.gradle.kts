plugins {
    id("java")
    `maven-publish`
}

group = "io.github.archivedev.lum"
version = System.getenv("VERSION")

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
    version = System.getenv("VERSION")

    apply<JavaPlugin>()

    if (this@subprojects != project(":intellij-plugin")) {
        tasks.withType<JavaCompile>() {
            options.compilerArgs.add("--enable-preview")
        }
    }
}