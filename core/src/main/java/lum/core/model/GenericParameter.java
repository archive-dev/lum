package lum.core.model;

public record GenericParameter (
        String genericName,
        WildcardIndicator wildcardIndicator,
        ClassModel bound
) {}
