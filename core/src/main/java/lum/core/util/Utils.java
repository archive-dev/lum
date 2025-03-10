package lum.core.util;

import lum.core.model.ClassModel;
import lum.core.model.GenericParameter;
import lum.core.model.ParameterModel;
import lum.core.model.TypeModel;
import lum.core.parsing.antlr4.LumParser;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Utils {
    public static final ClassModel[] EMPTY_CLASS_MODELS = new ClassModel[0];

    private Utils() {}

    public static final GenericParameter[] EMPTY_GENERIC_PARAMETERS = new GenericParameter[0];
    public static final TypeModel[] EMPTY_TYPE_MODELS = new TypeModel[0];
    public static final ParameterModel[] EMPTY_PARAMETERS = new ParameterModel[0];

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

    public static Set<AccessFlag> getAccessFlags(int modifiers) {
        Set<AccessFlag> flags = new HashSet<>();
        if (Modifier.isInterface(modifiers))
             flags.add(AccessFlag.INTERFACE);
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
        if (Modifier.isVolatile(modifiers))
            flags.add(AccessFlag.VOLATILE);
        if (Modifier.isTransient(modifiers))
            flags.add(AccessFlag.TRANSIENT);

        return flags;
    }

    public static Set<AccessFlag> getAccessFlags(LumParser.AccessContext access, LumParser.ModifierContext modifier) {
        Set<AccessFlag> accessFlags = new HashSet<>();
        if (access!=null)
            accessFlags.add(switch (access) {
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

    public static String toTitled(String str) {
        return String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1);
    }
}
