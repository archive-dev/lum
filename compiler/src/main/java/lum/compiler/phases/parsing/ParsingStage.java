package lum.compiler.phases.parsing;

import lum.compiler.phases.CompilationException;
import lum.compiler.phases.CompilationInfo;
import lum.compiler.phases.CompilationResult;
import lum.compiler.phases.CompilerStage;
import lum.core.model.ClassModel;

public class ParsingStage implements CompilerStage<CompilationInfo, CompilationResult<?>, ParsedClassesResult> {
    private static final ClassModel[] EMPTY_CLASS_MODELS = new ClassModel[0];

    @Override
    public ParsedClassesResult execute(CompilationInfo context, CompilationResult<?> previousResult) throws CompilationException {
        var classes = ClassModel.ofFile(context.srcDir(), context.file());

        return new ParsedClassesResult(classes.orElse(EMPTY_CLASS_MODELS), null, classes.isPresent());
    }
}
