package lum.core.impl.model;

import lum.antlr4.LumParser;
import lum.core.model.*;
import lum.core.util.Utils;

import java.lang.reflect.AccessFlag;
import java.util.*;

final class MethodModelParser {
    private MethodModelParser() {}

    private static final Map<Class<? extends LumParser.OperatorKeywordContext>, String> operatorNames = new HashMap<>();
    static {
        operatorNames.put(LumParser.ATContext.class, "at");
        operatorNames.put(LumParser.MULContext.class, "multiply");
        operatorNames.put(LumParser.DIVIDEContext.class, "divide");
        operatorNames.put(LumParser.DIVContext.class, "div");
        operatorNames.put(LumParser.MODContext.class, "mod");
        operatorNames.put(LumParser.ADDContext.class, "add");
        operatorNames.put(LumParser.SUBContext.class, "subtract");
        operatorNames.put(LumParser.SHRContext.class, "shr");
        operatorNames.put(LumParser.SHLContext.class, "shl");
        operatorNames.put(LumParser.GTContext.class, "greaterThan");
        operatorNames.put(LumParser.LTContext.class, "lessThan");
        operatorNames.put(LumParser.GEContext.class, "greaterEquals");
        operatorNames.put(LumParser.LEContext.class, "lessEquals");
        operatorNames.put(LumParser.EQContext.class, "equals");
        operatorNames.put(LumParser.ISINSTANCEContext.class, "isInstance");
        operatorNames.put(LumParser.INContext.class, "contains");
        operatorNames.put(LumParser.ANDContext.class, "and");
        operatorNames.put(LumParser.ORContext.class, "or");
        operatorNames.put(LumParser.XORContext.class, "xor");
        operatorNames.put(LumParser.NOTContext.class, "not");
        operatorNames.put(LumParser.INCContext.class, "increment");
        operatorNames.put(LumParser.DECContext.class, "decrement");
        operatorNames.put(LumParser.ARRAY_OPContext.class, "get");
        operatorNames.put(LumParser.ARRAYSET_OPContext.class, "set");
    }

    // todo
    static MethodModel buildMethodModel(ClassModel owner, TypeProcessor typeProcessor, LumParser.FuncMemberContext ctx) {
        String methodName = ctx.func().IDENTIFIER().getText();

        Set<AccessFlag> flagsSet = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        Optional<TypeParameter[]> typeParameters;
        if (ctx.func().generic() == null)
            typeParameters = Optional.empty();
        else typeParameters = Optional.of(new TypeParameter[ctx.func().generic().genericBound().size()]);

        if (typeParameters.isPresent()) {
            var genericsProcessor = new GenericsProcessor(typeProcessor);
            typeProcessor = new TypeProcessor(typeProcessor, genericsProcessor);
            genericsProcessor.processGenericParameters(typeParameters.get(), ctx.func().generic());
        }

        TypeModel returnType = typeProcessor.getType(ctx.func().type()).orElse(TypeModel.VOID);
        ParameterModel[] parameters = buildParameterModels(typeProcessor, ctx.func().parameterList());

        AttributeModel[] attributes = Utils.EMPTY_ATTRIBUTE_MODELS;
        if (ctx.func().codeBlock() != null)
            attributes = new AttributeModel[1];

        AttributeParser<?>[] attributeParsers = new AttributeParser[1];
        MethodModelImpl model = new MethodModelImpl(
                Optional.ofNullable(owner),
                attributes,
                flagsSet.toArray(AccessFlag[]::new),
                methodName,
                typeParameters,
                parameters,
                returnType,
                Utils.EMPTY_CLASS_MODELS,
                Optional.ofNullable(ctx.func().codeBlock()),
                attributeParsers
        );
        attributeParsers[0] = new CodeAttributeParser(model, typeProcessor);

        return model;
    }

