grammar Lum;

// Program Structure
program: statement* EOF;

// Statements
statement
    : package
    | expression
    | importStatement
    | declaration
    | controlStatement
    | break
    | continue
    | return
    ;

// Declarations
declaration
    : functionDeclaration
    | variableDeclarationStatement
    | operatorDeclaration
    | constructorDeclaration
    | functionSignature
    | classDeclaration
    | enumDeclaration
    | interfaceDeclaration
    | annotationDeclaration
    ;

// Control Flow Statements
controlStatement
    : ifStatement
    | switchStatement
    | loop
    ;

// Basic Blocks
block
    : ('{' statement* '}')
//    | (':' statement)
    | '=>' statement
    ;

// Expressions
expression
    : functionCall                                  # FunctionCallExpr
    | superCall                                     # SuperCallExpr
    | lambda                                        # LambdaExpression
    | expression '[' argumentList ']'               # ArrayAccess
    | expression '.' (functionCall | IDENTIFIER)    # MemberAccess
    | expression postUnaryOperator                  # PostUnary
    | preUnaryOperator expression                   # PreUnary
    | expression binaryOperator expression          # Binary
    | expression assignment                         # AssignmentExpr
    | expression 'as' type                          # CastExpression
    | primary                                       # PrimaryExpression
    ;

primary
    : literal                                       # LiteralExpr
    | IDENTIFIER                                    # IdentifierExpr
    | 'super'                                       # SuperAccess
    | '[' argumentList? ']'                         # ListLiteral
    | '[' keyValueList? ']'                         # DictLiteral
    | '(' expression ')'                            # ParenExpr
    ;

keyValueList
    : keyValue (',' keyValue)*
    ;

keyValue: expression ':' expression;

// Package and Import
package: 'package' IDENTIFIER ('.' IDENTIFIER)*;
importStatement
    : 'import'
    ( anySimpleImport
    | importFrom
    | importMultiple)
    ;

importAs
    : importSimple 'as' IDENTIFIER
    ;

importSimple
    : IDENTIFIER ('.' IDENTIFIER)*
    ;

importFrom
    : importMultiple 'from' IDENTIFIER ('.' IDENTIFIER)*
    ;

importMultiple
    : anySimpleImport (',' anySimpleImport)*
    ;

anySimpleImport
    : importSimple | importAs
    ;

// Control Flow Keywords
break: 'break' IDENTIFIER?;
continue: 'continue' IDENTIFIER?;
return: 'return' expression?;

// Lambda Expression
lambda
    : ((parameterList) | '(' parameterList ')')
    block
    ;

// Switch Statement
switchStatement
    : 'switch' expression '{' case* default '}'
    ;

case: 'case' expression block;
default: 'default' block;

// If Statement
ifStatement: if elif* else?;
if: 'if' expression block;
elif: 'elif' expression block;
else: 'else' block;

// Loops
loop
    : whileLoopStatement
    | forLoopStatement
    ;

whileLoopStatement
    : whileLoop
    | doWhileLoop
    ;

whileLoop: 'while' expression block;
doWhileLoop: 'do' block 'while' expression;

forLoopStatement
    : forILoop
    | forEachLoop
    ;

forILoop: 'for' variableDeclaration? ',' condition=expression? ',' iter=expression? block;
forEachLoop: 'for' variableDeclaration 'in' expression block;

// Variable Declaration
variableDeclarationStatement
    : annotation* access? modifier? (variableDeclaration) (',' variableDeclaration)*
    ;

variableDeclaration
    : IDENTIFIER (':' type)? ((typeEq=':=' | eq='=') expression)?
      (getterDeclaration | setterDeclaration)*
    ;

getterDeclaration: access? 'get' block?;
setterDeclaration: access? 'set' ( '(' parameter ')' block)?;

// Function and Constructor Declaration
functionDeclaration
    : annotation* access? modifier?
    'func' IDENTIFIER genericDeclaration? '(' parameterList? ')' (':' type)? block?
    ;

constructorDeclaration
    : annotation* access? modifier?
    'init' genericDeclaration? '(' parameterList? ')' block
    ;

// Operator Declaration
operatorDeclaration
    : annotation* access? modifier? 'operator' operator genericDeclaration?
      '(' parameterList? ')' (':' type)? block
    ;

// Parameters and Arguments
parameterList: parameter (',' parameter)*;
parameter: IDENTIFIER ':' type ('=' expression)?;
argumentList: expression (',' expression)*;

// Class and Interface Declaration
classDeclaration
    : annotation* access? modifier?
      'class' IDENTIFIER genericDeclaration? inheritance? block?
    ;

enumDeclaration
    : annotation* access? modifier?
      'enum' IDENTIFIER genericDeclaration? inheritance? block?
    ;

inheritance: '(' inheritanceSpec (',' inheritanceSpec)* ')';

