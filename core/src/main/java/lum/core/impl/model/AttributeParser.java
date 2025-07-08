package lum.core.impl.model;

import lum.core.model.AttributeModel;

public abstract class AttributeParser<T extends AttributeModel> {
    public abstract T parseAttribute();
}
