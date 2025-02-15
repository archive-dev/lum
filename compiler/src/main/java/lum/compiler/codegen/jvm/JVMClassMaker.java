package lum.compiler.codegen.jvm;

import lum.compiler.codegen.Accessible;
import lum.compiler.codegen.ClassMaker;
import lum.compiler.codegen.FieldMaker;
import lum.compiler.codegen.MethodMaker;
import lum.core.model.ClassModel;
import lum.core.model.FieldModel;
import lum.core.model.MethodModel;

import java.io.IOException;
import java.lang.classfile.ClassBuilder;
import java.lang.classfile.ClassFile;
import java.lang.reflect.AccessFlag;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;

class JVMClassMaker implements ClassMaker {
    private final List<Consumer<ClassBuilder>> builder = new ArrayList<>();
    private final ClassModel model;

    private final Set<MethodMaker> methods = new HashSet<>();
    private final Set<FieldMaker> fields = new HashSet<>();

    public JVMClassMaker(ClassModel model) {
        this.model = model;
    }

    @Override
    public ClassMaker extend(ClassModel model) {
        builder.add(cb -> {
            cb.withSuperclass(model.classDesc());
        });

        return this;
    }

    @Override
    public ClassMaker extend(ClassMaker maker) {
        builder.add(cb -> cb.withSuperclass(((JVMClassMaker) maker).model.classDesc()));
        return this;
    }

    @Override
    public ClassMaker implement(ClassModel model) {
        builder.add(cb -> cb.withInterfaceSymbols(model.classDesc()));
        return this;
    }

    @Override
    public ClassMaker implement(ClassModel... models) {
        builder.add(cb -> cb.withInterfaceSymbols(Arrays.stream(models).map(ClassModel::classDesc).toList()));
        return this;
    }

    @Override
    public ClassMaker implement(ClassMaker maker) {
        implement(((JVMClassMaker) maker).model);
        return this;
    }

    @Override
    public ClassMaker implement(ClassMaker... makers) {
        implement(Arrays.stream(makers).map(m -> ((JVMClassMaker) m).model).toArray(ClassModel[]::new));
        return this;
    }

    @Override
    public MethodMaker createMethod(MethodModel method) {
        return new JVMMethodMaker(method);
    }

    @Override
    public FieldMaker createField(FieldModel model) {
        return new JVMFieldMaker(model);
    }

    @Override
    public void finishTo(Path path) throws IOException {
        ClassFile.of().buildTo(path, this.model.classDesc(), cb -> {
            this.builder.forEach(b -> b.accept(cb));
            for (var method : methods) {
                JVMMethodMaker m = ((JVMMethodMaker) method);
                var model = m.model;
                cb.withMethod(
                        model.name(),
                        model.methodTypeDesc(),
                        model.intAccessFlags(),
                        mb -> {
                            for (var c : m.finish()) {
                                c.accept(mb);
                            }
                        });
            }

            for (var field : fields) {
                JVMFieldMaker f = ((JVMFieldMaker) field);
                FieldModel model = f.model;
                cb.withField(model.name(),
                        model.type().classDesc(),
                        fb -> {
                            for (var c : f.finish()) {
                                c.accept(fb);
                            }
                        });
            }
        });
    }

    @Override
    public void finishToFile() throws IOException {
        finishTo(Path.of(model.name()));
    }

    @Override
    public Accessible access(AccessFlag flag) {
        return this;
    }

    @Override
    public Accessible access(AccessFlag... flags) {
        return this;
    }
}
