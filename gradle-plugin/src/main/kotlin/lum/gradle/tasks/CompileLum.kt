package lum.gradle.tasks

import lum.compiler.Compiler
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.*
import java.nio.file.Path

@CacheableTask
abstract class CompileLum : DefaultTask() {
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val sourceDir: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun compile() {
        val source: Path = sourceDir.get().asFile.toPath()
        val output: Path = outputDir.get().asFile.toPath()

        println("Compiling Lum code from $source to $output")

        Compiler.compile(output, source, source)
    }
}