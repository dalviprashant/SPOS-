import java.util.Scanner;

public class Banker {
	static Scanner scan=new Scanner(System.in);
	public static void main(String [] args)
	{
		System.out.println("Enter the number of processes:");
		int n=scan.nextInt();
		System.out.println("Enter the number of resources:");
		int r=scan.nextInt();
		
		int allocated[][]=new int[n][r];
		int require[][]=new int[n][r];
		int available[]=new int [r];
		int need[][]=new int[n][r];
		
		System.out.println("Enter elements of Allocated matrix");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<r;j++)
			{
				allocated[i][j]=scan.nextInt();
			}
		}
		System.out.println("Enter elements of Requirement matrix");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<r;j++)
			{
				require[i][j]=scan.nextInt();
			}
		}
		System.out.println("Enter the number of available resources:");
		for(int i=0;i<r;i++)
		{
			available[i]=scan.nextInt();
		}
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<r;j++)
			{
				need[i][j]=require[i][j]-allocated[i][j];
			}
		}
		System.out.println("Maximum requirement matrix");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<r;j++)
			{
				System.out.print(require[i][j]+ " ");
			}
			System.out.println("");
		}
		System.out.println("Allocated matrix");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<r;j++)
			{
				System.out.print(allocated[i][j]+ " ");
			}
			System.out.println("");
		}
		System.out.println("Need matrix");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<r;j++)
			{
				System.out.print(need[i][j]+ " ");
			}
			System.out.println("");
		}
		
		int finish[]=new int[n];
		int sequence[]=new int[n];
		int count=0;
		boolean found;
		int flag;
		while(count<n)
		{
			found=false;
			for(int i=0;i<n;i++)
			{
				flag=0;
				if(finish[i]==0)
				{
					for(int j=0;j<r;j++)
					{
						if(need[i][j]>available[j])
						{
							flag=1;
							found=true;
							break;
						}
					}
					if(flag==0)
					{
						//System.out.println(i);
						for(int j=0;j<r;j++)
						{
							available[j]+=allocated[i][j];
						}
						finish[i]=1;
						sequence[count]=i;
						count++;
					}
				}
			}
			if((!found)&&(count!=n))
			{
				System.out.println("System is not in safe state");
				break;
			}
		}
		if(count==n)
		{
			System.out.println("System is in safe state");
			System.out.print("Safe sequence : ");
			for(int i=0;i<n;i++)
			{
				System.out.print(sequence[i]+"->");
			}
		}
	}
}
