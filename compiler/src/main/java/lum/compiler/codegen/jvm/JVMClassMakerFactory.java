package lum.compiler.codegen.jvm;

import lum.compiler.codegen.ClassMaker;
import lum.core.model.ClassModel;

public final class JVMClassMakerFactory {
    private JVMClassMakerFactory() {}

    public static ClassMaker create(ClassModel model) {
        return new JVMClassMaker(model);
    }
}
