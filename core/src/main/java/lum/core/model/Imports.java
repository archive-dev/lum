package lum.core.model;

import org.antlr.v4.runtime.ParserRuleContext;
import lum.core.parser.LumParser;
import lum.core.util.Utils;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;

public record Imports (
        HashMap<String, ClassModel> classes,
        HashMap<String, MethodModel> methods,
        HashMap<String, FieldModel> fields
) {
    public static Imports from(Path path) {
        var program = Utils.getProgramContext(path);
        HashMap<String, ClassPath> paths = new HashMap<>();
        HashMap<String, ClassModel> classes = new HashMap<>();
        program.statement()
                .stream()
                .map(LumParser.StatementContext::declaration)
                .filter(d -> d.classDeclaration() != null || d.interfaceDeclaration() != null || d.annotationDeclaration() != null)
                .map(d -> (ParserRuleContext)(d.getChild(1)))
                .map(ClassModelPool::get)
                .forEach(m -> classes.put(m.name(), m));

        program.statement()
                .stream()
                .map(LumParser.StatementContext::importStatement)
                .filter(Objects::nonNull)
                .map(LumParser.ImportStatementContext::importAs)
                .filter(Objects::nonNull)
                .forEach(importAs -> {
                    try {
                        paths.put(importAs.IDENTIFIER().getText(), ClassPath.from(importAs));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

        program.statement()
                .stream()
                .map(LumParser.StatementContext::importStatement)
                .filter(Objects::nonNull)
                .filter(s -> s.importAs() == null)
                .forEach(statement -> {
                    try {
                        var classPaths = ClassPath.from(statement);
                        classPaths.forEach(
                                cp -> {
                                    paths.put(cp.className(), cp);
                                }
                        );
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

        paths.forEach(
                (name, classpath) -> {
                    classes.put(name, ClassModelPool.get(classpath));
                }
        );

        return new Imports(
                classes,
                new HashMap<>(),
                new HashMap<>()
        );
    }

    public ClassModel getClassModel(LumParser.TypeContext ctx) {
        return classes().get(ctx.IDENTIFIER().getText());
    }
}
