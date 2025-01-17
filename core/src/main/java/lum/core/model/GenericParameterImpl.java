package lum.core.model;

record GenericParameterImpl(
        String genericName,
        WildcardIndicator wildcardIndicator,
        ClassModel bound
) implements GenericParameter {
    @Override
    public String toString() {
        return genericName() + switch (wildcardIndicator()) {
            case NONE -> "";
            case SUPER -> "super" + bound().name();
            case EXTENDS -> "extends" + bound().name();
        };
    }
}
