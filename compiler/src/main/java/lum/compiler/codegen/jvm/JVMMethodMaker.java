package lum.compiler.codegen.jvm;

import lum.compiler.codegen.*;
import lum.core.model.AnnotationModel;
import lum.core.model.ClassModel;
import lum.core.model.MethodModel;

import java.lang.annotation.Annotation;
import java.lang.classfile.MethodBuilder;
import java.lang.classfile.MethodElement;
import java.lang.classfile.attribute.RuntimeInvisibleAnnotationsAttribute;
import java.lang.classfile.attribute.RuntimeVisibleAnnotationsAttribute;
import java.lang.reflect.AccessFlag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

class JVMMethodMaker implements MethodMaker {
    final MethodModel model;
    final List<Consumer<MethodBuilder>> builder = new ArrayList<>();

    private final List<JVMAnnotationMaker> annotations = new ArrayList<>();

    private final Map<Boolean, Supplier<MethodElement>> suppliers = new HashMap<>();
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

    public JVMMethodMaker(MethodModel model) {
        this.model = model;
    }

    @Override
    public MethodMaker withCode(Consumer<CodeMaker> code) {
        builder.add(mb -> mb.withCode(c -> c.block(b -> code.accept(new JVMCodeMaker(b, model)))));
        return this;
    }

    @Override
    public Accessible access(AccessFlag flag) {
        builder.add(mb -> mb.withFlags(flag));
        return this;
    }

    @Override
    public Accessible access(AccessFlag... flags) {
        builder.add(mb -> mb.withFlags(flags));
        return this;
    }

    // todo:
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

    List<Consumer<MethodBuilder>> finish() {
        builder.add(mb -> {
            mb.with(suppliers.get(true).get());
            mb.with(suppliers.get(false).get());
        });
        return builder;
    }
}
