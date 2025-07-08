package lum.core.impl.model;

import lum.antlr4.LumParser;
import lum.core.model.ClassModel;
import lum.core.util.Utils;
import lum.lang.struct.Pair;

import java.util.*;

public class ClassModelProcessor {
    private final TypeProcessor typeProcessor;
    private final GenericsProcessor genericsProcessor;
    private final ClassModelImpl model;

    // type processor is FILE type processor
    // genericProcessor IS CREATED with FILE type processor
    // then new type processor is created for THIS class/member
    public ClassModelProcessor(ClassModelImpl model, TypeProcessor typeProcessor) {
        this.genericsProcessor = new GenericsProcessor(typeProcessor);
        this.typeProcessor = new TypeProcessor(typeProcessor, this.genericsProcessor);
        this.model = model;
    }

    public void processClassModel() {
        switch (model.clazzOrCtx.right()) {
            case LumParser.ClassDeclarationContext clazz -> {
                 processGenericTypeDeclaration(clazz.class_().genericTypeDeclaration());
                 processClassModelMembers(clazz.class_().genericTypeDeclaration().declaration());
            }
            case LumParser.InterfaceDeclarationContext interface_ -> {
                 processGenericTypeDeclaration(interface_.interface_().genericTypeDeclaration());
                 processClassModelMembers(interface_.interface_().genericTypeDeclaration().declaration());
            }
            case LumParser.EnumDeclarationContext enum_ -> {
                 processNonGenericTypeDeclaration(enum_.enum_().nonGenericTypeDeclaration());
                 processClassModelMembers(enum_.enum_().nonGenericTypeDeclaration().declaration());
            }
            case LumParser.AnnotationDeclarationContext annotation -> {
                 processNonGenericTypeDeclaration(annotation.annotation().nonGenericTypeDeclaration());
                 processClassModelMembers(annotation.annotation().nonGenericTypeDeclaration().declaration());
            }
            default -> throw new IllegalStateException("Unexpected value: " + model.clazzOrCtx);
        }
    }

    private void processClassModelMembers(List<LumParser.DeclarationContext> ctxs) {
        var members = new MemberProcessor(typeProcessor).processMembers(model, ctxs).toArray(model.members());
        Arrays.stream(members)
                .filter(m -> m instanceof MethodModelImpl)
                .map(m -> ((MethodModelImpl) m))
                .forEach(
                        method -> {
                            for (AttributeParser<?> parser : method.attributeParsers()) {
                                parser.parseAttribute();
                            }
                        }
                );
    }

    private void processGenericTypeDeclaration(LumParser.GenericTypeDeclarationContext ctx) {
        genericsProcessor.processGenericParameters(model.typeParameters().orElse(Utils.EMPTY_TYPE_PARAMETERS), ctx.generic());
        var pair = processInheritance(ctx.inheritance());

        model.setSuperClass(pair.a().isPresent() ? pair.a() : ClassModel.of(Object.class));
        ClassModel[] interfaces = pair.b().orElse(Utils.EMPTY_CLASS_MODELS);
        System.arraycopy(interfaces, 0, model.interfaces(), 0, interfaces.length);
    }

    private void processNonGenericTypeDeclaration(LumParser.NonGenericTypeDeclarationContext ctx) {
        var pair = processInheritance(ctx.inheritance());

        model.setSuperClass(pair.a().isPresent() ? pair.a() : ClassModel.of(Object.class));
        ClassModel[] interfaces = pair.b().orElse(Utils.EMPTY_CLASS_MODELS);
        System.arraycopy(interfaces, 0, model.interfaces(), 0, interfaces.length);
    }

    private Pair<Optional<ClassModel>, Optional<ClassModel[]>> processInheritance(LumParser.InheritanceContext ctx) {
        if (ctx == null)
            return new Pair<>(Optional.empty(), Optional.empty());
        Optional<ClassModel> a = Optional.empty();
        if (ctx.extends_() != null)
            a = typeProcessor.getModel(ctx.extends_().type());
        if (ctx.implements_() == null)
            return new Pair<>(a, Optional.empty());

        Set<ClassModel> interfaces = new HashSet<>();
        for (var id : ctx.implements_().type()) {
            interfaces.add(typeProcessor.getModel(id).orElseThrow());
        }

        return new Pair<>(a, Optional.of(interfaces.toArray(ClassModel[]::new)));
    }
}
