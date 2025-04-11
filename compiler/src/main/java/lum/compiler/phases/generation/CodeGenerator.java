package lum.compiler.phases.generation;

import lum.compiler.codegen.CodeMaker;
import lum.compiler.codegen.InlinedVariable;
import lum.compiler.codegen.Variable;
import lum.core.model.FieldModel;
import lum.core.model.Imports;
import lum.core.model.MethodModel;
import lum.core.model.TypeModel;
import lum.core.parsing.antlr4.LumParser;
import lum.core.util.TypeModelList;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.Label;
import java.util.*;
import java.util.function.BiFunction;

public class CodeGenerator implements CodeHandler {
    private final CodeMaker cm;
    private final MethodModel model;
    private final Imports imports;
    private final HashMap<String, Integer> parameters = new HashMap<>();

    public CodeGenerator(CodeMaker cm, MethodModel model, Imports imports) {
        this.cm = cm;
        this.model = model;
        this.imports = imports;

        int i = model.isStatic() ? 0 : 1;
        for (var param : model.parameters()) {
            parameters.put(param.name(), i++);
        }
    }

    public CodeGenerator(CodeMaker cm, CodeGenerator parent) {
        this.cm = cm;
        this.model = parent.model;
        this.imports = parent.imports;

        this.parameters.putAll(parent.parameters);
    }

    int blocksCount = 0;
    int returnsCount = 0;

    @Override
    public void handleBlock(LumParser.BlockContext ctx) {
        ctx.statement().forEach(context -> {
            var result = handleStatement(context);
            result.ifPresent(vars -> {
                for (var v : vars)
                    if (v instanceof InlinedVariable inlined)
                        inlined.load(cm);
            });
        });
        blocksCount++;
    }

    public void generate(LumParser.BlockContext ctx) {
        handleBlock(ctx);
        if (model.returnType() == TypeModel.of(void.class)) {
            if (returnsCount == 0)
                cm.return_();
        } else {
            throw new IllegalStateException("Method ("+model+") must return a value!");
        }
    }

    @Override
    public void handleIfStatement(LumParser.IfStatementContext ctx) {
        if (ctx.else_() == null && (ctx.elif() == null || ctx.elif().isEmpty())) {
            handleIf(ctx.if_());
        } else if ((ctx.elif() == null || ctx.elif().isEmpty())) {
            handleIfElse(ctx.if_(), ctx.else_());
        } else {
            handleIfElifElse(ctx.if_(), ctx.elif(), ctx.else_());
        }
    }

    private void handleIf(LumParser.IfContext if_) {
        var value = createConditionVariable(if_.expression());
        cm.ifThen(value, _ -> handleBlock(if_.block()));
    }

    private void handleIfElse(LumParser.IfContext if_, LumParser.ElseContext else_) {
        var value = createConditionVariable(if_.expression());
        cm.ifThenElse(value, _ -> handleBlock(if_.block()), _ -> handleBlock(else_.block()));
    }

    private void handleIfElifElse(LumParser.IfContext if_, List<LumParser.ElifContext> elifs, LumParser.ElseContext else_) {
        CodeBuilder cb = cm.codeBuilder();
        Label endLabel = cb.newLabel();

        handleConditionBlock(if_.expression(), if_.block(), endLabel);

        for (LumParser.ElifContext elif : elifs) {
            handleElifBlock(elif, endLabel);
        }

        handleBlock(else_.block());

        cb.labelBinding(endLabel);
    }

    private void handleConditionBlock(LumParser.ExpressionContext expression, LumParser.BlockContext block, Label endLabel) {
        Variable value = createConditionVariable(expression);

        cm.ifThen(value, _ -> {
            handleBlock(block);
            cm.codeBuilder().goto_(endLabel);
        });
    }

    private void handleElifBlock(LumParser.ElifContext elif, Label endLabel) {
        Variable value = createConditionVariable(elif.expression());
        value.load();

        CodeBuilder cb = cm.codeBuilder();
        Label elifEnd = cb.newLabel();

        cb.ifne(elifEnd);
        handleBlock(elif.block());
        cb.goto_(endLabel);
        cb.labelBinding(elifEnd);
    }

    private Variable createConditionVariable(LumParser.ExpressionContext expression) {
        return cm.var()
                .addCode(_ -> handleExpression(expression))
                .build();
    }

