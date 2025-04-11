package lum.core.model;

import java.lang.constant.MethodTypeDesc;
import java.util.Arrays;

public interface MethodModel extends Accessible, Parametrized, Member {
    String name();

    TypeModel returnType();

    ParameterModel[] parameters();

    TypeModel[] exceptions();

    default MethodTypeDesc methodTypeDesc() {
        return MethodTypeDesc.of(
                returnType().classDesc(),
                Arrays.stream(parameters()).map(ParameterModel::type).map(TypeModel::classDesc).toList()
        );
    }
}
