package lum.core.model;

import lum.core.parsing.antlr4.LumParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @param classes map that contains models of classes mapped by their short names
 * @param methods map that contains models of methods mapped by their short names
 * @param fields map that contains models of fields mapped by their short names
 */
public record ImportsImpl(
        HashMap<String, ClassModel> classes,
        HashMap<String, MethodModel> methods,
        HashMap<String, FieldModel> fields
) implements Imports {
    private static HashMap<String, ClassModel> defaultClasses = new HashMap<>();
    static {
        defaultClasses.put("str", ModelCache.getClass(String.class));
        defaultClasses.put("int", ModelCache.getClass(int.class));
        defaultClasses.put("long", ModelCache.getClass(long.class));
        defaultClasses.put("float", ModelCache.getClass(float.class));
        defaultClasses.put("double", ModelCache.getClass(double.class));
        defaultClasses.put("short", ModelCache.getClass(short.class));
        defaultClasses.put("bool", ModelCache.getClass(boolean.class));
        defaultClasses.put("byte", ModelCache.getClass(byte.class));
        defaultClasses.put("void", ModelCache.getClass(void.class));
    }

    public ImportsImpl(HashMap<String, ClassModel> classes,
                       HashMap<String, MethodModel> methods,
                       HashMap<String, FieldModel> fields) {
        this.classes = classes;
        this.classes.putAll(defaultClasses);
        this.methods = methods;
        this.fields = fields;
    }

    @Override
    public TypeModel getType(LumParser.TypeContext t) {
        if (!(t instanceof LumParser.PlainTypeContext)) throw new IllegalArgumentException();
        LumParser.PlainTypeContext type = ((LumParser.PlainTypeContext) t);
        return classes().get(String.join(".", type.IDENTIFIER().stream().map(TerminalNode::getText).toList())).typeModel();
    }

    @Override
    public TypeModel getType(List<String> pathElements) {
        return classes().get(String.join(".", pathElements)).typeModel();
    }
}
