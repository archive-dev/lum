package lum.compiler.phases.parsing;

import lum.compiler.phases.CompilationResult;
import lum.core.model.ClassModel;

public class ParsedClassesResult extends CompilationResult<ClassModel[]> {
    public ParsedClassesResult(ClassModel[] result, Exception error, boolean successful) {
        super(result, error, successful);
    }
}
