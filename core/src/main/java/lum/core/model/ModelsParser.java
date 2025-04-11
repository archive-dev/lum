package lum.core.model;

import lum.core.parsing.ParserFactory;
import lum.core.parsing.antlr4.LumParser;
import lum.core.util.Utils;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.AccessFlag;
import java.nio.file.Path;
import java.util.*;

import static lum.core.util.Utils.EMPTY_CLASS_MODELS;

public final class ModelsParser {
    private ModelsParser() {}

    private static final HashMap<Integer, Imports> importsCache = new HashMap<>();

    public static Set<ClassModel> parseModels(Path source) {
        Imports imports;
        try {
            imports = parseImports(ParserFactory.createParser(source).program());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var models = buildClassModels(source);

        models.forEach((m, _) -> buildClassModel(m, imports));

        return models.keySet();
    }

    public static Imports parseImports(LumParser.ProgramContext ctx) {
        if (importsCache.containsKey(ctx.hashCode()))
            return importsCache.get(ctx.hashCode());

        var imports = ImportsModelFactory.createImportsModel(
                ctx.statement().stream()
                        .map(LumParser.StatementContext::importStatement)
                        .filter(Objects::nonNull)
                        .toList()
        );
        importsCache.put(ctx.hashCode(), imports);

        return imports;
    }

    public static ClassModel parseFileClassModel(LumParser.ProgramContext ctx, String className) {
        String pkg = ModelConfig.workDir.relativize(Objects.requireNonNullElse(Path.of(className).getParent(), Path.of(""))).toString().replace(File.separatorChar, '.');
        className = List.of(className.split("\\%s".formatted(File.separatorChar))).getLast();

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
                className + "Lum", pkg,
                ClassModel.of(Object.class),
                Utils.EMPTY_CLASS_MODELS,
                Set.of(AccessFlag.PUBLIC, AccessFlag.FINAL),
                Utils.EMPTY_GENERIC_ARGUMENTS,
                EMPTY_CLASS_MODELS,
                false,
                false
        );

        var processor = new ClassModelProcessor(model, imports);

        for (var func : functions) {
            var method = new MethodModelProcessor(model, processor.getTypeProcessor()).createMethodModel(func);
            method.accessFlags().add(AccessFlag.STATIC);
            imports.methods()
                    .computeIfAbsent(method.name(), _ -> new HashMap<>())
                    .put(Arrays.stream(method.parameters())
                            .map(ParameterModel::type).toList(),
                            method
                    );

            ModelCache.methodContexts.put(method, func.block());
        }

        HashSet<FieldModel> fields = new HashSet<>();
        for (var var : variables) {
            fields.addAll(new FieldModelProcessor(model, processor.getTypeProcessor()).createFieldModels(var));
        }

        for (var field : fields) {
            field.accessFlags().add(AccessFlag.STATIC);
            imports.fields().put(field.name(), field);
        }

        return model;
    }

    public static void buildClassModel(ClassModel model, Imports imports) {
        if (ModelCache.classContexts.containsKey(model))
            if (!model.isInterface())
                new ClassModelProcessor(model, imports).processClass(ModelCache.classContexts.get(model));
            else
                new ClassModelProcessor(model, imports).processInterface(ModelCache.interfaceContexts.get(model));
    }

    public static Map<ClassModel, ParserRuleContext> buildClassModels(Path classFile) {
        LumParser.ProgramContext ctx;
        try {
            ctx = ParserFactory.createParser(classFile).program();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<ClassModel, ParserRuleContext> contexts = new HashMap<>();
        var imports = parseImports(ctx);

        List<LumParser.ClassDeclarationContext> classes = ctx.statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .map(LumParser.DeclarationContext::classDeclaration)
                .filter(Objects::nonNull)
                .toList();

        List<LumParser.InterfaceDeclarationContext> interfaces = ctx.statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .map(LumParser.DeclarationContext::interfaceDeclaration)
                .filter(Objects::nonNull)
                .toList();

        for (var decl : interfaces) {
            var model = ClassModelBuilder.buildInterfaceModel(imports, decl, new ClassPath(
                    classFile.getParent(),
                    classFile.getName(classFile.getNameCount()-1).toString().replace(File.separator, ""),
                    decl.IDENTIFIER().getText())
            );
            imports.classes().put(model.name(), model);
            contexts.put(model, decl);
            ModelCache.interfaceContexts.put(model, decl);
        }

        for (var decl : classes) {
            var model = ClassModelBuilder.buildClassModel(imports, decl, new ClassPath(
                    classFile.getParent(),
                    classFile.getName(classFile.getNameCount()-1).toString().replace(File.separator, ""),
                    decl.IDENTIFIER().getText()
            ));
            imports.classes().put(model.name(), model);
            contexts.put(model, decl);
            ModelCache.classContexts.put(model, decl);
        }

        return contexts;
    }

    public static HashMap<MethodModel, LumParser.BlockContext> parseMethodBodies(ClassModel model, Imports imports) {
        HashMap<MethodModel, LumParser.BlockContext> bodies = new HashMap<>();

        if (ModelCache.classContexts.get(model) == null) {
            for (var method : model.methods()) {
                bodies.put(method, ModelCache.methodContexts.get(method));
            }
            return bodies;
        }
        var funcs = ModelCache.classContexts.get(model).block().statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .map(LumParser.DeclarationContext::functionDeclaration)
                .filter(Objects::nonNull)
                .toList();

        var operators = ModelCache.classContexts.get(model).block().statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .map(LumParser.DeclarationContext::operatorDeclaration)
                .filter(Objects::nonNull)
                .toList();

        var constructors = ModelCache.classContexts.get(model).block().statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .map(LumParser.DeclarationContext::constructorDeclaration)
                .filter(Objects::nonNull)
                .toList();

        var processor = new ClassModelProcessor(model, imports);

        for (var func : funcs) {
            var method = new MethodModelProcessor(model, processor.getTypeProcessor()).createMethodModel(func);
            bodies.put(method, func.block());
        }

        for (var func : operators) {
            var method = new MethodModelProcessor(model, processor.getTypeProcessor()).createMethodModel(func);
            bodies.put(method, func.block());
        }

        for (var func : constructors) {
            var method = new MethodModelProcessor(model, processor.getTypeProcessor()).createMethodModel(func);
            bodies.put(method, func.block());
        }

        return bodies;
    }
}
