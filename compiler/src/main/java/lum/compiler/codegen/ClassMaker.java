package lum.compiler.codegen;

import lum.compiler.codegen.jvm.JVMClassMakerFactory;
import lum.core.model.ClassModel;
import lum.core.model.FieldModel;
import lum.core.model.MethodModel;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Function;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface ClassMaker extends Accessible, Annotatable {
    ClassMaker extend(ClassModel model);

    ClassMaker extend(ClassMaker maker);

    ClassMaker implement(ClassModel model);

    ClassMaker implement(ClassModel... models);

    ClassMaker implement(ClassMaker maker);

    ClassMaker implement(ClassMaker... makers);

    MethodMaker createMethod(MethodModel method);

    FieldMaker createField(FieldModel model);

    void finishTo(Path path) throws IOException;

    void finishToFile() throws IOException;

    byte[] finish();

    static ClassMaker of(ClassModel model, Option... options) {
        Function<ClassModel, ClassMaker> getter = Utils.suppliers.get(Option.SourceOption.JVM);

        for (var option : options) {
            if (option instanceof Option.SourceOption src) {
                getter = Utils.suppliers.get(src);
            }
        }

        return getter.apply(model);
    }

    static ClassMaker of(ClassModel model) {
        return of(model, new Option[0]);
    }
}

class Utils {
    final static HashMap<Option.SourceOption, Function<ClassModel, ClassMaker>> suppliers = new HashMap<>();
    static {
        suppliers.put(Option.SourceOption.JVM, JVMClassMakerFactory::create);
    }
}