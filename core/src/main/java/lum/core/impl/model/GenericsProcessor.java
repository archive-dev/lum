package lum.core.impl.model;

import lum.antlr4.LumParser;
import lum.core.model.*;
import lum.lang.struct.Pair;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.classfile.Signature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// one per generic member
class GenericsProcessor {
    private static final Logger log = LoggerFactory.getLogger(GenericsProcessor.class);
    private final TypeProcessor typeProcessor;


    private final MethodModel methodModel;
    private final Map<String, TypeParameter> genericBounds = new HashMap<>();

    public GenericsProcessor(TypeProcessor typeProcessor) {
        this.methodModel = null;
        this.typeProcessor = typeProcessor;
        if (typeProcessor.genericsProcessor != null)
            this.genericBounds.putAll(typeProcessor.genericsProcessor.genericBounds);
    }

    public TypeParameter[] processGenericParameters(LumParser.GenericContext ctx) {
        ArrayList<TypeParameter> params = new ArrayList<>();
        if (ctx != null) {
            for (var bound : ctx.genericBound()) {
                TypeParameter param = processGenericBound(bound);
                genericBounds.put(param.name(), param);
                params.add(param);
            }
        }

        return params.toArray(TypeParameter[]::new);
    }

    public TypeParameter[] processGenericParameters(TypeParameter[] to, LumParser.GenericContext ctx) {
        ArrayList<TypeParameter> params = new ArrayList<>();
        if (ctx != null) {
            for (var bound : ctx.genericBound()) {
                TypeParameter param = processGenericBound(bound);
                genericBounds.put(param.name(), param);
                params.add(param);
            }
        }

        return params.toArray(to);
    }

    private TypeParameter processGenericBound(LumParser.GenericBoundContext ctx) {
        String typeAlias;
        if (ctx.lhs instanceof LumParser.PlainTypeContext plainType && plainType.generic() == null)
            typeAlias = plainType.getText();
        else throw new IllegalStateException("Incorrect name for generic type: %s".formatted(ctx.lhs.getText()));

        if (ctx.rhs == null)
            return new TypeParameterImpl(
                            typeAlias,
                            ClassModel.of(Object.class).orElseThrow().asTypeModel(),
                            Signature.TypeArg.Bounded.WildcardIndicator.EXTENDS
            );
        else
            return new TypeParameterImpl(
                            typeAlias,
                            typeProcessor.getType(ctx.rhs).orElseThrow(() -> new IllegalStateException("Type not found: " + ctx.rhs.getText())),
                            Signature.TypeArg.Bounded.WildcardIndicator.EXTENDS
            );
    }

    public Optional<TypeArgument[]> processGenericArguments(TypeParameter @NotNull [] parameters, LumParser.GenericContext ctx) {
        if (ctx == null || ctx.genericBound() == null || ctx.genericBound().isEmpty())
            return Optional.empty();

        if (parameters.length > ctx.genericBound().size())
            log.warn(
                    "Parameters length is greater than count of provided arguments. Expected count: {}, actual: {}",
                    parameters.length,
                    ctx.genericBound().size()
            );
        else if (parameters.length < ctx.genericBound().size()) {
            log.error(
                    "Parameters length is less than count of provided arguments. Expected count: {}, actual: {}",
                    parameters.length,
                    ctx.genericBound().size()
            );
            throw new IllegalStateException();
        }

        TypeArgument[] args = new TypeArgument[ctx.genericBound().size()];

        for (int i = 0; i < ctx.genericBound().size(); i++) {
            var bound = ctx.genericBound(i);
            var param = parameters[i];

            args[i] = processGenericArgument(param, bound);
        }

        return Optional.of(args);
    }

    private TypeArgument processGenericArgument(TypeParameter parameter, LumParser.GenericBoundContext ctx) {
        if (ctx.QUESTION() != null) {
            if (ctx.rhs == null)
                return new TypeArgumentImpl(
                        parameter,
                        ClassModel.of(Object.class).orElseThrow().asTypeModel()
                );

            return new TypeArgumentImpl(
                    parameter,
                    typeProcessor.getType(ctx.rhs).orElseThrow(() -> new IllegalStateException("Type not found: " + ctx.rhs.getText()))
            );
        }

        if (ctx.rhs != null)
            throw new IllegalStateException("Incorrect generic argument declaration: "+ctx.getText());

        return new TypeArgumentImpl(
                parameter,
                typeProcessor.getType(ctx.lhs).orElseThrow(() -> new IllegalStateException("Type not found: " + ctx.rhs.getText()))
        );
    }

    public Optional<TypeParameter> getTypeParameter(String name) {
        return Optional.ofNullable(genericBounds.get(name));
    }
}
