package lum.compiler.phases.generation;

import lum.compiler.phases.CompilationException;
import lum.compiler.phases.CompilationInfo;
import lum.compiler.phases.CompilerStage;
import lum.compiler.phases.parsing.ProgramContextResult;
import lum.core.model.ModelsParser;

public class CodeGenerationStage implements CompilerStage<CompilationInfo, ProgramContextResult, GeneratedClassesResult> {
    @Override
    public GeneratedClassesResult execute(CompilationInfo context, ProgramContextResult result) throws CompilationException {
        var classModels = ModelsParser.parseClassModels(result.intermediateResult());
        return null; // todo
    }


}
