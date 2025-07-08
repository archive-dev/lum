grammar Lum;

file
    : (package ENDLINE+)?
      (imports ENDLINE+)*
      (ENDLINE* declaration)*
    ;

package
    : PACKAGE IDENTIFIER (DOT IDENTIFIER)*
    ;

imports
    : IMPORT STATIC? importSingle # SingleImport
    | IMPORT STATIC? importAs # AsImport
    | IMPORT STATIC? importFrom # FromImport
    | IMPORT STATIC? importMulti # MultiImport
    ;

importSingle
    : IDENTIFIER (DOT IDENTIFIER)*
    ;

importAs
    : importSingle AS IDENTIFIER
    ;

from
    : FROM IDENTIFIER (DOT IDENTIFIER)*
    ;

importFrom
    : importAny (COMMA importAny) from
    ;

importAny
    : importSingle # Single
    | importAs # As
    ;

importMulti
    : (importAny (',' importAny)*)
    ;

declaration
    : (annotationUse ENDLINE)*
    ( typeDeclaration
    | member )
    ENDLINE?
    ;

typeDeclaration
    : access? modifier? annotation # AnnotationDeclaration
    | access? modifier? enum # EnumDeclaration
    | access? modifier? class # ClassDeclaration
    | access? modifier? interface # InterfaceDeclaration
    ;

nonGenericTypeDeclaration
    : IDENTIFIER inheritance? (LBRACE ENDLINE* declaration* RBRACE)?
    ;

genericTypeDeclaration
    : IDENTIFIER generic? inheritance? (LBRACE ENDLINE* declaration* RBRACE)?
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

inheritance
    : LPAREN
    (extends COMMA)?
    implements?
    RPAREN
    ;

extends
    : (EXTENDS EQUAL)? type
    ;

implements
    : (IMPLEMENTS EQUAL)?
    ( LBRACK (type (COMMA type)*) RBRACK
    | (type (COMMA type)*))
    ;

member
    : access? modifier? func                # FuncMember
    | access? modifier? operator            # OperatorMember
    | access? modifier? constructor         # ConstructorMember
    | access? modifier? variableDeclaration # VariableDeclarationMember
    ;

func
    : FUNC IDENTIFIER generic? LPAREN parameterList? RPAREN (COLON type)? codeBlock?
    ;

operator
    : OPERATOR operatorKeyword generic? LPAREN parameterList? RPAREN (COLON type)? codeBlock?
    ;

constructor
    : INIT generic? LPAREN parameterList? RPAREN codeBlock?
    ;

variableDeclaration
    : variable (COMMA ENDLINE* variable)*
    ;

variable
    : IDENTIFIER (COLON type)? ((EQUAL | WALRUS) expression)?
    ( (ENDLINE* getter)
    | (ENDLINE* setter) )*
    ;

setter : access? SET LPAREN parameter RPAREN codeBlock;
getter : access? GET codeBlock;

literal
    : TRUE     # True
    | FALSE    # False
    | NUMBER     # Number
    | STRING     # String
    | NULL       # Null
    ;

call
    : LPAREN argumentList? RPAREN
    ;

argumentList
    : expression (COMMA expression)*
    ;

operatorKeyword
    : AT             # AT
    | MUL            # MUL
    | DIV            # DIVIDE
    | IDIV           # DIV
    | MOD            # MOD
    | ADD            # ADD
    | SUB            # SUB
    | SHR            # SHR
    | SHL            # SHL
    | GT             # GT //
    | LT             # LT //
    | GE             # GE // mb replace with one compareTo method
    | LE             # LE //
    | EQUAL_EQUAL    # EQ //
    | IS             # ISINSTANCE
    | IN             # IN
    | AND            # AND
    | OR             # OR
    | XOR            # XOR
    | NOT            # NOT
    | INC            # INC
    | DEC            # DEC
    | ARRAY_OP       # ARRAY_OP
    | ARRAY_OP EQUAL # ARRAYSET_OP
    ;

codeBlock
    : (LBRACE ENDLINE* ((expression | statement) ENDLINE*)* RBRACE)
    | (ARROW_RIGHT ENDLINE* (expression | statement))
    ;

