import java.util.Arrays;
import java.util.Scanner;

public class PriorityScheduling {
	private Scanner scan;
	public void execute()
	{
		scan=new Scanner(System.in);
		System.out.println("Enter the number of processes:");
		int n=scan.nextInt();
		Process process[]=new Process[n];
		for(int i=0;i<n;i++)
		{
			System.out.println("P"+(i+1)+":Enter Arrival time,burst time and priority");
			int at=scan.nextInt();
			int bt=scan.nextInt();
			int p=scan.nextInt();
			process[i]=new Process("P"+(i+1),at,bt,p);
		}
		
		Arrays.sort(process,new sortByPriority());
		int time=0;
		double avgWT=0,avgTAT=0;
		
		for(int i=0;i<n;i++)
		{
			time=process[i].CT=time+process[i].BT;
			process[i].TAT=process[i].CT-process[i].AT;
			process[i].WT=process[i].TAT-process[i].BT;
			avgWT+=process[i].WT;
			avgTAT+=process[i].TAT;
		}
		
		avgTAT=(double)avgTAT/n;

		avgWT=(double)avgWT/n;
		
		System.out.println("Average TurnAroundtime:"+avgTAT);

		System.out.println("Average Waiting:"+avgWT);
		
	}
}
