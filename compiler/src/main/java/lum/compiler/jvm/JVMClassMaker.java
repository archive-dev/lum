package lum.compiler.jvm;

import lum.compiler.bytecode.Accessible;
import lum.compiler.bytecode.ClassMaker;
import lum.compiler.bytecode.FieldMaker;
import lum.compiler.bytecode.MethodMaker;
import lum.core.model.ClassModel;
import lum.core.model.FieldModel;
import lum.core.model.MethodModel;

import java.lang.classfile.ClassBuilder;
import java.lang.constant.ClassDesc;
import java.lang.reflect.AccessFlag;
import java.nio.file.Path;

public class JVMClassMaker implements ClassMaker {
    private final ClassDesc desc;
    private final ClassBuilder cb;

    public JVMClassMaker(ClassDesc desc, ClassBuilder cb) {
        this.desc = desc;
        this.cb = cb;
    }

    @Override
    public ClassMaker extend(ClassModel model) {
        cb.withSuperclass(model.classDesc());
        return this;
    }

    @Override
    public ClassMaker extend(ClassMaker maker) {
        if (maker instanceof JVMClassMaker jvmMaker) {
            cb.withSuperclass(jvmMaker.desc);
            return this;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public ClassMaker implement(ClassModel model) {
        cb.withInterfaceSymbols(model.classDesc());
        return this;
    }

    @Override
    public ClassMaker implement(ClassModel... models) {
        for (var model : models) {
            implement(model);
        }
        return this;
    }

    @Override
    public ClassMaker implement(ClassMaker maker) {
        if (maker instanceof JVMClassMaker jvmMaker) {
            cb.withInterfaceSymbols(jvmMaker.desc);
            return this;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public ClassMaker implement(ClassMaker... makers) {
        for (var maker : makers) {
            implement(maker);
        }
        return this;
    }

    @Override
    public MethodMaker createMethod(MethodModel method) {
        return null;
    }

    @Override
    public FieldMaker createField(FieldModel model) {
        return null;
    }

    @Override
    public void finishTo(Path path) {

    }

    @Override
    public void finishToFile() {

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