    @Override
    public void handleSwitchStatement(LumParser.SwitchStatementContext ctx) {
        Variable switchValue = handleExpression(ctx.expression());

        List<LumParser.CaseContext> cases = ctx.case_();

        LumParser.DefaultContext defaultCase = ctx.default_();

        if (cases.isEmpty() && defaultCase == null) {
            return;
        }

        CodeBuilder cb = cm.codeBuilder();
        Label endLabel = cb.newLabel();

        for (LumParser.CaseContext caseCtx : cases) {
            LumParser.ExpressionContext caseExpr = caseCtx.expression();
            Variable caseValue = handleExpression(caseExpr);

            Variable comparison = switchValue.eq(caseValue);

            cm.ifThen(comparison, _ -> {
                handleBlock(caseCtx.block());
                cm.codeBuilder().goto_(endLabel);
            });
        }

        if (defaultCase != null) {
            handleBlock(defaultCase.block());
        }

        cb.labelBinding(endLabel);
    }

    @Override
    public void handleWhileLoop(LumParser.WhileLoopContext ctx) {
        cm.while_(cm -> handleExpression(ctx.expression()).load(cm), _ -> handleBlock(ctx.block()));
    }

    @Override
    public void handleDoWhileLoop(LumParser.DoWhileLoopContext ctx) {
        cm.doWhile(cm -> handleExpression(ctx.expression()).load(cm), _ -> handleBlock(ctx.block()));
    }

    @Override
    public void handleForILoop(LumParser.ForILoopContext ctx) {
        Variable i = handleVariableDeclaration(ctx.variableDeclaration());

        cm.for_(i,
                (cm, _) -> {
                    var v = handleExpression(ctx.condition);
                    if (v instanceof InlinedVariable) {
                        v.load(cm);
                    }
                },
                (cm, _) -> {
                    var v = handleExpression(ctx.iter);
                    if (v instanceof InlinedVariable) {
                        v.load(cm);
                    }
                },
                (_, _) -> handleBlock(ctx.block())
        );
    }

    private TypeModel getType(LumParser.TypeContext type) {
        return imports.getType(type);
    }

    private TypeModel getType(String type) {
        var t = imports.getType(List.of(type.split("\\.")));
        if (t == null)
            t = getType(model.owner().pkg()+"."+type);
        return t;
    }

    @Override
    public void handleForEachLoop(LumParser.ForEachLoopContext ctx) {
        var v = ctx.variableDeclaration();
        String name = v.IDENTIFIER().getText();
        Variable value = null;
        TypeModel type = null;
        if (v.type() != null) {
            type = getType(v.type());
        }
        if (v.expression() != null) {
            value = handleExpression(v.expression());
            if (type == null)
                type = value.getType();
        }
        Variable i = cm.var(name, type, value);

        Variable iterable = handleExpression(ctx.expression());

        cm.foreach(
                i,
                iterable,
                (cm, _) -> {
                    CodeGenerator cg = new CodeGenerator(cm, this);
                    cg.handleBlock(ctx.block());
                }
        );
    }

    @Override
    public void handleBreak(LumParser.BreakContext ctx) {
        cm.break_();
    }

    @Override
    public void handleContinue(LumParser.ContinueContext ctx) {
        cm.continue_();
    }

    @Override
    public void handleReturn(LumParser.ReturnContext ctx) {
        if (ctx.expression() != null) {
            cm.return_(handleExpression(ctx.expression()));
            return;
        }
        cm.return_();

        returnsCount++;
    }

    @Override
    public void handleFunctionDeclaration(LumParser.FunctionDeclarationContext ctx) {
        // Implementation for function declaration
    }

    @Override
    public Variable handleVariableDeclaration(LumParser.VariableDeclarationContext ctx) {
        TypeModel type = null;
        Variable value = null;
        String name = ctx.IDENTIFIER().getText();

        if (ctx.type() != null)
            type = getType(ctx.type());
        if (ctx.expression() != null) {
            value = handleExpression(ctx.expression());
            if (type == null) {
                type = value.getType();
            }
        }

        Variable variable;
        if (cm.hasVariable(name)) {
            variable = cm.var(name, type, value);
            if (ctx.typeEq != null) {
                variable.set(type, value);
            } else {
                variable.set(value);
            }
        } else {
            variable = cm.var(name, type, value);
        }

        return variable;
    }

    @Override
    public void handleOperatorDeclaration(LumParser.OperatorDeclarationContext ctx) {
        // Implementation for operator declaration
    }

    @Override
    public void handleConstructorDeclaration(LumParser.ConstructorDeclarationContext ctx) {
        // Implementation for constructor declaration
    }

    @Override
    public void handleFunctionSignature(LumParser.FunctionSignatureContext ctx) {
        // Implementation for function signature
    }

    @Override
    public void handleClassDeclaration(LumParser.ClassDeclarationContext ctx) {
        // Implementation for class declaration
    }