    static MethodModel buildMethodModel(ClassModel owner, TypeProcessor typeProcessor, LumParser.OperatorMemberContext ctx) {
        String methodName = operatorNames.get(ctx.operator().operatorKeyword().getClass());

        Set<AccessFlag> flagsSet = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        Optional<TypeParameter[]> typeParameters;
        if (ctx.operator().generic() == null)
            typeParameters = Optional.empty();
        else {
            typeParameters = Optional.of(new TypeParameter[ctx.operator().generic().genericBound().size()]);
        }


        if (typeParameters.isPresent()) {
            var genericsProcessor = new GenericsProcessor(typeProcessor);
            typeProcessor = new TypeProcessor(typeProcessor, genericsProcessor);
            genericsProcessor.processGenericParameters(typeParameters.get(), ctx.operator().generic());
        }

        AttributeModel[] attributes = Utils.EMPTY_ATTRIBUTE_MODELS;
        if (ctx.operator().codeBlock() != null)
            attributes = new AttributeModel[1];

        AttributeParser<?>[] attributeParsers = new AttributeParser[1];
        MethodModelImpl model = new MethodModelImpl(
                Optional.ofNullable(owner),
                attributes,
                flagsSet.toArray(AccessFlag[]::new),
                methodName,
                typeParameters,
                buildParameterModels(typeProcessor, ctx.operator().parameterList()),
                typeProcessor.getType(ctx.operator().type()).orElseThrow(() -> new IllegalStateException("Type not found: " + ctx.operator().type().getText())),
                Utils.EMPTY_CLASS_MODELS,
                Optional.ofNullable(ctx.operator().codeBlock()),
                attributeParsers
        );
        attributeParsers[0] = new CodeAttributeParser(model, typeProcessor);

        return model;
    }

    static MethodModel buildMethodModel(ClassModel owner, TypeProcessor typeProcessor, LumParser.ConstructorMemberContext ctx) {
        String methodName = "<init>";

        Set<AccessFlag> flagsSet = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        Optional<TypeParameter[]> typeParameters;
        if (ctx.constructor().generic() == null)
            typeParameters = Optional.empty();
        else {
            typeParameters = Optional.of(new TypeParameter[ctx.constructor().generic().genericBound().size()]);
        }


        if (typeParameters.isPresent()) {
            var genericsProcessor = new GenericsProcessor(typeProcessor);
            typeProcessor = new TypeProcessor(typeProcessor, genericsProcessor);
            genericsProcessor.processGenericParameters(typeParameters.get(), ctx.constructor().generic());
        }

        AttributeModel[] attributes = new AttributeModel[1];
        AttributeParser<?>[] attributeParsers = new AttributeParser[1];
        MethodModelImpl model = new MethodModelImpl(
                Optional.ofNullable(owner),
                attributes,
                flagsSet.toArray(AccessFlag[]::new),
                methodName,
                typeParameters,
                buildParameterModels(typeProcessor, ctx.constructor().parameterList()),
                TypeModel.of(void.class).orElseThrow(),
                Utils.EMPTY_CLASS_MODELS,
                Optional.ofNullable(ctx.constructor().codeBlock()),
                attributeParsers
        );
        attributeParsers[0] = new CodeAttributeParser(model, typeProcessor);

        return model;
    }

    private static ParameterModel[] buildParameterModels(TypeProcessor typeProcessor, LumParser.ParameterListContext ctx) {
        return ctx.parameter().stream().map(c -> buildParameterModel(typeProcessor, c)).toArray(ParameterModel[]::new);
    }

    private static ParameterModel buildParameterModel(TypeProcessor typeProcessor, LumParser.ParameterContext ctx) {
        return new ParameterModelImpl(
                Utils.EMPTY_ATTRIBUTE_MODELS,
                ctx.IDENTIFIER().getText(),
                typeProcessor.getType(ctx.type()).orElseThrow(() -> new IllegalStateException("Type not found " + ctx.type().getText()))
        );
    }
}
