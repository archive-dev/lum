package lum.compiler.phases;

import java.nio.file.Path;
import java.util.List;

public final class CompilationInfo extends CompilationContext {
    private final Path file;

    public CompilationInfo(Path file, List<String> warnings, List<Exception> errors) {
        this.file = file;
        super(warnings, errors);
    }

    public Path file() {
        return file;
    }
}
