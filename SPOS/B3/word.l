%{
#include<string.h>
#include<stdio.h>
int charCount=0,wordCount=0,lineCount=0;
%}

word [^ \t\n]+
eol \n


%%
{word} 	{wordCount++;charCount+=yyleng;}
{eol} 	{lineCount++;}
. 	{charCount++;}
%%
int yywrap(void)
{
	return 1;
}

int main()
{
	extern FILE* yyin;
	yyin=fopen("input","r");
	yylex();
	printf("Word count = %d \nCharacter Count = %d \nLine Count = %d \n" ,wordCount,charCount,lineCount);
	return 0;
}
