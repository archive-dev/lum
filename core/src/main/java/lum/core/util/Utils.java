package lum.core.util;

import lum.antlr4.LumParser;
import lum.core.impl.model.AttributeParser;
import lum.core.ir.CodeElement;
import lum.core.model.*;
//import lum.core.parsing.antlr4.LumParser;

import java.lang.reflect.AccessFlag;
//import java.lang.reflect.Modifier;
//import java.nio.file.Path;
import java.nio.file.Path;
import java.util.ArrayList;
//import java.util.HashSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import java.util.Set;

public final class Utils {
    public static final ClassModel[] EMPTY_CLASS_MODELS = new ClassModel[0];
    public static final AttributeModel[] EMPTY_ATTRIBUTE_MODELS = new AttributeModel[0];
    public static final TypeModel[] EMPTY_TYPE_MODELS = new TypeModel[0];
    public static final ParameterModel[] EMPTY_PARAMETERS = new ParameterModel[0];
    public static final Member[] EMPTY_MEMBERS = new Member[0];
    public static final AccessFlag[] EMPTY_ACCESS_FLAGS = new AccessFlag[0];
    public static final AccessFlag[] DEFAULT_ACCESS_FLAGS = {AccessFlag.PUBLIC, AccessFlag.FINAL};
    public static final TypeParameter[] EMPTY_TYPE_PARAMETERS = new TypeParameter[0];
    public static final TypeArgument[] EMPTY_TYPE_ARGUMENTS = new TypeArgument[0];
    public static final CodeElement.ExpressionElement[] EMPTY_EXPRESSION_ELEMENTS = new CodeElement.ExpressionElement[0];
    public static final AttributeParser<?>[] EMPTY_ATTRIBUTE_PARSERS = new AttributeParser[0];

    private Utils() {}
/*

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

*/
    public static Class<?> getComponentType(Class<?> clazz) {
        while (clazz.isArray()) {
            clazz = clazz.getComponentType();
        }
        return clazz;
    }
/*

    public static boolean fileExists(Path path) {
        return ModelConfig.workDir.resolve(Path.of(path+".lum")).toFile().exists();
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

*/
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
            if (modifier.ABSTRACT() != null) accessFlags.add(AccessFlag.ABSTRACT);
            if (modifier.STATIC() != null) accessFlags.add(AccessFlag.STATIC);
            if (modifier.FINAL() != null) accessFlags.add(AccessFlag.FINAL);
        }

        return accessFlags;
    }

    public static int count(String what, String in) {
        int c = 0;

        while (true) {
            var replaced = in.replaceFirst(what, "");
            if (in.equals(replaced))
                break;
            else {
                c++;
                in = replaced;
            }
        }

        return c;
    }

    /// "someString" -> "SomeString"
    public static String capitalize(String str) {
        return String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1);
    }

    public static java.lang.reflect.Member[] getClassMembers(Class<?> clazz) {
        var constructors = clazz.getConstructors();
        var fields = clazz.getFields();
        var methods = clazz.getMethods();

        List<java.lang.reflect.Member> members = new ArrayList<>();
        members.addAll(List.of(constructors));
        members.addAll(List.of(fields));
        members.addAll(List.of(methods));

        return members.toArray(java.lang.reflect.Member[]::new);
    }

    /**
     * Subtracts path2 from path1, effectively returning the path that needs to be appended to path2
     * to get path1.
     *
     * @param path1 The path from which to subtract.
     * @param path2 The path to subtract.
     * @return The resulting relative path.
     * @throws IllegalArgumentException If path2 is not a prefix of path1 or if paths have different roots.
     */
    public static Path subtractPaths(Path path1, Path path2) {
        Path normalizedPath1 = path1.normalize();
        Path normalizedPath2 = path2.normalize();

        if (!normalizedPath1.startsWith(normalizedPath2)) {
            throw new IllegalArgumentException("Cannot subtract: " + path2 + " is not a prefix of " + path1);
        }

        return normalizedPath2.relativize(normalizedPath1);
    }
}
