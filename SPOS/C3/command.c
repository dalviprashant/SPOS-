#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<sys/wait.h> 
int main()
{
	char *command;
	
	int choice,pid;
	do
	{
		printf("\nMENU\n1.PS \n2.FORK\n3.JOIN\n4.WAIT\n5.EXIT\n");
		
		scanf("%d",&choice);
		switch(choice)
		{
			case 1:
				strcpy(command,"ps");
				printf("Executing ps command...\n");
				system(command);
				break;
			case 2:

				 pid=fork();
				if(pid==0)
				{
					printf("Child process %d\n",getpid());
				}
				else
				{
					printf("Parent process %d\n",getpid());
				}
				break;
			case 3:
				printf("Joining file1 and file2 ");
				strcpy(command,"join file1 file2");
				system(command);
				break;
			case 4:
				pid=fork();
				if(pid==0)
				{
					printf("CP:Child process %d\n",getpid());
				}
				else
				{
					printf("PP:Parent Process %d\n",getpid());
					wait(NULL);
					printf("T:Terminated child\n");
				}
				break;
			
		}
	}while(choice!=5);
				

	return 0;
}
