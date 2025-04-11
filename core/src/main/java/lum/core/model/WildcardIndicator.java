package lum.core.model;

import lum.core.parsing.antlr4.LumParser;

import java.lang.classfile.Signature;

public enum WildcardIndicator {
    EXTENDS(Signature.TypeArg.Bounded.WildcardIndicator.EXTENDS),
    SUPER(Signature.TypeArg.Bounded.WildcardIndicator.SUPER),
    NONE(Signature.TypeArg.Bounded.WildcardIndicator.NONE);

    private final Signature.TypeArg.Bounded.WildcardIndicator value;

    WildcardIndicator(Signature.TypeArg.Bounded.WildcardIndicator value) {
        this.value = value;
    }

    public Signature.TypeArg.Bounded.WildcardIndicator getValue() {
        return value;
    }

    public static Signature.TypeArg.Bounded.WildcardIndicator getIndicator(LumParser.GenericContext ctx) {
        if (ctx instanceof LumParser.BoundGenericContext bound) {
            if (bound.extends_ != null)
                return EXTENDS.getValue();
            if (bound.super_ != null)
                return SUPER.getValue();
        }
        return NONE.getValue();
    }
}
