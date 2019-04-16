%{
#include<stdio.h>
#include "y.tab.h"

%}

DIGIT [0-9]
REAL {DIGIT}+[.]{DIGIT}*
ASSIGN =

%%
[\t ] ;
int {printf("%s => DATATYPE \n", yytext); return (INT);}
[a-zA-Z]+[a-zA-Z0-9_]* {printf("%s => ID\n",yytext); return (ID);}
{ASSIGN} {printf("%s => Assignment\n",yytext); return (AS);}
{DIGIT}+ {printf("%s => Number\n",yytext); return NUM;}
";" {printf("%s => Semicolon\n" ,yytext); return (SC);}
"\n" return (NL);
. ;
%%
int yywrap(void)
{
	return 1;
}
