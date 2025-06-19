package lum.core.model;

import java.util.Map;
import java.util.Optional;

public interface ImportsModel {
    Map<String, ClassModel> classes();
    Map<String, MethodModel> methods();
    Map<String, FieldModel> fields();

    Optional<ClassModel> getModel(String alias);
    default Optional<TypeModel> getType(String alias) {
        var m = getModel(alias);
        return m.map(ClassModel::asTypeModel);
    }
}
