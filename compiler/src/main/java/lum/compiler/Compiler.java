package lum.compiler;

import lum.compiler.phases.CompilationInfo;
import lum.compiler.pipeline.Executor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

@CommandLine.Command(name = "lumc")
public class Compiler implements Callable<Integer> {
    private static final Logger logger = LoggerFactory.getLogger(Compiler.class);

    @CommandLine.Option(names = {"-o", "--output"}, description = "Output directory")
    private Path outputDir = Path.of("");

    @CommandLine.Option(names = "-src", description = "Sources directory")
    private Path srcDir = Path.of("");

    @CommandLine.Parameters(description = "Input file")
    private Path file;

    public Compiler() {}

    public Compiler(Path file) {
        this.file = file;
    }

    public Compiler(Path outputDir, Path file, Path srcDir) {
        this.outputDir = outputDir;
        this.file = file;
        this.srcDir = srcDir;
    }

    public static int compile(Path outputDir, Path file, Path srcDir) {
        return new Compiler(outputDir, file, srcDir).compile();
    }

    public int compile() {
        Path defaultPath = Path.of("");
        if (srcDir.equals(defaultPath))
            srcDir = Files.isDirectory(file) ? file : Objects.requireNonNullElse(file.getParent(), defaultPath);

        Executor executor = new Executor();

        if (!file.toFile().isDirectory()) {
            CompilationInfo compilationInfo = new CompilationInfo(file, outputDir);
            var res = executor.execute(compilationInfo);

            res.printInfo();
            res.printWarnings();

            if (!res.errors().isEmpty()) {
                res.printErrors();
                return 1;
            }
        } else {
            List<File> files = listFilesRecursive(file.toFile());

            for (var f : files) {
                CompilationInfo compilationInfo = new CompilationInfo(f.toPath(), outputDir, srcDir);
                var res = executor.execute(compilationInfo);

                res.printInfo();
                res.printWarnings();

                if (!res.errors().isEmpty()) {
                    res.printErrors();
                    return 1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Compiler()).execute(args);
        System.exit(exitCode);
    }

    private List<File> listFilesRecursive(File directory) {
        List<File> files = new ArrayList<>();
        try (Stream<Path> pathStream = Files.walk(directory.toPath())) {
            files = pathStream.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".lum"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Error listing files recursively", e);
        }
        return files;
    }

    public Path srcDir() {
        return srcDir;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     */
    @Override
    public Integer call() {
        return compile(outputDir, file, srcDir);
    }
}
