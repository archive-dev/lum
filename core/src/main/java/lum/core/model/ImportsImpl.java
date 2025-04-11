package lum.core.model;

import lum.core.parsing.antlr4.LumParser;
import lum.core.util.TypeModelList;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @param classes map that contains models of classes mapped by their short names
 * @param methods map that contains models of methods mapped by their short names
 * @param fields map that contains models of fields mapped by their short names
 */
record ImportsImpl(
        Map<String, ClassModel> classes,
        Map<String, Map<List<TypeModel>, MethodModel>> methods,
        Map<String, FieldModel> fields
) implements Imports {
    private static final Map<String, Map<List<TypeModel>, MethodModel>> defaultMethods = new HashMap<>();
    private static final Map<String, ClassModel> defaultClasses = new HashMap<>();
    static {
        defaultMethods.put("println", ModelCache.getMethods(IO.class, "println"));
        defaultMethods.put("print", ModelCache.getMethods(IO.class, "print"));
        defaultMethods.put("readln", ModelCache.getMethods(IO.class, "readln"));

        defaultClasses.put("void", ClassModel.of(void.class));
        defaultClasses.put("bool", ClassModel.of(boolean.class));
        defaultClasses.put("byte", ClassModel.of(byte.class));
        defaultClasses.put("short", ClassModel.of(short.class));
        defaultClasses.put("int", ClassModel.of(int.class));
        defaultClasses.put("long", ClassModel.of(long.class));
        defaultClasses.put("float", ClassModel.of(float.class));
        defaultClasses.put("double", ClassModel.of(double.class));
        defaultClasses.put("str", ClassModel.of(String.class));
        defaultClasses.put("obj", ClassModel.of(Object.class));
    }

    public ImportsImpl(Map<String, ClassModel> classes,
                       Map<String, Map<List<TypeModel>, MethodModel>> methods,
                       Map<String, FieldModel> fields) {
        this.classes = classes;
        this.classes.putAll(defaultClasses);
        this.methods = methods;
        this.methods.putAll(defaultMethods);
        this.fields = fields;
    }

    @Override
    public TypeModel getType(LumParser.TypeContext t) {
        if (t instanceof LumParser.PlainTypeContext type) {
            return classes().get(String.join(".", type.IDENTIFIER().stream().map(TerminalNode::getText).toList())).typeModel();
        } else if (t instanceof LumParser.UnionTypeContext union) {
            TypeModel[] types = new TypeModel[union.type().size()];
            for (int i = 0; i < types.length; i++) {
                types[i] = getType(union.type(i));
            }
            return TypeModel.unionOf(types);
        } else if (t instanceof LumParser.IntersectionTypeContext intersection) {
            TypeModel[] types = new TypeModel[intersection.type().size()];
            for (int i = 0; i < types.length; i++) {
                types[i] = getType(intersection.type(i));
            }
            return TypeModel.intersectionOf(types);
        } else if (t instanceof LumParser.ArrayTypeContext arrayType) {
            return getType(arrayType.type()).asArray(arrayType.ARRAY().size());
        }
        throw new IllegalStateException();
    }

    @Override
    public TypeModel getType(String className) {
        if (!classes().containsKey(className)) return null;
        return classes().get(className).typeModel();
    }

    @Override
    public MethodModel getMethod(String methodName) {
        if (methods().get(methodName) == null) return null;
        return methods().get(methodName).get(List.of());
    }

    @Override
    public MethodModel getMethod(String methodName, List<TypeModel> args) {
        var _args = new TypeModelList(args);
        if (methods().get(methodName) == null) return null;
        if (methods.get(methodName).keySet().stream().noneMatch(c -> new TypeModelList(c).isAssignableFrom(_args))) return null;

        var correctArgs = methods.get(methodName).keySet().stream().filter(c -> new TypeModelList(c).isAssignableFrom(_args)).findFirst().orElse(null);

        return methods().get(methodName).get(correctArgs);
    }
}
