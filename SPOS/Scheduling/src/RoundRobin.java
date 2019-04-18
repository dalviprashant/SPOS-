import java.util.Arrays;
import java.util.Scanner;

public class RoundRobin {
	private Scanner scan;
	public void execute()
	{
		scan=new Scanner(System.in);
		System.out.println("Enter number of process:");
		int n=scan.nextInt();
		Process[] process=new Process[n];
	
		for(int i=0;i<n;i++)
		{
			System.out.println("P"+(i+1)+":Enter Arrival time,burst time");
			int at=scan.nextInt();
			int bt=scan.nextInt();
		
			process[i]=new Process("P"+(i+1),at,bt);
			process[i].remBT=bt;
		}
		
		Arrays.sort(process,new sortByArrival());
		
		System.out.println("Enter quantum:");
		int quantum=scan.nextInt();
		
		int time=0;
		boolean flag;
		double avgTAT=0,avgWT=0;
		while(true)
		{
			flag=true;
			for(int i=0;i<n;i++)
			{
				if((process[i].remBT>0)&&(process[i].AT<=time))
				{
					flag=false;
					if(process[i].remBT>quantum)
					{
						process[i].remBT-=quantum;
						time+=quantum;
					}
					else
					{
						time+=process[i].remBT;
						process[i].remBT=0;
						process[i].CT=time;
						process[i].TAT=process[i].CT-process[i].AT;
						process[i].WT=process[i].TAT-process[i].BT;
						avgWT+=process[i].WT;
						avgTAT+=process[i].TAT;
						process[i].display();
						
					}
				}
			}
			if(flag)
			{
				break;
			}
		}
		
		avgTAT=(double)avgTAT/n;

		avgWT=(double)avgWT/n;
		
		System.out.println("Average TurnAroundtime:"+avgTAT);

		System.out.println("Average Waiting:"+avgWT);
	}
}
