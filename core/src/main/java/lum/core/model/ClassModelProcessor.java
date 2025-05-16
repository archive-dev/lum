
package lum.core.model;

import lum.core.parsing.antlr4.LumParser;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Orchestrates the processing of a Lum class or interface declaration
 * by delegating tasks to specialized processors.
 */
public final class ClassModelProcessor {

    private final ClassModel model;

    private final GenericsProcessor genericsProcessor;
    private final AnnotationProcessor annotationProcessor;
    private final TypeProcessor typeProcessor;

    public ClassModelProcessor(ClassModel model, Imports imports) {
        this.model = model;

        this.typeProcessor = new TypeProcessor(imports, this::getGenericBoundsFromProcessor);
        this.genericsProcessor = new GenericsProcessor(this.typeProcessor);
        this.annotationProcessor = new AnnotationProcessor(typeProcessor);
        this.typeProcessor.genericsProcessor = this.genericsProcessor;
    }

    
    private Map<String, TypeModel> getGenericBoundsFromProcessor() {
        return this.genericsProcessor.getGenericBounds();
    }

    /**
     * Processes a class declaration context.
     * @param ctx The parser context for the class declaration.
     */
    public void processClass(LumParser.ClassDeclarationContext ctx) {
        annotationProcessor.processAnnotations(ctx.annotation(), model);
        genericsProcessor.processGenerics(ctx.genericDeclaration(), model);
        processMembers(getDeclarations(ctx.block()));
    }

    /**
     * Processes an interface declaration context.
     * @param ctx The parser context for the interface declaration.
     */
    public void processInterface(LumParser.InterfaceDeclarationContext ctx) {
        annotationProcessor.processAnnotations(ctx.annotation(), model);
        genericsProcessor.processGenerics(ctx.genericDeclaration(), model);
        processMembers(getDeclarations(ctx.block()));
    }

    /**
     * Extracts declaration contexts from a block context.
     * @param block The block context.
     * @return A list of declaration contexts, or an empty list if block is null.
     */
    private List<LumParser.DeclarationContext> getDeclarations(LumParser.BlockContext block) {
        if (block == null) {
            return Collections.emptyList();
        }
        
        return block.statement().stream()
                .map(LumParser.StatementContext::declaration)
                .filter(Objects::nonNull)
                .toList();
    }

    /**
     * Processes a list of member declarations, delegating to appropriate processors.
     * @param declarations The list of member declarations.
     */
    private void processMembers(List<LumParser.DeclarationContext> declarations) {
        for (var decl : declarations) {
            if (decl.variableDeclarationStatement() != null) {
                processVariableDeclaration(decl.variableDeclarationStatement());
            } else if (decl.functionDeclaration() != null) {
                ModelCache.methodContexts.put(
                        new MethodModelProcessor(this.model, this.typeProcessor).createMethodModel(decl.functionDeclaration()),
                        decl.functionDeclaration().block()
                );
            } else if (decl.operatorDeclaration() != null) {
                ModelCache.methodContexts.put(
                        new MethodModelProcessor(this.model, this.typeProcessor).createMethodModel(decl.operatorDeclaration()),
                        decl.operatorDeclaration().block()
                );
            } else if (decl.constructorDeclaration() != null) {
                ModelCache.methodContexts.put(
                        new MethodModelProcessor(this.model, this.typeProcessor).createMethodModel(decl.constructorDeclaration()),
                        decl.constructorDeclaration().block()
                );
            } else if (decl.functionSignature() != null) {

                new MethodModelProcessor(this.model, this.typeProcessor).createMethodModel(decl.functionSignature());
            }
        }
    }

    /**
     * Processes a variable declaration, delegating based on whether it's in a class or interface.
     * @param ctx The variable declaration statement context.
     */
    private void processVariableDeclaration(LumParser.VariableDeclarationStatementContext ctx) {
        if (model.isInterface()) {
            new MethodModelProcessor(this.model, this.typeProcessor).createInterfaceMethodsForFields(ctx);
        } else {
            new FieldModelProcessor(this.model, this.typeProcessor).createFieldModels(ctx);
        }
    }

    public TypeProcessor getTypeProcessor() {
        return typeProcessor;
    }
}
