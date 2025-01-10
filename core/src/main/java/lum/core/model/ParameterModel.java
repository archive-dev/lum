package lum.core.model;

public record ParameterModel (
        String name,
        TypeModel type,
        GenericParameter[] genericParameters
) implements GenericTyped {
}
