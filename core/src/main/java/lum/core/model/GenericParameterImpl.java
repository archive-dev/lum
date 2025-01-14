package lum.core.model;

record GenericParameterImpl(
        String genericName,
        WildcardIndicator wildcardIndicator,
        ClassModel bound
) implements GenericParameter {}
