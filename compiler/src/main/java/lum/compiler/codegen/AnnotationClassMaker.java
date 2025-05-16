package lum.compiler.codegen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface AnnotationClassMaker extends ClassMaker {
    default AnnotationClassMaker setTargetTypes(ElementType... types) {
        return (AnnotationClassMaker) this.annotateWith(Target.class).setProperty("value", types);
    }

    default AnnotationClassMaker setRetention(RetentionPolicy retention) {
        return (AnnotationClassMaker) this.annotateWith(Retention.class).setProperty("value", retention);
    }
}
