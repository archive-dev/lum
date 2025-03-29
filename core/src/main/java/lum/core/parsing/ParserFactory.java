package lum.core.parsing;

import lum.core.model.ClassPath;
import lum.core.parsing.antlr4.LumLexer;
import lum.core.parsing.antlr4.LumParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public final class ParserFactory {
    private ParserFactory() {}

    private static final HashMap<Integer, LumParser> lumParserCache = new HashMap<>();

    public static LumParser createParser(String code) {
        LumLexer lx = new LumLexer(CharStreams.fromString(code));
        CommonTokenStream cts = new CommonTokenStream(lx);
        return new LumParser(cts);
    }

    public static LumParser createParser(Path path) throws IOException {
        LumLexer lx = null;
        lx = new LumLexer(CharStreams.fromFileName(path.toAbsolutePath().toString()));
        CommonTokenStream cts = new CommonTokenStream(lx);

        LumParser ret = new LumParser(cts);
        lumParserCache.put(path.hashCode(), ret);
        return ret;
    }

    public static LumParser createParser(ClassPath path) throws IOException {
        var pathArray = new ArrayList<>(Arrays.stream(path.pathToDir().toString().split("\\%s".formatted(File.separator))).toList());
        pathArray.add(path.fileName());
        var pathString = String.join(File.separator, pathArray);
        Path p = Path.of(pathString);
        return createParser(p);
    }

    public static LumParser.ProgramContext createProgramContext(Path path) throws IOException {
        return createParser(path).program();
    }

    public static ParseTree getClassContext(ClassPath path) throws IOException {
        return getClassContext(createParser(path).program(), path.className());
    }

    public static ParseTree getClassContext(LumParser.ProgramContext program, String className) {
        ParseTree ctx = null;
        for (var stmt : program.statement()) {
            if (stmt.declaration() == null) continue;
            switch (stmt.declaration().getChild(0)) {
                case LumParser.ClassDeclarationContext clazz -> {
                    if (clazz.IDENTIFIER().getText().equals(className))
                        return clazz;
                    clazz.block();
                    ctx = findDeclarationContext(clazz.block(), className);
                }
                case LumParser.InterfaceDeclarationContext interface_ -> {
                    if (interface_.IDENTIFIER().getText().equals(className))
                        return interface_;
                    ctx = findDeclarationContext(interface_.block(), className);
                }
                case LumParser.AnnotationDeclarationContext annotation -> {
                    if (annotation.IDENTIFIER().getText().equals(className))
                        return annotation;
                    ctx = findDeclarationContext(annotation.block(), className);
                }
                default -> {}
            }
        }
        return ctx;
    }

    public static ParseTree findDeclarationContext(LumParser.BlockContext ctx, String className) {
        if (ctx == null) return null;
        for (var stmt : ctx.statement()) {
            if (stmt.declaration() == null) continue;
            switch (stmt.declaration().getChild(0)) {
                case LumParser.ClassDeclarationContext clazz -> {
                    if (clazz.IDENTIFIER().getText().equals(className))
                        return clazz;
                    return findDeclarationContext(clazz.block(), className);
                }
                case LumParser.InterfaceDeclarationContext interface_ -> {
                    if (interface_.IDENTIFIER().getText().equals(className))
                        return interface_;
                    return findDeclarationContext(interface_.block(), className);
                }
                case LumParser.AnnotationDeclarationContext annotation -> {
                    if (annotation.IDENTIFIER().getText().equals(className))
                        return annotation;
                    return findDeclarationContext(annotation.block(), className);
                }
                default -> {}
            }
        }
        return null;
    }
}
