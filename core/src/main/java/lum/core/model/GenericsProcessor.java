
package lum.core.model;

import lum.core.parsing.antlr4.LumParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lum.core.util.Utils.EMPTY_GENERIC_ARGUMENTS;

/**
 * Processes generic declarations for ClassModels.
 */
public class GenericsProcessor {

    private final Map<String, TypeModel> genericBounds = new HashMap<>();
    private final TypeProcessor typeProcessor; // Needs TypeProcessor to resolve bound types

    public GenericsProcessor(TypeProcessor typeProcessor) {
        this.typeProcessor = typeProcessor;
    }

    /**
     * Processes the generic declaration context and updates the ClassModel's generic arguments.
     * @param ctx The generic declaration context.
     * @param model The ClassModel being processed.
     */
    public void processGenerics(LumParser.GenericDeclarationContext ctx, ClassModel model) {
        GenericArgument[] genericArguments = processGenerics(ctx);
        // Ensure the model's array is appropriately sized before copying
        if (model.genericArguments() != null && model.genericArguments().length == genericArguments.length) {
            System.arraycopy(genericArguments, 0, model.genericArguments(), 0, genericArguments.length);
        } else if (genericArguments.length > 0) {
            // Handle potential mismatch or initial setup if needed, maybe log a warning or error
            // For now, let's assume the model is prepared correctly beforehand or handle resizing if necessary.
            // This part might need adjustment based on how ClassModel is initialized.
            System.err.println("Warning: Generic arguments array size mismatch or not initialized for " + model.name());
        }
    }

    /**
     * Creates an array of GenericArgument models from the parser context.
     * @param ctx The generic declaration context.
     * @return An array of GenericArgument.
     */
    public GenericArgument[] processGenerics(LumParser.GenericDeclarationContext ctx) {
        if (ctx == null || ctx.generic() == null || ctx.generic().isEmpty())
            return EMPTY_GENERIC_ARGUMENTS;

        List<GenericArgument> args = new ArrayList<>();
        for (var generic : ctx.generic()) {
            GenericArgument arg;
            args.add(arg = createGenericArgument(generic));
            if (arg.name() != null && !arg.name().equals("?") && typeProcessor.resolveType(arg.name()) == null) {
                // Assuming the first bound is the primary one for the map key
                genericBounds.put(arg.name(), arg.bounds()[0].asGeneric(arg.name()));
            }
        }
        return args.toArray(GenericArgument[]::new);
    }

    /**
     * Creates a single GenericArgument model from the parser context.
     * @param generic The generic context.
     * @return A GenericArgument model.
     */
    private GenericArgument createGenericArgument(LumParser.GenericContext generic) {
        String name;
        TypeModel boundType;
        WildcardIndicator wildcard = WildcardIndicator.NONE;
        List<TypeModel> bounds = new ArrayList<>();


        if (generic instanceof LumParser.BoundGenericContext bound) {
            // Handle name: '?' or IDENTIFIER
            if (bound.QUESTION_MARK != null) {
                name = "?";
            } else {
                name = bound.IDENTIFIER().getText();
                // Check if the identifier itself resolves to a known type
                TypeModel resolvedNameAsType = typeProcessor.resolveType(name);
                if (resolvedNameAsType != null) {
                    // This case might be complex - is it a generic parameter name or a type name?
                    // Assuming it's intended as a generic parameter name here.
                    // If it resolves, it might indicate an issue or a specific pattern.
                    // For now, we keep the text as the name.


                }
            }

            wildcard = determineWildcardIndicator(bound);

            // Determine the bound type(s)
            if (bound.type() != null) {
                // Potentially multiple bounds with '&', though the grammar might need updates
                // Assuming single bound for now based on current structure
                boundType = typeProcessor.getType(bound.type());
                bounds.add(boundType);
            } else {
                bounds.add(TypeModel.OBJECT); // Explicitly add Object if no bound specified
            }

        } else if (generic instanceof LumParser.UnboundGenericContext unbound) {
            // This represents a type argument like List<String>, not a declaration like <T>
            // It should likely be handled where type arguments are processed (e.g., TypeProcessor)
            // For the declaration context <...>, only BoundGenericContext seems relevant.
            // If UnboundGenericContext can appear here, the logic needs clarification.
            // Assuming it results in a simple type argument bound by Object.
            name = "?"; // Or handle appropriately if it represents a concrete type argument
            boundType = typeProcessor.getType(unbound.type());
            bounds.add(boundType);
            // Wildcard might depend on context, defaulting to NONE here
        } else {
            throw new IllegalStateException("Unexpected generic context type: " + generic.getClass());
        }


        // Register the bound in the map *after* resolving the type
        // Note: This registration logic is moved to processGenerics to ensure all types are processed first.
        // if (name != null && !name.equals("?") && typeProcessor.resolveType(name) == null) {
        //     genericBounds.put(name, boundType.asGeneric(name));
        // }

        return new GenericArgumentImpl(name, wildcard, bounds.toArray(TypeModel[]::new));
    }

    /**
     * Determines the wildcard indicator (super, extends, none) for a bound generic.
     * @param bound The bound generic context.
     * @return The WildcardIndicator.
     */
    private WildcardIndicator determineWildcardIndicator(LumParser.BoundGenericContext bound) {
        if (bound.super_ != null)
            return WildcardIndicator.SUPER;
        else if (bound.extends_ != null)
            return WildcardIndicator.EXTENDS;
        // If it's just <T> or <?> without extends/super, it's NONE
        // If it's <T extends Bound> or <? extends Bound>, it's EXTENDS
        // If it's <? super Bound>, it's SUPER
        // An explicit check for extends might be redundant if covered by the else if.
        // Consider the case <T> vs <T extends Object> - both effectively NONE wildcard indicator?
        // Let's refine: EXTENDS applies if `extends_` is present.
        return WildcardIndicator.NONE;
    }

    /**
     * Provides access to the resolved generic bounds.
     * @return The map of generic parameter names to their bound TypeModels.
     */
    public Map<String, TypeModel> getGenericBounds() {
        return genericBounds;
    }
}
