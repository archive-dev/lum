package lum.compiler.codegen.jvm;

import lum.compiler.codegen.AnnotationMaker;
import lum.core.model.ClassModel;

import java.lang.classfile.*;
import java.lang.classfile.attribute.RuntimeInvisibleAnnotationsAttribute;
import java.lang.classfile.attribute.RuntimeVisibleAnnotationsAttribute;
import java.util.*;
import java.util.function.Supplier;

public class JVMAnnotationMaker implements AnnotationMaker {
    private final ClassModel annotationModel;
    private final List<Supplier<AnnotationElement>> valuesBuilder = new ArrayList<>();

    private final Map<Boolean, Supplier<Attribute<?>>> suppliers = new HashMap<>();

    private boolean isVisible = true;

    public JVMAnnotationMaker(ClassModel annotationModel) {
        this.annotationModel = annotationModel;
        suppliers.put(true,
                () -> RuntimeVisibleAnnotationsAttribute.of(
                        Annotation.of(
                                annotationModel.classDesc(),
                                valuesBuilder.stream()
                                        .map(Supplier::get)
                                        .toList()
                        )
                )
        );
        suppliers.put(false,
                () -> RuntimeInvisibleAnnotationsAttribute.of(
                        Annotation.of(
                                annotationModel.classDesc(),
                                valuesBuilder.stream()
                                        .map(Supplier::get)
                                        .toList()
                        )
                )
        );
    }

    @Override
    public AnnotationMaker setProperty(String name, Object value) {
        if (value instanceof AnnotationValue annotationValue)
            valuesBuilder.add(() -> AnnotationElement.of(name, annotationValue));
        else
            valuesBuilder.add(() -> AnnotationElement.of(name, AnnotationValue.of(value)));
        return this;
    }

    @Override
    public AnnotationMaker setRuntimeVisibility(boolean visibility) {
        isVisible = visibility;
        return this;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    @SuppressWarnings("unchecked")
    <T> T finish() {
        return (T) suppliers.get(isVisible).get();
    }

    ClassModel annotationModel() {
        return annotationModel;
    }

    List<AnnotationElement> values() {
        return valuesBuilder.stream()
                .map(Supplier::get)
                .toList();
    }
}
