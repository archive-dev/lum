package lum.compiler.phases.generation;

import lum.compiler.phases.CompilationException;
import lum.compiler.phases.CompilationInfo;
import lum.compiler.phases.CompilerStage;
import lum.compiler.phases.parsing.ClassDefinitionsResult;
import lum.core.model.ModelsParser;

import java.nio.file.Path;
import java.util.HashMap;

public class CodeGenerationStage implements CompilerStage<CompilationInfo, ClassDefinitionsResult, GeneratedClassesResult> {
    @Override
    public GeneratedClassesResult execute(CompilationInfo context, ClassDefinitionsResult result) throws CompilationException {
        var classModels = result.intermediateResult();
        var fileCM = ModelsParser.parseFileClassModel(result.ctx(), context.file().toString().substring(0, context.file().toString().lastIndexOf(".")));
        if (fileCM != null)
            classModels.add(fileCM);
        var imports = ModelsParser.parseImports(result.ctx());

        HashMap<Path, byte[]> files = new HashMap<>();

        Exception error = null;

        for (var model : classModels) {
            try {
                Path path = context.srcDir().resolve(model.pkg().replace(".", "/"));
                path = context.srcDir().relativize(path.resolve(model.name() + ".class"));
                byte[] file = ClassGenerator.generate(model, imports);
                files.put(path, file);
            } catch (Exception e) {
                error = e;
                    break;
            }
        }
        return new GeneratedClassesResult(files, error, error == null);
    }
}
