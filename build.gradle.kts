plugins {
    id("java")
}

group = "org.lum"
version = "1.0-SNAPSHOT"

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
    sourceCompatibility = JavaVersion.VERSION_23
    targetCompatibility = JavaVersion.VERSION_23
}

tasks.test {
    useJUnitPlatform()
}

tasks.compileJava {
    options.compilerArgs.add("--enable-preview")
}

subprojects {
    apply(plugin = "java")

    tasks.compileJava {
        options.compilerArgs.add("--enable-preview")
    }

    tasks.compileTestJava {
        options.compilerArgs.add("--enable-preview")
    }


    java {
        sourceCompatibility = JavaVersion.VERSION_23
        targetCompatibility = JavaVersion.VERSION_23
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