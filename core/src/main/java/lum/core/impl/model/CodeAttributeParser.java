package lum.core.impl.model;

import lum.antlr4.LumLexer;
import lum.antlr4.LumParser;
import lum.core.ir.CodeElement;
import lum.core.ir.Operator;
import lum.core.model.*;
import lum.core.util.Utils;
import lum.lang.reflect.Function;
import lum.lang.struct.Pair;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.IntStream;

public final class CodeAttributeParser extends AttributeParser<AttributeModel.CodeAttribute> {
    @SuppressWarnings("rawtypes")
    private static final Pair[] EMPTY_PAIRS_ARRAY = new Pair[0];
    private final MethodModelImpl methodModel;
    private final TypeProcessor typeProcessor;

    private final Map<String, TypeModel> variables = new HashMap<>();

    public CodeAttributeParser(MethodModel methodModel, TypeProcessor typeProcessor) { // todo: use
        this.methodModel = (MethodModelImpl) methodModel;
        this.typeProcessor = typeProcessor;
    }

    @Override
    public AttributeModel.CodeAttribute parseAttribute() {
        return parseCodeAttribute();
    }

    private AttributeModel.CodeAttribute parseCodeAttribute() {
        CodeAttributeImpl codeAttribute = new CodeAttributeImpl(parseCodeBlock());
        List<AttributeModel> list = Arrays.asList(methodModel.attributes());
        if (list.contains(null))
            list.set(list.indexOf(null), codeAttribute);

        return codeAttribute;
    }

    private CodeElement.@Nullable CodeBlock parseCodeBlock() {
        return methodModel.codeBlock().map(this::parseCodeBlock).orElse(null);
    }

    private CodeElement.CodeBlock parseCodeBlock(LumParser.CodeBlockContext ctx) {
        return parseCodeBlock(null, ctx);
    }

    private CodeElement.CodeBlock parseCodeBlock(CodeElement.CodeBlock parent, LumParser.CodeBlockContext ctx) {
        List<CodeElement> codeElements = new ArrayList<>();

        var block = CodeElement.CodeBlock.of(parent, methodModel, codeElements);
        for (ParseTree child : ctx.children) {
            switch (child) {
                case LumParser.ExpressionContext expr -> codeElements.add(parseExpression(block, expr));
                case LumParser.StatementContext stmt -> Collections.addAll(codeElements, parseStatement(block, stmt));
                default -> {}
            }
        }

        return block;
    }

    private CodeElement.StatementElement[] parseStatement(CodeElement.CodeBlock parent, LumParser.StatementContext ctx) {
        return switch (ctx) {
            case LumParser.IfStmtContext c -> new CodeElement.StatementElement[]{parseIfStatement(parent, c.ifStatement())};
            case LumParser.SwitchStmtContext c -> new CodeElement.StatementElement[]{parseSwitchStatement(parent, c.switchStatement())};
            case LumParser.ForIStmtContext c -> new CodeElement.StatementElement[]{parseForIStatement(parent, c.forILoop())};
            case LumParser.ForEachStmtContext c -> new CodeElement.StatementElement[]{parseForEachStatement(parent, c.forEachLoop())};
            case LumParser.WhileStmtContext c -> new CodeElement.StatementElement[]{parseWhileStatement(parent, c.whileLoop())};
            case LumParser.DoWhileStmtContext c -> new CodeElement.StatementElement[]{parseDoWhileStatement(parent, c.doWhileLoop())};
            case LumParser.BreakStmtContext c -> new CodeElement.StatementElement[]{parseBreakStatement(parent, c)};
            case LumParser.ContinueStmtContext c -> new CodeElement.StatementElement[]{parseContinueStatement(parent, c)};
            case LumParser.ReturnStmtContext c -> new CodeElement.StatementElement[]{parseReturnStatement(parent, c.return_())};
            case LumParser.VariableDeclarationStmtContext c -> parseVariableDeclarationStatement(parent, c.variableDeclaration());
            default -> throw new IllegalStateException("Unexpected value: " + ctx);
        };
    }

    private CodeElement.ExpressionElement parseExpression(CodeElement.CodeBlock parent, LumParser.ExpressionContext ctx) {
        return switch (ctx) {
            case LumParser.PrimaryExprContext c -> parsePrimaryExpression(parent, c.primaryExpression());
            case LumParser.LiteralExprContext c -> parseLiteral(parent, c.literal());
            case LumParser.PostfixExprContext c -> parsePostfixExpression(parent, c.postfixExpression());
            case LumParser.UnaryExprContext c -> parseUnaryExpression(parent, c.unaryExpression());
            case LumParser.MultiplicativeExprContext c -> parseMultiplicativeExpression(parent, c.multiplicativeExpression());
            case LumParser.AdditiveExprContext c -> parseAdditiveExpression(parent, c.additiveExpression());
            case LumParser.ShiftExprContext c -> parseShiftExpression(parent, c.shiftExpression());
            case LumParser.RelationalExprContext c -> parseRelationalExpression(parent, c.relationalExpression());
            case LumParser.EqualityExprContext c -> parseEqualityExpression(parent, c.equalityExpression());
            case LumParser.BitwiseAndExprContext c -> parseBitwiseAndExpression(parent, c.bitwiseAndExpression());
            case LumParser.BitwiseXorExprContext c -> parseBitwiseXorExpression(parent, c.bitwiseXorExpression());
            case LumParser.BitwiseOrExprContext c -> parseBitwiseOrExpression(parent, c.bitwiseOrExpression());
            case LumParser.LogicalAndExprContext c -> parseLogicalAndExpression(parent, c.logicalAndExpression());
            case LumParser.LogicalOrExprContext c -> parseLogicalOrExpression(parent, c.logicalOrExpression());
            case LumParser.AssignmentExprContext c -> parseAssignmentExpression(parent, c.assignmentExpression());
            default -> throw new IllegalStateException("Unexpected value: " + ctx);
        };
    }

