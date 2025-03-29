package lum.core.model;

import lum.core.parsing.antlr4.LumParser;
import lum.core.util.Utils;

import java.lang.reflect.AccessFlag;
import java.util.*;

import static lum.core.util.Utils.EMPTY_CLASS_MODELS;
import static lum.core.util.Utils.EMPTY_GENERIC_PARAMETERS;
import static lum.core.util.Utils.EMPTY_PARAMETERS;
import static lum.core.util.Utils.EMPTY_TYPE_MODELS;

public final class ClassModelProcessor {
    private ClassModelProcessor() {}

    public static void processClassMembers(ClassModel model, Imports imports, LumParser.ClassDeclarationContext ctx) {
        List<LumParser.DeclarationContext> declarations = Collections.emptyList();
        if (ctx != null && ctx.block() != null) {
            declarations = ctx.block().statement().stream()
                    .map(LumParser.StatementContext::declaration)
                    .filter(Objects::nonNull)
                    .toList();
        }

        processMembers(model, imports, declarations);
    }

    public static void processInterfaceMembers(ClassModel model, Imports imports, LumParser.InterfaceDeclarationContext ctx) {
        List<LumParser.DeclarationContext> declarations = ctx.block().statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .toList();

        processMembers(model, imports, declarations);
    }

    private static void processMembers(ClassModel owner, Imports imports, List<LumParser.DeclarationContext> declarations) {
        for (var decl : declarations) {
            if (decl.variableDeclarationStatement() != null) {
                if (owner.isInterface()) {
                    createInterfaceFields(owner, imports, decl.variableDeclarationStatement());
                } else {
                    createFieldModels(owner, imports, decl.variableDeclarationStatement());
                }
            }
            if (decl.functionDeclaration() != null) {
                createMethodModel(owner, imports, decl.functionDeclaration());
            }
            if (decl.operatorDeclaration() != null) {
                createMethodModel(owner, imports, decl.operatorDeclaration());
            }
            if (decl.constructorDeclaration() != null) {
                createMethodModel(owner, imports, decl.constructorDeclaration());
            }
            if (decl.functionSignature() != null) {
                createMethodModel(owner, imports, decl.functionSignature());
            }
        }
    }

    public static List<MethodModel> createInterfaceFields(ClassModel owner, Imports imports, LumParser.VariableDeclarationStatementContext ctx) {
        List<MethodModel> models = new ArrayList<>();
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        for (var decl : ctx.variableDeclaration()) {
            var type = imports.getType(decl.type());
            var name = decl.IDENTIFIER().getText();

            String titledName = Utils.toTitled(name);
            var getter = new MethodModelImpl(
                    owner,
                    "get"+ titledName,
                    type,
                    EMPTY_PARAMETERS,
                    EMPTY_TYPE_MODELS,
                    accessFlags,
                    EMPTY_GENERIC_PARAMETERS,
                    EMPTY_CLASS_MODELS
            );

            var setter = new MethodModelImpl(
                    owner,
                    "set" + titledName,
                    type,
                    EMPTY_PARAMETERS,
                    EMPTY_TYPE_MODELS,
                    accessFlags,
                    EMPTY_GENERIC_PARAMETERS,
                    EMPTY_CLASS_MODELS
            );

            models.add(getter);
            models.add(setter);
            ModelCache.cacheMethod(getter);
            ModelCache.cacheMethod(setter);
        }

        return models;
    }

    private static final HashMap<Integer, MethodModel> methodModelCache = new HashMap<>();

    public static MethodModel createMethodModel(ClassModel owner, Imports imports, LumParser.FunctionDeclarationContext ctx) {
        if (methodModelCache.containsKey(Objects.hash(ctx)))
            return methodModelCache.get(Objects.hash(ctx));

        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        if (owner.isInterface() && ctx.block() == null)
            accessFlags.add(AccessFlag.ABSTRACT);

        var name = ctx.IDENTIFIER().getText();
        var parameters = createParameterModels(imports, ctx.parameterList());

        var type = TypeModel.VOID;
        if (ctx.type() != null)
            type = imports.getType(ctx.type());


        var model = new MethodModelImpl(
                owner,
                name,
                type,
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                EMPTY_GENERIC_PARAMETERS,
                EMPTY_CLASS_MODELS
        );
        methodModelCache.put(Objects.hash(ctx), model);

        ModelCache.cacheMethod(model);
        return model;
    }

    public static MethodModel createMethodModel(ClassModel owner, Imports imports, LumParser.FunctionSignatureContext ctx) {
        if (methodModelCache.containsKey(Objects.hash(ctx)))
            return methodModelCache.get(Objects.hash(ctx));

        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        accessFlags.add(AccessFlag.ABSTRACT);

        var name = ctx.IDENTIFIER().getText();
        var parameters = createParameterModels(imports, ctx.parameterList());

        var model = new MethodModelImpl(
                owner,
                name,
                imports.getType(ctx.type()),
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                EMPTY_GENERIC_PARAMETERS,
                EMPTY_CLASS_MODELS
        );
        methodModelCache.put(Objects.hash(ctx), model);

        ModelCache.cacheMethod(model);
        return model;
    }

