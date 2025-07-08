
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.internal.DefaultGradleRunner
import java.io.File
import kotlin.test.Test

object PluginTest {
    private val defaultRunner: GradleRunner
        get() {
            return (GradleRunner.create()
                .withPluginClasspath(File("build/libs").listFiles()!!.toList())
                .withProjectDir(File("../lum/hello-world")) as DefaultGradleRunner)
                .withJvmArguments("--enable-preview")
                .forwardOutput()
        }

    @Test
    fun `main compile target`() {
        defaultRunner
            .withArguments("clean", "jar")
            .build().run{}

//        assertTrue(Path("../lum/hello-world/build/classes/lum/main").exists())
//        assertTrue(Path("../lum/hello-world/build/libs/hello-world.jar").exists())
    }

    @Test
    fun `test compile target`() {
        defaultRunner
            .withArguments("clean", "test")
            .build().run{}

//        assertTrue(Path("../lum/hello-world/build/classes/lum/test/Test.class").exists())
    }
}