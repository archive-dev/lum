package lum.core.impl;

import lum.core.model.TypeModel;
import lum.core.model.TypeParameter;

import java.lang.classfile.Signature;

public record TypeParameterImpl(
        TypeModel bound,
        Signature.TypeArg.Bounded.WildcardIndicator wildcardType
) implements TypeParameter {}
