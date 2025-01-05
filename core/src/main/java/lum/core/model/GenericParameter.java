package lum.core.model;

import java.util.HashSet;

public record GenericParameter(
        String name,
        HashSet<ClassModel> bounds
) {
}
