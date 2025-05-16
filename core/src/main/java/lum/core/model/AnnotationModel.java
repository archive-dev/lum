package lum.core.model;

import java.lang.classfile.AnnotationValue;
import java.util.Map;

public interface AnnotationModel {
    ClassModel annotationModel();
    Map<String, AnnotationValue> values();
}
