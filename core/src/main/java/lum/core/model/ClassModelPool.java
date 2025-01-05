package lum.core.model;

import org.antlr.v4.runtime.ParserRuleContext;
import lum.core.parser.LumParser;
import lum.core.util.Utils;

import java.util.HashMap;

public final class ClassModelPool {
    private ClassModelPool() {}

    private static final HashMap<Class<?>, ClassModel> pool = new HashMap<>();
    private static final HashMap<ParserRuleContext, ClassModel> pool2 = new HashMap<>();
    private static final HashMap<ClassPath, ClassModel> pool3 = new HashMap<>();

    /**
     * Retrieves a ClassModel for a class with specified array depth from the pool.
     * @param clazz The class to get the model for
     * @param arrayDepth The number of array dimensions to add
     * @return The ClassModel for the class with the specified array depth
     */
    public static ClassModel get(Class<?> clazz, int arrayDepth) {
        return get(Utils.addArrayDepth(clazz, arrayDepth));
    }

    /**
     * Retrieves a ClassModel for the specified class from the pool.
     * If not found, creates and stores a new ClassModel.
     * @param clazz The class to get the model for
     * @return The ClassModel for the class, or null if input is null
     */
    public static ClassModel get(Class<?> clazz) {
        if (clazz == null) return null;
        if (!contains(clazz))
            pool.put(clazz, ClassModel.from(clazz));
        return pool.get(clazz);
    }

    public static ClassModel get(ParserRuleContext ctx) {
        if (ctx == null) return null;
        if (!contains(ctx)) {
            switch (ctx) {
                case LumParser.ClassDeclarationContext cd -> pool2.put(cd, ClassModel.from(cd));
                case LumParser.AnnotationDeclarationContext ad -> pool2.put(ad, ClassModel.from(ad));
                case LumParser.InterfaceDeclarationContext id -> pool2.put(id, ClassModel.from(id));
                default -> throw new IllegalStateException();
            }
        }
        return pool2.get(ctx);
    }

    public static ClassModel get(ClassPath classpath) {
        if (classpath == null) return null;
        if (!contains(classpath))
            pool3.put(classpath, ClassModel.from(classpath));
        return pool3.get(classpath);
    }

    public static ClassModel get(LumParser.ClassDeclarationContext ctx) {
        if (ctx == null) return null;
        if (!contains(ctx))
            pool2.put(ctx, ClassModel.from(ctx));
        return pool2.get(ctx);
    }

    public static ClassModel get(LumParser.InterfaceDeclarationContext ctx) {
        if (ctx == null) return null;
        if (!contains(ctx))
            pool2.put(ctx, ClassModel.from(ctx));
        return pool2.get(ctx);
    }

    public static ClassModel get(LumParser.AnnotationDeclarationContext ctx) {
        if (ctx == null) return null;
        if (!contains(ctx))
            pool2.put(ctx, ClassModel.from(ctx));
        return pool2.get(ctx);
    }

    static void put(Class<?> clazz, ClassModel model) {
        pool.put(clazz, model);
    }

    public static void clear() {
        pool.clear();
    }

    public static boolean contains(Class<?> clazz) {
        return pool.containsKey(clazz);
    }

    public static boolean contains(ClassPath path) {
        return pool3.containsKey(path);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean contains(ParserRuleContext ctx) {
        return pool2.containsKey(ctx);
    }

    public static int size() {
        return pool.size();
    }
}
