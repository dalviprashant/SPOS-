import java.util.Arrays;
import java.util.Scanner;

public class FCFS {
	private Scanner scan;
	public void exectue()
	{
		scan=new Scanner(System.in);
		System.out.println("Enter the number of processes:");
		int n=scan.nextInt();
		Process process[]=new Process[n];
		for(int i=0;i<n;i++)
		{
			System.out.println("P"+(i+1)+":Enter arrival and burst time");
			int at=scan.nextInt();
			int bt=scan.nextInt();
			process[i]=new Process("P"+(i+1),at,bt);
		}
		
		
		Arrays.sort(process,new sortByArrival());
		int sum=0;
		double avgWT=0,avgTAT=0;
		for(int i=0;i<n;i++)
		{
			sum=process[i].CT=sum+process[i].BT;
			process[i].TAT=process[i].CT-process[i].AT;
			process[i].WT=process[i].TAT-process[i].BT;
			
			avgTAT+=process[i].TAT;
			avgWT+=process[i].WT;
		}
		avgTAT=(double)avgTAT/n;

		avgWT=(double)avgWT/n;
		
		System.out.println("Average Waiting Time:"+avgWT);

		System.out.println("Average Turnaround Time:"+avgTAT);
	}
}
