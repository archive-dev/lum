package lum.compiler.phases.parsing;

import lum.compiler.phases.CompilationResult;
import lum.core.parsing.antlr4.LumParser;

public class ProgramContextResult extends CompilationResult<LumParser.ProgramContext> {
    private final LumParser.ProgramContext program;

    public ProgramContextResult(LumParser.ProgramContext program, Exception error, boolean successful) {
        super(error, successful);
        this.program = program;
    }

    @Override
    public LumParser.ProgramContext intermediateResult() {
        return program;
    }
}
