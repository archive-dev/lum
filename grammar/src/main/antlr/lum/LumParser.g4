parser grammar LumParser;
options {
    tokenVocab = LumLexer;
}

file
    : (declaration ENDLINE?)*
    ;

declaration
    : annotationUse*
    ( typeDeclaration
    | member )
    ;

typeDeclaration
    : access? modifier? annotation
    | access? modifier? enum
    | access? modifier? class
    | access? modifier? interface
    ;

nonGenericTypeDeclaration
    : IDENTIFIER ('{' declaration* '}')?
    ;

genericTypeDeclaration
    : IDENTIFIER generic? ('{' declaration* '}')?
    ;

annotation
    : ANNOTATION nonGenericTypeDeclaration
    ;

enum
    : ENUM nonGenericTypeDeclaration
    ;

class
    : CLASS genericTypeDeclaration
    ;

interface
    : INTERFACE genericTypeDeclaration
    ;

member
    : access? modifier? func                # FuncMember
    | access? modifier? operator            # OperatorMember
    | access? modifier? constructor         # ConstructorMember
    | access? modifier? variableDeclaration # VariableDeclarationMember
    ;

func
    : FUNC IDENTIFIER generic? '(' parameterList? ')' (':' type)? codeBlock?
    ;

operator
    : OPERATOR operator generic? '(' parameterList? ')' (':' type)? codeBlock?
    ;

constructor
    : INIT generic? '(' parameterList? ')' codeBlock?
    ;

variableDeclaration
    : access? modifier?
      variable (',' variable)
    ;

variable
    : IDENTIFIER (':' type)? (('=' | ':=') expression)?
    ;

literal
    : 'true'     # True
    | 'false'    # False
    | NUMBER     # Number
    | STRING     # String
    | NULL       # Null
    ;

call
    : '(' argumentList? ')'
    ;

argumentList
    : expression (',' expression)*
    ;

operatorKeywords
    : AT            # AT
    | MUL           # MUL
    | DIV           # DIVIDE
    | IDIV          # DIV
    | MOD           # MOD
    | ADD           # ADD
    | SUB           # SUB
    | SHR           # SHR
    | SHL           # SHL
    | GT            # GT
    | LT            # LT
    | GE            # GE
    | LE            # LE
    | EQUAL_EQUAL   # EQ
    | NOT_EQUAL     # NEQ
    | IS            # ISINSTANCE
    | IN            # IN
    | AND           # AND
    | OR            # OR
    | XOR           # XOR
    | NOT           # NOT
    | INC           # INC
    | DEC           # DEC
    | ADD           # PLUS
    | SUB           # MINUS
    ;

codeBlock
    : ('{' ENDLINE* ((expression | statement) ENDLINE)* '}')
    | ('=>' (expression | statement))
    ;

statement
    : ifStatement # IfStmt
    | switchStatement # SwitchStmt
    | forILoop # ForIStmt
    | forEachLoop # ForEachStmt
    | whileLoop # WhileStmt
    | doWhileLoop # DoWhileStmt
    ;

forILoop
    : 'for' variable ',' expression ',' expression codeBlock
    ;

forEachLoop
    : 'for' variable 'in' expression codeBlock
    ;

whileLoop
    : 'while' expression 'do'? codeBlock
    ;

doWhileLoop
    : 'do' codeBlock 'while' expression
    ;

switchStatement
    : 'switch' expression
    '{'
        switchCase+
        defaultCase
    '}'
    ;

switchCase
    : 'case' expression codeBlock
    ;

defaultCase
    : 'case' 'default' codeBlock
    ;

ifStatement
    : if
      elif*
      else?
    ;

if
    : 'if' expression codeBlock
    ;

elif
    : 'elif' expression codeBlock
    ;

else
    : 'else' codeBlock
    ;

expression
    : literal                    # LiteralExpr
    | primaryExpression          # PrimaryExpr
    | postfixExpression          # PostfixExpr
    | unaryExpression            # UnaryExpr
    | multiplicativeExpression   # MultiplicativeExpr
    | additiveExpression         # AdditiveExpr
    | shiftExpression            # ShiftExpr
    | relationalExpression       # RelationalExpr
    | equalityExpression         # EqualityExpr
    | bitwiseAndExpression       # BitwiseAndExpr
    | bitwiseXorExpression       # BitwiseXorExpr
    | bitwiseOrExpression        # BitwiseOrExpr
    | logicalAndExpression       # LogicalAndExpr
    | logicalOrExpression        # LogicalOrExpr
    | assignmentExpression       # AssignmentExpr
    ;

