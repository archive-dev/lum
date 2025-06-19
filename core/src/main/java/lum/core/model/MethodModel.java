package lum.core.model;

public interface MethodModel extends Attributable, Member, Generic {
    ParameterModel[] parameters();
    TypeModel returnType();
    ClassModel[] exceptions();
}
