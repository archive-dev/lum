package lum.core.model;

import java.lang.reflect.*;
import java.util.Arrays;

public interface GenericArgument {
    String name();

    WildcardIndicator wildcardIndicator();

    TypeModel[] bounds();

    static String toString(GenericArgument[] genericParameters) {
        return "<" + String.join(",", Arrays.stream(genericParameters)
                .map(GenericArgument::toString)
                .toList()) + ">";
    }

    static GenericArgument[] of(GenericDeclaration genericDeclaration) {
        var parameters = genericDeclaration.getTypeParameters();
        GenericArgument[] params = new GenericArgument[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            var param = parameters[i];
            params[i] = of(param);
        }

        return params;
    }

    static GenericArgument[] of(Field field) {
        var parameters = field.getType().getTypeParameters();
        GenericArgument[] params = new GenericArgument[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            var param = parameters[i];
            params[i] = of(param);
        }

        return params;
    }

    static GenericArgument of(TypeVariable<?> var) {
        if (ModelCache.genericArguments.containsKey(var))
            return ModelCache.genericArguments.get(var);
        var arg = new GenericArgumentImpl(var.getName(), WildcardIndicator.EXTENDS, Arrays.stream(var.getBounds()).map(TypeModel::of).toArray(TypeModel[]::new));
        ModelCache.genericArguments.put(var, arg);

        return arg;
    }
}
