package lum.compiler.jvm;

import lum.compiler.bytecode.Accessible;
import lum.compiler.bytecode.AnnotationMaker;
import lum.compiler.bytecode.ClassMaker;
import lum.compiler.bytecode.FieldMaker;
import lum.core.model.ClassModel;
import lum.core.model.FieldModel;

import java.lang.classfile.FieldBuilder;
import java.lang.reflect.AccessFlag;

public class JVMFieldMaker implements FieldMaker {
    private final FieldModel field;

    private FieldBuilder fb;

    public JVMFieldMaker(FieldModel field) {
        this.field = field;
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

    @Override
    public Accessible access(AccessFlag flag) {
        return null;
    }

    @Override
    public Accessible access(AccessFlag... flags) {
        return null;
    }
}
