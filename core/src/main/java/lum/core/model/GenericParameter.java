package lum.core.model;

import java.util.Arrays;

public interface GenericParameter {
    String genericName();

    WildcardIndicator wildcardIndicator();

    ClassModel bound();

    static String toString(GenericParameter[] genericParameters) {
        return "<" + String.join(",", Arrays.stream(genericParameters)
                .map(GenericParameter::toString)
                .toList()) + ">";
    }
}