    @Override
    public void handleInterfaceDeclaration(LumParser.InterfaceDeclarationContext ctx) {
        // Implementation for interface declaration
    }

    @Override
    public void handleAnnotationDeclaration(LumParser.AnnotationDeclarationContext ctx) {
        // Implementation for annotation declaration
    }

    @Override
    public Variable handleFalseLiteral() {
        return cm.var(TypeModel.BOOLEAN, 0);
    }

    @Override
    public Variable handleTrueLiteral() {
        return cm.var(TypeModel.BOOLEAN, 1);
    }

    @Override
    public Variable handleNullLiteral() {
        return cm.var(TypeModel.OBJECT).setCodeMaker(cm).addCode(cm -> cm.codeBuilder().aconst_null()).build();
    }

    @Override
    public Variable handleNumberLiteral(LumParser.NumContext number) {
        String num = number.NUMBER().getText().toLowerCase();
        var v = cm.var();
        if (num.contains(".")) {
            if (num.contains("f")) {
                v.addCode(cm -> cm.load(Float.parseFloat(num))).setType(TypeModel.FLOAT);
            } else {
                v.addCode(cm -> cm.load(Double.parseDouble(num))).setType(TypeModel.DOUBLE);
            }
        } else {
            if (num.contains("l")) {
                v.addCode(cm -> cm.load(Long.parseLong(num))).setType(TypeModel.LONG);
            } else if (num.contains("f")) {
                v.addCode(cm -> cm.load(Float.parseFloat(num))).setType(TypeModel.FLOAT);
            } else if (num.contains("d")) {
                v.addCode(cm -> cm.load(Double.parseDouble(num))).setType(TypeModel.DOUBLE);
            } else {
                v.addCode(cm -> cm.load(Integer.parseInt(num))).setType(TypeModel.INT);
            }
        }

        return v.build();
    }

    @Override
    public Variable handleStringLiteral(LumParser.StrContext string) {
        String str = string.STRING().getText().substring(1);
        str = str.substring(0, str.length()-1);
        return cm.var(TypeModel.STRING, str);
    }

    @Override
    public Variable handleIdentifierExpr(LumParser.IdentifierExprContext ctx) { // todo: var assignment
        String identifier = ctx.IDENTIFIER().getText();

        if (parameters.containsKey(identifier))
            return cm.parameter(parameters.get(identifier));

        if (cm.hasVariable(identifier))
            return cm.var(identifier);

        if (model.owner().getField(identifier) != null) {
            return handleFieldAccess(identifier);
        }

        if (imports.fields().containsKey(identifier)) {
            FieldModel field = imports.fields().get(identifier);
            return cm.var(field.owner().typeModel()).build().field(field.name());
        }

        //noinspection ConstantValue
        if (getType(identifier) != null)
            return cm.var().setType(getType(identifier)).build();

        return cm.var(identifier);
    }

    private Variable handleFieldAccess(String identifier) {
        FieldModel field = model.owner().getField(identifier);
        if (!field.isStatic() && model.isStatic())
            throw new IllegalStateException("Cannot reference non static fields from static context");
        if (model.isStatic()) {
            return cm.var(model.owner().typeModel()).build().field(identifier);
        }
        return cm.this_().field(field.name());
    }

    @Override
    public Variable handleAssignmentExpr(LumParser.AssignmentExprContext ctx) {
        Variable var = handleExpression(ctx.expression());
        Variable expr = handleExpression(ctx.assignment().expression());
        TypeModel type = ctx.assignment().typeEq != null ? expr.getType() : var.getType();

        if (ctx.assignment().binaryOperator() != null) {
            var.set(OperatorHandler.handleOperator(ctx.assignment().binaryOperator().getClass(), var, expr));
        } else {
            var.set(type, expr);
        }

        return var;
    }

    @Override
    public Variable handleListLiteral(LumParser.ListLiteralContext ctx) {
        Variable[] values = handleArgumentList(ctx.argumentList());
        TypeModelList types = new TypeModelList(Arrays.stream(values).map(Variable::getType).toArray(TypeModel[]::new));
        TypeModel componentType = types.getCommonAncestor();

        return cm.var().addCode(cm -> {
            var array = cm.var("array"+ctx.hashCode(), componentType.asArray(1)).set(cm.newArray(componentType, values.length));
            for (int i = 0; i < values.length; i++) {
                var value = values[i];
                array.arrayAccess(value, new Variable[]{cm.var(i)});
            }
            array.load(cm);
        }).setType(componentType.asArray(1)).build();
    }

