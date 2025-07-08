package lum.core.impl.model;

import lum.core.model.AttributeModel;
import lum.core.model.ParameterModel;
import lum.core.model.TypeModel;

public record ParameterModelImpl(
        AttributeModel[] attributes,
        String name,
        TypeModel type
) implements ParameterModel {}
