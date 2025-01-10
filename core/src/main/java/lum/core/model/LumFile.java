package lum.core.model;

import lum.core.parsing.antlr4.LumParser;

public record LumFile(
        Imports imports,
        ClassModel[] classes,
        MethodModel[] methods,
        FieldModel[] fields
) {
    public static LumFile from(LumParser.ProgramContext programContext) {
        return null;
    }
}