package lum.core.model;

import lum.core.parsing.ParserFactory;
import lum.core.parsing.antlr4.LumParser;
import lum.core.util.Utils;
import lum.lang.struct.Pair;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class ParserModelFactory {
    private static final GenericParameter[] EMPTY_GENERIC_PARAMETERS = new GenericParameter[0];
    private static final TypeModel[] EMPTY_TYPE_MODELS = new TypeModel[0];
    private static final ParameterModel[] EMPTY_PARAMETERS = new ParameterModel[0];

    private ParserModelFactory() {}

    public static Imports createImportsModel(List<LumParser.ImportStatementContext> imports) {
        final HashMap<String, ClassModel> classes = new HashMap<>();

        for (var importStatement : imports) {
            switch (importStatement.getChild(1)) {
                case LumParser.AnySimpleImportContext simple -> handleAnyImportSimple(simple, classes);
                case LumParser.ImportAsContext importAs -> handleImportAs(importAs, classes);
                case LumParser.ImportMultipleContext multi -> handleImportMultiple(multi, classes);
                case LumParser.ImportFromContext from -> handleImportFrom(from, classes);
                default -> throw new IllegalStateException("Unexpected value: " + importStatement.getChild(1));
            }
        }

        return new Imports(
                classes,
                new HashMap<>(),
                new HashMap<>()
        );
    }

    private static void handleImportFrom(LumParser.ImportFromContext from, final HashMap<String, ClassModel> classes) {
        List<String> basePath = from.IDENTIFIER().stream().map(TerminalNode::getText).toList();
        handleImportMultiple(from.importMultiple(), basePath, classes);
    }

    private static void handleImportMultiple(LumParser.ImportMultipleContext multi, final HashMap<String, ClassModel> classes) {
        handleImportMultiple(multi, null, classes);
    }

    private static void handleImportMultiple(LumParser.ImportMultipleContext multi, List<String> basePath, final HashMap<String, ClassModel> classes) {
        multi.anySimpleImport().forEach(imp -> handleAnyImportSimple(imp, basePath, classes));
    }

    private static void handleAnyImportSimple(LumParser.AnySimpleImportContext anyImport, final HashMap<String, ClassModel> classes) {
        handleAnyImportSimple(anyImport, null, classes);
    }

    private static void handleAnyImportSimple(LumParser.AnySimpleImportContext anyImport, List<String> basePath, final HashMap<String, ClassModel> classes) {
        if (anyImport.importAs() != null) {
            handleImportAs(anyImport.importAs(), basePath, classes);
        } else {
            handleSimpleImport(anyImport.importSimple(), basePath, classes);
        }
    }

    private static void handleImportAs(LumParser.ImportAsContext importAs, List<String> basePath, final HashMap<String, ClassModel> classes) {
        List<String> path;
        if (basePath != null) {
            path = new ArrayList<>(basePath);
            path.addAll(importAs.importSimple().IDENTIFIER().stream().map(TerminalNode::getText).toList());
        } else {
            path = importAs.importSimple().IDENTIFIER().stream().map(TerminalNode::getText).toList();
        }

        classes.put(
                importAs.IDENTIFIER().getText(),
                ModelCache.getClassFromPath(path)
        );
    }

    private static void handleImportAs(LumParser.ImportAsContext importAs, final HashMap<String, ClassModel> classes) {
        handleImportAs(importAs, null, classes);
    }

    private static void handleSimpleImport(LumParser.ImportSimpleContext simple, final HashMap<String, ClassModel> classes) {
        handleSimpleImport(simple, null, classes);
    }

    private static void handleSimpleImport(LumParser.ImportSimpleContext simple, List<String> basePath, final HashMap<String, ClassModel> classes) {
        List<String> path;
        if (basePath != null) {
            path = new ArrayList<>(basePath);
            path.addAll(simple.IDENTIFIER().stream().map(TerminalNode::getText).toList());
        } else {
            path = simple.IDENTIFIER().stream().map(TerminalNode::getText).toList();
        }
        classes.put(
                simple.IDENTIFIER().getLast().getText(),
                ModelCache.getClassFromPath(path)
        );
    }

    public static ClassModel createClassModel(ClassPath path) throws IOException {
        LumParser.ProgramContext program = ParserFactory.createParser(path).program();

        Imports imports = createImportsModel(
                program.statement().stream()
                        .map(LumParser.StatementContext::importStatement)
                        .filter(Objects::nonNull)
                        .toList()
        );

        return switch (ParserFactory.getClassContext(program, path.className())) {
            case LumParser.ClassDeclarationContext clazz -> createClassModel(imports, clazz);
            case LumParser.InterfaceDeclarationContext interface_ -> createInterfaceModel(imports, interface_);
            case null, default -> null;
        };
    }

    public static ClassModel createClassModel(Imports imports, LumParser.ClassDeclarationContext ctx) {
        var model = buildClassModel(imports, ctx);
        imports.classes().put(ctx.IDENTIFIER().getText(), model);
        processClassMembers(model, imports, ctx);
        return model;
    }

    public static ClassModel createInterfaceModel(Imports imports, LumParser.InterfaceDeclarationContext ctx) {
        var model = buildInterfaceModel(imports, ctx);
        imports.classes().put(ctx.IDENTIFIER().getText(), model);
        processInterfaceMembers(model, imports, ctx);
        return model;
    }

    private static ClassModel buildClassModel(Imports imports, LumParser.ClassDeclarationContext ctx) {
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        var name = ctx.IDENTIFIER().getText();
        var inheritance = processInheritance(imports, ctx.inheritance());

        return new ClassModel(
                name,
                inheritance.a(),
                inheritance.b().toArray(ClassModel[]::new),
                accessFlags,
                EMPTY_GENERIC_PARAMETERS,
                false
        );
    }

    private static ClassModel buildInterfaceModel(Imports imports, LumParser.InterfaceDeclarationContext ctx) {
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        var name = ctx.IDENTIFIER().getText();
        var inheritance = processInheritance(imports, ctx.inheritance());

        return new ClassModel(
                name,
                inheritance.a(),
                inheritance.b().toArray(ClassModel[]::new),
                accessFlags,
                EMPTY_GENERIC_PARAMETERS,
                true
        );
    }

    private static void processClassMembers(ClassModel model, Imports imports, LumParser.ClassDeclarationContext ctx) {
        ctx.block().statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .forEach(decl -> {
                    if (decl.variableDeclarationStatement() != null) {
                        createFieldModels(model, imports, decl.variableDeclarationStatement());
                    }
                    if (decl.functionDeclaration() != null) {
                        createMethodModel(model, imports, decl.functionDeclaration());
                    }
                    if (decl.operatorDeclaration() != null) {
                        createMethodModel(model, imports, decl.operatorDeclaration());
                    }
                    if (decl.constructorDeclaration() != null) {
                        createMethodModel(model, imports, decl.constructorDeclaration());
                    }
                });
    }

    private static void processInterfaceMembers(ClassModel model, Imports imports, LumParser.InterfaceDeclarationContext ctx) {
        ctx.block().statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .forEach(decl -> {
                    if (decl.variableDeclarationStatement() != null) {
                        createInterfaceFields(model, imports, decl.variableDeclarationStatement());
                    }
                    if (decl.functionDeclaration() != null) {
                        createMethodModel(model, imports, decl.functionDeclaration());
                    }
                    if (decl.operatorDeclaration() != null) {
                        createMethodModel(model, imports, decl.operatorDeclaration());
                    }
                    if (decl.constructorDeclaration() != null) {
                        createMethodModel(model, imports, decl.constructorDeclaration());
                    }
                    if (decl.functionSignature() != null) {
                        createMethodModel(model, imports, decl.functionSignature());
                    }
                });
    }

    public static List<MethodModel> createInterfaceFields(ClassModel owner, Imports imports, LumParser.VariableDeclarationStatementContext ctx) {
        List<MethodModel> models = new ArrayList<>();
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        for (var decl : ctx.variableDeclaration()) {
            var type = imports.get(decl.type());
            var name = decl.IDENTIFIER().getText();

            String firstLetter = String.valueOf(name.charAt(0)).toUpperCase();
            var getter = new MethodModel(
                    owner,
                    "get"+ firstLetter + name.substring(1),
                    type,
                    EMPTY_PARAMETERS,
                    EMPTY_TYPE_MODELS,
                    accessFlags,
                    EMPTY_GENERIC_PARAMETERS
            );

            var setter = new MethodModel(
                    owner,
                    "set"+ firstLetter + name.substring(1),
                    type,
                    EMPTY_PARAMETERS,
                    EMPTY_TYPE_MODELS,
                    accessFlags,
                    EMPTY_GENERIC_PARAMETERS
            );

            models.add(getter);
            models.add(setter);
            ModelCache.cacheMethod(getter);
            ModelCache.cacheMethod(setter);
        }

        return models;
    }

    private static Pair<ClassModel, List<ClassModel>> processInheritance(Imports imports, LumParser.InheritanceContext ctx) {
        if (ctx == null || ctx.inheritanceSpec() == null) {
            return new Pair<>(null, new ArrayList<>());
        }

        var inheritance = new Pair<ClassModel, List<ClassModel>>(null, new ArrayList<>());
        boolean hasExtends = false;

        for (var spec : ctx.inheritanceSpec()) {
            try {
                if (spec.extends_ != null) {
                    inheritance.a(resolveClassModel(imports, spec.extends_));
                    continue;
                }

                if (spec.first != null) {
                    if (!hasExtends) {
                        inheritance.b().add(resolveClassModel(imports, spec.first));
                    } else {
                        inheritance.a(resolveClassModel(imports, spec.first));
                    }
                    hasExtends = spec.first != null;
                    continue;
                }

                inheritance.b().addAll(
                        spec.type().stream()
                                .map(imports::get)
                                .map(TypeModel::model)
                                .toList()
                );
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Failed to resolve class model", e);
            }
        }

        return inheritance;
    }

    private static ClassModel resolveClassModel(Imports imports, LumParser.TypeContext type) throws FileNotFoundException {
        return imports.get(type).model();
    }

    public static MethodModel createMethodModel(ClassModel owner, Imports imports, LumParser.FunctionDeclarationContext ctx) {
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        var name = ctx.IDENTIFIER().getText();
        var parameters = createParameterModels(imports, ctx.parameterList());

        var model = new MethodModel(
                owner,
                name,
                imports.get(ctx.type()),
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                EMPTY_GENERIC_PARAMETERS
        );

        ModelCache.cacheMethod(model);
        return model;
    }

    private static MethodModel createMethodModel(ClassModel owner, Imports imports, LumParser.FunctionSignatureContext ctx) {
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        var name = ctx.IDENTIFIER().getText();
        var parameters = createParameterModels(imports, ctx.parameterList());

        var model = new MethodModel(
                owner,
                name,
                imports.get(ctx.type()),
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                EMPTY_GENERIC_PARAMETERS
        );

        ModelCache.cacheMethod(model);
        return model;
    }

    private static MethodModel createMethodModel(ClassModel owner, Imports imports, LumParser.OperatorDeclarationContext ctx) {
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        var name = ctx.operator().getText();
        var parameters = createParameterModels(imports, ctx.parameterList());

        var model = new MethodModel(
                owner,
                name,
                imports.get(ctx.type()),
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                EMPTY_GENERIC_PARAMETERS
        );

        ModelCache.cacheMethod(model);
        return model;
    }

    private static MethodModel createMethodModel(ClassModel owner, Imports imports, LumParser.ConstructorDeclarationContext ctx) {
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        var name = "<init>";
        var parameters = createParameterModels(imports, ctx.parameterList());

        var model = new MethodModel(
                owner,
                name,
                ModelCache.getTypeModel(void.class),
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                EMPTY_GENERIC_PARAMETERS
        );

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
        return new ParameterModel(
                ctx.IDENTIFIER().getText(),
                imports.get(ctx.type()),
                EMPTY_GENERIC_PARAMETERS
        );
    }

    public static List<FieldModel> createFieldModels(ClassModel owner, Imports imports, LumParser.VariableDeclarationStatementContext ctx) {
        List<FieldModel> models = new ArrayList<>();
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        for (var decl : ctx.variableDeclaration()) {
            var type = imports.get(decl.type());
            var name = decl.IDENTIFIER().getText();

            var field = new FieldModel(
                    owner,
                    name,
                    type,
                    accessFlags,
                    EMPTY_GENERIC_PARAMETERS
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

        var model = new MethodModel(
                owner,
                "get" + String.valueOf(field.name().charAt(0)).toUpperCase() + field.name().substring(1),
                field.type(),
                EMPTY_PARAMETERS,
                EMPTY_TYPE_MODELS,
                accessFlags,
                EMPTY_GENERIC_PARAMETERS
        );

        ModelCache.cacheMethod(model);
    }

    private static void createSetterMethod(ClassModel owner, Imports imports, FieldModel field, LumParser.SetterDeclarationContext ctx) {
        var accessFlags = field.accessFlags();
        if (ctx.access() != null) {
            accessFlags = Utils.getAccessFlags(ctx.access(), null);
        }

        var parameters = new ParameterModel[]{createParameterModel(imports, ctx.parameter())};

        var model = new MethodModel(
                owner,
                "set" + String.valueOf(field.name().charAt(0)).toUpperCase() + field.name().substring(1),
                ModelCache.getTypeModel(void.class),
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                EMPTY_GENERIC_PARAMETERS
        );

        ModelCache.cacheMethod(model);
    }
}