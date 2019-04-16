%{
#include<stdio.h>
#include "y.tab.h"

%}

digit [0-9]
ASSIGN =
letter [a-zA-Z]

%%

int	{printf("%s=>	Datatype\n",yytext);return (INT);}
float	{printf("%s=>	Datatype\n",yytext);return (FLOAT);}
double	{printf("%s=>	Datatype\n",yytext);return (DOUBLE);}
boolean	{printf("%s=>	Datatype\n",yytext);return (BOOL);}
true|false	{printf("%s=>	Boolean Value\n",yytext);return (BV);}
{digit}+[.]{digit}+	{printf("%s=>	Real Number\n",yytext);return (REAL);}

[a-zA-Z]+[a-zA-Z0-9_]*	{printf("%s=>	ID\n",yytext);return (ID);}
{ASSIGN}	{printf("%s=>	Assignment\n",yytext);return (AS);}
{digit}+	{printf("%s=>	Number\n",yytext);return (NUM);}

";"	{printf("%s=>	Semicolon\n",yytext);return SC;}
","  return COMMA;
\n return NL;
. ;

%%

int yywrap(void)
{
	return 1;
}	
