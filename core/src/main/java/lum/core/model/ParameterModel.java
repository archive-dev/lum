package lum.core.model;

public interface ParameterModel extends GenericTyped {
    String name();

    TypeModel type();

    GenericParameter[] genericParameters();
}
