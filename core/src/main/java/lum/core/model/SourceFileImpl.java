package lum.core.model;

import lum.core.parsing.antlr4.LumParser;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

record SourceFileImpl (
        Imports imports,
        ClassModel[] classes,
        MethodModel[] methods,
        FieldModel[] fields
) implements SourceFile {
    public SourceFileImpl(LumParser.ProgramContext program) {
        Imports imports = ImportsModelFactory.createImportsModel(
                program.statement()
                        .stream()
                        .map(LumParser.StatementContext::importStatement)
                        .filter(Objects::nonNull)
                        .toList()
        );

        List<ClassModel> classesList = program.statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .map(LumParser.DeclarationContext::classDeclaration)
                .filter(Objects::nonNull)
                .map(decl -> ClassModelBuilder.createClassModel(imports, decl))
                .collect(Collectors.toList()); // default .toList() return unmodifiable list

        classesList.addAll(program.statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .map(LumParser.DeclarationContext::interfaceDeclaration)
                .filter(Objects::nonNull)
                .map(decl -> ClassModelBuilder.createInterfaceModel(imports, decl))
                .toList());

        this(imports, classesList.toArray(ClassModel[]::new), new MethodModel[0], new FieldModel[0]);
    }
}