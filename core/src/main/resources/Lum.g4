grammar Lum;

// Program Structure
program: statement* EOF;

// Statements
statement
    : package
    | importStatement
    | declaration
    | controlStatement
    | expression
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
    | classDeclaration
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
    | (':' statement)
    | '=>' statement
    ;

// Expressions
expression
    : primary                                       # PrimaryExpression
    | lambda                                        # LambdaExpression
    | functionCall                                  # FunctionCallExpr
    | expression '[' expression ']'                 # ArrayAccess
    | expression '.' (IDENTIFIER | functionCall)    # MemberAccess
    | expression after=unaryOperator                # PostUnary
    | before=unaryOperator expression               # PreUnary
    | expression binaryOperator expression          # Binary
    ;

primary
    : literal                                        # LiteralExpr
    | IDENTIFIER                                    # IdentifierExpr
    | IDENTIFIER assignment                         # AssignmentExpr
    | '[' argumentList? ']'                         # ListLiteral
    | '[' keyValueList? ']'                         # DictLiteral
    | '(' expression ')'                            # ParenExpr
    ;

keyValueList
    : keyValue (',' keyValue)*
    ;


// Package and Import
package: 'package' IDENTIFIER ('.' IDENTIFIER)*;
importStatement
    : 'import'
    ( importSimple
    | importAs
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
    : importAs (',' importAs)* 'from' IDENTIFIER ('.' IDENTIFIER)*
    ;

importMultiple
    : importSimple (',' importSimple)*
    ;

// Control Flow Keywords
break: 'break' IDENTIFIER?;
continue: 'continue' IDENTIFIER?;
return: 'return' expression?;

// Lambda Expression
lambda
    : ((IDENTIFIER (',' IDENTIFIER)*)
    | '(' IDENTIFIER (',' IDENTIFIER)* ')')
    '=>' ('{' statement* '}' | statement)
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

forILoop: 'for' variableDeclarationStatement ',' expression ',' expression block;
forEachLoop: 'for' variableDeclaration 'in' expression block;

// Variable Declaration
keyValue: (STRING | IDENTIFIER) ':' expression;

variableDeclarationStatement
    : access? modifier? (variableDeclaration) (',' variableDeclaration)*
    ;

variableDeclaration
    : IDENTIFIER (':' type)? ((':=' | '=') expression)?
      (getterDeclaration | setterDeclaration)*
    ;

getterDeclaration: access? 'get' block?;
setterDeclaration: access? 'set' ( '(' parameter ')' block)?;

// Function and Constructor Declaration
functionDeclaration
    : annotation* access? modifier?
    'func' IDENTIFIER genericDeclaration? '(' parameterList? ')' (':' type)? (block)?
    ;

constructorDeclaration
    : annotation* access? modifier?
    'init' genericDeclaration? '(' parameterList? ')' block
    ;

// Operator Declaration
operatorDeclaration
    : access? modifier? 'operator' operator genericDeclaration?
      '(' parameterList? ')' (':' type)? block
    ;

// Parameters and Arguments
parameterList: parameter (',' parameter)*;
parameter: IDENTIFIER ':' type ('=' expression)?;
argumentList: expression (',' expression)*;

// Class and Interface Declaration
classDeclaration
    : annotation* access? modifier?
      'class' IDENTIFIER genericDeclaration? inheritance? block
    ;

inheritance: '(' inheritanceSpec (',' inheritanceSpec)* ')';

inheritanceSpec
    : first=type
    | 'extends' '=' extends=type
    | 'implements' '=' '[' type (',' type)* ']'
    ;

interfaceDeclaration
    : access? modifier?
      'interface' IDENTIFIER inheritance? genericDeclaration? '{' functionSignature* '}'
    ;

functionSignature
    : access? modifier?
      'func' genericDeclaration? IDENTIFIER '(' parameterList? ')' (':' type)
    ;

// Annotation
annotationDeclaration
    : annotation* access? modifier?
      'annotation' IDENTIFIER ('(' parameterList? ')')? block?
    ;

annotation: '@' IDENTIFIER ('(' (annotationArgs | literal) ')')?;
annotationArgs: annotationArg (',' annotationArg)*;
annotationArg: IDENTIFIER '=' STRING;

// Function Call
functionCall: IDENTIFIER genericDeclaration? '(' argumentList? ')';

// Types and Generics
type
    : (IDENTIFIER genericDeclaration?)
    | type ('|' type)+;

genericDeclaration: '[' generic (',' generic)* ']';

generic
    : ((IDENTIFIER | '?') (extends=('extends' | ':') | super='super')?)?
      type ('&' type)*
    ;

// Literals and Basic Types
literal
    : NUMBER
    | STRING
    | 'null'    # Null
    | 'true'    # True
    | 'false'   # False
    ;

// Operators
operator: binaryOperator | unaryOperator;

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

    // Level 8-9: Logical operations
    | '&&'  # CAND
    | 'and' # CAND
    | '||'  # COR
    | 'or'  # COR
    ;

unaryOperator
    : '++'  # Increment
    | '--'  # Decrement
    | '!'   # Not
    | 'not' # Not
    ;

// Assignments and Modifiers
assignment: operator? '=' expression;

access
    : 'public'    # Public
    | 'private'   # Private
    | 'protected' # Protected
    ;

modifier: (static='static' | abstract='abstract')? (final='final')?;

// Lexer Rules
fragment IDENTIFIER0: [a-zA-Z_][a-zA-Z0-9_]*;
IDENTIFIER: IDENTIFIER0;
NUMBER: [0-9]+ ('.' [0-9]+)?;
STRING: '\'' .*? '\'' | '"' .*? '"';
WS: [ \t\r\n]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;