package lum.compiler.codegen.jvm;

import lum.compiler.codegen.*;
import lum.compiler.codegen.Accessible;
import lum.core.model.*;
import lum.core.model.ClassModel;
import lum.core.model.FieldModel;
import lum.core.model.MethodModel;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.classfile.*;
import java.lang.classfile.attribute.RuntimeInvisibleAnnotationsAttribute;
import java.lang.classfile.attribute.RuntimeVisibleAnnotationsAttribute;
import java.lang.classfile.attribute.SignatureAttribute;
import java.lang.reflect.AccessFlag;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

class JVMClassMaker implements ClassMaker {
    private final List<Consumer<ClassBuilder>> classBuilderActions = new ArrayList<>();
    final ClassModel model;
    private final Set<MethodMaker> methods = new HashSet<>();
    private final Set<FieldMaker> fields = new HashSet<>();
    private final Set<AccessFlag> accessFlags = new HashSet<>();

    private final List<JVMAnnotationMaker> annotations = new ArrayList<>();

    private final Map<Boolean, Supplier<ClassElement>> suppliers = new HashMap<>();
    {
        suppliers.put(true,
                () -> RuntimeVisibleAnnotationsAttribute.of(
                        annotations.stream()
                                .filter(JVMAnnotationMaker::isVisible)
                                .map(maker ->
                                    java.lang.classfile.Annotation.of(
                                        maker.annotationModel().classDesc(),
                                        maker.values()
                                ))
                                .toList()
                )
        );
        suppliers.put(false,
                () -> RuntimeInvisibleAnnotationsAttribute.of(
                        annotations.stream()
                                .filter(m -> !m.isVisible())
                                .map(maker ->
                                        java.lang.classfile.Annotation.of(
                                                maker.annotationModel().classDesc(),
                                                maker.values()
                                        ))
                                .toList()
                )
        );
    }

    public JVMClassMaker(ClassModel model) {
        this.model = model;
    }

    @Override
    public ClassMaker extend(ClassModel model) {
        addClassBuilderAction(cb -> cb.withSuperclass(model.classDesc()));
        return this;
    }

    @Override
    public ClassMaker extend(ClassMaker maker) {
        return extend(((JVMClassMaker) maker).model);
    }

    @Override
    public ClassMaker implement(ClassModel model) {
        addClassBuilderAction(cb -> cb.withInterfaceSymbols(model.classDesc()));
        return this;
    }

    @Override
    public ClassMaker implement(ClassModel... models) {
        addClassBuilderAction(cb -> cb.withInterfaceSymbols(Stream.of(models).map(ClassModel::classDesc).toList()));
        return this;
    }

    @Override
    public ClassMaker implement(ClassMaker maker) {
        return implement(((JVMClassMaker) maker).model);
    }

    @Override
    public ClassMaker implement(ClassMaker... makers) {
        implement(Stream.of(makers).map(m -> ((JVMClassMaker) m).model).toArray(ClassModel[]::new));
        return this;
    }

    @Override
    public MethodMaker createMethod(MethodModel method) {
        JVMMethodMaker methodMaker = new JVMMethodMaker(method);
        methods.add(methodMaker);
        return methodMaker;
    }

    @Override
    public FieldMaker createField(FieldModel model) {
        JVMFieldMaker fieldMaker = new JVMFieldMaker(model);
        fields.add(fieldMaker);
        return fieldMaker;
    }

    @Override
    public void finishTo(Path path) throws IOException {
        buildClassFile(path, (cb) -> {
            methods.forEach(method -> addMethodToClassBuilder(cb, (JVMMethodMaker) method));
            fields.forEach(field -> addFieldToClassBuilder(cb, (JVMFieldMaker) field));
        });
    }

    private void buildClassFile(Path path, Consumer<ClassBuilder> extraActions) throws IOException {
        ClassFile.of().buildTo(path, this.model.classDesc(), cb -> {
            this.classBuilderActions.forEach(b -> b.accept(cb));
            extraActions.accept(cb);
        });
    }

    private void addMethodToClassBuilder(ClassBuilder cb, JVMMethodMaker methodMaker) {
        MethodModel model = methodMaker.model;
        cb.withMethod(
                model.name(),
                model.methodTypeDesc(),
                model.intAccessFlags(),
                mb -> {
                    if (model.genericArguments().length > 0)
                        methodMaker.builder.add(m -> m.with(SignatureAttribute.of(getMethodSignature(model))));
                    methodMaker.finish().forEach(c -> c.accept(mb));
                }
        );
    }

    private void addFieldToClassBuilder(ClassBuilder cb, JVMFieldMaker fieldMaker) {
        FieldModel model = fieldMaker.model;
        cb.withField(
                model.name(),
                model.type().classDesc(),
                fb -> {
                    if (model.type().genericArguments().length > 0)
                        fieldMaker.builder.add(f -> f.with(SignatureAttribute.of((Signature) getTypeSignature(model.type()))));
                    fieldMaker.finish().forEach(c -> c.accept(fb));
                }
        );
    }

    @Override
    public void finishToFile() throws IOException {
        finishTo(Path.of(model.name()));
    }

