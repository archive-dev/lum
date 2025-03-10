package lum.compiler.phases.parsing;

import lum.compiler.phases.CompilationResult;
import lum.core.model.ClassModel;
import lum.core.parsing.antlr4.LumParser;

import java.util.Set;

public class ClassDefinitionsResult extends CompilationResult<Set<ClassModel>> {
    private final Set<ClassModel> models;
    private final LumParser.ProgramContext ctx;

    public ClassDefinitionsResult(LumParser.ProgramContext ctx, Set<ClassModel> models, Exception error, boolean successful) {
        super(error, successful);
        this.ctx = ctx;
        this.models = models;
    }

    @Override
    public Set<ClassModel> intermediateResult() {
        return models;
    }

    public LumParser.ProgramContext ctx() {
        return ctx;
    }
}
