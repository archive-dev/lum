package lum.compiler.phases;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public final class CompilationInfo extends CompilationContext {
    private final Path file;
    private final Path outputDirectory;
    private final Path srcDir;

    public CompilationInfo(Path file) {
        super(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.file = file;
        this.srcDir = Files.isDirectory(file) ? file : file.getParent();
        outputDirectory = null;
    }

    public CompilationInfo(Path file, Path outputDirectory) {
        super(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.file = file;
        this.outputDirectory = outputDirectory;
        this.srcDir = Files.isDirectory(file) ? file : file.getParent();
    }

    public CompilationInfo(Path file, Path outputDirectory, Path srcDir) {
        super(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.file = file;
        this.outputDirectory = outputDirectory;
        this.srcDir = srcDir;
    }

    public Path file() {
        return file;
    }

    public Path outputDirectory() {
        return outputDirectory;
    }

    public Path srcDir() {
        return srcDir;
    }
}
