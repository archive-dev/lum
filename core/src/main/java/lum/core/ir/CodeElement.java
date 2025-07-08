package lum.core.ir;

import lum.core.impl.ir.CodeElementImpl;
import lum.core.model.FieldModel;
import lum.core.model.Member;
import lum.core.model.MethodModel;
import lum.core.model.TypeModel;
import lum.core.util.Utils;
import lum.lang.struct.Either;
import lum.lang.struct.Pair;
import org.jetbrains.annotations.NotNull;

import java.lang.constant.ConstantDesc;
import java.lang.constant.ConstantDescs;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface CodeElement {
    Optional<CodeBlock> parent();

    interface TypedCodeElement extends CodeElement {
        TypeModel type();
    }

    interface CodeBlock extends CodeElement {
        Optional<String> name();
        MethodModel ownerMethod();
        List<CodeElement> elements();
        
        static CodeBlock of(CodeBlock parent, MethodModel ownerMethod, List<CodeElement> elements) {
            return new CodeElementImpl.CodeBlockImpl(Optional.empty(), Optional.ofNullable(parent), ownerMethod, elements);
        }
        
        static CodeBlock of(MethodModel ownerMethod, List<CodeElement> elements) {
            return new CodeElementImpl.CodeBlockImpl(Optional.empty(), Optional.empty(), ownerMethod, elements);
        }

        static CodeBlock of(CodeBlock parent, String name, MethodModel ownerMethod, List<CodeElement> elements) {
            return new CodeElementImpl.CodeBlockImpl(Optional.ofNullable(name), Optional.ofNullable(parent), ownerMethod, elements);
        }

        static CodeBlock of(String name, MethodModel ownerMethod, List<CodeElement> elements) {
            return new CodeElementImpl.CodeBlockImpl(Optional.ofNullable(name), Optional.empty(), ownerMethod, elements);
        }
    }

    interface ExpressionElement extends TypedCodeElement {}
    interface ArrayExpression extends ExpressionElement {
        Optional<ExpressionElement[]> values();
        
        static ArrayExpression of(CodeBlock parent, TypeModel type, ExpressionElement[] values) {
            return new CodeElementImpl.ArrayExpressionImpl(Optional.ofNullable(parent), type, Optional.ofNullable(values));
        }
        
        static ArrayExpression of(CodeBlock parent, TypeModel type) {
            return new CodeElementImpl.ArrayExpressionImpl(Optional.ofNullable(parent), type, Optional.empty());
        }
        
        static ArrayExpression of(TypeModel type, ExpressionElement[] values) {
            return new CodeElementImpl.ArrayExpressionImpl(Optional.empty(), type, Optional.ofNullable(values));
        }
        
        static ArrayExpression of(TypeModel type) {
            return new CodeElementImpl.ArrayExpressionImpl(Optional.empty(), type, Optional.empty());
        }
    }

    interface DictExpression extends ExpressionElement {
        ExpressionElement[] keys();
        ExpressionElement[] values();
        
        static DictExpression of(CodeBlock parent, TypeModel type, ExpressionElement[] keys, ExpressionElement[] values) {
            return new CodeElementImpl.DictExpressionImpl(Optional.ofNullable(parent), type, keys, values);
        }
        
        static DictExpression of(TypeModel type, ExpressionElement[] keys, ExpressionElement[] values) {
            return new CodeElementImpl.DictExpressionImpl(Optional.empty(), type, keys, values);
        }
    }

    interface LambdaExpression extends ExpressionElement {
        Pair<String, TypeModel>[] parameters();
        CodeBlock block();
        
        static LambdaExpression of(CodeBlock parent, TypeModel type, Pair<String, TypeModel>[] parameters, CodeBlock block) {
            return new CodeElementImpl.LambdaExpressionImpl(Optional.ofNullable(parent), type, parameters, block);
        }
        
        static LambdaExpression of(TypeModel type, Pair<String, TypeModel>[] parameters, CodeBlock block) {
            return new CodeElementImpl.LambdaExpressionImpl(Optional.empty(), type, parameters, block);
        }
    }

    interface IdentifierExpression extends ExpressionElement {
        Either<Either<String, TypeModel>, Member> identifier();
        
        static IdentifierExpression of(TypeModel type, String identifier) {
            return new CodeElementImpl.IdentifierExpressionImpl(Optional.empty(), type, Either.left(Either.left(identifier)));
        }
        
        static IdentifierExpression of(TypeModel type, FieldModel identifier) {
            return new CodeElementImpl.IdentifierExpressionImpl(Optional.empty(), type, Either.right(identifier));
        }

        static IdentifierExpression of(TypeModel type, MethodModel identifier) {
            return new CodeElementImpl.IdentifierExpressionImpl(Optional.empty(), type, Either.right(identifier));
        }

        static IdentifierExpression of(TypeModel type, TypeModel identifier) {
            return new CodeElementImpl.IdentifierExpressionImpl(Optional.empty(), type, Either.left(Either.right(identifier)));
        }
        
        static IdentifierExpression of(CodeBlock parent, TypeModel type, String identifier) {
            return new CodeElementImpl.IdentifierExpressionImpl(Optional.ofNullable(parent), type, Either.left(Either.left(identifier)));
        }
        
        static IdentifierExpression of(CodeBlock parent, TypeModel type, FieldModel identifier) {
            return new CodeElementImpl.IdentifierExpressionImpl(Optional.ofNullable(parent), type, Either.right(identifier));
        }

        static IdentifierExpression of(CodeBlock parent, TypeModel type, MethodModel identifier) {
            return new CodeElementImpl.IdentifierExpressionImpl(Optional.ofNullable(parent), type, Either.right(identifier));
        }

        static IdentifierExpression of(CodeBlock parent, TypeModel type, TypeModel identifier) {
            return new CodeElementImpl.IdentifierExpressionImpl(Optional.ofNullable(parent), type, Either.left(Either.right(identifier)));
        }
    }

    interface SuperAccessExpression extends IdentifierExpression {
        static SuperAccessExpression of(CodeBlock parent, TypeModel type) {
            return new CodeElementImpl.SuperAccessExpressionImpl(Optional.ofNullable(parent), type, Either.left(Either.left("super")));
        }
        
        static SuperAccessExpression of(TypeModel type) {
            return new CodeElementImpl.SuperAccessExpressionImpl(Optional.empty(), type, Either.left(Either.left("super")));
        }
    }

    interface LiteralExpression<T extends ConstantDesc> extends ExpressionElement {
        T value();
    }
    interface TrueExpr extends LiteralExpression<Integer> {
        @Override
        default Integer value() {
            return 1;
        }
        
        static TrueExpr of(CodeBlock parent) {
            return new CodeElementImpl.TrueExprImpl(Optional.ofNullable(parent), TypeModel.of(boolean.class).orElseThrow());
        }

        static TrueExpr of() {
            return new CodeElementImpl.TrueExprImpl(Optional.empty(), TypeModel.of(boolean.class).orElseThrow());
        }
    }
    interface FalseExpr extends LiteralExpression<Integer> {
        @Override
        default Integer value() {
            return 0;
        }
        
        static FalseExpr of(CodeBlock parent) {
            return new CodeElementImpl.FalseExprImpl(Optional.ofNullable(parent), TypeModel.of(boolean.class).orElseThrow());
        }
        
        static FalseExpr of() {
            return new CodeElementImpl.FalseExprImpl(Optional.empty(), TypeModel.of(boolean.class).orElseThrow());
        }
    }
    interface NullExpr extends LiteralExpression<ConstantDesc> {
        @Override
        default ConstantDesc value() {
            return ConstantDescs.NULL;
        }
        
        static NullExpr of(CodeBlock parent, TypeModel type) {
            return new CodeElementImpl.NullExprImpl(Optional.ofNullable(parent), type);
        }
        static NullExpr of(CodeBlock parent) {
            return new CodeElementImpl.NullExprImpl(Optional.ofNullable(parent), TypeModel.of(Object.class).orElseThrow());
        }

        static NullExpr of() {
            return new CodeElementImpl.NullExprImpl(Optional.empty(), TypeModel.of(Object.class).orElseThrow());
        }
        static NullExpr of(TypeModel type) {
            return new CodeElementImpl.NullExprImpl(Optional.empty(), type);
        }
    }
    interface StringExpr extends LiteralExpression<String> {
        static StringExpr of(CodeBlock parent, String value) {
            return new CodeElementImpl.StringExprImpl(Optional.ofNullable(parent), TypeModel.of(String.class).orElseThrow(), value);
        }
        
        static StringExpr of(String value) {
            return new CodeElementImpl.StringExprImpl(Optional.empty(), TypeModel.of(String.class).orElseThrow(), value);
        }
    }
    interface NumberExpr<T extends Number & ConstantDesc> extends LiteralExpression<T> {
        static <T extends Number & ConstantDesc> NumberExpr<T> of(CodeBlock parent, TypeModel type, T value) {
            return new CodeElementImpl.NumberExprImpl<>(Optional.ofNullable(parent), type, value);
        }
        
        static <T extends Number & ConstantDesc> NumberExpr<T> of(TypeModel type, T value) {
            return new CodeElementImpl.NumberExprImpl<>(Optional.empty(), type, value);
        }
    }

    interface AssignmentExpression extends ExpressionElement {
        Either<Either<VariableDeclarationStatement, AssignmentExpression>, ArrayAccessExpression> variable();
        ExpressionElement value();

        static AssignmentExpression of(CodeBlock parent,
                VariableDeclarationStatement variable,
                ExpressionElement value) {
            return new CodeElementImpl.AssignmentExpressionImpl(Optional.ofNullable(parent), value.type(), Either.left(Either.left(variable)), value);
        }

        static AssignmentExpression of(CodeBlock parent, TypeModel type,
                                       VariableDeclarationStatement variable,
                                       ExpressionElement value) {
            return new CodeElementImpl.AssignmentExpressionImpl(Optional.ofNullable(parent), type, Either.left(Either.left(variable)), value);
        }

        static AssignmentExpression of(TypeModel type,
                                      VariableDeclarationStatement variable,
                                      ExpressionElement value) {
            return new CodeElementImpl.AssignmentExpressionImpl(Optional.empty(), type, Either.left(Either.left(variable)), value);
        }

        static AssignmentExpression of(VariableDeclarationStatement variable,
                                       ExpressionElement value) {
            return new CodeElementImpl.AssignmentExpressionImpl(Optional.empty(), value.type(), Either.left(Either.left(variable)), value);
        }

        static AssignmentExpression of(CodeBlock parent,
                AssignmentExpression variable,
                ExpressionElement value) {
            return new CodeElementImpl.AssignmentExpressionImpl(Optional.ofNullable(parent), value.type(), Either.left(Either.right(variable)), value);
        }

        static AssignmentExpression of(CodeBlock parent, TypeModel type,
                                       AssignmentExpression variable,
                                       ExpressionElement value) {
            return new CodeElementImpl.AssignmentExpressionImpl(Optional.ofNullable(parent), type, Either.left(Either.right(variable)), value);
        }

        static AssignmentExpression of(TypeModel type,
                                      AssignmentExpression variable,
                                      ExpressionElement value) {
            return new CodeElementImpl.AssignmentExpressionImpl(Optional.empty(), type, Either.left(Either.right(variable)), value);
        }

        static AssignmentExpression of(AssignmentExpression variable,
                                       ExpressionElement value) {
            return new CodeElementImpl.AssignmentExpressionImpl(Optional.empty(), value.type(), Either.left(Either.right(variable)), value);
        }

        static AssignmentExpression of(CodeBlock parent,
                ArrayAccessExpression variable,
                ExpressionElement value) {
            return new CodeElementImpl.AssignmentExpressionImpl(Optional.ofNullable(parent), value.type(), Either.right(variable), value);
        }

        static AssignmentExpression of(CodeBlock parent, TypeModel type,
                                       ArrayAccessExpression variable,
                                       ExpressionElement value) {
            return new CodeElementImpl.AssignmentExpressionImpl(Optional.ofNullable(parent), type, Either.right(variable), value);
        }

        static AssignmentExpression of(TypeModel type,
                                      ArrayAccessExpression variable,
                                      ExpressionElement value) {
            return new CodeElementImpl.AssignmentExpressionImpl(Optional.empty(), type, Either.right(variable), value);
        }

        static AssignmentExpression of(ArrayAccessExpression variable,
                                       ExpressionElement value) {
            return new CodeElementImpl.AssignmentExpressionImpl(Optional.empty(), value.type(), Either.right(variable), value);
        }
    }
    interface BinaryExpression extends ExpressionElement {
        ExpressionElement left();
        Operator.BinaryOperator operator();
        ExpressionElement right();

        static BinaryExpression of(CodeBlock parent, TypeModel type, ExpressionElement left,
                Operator.BinaryOperator operator, ExpressionElement right) {
            return new CodeElementImpl.BinaryExpressionImpl(Optional.ofNullable(parent), type, left, operator, right);
        }
        
        static BinaryExpression of(TypeModel type, ExpressionElement left,
                Operator.BinaryOperator operator, ExpressionElement right) {
            return new CodeElementImpl.BinaryExpressionImpl(Optional.empty(), type, left, operator, right);
        }

        static BinaryExpression of(CodeBlock parent, ExpressionElement left,
                Operator.BinaryOperator operator, ExpressionElement right) {
            return new CodeElementImpl.BinaryExpressionImpl(Optional.ofNullable(parent), left.type(), left, operator, right);
        }

        static BinaryExpression of(ExpressionElement left,
                Operator.BinaryOperator operator, ExpressionElement right) {
            return new CodeElementImpl.BinaryExpressionImpl(Optional.empty(), left.type(), left, operator, right);
        }
    }
    interface UnaryPrefixExpression extends ExpressionElement {
        Operator.UnaryOperator operator();
        ExpressionElement expression();
        
        static UnaryPrefixExpression of(CodeBlock parent, TypeModel type,
                Operator.UnaryOperator operator, ExpressionElement expression) {
            return new CodeElementImpl.UnaryPrefixExpressionImpl(Optional.ofNullable(parent), type, operator, expression);
        }

        static UnaryPrefixExpression of(CodeBlock parent,
                Operator.UnaryOperator operator, ExpressionElement expression) {
            return new CodeElementImpl.UnaryPrefixExpressionImpl(Optional.ofNullable(parent), expression.type(), operator, expression);
        }
        
        static UnaryPrefixExpression of(TypeModel type,
                Operator.UnaryOperator operator, ExpressionElement expression) {
            return new CodeElementImpl.UnaryPrefixExpressionImpl(Optional.empty(), type, operator, expression);
        }

        static UnaryPrefixExpression of(Operator.UnaryOperator operator, ExpressionElement expression) {
            return new CodeElementImpl.UnaryPrefixExpressionImpl(Optional.empty(), expression.type(), operator, expression);
        }
    }
    interface UnaryPostfixExpression extends ExpressionElement {
        ExpressionElement expression();
        Operator.UnaryOperator operator();
        
        static UnaryPostfixExpression of(CodeBlock parent, TypeModel type,
                ExpressionElement expression, Operator.UnaryOperator operator) {
            return new CodeElementImpl.UnaryPostfixExpressionImpl(Optional.ofNullable(parent), type, expression, operator);
        }
        
        static UnaryPostfixExpression of(TypeModel type,
                ExpressionElement expression, Operator.UnaryOperator operator) {
            return new CodeElementImpl.UnaryPostfixExpressionImpl(Optional.empty(), type, expression, operator);
        }

        static UnaryPostfixExpression of(CodeBlock parent,
                ExpressionElement expression, Operator.UnaryOperator operator) {
            return new CodeElementImpl.UnaryPostfixExpressionImpl(Optional.ofNullable(parent), expression.type(), expression, operator);
        }

        static UnaryPostfixExpression of(ExpressionElement expression, Operator.UnaryOperator operator) {
            return new CodeElementImpl.UnaryPostfixExpressionImpl(Optional.empty(), expression.type(), expression, operator);
        }
    }
    interface CastExpression extends ExpressionElement {
        TypeModel castType();
        ExpressionElement expression();
        
        static CastExpression of(CodeBlock parent, TypeModel castType, ExpressionElement expression) {
            return new CodeElementImpl.CastExpressionImpl(Optional.ofNullable(parent), castType, castType, expression);
        }
        
        static CastExpression of(TypeModel castType, ExpressionElement expression) {
            return new CodeElementImpl.CastExpressionImpl(Optional.empty(), castType, castType, expression);
        }
    }

    interface MemberAccessExpression extends ExpressionElement {
        Optional<ExpressionElement> expression();
        Member member();
        
        static MemberAccessExpression of(CodeBlock parent, TypeModel type,
                ExpressionElement expression, Member member) {
            return new CodeElementImpl.MemberAccessExpressionImpl(Optional.ofNullable(parent), type, Optional.ofNullable(expression), member);
        }
        
        static MemberAccessExpression of(CodeBlock parent, TypeModel type, Member member) {
            return new CodeElementImpl.MemberAccessExpressionImpl(Optional.ofNullable(parent), type, Optional.empty(), member);
        }
        
        static MemberAccessExpression of(TypeModel type, ExpressionElement expression, Member member) {
            return new CodeElementImpl.MemberAccessExpressionImpl(Optional.empty(), type, Optional.of(expression), member);
        }
        
        static MemberAccessExpression of(TypeModel type, Member member) {
            return new CodeElementImpl.MemberAccessExpressionImpl(Optional.empty(), type, Optional.empty(), member);
        }
    }

    interface CallExpression extends ExpressionElement {
        Optional<ExpressionElement> caller();
        @NotNull MethodModel method();
        Optional<ExpressionElement[]> arguments();
        
        static CallExpression of(CodeBlock parent, TypeModel type, ExpressionElement caller,
                @NotNull MethodModel method, ExpressionElement[] arguments) {
            return new CodeElementImpl.CallExpressionImpl(Optional.ofNullable(parent), type, Optional.ofNullable(caller), method, Optional.ofNullable(arguments));
        }
        
        static CallExpression of(CodeBlock parent, TypeModel type, @NotNull MethodModel method, ExpressionElement[] arguments) {
            return new CodeElementImpl.CallExpressionImpl(Optional.ofNullable(parent), type, Optional.empty(), method, Optional.ofNullable(arguments));
        }
        
        static CallExpression of(CodeBlock parent, TypeModel type, ExpressionElement caller, @NotNull MethodModel method) {
            return new CodeElementImpl.CallExpressionImpl(Optional.ofNullable(parent), type, Optional.of(caller), method, Optional.empty());
        }
        
        static CallExpression of(CodeBlock parent, TypeModel type, @NotNull MethodModel method) {
            return new CodeElementImpl.CallExpressionImpl(Optional.ofNullable(parent), type, Optional.empty(), method, Optional.empty());
        }
        
        static CallExpression of(TypeModel type, ExpressionElement caller, @NotNull MethodModel method, ExpressionElement[] arguments) {
            return new CodeElementImpl.CallExpressionImpl(Optional.empty(), type, Optional.of(caller), method, Optional.ofNullable(arguments));
        }
        
        static CallExpression of(TypeModel type, @NotNull MethodModel method, ExpressionElement[] arguments) {
            return new CodeElementImpl.CallExpressionImpl(Optional.empty(), type, Optional.empty(), method, Optional.ofNullable(arguments));
        }
        
        static CallExpression of(TypeModel type, ExpressionElement caller, @NotNull MethodModel method) {
            return new CodeElementImpl.CallExpressionImpl(Optional.empty(), type, Optional.of(caller), method, Optional.empty());
        }
        
        static CallExpression of(TypeModel type, @NotNull MethodModel method) {
            return new CodeElementImpl.CallExpressionImpl(Optional.empty(), type, Optional.empty(), method, Optional.empty());
        }
        
        static CallExpression of(CodeBlock parent, ExpressionElement caller,
                @NotNull MethodModel method, ExpressionElement[] arguments) {
            return new CodeElementImpl.CallExpressionImpl(Optional.ofNullable(parent), method.returnType(), Optional.ofNullable(caller), method, Optional.ofNullable(arguments));
        }
        
        static CallExpression of(CodeBlock parent, @NotNull MethodModel method, ExpressionElement[] arguments) {
            return new CodeElementImpl.CallExpressionImpl(Optional.ofNullable(parent), method.returnType(), Optional.empty(), method, Optional.ofNullable(arguments));
        }
        
        static CallExpression of(CodeBlock parent, ExpressionElement caller, @NotNull MethodModel method) {
            return new CodeElementImpl.CallExpressionImpl(Optional.ofNullable(parent), method.returnType(), Optional.of(caller), method, Optional.empty());
        }
        
        static CallExpression of(CodeBlock parent, @NotNull MethodModel method) {
            return new CodeElementImpl.CallExpressionImpl(Optional.ofNullable(parent), method.returnType(), Optional.empty(), method, Optional.empty());
        }
        
        static CallExpression of(ExpressionElement caller, @NotNull MethodModel method, ExpressionElement[] arguments) {
            return new CodeElementImpl.CallExpressionImpl(Optional.empty(), method.returnType(), Optional.of(caller), method, Optional.ofNullable(arguments));
        }
        
        static CallExpression of(@NotNull MethodModel method, ExpressionElement[] arguments) {
            return new CodeElementImpl.CallExpressionImpl(Optional.empty(), method.returnType(), Optional.empty(), method, Optional.ofNullable(arguments));
        }
        
        static CallExpression of(ExpressionElement caller, @NotNull MethodModel method) {
            return new CodeElementImpl.CallExpressionImpl(Optional.empty(), method.returnType(), Optional.of(caller), method, Optional.empty());
        }
        
        static CallExpression of(@NotNull MethodModel method) {
            return new CodeElementImpl.CallExpressionImpl(Optional.empty(), method.returnType(), Optional.empty(), method, Optional.empty());
        }
    }

    interface NewExpression extends CallExpression {
        @Override
        default Optional<ExpressionElement> caller() {
            return Optional.empty();
        }
        
        static NewExpression of(CodeBlock parent, TypeModel type, ExpressionElement[] arguments) {
            return new CodeElementImpl.NewExpressionImpl(
                    Optional.ofNullable(parent),
                    type,
                    type.model().getMethod(
                            "<init>",
                            Arrays.stream(arguments != null ? arguments : Utils.EMPTY_EXPRESSION_ELEMENTS)
                                    .map(ExpressionElement::type)
                                    .toArray(TypeModel[]::new)
                    ).orElseThrow(),
                    Optional.ofNullable(arguments)
            );
        }
        
        static NewExpression of(CodeBlock parent, TypeModel type) {
            return new CodeElementImpl.NewExpressionImpl(Optional.ofNullable(parent),
                    type,
                    type.model().getMethod("<init>").orElseThrow(),
                    Optional.empty()
            );
        }
        
        static NewExpression of(TypeModel type, ExpressionElement[] arguments) {
            return new CodeElementImpl.NewExpressionImpl(
                    Optional.empty(),
                    type,
                    type.model().getMethod(
                            "<init>",
                            Arrays.stream(arguments != null ? arguments : Utils.EMPTY_EXPRESSION_ELEMENTS)
                                    .map(ExpressionElement::type)
                                    .toArray(TypeModel[]::new)
                    ).orElseThrow(),
                    Optional.ofNullable(arguments)
            );
        }
        
        static NewExpression of(TypeModel type) {
            return new CodeElementImpl.NewExpressionImpl(
                    Optional.empty(),
                    type,
                    type.model().getMethod("<init>").orElseThrow(),
                    Optional.empty()
            );
        }
    }

    interface ArrayAccessExpression extends ExpressionElement {
        ExpressionElement expression();
        ExpressionElement index();
        
        static ArrayAccessExpression of(CodeBlock parent, TypeModel type, ExpressionElement expression, ExpressionElement index) {
            return new CodeElementImpl.ArrayAccessExpressionImpl(Optional.ofNullable(parent), type, expression, index);
        }
        
        static ArrayAccessExpression of(TypeModel type, ExpressionElement expression, ExpressionElement index) {
            return new CodeElementImpl.ArrayAccessExpressionImpl(Optional.empty(), type, expression, index);
        }

        static ArrayAccessExpression of(CodeBlock parent, ExpressionElement expression, ExpressionElement index) {
            return new CodeElementImpl.ArrayAccessExpressionImpl(Optional.ofNullable(parent), expression.type().asComponent(), expression, index);
        }

        static ArrayAccessExpression of(ExpressionElement expression, ExpressionElement index) {
            return new CodeElementImpl.ArrayAccessExpressionImpl(Optional.empty(), expression.type().asComponent(), expression, index);
        }
    }

    interface StatementElement extends CodeElement {}
    interface VariableDeclarationStatement extends StatementElement, TypedCodeElement {
        String name();
        Optional<ExpressionElement> value();
        
        static VariableDeclarationStatement of(CodeBlock parent, TypeModel type, String name) {
            return new CodeElementImpl.VariableDeclarationStatementImpl(Optional.ofNullable(parent), type, name, Optional.empty());
        }
        
        static VariableDeclarationStatement of(TypeModel type, String name) {
            return new CodeElementImpl.VariableDeclarationStatementImpl(Optional.empty(), type, name, Optional.empty());
        }

        static VariableDeclarationStatement of(CodeBlock parent, TypeModel type, String name, ExpressionElement value) {
            return new CodeElementImpl.VariableDeclarationStatementImpl(Optional.ofNullable(parent), type, name, Optional.ofNullable(value));
        }

        static VariableDeclarationStatement of(TypeModel type, String name, ExpressionElement value) {
            return new CodeElementImpl.VariableDeclarationStatementImpl(Optional.empty(), type, name, Optional.ofNullable(value));
        }
    }
    interface ControlStatement extends StatementElement {}
    interface IfStatement extends ControlStatement {
        ExpressionElement ifCondition();
        CodeBlock ifBlock();
        Optional<Pair<ExpressionElement, CodeBlock>[]> elifConditions();
        Optional<CodeBlock> elseBlock();
        
        static IfStatement of(CodeBlock parent, ExpressionElement ifCondition, CodeBlock ifBlock,
                Pair<ExpressionElement, CodeBlock>[] elifConditions, CodeBlock elseBlock) {
            return new CodeElementImpl.IfStatementImpl(Optional.ofNullable(parent), ifCondition, ifBlock, Optional.ofNullable(elifConditions), Optional.ofNullable(elseBlock));
        }
        
        static IfStatement of(CodeBlock parent, ExpressionElement ifCondition, CodeBlock ifBlock) {
            return new CodeElementImpl.IfStatementImpl(Optional.ofNullable(parent), ifCondition, ifBlock, Optional.empty(), Optional.empty());
        }
        
        static IfStatement of(CodeBlock parent, ExpressionElement ifCondition, CodeBlock ifBlock, CodeBlock elseBlock) {
            return new CodeElementImpl.IfStatementImpl(Optional.ofNullable(parent), ifCondition, ifBlock, Optional.empty(), Optional.of(elseBlock));
        }
        
        static IfStatement of(CodeBlock parent, ExpressionElement ifCondition, CodeBlock ifBlock, 
                Pair<ExpressionElement, CodeBlock>[] elifConditions) {
            return new CodeElementImpl.IfStatementImpl(Optional.ofNullable(parent), ifCondition, ifBlock, Optional.of(elifConditions), Optional.empty());
        }
        
        static IfStatement of(ExpressionElement ifCondition, CodeBlock ifBlock,
                Pair<ExpressionElement, CodeBlock>[] elifConditions, CodeBlock elseBlock) {
            return new CodeElementImpl.IfStatementImpl(Optional.empty(), ifCondition, ifBlock, Optional.ofNullable(elifConditions), Optional.ofNullable(elseBlock));
        }
        
        static IfStatement of(ExpressionElement ifCondition, CodeBlock ifBlock) {
            return new CodeElementImpl.IfStatementImpl(Optional.empty(), ifCondition, ifBlock, Optional.empty(), Optional.empty());
        }
        
        static IfStatement of(ExpressionElement ifCondition, CodeBlock ifBlock, CodeBlock elseBlock) {
            return new CodeElementImpl.IfStatementImpl(Optional.empty(), ifCondition, ifBlock, Optional.empty(), Optional.of(elseBlock));
        }
        
        static IfStatement of(ExpressionElement ifCondition, CodeBlock ifBlock, 
                Pair<ExpressionElement, CodeBlock>[] elifConditions) {
            return new CodeElementImpl.IfStatementImpl(Optional.empty(), ifCondition, ifBlock, Optional.of(elifConditions), Optional.empty());
        }
    }
    interface SwitchStatement extends ControlStatement {
        ExpressionElement value();
        Pair<ExpressionElement, CodeBlock>[] cases();
        CodeBlock defaultCase();
        
        static SwitchStatement of(CodeBlock parent, ExpressionElement value,
                Pair<ExpressionElement, CodeBlock>[] cases, CodeBlock defaultCase) {
            return new CodeElementImpl.SwitchStatementImpl(Optional.ofNullable(parent), value, cases, defaultCase);
        }
        
        static SwitchStatement of(ExpressionElement value,
                Pair<ExpressionElement, CodeBlock>[] cases, CodeBlock defaultCase) {
            return new CodeElementImpl.SwitchStatementImpl(Optional.empty(), value, cases, defaultCase);
        }
    }

    interface ReturnStatement extends ControlStatement {
        Optional<ExpressionElement> returnValue();
        
        static ReturnStatement of(CodeBlock parent, ExpressionElement returnValue) {
            return new CodeElementImpl.ReturnStatementImpl(Optional.ofNullable(parent), Optional.ofNullable(returnValue));
        }
        
        static ReturnStatement of(CodeBlock parent) {
            return new CodeElementImpl.ReturnStatementImpl(Optional.ofNullable(parent), Optional.empty());
        }
        
        static ReturnStatement of(ExpressionElement returnValue) {
            return new CodeElementImpl.ReturnStatementImpl(Optional.empty(), Optional.ofNullable(returnValue));
        }
        
        static ReturnStatement of() {
            return new CodeElementImpl.ReturnStatementImpl(Optional.empty(), Optional.empty());
        }
    }
    interface BreakStatement extends ControlStatement {
        Optional<String> breakLabel();
        
        static BreakStatement of(CodeBlock parent, String breakLabel) {
            return new CodeElementImpl.BreakStatementImpl(Optional.ofNullable(parent), Optional.ofNullable(breakLabel));
        }
        
        static BreakStatement of(CodeBlock parent) {
            return new CodeElementImpl.BreakStatementImpl(Optional.ofNullable(parent), Optional.empty());
        }
        
        static BreakStatement of(String breakLabel) {
            return new CodeElementImpl.BreakStatementImpl(Optional.empty(), Optional.ofNullable(breakLabel));
        }
        
        static BreakStatement of() {
            return new CodeElementImpl.BreakStatementImpl(Optional.empty(), Optional.empty());
        }
    }
    interface ContinueStatement extends ControlStatement {
        Optional<String> continueLabel();
        
        static ContinueStatement of(CodeBlock parent, String continueLabel) {
            return new CodeElementImpl.ContinueStatementImpl(Optional.ofNullable(parent), Optional.ofNullable(continueLabel));
        }
        
        static ContinueStatement of(CodeBlock parent) {
            return new CodeElementImpl.ContinueStatementImpl(Optional.ofNullable(parent), Optional.empty());
        }
        
        static ContinueStatement of(String continueLabel) {
            return new CodeElementImpl.ContinueStatementImpl(Optional.empty(), Optional.ofNullable(continueLabel));
        }
        
        static ContinueStatement of() {
            return new CodeElementImpl.ContinueStatementImpl(Optional.empty(), Optional.empty());
        }
    }

    interface LoopElement extends ControlStatement {}
    interface DoWhileLoopElement extends LoopElement {
        ExpressionElement condition();
        CodeBlock block();
        
        static DoWhileLoopElement of(CodeBlock parent, ExpressionElement condition, CodeBlock block) {
            return new CodeElementImpl.DoWhileLoopElementImpl(Optional.ofNullable(parent), condition, block);
        }
        
        static DoWhileLoopElement of(ExpressionElement condition, CodeBlock block) {
            return new CodeElementImpl.DoWhileLoopElementImpl(Optional.empty(), condition, block);
        }
    }
    interface WhileLoopElement extends LoopElement {
        ExpressionElement condition();
        CodeBlock block();
        
        static WhileLoopElement of(CodeBlock parent, ExpressionElement condition, CodeBlock block) {
            return new CodeElementImpl.WhileLoopElementImpl(Optional.ofNullable(parent), condition, block);
        }
        
        static WhileLoopElement of(ExpressionElement condition, CodeBlock block) {
            return new CodeElementImpl.WhileLoopElementImpl(Optional.empty(), condition, block);
        }
    }

    interface ForLoopElement extends LoopElement {
        Either<VariableDeclarationStatement, AssignmentExpression> variable();
        ExpressionElement condition();
        ExpressionElement incrementor();
        CodeBlock block();
        
        static ForLoopElement of(CodeBlock parent, AssignmentExpression variable, ExpressionElement condition,
                ExpressionElement incrementor, CodeBlock block) {
            return new CodeElementImpl.ForLoopElementImpl(Optional.ofNullable(parent), Either.right(variable), condition, incrementor, block);
        }
        
        static ForLoopElement of(AssignmentExpression variable, ExpressionElement condition,
                ExpressionElement incrementor, CodeBlock block) {
            return new CodeElementImpl.ForLoopElementImpl(Optional.empty(), Either.right(variable), condition, incrementor, block);
        }

        static ForLoopElement of(CodeBlock parent, VariableDeclarationStatement variable, ExpressionElement condition,
                ExpressionElement incrementor, CodeBlock block) {
            return new CodeElementImpl.ForLoopElementImpl(Optional.ofNullable(parent), Either.left(variable), condition, incrementor, block);
        }

        static ForLoopElement of(VariableDeclarationStatement variable, ExpressionElement condition,
                ExpressionElement incrementor, CodeBlock block) {
            return new CodeElementImpl.ForLoopElementImpl(Optional.empty(), Either.left(variable), condition, incrementor, block);
        }
    }

    interface ForEachLoopElement extends LoopElement {
        VariableDeclarationStatement variable();
        ExpressionElement iterable();
        CodeBlock block();
        
        static ForEachLoopElement of(CodeBlock parent, VariableDeclarationStatement variable,
                ExpressionElement iterable, CodeBlock block) {
            return new CodeElementImpl.ForEachLoopElementImpl(Optional.ofNullable(parent), variable, iterable, block);
        }
        
        static ForEachLoopElement of(VariableDeclarationStatement variable,
                ExpressionElement iterable, CodeBlock block) {
            return new CodeElementImpl.ForEachLoopElementImpl(Optional.empty(), variable, iterable, block);
        }
    }
}
