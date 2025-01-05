package lum.core.model;

import lum.core.parser.LumParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

public final class ClassPathPool {
    private ClassPathPool() {}

    private static HashMap<Path, ClassPath> pool = new HashMap<>();
    private static HashMap<List<String>, ClassPath> pool2 = new HashMap<>();

    public static ClassPath get(Path path) {
        return pool.computeIfAbsent(path, p -> {
            try {
                return ClassPath.from(p);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static ClassPath get(List<String> pathElements) {
        return pool2.computeIfAbsent(pathElements, elements -> {
            try {
                return ClassPath.from(elements);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static ClassPath get(LumParser.ImportSimpleContext ctx) {
        return get(ctx.IDENTIFIER().stream().map(TerminalNode::getText).toList());
    }

    public static ClassPath get(LumParser.ImportAsContext ctx) {
        return get(ctx.importSimple());
    }

    public static Set<ClassPath> get(LumParser.ImportStatementContext ctx) {
        switch (ctx.getChild(1)) { // because child at index 0 is 'import'
            case LumParser.ImportSimpleContext simple -> {
                return Collections.singleton(get(simple));
            }
            case LumParser.ImportAsContext importAs -> {
                return Collections.singleton(get(importAs));
            }
            case LumParser.ImportFromContext importFrom -> {
                return get(importFrom);
            }
            case LumParser.ImportMultipleContext importMulti -> {
                return get(importMulti);
            }
            default -> throw new IllegalStateException("Unexpected value: " + ctx.getChild(1));
        }
    }

    public static Set<ClassPath> get(LumParser.ImportMultipleContext ctx) {
        Set<ClassPath> paths = new HashSet<>();

        for (var importSimple : ctx.importSimple()) {
            paths.add(get(importSimple));
        }

        return paths;
    }

    public static Set<ClassPath> get(LumParser.ImportFromContext ctx) {
        Set<ClassPath> paths = new HashSet<>();

        List<String> basePath = ctx.IDENTIFIER().stream().map(TerminalNode::getText).toList();

        for (var importAs : ctx.importAs()) {
            ArrayList<String> path = new ArrayList<>(basePath);
            path.addAll(importAs.importSimple().IDENTIFIER().stream().map(TerminalNode::getText).toList());
            paths.add(get(path));
        }

        return paths;
    }


}