    private CodeElement.ExpressionElement parsePrimaryExpression(CodeElement.CodeBlock parent, LumParser.PrimaryExpressionContext ctx) {
        return switch (ctx) {
            case LumParser.LiteralExpr1Context c -> parseLiteralExpr1(parent, c);
            case LumParser.IdentifierExprContext c -> parseIdentifierExpr(parent, c);
            case LumParser.ParenExprContext c -> parseParenExpr(parent, c);
            case LumParser.SuperAccessExprContext _ -> parseSuperAccessExpr(parent);
            case LumParser.ArrayExprContext c -> parseArrayExpr(parent, c);
            case LumParser.DictExprContext c -> parseDictExpr(parent, c);
            case LumParser.LambdaExprContext c -> parseLambdaExpr(parent, c);
            default -> throw new IllegalStateException("Unexpected value: " + ctx);
        };
    }

    private CodeElement.LiteralExpression<?> parseLiteralExpr1(CodeElement.CodeBlock parent, LumParser.LiteralExpr1Context ctx) {
        return parseLiteral(parent, ctx.literal());
    }

    private CodeElement.LiteralExpression<?> parseLiteral(CodeElement.CodeBlock parent, LumParser.LiteralContext literal) {
        return switch (literal) {
            case LumParser.TrueContext _ -> CodeElement.TrueExpr.of(parent);
            case LumParser.FalseContext _ -> CodeElement.FalseExpr.of(parent);
            case LumParser.NumberContext numberCtx -> {
                var numberText = numberCtx.NUMBER().getText().toLowerCase();
                if (numberText.startsWith("0x") || numberText.startsWith("0b") || numberText.startsWith("0")) {
                    if (numberText.endsWith("l")) {
                        yield CodeElement.NumberExpr.of(
                                parent,
                                TypeModel.LONG,
                                Long.parseLong(numberText)
                        );
                    }
                    yield CodeElement.NumberExpr.of(
                            parent,
                            TypeModel.INT,
                            Integer.parseInt(numberText)
                    );
                }

                if (numberText.contains(".")) {
                    if (numberText.endsWith("f")) {
                        yield CodeElement.NumberExpr.of(
                                parent,
                                TypeModel.FLOAT,
                                Float.parseFloat(numberText)
                        );
                    }
                    yield CodeElement.NumberExpr.of(
                            parent,
                            TypeModel.DOUBLE,
                            Double.parseDouble(numberText)
                    );
                }

                if (numberText.endsWith("l")) {
                    yield CodeElement.NumberExpr.of(
                            parent,
                            TypeModel.LONG,
                            Long.parseLong(numberText)
                    );
                }
                yield CodeElement.NumberExpr.of(
                        parent,
                        TypeModel.INT,
                        Integer.parseInt(numberText)
                );
            }
            case LumParser.StringContext stringCtx -> {
                String text = stringCtx.STRING().getText();
                var strLen = text.length();

                yield CodeElement.StringExpr.of(parent, text.substring(1, strLen-1));
            }
            case LumParser.NullContext _ -> CodeElement.NullExpr.of(parent);
            default -> throw new IllegalStateException("Unexpected value: " + literal);
        };
    }

    private CodeElement.IdentifierExpression parseIdentifierExpr(CodeElement.CodeBlock parent, LumParser.IdentifierExprContext ctx) {
        String text = ctx.IDENTIFIER().getText();

        Optional<TypeModel> type = typeProcessor.getType(text);
        if (type.isPresent())
            return CodeElement.IdentifierExpression.of(parent, type.get(), type.get());

        Optional<ClassModel> owner = methodModel.owner();
        if (variables.containsKey(text)) {
            return CodeElement.IdentifierExpression.of(parent, variables.get(text), text);
        }
        if (owner.isEmpty()) {
            throw new IllegalStateException("No such type or variable: " + text);
        }
        ClassModel ownerCM = owner.get();

        if (text.equals("this") && !methodModel.isStatic()) {
            return CodeElement.IdentifierExpression.of(parent, ownerCM.asTypeModel(), "this");
        }

        Optional<FieldModel> field = ownerCM.getField(text);
        if (field.isPresent())
            return CodeElement.IdentifierExpression.of(parent, field.get().type(), field.get());

        Optional<MethodModel> method = Arrays.stream(ownerCM.allMembers())
                .filter(m -> m.name().equals(text))
                .filter(m -> m instanceof MethodModel)
                .map(m -> ((MethodModel) m))
                .min(Comparator.comparingInt(o -> o.parameters().length));

        if (method.isPresent())
            return CodeElement.IdentifierExpression.of(parent, TypeModel.of(Function.class).orElseThrow(), method.get());

        throw new IllegalStateException("Found no member with name of: " + text);
    }

    private CodeElement.ExpressionElement parseParenExpr(CodeElement.CodeBlock parent, LumParser.ParenExprContext ctx) {
        return parseExpression(parent, ctx.expression());
    }

    private CodeElement.SuperAccessExpression parseSuperAccessExpr(CodeElement.CodeBlock parent) {
        ClassModel owner = methodModel.owner().orElseThrow(() -> new IllegalStateException("Method has no owner"));
        TypeModel superType = owner.superClass().orElse(owner.interfaces()[0]).asTypeModel();

        return CodeElement.SuperAccessExpression.of(parent, superType);
    }

