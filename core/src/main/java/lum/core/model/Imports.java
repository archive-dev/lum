package lum.core.model;

import lum.core.parsing.antlr4.LumParser;

import java.util.HashMap;

/**
 *
 * @param classes map that contains fully qualified names of classes mapped by their short names
 * @param methods map that contains fully qualified names of methods mapped by their short names
 * @param fields map that contains fully qualified names of fields mapped by their short names
 */
public record Imports(
        HashMap<String, ClassModel> classes,
        HashMap<String, MethodModel> methods,
        HashMap<String, FieldModel> fields
) {
    public TypeModel get(LumParser.TypeContext t) {
        return classes().get(t.IDENTIFIER().getText()).typeModel();
    }
}