    @Override
    public Variable handleDictLiteral(LumParser.DictLiteralContext ctx) {
        // Implementation for dict literal
        var hashMap =
                cm.var("tmpMap"+ctx.hashCode(), TypeModel.of(Map.class))
                .set(cm.new_(TypeModel.of(HashMap.class)));

        for (var kv : ctx.keyValueList().keyValue()) {
            var key = handleExpression(kv.expression(0));
            var val = handleExpression(kv.expression(1));

            hashMap.invoke("put", key, val).load(cm);
        }

        return hashMap;
    }

    @Override
    public Variable handleParenExpr(LumParser.ParenExprContext ctx) {
        return handleExpression(ctx.expression());
    }

    @Override
    public Variable handleSuperAccess(LumParser.SuperAccessContext ctx) {
        return cm.super_();
    }

    @Override
    public Variable handleCastExpression(LumParser.CastExpressionContext ctx) {
        return handleExpression(ctx.expression()).cast(getType(ctx.type()));
    }

    @Override
    public Variable handleLambdaExpression(LumParser.LambdaExpressionContext ctx) {
        // Implementation for lambda expression
        return null;
    }

    @Override
    public Variable handleFunctionCallExpr(LumParser.FunctionCallExprContext ctx) {
        var funcCall = ctx.functionCall();
        var argsCtx = funcCall.argumentList();
        Variable[] args;
        String methodName = funcCall.IDENTIFIER().getText();

        if (argsCtx == null || argsCtx.expression() == null || argsCtx.expression().isEmpty()) {
            var method = imports.getMethod(methodName);
            if (method != null)
                return cm.invokestatic(method);
            return cm.invokestatic(methodName);
        } else {
            args = handleArgumentList(argsCtx);
            var method = imports.getMethod(methodName, Arrays.stream(args).map(Variable::getType).toList());
            if (method != null)
                return cm.invokestatic(method, args);
            return cm.invokestatic(methodName, args);
        }
    }

    @Override
    public Variable handleSuperCallExpr(LumParser.SuperCallExprContext superCall) {
        var args = handleArgumentList(superCall.superCall().argumentList());

        return cm.superInit(args);
    }

    private Variable[] handleArgumentList(LumParser.ArgumentListContext ctx) {
        if (ctx == null || ctx.expression() == null || ctx.expression().isEmpty()) return Variable.EMPTY_VARIABLES;
        var size = ctx.expression().size();
        Variable[] args = new Variable[size];
        for (int i = 0; i < args.length; i++) {
            args[i] = handleExpression(ctx.expression(i));
        }

        return args;
    }

    @Override
    public Variable handleArrayAccess(LumParser.ArrayAccessContext ctx) {
        var array = handleExpression(ctx.expression());
        Variable[] args = handleArgumentList(ctx.argumentList());

        return array.arrayAccess(args);
    }

    @Override
    public Variable handleMemberAccess(LumParser.MemberAccessContext ctx) {
        Variable value = handleExpression(ctx.expression());
        if (ctx.IDENTIFIER() != null) {
            return value.field(ctx.IDENTIFIER().getText());
        }
        return handleFunctionCall(value, ctx.functionCall());
    }

    private Variable handleFunctionCall(Variable caller, LumParser.FunctionCallContext ctx) {
        if (ctx.NEW() == null) {
            String name = ctx.IDENTIFIER().getText();
            if (ctx.argumentList() == null || ctx.argumentList().expression() == null || ctx.argumentList().isEmpty())
                return caller.invoke(name, Variable.EMPTY_VARIABLES);
            var args = handleArgumentList(ctx.argumentList());
            return caller.invoke(name, args);
        }

        var args = handleArgumentList(ctx.argumentList());
        return cm.new_(caller.getType(), args);
    }

    @Override
    public Variable handlePostUnary(LumParser.PostUnaryContext ctx) {
        var var = handleExpression(ctx.expression());
        return OperatorHandler.handlePostUnaryOperator(ctx.postUnaryOperator(), var);
    }

    @Override
    public Variable handlePreUnary(LumParser.PreUnaryContext ctx) {
        var var = handleExpression(ctx.expression());
        return OperatorHandler.handlePreUnaryOperator(ctx.preUnaryOperator(), var);
    }

    @Override
    public Variable handleBinary(LumParser.BinaryContext ctx) {
        var left = handleExpression(ctx.expression(0));
        var right = handleExpression(ctx.expression(1));
        return OperatorHandler.handleBinaryOperator(ctx.binaryOperator(), left, right);
    }
}

