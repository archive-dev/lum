package lum.core.model;

import lum.core.parsing.antlr4.LumParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

final class ImportsModelFactory {
    private ImportsModelFactory() {}

    private static final HashMap<List<String>, Imports> importsCache = new HashMap<>();

    public static Imports createImportsModel(List<LumParser.ImportStatementContext> imports) {
        if (importsCache.containsKey(imports.stream().map(ParserRuleContext::getText).toList()))
            return importsCache.get(imports.stream().map(ParserRuleContext::getText).toList());
        final HashMap<String, ClassModel> classes = new HashMap<>();
        var importsModel = new ImportsImpl(
                classes,
                new HashMap<>(),
                new HashMap<>()
        );
        importsCache.put(imports.stream().map(ParserRuleContext::getText).toList(), importsModel);

        for (var importStatement : imports) {
            switch (importStatement.getChild(1)) {
                case LumParser.AnySimpleImportContext simple -> handleAnyImportSimple(simple, classes);
                case LumParser.ImportAsContext importAs -> handleImportAs(importAs, classes);
                case LumParser.ImportMultipleContext multi -> handleImportMultiple(multi, classes);
                case LumParser.ImportFromContext from -> handleImportFrom(from, classes);
                default -> throw new IllegalStateException("Unexpected value: " + importStatement.getChild(1));
            }
        }

        if (classes.containsValue(null))
            throw new RuntimeException(new ClassNotFoundException());

        return importsModel;
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
}