    private CodeElement.ArrayExpression parseArrayExpr(CodeElement.CodeBlock parent, LumParser.ArrayExprContext ctx) {
        if (ctx.arrayInitializer().ARRAY_OP() != null)
            return CodeElement.ArrayExpression.of(parent, TypeModel.OBJECT.asArray(1));

        var elements = ctx.arrayInitializer().expression().stream()
                .map(expr -> parseExpression(parent, expr))
                .toArray(CodeElement.ExpressionElement[]::new);

        TypeModel type = TypeModel.leastUpperBoundOf(
                Arrays.stream(elements)
                        .map(CodeElement.ExpressionElement::type)
                        .toArray(TypeModel[]::new)
        );

        return CodeElement.ArrayExpression.of(parent, type, elements);
    }

    private CodeElement.DictExpression parseDictExpr(CodeElement.CodeBlock parent, LumParser.DictExprContext ctx) {
        List<LumParser.KvPairContext> kvPair = ctx.dictInitializer().kvPair();
        CodeElement.ExpressionElement[] keys = kvPair.stream()
                .map(LumParser.KvPairContext::expression)
                .map(expr -> parseExpression(parent, expr.getFirst()))
                .toArray(CodeElement.ExpressionElement[]::new);

        CodeElement.ExpressionElement[] values = kvPair.stream()
                .map(LumParser.KvPairContext::expression)
                .map(expr -> parseExpression(parent, expr.getLast()))
                .toArray(CodeElement.ExpressionElement[]::new);

        TypeModel kType = TypeModel.leastUpperBoundOf(
                Arrays.stream(keys)
                        .map(CodeElement.ExpressionElement::type)
                        .toArray(TypeModel[]::new)
        );

        TypeModel vType = TypeModel.leastUpperBoundOf(
                Arrays.stream(keys)
                        .map(CodeElement.ExpressionElement::type)
                        .toArray(TypeModel[]::new)
        );

        return CodeElement.DictExpression.of(parent, TypeModel.of(HashMap.class, kType, vType).orElseThrow(), keys, values);
    }

    @SuppressWarnings("unchecked")
    private CodeElement.LambdaExpression parseLambdaExpr(CodeElement.CodeBlock parent, LumParser.LambdaExprContext ctx) {
        return CodeElement.LambdaExpression.of(parent, TypeModel.OBJECT, EMPTY_PAIRS_ARRAY, parseCodeBlock(parent, ctx.codeBlock()));
    }

    private CodeElement.ExpressionElement parsePrimary(CodeElement.CodeBlock parent, LumParser.PrimaryContext ctx) {
        return switch (ctx.primaryExpression()) {
            case LumParser.LiteralExpr1Context c -> parseLiteralExpr1(parent, c);
            case LumParser.IdentifierExprContext c -> parseIdentifierExpr(parent, c);
            case LumParser.ParenExprContext c -> parseParenExpr(parent, c);
            case LumParser.SuperAccessExprContext _ -> parseSuperAccessExpr(parent);
            case LumParser.ArrayExprContext c -> parseArrayExpr(parent, c);
            case LumParser.DictExprContext c -> parseDictExpr(parent, c);
            case LumParser.LambdaExprContext c -> parseLambdaExpr(parent, c);
            default -> throw new IllegalStateException("Unexpected value: " + ctx.primaryExpression());
        };
    }
    private CodeElement.MemberAccessExpression parseMemberAccess(CodeElement.CodeBlock parent, LumParser.MemberAccessContext ctx) {
        CodeElement.ExpressionElement expr = parsePostfixExpression(parent, ctx.postfixExpression());
        ClassModel exprCM = expr.type().model();
        if (expr.type().isArray())
            exprCM = ClassModel.of(Object.class).orElseThrow();

        String memberName = ctx.IDENTIFIER() != null
                ? ctx.IDENTIFIER().getText()
                : "<init>";
        Member member = Arrays.stream(exprCM.allMembers())
                .filter(Objects::nonNull)
                .filter(m -> m.name().equals(memberName))
                .findFirst().orElseThrow();

        TypeModel memberType = switch (member) {
            case MethodModel _ -> TypeModel.of(Function.class).orElseThrow();
            case FieldModel field -> field.type();
            case ClassModel clazz -> clazz.asTypeModel();
            default -> throw new IllegalStateException("Unexpected value: " + member);
        };

        return CodeElement.MemberAccessExpression.of(parent, memberType, expr, member);
    }
    private CodeElement.CallExpression parseCallExpression(CodeElement.CodeBlock parent, LumParser.CallExpressionContext ctx) {
        var expr = parsePostfixExpression(parent, ctx.postfixExpression());
        CodeElement.ExpressionElement[] arguments = ctx.call().argumentList().expression().stream()
                .map(e -> parseExpression(parent, e))
                .toArray(CodeElement.ExpressionElement[]::new);

        return switch (expr) {
            case CodeElement.MemberAccessExpression member ->
                    callFromMember(parent, ctx, member.member(), member.expression().orElse(null), expr);
            case CodeElement.IdentifierExpression identifier ->
                    parseIdentifierCallExpression(parent, ctx, identifier, arguments, expr);
            default -> parseGenericCallExpression(parent, expr, arguments);
        };
    }
    private CodeElement.MemberAccessExpression parseFuncAccessExpression(CodeElement.CodeBlock parent, LumParser.GenericFuncAccessContext ctx) {
        CodeElement.ExpressionElement expr = parsePostfixExpression(parent, ctx.postfixExpression());
        TypeModel type = expr.type();
        ClassModel classModel = type.model();
        if (type.isArray())
            classModel = ClassModel.of(Object.class).orElseThrow();

        Optional<MethodModel> method = Arrays.stream(classModel.allMembers())
                .filter(m -> m instanceof MethodModel)
                .map(m -> ((MethodModel) m))
                .filter(method1 -> method1.name().equals(ctx.IDENTIFIER().getText()))
                .min(Comparator.comparingInt(m -> m.parameters().length));

        if (method.isEmpty())
            throw new IllegalStateException("No function found with name " + ctx.IDENTIFIER().getText());

        MethodModel methodModel = method.get();
        TypeModel returnType = resolveGenericReturnType(methodModel, ctx);

        return CodeElement.MemberAccessExpression.of(
                parent,
                TypeModel.of(Function.class, returnType).orElseThrow(),
                expr,
                methodModel
        );
    }
    private CodeElement.MemberAccessExpression parseGenericConstructorAccess(CodeElement.CodeBlock parent, LumParser.GenericConstructorAccessContext ctx) {
        CodeElement.ExpressionElement expr = parsePostfixExpression(parent, ctx.postfixExpression());
        TypeModel type = expr.type();
        ClassModel classModel = type.model();
        if (type.isArray())
            classModel = ClassModel.of(Object.class).orElseThrow();

        Optional<MethodModel> method = Arrays.stream(classModel.allMembers())
                .filter(m -> m instanceof MethodModel)
                .map(m -> ((MethodModel) m))
                .filter(method1 -> method1.name().equals("<init>"))
                .min(Comparator.comparingInt(m -> m.parameters().length));

        if (method.isEmpty())
            throw new IllegalStateException("No function found with name <init>");

        MethodModel methodModel = method.get();

        return CodeElement.MemberAccessExpression.of(
                parent,
                TypeModel.VOID,
                expr,
                methodModel
        );
    }
    private CodeElement.ArrayAccessExpression parseArrayAccess(CodeElement.CodeBlock parent, LumParser.ArrayAccessContext ctx) {
        CodeElement.ExpressionElement arrayExpr = parsePostfixExpression(parent, ctx.postfixExpression());

        TypeModel type = arrayExpr.type();
        CodeElement.ExpressionElement index = parseExpression(parent, ctx.expression());
        if (type.isArray()) {
            type = type.asComponent();
        } else {
            type = type.model().getMethod(
                    "get",
                    index.type()
            ).orElseThrow().returnType();
        }
        return CodeElement.ArrayAccessExpression.of(
                parent,
                type,
                arrayExpr,
                index
        );
    }