assignmentExpression
    : logicalOrExpression ( (EQUAL | WALRUS) assignmentExpression )? // WALRUS is :=
    ;

logicalOrExpression // or, ||
    : logicalAndExpression (OR logicalAndExpression)*
    ;

logicalAndExpression // and, &&
    : bitwiseOrExpression (AND bitwiseOrExpression)*
    ;

bitwiseOrExpression // |
    : bitwiseXorExpression (BITOR bitwiseXorExpression)*
    ;

bitwiseXorExpression // ^, xor
    : bitwiseAndExpression (XOR bitwiseAndExpression)*
    ;

bitwiseAndExpression // &
    : equalityExpression (BITAND equalityExpression)*
    ;

equalityExpression // ==, !=
    : relationalExpression ( op=(EQUAL_EQUAL | NOT_EQUAL) relationalExpression )*
    ;

relationalExpression // <, >, <=, >=, is, in
    : shiftExpression ( op=(GT | LT | GE | LE | IS | IN) shiftExpression )*
    ;

shiftExpression // <<, >>
    : additiveExpression ( op=(SHL | SHR) additiveExpression )*
    ;

additiveExpression // +, -
    : multiplicativeExpression ( op=(ADD | SUB) multiplicativeExpression )*
    ;

multiplicativeExpression // *, /, //, %
    : unaryExpression ( op=(MUL | DIV | IDIV | MOD) unaryExpression )*
    ;

unaryExpression
    : op=(ADD | SUB | NOT | TILDA) unaryExpression # OpExpr  // Prefix: +expr, -expr, !expr, ~expr
    | INC unaryExpression                          # IncExpr // Prefix: ++expr
    | DEC unaryExpression                          # DecExpr // Prefix: --expr
    | postfixExpression                            # Postfix
    ;

postfixExpression
    : primaryExpression                                      # Primary
    | postfixExpression DOT IDENTIFIER                       # MemberAccess      // Member access: .member
    | postfixExpression call                                 # CallExpression    // Member access: .member
    | postfixExpression DOT generic? IDENTIFIER              # GenericFuncAccess // Qualified generic method access: .<T>method - if syntax supported
    | postfixExpression DOT generic? NEW                     # GenericConstructorAccess
    | postfixExpression LBRACK expression RBRACK             # ArrayAccess       // Array access: [expr]
    | postfixExpression INC                                  # Increment         // Postfix increment: expr++
    | postfixExpression DEC                                  # Decrement         // Postfix decrement: expr--
    | postfixExpression AS type                              # CastExpression    // Type cast/conversion: expr as Type
    ;

primaryExpression
    : literal # LiteralExpr1
    | IDENTIFIER # IdentifierExpr
    | LPAREN expression RPAREN # ParenExpr
    | SUPER # SuperAccessExpr
    | arrayInitializer # ArrayExpr
    | dictInitializer # DictExpr
    | (('(' lambdaParameterList ')') | lambdaParameter) codeBlock # LambdaExpr
    // Add lambda expressions if ARROW_RIGHT is used, e.g. (params) => expr
    // Add property access if GET/SET are used
    // Add ternary if QUESTION is used, e.g. cond ? trueExpr : falseExpr
    ;

dictInitializer
    : '[' (kvPair (COMMA (kvPair))* )? COMMA? ']' // e.g. [a = 1, b = 3]
    ;

kvPair
    : expression '=' expression
    ;

arrayInitializer
    : '[' (expression (COMMA expression)* )? COMMA? ']' // e.g. [1, 2, 3,]
    ;

lambdaParameterList
    : lambdaParameter (',' lambdaParameter)*
    ;

lambdaParameter
    : IDENTIFIER (':' type)?
    ;

parameterList
    : parameter (',' parameter)*
    ;

parameter
    : IDENTIFIER ':' type
    ;

generic
    : '[' (
    type (',' type)*
    )? ']';

type
    : IDENTIFIER generic? # PlainType
    | type ('|' type)+    # UnionType
    | type ('&' type)+    # IntersectionType
    | type ('[' ']')+     # ArrayType
    ;

annotationUse
    : AT IDENTIFIER ('(' (kvPair | expression)? ')')?
    ;

access: (PUBLIC | PROTECTED | PRIVATE);
modifier: ABSTRACT | (STATIC FINAL?);