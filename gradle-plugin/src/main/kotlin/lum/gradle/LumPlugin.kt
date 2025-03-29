package lum.gradle

import lum.gradle.tasks.CompileLum
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.apply
import kotlin.io.path.Path

class LumPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.apply<JavaPlugin>()

        // 1. Add a 'lum' extension to the project
        val extension = project.extensions.create("lum", LumExtension::class.java)

        // 2. Register a 'lumCompile' task
        val compileTask: TaskProvider<CompileLum> = project.tasks.register("compileLum", CompileLum::class.java) {
            sourceDir.set(project.file(extension.sourceDir.get()))
            outputDir.set(project.file(extension.outputDir.get()))
        }

        val compileTestTask: TaskProvider<CompileLum> = project.tasks.register("compileLumTest", CompileLum::class.java) {
            sourceDir.set(project.file(extension.testSourceDir.get()))
            outputDir.set(project.file(extension.testOutputDir.get()))
        }

        project.tasks.named("compileJava", JavaCompile::class.java) {
            options.compilerArgs.add("--enable-preview")
            dependsOn(compileTask)
        }

        project.tasks.named("jar", Jar::class.java) {
            dependsOn(compileTask)
            from(Path("build/classes/lum/main"))
        }

        project.tasks.named("testClasses").orNull?.dependsOn(compileTestTask)
    }
}