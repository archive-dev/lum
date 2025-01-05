package lum.core.model;

import lum.core.parser.LumParser;

import java.util.ArrayList;
import java.util.List;

public record Parameter(
        String name,
        ClassModel type,
        List<GenericParameter> genericParameters
) {
    public static Parameter from(java.lang.reflect.Parameter parameter) {
        String name = parameter.getName();
        ClassModel type = ClassModelPool.get(parameter.getType());

        return new Parameter(
                name,
                type,
                List.of()
        );
    }

    public static Parameter from(LumFile file, LumParser.ParameterContext ctx) {
        ClassModel type = file.imports().getClassModel(ctx.type());
        List<GenericParameter> genericParameters = new ArrayList<>();
        String name = ctx.IDENTIFIER().getText();

        return new Parameter(
                name, type, genericParameters
        );
    }
}
