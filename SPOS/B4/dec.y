%{

#include<stdio.h>
void yyerror(char*);
int yylex();

%}

%token INT AS SC NL ID NUM

%%
s: type1
;

type1:INT ID AS NUM SC NL { printf("valid INT Variable declaration"); return 0;}
;
%%
void yyerror(char *s)
{
	fprintf(stderr, "ERROR: %s\n",s);
}

int main()
{
	yyparse();
	return 0;
}
