package lum.core.impl;

import lum.core.model.TypeArgument;
import lum.core.model.TypeModel;
import lum.core.model.TypeParameter;

public record TypeArgumentImpl(
    TypeParameter parameter,
    TypeModel type
) implements TypeArgument {}
