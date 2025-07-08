package lum.compiler.phases.generation.jvm;

import lum.compiler.phases.generation.ClassGenerator;
import lum.core.model.ClassModel;

import java.lang.classfile.ClassFile;
import java.lang.constant.ClassDesc;

public class JvmClassGenerator extends ClassGenerator {
    @Override
    public byte[] generate(ClassModel model) {
        return ClassFile.of().build(
                ClassDesc.of(model.name()),
                classBuilder -> {
                    // todo: member generation
                    // todo: method generation
                }
        );
    }
}
