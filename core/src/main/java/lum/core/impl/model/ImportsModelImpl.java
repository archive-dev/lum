package lum.core.impl.model;

import lum.core.model.ClassModel;
import lum.core.model.FieldModel;
import lum.core.model.ImportsModel;
import lum.core.model.MethodModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record ImportsModelImpl(
        Map<String, ClassModel> classes,
        Map<String, MethodModel> methods,
        Map<String, FieldModel> fields
) implements ImportsModel {
    public ImportsModelImpl() {
        this(
          new HashMap<>(),
          new HashMap<>(),
          new HashMap<>()
        );
    }

    @Override
    public Optional<ClassModel> getModel(String alias) {
        return Optional.ofNullable(classes().get(alias));
    }
}
