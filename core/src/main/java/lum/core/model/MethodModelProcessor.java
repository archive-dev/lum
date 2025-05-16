
package lum.core.model;

import lum.core.parsing.antlr4.LumParser;
import lum.core.util.Utils;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.GenericDeclaration;
import java.util.*;

import static lum.core.util.Utils.*;

/**
 * Processes method-like declarations (functions, constructors, operators, interface methods) for ClassModels.
 */
@SuppressWarnings("UnusedReturnValue")
public class MethodModelProcessor {

    private final ClassModel ownerModel;
    private final TypeProcessor typeProcessor;
    private final AnnotationProcessor annotationProcessor;
    private final GenericsProcessor genericsProcessor;
        
    public MethodModelProcessor(ClassModel ownerModel, TypeProcessor typeProcessor) {
        this.ownerModel = ownerModel;
        this.typeProcessor = new TypeProcessor(typeProcessor.getImports(), this::getGenericBoundsFromProcessor);
        this.annotationProcessor = new AnnotationProcessor(typeProcessor);
        this.genericsProcessor = new GenericsProcessor(this.typeProcessor);
        this.genericsProcessor.getGenericBounds().putAll(typeProcessor.genericBoundsSupplier.get());
        this.typeProcessor.genericsProcessor = this.genericsProcessor;
    }

    private Map<String, TypeModel> getGenericBoundsFromProcessor() {
        if (genericsProcessor != null)
            return genericsProcessor.getGenericBounds();

        return Collections.emptyMap();
    }

    /**
     * Creates MethodModel instances for fields declared in an interface context.
     * In interfaces, fields often imply getter/setter methods.
     * @param ctx The variable declaration statement context within an interface.
     * @return A list of created MethodModel instances (getters/setters).
     */
    public List<MethodModel> createInterfaceMethodsForFields(LumParser.VariableDeclarationStatementContext ctx) {
        List<MethodModel> models = new ArrayList<>();
        var accessFlags = getAccessFlags(ctx.access(), ctx.modifier());
        accessFlags.add(AccessFlag.PUBLIC);
        accessFlags.remove(AccessFlag.PRIVATE);
        accessFlags.remove(AccessFlag.PROTECTED);
                
        for (var decl : ctx.variableDeclaration()) {
            var type = typeProcessor.getType(decl.type());
            var name = decl.IDENTIFIER().getText();
            String titledName = toTitled(name); 
            var getter = createGetterMethodModel(ownerModel, "get" + titledName, type, accessFlags);
            models.add(getter);
            ModelCache.cacheMethod(getter); 
            boolean isFinal = false;
            if (!isFinal) {
                var setter = createSetterMethodModel(ownerModel, "set" + titledName, type, accessFlags);
                models.add(setter);
                ModelCache.cacheMethod(setter);
            }
        }
        return models;
    }

    /**
     * Creates a MethodModel for a standard function declaration.
     * @param ctx The function declaration context.
     * @return The created MethodModel.
     */
    public MethodModel createMethodModel(LumParser.FunctionDeclarationContext ctx) {
        var accessFlags = getAccessFlags(ctx.access(), ctx.modifier());
        String name = ctx.IDENTIFIER().getText();
        GenericArgument[] genericArguments = genericsProcessor.processGenerics(ctx.genericDeclaration());
        TypeModel returnType = ctx.type() != null ? typeProcessor.getType(ctx.type()) : TypeModel.VOID;
        ParameterModel[] parameters = createParameterModels(ctx.parameterList());
        AnnotationModel[] annotations = annotationProcessor.processAnnotations(ctx.annotation());

        var method = new MethodModelImpl(
                ownerModel,
                name,
                returnType,
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                genericArguments,
                annotations
        );
        ModelCache.cacheMethod(method);
        return method;
    }

    /**
     * Creates a MethodModel for an operator declaration.
     * @param ctx The operator declaration context.
     * @return The created MethodModel.
     */
    public MethodModel createMethodModel(LumParser.OperatorDeclarationContext ctx) {
        var accessFlags = getAccessFlags(ctx.access(), ctx.modifier());
        String name = determineOperatorName(ctx);
        GenericArgument[] genericArguments = genericsProcessor.processGenerics(ctx.genericDeclaration());
        TypeModel returnType = ctx.type() != null ? typeProcessor.getType(ctx.type()) : TypeModel.VOID;
        ParameterModel[] parameters = createParameterModels(ctx.parameterList());
        AnnotationModel[] annotations = annotationProcessor.processAnnotations(ctx.annotation());

        var method = new MethodModelImpl(
                ownerModel,
                name,
                returnType,
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                genericArguments,
                annotations
        );
        ModelCache.cacheMethod(method);
        return method;
    }

