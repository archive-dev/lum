package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.util.Arrays;
import java.util.Set;

record MethodModelImpl(
        ClassModel owner,
        String name,
        TypeModel returnType,
        ParameterModel[] parameters,
        TypeModel[] exceptions,
        Set<AccessFlag> accessFlags,
        GenericArgument[] genericArguments,
        ClassModel[] annotations
) implements MethodModel {
    @Override
    public String toString() {
        return "MethodModelImpl[" +
                "name=" + name + ", " +
                "returnType=" + returnType + ", " +
                "parameters=" + Arrays.toString(parameters) + ", " +
                "exceptions=" + Arrays.toString(exceptions) + ", " +
                "accessFlags=" + accessFlags + ", " +
                "genericParameters=" + Arrays.toString(genericArguments) + ']';
    }
}
