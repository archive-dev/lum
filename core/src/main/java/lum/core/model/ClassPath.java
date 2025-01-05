package lum.core.model;

import lum.core.util.Utils;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import lum.core.parser.LumParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.StreamSupport;

/**
 * Represents a class path with its file path and class extension.
 * Provides methods to retrieve class and interface declaration contexts from the source file.
 * Uses LumParser to parse and analyze class/interface declarations.
 * @param path path to the file. Does not contain the file itself.
 * @param fileName name of the file with extension.
 * @param className name of the class.
 * @param extension extension of the file.
 */
public record ClassPath(Path path, String fileName, String className, Extension extension) {

    public ClassPath(Path path, String fileName, String className) {
        this(path, fileName, className, Extension.getExtension(fileName));
    }

    /**
     * Creates a set of ClassPath objects from an import statement context.
     * Processes different types of imports (simple, as, from, multiple) and returns corresponding ClassPath objects.
     *
     * @param ctx The ImportStatementContext containing the parsed import statement
     * @return A set of ClassPath objects representing the imported classes
     * @throws FileNotFoundException if any of the imported files cannot be found
     */
    public static Set<ClassPath> from(LumParser.ImportStatementContext ctx) throws FileNotFoundException {
        switch (ctx.getChild(1)) { // because child at index 0 is 'import'
            case LumParser.ImportSimpleContext simple -> {
                return Collections.singleton(from(simple));
            }
            case LumParser.ImportAsContext importAs -> {
                return Collections.singleton(from(importAs));
            }
            case LumParser.ImportFromContext importFrom -> {
                return from(importFrom);
            }
            case LumParser.ImportMultipleContext importMulti -> {
                return from(importMulti);
            }
            default -> throw new IllegalStateException("Unexpected value: " + ctx.getChild(1));
        }
    }

    /**
     * Creates a set of ClassPath objects from a multiple import statement context.
     * Processes each simple import within the multiple import and adds them to the result set.
     *
     * @param importMulti The ImportMultipleContext containing multiple import statements
     * @return A set of ClassPath objects representing all imported classes
     * @throws FileNotFoundException if any of the imported files cannot be found
     */
    private static Set<ClassPath> from(LumParser.ImportMultipleContext importMulti) throws FileNotFoundException {
        Set<ClassPath> paths = new HashSet<>();

        for (var importSimple : importMulti.importSimple()) {
            paths.add(from(importSimple));
        }

        return paths;
    }

    /**
     * Creates a ClassPath object from a LumParser's ImportSimpleContext.
     * Processes identifiers to construct file path and class name, handling nested classes.
     * 
     * @param ctx The ImportSimpleContext containing parsed import statement identifiers
     * @return A new ClassPath with constructed path, class name and extension
     */
    public static ClassPath from(LumParser.ImportSimpleContext ctx) throws FileNotFoundException {
        return from(ctx.IDENTIFIER().stream().map(TerminalNode::getText).toList());
    }

    public static Set<ClassPath> from(LumParser.ImportFromContext ctx) throws FileNotFoundException {
        Set<ClassPath> paths = new HashSet<>();

        List<String> basePath = ctx.IDENTIFIER().stream().map(TerminalNode::getText).toList();

        for (var importAs : ctx.importAs()) {
            ArrayList<String> path = new ArrayList<>(basePath);
            path.addAll(importAs.importSimple().IDENTIFIER().stream().map(TerminalNode::getText).toList());
            paths.add(from(path));
        }

        return paths;
    }

    /**
     * Creates a ClassPath instance from an ImportAsContext.
     *
     * @param ctx The ImportAsContext containing the import statement
     * @return A new ClassPath instance based on the simple import
     * @throws FileNotFoundException if the file cannot be found
     */
    public static ClassPath from(LumParser.ImportAsContext ctx) throws FileNotFoundException {
        return from(ctx.importSimple());
    }

