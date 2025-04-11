package lum.core.model;

import java.util.Arrays;

record GenericArgumentImpl(
        String name,
        WildcardIndicator wildcardIndicator,
        TypeModel[] bounds
) implements GenericArgument {
    @Override
    public String toString() {
        if (name() != null)
            return name() + switch (wildcardIndicator()) {
                case NONE -> "";
                case SUPER -> " super " + String.join(", ", Arrays.stream(bounds()).map(TypeModel::model).map(ClassModel::name).toList());
                case EXTENDS -> " extends " + String.join(", ", Arrays.stream(bounds()).map(TypeModel::model).map(ClassModel::name).toList());
            };

        return String.join(", ", Arrays.stream(bounds()).map(TypeModel::model).map(ClassModel::name).toList());
    }
}
