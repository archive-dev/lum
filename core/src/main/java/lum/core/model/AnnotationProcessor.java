package lum.core.model;

import lum.core.parsing.antlr4.LumParser;
import lum.core.util.Utils;

import java.lang.classfile.AnnotationValue;
import java.util.ArrayList;
import java.util.List;

class AnnotationProcessor {
    private final TypeProcessor typeProcessor;

    AnnotationProcessor(TypeProcessor typeProcessor) {
        this.typeProcessor = typeProcessor;
    }

    public void processAnnotations(List<LumParser.AnnotationContext> annotations, Annotatable annotatable) {
        if (annotations == null || annotations.isEmpty()) return;

        for (int i = 0; i < annotatable.annotations().length; i++) {
            LumParser.AnnotationContext annotationContext = annotations.get(i);
            annotatable.annotations()[i] = new AnnotationModelImpl(typeProcessor.resolveType(annotationContext.IDENTIFIER().getText()).model());
            if (annotationContext.expression() == null) {
                if (annotationContext.annotationArgs() != null
                        && annotationContext.annotationArgs().annotationArg() != null) {
                    for (var arg : annotationContext.annotationArgs().annotationArg()) {
                        annotatable.annotations()[i].values().put(arg.IDENTIFIER().getText(), parseExpression(arg.expression()));
                    }
                }
            }
            else
                annotatable.annotations()[i].values().put("value", parseExpression(annotationContext.expression()));
        }
    }

    public AnnotationModel[] processAnnotations(List<LumParser.AnnotationContext> annotations) {
        if (annotations == null || annotations.isEmpty()) return Utils.EMPTY_ANNOTATION_MODELS;

        var list = new ArrayList<AnnotationModel>();
        for (LumParser.AnnotationContext annotationContext : annotations) {
            var annotation = new AnnotationModelImpl(typeProcessor.resolveType(annotationContext.IDENTIFIER().getText()).model());
            if (annotationContext.expression() == null) {
                if (annotationContext.annotationArgs() != null
                        && annotationContext.annotationArgs().annotationArg() != null) {
                    for (var arg : annotationContext.annotationArgs().annotationArg()) {
                        annotation.values().put(arg.IDENTIFIER().getText(), parseExpression(arg.expression()));
                    }
                }
            }
            else
                annotation.values().put("value", parseExpression(annotationContext.expression()));
            list.add(annotation);
        }

        return list.toArray(AnnotationModel[]::new);
    }

    private AnnotationValue parseExpression(LumParser.ExpressionContext expression) {
        return switch (expression) {
            case LumParser.MemberAccessContext member -> {
                if (member.expression() instanceof LumParser.MemberAccessContext memberAccess) {
                    var type = resolveType(memberAccess);
                    if (member.IDENTIFIER() == null) throw new IllegalStateException("Cannot use non constants in annotations");
                    yield AnnotationValue.ofEnum(type.classDesc(), member.IDENTIFIER().getText());
                }
                throw new IllegalStateException("Cannot use non constants in annotations");
            }
            case LumParser.PrimaryExpressionContext primary -> parsePrimary(primary.primary());
            default -> throw new IllegalStateException("Unexpected value: " + expression);
        };
    }

    private TypeModel resolveType(LumParser.ExpressionContext expr) {
        return switch (expr) {
            case LumParser.MemberAccessContext member -> {
                if (member.expression() instanceof LumParser.PrimaryExpressionContext primary) {
                    yield resolveType(primary.primary());
                } else if (member.expression() instanceof LumParser.MemberAccessContext memberAccess) {
                    yield resolveType(memberAccess);
                }

                throw new IllegalStateException("Cannot use non constants in annotations");
            }
            case LumParser.PrimaryExpressionContext primary -> resolveType(primary);
            default -> throw new IllegalStateException("Unexpected value: " + expr);
        };
    }

    private TypeModel resolveType(LumParser.MemberAccessContext member) {
        if (member.IDENTIFIER() == null) throw new IllegalStateException("Cannot use non constants in annotations");

        return resolveType(member.expression()).model().getField(member.IDENTIFIER().getText()).type();
    }

    private TypeModel resolveType(LumParser.PrimaryContext primary) {
        return switch (primary) {
            case LumParser.LiteralExprContext lit -> TypeModel.of(parseLiteral(lit.literal()));
            case LumParser.IdentifierExprContext id ->
                //  FieldModel field;
                //  if ((field = annotated.getField(id.getText())) != null) {
                //      yield field.type();
                //  }
                    typeProcessor.resolveType(id.getText());
            case LumParser.ListLiteralContext list -> {
                var args = new ArrayList<TypeModel>();
                for (var expr :
                        list.argumentList().expression()) {
                    args.add(resolveType(expr));
                }
                yield TypeModel.unionOf(args.toArray(TypeModel[]::new));
            }
            case LumParser.ParenExprContext paren -> resolveType(paren);
            default -> throw new IllegalStateException("Unexpected value: " + primary);
        };
    }

    private AnnotationValue parsePrimary(LumParser.PrimaryContext primary) {
        return switch (primary) {
            case LumParser.IdentifierExprContext id ->
                    AnnotationValue.ofClass(typeProcessor.resolveType(id.getText()).classDesc());
            case LumParser.LiteralExprContext lit ->
                    parseLiteral(lit.literal());
            case LumParser.ParenExprContext paren ->
                    parseExpression(paren.expression());
            case LumParser.ListLiteralContext list ->
                    AnnotationValue.ofArray(
                            list.argumentList().expression()
                                    .stream()
                                    .map(this::parseExpression)
                                    .toArray(AnnotationValue[]::new)
                    );
            default -> throw new IllegalStateException("Unexpected value: " + primary);
        };
    }

    private AnnotationValue parseLiteral(LumParser.LiteralContext literal) {
        return switch (literal) {
            case LumParser.StrContext str -> AnnotationValue.of(str.STRING().getText().substring(1, str.STRING().getText().length()-1));
            case LumParser.NumContext n -> {
                var num = n.getText();
                if (num.contains(".")) {
                    if (num.contains("f")) {
                        yield AnnotationValue.of(Float.parseFloat(num));
                    } else {
                        yield AnnotationValue.of(Double.parseDouble(num));
                    }
                } else {
                    if (num.contains("l")) {
                        yield AnnotationValue.of(Long.parseLong(num));
                    } else if (num.contains("f")) {
                        yield AnnotationValue.of(Float.parseFloat(num));
                    } else if (num.contains("d")) {
                        yield AnnotationValue.of(Double.parseDouble(num));
                    } else {
                        yield AnnotationValue.of(Integer.parseInt(num));
                    }
                }
            }
            case LumParser.TrueContext _ -> AnnotationValue.of(true);
            case LumParser.FalseContext _ -> AnnotationValue.of(false);
            default -> throw new IllegalStateException("Unexpected value: " + literal);
        };
    }
}