    private CodeElement.UnaryPostfixExpression parseIncrement(CodeElement.CodeBlock parent, LumParser.IncrementContext ctx) {
        CodeElement.ExpressionElement incremented = parsePostfixExpression(parent, ctx.postfixExpression());
        TypeModel type = incremented.type();
        if (!type.isPrimitive()) {
            type = type.model().getMethod("increment").orElseThrow().returnType();
        }

        return CodeElement.UnaryPostfixExpression.of(parent, type, incremented, Operator.UnaryOperator.INC);
    }
    private CodeElement.UnaryPostfixExpression parseDecrement(CodeElement.CodeBlock parent, LumParser.DecrementContext ctx) {
        CodeElement.ExpressionElement decremented = parsePostfixExpression(parent, ctx.postfixExpression());
        TypeModel type = decremented.type();
        if (!type.isPrimitive()) {
            type = type.model().getMethod("decrement").orElseThrow().returnType();
        }

        return CodeElement.UnaryPostfixExpression.of(parent, type, decremented, Operator.UnaryOperator.DEC);
    }

    private CodeElement.CastExpression parseCastExpression(CodeElement.CodeBlock parent, LumParser.CastExpressionContext ctx) {
        return CodeElement.CastExpression.of(parent, typeProcessor.getType(ctx.type()).orElseThrow(), parsePostfixExpression(parent, ctx.postfixExpression()));
    }

    private CodeElement.ExpressionElement parseUnaryExpression(CodeElement.CodeBlock parent, LumParser.UnaryExpressionContext ctx) {
        return switch (ctx) {
            case LumParser.OpExprContext op -> parseOpExpr(parent, op);
            case LumParser.IncExprContext inc -> parseIncExpr(parent, inc);
            case LumParser.DecExprContext dec -> parseDecExpr(parent, dec);
            case LumParser.PostfixContext postfix ->
                parsePostfix(parent, postfix);
            default -> throw new IllegalStateException("Unexpected value: " + ctx);
        };
    }

    private CodeElement.ExpressionElement parsePostfixExpression(CodeElement.CodeBlock parent, LumParser.PostfixExpressionContext ctx) {
        return switch (ctx) {
            case LumParser.PrimaryContext primary -> parsePrimary(parent, primary);
            case LumParser.MemberAccessContext memberAccess -> parseMemberAccess(parent, memberAccess);
            case LumParser.CallExpressionContext call -> parseCallExpression(parent, call);
            case LumParser.GenericFuncAccessContext funcAccess -> parseFuncAccessExpression(parent, funcAccess);
            case LumParser.GenericConstructorAccessContext constructorAccess -> parseGenericConstructorAccess(parent, constructorAccess);
            case LumParser.ArrayAccessContext arrayAccess -> parseArrayAccess(parent, arrayAccess);
            case LumParser.IncrementContext increment -> parseIncrement(parent, increment);
            case LumParser.DecrementContext decrement -> parseDecrement(parent, decrement);
            case LumParser.CastExpressionContext cast -> parseCastExpression(parent, cast);
            default -> throw new IllegalStateException("Unexpected value: " + ctx);
        };
    }

