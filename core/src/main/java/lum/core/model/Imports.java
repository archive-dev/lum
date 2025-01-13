package lum.core.model;

import lum.core.parsing.antlr4.LumParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;

/**
 *
 * @param classes map that contains models of classes mapped by their short names
 * @param methods map that contains models of methods mapped by their short names
 * @param fields map that contains models of fields mapped by their short names
 */
public record Imports(
        HashMap<String, ClassModel> classes,
        HashMap<String, MethodModel> methods,
        HashMap<String, FieldModel> fields
) {
    private static HashMap<String, ClassModel> defaultClasses = new HashMap<>();
    static {
        defaultClasses.put("str", ModelCache.getClass(String.class));
        defaultClasses.put("void", ModelCache.getClass(void.class));
    }

    public Imports(HashMap<String, ClassModel> classes,
                   HashMap<String, MethodModel> methods,
                   HashMap<String, FieldModel> fields) {
        this.classes = classes;
        this.classes.putAll(defaultClasses);
        this.methods = methods;
        this.fields = fields;
    }

    public TypeModel get(LumParser.TypeContext t) {
        return classes().get(String.join(".", t.IDENTIFIER().stream().map(TerminalNode::getText).toList())).typeModel();
    }
}