inheritanceSpec
    : first=type
    | 'extends' '=' extends=type
    | 'implements' '=' '[' type (',' type)* ']'
    ;

interfaceDeclaration
    : annotation* access? modifier?
      'interface' IDENTIFIER inheritance? genericDeclaration? block?
    ;

functionSignature
    : annotation* access? modifier?
      'func' genericDeclaration? IDENTIFIER '(' parameterList? ')' (':' type)
    ;

// Annotation
annotationDeclaration
    : annotation* access? modifier?
      'annotation' IDENTIFIER ('(' parameterList? ')')? block?
    ;

annotation: '@' IDENTIFIER ('(' (annotationArgs | expression) ')')?;
annotationArgs: annotationArg (',' annotationArg)*;
annotationArg: IDENTIFIER '=' expression;

// Function Call
functionCall: (IDENTIFIER | NEW) genericDeclaration? '(' argumentList? ')';
superCall: 'super' '.' NEW '(' argumentList? ')';

// Types and Generics
type
    : ((IDENTIFIER ('.' IDENTIFIER)*) genericDeclaration?) # PlainType
    | type (union='|' type)+ # UnionType
    | type (intersection='&' type)+ # IntersectionType
    | type ARRAY+ # ArrayType
    ;

genericDeclaration: '[' generic (',' generic)* ']';

generic
    : type # UnboundGeneric
    | ((IDENTIFIER | QUESTION_MARK='?') ((extends=('extends' | ':') | super='super') type)) # BoundGeneric
    ;

// Literals and Basic Types
literal
    : NUMBER    # Num
    | STRING    # Str
    | 'null'    # Null
    | 'true'    # True
    | 'false'   # False
    ;

// Operators
operator
    : binaryOperator # BinaryOp
    | unaryOperator  # UnaryOp
    | '[]'           # ArrayAccessOp;

binaryOperator
    // Level 1: Multiplicative
    : '*'   # Mul
    | '/'   # Divide
    | '//'  # DIV
    | '%'   # MOD

    // Level 2: Additive
    | '+'   # Add
    | '-'   # Sub

    // Level 3: Bitwise shift
    | '>>'  # SHR
    | '<<'  # SHL

    // Level 4-6: Bitwise operations
    | '&'   # AND
    | '^'   # XOR
    | 'xor' # XOR
    | '|'   # OR

    // Level 7: Relational
    | '>'   # GT
    | '<'   # LT
    | '>='  # GE
    | '<='  # LE
    | '=='  # EQ
    | '!='  # NEQ
    | 'is'  # IsInstance
    | 'in'  # In

    // Level 8-9: Logical operations
    | '&&'  # CAND
    | 'and' # CAND
    | '||'  # COR
    | 'or'  # COR
    ;

unaryOperator
    : preUnaryOperator  # PreUnaryOp
    | postUnaryOperator # PostUnaryOp
    ;

preUnaryOperator
    : postUnaryOperator # AnyUnary
    | '!'     # Not
    | 'not'   # Not
    ;

postUnaryOperator
    : '++'    # Increment
    | '--'    # Decrement
    ;

// Assignments and Modifiers
assignment: binaryOperator? (eq='=' | typeEq=':=') expression;

access
    : 'public'    # Public
    | 'private'   # Private
    | 'protected' # Protected
    ;

modifier: (static='static' | abstract='abstract') (final='final')?;

// Lexer Rules
LBRACE : '{' ;
RBRACE : '}' ;
T__8 : '=>' ;
LBRACK : '[' ;
RBRACK : ']' ;
DOT : '.' ;
AS : 'as' ;
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
T__14 : ':=' ;
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
AT : '@' ;
BITOR : '|' ;
BITAND : '&' ;
QUESTION : '?' ;
NULL : 'null' ;
TRUE : 'true' ;
FALSE : 'false' ;
MUL : '*' ;
DIV : '/' ;
T__11 : '//' ;
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
PUBLIC : 'public' ;
PRIVATE : 'private' ;
PROTECTED : 'protected' ;
STATIC : 'static' ;
ABSTRACT : 'abstract' ;
FINAL : 'final' ;

fragment IDENTIFIER0: [a-zA-Z_][a-zA-Z0-9_]*;
NEW: 'new';
ARRAY: '[]';
IDENTIFIER: IDENTIFIER0;
NUMBER
    : ('+' | '-')?
    ( [0-9]+ ('.' [0-9]+)? ('f' | 'F' | 'd' | 'D' | 'l' | 'L')?
    | '0x' [0-9a-fA-F]+ ('l' | 'L')?
    | '0b' [01]+        ('l' | 'L')?
    | '0'  [0-7]+       ('l' | 'L')?
    )
    ;
STRING: '\'' .*? '\'' | '"' .*? '"';
WS: [ \t\r\n;]+ -> channel(HIDDEN);
COMMENT: ('#' .*? ('\n' | EOF)) -> channel(HIDDEN);