    private CodeElement.CallExpression parseIdentifierCallExpression(CodeElement.CodeBlock parent, 
                                                                    LumParser.CallExpressionContext call,
                                                                    CodeElement.IdentifierExpression identifier, 
                                                                    CodeElement.ExpressionElement[] arguments,
                                                                    CodeElement.ExpressionElement expr) {
        if (identifier.identifier().isRight())
            return callFromMember(parent, call, identifier.identifier().right(), null, expr);

        if (identifier.identifier().left().isRight()) {
            throw new IllegalStateException("Cannot invoke type " + identifier.identifier().left().right());
        }

        String varName = identifier.identifier().left().left();
        if (!variables.containsKey(varName))
            throw new IllegalStateException("Found no callable target with name " + varName);

        TypeModel type = variables.get(varName);
        if (type.isArray()) throw new IllegalStateException("Cannot call an array object");
        
        ClassModel varCM = type.model();
        TypeModel[] argTypes = Arrays.stream(arguments)
                .map(CodeElement.ExpressionElement::type)
                .toArray(TypeModel[]::new);

        Optional<MethodModel> method = getMethodModelOfCallable(varCM, argTypes);
        if (method.isEmpty())
            throw new IllegalStateException("Cannot call variable " + varName + " with type " + type);

        return CodeElement.CallExpression.of(parent, expr, method.get(), arguments);
    }

    private CodeElement.CallExpression parseGenericCallExpression(CodeElement.CodeBlock parent, 
                                                                 CodeElement.ExpressionElement expr,
                                                                 CodeElement.ExpressionElement[] arguments) {
        TypeModel type = expr.type();
        if (type.isArray()) throw new IllegalStateException("Cannot call an array object");
        
        ClassModel classModel = type.model();
        TypeModel[] argTypes = Arrays.stream(arguments)
                .map(CodeElement.ExpressionElement::type)
                .toArray(TypeModel[]::new);

        Optional<MethodModel> method = getMethodModelOfCallable(classModel, argTypes);
        if (method.isEmpty())
            throw new IllegalStateException("Cannot call object of type " + type);

        return CodeElement.CallExpression.of(parent, expr, method.get(), arguments);
    }

    private TypeModel resolveGenericReturnType(MethodModel methodModel, LumParser.GenericFuncAccessContext funcAccess) {
        TypeModel returnType = methodModel.returnType();
        if (methodModel.returnType() instanceof TypeModel.GenericTypeModel generic) {
            TypeParameter[] typeParameters = methodModel.typeParameters().orElse(Utils.EMPTY_TYPE_PARAMETERS);
            for (int i = 0; i < typeParameters.length; i++) {
                TypeParameter param = typeParameters[i];
                if (param.equals(generic.parameter())) {
                    LumParser.GenericBoundContext genericCtx = funcAccess.generic().genericBound(i);
                    returnType = genericCtx.QUESTION() == null
                            ? param.bound()
                            : typeProcessor.getType(genericCtx.lhs).orElseThrow();
                }
            }
        }
        return returnType;
    }

    private Optional<MethodModel> getMethodModelOfCallable(ClassModel callable, TypeModel[] argTypes) {
        Optional<MethodModel> method = callable.getMethod("call", argTypes);
        if (method.isPresent())
            return method;

        method = callable.getMethod("apply", argTypes);
        if (method.isPresent())
            return method;

        method = callable.getMethod("get", argTypes);
        if (method.isPresent())
            return method;

        method = callable.getMethod("test", argTypes);
        if (method.isPresent())
            return method;

        method = callable.getMethod("accept", argTypes);
        return method;
    }

    private CodeElement.@NotNull CallExpression callFromMember(CodeElement.CodeBlock parent,
                                                               LumParser.CallExpressionContext call,
                                                               Member member,
                                                               CodeElement.ExpressionElement caller,
                                                               CodeElement.ExpressionElement expr) {
        return switch (member) {
            case MethodModel method -> {
                CodeElement.ExpressionElement[] array = call.call().argumentList().expression().stream()
                        .map(e -> parseExpression(parent, e))
                        .toArray(CodeElement.ExpressionElement[]::new);
                yield CodeElement.CallExpression.of(
                        parent,
                        caller,
                        method,
                        array
                );
            }
            case FieldModel field -> { // todo: add more types
                TypeModel type = field.type();
                if (type.isArray())
                    throw new IllegalStateException("Cannot call an array object as function");
                ClassModel functionModel = ClassModel.of(java.util.function.Function.class).orElseThrow();
                ClassModel model = type.model();
                if (!model.isSubclassOf(functionModel)) {
                    throw new IllegalStateException("Cannot call an array object as function");
                }
                CodeElement.ExpressionElement arg = parseExpression(parent, call.call().argumentList().expression(0));
                var method = model.getMethod("apply", arg.type()).orElseThrow();

                yield CodeElement.CallExpression.of(parent, expr, method, new CodeElement.ExpressionElement[]{arg});
            }
            case null, default -> throw new IllegalStateException("Cannot call " + member);
        };
    }

