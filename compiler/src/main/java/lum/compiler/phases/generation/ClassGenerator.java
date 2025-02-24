package lum.compiler.phases.generation;

import lum.compiler.codegen.ClassMaker;
import lum.compiler.codegen.MethodMaker;
import lum.core.model.ClassModel;
import lum.core.model.Imports;
import lum.core.model.ModelsParser;

import java.lang.reflect.AccessFlag;
import java.util.ArrayList;

public final class ClassGenerator {
    private ClassGenerator() {}

    public static byte[] generate(ClassModel model, Imports imports) {
        var blocks = ModelsParser.parseMethodBodies(model, imports);
        ClassMaker maker = ClassMaker.of(model);

        for (var f : model.fields()) {
            maker.createField(f).access(f.accessFlags().toArray(AccessFlag[]::new));
        }

        for (var m : model.methods()) {
            MethodMaker method = (MethodMaker) maker.createMethod(m).access(m.accessFlags().toArray(AccessFlag[]::new));
            if (m.isAbstract()) continue;
            method.withCode((cm) -> {
                var cg = new CodeGenerator(cm, m, imports);
                cg.generate(blocks.get(m));
            });
        }

        return maker.finish();
    }
}