    public static MethodModel createMethodModel(ClassModel owner, Imports imports, LumParser.OperatorDeclarationContext ctx) {
        if (methodModelCache.containsKey(Objects.hash(ctx)))
            return methodModelCache.get(Objects.hash(ctx));

        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        if (owner.isInterface() && ctx.block() == null)
            accessFlags.add(AccessFlag.ABSTRACT);

        var name = ctx.operator().getText();
        var parameters = createParameterModels(imports, ctx.parameterList());

        var model = new MethodModelImpl(
                owner,
                name,
                imports.getType(ctx.type()),
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                EMPTY_GENERIC_PARAMETERS,
                EMPTY_CLASS_MODELS
        );
        methodModelCache.put(Objects.hash(ctx), model);

        ModelCache.cacheMethod(model);
        return model;
    }

    public static MethodModel createMethodModel(ClassModel owner, Imports imports, LumParser.ConstructorDeclarationContext ctx) {
        if (methodModelCache.containsKey(Objects.hash(ctx)))
            return methodModelCache.get(Objects.hash(ctx));

        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        var name = "<init>";
        var parameters = createParameterModels(imports, ctx.parameterList());

        var model = new MethodModelImpl(
                owner,
                name,
                TypeModel.of(void.class),
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                EMPTY_GENERIC_PARAMETERS,
                EMPTY_CLASS_MODELS
        );
        methodModelCache.put(Objects.hash(ctx), model);

        ModelCache.cacheMethod(model);
        return model;
    }

    private static ParameterModel[] createParameterModels(Imports imports, LumParser.ParameterListContext ctx) {
        if (ctx == null) return EMPTY_PARAMETERS;
        ArrayList<ParameterModel> parameterModels = new ArrayList<>();
        for (var param : ctx.parameter()) {
            parameterModels.add(createParameterModel(imports, param));
        }
        return parameterModels.toArray(ParameterModel[]::new);
    }

    private static ParameterModel createParameterModel(Imports imports, LumParser.ParameterContext ctx) {
        return new ParameterModelImpl(
                ctx.IDENTIFIER().getText(),
                imports.getType(ctx.type())
        );
    }

    public static List<FieldModel> createFieldModels(ClassModel owner, Imports imports, LumParser.VariableDeclarationStatementContext ctx) {
        List<FieldModel> models = new ArrayList<>();
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        for (var decl : ctx.variableDeclaration()) {
            var type = imports.getType(decl.type());
            var name = decl.IDENTIFIER().getText();

            var field = new FieldModelImpl(
                    owner,
                    name,
                    type,
                    accessFlags,
                    EMPTY_GENERIC_PARAMETERS,
                    EMPTY_CLASS_MODELS
            );
            models.add(field);
            ModelCache.cacheField(field);

            processFieldAccessors(owner, imports, field, decl);
        }

        return models;
    }

    private static void processFieldAccessors(ClassModel owner, Imports imports, FieldModel field, LumParser.VariableDeclarationContext decl) {
        if (decl.getterDeclaration() != null && !decl.setterDeclaration().isEmpty()) {
            createGetterMethod(owner, field, decl.getterDeclaration(0));
        }
        if (decl.setterDeclaration() != null && !decl.setterDeclaration().isEmpty()) {
            for (var setter : decl.setterDeclaration()) {
                createSetterMethod(owner, imports, field, setter);
            }
        }
    }

    private static void createGetterMethod(ClassModel owner, FieldModel field, LumParser.GetterDeclarationContext ctx) {
        var accessFlags = field.accessFlags();
        if (ctx.access() != null) {
            accessFlags = Utils.getAccessFlags(ctx.access(), null);
        }

        var model = new MethodModelImpl(
                owner,
                "get" + Utils.toTitled(field.name()),
                field.type(),
                EMPTY_PARAMETERS,
                EMPTY_TYPE_MODELS,
                accessFlags,
                EMPTY_GENERIC_PARAMETERS,
                EMPTY_CLASS_MODELS
        );

        ModelCache.cacheMethod(model);
    }

    private static void createSetterMethod(ClassModel owner, Imports imports, FieldModel field, LumParser.SetterDeclarationContext ctx) {
        var accessFlags = field.accessFlags();
        if (ctx.access() != null) {
            accessFlags = Utils.getAccessFlags(ctx.access(), null);
        }

        var parameters = new ParameterModel[]{createParameterModel(imports, ctx.parameter())};

        var model = new MethodModelImpl(
                owner,
                "set" + Utils.toTitled(field.name()),
                TypeModel.of(void.class),
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                EMPTY_GENERIC_PARAMETERS,
                EMPTY_CLASS_MODELS
        );

        ModelCache.cacheMethod(model);
    }
}