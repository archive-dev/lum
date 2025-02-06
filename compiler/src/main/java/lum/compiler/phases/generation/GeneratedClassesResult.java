package lum.compiler.phases.generation;

import lum.compiler.phases.CompilationException;
import lum.compiler.phases.CompilationResult;

import java.nio.file.Path;
import java.util.HashMap;

public class GeneratedClassesResult extends CompilationResult<HashMap<Path, byte[]>> {
    private final HashMap<Path, byte[]> classes;

    public GeneratedClassesResult(HashMap<Path, byte[]> classes, CompilationException error, boolean successful) {
        super(error, successful);
        this.classes = classes;
    }

    @Override
    public HashMap<Path, byte[]> intermediateResult() {
        return classes;
    }
}
