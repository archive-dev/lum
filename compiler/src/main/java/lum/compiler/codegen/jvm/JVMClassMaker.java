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
import java.util.stream.Stream;

class JVMClassMaker implements ClassMaker {
    private final List<Consumer<ClassBuilder>> classBuilderActions = new ArrayList<>();
    private final ClassModel model;
    private final Set<MethodMaker> methods = new HashSet<>();
    private final Set<FieldMaker> fields = new HashSet<>();

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
                mb -> methodMaker.finish().forEach(c -> c.accept(mb))
        );
    }

    private void addFieldToClassBuilder(ClassBuilder cb, JVMFieldMaker fieldMaker) {
        FieldModel model = fieldMaker.model;
        cb.withField(
                model.name(),
                model.type().classDesc(),
                fb -> fieldMaker.finish().forEach(c -> c.accept(fb))
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
        });
    }

    private byte[] buildClassFile(Consumer<ClassBuilder> extraActions) {
        return ClassFile.of().build(this.model.classDesc(), cb -> {
            this.classBuilderActions.forEach(b -> b.accept(cb));
            extraActions.accept(cb);
        });
    }

    @Override
    public Accessible access(AccessFlag flag) {
        return this;
    }

    @Override
    public Accessible access(AccessFlag... flags) {
        return this;
    }

    private void addClassBuilderAction(Consumer<ClassBuilder> action) {
        this.classBuilderActions.add(action);
    }
}