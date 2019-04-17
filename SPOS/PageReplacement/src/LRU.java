import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LRU {
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
		HashSet<Integer> frames=new HashSet<Integer>(width);
		HashMap<Integer, Integer> index=new HashMap<Integer, Integer>();
		Queue<Integer> q=new LinkedList<Integer>();
		System.out.println(q.size());
		int hit=0,miss=0;
		for(int i=0;i<num;i++)
		{
			if(frames.size()<width)
			{
				if(!frames.contains(pages[i]))
				{
					miss++;
					frames.add(pages[i]);
					index.put(pages[i],i);
					
				}
				else
				{
					//frames.add(pages[i]);
					index.put(pages[i],i);
					hit++;
				}
				
				
			}
			else
			{
				if(!frames.contains(pages[i]))
				{
					int min=Integer.MAX_VALUE;
					int val=Integer.MIN_VALUE;
					Iterator<Integer> itr=frames.iterator();
					while(itr.hasNext())
					{
						int temp=itr.next();
						if(index.get(temp)<min)
						{
							min=index.get(temp);
							val=temp;
						}
					}
					frames.remove(val);
					frames.add(pages[i]);
					index.put(pages[i],i);
					miss++;
					
					
				}
				else
				{

					index.put(pages[i],i);
					hit++;
				}
			
				
			}
		}
		double ratio=(double)hit/num;
		System.out.println("Misses:"+miss);
		System.out.println("Hit:"+hit);
		System.out.println("Hit Ratio:"+ratio);
	
	}
}
