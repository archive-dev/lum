lexer grammar LumLexer;

channels {
    COMMENTS,
    IGNORE
}

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
ARRAY_OP: '[]';
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
COMMENT: ('#' .*? ('\n' | EOF)) -> channel(COMMENTS);