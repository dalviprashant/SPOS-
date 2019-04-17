import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class FCFS {
	public static Scanner scan=new Scanner(System.in);
	public static void main(String [] args)
	{
		System.out.println("Enter the number of references:");
		int num=scan.nextInt();
		System.out.println("Enter the page references:");
		int [] pages=new int[num];
		for(int i=0;i<num;i++)
		{
			pages[i]=scan.nextInt();
		}
		System.out.println("Number of frames:");
		int width=scan.nextInt();
		Queue<Integer> q=new LinkedList<Integer>();
		System.out.println(q.size());
		int hit=0,miss=0;
		for(int i=0;i<num;i++)
		{
			if(q.size()<width)
			{
				if(!q.contains(pages[i]))
				{
					miss++;
					q.add(pages[i]);
				}
				else
				{
					hit++;
				}
				System.out.println("Partial");
				for(int n:q)
				{
					
					System.out.print(n);
				}
			}
			else
			{
				if(!q.contains(pages[i]))
				{
					q.poll();
					q.add(pages[i]);
					miss++;
				}
				else
				{
					hit++;
				}
				System.out.println("Full");
				for(int n:q)
				{
					
					System.out.print(n);
				}
				
			}
		}
		double ratio=(double)hit/num;
		System.out.println("Misses:"+miss);
		System.out.println("Hit:"+hit);
		System.out.println("Hit Ratio:"+ratio);
	}
}
