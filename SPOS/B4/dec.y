%{
#include<stdio.h>

void yyerror(char*);
int yylex();

%}

%token INT ID AS NUM SC NL FLOAT REAL DOUBLE BOOL COMMA BV 

%%

s:type1|type2|type3|type4
;

type1:INT varlist SC NL	{printf("Valid integer declaration");return 0;}
;

type2:FLOAT varlist1 SC NL	{printf("Valid Float declaration");return 0;} 
;

type3:DOUBLE varlist1 SC NL	{printf("Valid Double declaration");return 0;} 
;

type4:BOOL varlist2 SC NL	{printf("Valid Boolean declaration");return 0;} 
;

varlist: ID | ID AS NUM | ID COMMA varlist | ID AS NUM COMMA varlist 
;

varlist1: ID | ID AS NUM | ID COMMA varlist | ID AS NUM COMMA varlist 
;

varlist2: ID | ID AS BV | ID COMMA varlist | ID AS BV COMMA varlist 

%%

void yyerror(char *s)
{
	fprintf(stderr,"ERROR:%s\n",s);
}

int main()
{
	yyparse();
	return 0;
}
