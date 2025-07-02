package lum.compiler.phases.generation;

import lum.core.model.ClassModel;

public abstract class ClassGenerator {
    public abstract byte[] generate(ClassModel model);
}
