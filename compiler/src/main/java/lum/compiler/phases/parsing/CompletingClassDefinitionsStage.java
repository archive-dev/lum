package lum.compiler.phases.parsing;

import lum.compiler.phases.CompilationException;
import lum.compiler.phases.CompilationInfo;
import lum.compiler.phases.CompilerStage;
import lum.core.model.ClassModel;
import lum.core.model.ClassModelProcessor;
import lum.core.model.ModelsParser;
import lum.core.parsing.antlr4.LumParser;
import lum.lang.struct.Pair;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompletingClassDefinitionsStage implements CompilerStage<CompilationInfo, ProgramContextResult, ClassDefinitionsResult> {
    @Override
    public ClassDefinitionsResult execute(CompilationInfo context, ProgramContextResult previousResult) throws CompilationException {
        LumParser.ProgramContext ctx = previousResult.intermediateResult();
        var imports = ModelsParser.parseImports(ctx);

        ModelsParser.buildClassModels(context.file());

        imports.classes().values().forEach(model -> ModelsParser.buildClassModel(model, imports));

        Set<ClassModel> classes = new HashSet<>();

        Exception error = null;

        try {
            List<Pair<ClassModel, ParserRuleContext>> pairs = ModelsParser.buildClassModels(context.file()).entrySet().stream().map(e -> new Pair<>(e.getKey(), e.getValue())).toList();
            for (var pair : pairs) {
                if (pair.b() instanceof LumParser.ClassDeclarationContext clazz)
                    new ClassModelProcessor(pair.a(), imports).processClass(clazz);
                else if (pair.b() instanceof LumParser.InterfaceDeclarationContext interface_)
                    new ClassModelProcessor(pair.a(), imports).processInterface(interface_);
                else if (pair.b() instanceof LumParser.EnumDeclarationContext enum_)
                    new ClassModelProcessor(pair.a(), imports).processEnum(enum_);
                else if (pair.b() instanceof LumParser.AnnotationDeclarationContext annotation)
                    new ClassModelProcessor(pair.a(), imports).processAnnotation(annotation);

                classes.add(pair.a());
            }
        } catch (Exception e) {
            error = e;
        }

        return new ClassDefinitionsResult(ctx, classes, error, error == null);
    }
}
