
package lum.core.model;

import lum.core.parsing.antlr4.LumParser;
import lum.core.util.Utils;

import java.lang.reflect.AccessFlag;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static lum.core.util.Utils.*;

/**
 * Processes field declarations and their accessors for ClassModels.
 */
public class FieldModelProcessor {
    private final ClassModel ownerModel;
    private final TypeProcessor typeProcessor;
    private final AnnotationProcessor annotationProcessor;

    public FieldModelProcessor(ClassModel ownerModel, TypeProcessor typeProcessor) {
        this.ownerModel = ownerModel;
        this.typeProcessor = typeProcessor;
        this.annotationProcessor = new AnnotationProcessor(typeProcessor);
    }

    /**
     * Creates FieldModel instances from a variable declaration statement context.
     * This is typically used for class fields.
     * @param ctx The variable declaration statement context.
     * @return A list of created FieldModel instances.
     */
    public List<FieldModel> createFieldModels(LumParser.VariableDeclarationStatementContext ctx) {
        List<FieldModel> models = new ArrayList<>();
        var accessFlags = getAccessFlags(ctx.access(), ctx.modifier()); 

        for (var decl : ctx.variableDeclaration()) {
            var type = typeProcessor.getType(decl.type());
            var name = decl.IDENTIFIER().getText();
            var annotations = annotationProcessor.processAnnotations(ctx.annotation());

            var field = new FieldModelImpl(
                    ownerModel,
                    name,
                    type,
                    accessFlags,
                    annotations
            );
            models.add(field);
            ModelCache.cacheField(field);
            
            processFieldAccessors(field, decl);
        }

        return models;
    }

    /**
     * Processes getter and setter declarations associated with a field.
     * @param field The FieldModel the accessors belong to.
     * @param decl The variable declaration context containing accessor definitions.
     */
    private void processFieldAccessors(FieldModel field, LumParser.VariableDeclarationContext decl) {
        if (decl.getterDeclaration() != null && !decl.getterDeclaration().isEmpty()) {
            createGetterMethod(field, decl.getterDeclaration(0));
        }
        
        if (decl.setterDeclaration() != null && !decl.setterDeclaration().isEmpty()) {
            for (var setterCtx : decl.setterDeclaration()) {
                createSetterMethod(field, setterCtx);
            }
        }
    }

    /**
     * Creates and caches a getter MethodModel for a given field.
     * @param field The field to create a getter for.
     * @param ctx The getter declaration context.
     */
    private void createGetterMethod(FieldModel field, LumParser.GetterDeclarationContext ctx) {
        var accessFlags = field.accessFlags(); 
        if (ctx.access() != null) {
            accessFlags = getAccessFlags(ctx.access(), null); 
        }
        
        String getterName = "get" + toTitled(field.name());

        var method = new MethodModelImpl(
                ownerModel,
                getterName,
                field.type(), 
                EMPTY_PARAMETERS, 
                EMPTY_TYPE_MODELS, 
                accessFlags,
                EMPTY_GENERIC_ARGUMENTS, 
                EMPTY_ANNOTATION_MODELS
        );

        ModelCache.cacheMethod(method); 
    }

    /**
     * Creates and caches a setter MethodModel for a given field.
     * @param field The field to create a setter for.
     * @param ctx The setter declaration context.
     */
    private void createSetterMethod(FieldModel field, LumParser.SetterDeclarationContext ctx) {
        var accessFlags = field.accessFlags(); 
        if (ctx.access() != null) {
            accessFlags = getAccessFlags(ctx.access(), null);
        }
        
        ParameterModel parameter;
        if (ctx.parameter() != null) {
            parameter = createParameterModel(ctx.parameter()); 
        } else {
            parameter = new ParameterModelImpl(field.name(), field.type()); 
        }
        
        String setterName = "set" + toTitled(field.name()); 

        var method = new MethodModelImpl(
                ownerModel,
                setterName,
                TypeModel.of(void.class), 
                new ParameterModel[]{parameter}, 
                EMPTY_TYPE_MODELS, 
                accessFlags,
                EMPTY_GENERIC_ARGUMENTS,
                EMPTY_ANNOTATION_MODELS
        );

        ModelCache.cacheMethod(method); 
    }

    /**
     * Creates a ParameterModel from the parser context.
     * Note: This might need access to TypeProcessor.
     * Consider moving this to a shared utility or MethodModelProcessor if it's complex.
     * @param ctx The parameter context.
     * @return A ParameterModel.
     */
    private ParameterModel createParameterModel(LumParser.ParameterContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        TypeModel type = typeProcessor.getType(ctx.type());
        return new ParameterModelImpl(name, type);
    }

    
    private static Set<AccessFlag> getAccessFlags(LumParser.AccessContext access, LumParser.ModifierContext modifier) {
        return Utils.getAccessFlags(access, modifier); 
    }
}
