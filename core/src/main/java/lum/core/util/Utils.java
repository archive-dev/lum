package lum.core.util;

import lum.core.parsing.antlr4.LumParser;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class Utils {
    private Utils() {}

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

    public static boolean fileExists(Path path) {
        return Path.of(path+".lum").toFile().exists();
    }

    public static List<AccessFlag> getAccessFlags(int modifiers) {
        List<AccessFlag> flags = new ArrayList<>();
        if (Modifier.isPrivate(modifiers))
            flags.add(AccessFlag.PRIVATE);
        if (Modifier.isPublic(modifiers))
            flags.add(AccessFlag.PUBLIC);
        if (Modifier.isProtected(modifiers))
            flags.add(AccessFlag.PROTECTED);
        if (Modifier.isFinal(modifiers))
            flags.add(AccessFlag.FINAL);
        if (Modifier.isStatic(modifiers))
            flags.add(AccessFlag.STATIC);
        if (Modifier.isAbstract(modifiers))
            flags.add(AccessFlag.ABSTRACT);

        return flags;
    }

    public static List<AccessFlag> getAccessFlags(LumParser.AccessContext access, LumParser.ModifierContext modifier) {
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

        return accessFlags;
    }
}
