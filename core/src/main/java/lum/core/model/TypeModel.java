package lum.core.model;

import java.lang.constant.ClassDesc;

public interface TypeModel {
    TypeModel asArray(int dimensions);

    ClassDesc classDesc();

    ClassModelImpl model();

    int arrayDimensions();
}
