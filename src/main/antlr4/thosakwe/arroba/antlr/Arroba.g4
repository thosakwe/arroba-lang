grammar Arroba;

compilationUnit: stmt*;

stmt: (assignStmt | exprStmt | ifStmt | retStmt) SEMI?;
assignStmt: expr (ARR_L | EQUALS) expr;
exprStmt: expr;
retStmt: RET expr;

ifStmt:  ifBlock elifBlock* elseBlock?;
ifBlock: (IF PAREN_L expr PAREN_R stmt) | (IF PAREN_L expr PAREN_R CURLY_L stmt* CURLY_R);
elifBlock: (ELSE IF PAREN_L expr PAREN_R stmt) | (ELSE IF PAREN_L expr PAREN_R CURLY_L stmt* CURLY_R);
elseBlock: (ELSE stmt) | (ELSE CURLY_L stmt* CURLY_R);

expr:
    ID #IdExpr
    | (INT | HEX | DBL) #NumExpr
    | (TRUE | FALSE) #ConstBoolExpr
    | expr (PLUS | MINUS | TIMES | DIVIDE | MODULO | CARET) expr #MathExpr
    | STRING #StringExpr
    | expr DOT ID #MemberExpr
    | target=expr SQUARE_L index=expr SQUARE_R #IndexExpr
    | SQUARE_L (expr COMMA)* expr SQUARE_R #ArrayExpr
    | LOCAL COLON ID #LocalExpr
    | expr ARR_R expr #ArrowRightExpr
    | FN paramSpec CURLY_L stmt* CURLY_R #FunctionExpr
    | paramSpec ARR_FAT expr #InlineFunctionExpr
    | target=expr PAREN_L ((expr COMMA)* expr)? PAREN_R #InvocationExpr
    | PAREN_L expr PAREN_R #NestedExpr
;

paramSpec: (PAREN_L ((ID COMMA)* ID)? PAREN_R)?;

SL_CMT: ('#' | '//') ~('\n')* -> channel(HIDDEN);
WS: (' ' | '\n' | '\r' | '\r\n') -> skip;

ARR_FAT: '=>';
ARR_L: '<-';
ARR_R: '->';
COLON: ':';
COMMA: ',';
CURLY_L: '{';
CURLY_R: '}';
DOT: '.';
EQUALS: '=';
PAREN_L: '(';
PAREN_R: ')';
SEMI: ';';
SQUARE_L: '[';
SQUARE_R: ']';

CARET: '^';
PLUS: '+';
MINUS: '-';
MODULO: '%';
TIMES: '*';
DIVIDE: '/';
SUMMA: '$';

BITWISE_AND: '&';
BITWISE_OR: '|';
BITWISE_NOT: '!';
BITWISE_SHL: '<<';
BITWISE_SHR: '>>';
BITWISE_XOR:  'xor';

IF: 'if';
ELIF: 'elif';
ELSE: 'else';
IS: '==' | 'is' | 'equ';
NOT: '!=' | 'not' | 'nequ';
AND: '&&' | 'and';
OR: '||' | 'or';
LT: '<' | 'lt';
LTE: LT EQUALS | 'lte';
GT: '>' | 'gt';
GTE: GT EQUALS | 'gte';

FALSE: 'false';
TRUE: 'true';

FN: 'fn';
LOCAL: 'local';
RET: 'ret';

DBL: MINUS? [0-9]+ DOT [0-9]+;
HEX: '0x' [a-zA-Z0-9]+;
INT: MINUS? [0-9]+;
fragment ESCAPED: '\\"' | '\\r' | '\\n';
STRING: '"' (ESCAPED | ~('\n'|'\r'))*? '"';
ID: [a-zA-Z_] [a-zA-Z0-9_]*;