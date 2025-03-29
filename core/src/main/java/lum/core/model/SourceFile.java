package lum.core.model;

import lum.core.parsing.antlr4.LumParser;

@Deprecated
public interface SourceFile {
    Imports imports();

    ClassModel[] classes();

    MethodModel[] methods();

    FieldModel[] fields();

//    static SourceFile from(LumParser.ProgramContext programContext) {
//        return new SourceFileImpl(programContext);
//    }
}
