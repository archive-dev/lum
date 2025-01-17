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
record ImportsImpl(
        HashMap<String, ClassModel> classes,
        HashMap<String, MethodModel> methods,
        HashMap<String, FieldModel> fields
) implements Imports {
    private static HashMap<String, ClassModel> defaultClasses = new HashMap<>();
    static {
        defaultClasses.put("str", ClassModel.of(String.class));
        defaultClasses.put("int", ClassModel.of(int.class));
        defaultClasses.put("long", ClassModel.of(long.class));
        defaultClasses.put("float", ClassModel.of(float.class));
        defaultClasses.put("double", ClassModel.of(double.class));
        defaultClasses.put("short", ClassModel.of(short.class));
        defaultClasses.put("bool", ClassModel.of(boolean.class));
        defaultClasses.put("byte", ClassModel.of(byte.class));
        defaultClasses.put("void", ClassModel.of(void.class));
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