    private CodeElement.UnaryPrefixExpression parseOpExpr(CodeElement.CodeBlock parent, LumParser.OpExprContext ctx) {
        return CodeElement.UnaryPrefixExpression.of(
                parent,
                switch (ctx.op.getType()) {
                    case LumLexer.ADD -> Operator.UnaryOperator.PLUS;
                    case LumLexer.SUB -> Operator.UnaryOperator.MINUS;
                    case LumLexer.NOT -> Operator.UnaryOperator.NOT;
                    case LumLexer.TILDA -> Operator.UnaryOperator.TILDA;
                    default -> throw new IllegalStateException("Unexpected value: " + ctx.op.getType());
                },
                parseUnaryExpression(parent, ctx.unaryExpression()));
    }
    private CodeElement.UnaryPrefixExpression parseIncExpr(CodeElement.CodeBlock parent, LumParser.IncExprContext ctx) {
        // todo: handling return type with method (???)

        return CodeElement.UnaryPrefixExpression.of(
                parent,
                Operator.UnaryOperator.INC,
                parseUnaryExpression(parent, ctx.unaryExpression())
        );
    }
    private CodeElement.UnaryPrefixExpression parseDecExpr(CodeElement.CodeBlock parent, LumParser.DecExprContext ctx) {
        return CodeElement.UnaryPrefixExpression.of(
                parent,
                Operator.UnaryOperator.DEC,
                parseUnaryExpression(parent, ctx.unaryExpression())
        );
    }
    private CodeElement.ExpressionElement parsePostfix(CodeElement.CodeBlock parent, LumParser.PostfixContext ctx) {
        return parsePostfixExpression(parent, ctx.postfixExpression());
    }

    private CodeElement.ExpressionElement parseMultiplicativeExpression(CodeElement.CodeBlock parent, LumParser.MultiplicativeExpressionContext ctx) {
        CodeElement.ExpressionElement left = parseUnaryExpression(parent, ctx.unaryExpression(0));
        Optional<CodeElement.ExpressionElement> right = Optional.ofNullable(ctx.unaryExpression(1))
                .map(expr -> parseUnaryExpression(parent, expr));

        if (right.isEmpty())
            return left;

        Operator.BinaryOperator op = switch (ctx.op.getType()) {
            case LumLexer.MUL -> Operator.BinaryOperator.MUL;
            case LumLexer.DIV -> Operator.BinaryOperator.DIVIDE;
            case LumLexer.IDIV -> Operator.BinaryOperator.DIV;
            case LumLexer.MOD -> Operator.BinaryOperator.MOD;
            default -> throw new IllegalStateException("Unexpected value: " + ctx.op.getType());
        };

        return CodeElement.BinaryExpression.of(parent, left, op, right.get());
    }

