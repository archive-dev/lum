package lum.core.model;

import java.lang.constant.MethodTypeDesc;
import java.lang.reflect.AccessFlag;
import java.util.Arrays;
import java.util.List;

public interface MethodModel extends Accessible, GenericTyped, Member {
    String name();

    TypeModel returnType();

    ParameterModel[] parameters();

    TypeModel[] exceptions();

    List<AccessFlag> accessFlags();

    default MethodTypeDesc methodTypeDesc() {
        return MethodTypeDesc.of(
                returnType().classDesc(),
                Arrays.stream(parameters()).map(ParameterModel::type).map(TypeModel::classDesc).toList()
        );
    }
}
