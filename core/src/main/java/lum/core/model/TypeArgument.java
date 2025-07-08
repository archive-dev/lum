package lum.core.model;

import lum.core.impl.model.TypeArgumentImpl;

public interface TypeArgument {
    TypeParameter parameter();
    TypeModel type();

    static TypeArgument of(TypeParameter parameter, TypeModel type) {
        return new TypeArgumentImpl(parameter, type);
    }
}
