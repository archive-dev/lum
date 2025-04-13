
package lum.core.model;

import lum.core.parsing.antlr4.LumParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Resolves TypeModels from parser contexts and strings.
 */
public class TypeProcessor {

    private final Imports imports;
    final Supplier<Map<String, TypeModel>> genericBoundsSupplier;
    GenericsProcessor genericsProcessor; 

    public TypeProcessor(Imports imports, Supplier<Map<String, TypeModel>> genericBoundsSupplier) {
        this.imports = imports;
        this.genericBoundsSupplier = genericBoundsSupplier;
    }

    /**
     * Resolves a TypeModel from a TypeContext.
     * @param ctx The parser TypeContext.
     * @return The resolved TypeModel.
     */
    public TypeModel getType(LumParser.TypeContext ctx) {
        if (ctx instanceof LumParser.PlainTypeContext plain) {
            return getPlainType(plain);
        } else if (ctx instanceof LumParser.UnionTypeContext union) {
            TypeModel[] types = new TypeModel[union.type().size()];
            for (int i = 0; i < types.length; i++) {
                types[i] = getType(union.type(i));
            }
            return TypeModel.unionOf(types);
        } else if (ctx instanceof LumParser.IntersectionTypeContext intersection) {
            TypeModel[] types = new TypeModel[intersection.type().size()];
            for (int i = 0; i < types.length; i++) {
                types[i] = getType(intersection.type(i));
            }
            return TypeModel.intersectionOf(types);
        } else if (ctx instanceof LumParser.ArrayTypeContext arrayType) {
            return getType(arrayType.type()).asArray(arrayType.ARRAY().size());
        }
        
        throw new IllegalStateException("Unsupported TypeContext: " + (ctx != null ? ctx.getClass().getName() : "null"));
    }

    /**
     * Resolves a TypeModel from a PlainTypeContext, handling generics.
     * @param plain The PlainTypeContext.
     * @return The resolved TypeModel.
     */
    private TypeModel getPlainType(LumParser.PlainTypeContext plain) {
        var plainTypeName = String.join(".", plain.IDENTIFIER().stream().map(TerminalNode::getText).toList());
        
        Map<String, TypeModel> currentGenericBounds = genericBoundsSupplier.get();
        if (currentGenericBounds.containsKey(plainTypeName)) {
            TypeModel genericType = currentGenericBounds.get(plainTypeName);

            if (plain.genericDeclaration() != null) {
                GenericArgument[] arguments = genericsProcessor.processGenerics(plain.genericDeclaration());
                return genericType.withGenericArguments(arguments); 
            }
            return genericType;
        }
        
        var type = imports.getType(plain);

        if (plain.genericDeclaration() != null) {
            GenericArgument[] arguments = genericsProcessor.processGenerics(plain.genericDeclaration());
            type = type.withGenericArguments(arguments);
        }
        return type;
    }

    /**
     * Resolves a TypeModel from a type name string. Checks generic bounds first.
     * @param typeName The simple or qualified type name.
     * @return The resolved TypeModel.
     */
    public TypeModel resolveType(String typeName) {
        Map<String, TypeModel> currentGenericBounds = genericBoundsSupplier.get();
        if (currentGenericBounds.containsKey(typeName)) {
            return currentGenericBounds.get(typeName);
        }
        
        return imports.getType(typeName);
    }

    Imports getImports() {
        return imports;
    }
}