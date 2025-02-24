package lum.core.model;

import lum.core.parsing.antlr4.LumParser;

import java.util.List;
import java.util.Map;

public interface Imports {
    TypeModel getType(LumParser.TypeContext t);

    default TypeModel getType(List<String> pathElements) {
        return getType(String.join(".", pathElements));
    }

    TypeModel getType(String typeName);

    MethodModel getMethod(String methodName);

    MethodModel getMethod(String methodName, List<TypeModel> args);

    Map<String, ClassModel> classes();

    Map<String, Map<List<TypeModel>, MethodModel>> methods();

    Map<String, FieldModel> fields();
}
