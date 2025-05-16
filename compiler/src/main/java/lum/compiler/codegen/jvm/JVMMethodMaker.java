package lum.compiler.codegen.jvm;

import lum.compiler.codegen.*;
import lum.core.model.AnnotationModel;
import lum.core.model.ClassModel;
import lum.core.model.MethodModel;

import java.lang.annotation.Annotation;
import java.lang.classfile.MethodBuilder;
import java.lang.reflect.AccessFlag;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class JVMMethodMaker implements MethodMaker {
    final MethodModel model;
    final List<Consumer<MethodBuilder>> builder = new ArrayList<>();

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
        builder.add(mb -> mb.with(maker.finish()));
        return maker;
    }

    @Override
    public AnnotationMaker annotateWith(ClassMaker annotation) {
        JVMAnnotationMaker maker = new JVMAnnotationMaker(((JVMClassMaker) annotation).model);
        builder.add(mb -> mb.with(maker.finish()));
        return maker;
    }

    @Override
    public AnnotationMaker annotateWith(AnnotationModel annotation) {
        JVMAnnotationMaker maker = new JVMAnnotationMaker(annotation.annotationModel());
        for (var kv : annotation.values().entrySet()) {
            maker.setProperty(kv.getKey(), kv.getValue());
        }
        builder.add(mb -> mb.with(maker.finish()));
        return maker;
    }

    @Override
    public AnnotationMaker annotateWith(Class<? extends Annotation> annotation) {
        JVMAnnotationMaker maker = new JVMAnnotationMaker(ClassModel.of(annotation));
        builder.add(mb -> mb.with(maker.finish()));
        return maker;
    }

    List<Consumer<MethodBuilder>> finish() {
        return builder;
    }
}
