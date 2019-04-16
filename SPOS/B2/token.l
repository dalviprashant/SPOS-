%{
#include<stdio.h>

%}

DIGIT [0-9]
LETTER [a-zA-Z]
PREPROCESSOR "import"[^\n]*";"
CONDITIONAL "if"|"else"|"else if"|"switch"
ITERATIVE "for"|"while"|"do"
DATATYPE "int"|"float"|"double"|"boolean"
IDENTIFIER {LETTER}({LETTER}|{DIGIT}|"_")*
NUM {DIGIT}+
REAL {DIGIT}+[.]{DIGIT}+
%%

{PREPROCESSOR}	{printf("%s=>\tPREPROCESSOR\n",yytext);}
{CONDITIONAL}	{printf("%s=>\tCONDITONAL\n",yytext);}
{ITERATIVE}	{printf("%s=>\tITERATIVE\n",yytext);}
{DATATYPE}	{printf("%s=>\tDATATYPE\n",yytext);}
{IDENTIFIER}	{printf("%s=>\tIDENTIFIER\n",yytext);}
{REAL}		{printf("%s=>\tREAL NUMBER\n",yytext);}
{NUM}		{printf("%s=>\t NUMBER\n",yytext);}
"("		{printf("%s=>\t Opening Bracket\n",yytext);}
")"		{printf("%s=>\t Closing Bracket\n",yytext);}
"{"		{printf("%s=>\t Opening Parentheses\n",yytext);}
"}"		{printf("%s=>\t Closing Parentheses\n",yytext);}
		

. ;
%%

int yywrap(void)
{
	return 1;
}
int main()
{
	//extern FILE *yyin;
	//yyin=fopen("FCFS.java","r");
	yylex();
	return 0;
}