class OperatorHandler {
    private static final HashMap<Class<? extends LumParser.BinaryOperatorContext>, BiFunction<Variable, Variable, Variable>> operatorHandlers = new HashMap<>();

    static {
        operatorHandlers.put(LumParser.AddContext.class, Variable::add);
        operatorHandlers.put(LumParser.SubContext.class, Variable::sub);
        operatorHandlers.put(LumParser.MulContext.class, Variable::multiply);
        operatorHandlers.put(LumParser.DivideContext.class, Variable::divide);
        operatorHandlers.put(LumParser.DIVContext.class, Variable::intDiv);
        operatorHandlers.put(LumParser.SHRContext.class, Variable::shr);
        operatorHandlers.put(LumParser.SHLContext.class, Variable::shl);

        operatorHandlers.put(LumParser.ORContext.class, Variable::or);
        operatorHandlers.put(LumParser.CORContext.class, Variable::or);
        operatorHandlers.put(LumParser.ANDContext.class, Variable::and);
        operatorHandlers.put(LumParser.CANDContext.class, Variable::and);
        operatorHandlers.put(LumParser.XORContext.class, Variable::xor);
        operatorHandlers.put(LumParser.MODContext.class, Variable::mod);

        operatorHandlers.put(LumParser.GTContext.class, Variable::gt);
        operatorHandlers.put(LumParser.LTContext.class, Variable::lt);
        operatorHandlers.put(LumParser.GEContext.class, Variable::ge);
        operatorHandlers.put(LumParser.LEContext.class, Variable::le);
        operatorHandlers.put(LumParser.EQContext.class, Variable::eq);
        operatorHandlers.put(LumParser.NEQContext.class, Variable::neq);
        operatorHandlers.put(LumParser.IsInstanceContext.class, Variable::isInstance);
        operatorHandlers.put(LumParser.InContext.class, Variable::in);
    }

    public static Variable handlePreUnaryOperator(LumParser.PreUnaryOperatorContext ctx, Variable var) {
        switch (ctx) {
            case LumParser.AnyUnaryContext any -> {
                switch (any.postUnaryOperator()) {
                    case LumParser.IncrementContext _ -> {
                        return var.increment(null);
                    }
                    case LumParser.DecrementContext _ -> {
                        return var.decrement(null);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + any.postUnaryOperator());
                }
            }
            case LumParser.NotContext _ -> {
                return var.not();
            }
            default -> throw new IllegalStateException("Unexpected value: " + ctx);
        }
    }

    public static Variable handlePostUnaryOperator(LumParser.PostUnaryOperatorContext ctx, Variable var) {
        switch (ctx) {
            case LumParser.IncrementContext _ -> {
                return var.increment(null);
            }
            case LumParser.DecrementContext _ -> {
                return var.decrement(null);
            }
            default -> throw new IllegalStateException("Unexpected value: " + ctx);
        }
    }

    public static Variable handleOperator(Class<? extends LumParser.BinaryOperatorContext> operatorType, Variable left, Variable right) {
        BiFunction<Variable, Variable, Variable> handler = operatorHandlers.get(operatorType);
        if (handler == null) {
            throw new IllegalArgumentException("Unsupported binary operator: " + operatorType.getSimpleName());
        }
        return handler.apply(left, right);
    }

    public static Variable handleBinaryOperator(LumParser.BinaryOperatorContext ctx, Variable left, Variable right) {
        return switch (ctx) {
            case LumParser.MulContext _ -> left.multiply(right);
            case LumParser.DivideContext _ -> left.divide(right);
            case LumParser.DIVContext _ -> left.intDiv(right);
            case LumParser.MODContext _ -> left.mod(right);

            case LumParser.AddContext _ -> left.add(right);
            case LumParser.SubContext _ -> left.sub(right);

            case LumParser.SHRContext _ -> left.shr(right);
            case LumParser.SHLContext _ -> left.shl(right);

            case LumParser.ANDContext _, LumParser.CANDContext _ -> left.and(right);
            case LumParser.ORContext _, LumParser.CORContext _ -> left.or(right);
            case LumParser.XORContext _ -> left.xor(right);

            case LumParser.GTContext _ -> left.gt(right);
            case LumParser.LTContext _ -> left.lt(right);
            case LumParser.GEContext _ -> left.ge(right);
            case LumParser.LEContext _ -> left.le(right);
            case LumParser.EQContext _ -> left.eq(right);
            case LumParser.NEQContext _ -> left.neq(right);
            case LumParser.IsInstanceContext _ -> left.isInstance(right);
            case LumParser.InContext _ -> left.in(right);

            default -> throw new IllegalStateException("Unexpected value: " + ctx);
        };
    }
}