    /**
     * Creates a MethodModel for a constructor declaration.
     * @param ctx The constructor declaration context.
     * @return The created MethodModel.
     */
    public MethodModel createMethodModel(LumParser.ConstructorDeclarationContext ctx) {
        var accessFlags = getAccessFlags(ctx.access(), null);
        String name = "<init>";
        GenericArgument[] genericArguments = genericsProcessor.processGenerics(ctx.genericDeclaration());
        TypeModel returnType = TypeModel.of(void.class);
        ParameterModel[] parameters = createParameterModels(ctx.parameterList());
        AnnotationModel[] annotations = annotationProcessor.processAnnotations(ctx.annotation());

        var method = new MethodModelImpl(
                ownerModel,
                name,
                returnType,
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                genericArguments,
                annotations
        );
        ModelCache.cacheMethod(method);
        return method;
    }

    /**
     * Creates a MethodModel for a function signature (e.g., in an interface).
     * @param ctx The function signature context.
     * @return The created MethodModel.
     */
    public MethodModel createMethodModel(LumParser.FunctionSignatureContext ctx) {
        var accessFlags = getAccessFlags(ctx.access(), ctx.modifier());
        accessFlags.add(AccessFlag.ABSTRACT);
        String name = ctx.IDENTIFIER().getText();
        GenericArgument[] genericArguments = genericsProcessor.processGenerics(ctx.genericDeclaration());
        TypeModel returnType = ctx.type() != null ? typeProcessor.getType(ctx.type()) : TypeModel.VOID;
        ParameterModel[] parameters = createParameterModels(ctx.parameterList());
        AnnotationModel[] annotations = annotationProcessor.processAnnotations(ctx.annotation());

        var method = new MethodModelImpl(
                ownerModel,
                name,
                returnType,
                parameters,
                EMPTY_TYPE_MODELS,
                accessFlags,
                genericArguments,
                annotations
        );
        ModelCache.cacheMethod(method);
        return method;
    }

    /**
     * Helper to create a simple getter MethodModel structure.
     */
    private MethodModel createGetterMethodModel(ClassModel owner, String name, TypeModel returnType, Set<AccessFlag> accessFlags) {
        return new MethodModelImpl(
                owner, name, returnType,
                EMPTY_PARAMETERS, EMPTY_TYPE_MODELS, accessFlags,
                EMPTY_GENERIC_ARGUMENTS, EMPTY_ANNOTATION_MODELS
        );
    }

    /**
     * Helper to create a simple setter MethodModel structure.
     * Assumes single parameter with the given type and derived name.
     */
    private MethodModel createSetterMethodModel(ClassModel owner, String name, TypeModel type, Set<AccessFlag> accessFlags) {
        String paramName = toCamelCase(name.substring(3));
        ParameterModel parameter = new ParameterModelImpl(paramName, type);

        return new MethodModelImpl(
                owner, name, TypeModel.of(void.class),
                new ParameterModel[]{parameter},
                EMPTY_TYPE_MODELS, accessFlags,
                EMPTY_GENERIC_ARGUMENTS, EMPTY_ANNOTATION_MODELS
        );
    }

    /**
     * Helper to create ParameterModel array from parser context.
     * Needs access to TypeProcessor.
     */
    private ParameterModel[] createParameterModels(LumParser.ParameterListContext listCtx) {
        if (listCtx == null || listCtx.parameter() == null || listCtx.parameter().isEmpty()) {
            return EMPTY_PARAMETERS;
        }
        List<ParameterModel> params = new ArrayList<>();
        for (LumParser.ParameterContext pCtx : listCtx.parameter()) {
            String name = pCtx.IDENTIFIER().getText();
            TypeModel type = typeProcessor.getType(pCtx.type());
            params.add(new ParameterModelImpl(name, type));
        }
        return params.toArray(ParameterModel[]::new);
    }

    /**
     * Helper to determine the internal name for an operator method.
     */
    private String determineOperatorName(LumParser.OperatorDeclarationContext ctx) {
                        return "operator_" + ctx.operator().getText();
    }

    private static Set<AccessFlag> getAccessFlags(LumParser.AccessContext access, LumParser.ModifierContext modifier) {
                return Utils.getAccessFlags(access, modifier);
    }
}