    private CodeElement.ExpressionElement parseAdditiveExpression(CodeElement.CodeBlock parent, LumParser.AdditiveExpressionContext ctx) {
        CodeElement.ExpressionElement left = parseMultiplicativeExpression(parent, ctx.multiplicativeExpression(0));
        Optional<CodeElement.ExpressionElement> right = Optional.ofNullable(ctx.multiplicativeExpression(1))
                .map(expr -> parseMultiplicativeExpression(parent, expr));

        if (right.isEmpty())
            return left;

        Operator.BinaryOperator op = switch (ctx.op.getType()) {
            case LumLexer.ADD -> Operator.BinaryOperator.PLUS;
            case LumLexer.SUB -> Operator.BinaryOperator.MINUS;
            default -> throw new IllegalStateException("Unexpected value: " + ctx.op.getType());
        };

        return CodeElement.BinaryExpression.of(parent, left, op, right.get());
    }
    private CodeElement.ExpressionElement parseShiftExpression(CodeElement.CodeBlock parent, LumParser.ShiftExpressionContext ctx) {
        CodeElement.ExpressionElement left = parseAdditiveExpression(parent, ctx.additiveExpression(0));
        Optional<CodeElement.ExpressionElement> right = Optional.ofNullable(ctx.additiveExpression(1))
                .map(expr -> parseAdditiveExpression(parent, expr));

        if (right.isEmpty())
            return left;

        Operator.BinaryOperator op = switch (ctx.op.getType()) {
            case LumLexer.SHL -> Operator.BinaryOperator.SHL;
            case LumLexer.SHR -> Operator.BinaryOperator.SHR;
            default -> throw new IllegalStateException("Unexpected value: " + ctx.op.getType());
        };

        return CodeElement.BinaryExpression.of(parent, left, op, right.get());
    }
    private CodeElement.ExpressionElement parseRelationalExpression(CodeElement.CodeBlock parent, LumParser.RelationalExpressionContext ctx) {
        CodeElement.ExpressionElement left = parseShiftExpression(parent, ctx.shiftExpression(0));
        Optional<CodeElement.ExpressionElement> right = Optional.ofNullable(ctx.shiftExpression(1))
                .map(expr -> parseShiftExpression(parent, expr));

        if (right.isEmpty())
            return left;

        Operator.BinaryOperator op = switch (ctx.op.getType()) {
            case LumLexer.GT -> Operator.BinaryOperator.GT;
            case LumLexer.LT -> Operator.BinaryOperator.LT;
            case LumLexer.GE -> Operator.BinaryOperator.GE;
            case LumLexer.LE -> Operator.BinaryOperator.LE;
            case LumLexer.IS -> Operator.BinaryOperator.IS_INSTANCE;
            case LumLexer.IN -> Operator.BinaryOperator.IN;
            default -> throw new IllegalStateException("Unexpected value: " + ctx.op.getType());
        };

        return CodeElement.BinaryExpression.of(parent, left, op, right.get());
    }
    private CodeElement.ExpressionElement parseEqualityExpression(CodeElement.CodeBlock parent, LumParser.EqualityExpressionContext ctx) {
        CodeElement.ExpressionElement left = parseRelationalExpression(parent, ctx.relationalExpression(0));
        Optional<CodeElement.ExpressionElement> right = Optional.ofNullable(ctx.relationalExpression(1))
                .map(expr -> parseRelationalExpression(parent, expr));

        if (right.isEmpty())
            return left;

        Operator.BinaryOperator op = switch (ctx.op.getType()) {
            case LumLexer.EQUAL_EQUAL -> Operator.BinaryOperator.EQ;
            case LumLexer.NOT_EQUAL -> Operator.BinaryOperator.NEQ;
            default -> throw new IllegalStateException("Unexpected value: " + ctx.op.getType());
        };

        return CodeElement.BinaryExpression.of(parent, left, op, right.get());
    }
    private CodeElement.ExpressionElement parseBitwiseAndExpression(CodeElement.CodeBlock parent, LumParser.BitwiseAndExpressionContext ctx) {
        CodeElement.ExpressionElement left = parseEqualityExpression(parent, ctx.equalityExpression(0));
        Optional<CodeElement.ExpressionElement> right = Optional.ofNullable(ctx.equalityExpression(1))
                .map(expr -> parseEqualityExpression(parent, expr));

        if (right.isEmpty())
            return left;

        Operator.BinaryOperator op = Operator.BinaryOperator.AND;

        return CodeElement.BinaryExpression.of(parent, left, op, right.get());
    }
    private CodeElement.ExpressionElement parseBitwiseXorExpression(CodeElement.CodeBlock parent, LumParser.BitwiseXorExpressionContext ctx) {
        CodeElement.ExpressionElement left = parseBitwiseAndExpression(parent, ctx.bitwiseAndExpression(0));
        Optional<CodeElement.ExpressionElement> right = Optional.ofNullable(ctx.bitwiseAndExpression(1))
                .map(expr -> parseBitwiseAndExpression(parent, expr));

        if (right.isEmpty())
            return left;

        Operator.BinaryOperator op = Operator.BinaryOperator.XOR;

        return CodeElement.BinaryExpression.of(parent, left, op, right.get());
    }
    private CodeElement.ExpressionElement parseBitwiseOrExpression(CodeElement.CodeBlock parent, LumParser.BitwiseOrExpressionContext ctx) {
        CodeElement.ExpressionElement left = parseBitwiseXorExpression(parent, ctx.bitwiseXorExpression(0));
        Optional<CodeElement.ExpressionElement> right = Optional.ofNullable(ctx.bitwiseXorExpression(1))
                .map(expr -> parseBitwiseXorExpression(parent, expr));

        if (right.isEmpty())
            return left;

        Operator.BinaryOperator op = Operator.BinaryOperator.OR;

        return CodeElement.BinaryExpression.of(parent, left, op, right.get());
    }
    private CodeElement.ExpressionElement parseLogicalAndExpression(CodeElement.CodeBlock parent, LumParser.LogicalAndExpressionContext ctx) {
        CodeElement.ExpressionElement left = parseBitwiseOrExpression(parent, ctx.bitwiseOrExpression(0));
        Optional<CodeElement.ExpressionElement> right = Optional.ofNullable(ctx.bitwiseOrExpression(1))
                .map(expr -> parseBitwiseOrExpression(parent, expr));

        if (right.isEmpty())
            return left;

        Operator.BinaryOperator op = Operator.BinaryOperator.AND;

        return CodeElement.BinaryExpression.of(parent, left, op, right.get());
    }
    private CodeElement.ExpressionElement parseLogicalOrExpression(CodeElement.CodeBlock parent, LumParser.LogicalOrExpressionContext ctx) {
        CodeElement.ExpressionElement left = parseLogicalAndExpression(parent, ctx.logicalAndExpression(0));
        Optional<CodeElement.ExpressionElement> right = Optional.ofNullable(ctx.logicalAndExpression(1))
                .map(expr -> parseLogicalAndExpression(parent, expr));

        if (right.isEmpty())
            return left;

        Operator.BinaryOperator op = Operator.BinaryOperator.OR;

        return CodeElement.BinaryExpression.of(parent, left, op, right.get());
    }
    private CodeElement.ExpressionElement parseAssignmentExpression(CodeElement.CodeBlock parent, LumParser.AssignmentExpressionContext ctx) {
        CodeElement.ExpressionElement left = parseLogicalOrExpression(parent, ctx.logicalOrExpression());
        Optional<CodeElement.ExpressionElement> right = Optional.ofNullable(ctx.assignmentExpression())
                .map(expr -> parseAssignmentExpression(parent, expr));

        return right.map(expressionElement -> ((CodeElement.ExpressionElement) switch (left) {
            case CodeElement.VariableDeclarationStatement varDecl
                    when ctx.WALRUS() != null ->
                    CodeElement.AssignmentExpression.of(parent, varDecl, expressionElement);
            case CodeElement.AssignmentExpression assignment
                    when ctx.WALRUS() != null ->
                    CodeElement.AssignmentExpression.of(parent, assignment, expressionElement);
            case CodeElement.ArrayAccessExpression arrayAccess
                    when ctx.WALRUS() != null ->
                    CodeElement.AssignmentExpression.of(parent, arrayAccess, expressionElement);

            case CodeElement.VariableDeclarationStatement varDecl
                    when ctx.WALRUS() == null ->
                    CodeElement.AssignmentExpression.of(parent, varDecl.type(), varDecl, expressionElement);
            case CodeElement.AssignmentExpression assignment
                    when ctx.WALRUS() == null ->
                    CodeElement.AssignmentExpression.of(parent, assignment.type(), assignment, expressionElement);
            case CodeElement.ArrayAccessExpression arrayAccess
                    when ctx.WALRUS() == null ->
                    CodeElement.AssignmentExpression.of(parent, arrayAccess.type(), arrayAccess, expressionElement);
            default -> throw new IllegalStateException("Unexpected value: " + left);
        })).orElse(left);
    }

