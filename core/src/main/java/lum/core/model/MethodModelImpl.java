package lum.core.model;

import java.lang.constant.MethodTypeDesc;
import java.lang.reflect.AccessFlag;
import java.util.Arrays;
import java.util.List;

record MethodModelImpl(
        ClassModel owner,
        String name,
        TypeModel returnType,
        ParameterModel[] parameters,
        TypeModel[] exceptions,
        List<AccessFlag> accessFlags,
        GenericParameter[] genericParameters
) implements MethodModel {
    @Override
    public MethodTypeDesc methodTypeDesc() {
        return MethodTypeDesc.of(
                returnType().classDesc(),
                Arrays.stream(parameters()).map(ParameterModel::type).map(TypeModel::classDesc).toList()
        );
    }

    @Override
    public String toString() {
        return "MethodModelImpl[" +
                "name=" + name + ", " +
                "returnType=" + returnType + ", " +
                "parameters=" + Arrays.toString(parameters) + ", " +
                "exceptions=" + Arrays.toString(exceptions) + ", " +
                "accessFlags=" + accessFlags + ", " +
                "genericParameters=" + Arrays.toString(genericParameters) + ']';
    }
}