statement
    : ifStatement # IfStmt
    | switchStatement # SwitchStmt
    | forILoop # ForIStmt
    | forEachLoop # ForEachStmt
    | whileLoop # WhileStmt
    | doWhileLoop # DoWhileStmt
    | BREAK IDENTIFIER # BreakStmt
    | CONTINUE IDENTIFIER # ContinueStmt
    | return # ReturnStmt
    | variableDeclaration # VariableDeclarationStmt
    ;

return
    : RETURN expression?
    ;

forILoop
    : FOR variable COMMA expression COMMA expression codeBlock
    ;

forEachLoop
    : FOR variable IN expression codeBlock
    ;

whileLoop
    : WHILE expression DO? codeBlock
    ;

doWhileLoop
    : DO codeBlock WHILE expression
    ;

switchStatement
    : SWITCH expression
    LBRACE
        switchCase+
        defaultCase
    RBRACE
    ;

switchCase
    : CASE expression codeBlock
    ;

defaultCase
    : CASE DEFAULT codeBlock
    ;

ifStatement
    : if
      elif*
      else?
    ;

if
    : IF expression codeBlock
    ;

elif
    : ELIF expression codeBlock
    ;

else
    : ELSE codeBlock
    ;

expression
    : primaryExpression          # PrimaryExpr
    | literal                    # LiteralExpr
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
    : logicalAndExpression (OR logicalAndExpression)?
    ;

logicalAndExpression // and, &&
    : bitwiseOrExpression (AND bitwiseOrExpression)?
    ;

bitwiseOrExpression // |
    : bitwiseXorExpression (BITOR bitwiseXorExpression)?
    ;

bitwiseXorExpression // ^, xor
    : bitwiseAndExpression (XOR bitwiseAndExpression)?
    ;

bitwiseAndExpression // &
    : equalityExpression (BITAND equalityExpression)?
    ;

equalityExpression // ==, !=
    : relationalExpression ( op=(EQUAL_EQUAL | NOT_EQUAL) relationalExpression )?
    ;

relationalExpression // <, >, <=, >=, is, in
    : shiftExpression ( op=(GT | LT | GE | LE | IS | IN) shiftExpression )?
    ;

shiftExpression // <<, >>
    : additiveExpression ( op=(SHL | SHR) additiveExpression )?
    ;

additiveExpression // +, -
    : multiplicativeExpression ( op=(ADD | SUB) multiplicativeExpression )?
    ;

multiplicativeExpression // *, /, //, %
    : unaryExpression ( op=(MUL | DIV | IDIV | MOD) unaryExpression )?
    ;

unaryExpression
    : op=(ADD | SUB | NOT | TILDA) unaryExpression # OpExpr  // Prefix: +expr, -expr, !expr, ~expr
    | INC unaryExpression                          # IncExpr // Prefix: ++expr
    | DEC unaryExpression                          # DecExpr // Prefix: --expr
    | postfixExpression                            # Postfix
    ;

postfixExpression
    : primaryExpression                                      # Primary
    | postfixExpression DOT (IDENTIFIER| NEW)                # MemberAccess      // Member access: .member
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
    | ((LPAREN lambdaParameterList RPAREN) | lambdaParameter) codeBlock # LambdaExpr
    // Add lambda expressions if ARROW_RIGHT is used, e.g. (params) => expr
    // Add property access if GET/SET are used
    // Add ternary if QUESTION is used, e.g. cond ? trueExpr : falseExpr
    ;

dictInitializer
    : LBRACK (kvPair (COMMA (kvPair))* )? COMMA? RBRACK // e.g. [a = 1, b = 3] [,]
    ;

kvPair
    : expression EQUAL expression
    ;

arrayInitializer
    : LBRACK (expression (COMMA expression)*)? COMMA? RBRACK // e.g. [1, 2, 3,]
    | ARRAY_OP
    ;

lambdaParameterList
    : lambdaParameter (COMMA lambdaParameter)*
    ;

lambdaParameter
    : IDENTIFIER (COLON type)?
    ;

