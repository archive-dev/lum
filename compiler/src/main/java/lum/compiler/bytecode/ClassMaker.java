package lum.compiler.bytecode;

import lum.core.model.ClassModel;
import lum.core.model.FieldModel;
import lum.core.model.MethodModel;

import java.nio.file.Path;

public interface ClassMaker extends Accessible {
    ClassMaker extend(ClassModel model);
    ClassMaker extend(ClassMaker maker);

    ClassMaker implement(ClassModel model);
    ClassMaker implement(ClassModel... models);
    ClassMaker implement(ClassMaker maker);
    ClassMaker implement(ClassMaker... makers);

    MethodMaker createMethod(MethodModel method);
    FieldMaker createField(FieldModel model);

    void finishTo(Path path);
    void finishToFile();
}
