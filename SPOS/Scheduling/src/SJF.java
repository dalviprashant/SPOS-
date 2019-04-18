import java.util.Scanner;

public class SJF {
	private Scanner scan;
	public void execute()
	{
		scan=new Scanner(System.in);
		System.out.println("Enter number of Processes:");
		int n=scan.nextInt();
		Process[] process=new Process[n];
		for(int i =0;i<n;i++)
		{
			System.out.println("P"+(i+1)+":Enter arrival time and burst time");
			int at=scan.nextInt();
			int bt=scan.nextInt();
			process[i]=new Process("P"+(i+1),at,bt);
			process[i].remBT=bt;
		}
		
		double avgTAT=0,avgWT=0;
		int sum=0,time=0;
		boolean done=false,check;
		int minimum=0;
		int min=Integer.MAX_VALUE;
		int count=0;
		
		while(count<n)
		{
			
			check=false;
			for(int i=0;i<n;i++)
			{
				if((process[i].AT<=time)&&(process[i].remBT<=min && process[i].remBT>0))
				{
					minimum=i;
					System.out.println(i);
					min=process[i].remBT;
					check=true;
				}
			}
			if(!check)
			{
				time++;
				continue;
			}
			process[minimum].remBT--;
			min--;
			if(min==0)
			{
				min=Integer.MAX_VALUE;
				count++;
				sum=time+1;
				process[minimum].CT=sum;
				process[minimum].TAT=process[minimum].CT-process[minimum].AT;
				process[minimum].WT=process[minimum].TAT-process[minimum].BT;
				avgWT=avgWT+process[minimum].WT;
				avgTAT=avgTAT+process[minimum].TAT;
			}
			time++;
		}
		avgTAT=(double)avgTAT/n;
		avgWT=(double)avgWT/n;
		System.out.println("Average Waiting Time"+avgWT);
		System.out.println("Average TAT="+avgTAT);
	}
}
