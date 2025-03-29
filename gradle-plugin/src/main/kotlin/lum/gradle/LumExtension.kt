package lum.gradle

import org.gradle.api.Project

open class LumExtension(project: Project) {
    val sourceDir = project.objects.property(String::class.java).convention("src/main/lum")
    val outputDir = project.objects.property(String::class.java).convention("build/classes/lum/main")

    val testSourceDir = project.objects.property(String::class.java).convention("src/test/lum")
    val testOutputDir = project.objects.property(String::class.java).convention("build/classes/lum/test")
}