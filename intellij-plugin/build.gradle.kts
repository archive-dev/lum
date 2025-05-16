plugins {
  kotlin("jvm") version "2.1.20"
//    kotlin("jvm") version "1.9.23"
//  id("org.jetbrains.kotlin.jvm") version "2.0.21"
//  id("java")
  id("org.jetbrains.intellij.platform") version "2.5.0"
}

//group = "ru.whoisamyy.lum"
//version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
dependencies {
  intellijPlatform {
    create("IC", "2025.1")
//    testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)

    // Add necessary plugin dependencies for compilation here, example:
//     bundledPlugin("com.intellij.kotlin")
     bundledPlugin("org.jetbrains.kotlin")
  }
  implementation(project(":grammar"))

  implementation("org.antlr:antlr4-intellij-adaptor:0.1")
}

intellijPlatform {
  pluginConfiguration {
    ideaVersion {
      sinceBuild = "242"
    }

    changeNotes = """
      Initial version
    """.trimIndent()
  }
}

tasks.compileJava {
  targetCompatibility = "21"
  sourceCompatibility = "21"
}

kotlin {
  jvmToolchain(21)
}

// some hacks
tasks.register("wrapper") {}
tasks.register("prepareKotlinBuildScriptModel") {}