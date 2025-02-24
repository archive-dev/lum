package lum.core.model;

import lum.core.parsing.antlr4.LumParser;
import lum.core.util.Utils;

import java.lang.reflect.AccessFlag;
import java.util.*;

public final class ModelsParser {
    private ModelsParser() {}

    private static final HashMap<ClassModel, LumParser.ClassDeclarationContext> classContexts = new HashMap<>();
    private static final HashMap<ClassModel, LumParser.InterfaceDeclarationContext> interfaceContexts = new HashMap<>();
    private static final HashMap<MethodModel, LumParser.BlockContext> methodContexts = new HashMap<>();

    private static final HashMap<Integer, Imports> importsCache = new HashMap<>();

    public static Imports parseImports(LumParser.ProgramContext ctx) {
        if (importsCache.containsKey(ctx.hashCode()))
            return importsCache.get(ctx.hashCode());

        var imports = ParserModelFactory.createImportsModel(
                ctx.statement().stream()
                        .map(LumParser.StatementContext::importStatement)
                        .filter(Objects::nonNull)
                        .toList()
        );
        importsCache.put(ctx.hashCode(), imports);

        return imports;
    }

    public static ClassModel parseFileClassModel(LumParser.ProgramContext ctx, String className) {
        Imports imports = parseImports(ctx);

        List<LumParser.FunctionDeclarationContext> functions =
                ctx.statement().stream()
                        .map(LumParser.StatementContext::declaration)
                        .filter(Objects::nonNull)
                        .map(LumParser.DeclarationContext::functionDeclaration)
                        .filter(Objects::nonNull)
                        .toList();

        List<LumParser.VariableDeclarationStatementContext> variables =
                ctx.statement().stream()
                        .map(LumParser.StatementContext::declaration)
                        .filter(Objects::nonNull)
                        .map(LumParser.DeclarationContext::variableDeclarationStatement)
                        .filter(Objects::nonNull)
                        .toList();

        if (functions.isEmpty() && variables.isEmpty())
            return null;

        ClassModel model = new ClassModelImpl(
                className + "Lum",
                ClassModel.of(Object.class),
                Utils.EMPTY_CLASS_MODELS,
                Set.of(AccessFlag.PUBLIC, AccessFlag.FINAL),
                Utils.EMPTY_GENERIC_PARAMETERS,
                false,
                false
        );

        for (var func : functions) {
            var method = ParserModelFactory.createMethodModel(model, imports, func);
            method.accessFlags().add(AccessFlag.STATIC);
            imports.methods()
                    .computeIfAbsent(method.name(), _ -> new HashMap<>())
                    .put(Arrays.stream(method.parameters())
                            .map(ParameterModel::type).toList(),
                            method
                    );

            methodContexts.put(method, func.block());
        }

        HashSet<FieldModel> fields = new HashSet<>();
        for (var var : variables) {
            fields.addAll(ParserModelFactory.createFieldModels(model, imports, var));
        }

        for (var field : fields) {
            field.accessFlags().add(AccessFlag.STATIC);
            imports.fields().put(field.name(), field);
        }

        return model;
    }

    public static Set<ClassModel> parseClassModels(LumParser.ProgramContext ctx) {
        HashSet<ClassModel> models = new HashSet<>();

        Imports imports = parseImports(ctx);

        List<LumParser.ClassDeclarationContext> classes =
                ctx.statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .map(LumParser.DeclarationContext::classDeclaration)
                .filter(Objects::nonNull)
                .toList();

        List<LumParser.InterfaceDeclarationContext> interfaces =
                ctx.statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .map(LumParser.DeclarationContext::interfaceDeclaration)
                .filter(Objects::nonNull)
                .toList();

        for (var classDeclaration : classes) {
            var model = ParserModelFactory.createClassModel(imports, classDeclaration);
            models.add(model);
            imports.classes().put(model.name(), model);
            classContexts.put(model, classDeclaration);
        }

        for (var interfaceDeclaration : interfaces) {
            var model = ParserModelFactory.createInterfaceModel(imports, interfaceDeclaration);
            models.add(model);
            imports.classes().put(model.name(), model);
            interfaceContexts.put(model, interfaceDeclaration);
        }

        return models;
    }

    public static HashMap<MethodModel, LumParser.BlockContext> parseMethodBodies(ClassModel model, Imports imports) {
        HashMap<MethodModel, LumParser.BlockContext> bodies = new HashMap<>();

        if (classContexts.get(model) == null) {
            for (var method : model.methods()) {
                bodies.put(method, methodContexts.get(method));
            }
            return bodies;
        }
        var funcs = classContexts.get(model).block().statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .map(LumParser.DeclarationContext::functionDeclaration)
                .filter(Objects::nonNull)
                .toList();

        var operators = classContexts.get(model).block().statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .map(LumParser.DeclarationContext::operatorDeclaration)
                .filter(Objects::nonNull)
                .toList();

        var constructors = classContexts.get(model).block().statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .map(LumParser.DeclarationContext::constructorDeclaration)
                .filter(Objects::nonNull)
                .toList();

        for (var func : funcs) {
            var method = ParserModelFactory.createMethodModel(model, imports, func);
            bodies.put(method, func.block());
        }

        for (var func : operators) {
            var method = ParserModelFactory.createMethodModel(model, imports, func);
            bodies.put(method, func.block());
        }

        for (var func : constructors ) {
            var method = ParserModelFactory.createMethodModel(model, imports, func);
            bodies.put(method, func.block());
        }

        return bodies;
    }
}
