package lum.core.model;

import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.util.Arrays;

public interface MethodModel extends Attributable, Member, Generic {
    ParameterModel[] parameters();
    TypeModel returnType();
    ClassModel[] exceptions();

    default MethodTypeDesc methodTypeDesc() {
        return MethodTypeDesc.of(
                returnType().classDesc(),
                Arrays.stream(parameters())
                        .map(ParameterModel::type)
                        .map(TypeModel::classDesc)
                        .toArray(ClassDesc[]::new)
        );
    }
}