    public static ClassPath from(Path path) throws FileNotFoundException {
        return from(List.of(path.toString().split("\\%s".formatted(File.separator))));
    }

    /**
     * Creates a ClassPath instance from a list of path elements.
     * Resolves the file path, extension, and class name based on the provided path elements.
     *
     * @param pathElements List of path segments representing the class path
     * @return A new ClassPath instance with resolved path, filename and class name
     * @throws FileNotFoundException if the file cannot be found or path is invalid
     * @throws IllegalArgumentException if path elements list is empty
     */
    public static ClassPath from(List<String> pathElements) throws FileNotFoundException {
        if (pathElements.isEmpty()) {
            throw new IllegalArgumentException("Path elements cannot be empty");
        }

        if (pathElements.size() == 1) {
            Path path = Path.of("");
            Extension extension = Utils.getExistingPathExtension(Path.of(pathElements.getLast()));
            assert extension != null;
            String fileName = pathElements.getLast() + extension.extension;
            String className = pathElements.getLast();
            return new ClassPath(path, fileName, className, extension);
        }

        Path path = Path.of(pathElements.getFirst(), pathElements.subList(1, pathElements.size()).toArray(String[]::new));
        int i = 1;
        while (Utils.getExistingPathExtension(path) == null && i < pathElements.size()) {
            path = Path.of(pathElements.getFirst(), pathElements.subList(1, pathElements.size()-i).toArray(String[]::new));
            i++;
        }
        Path filePath = path;
        try {
            path = Path.of(pathElements.getFirst(), pathElements.subList(1, pathElements.size() - i).toArray(String[]::new));
        } catch (IllegalArgumentException _) {
            throw new FileNotFoundException();
        }


        List<String> pathToFile = StreamSupport.stream(path.spliterator(), false)
                .map(Path::toString)
                .toList();

        var fileNameList = new ArrayList<>(pathElements);
        fileNameList.removeAll(pathToFile);
        Extension extension = Utils.getExistingPathExtension(filePath);
        String fileName;
        switch (extension) {
            case LUM, JAVA -> fileName = fileNameList.getFirst() + extension.extension;
            case Extension.CLASS -> fileName = String.join("$", fileNameList) + extension.extension;
            case null, default -> throw new FileNotFoundException();
        }

        String className = pathElements.getLast();

        return new ClassPath(path, fileName, className, extension);
    }

    public LumParser.ClassDeclarationContext classDeclarationContext() {
        if (extension() != Extension.LUM) throw new IllegalArgumentException();

        for (var statement : Utils.getProgramContext(path()).statement()) {
            if (statement.getChild(0) instanceof LumParser.ClassDeclarationContext ctx)
                if (ctx.IDENTIFIER().getText().equals(className()))
                    return ctx;
        }

        throw new IllegalStateException();
    }

    public LumParser.InterfaceDeclarationContext interfaceDeclarationContext() {
        if (extension() != Extension.LUM) throw new IllegalArgumentException();
        for (var statement : Utils.getProgramContext(path()).statement()) {
            if (statement.getChild(0) instanceof LumParser.InterfaceDeclarationContext ctx)
                if (ctx.IDENTIFIER().getText().equals(className()))
                    return ctx;
        }

        throw new IllegalStateException();
    }

    public ParserRuleContext getAnyContext() {
        if (extension() != Extension.LUM) throw new IllegalArgumentException();

        for (var statement : Utils.getProgramContext(path()).statement()) {
            switch (statement.getChild(0)) {
                case LumParser.InterfaceDeclarationContext ctx -> {
                    if (ctx.IDENTIFIER().getText().equals(className()))
                        return ctx;
                }
                case LumParser.ClassDeclarationContext ctx -> {
                    if (ctx.IDENTIFIER().getText().equals(className()))
                        return ctx;
                }
                default -> {}
            }
        }

        throw new IllegalStateException();
    }
}
