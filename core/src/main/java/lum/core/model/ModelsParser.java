package lum.core.model;

import lum.core.parsing.antlr4.LumParser;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class ModelsParser {
    private ModelsParser() {}

    public static Set<ClassModel> parseClassModels(LumParser.ProgramContext ctx) {
        HashSet<ClassModel> models = new HashSet<>();

        Imports imports = ParserModelFactory.createImportsModel(
                ctx.statement().stream()
                        .map(LumParser.StatementContext::importStatement)
                        .filter(Objects::nonNull)
                        .toList()
        );

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
            models.add(ParserModelFactory.createClassModel(imports, classDeclaration));
        }

        for (var interfaceDeclaration : interfaces) {
            models.add(ParserModelFactory.createInterfaceModel(imports, interfaceDeclaration));
        }

        return models;
    }
}
