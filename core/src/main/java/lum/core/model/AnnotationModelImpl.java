package lum.core.model;

import java.lang.classfile.AnnotationValue;
import java.util.HashMap;
import java.util.Map;

record AnnotationModelImpl(
    ClassModel annotationModel,
    Map<String, AnnotationValue> values
) implements AnnotationModel {
    AnnotationModelImpl(ClassModel annotationModel) {
        this(annotationModel, new HashMap<>());
    }
}
