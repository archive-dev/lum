package lum.compiler.phases.generation;

import lum.compiler.phases.CompilationException;
import lum.compiler.phases.CompilationResult;

public class ClassWritingResult extends CompilationResult<Boolean> {
    public ClassWritingResult(Exception error, boolean successful) {
        super(error, successful);
    }

    @Override
    public Boolean intermediateResult() {
        return successful();
    }
}
