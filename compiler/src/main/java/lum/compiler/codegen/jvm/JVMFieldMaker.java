package lum.compiler.codegen.jvm;

import lum.compiler.codegen.Accessible;
import lum.compiler.codegen.AnnotationMaker;
import lum.compiler.codegen.ClassMaker;
import lum.compiler.codegen.FieldMaker;
import lum.core.model.ClassModel;
import lum.core.model.FieldModel;

import java.lang.classfile.FieldBuilder;
import java.lang.reflect.AccessFlag;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class JVMFieldMaker implements FieldMaker {
    private final List<Consumer<FieldBuilder>> code = new ArrayList<>();

    final FieldModel model;

    public JVMFieldMaker(FieldModel model) {
        this.model = model;
    }

    @Override
    public Accessible access(AccessFlag flag) {
        code.add(fb -> fb.withFlags(flag));
        return this;
    }

    @Override
    public Accessible access(AccessFlag... flags) {
        code.add(fb -> fb.withFlags(flags));
        return this;
    }

    @Override
    public AnnotationMaker annotateWith(ClassModel annotation) {
        return null;
    }

    @Override
    public AnnotationMaker annotateWith(ClassMaker annotation) {
        return null;
    }

    @Override
    public AnnotationMaker annotateWith(Class<?> annotation) {
        return null;
    }

    List<Consumer<FieldBuilder>> finish() {
        return code;
    }
}
