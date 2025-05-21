package lum.compiler.codegen.jvm;

import lum.compiler.codegen.Accessible;
import lum.compiler.codegen.AnnotationMaker;
import lum.compiler.codegen.ClassMaker;
import lum.compiler.codegen.FieldMaker;
import lum.core.model.AnnotationModel;
import lum.core.model.ClassModel;
import lum.core.model.FieldModel;

import java.lang.annotation.Annotation;
import java.lang.classfile.FieldBuilder;
import java.lang.classfile.FieldElement;
import java.lang.classfile.attribute.RuntimeInvisibleAnnotationsAttribute;
import java.lang.classfile.attribute.RuntimeVisibleAnnotationsAttribute;
import java.lang.reflect.AccessFlag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

class JVMFieldMaker implements FieldMaker {
    final List<Consumer<FieldBuilder>> builder = new ArrayList<>();

    final FieldModel model;

    private final List<JVMAnnotationMaker> annotations = new ArrayList<>();

    private final Map<Boolean, Supplier<FieldElement>> suppliers = new HashMap<>();
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

    public JVMFieldMaker(FieldModel model) {
        this.model = model;
    }

    @Override
    public Accessible access(AccessFlag flag) {
        builder.add(fb -> fb.withFlags(flag));
        return this;
    }

    @Override
    public Accessible access(AccessFlag... flags) {
        builder.add(fb -> fb.withFlags(flags));
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

    List<Consumer<FieldBuilder>> finish() {
        builder.add(fb -> {
            fb.with(suppliers.get(true).get());
            fb.with(suppliers.get(false).get());
        });
        return builder;
    }
}
