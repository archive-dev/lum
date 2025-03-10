package lum.compiler.codegen.jvm;

import lum.compiler.codegen.AnnotationMaker;
import lum.core.model.ClassModel;

import java.lang.classfile.*;
import java.lang.classfile.attribute.RuntimeInvisibleAnnotationsAttribute;
import java.lang.classfile.attribute.RuntimeVisibleAnnotationsAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class JVMAnnotationMaker implements AnnotationMaker {
    private final ClassModel model;

    private final List<Supplier<AnnotationElement>> valuesBuilder = new ArrayList<>();

    private final Map<Boolean, Supplier<Attribute<?>>> suppliers = new HashMap<>();

    private boolean isVisible = true;

    public JVMAnnotationMaker(ClassModel model) {
        this.model = model;
        suppliers.put(true, () -> RuntimeVisibleAnnotationsAttribute.of(Annotation.of(model.classDesc(), valuesBuilder.stream().map(Supplier::get).toList())));
        suppliers.put(false, () -> RuntimeInvisibleAnnotationsAttribute.of(Annotation.of(model.classDesc(), valuesBuilder.stream().map(Supplier::get).toList())));
    }

    @Override
    public AnnotationMaker setProperty(String name, Object value) {
        valuesBuilder.add(() -> AnnotationElement.of(name, AnnotationValue.of(value)));
        return this;
    }

    @Override
    public AnnotationMaker setRuntimeVisibility(boolean visibility) {
        isVisible = visibility;
        return this;
    }

    @SuppressWarnings("unchecked")
    <T> T finish() {
        return (T) suppliers.get(isVisible).get();
    }
}
