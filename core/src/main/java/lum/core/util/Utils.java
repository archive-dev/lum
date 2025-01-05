package lum.core.util;

import lum.core.model.Extension;
import lum.core.parser.LumLexer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import lum.core.parser.LumParser;

import java.io.IOException;
import java.lang.reflect.AccessFlag;
import java.nio.file.Path;
import java.util.ArrayList;

public final class Utils {
    private Utils() {}

    public static LumParser.ProgramContext getProgramContext(Path path) {
        try {
            LumLexer lx = new LumLexer(CharStreams.fromFileName(path.toAbsolutePath().toString()));
            CommonTokenStream cts = new CommonTokenStream(lx);
            LumParser parser = new LumParser(cts);
            return parser.program();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getArrayDepth(Class<?> clazz) {
        int depth = 0;
        while (clazz.isArray()) {
            depth++;
            clazz = clazz.getComponentType();
        }
        return depth;
    }

    public static Class<?> addArrayDepth(Class<?> clazz, int depth) {
        if (clazz == null) return null;
        for (int i = 0; i < depth; i++) {
            clazz = clazz.arrayType();
        }
        return clazz;
    }

    public static Class<?> getComponentType(Class<?> clazz) {
        while (clazz.isArray()) {
            clazz = clazz.getComponentType();
        }
        return clazz;
    }

    public static AccessFlag[] getAccessFlags(LumParser.AccessContext access, LumParser.ModifierContext modifier) {
        ArrayList<AccessFlag> accessFlags = new ArrayList<>();
        if (access!=null)
            accessFlags.add(switch (access.getChild(0)) {
                case LumParser.PublicContext _ -> AccessFlag.PUBLIC;
                case LumParser.PrivateContext _ -> AccessFlag.PRIVATE;
                case LumParser.ProtectedContext _ -> AccessFlag.PROTECTED;
                default -> throw new IllegalStateException();
            });

        if (modifier != null) {
            if (modifier.abstract_ != null) accessFlags.add(AccessFlag.ABSTRACT);
            if (modifier.static_ != null) accessFlags.add(AccessFlag.STATIC);
            if (modifier.final_ != null) accessFlags.add(AccessFlag.FINAL);
        }

        return accessFlags.toArray(AccessFlag[]::new);
    }

    public static Extension getExistingPathExtension(Path path) {
        for (var v : Extension.values()) {
            if (Path.of(path + v.extension).toFile().exists())
                return v;
        }
        return null;
    }
}
