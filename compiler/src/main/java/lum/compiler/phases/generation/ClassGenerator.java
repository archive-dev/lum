package lum.compiler.phases.generation;

import lum.compiler.codegen.ClassMaker;
import lum.compiler.codegen.FieldMaker;
import lum.compiler.codegen.MethodMaker;
import lum.core.model.ClassModel;
import lum.core.model.Imports;
import lum.core.model.MethodModel;
import lum.core.model.ModelsParser;
import lum.core.parsing.antlr4.LumParser;

import java.lang.reflect.AccessFlag;
import java.util.HashMap;

public final class ClassGenerator {
    private ClassGenerator() {}

    public static byte[] generate(ClassModel model, Imports imports) {
        HashMap<MethodModel, LumParser.BlockContext> blocks = ModelsParser.parseMethodBodies(model, imports);
        ClassMaker maker = ClassMaker.of(model);

        maker.access(model.accessFlags().toArray(AccessFlag[]::new));
        maker.extend(model.superClass());
        maker.implement(model.interfaces());
        for (var annotation : model.annotations()) {
            maker.annotateWith(annotation);
        }

        for (var f : model.fields()) {
            FieldMaker field = (FieldMaker) maker.createField(f).access(f.accessFlags().toArray(AccessFlag[]::new));

            for (var annotation : f.annotations())
                field.annotateWith(annotation);
        }

        for (var m : model.methods()) {
            if (!m.owner().equals(model)) continue;
            MethodMaker method = (MethodMaker) maker.createMethod(m).access(m.accessFlags().toArray(AccessFlag[]::new));
            for (var annotation : m.annotations())
                method.annotateWith(annotation);

            if (blocks.get(m) != null) {
                method.withCode((cm) -> {
                    var cg = new CodeGenerator(cm, m, imports);
                    cg.generate(blocks.get(m));
                });
            }
        }

        return maker.finish();
    }
}
