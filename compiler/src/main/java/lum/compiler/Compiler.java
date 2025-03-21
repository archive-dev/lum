package lum.compiler;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import com.beust.jcommander.converters.PathConverter;
import lum.compiler.phases.CompilationContext;
import lum.compiler.phases.CompilationInfo;
import lum.compiler.pipeline.Executor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Compiler {
    @Parameter(names = {"-o", "--output"}, description = "Output directory", converter = PathConverter.class)
    private Path outputDir = null;

    @Parameter(description = "Input file", converter = PathConverter.class)
    private Path file;

    @Parameter(names = "-src", description = "Sources directory", converter = PathConverter.class)
    private Path srcDir;

    public int compile() {
        if (srcDir == null)
            srcDir = Files.isDirectory(file) ? file : file.getParent();

        if (!file.toFile().isDirectory()) {
            var res = new Executor().execute(new CompilationInfo(file, outputDir));
            res.info().add(outputDir.toAbsolutePath().toString());

            res.printInfo();
            res.printWarnings();

            if (!res.errors().isEmpty()) {
                res.print();
                return 1;
            }
        } else {
            Executor executor = new Executor();
            var files = listFilesRecursive(file.toFile(), new ArrayList<>());

            for (var file : files) {
                var res = executor.execute(new CompilationInfo(file.toPath(), outputDir));
                res.info().add(outputDir.toAbsolutePath().toString());

                res.printInfo();
                res.printWarnings();

                if (!res.errors().isEmpty()) {
                    res.print();
                    return 1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Compiler compilerArgs = new Compiler();

        JCommander.newBuilder()
                .addObject(compilerArgs)
                .build()
                .parse(args);

        compilerArgs.compile();
    }

    private static List<File> listFilesRecursive(File directory, List<File> files) {
        files.addAll(
                Arrays.stream(Objects.requireNonNull(
                        directory.listFiles(
                                file -> !file.isDirectory() && file.getName().endsWith(".lum")
                            )
                        )
                    )
                .toList());

        for (File file : Objects.requireNonNull(directory.listFiles(File::isDirectory))) {
            listFilesRecursive(file, files);
        }

        return files;
    }
}