parameterList
    : parameter (COMMA parameter)*
    ;

parameter
    : IDENTIFIER COLON type
    ;

generic
    : LBRACK (
    genericBound (COMMA genericBound)*
    )? RBRACK;

genericBound
    : (lhs=type | QUESTION) ((COLON | EXTENDS) rhs=type)?
    ;

type
//    : IDENTIFIER                        # PlainType
    : IDENTIFIER generic?              # PlainType
    | type (BITOR type)+                 # UnionType
    | type (BITAND type)+                 # IntersectionType
    | type (ARRAY_OP | (LBRACK RBRACK))+     # ArrayType
    | type QUESTION                         # NullableType
    ;

annotationUse
    : AT IDENTIFIER (LPAREN (kvPair | expression)? RPAREN)?
    ;

access
    : PUBLIC # Public
    | PROTECTED # Protected
    | PRIVATE # Private
    ;
modifier: ABSTRACT | (STATIC FINAL?);

LINE_CONTINUATION: '\\' ('\r\n' | '\n' | '\r') -> skip;

LBRACE : '{' ;
RBRACE : '}' ;
LBRACK : '[' ;
RBRACK : ']' ;
ARROW_RIGHT : '=>' ;
DOT : '.' ;
SUPER : 'super' ;
LPAREN : '(' ;
RPAREN : ')' ;
COMMA : ',' ;
COLON : ':' ;
PACKAGE : 'package' ;
IMPORT : 'import' ;
FROM : 'from' ;
BREAK : 'break' ;
CONTINUE : 'continue' ;
RETURN : 'return' ;
SWITCH : 'switch' ;
CASE : 'case' ;
DEFAULT : 'default' ;
IF : 'if' ;
ELIF : 'elif' ;
ELSE : 'else' ;
WHILE : 'while' ;
DO : 'do' ;
FOR : 'for' ;
IN : 'in' ;
WALRUS : ':=' ;
EQUAL : '=' ;
GET : 'get' ;
SET : 'set' ;
FUNC : 'func' ;
INIT : 'init' ;
OPERATOR : 'operator' ;
CLASS : 'class' ;
ENUM  : 'enum' ;
EXTENDS : 'extends' ;
IMPLEMENTS : 'implements' ;
INTERFACE : 'interface' ;
ANNOTATION : 'annotation' ;
QUESTION : '?' ;
NULL : 'null' ;
TRUE : 'true' ;
FALSE : 'false' ;
AT : '@' ;
AS : 'as' ;
BITOR : '|' ;
BITAND : '&' ;
MUL : '*' ;
DIV : '/' ;
IDIV : '//' ;
MOD : '%' ;
ADD : '+' ;
SUB : '-' ;
SHR : '>>' ;
SHL : '<<' ;
XOR : 'xor' | '^';
GT : '>' ;
LT : '<' ;
GE : '>=' ;
LE : '<=' ;
EQUAL_EQUAL : '==' ;
NOT_EQUAL : '!=' ;
IS : 'is' ;
AND : 'and' | '&&';
OR : 'or' | '||';
NOT : 'not' | '!';
INC : '++' ;
DEC : '--' ;
ARRAY_OP: '['']';
TILDA: '~';
PUBLIC : 'public' ;
PRIVATE : 'private' ;
PROTECTED : 'protected' ;
STATIC : 'static' ;
ABSTRACT : 'abstract' ;
FINAL : 'final' ;
NEW: 'new';
IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER
    : ( [0-9]+ ('.' [0-9]+)? ('f' | 'F' | 'd' | 'D' | 'l' | 'L')?
    | '0x' [0-9a-fA-F]+ ('l' | 'L')?
    | '0b' [01]+        ('l' | 'L')?
    | '0'  [0-7]+       ('l' | 'L')?
    )
    ;
STRING: '\'' .*? '\'' | '"' .*? '"';
WS: [ \t\r]+ -> channel(HIDDEN);
ENDLINE: ('\n' | ';');
COMMENT: ('#' .*? ('\n' | EOF)) -> channel(HIDDEN);