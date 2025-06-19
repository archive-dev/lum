package lum.core.model;

import java.lang.classfile.Signature;

public interface TypeParameter {
    TypeModel bound();
    Signature.TypeArg.Bounded.WildcardIndicator wildcardType();
}
