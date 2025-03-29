package lum.core.model;

import lum.core.parsing.ParserFactory;
import lum.core.parsing.antlr4.LumParser;
import lum.core.util.Utils;
import lum.lang.struct.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.AccessFlag;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lum.core.model.ModelConfig.workDir;
import static lum.core.util.Utils.EMPTY_CLASS_MODELS;
import static lum.core.util.Utils.EMPTY_GENERIC_PARAMETERS;

final class ClassModelBuilder {
    private ClassModelBuilder() {}

    public static ClassModel createClassModel(ClassPath path) throws IOException {
        LumParser.ProgramContext program = ParserFactory.createParser(path).program();

        Imports imports = ImportsModelFactory.createImportsModel(
                program.statement().stream()
                        .map(LumParser.StatementContext::importStatement)
                        .filter(Objects::nonNull)
                        .toList()
        );

        ClassModel model = buildClassModel(path, program, imports);
        if (model == null) return null;
        ModelCache.pathPool.put(path, model);

        return model;
    }

    private static ClassModel buildClassModel(ClassPath path, LumParser.ProgramContext program, Imports imports) {
        return switch (ParserFactory.getClassContext(program, path.className())) {
            case LumParser.ClassDeclarationContext clazz -> {
                var model = buildClassModel(imports, clazz, path);
                imports.classes().put(model.name(), model);
                ModelCache.classContexts.put(model, clazz);
                yield model;
            }
            case LumParser.InterfaceDeclarationContext interface_ -> {
                var model = buildInterfaceModel(imports, interface_, path);
                imports.classes().put(model.name(), model);
                ModelCache.interfaceContexts.put(model, interface_);
                yield model;
            }
            case null, default -> null;
        };
    }

    public static ClassModel buildClassModel(Imports imports, LumParser.ClassDeclarationContext ctx, ClassPath path) {
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        var name = ctx.IDENTIFIER().getText();
        var inheritance = processInheritance(imports, ctx.inheritance());
        String pkg = "";

        if (path != null) {
            var p = workDir.relativize(path.pathToDir()).toString();
            if (!p.isEmpty())
                pkg = p.replace(File.separator, ".");
        }

        var model = new ClassModelImpl(
                name, pkg,
                inheritance.a(),
                inheritance.b().toArray(ClassModel[]::new),
                accessFlags,
                EMPTY_GENERIC_PARAMETERS,
                EMPTY_CLASS_MODELS,
                false,
                false
        );

        return model;
    }

    public static ClassModel buildInterfaceModel(Imports imports, LumParser.InterfaceDeclarationContext ctx, ClassPath path) {
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        accessFlags.add(AccessFlag.ABSTRACT);
        accessFlags.add(AccessFlag.INTERFACE);
        var name = ctx.IDENTIFIER().getText();
        var inheritance = processInheritance(imports, ctx.inheritance());
        String pkg = "";

        if (path != null) {
            var p = workDir.relativize(path.pathToDir()).toString();
            if (!p.isEmpty())
                pkg = p.replace(File.separator, ".");
        }

        var model = new ClassModelImpl(
                name, pkg,
                inheritance.a(),
                inheritance.b().toArray(ClassModel[]::new),
                accessFlags,
                EMPTY_GENERIC_PARAMETERS,
                EMPTY_CLASS_MODELS,
                true,
                false
        );

        return model;
    }

    private static Pair<ClassModel, List<ClassModel>> processInheritance(Imports imports, LumParser.InheritanceContext ctx) {
        if (ctx == null || ctx.inheritanceSpec() == null) {
            return new Pair<>(ClassModel.of(Object.class), new ArrayList<>());
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
                                .map(imports::getType)
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
        return imports.getType(type).model();
    }

    public static ClassModel createClassModel(Imports imports, LumParser.ClassDeclarationContext ctx, ClassPath path) {
        var model = buildClassModel(imports, ctx, path);
        imports.classes().put(model.name(), model);
        return model;
    }

    public static ClassModel createInterfaceModel(Imports imports, LumParser.InterfaceDeclarationContext ctx, ClassPath path) {
        var model = buildInterfaceModel(imports, ctx, path);
        imports.classes().put(model.name(), model);
        return model;
    }
}