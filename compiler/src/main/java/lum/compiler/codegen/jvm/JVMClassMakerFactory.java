package lum.compiler.codegen.jvm;

import lum.core.model.ClassModel;

public final class JVMClassMakerFactory {
    private JVMClassMakerFactory() {}

    public static JVMClassMaker create(ClassModel model) {
        return new JVMClassMaker(model);
    }
}
