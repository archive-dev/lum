package lum.compiler.phases.generation;

import lum.compiler.phases.CompilationResult;

import java.nio.file.Path;
import java.util.HashMap;

public class GeneratedClassesResult extends CompilationResult<HashMap<Path, byte[]>> {
    public GeneratedClassesResult(HashMap<Path, byte[]> classes, Exception error, boolean successful) {
        super(classes, error, successful);
    }
}
