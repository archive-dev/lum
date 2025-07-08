package lum.core.impl.model;

import lum.antlr4.LumParser;
import lum.core.model.ClassModel;
import lum.core.model.Member;
import lum.core.model.ParameterModel;
import lum.core.model.TypeModel;
import lum.core.util.Utils;

import java.lang.reflect.AccessFlag;
import java.util.*;

final class FieldModelParser {
    private static final Set<AccessFlag> ACCESS_VISIBILITY_FLAGS = Set.of(
            AccessFlag.PUBLIC, AccessFlag.PRIVATE, AccessFlag.PROTECTED
    );

    private FieldModelParser() {}

    static Set<Member> buildFieldModels(ClassModel owner, TypeProcessor typeProcessor, LumParser.VariableDeclarationMemberContext ctx) {
        Set<Member> members = new HashSet<>();
        Set<AccessFlag> baseFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        
        for (var variable : ctx.variableDeclaration().variable()) {
            FieldContext fieldContext = new FieldContext(owner, typeProcessor, variable, baseFlags);
            
            // Add the field itself
            members.add(createField(fieldContext));
            
            // Add getter if present
            if (variable.getter() != null) {
                members.add(createGetter(fieldContext, variable.getter().getFirst()));
            }
            
            // Add setters if present
            if (variable.setter() != null) {
                for (var setter : variable.setter()) {
                    members.add(createSetter(fieldContext, setter));
                }
            }
        }

        return members;
    }

    private static FieldModelImpl createField(FieldContext context) {
        return new FieldModelImpl(
                Optional.ofNullable(context.owner),
                Utils.EMPTY_ATTRIBUTE_MODELS,
                context.baseFlags.toArray(AccessFlag[]::new),
                context.fieldName,
                context.fieldType
        );
    }

    private static MethodModelImpl createGetter(FieldContext context, LumParser.GetterContext getter) {
        Set<AccessFlag> getterFlags = computeAccessorFlags(context.baseFlags, getter.access());
        Optional<LumParser.CodeBlockContext> codeBlock = Optional.ofNullable(getter.codeBlock());
        
        MethodModelImpl getterMethod = new MethodModelImpl(
                Optional.ofNullable(context.owner),
                Utils.EMPTY_ATTRIBUTE_MODELS,
                getterFlags.toArray(AccessFlag[]::new),
                "get" + Utils.capitalize(context.fieldName),
                Optional.empty(),
                Utils.EMPTY_PARAMETERS,
                context.fieldType, // Getter should return the field type, not void
                Utils.EMPTY_CLASS_MODELS,
                codeBlock,
                createAttributeParsers(codeBlock.isPresent())
        );
        
        setupCodeAttributeParser(getterMethod, context.typeProcessor);
        return getterMethod;
    }

    private static MethodModelImpl createSetter(FieldContext context, LumParser.SetterContext setter) {
        Set<AccessFlag> setterFlags = computeAccessorFlags(context.baseFlags, setter.access());
        Optional<LumParser.CodeBlockContext> codeBlock = Optional.ofNullable(setter.codeBlock());
        ParameterModel[] parameters = createSetterParameters(context);
        
        MethodModelImpl setterMethod = new MethodModelImpl(
                Optional.ofNullable(context.owner),
                Utils.EMPTY_ATTRIBUTE_MODELS,
                setterFlags.toArray(AccessFlag[]::new),
                "set" + Utils.capitalize(context.fieldName),
                Optional.empty(),
                parameters,
                TypeModel.of(void.class).orElseThrow(),
                Utils.EMPTY_CLASS_MODELS,
                codeBlock,
                createAttributeParsers(codeBlock.isPresent())
        );
        
        setupCodeAttributeParser(setterMethod, context.typeProcessor);
        return setterMethod;
    }

    private static Set<AccessFlag> computeAccessorFlags(Set<AccessFlag> baseFlags, LumParser.AccessContext accessContext) {
        Set<AccessFlag> accessorFlags = new HashSet<>(baseFlags);
        accessorFlags.removeAll(ACCESS_VISIBILITY_FLAGS);
        accessorFlags.addAll(Utils.getAccessFlags(accessContext, null));
        return accessorFlags;
    }

    private static ParameterModel[] createSetterParameters(FieldContext context) {
        return new ParameterModel[] {
                new ParameterModelImpl(
                        Utils.EMPTY_ATTRIBUTE_MODELS,
                        "value",
                        context.fieldType
                )
        };
    }

    private static AttributeParser<?>[] createAttributeParsers(boolean hasCodeBlock) {
        return hasCodeBlock ? new AttributeParser[1] : Utils.EMPTY_ATTRIBUTE_PARSERS;
    }

    private static void setupCodeAttributeParser(MethodModelImpl method, TypeProcessor typeProcessor) {
        AttributeParser<?>[] parsers = method.attributeParsers();
        if (parsers.length > 0 && parsers[0] == null) {
            List<AttributeParser<?>> parserList = Arrays.asList(parsers);
            parserList.set(0, new CodeAttributeParser(method, typeProcessor));
        }
    }

    /**
     * Context object to hold field-related information and reduce parameter passing.
     */
    private static class FieldContext {
        final ClassModel owner;
        final TypeProcessor typeProcessor;
        final String fieldName;
        final TypeModel fieldType;
        final Set<AccessFlag> baseFlags;

        FieldContext(ClassModel owner, TypeProcessor typeProcessor, LumParser.VariableContext variable, Set<AccessFlag> baseFlags) {
            this.owner = owner;
            this.typeProcessor = typeProcessor;
            this.fieldName = variable.IDENTIFIER().getText();
            this.fieldType = typeProcessor.getType(variable.type())
                    .orElseThrow(() -> new IllegalStateException("Type not found: " + variable.type().getText()));
            this.baseFlags = baseFlags;
        }
    }
}
