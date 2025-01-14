package lum.core.model;

public interface GenericParameter {
    String genericName();

    WildcardIndicator wildcardIndicator();

    ClassModel bound();
}