    @Override
    public byte[] finish() {
        return buildClassFile((cb) -> {
            methods.forEach(method -> addMethodToClassBuilder(cb, (JVMMethodMaker) method));
            fields.forEach(field -> addFieldToClassBuilder(cb, (JVMFieldMaker) field));
            cb.withFlags(accessFlags.toArray(AccessFlag[]::new));
            cb.with(suppliers.get(true).get());
            cb.with(suppliers.get(false).get());
        });
    }

    private byte[] buildClassFile(Consumer<ClassBuilder> extraActions) {
        return ClassFile.of().build(this.model.classDesc(), cb -> {
            cb.withVersion(ClassFile.JAVA_23_VERSION, 0);
            if (this.model.genericArguments().length > 0)
                cb.with(SignatureAttribute.of(getClassSignature(this.model)));
            this.classBuilderActions.forEach(b -> b.accept(cb));
            extraActions.accept(cb);
        });
    }

    @Override
    public Accessible access(AccessFlag flag) {
        this.accessFlags.add(flag);
        return this;
    }

    @Override
    public Accessible access(AccessFlag... flags) {
        this.accessFlags.addAll(List.of(flags));
        return this;
    }

    @Override
    public AnnotationMaker annotateWith(ClassModel annotation) {
        JVMAnnotationMaker maker = new JVMAnnotationMaker(annotation);
        annotations.add(maker);
        return maker;
    }

    @Override
    public AnnotationMaker annotateWith(ClassMaker annotation) {
        JVMAnnotationMaker maker = new JVMAnnotationMaker(((JVMClassMaker) annotation).model);
        annotations.add(maker);
        return maker;
    }

    @Override
    public AnnotationMaker annotateWith(AnnotationModel annotation) {
        JVMAnnotationMaker maker = new JVMAnnotationMaker(annotation.annotationModel());
        for (var kv : annotation.values().entrySet()) {
            maker.setProperty(kv.getKey(), kv.getValue());
        }
        annotations.add(maker);
        return maker;
    }

    @Override
    public AnnotationMaker annotateWith(Class<? extends Annotation> annotation) {
        JVMAnnotationMaker maker = new JVMAnnotationMaker(ClassModel.of(annotation));
        annotations.add(maker);
        return maker;
    }

    private void addClassBuilderAction(Consumer<ClassBuilder> action) {
        this.classBuilderActions.add(action);
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    private static MethodSignature getMethodSignature(MethodModel model) {
        List<Signature.TypeParam> typeParams = new ArrayList<>();
        for (var param : model.genericArguments()) {
            typeParams.add(Signature.TypeParam.of(param.name(), (Signature.RefTypeSig) getTypeSignature(param.bounds()[0]), Arrays.stream(param.bounds()).skip(1).map(JVMClassMaker::getTypeSignature).toArray(Signature.RefTypeSig[]::new)));
        }

        return
                MethodSignature.of(
                        typeParams,
                        List.of(),
                        getTypeSignature(model.returnType()),
                        Arrays.stream(model.parameters())
                                .map(ParameterModel::type)
                                .map(JVMClassMaker::getTypeSignature)
                                .toArray(Signature[]::new)
                );
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    private static ClassSignature getClassSignature(ClassModel model) {
        List<Signature.TypeParam> typeParams = new ArrayList<>();
        for (var param : model.genericArguments()) {
            //noinspection SuspiciousToArrayCall
            typeParams.add(
                    Signature.TypeParam.of(
                            param.name(),
                            (Signature.RefTypeSig) getTypeSignature(param.bounds()[0]),
                            Arrays.stream(param.bounds())
                                    .skip(1)
                                    .map(JVMClassMaker::getTypeSignature)
                                    .toArray(Signature.RefTypeSig[]::new)
                    )
            );
        }

        return ClassSignature.of(typeParams, getTypeSignature(model.superClass().typeModel()), Arrays.stream(model.interfaces()).map(ClassModel::typeModel).map(JVMClassMaker::getTypeSignature).toArray(Signature.ClassTypeSig[]::new));
    }

    @SuppressWarnings("unchecked")
    private static <T extends Signature> T getTypeSignature(TypeModel typeModel) {
        if (typeModel.isArray())
            return (T) Signature.ArrayTypeSig.of(getTypeSignature(typeModel.asComponent()));

        String desc = typeModel.model().fullName();
        if (typeModel instanceof GenericTypeModel gen)
            return (T) Signature.TypeVarSig.of(gen.name());

        if (!typeModel.isPrimitive())
            return (T) Signature.ClassTypeSig.of(desc, Arrays.stream(typeModel.genericArguments()).map(JVMClassMaker::getTypeArg).toArray(Signature.TypeArg[]::new));
        return (T) Signature.BaseTypeSig.of(typeModel.classDesc());
    }

    private static Signature.TypeArg getTypeArg(GenericArgument genericArgument) {
        return Signature.TypeArg.bounded(genericArgument.wildcardIndicator().getValue(), getTypeSignature(genericArgument.bounds()[0]));
    }
}