package lum.core.model;

import lum.core.parsing.antlr4.LumParser;

import java.util.HashMap;
import java.util.List;

public interface Imports {
    TypeModel getType(LumParser.TypeContext t);

    TypeModel getType(List<String> pathElements);

    HashMap<String, ClassModel> classes();

    HashMap<String, MethodModel> methods();

    HashMap<String, FieldModel> fields();
}
