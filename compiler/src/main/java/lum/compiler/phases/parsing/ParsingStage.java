package lum.compiler.phases.parsing;

import lum.compiler.phases.CompilationException;
import lum.compiler.phases.CompilationInfo;
import lum.compiler.phases.CompilationResult;
import lum.compiler.phases.CompilerStage;
import lum.core.parsing.ParserFactory;
import lum.core.parsing.antlr4.LumParser;

import java.io.IOException;

public class ParsingStage implements CompilerStage<CompilationInfo, CompilationResult<Object>, ProgramContextResult> {
    @Override
    public ProgramContextResult execute(CompilationInfo context, CompilationResult<Object> __) throws CompilationException {
        LumParser.ProgramContext program = null;
        CompilationException error = null;
        try {
            program = ParserFactory.createProgramContext(context.file());
        } catch (IOException e) {
            error = new CompilationException(e.getMessage());
        }
        return new ProgramContextResult(program, error, error == null);
    }
}
