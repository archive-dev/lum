package lum.core.model;

record ParameterModelImpl(
        String name,
        TypeModel type,
        GenericParameter[] genericParameters
) implements ParameterModel {
}