    private CodeElement.IfStatement parseIfStatement(CodeElement.CodeBlock parent, LumParser.IfStatementContext ctx) {
        CodeElement.ExpressionElement ifCond = parseExpression(parent, ctx.if_().expression());
        CodeElement.CodeBlock ifBlock = parseCodeBlock(parent, ctx.if_().codeBlock());

        Optional<List<LumParser.ElifContext>> elifs = Optional.ofNullable(ctx.elif());
        Pair<CodeElement.ExpressionElement, CodeElement.CodeBlock>[] elifPairs = null;
        if (elifs.map(l -> !l.isEmpty()).orElse(false)) {
            //noinspection unchecked
            elifPairs = elifs.get().stream().map(
                    elif ->
                            new Pair<>(
                                    parseExpression(parent, elif.expression()),
                                    parseCodeBlock(parent, elif.codeBlock())
                            )
            ).toArray(Pair[]::new);
        }

        if (ctx.else_() != null) {
            return CodeElement.IfStatement.of(parent, ifCond, ifBlock, elifPairs, parseCodeBlock(parent, ctx.else_().codeBlock()));
        }
        return CodeElement.IfStatement.of(parent, ifCond, ifBlock, elifPairs);
    }
    private CodeElement.SwitchStatement parseSwitchStatement(CodeElement.CodeBlock parent, LumParser.SwitchStatementContext ctx) {
        //noinspection unchecked
        return CodeElement.SwitchStatement.of(
                parent,
                parseExpression(parent, ctx.expression()),
                ctx.switchCase().stream().map(
                        case_ ->
                                new Pair<>(
                                        parseExpression(parent, case_.expression()),
                                        parseCodeBlock(parent, case_.codeBlock())
                                )
                ).toArray(Pair[]::new),
                parseCodeBlock(parent, ctx.defaultCase().codeBlock())
        );
    }
    private CodeElement.ForLoopElement parseForIStatement(CodeElement.CodeBlock parent, LumParser.ForILoopContext ctx) {
        CodeElement.VariableDeclarationStatement variable = parseVariable(parent, ctx.variable());

        return CodeElement.ForLoopElement.of(
                parent,
                variable,
                parseExpression(parent, ctx.expression(0)),
                parseExpression(parent, ctx.expression(1)),
                parseCodeBlock(parent, ctx.codeBlock())
        );
    }

    private CodeElement.ForEachLoopElement parseForEachStatement(CodeElement.CodeBlock parent, LumParser.ForEachLoopContext ctx) {
        return CodeElement.ForEachLoopElement.of(
                parent,
                parseVariable(parent, ctx.variable()),
                parseExpression(parent, ctx.expression()),
                parseCodeBlock(parent, ctx.codeBlock())
        );
    }

    private CodeElement.WhileLoopElement parseWhileStatement(CodeElement.CodeBlock parent, LumParser.WhileLoopContext ctx) {
        return CodeElement.WhileLoopElement.of(
                parent,
                parseExpression(parent, ctx.expression()),
                parseCodeBlock(parent, ctx.codeBlock())
        );
    }
    private CodeElement.DoWhileLoopElement parseDoWhileStatement(CodeElement.CodeBlock parent, LumParser.DoWhileLoopContext ctx) {
        return CodeElement.DoWhileLoopElement.of(
                parent,
                parseExpression(parent, ctx.expression()),
                parseCodeBlock(parent, ctx.codeBlock())
        );
    }
    private CodeElement.BreakStatement parseBreakStatement(CodeElement.CodeBlock parent, LumParser.BreakStmtContext ctx) {
        if (ctx.IDENTIFIER() != null)
            return CodeElement.BreakStatement.of(parent, ctx.IDENTIFIER().getText());
        return CodeElement.BreakStatement.of(parent);
    }
    private CodeElement.ContinueStatement parseContinueStatement(CodeElement.CodeBlock parent, LumParser.ContinueStmtContext ctx) {
        if (ctx.IDENTIFIER() != null)
            return CodeElement.ContinueStatement.of(parent, ctx.IDENTIFIER().getText());
        return CodeElement.ContinueStatement.of(parent);
    }
    private CodeElement.ReturnStatement parseReturnStatement(CodeElement.CodeBlock parent, LumParser.ReturnContext ctx) {
        if (ctx.expression() != null)
            return CodeElement.ReturnStatement.of(parent, parseExpression(parent, ctx.expression()));
        return CodeElement.ReturnStatement.of(parent);
    }

    private CodeElement.VariableDeclarationStatement[] parseVariableDeclarationStatement(CodeElement.CodeBlock parent, LumParser.VariableDeclarationContext ctx) {
        return ctx.variable().stream().map(
                v -> parseVariable(parent, v)
        ).toArray(CodeElement.VariableDeclarationStatement[]::new);
    }

    private CodeElement.@NotNull VariableDeclarationStatement parseVariable(CodeElement.CodeBlock parent, LumParser.VariableContext ctx) {
        String varName = ctx.IDENTIFIER().getText();
        Optional<TypeModel> type = typeProcessor.getType(ctx.type());
        Optional<CodeElement.ExpressionElement> expression = Optional.ofNullable(ctx.expression())
                .map(expr -> parseExpression(parent, expr));

        if (type.isEmpty())
            type = expression
                    .map(CodeElement.ExpressionElement::type);

        if (type.isEmpty())
            type = Optional.of(TypeModel.OBJECT);

        variables.put(varName, type.get());

        return CodeElement.VariableDeclarationStatement.of(
                parent,
                type.orElseThrow(),
                varName,
                expression.orElse(null)
        );
    }
}
