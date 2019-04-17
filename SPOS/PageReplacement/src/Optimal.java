import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Optimal {
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
					int farthest=findFarthest(pages,i);
					index.put(pages[i],farthest);
					
				}
				else
				{
					//frames.add(pages[i]);
					index.put(pages[i],findFarthest(pages, i));
					hit++;
				}
				
				
			}
			else
			{
				if(!frames.contains(pages[i]))
				{
					Iterator<Integer>itr=frames.iterator();
					int farthest=-1;
					int rem=0;
					while(itr.hasNext())
					{
						int val=itr.next();
						if(index.get(val)>farthest)
						{
							rem=val;
							farthest=index.get(val);
						}
					}
					frames.remove(rem);
					index.remove(rem);
					frames.add(pages[i]);
					index.put(pages[i],findFarthest(pages, i));
					miss++;
					
					
				}
				else
				{

					index.put(pages[i],findFarthest(pages, i));
					hit++;
				}
			
				
			}
		}
		double ratio=(double)hit/num;
		System.out.println("Misses:"+miss);
		System.out.println("Hit:"+hit);
		System.out.println("Hit Ratio:"+ratio);
	
	}
	public static int findFarthest(int pages[],int curInd)
	{
		int farthest=curInd;
		int curPage=pages[curInd];
		for(int i=farthest+1;i<pages.length;i++)
		{
			if(pages[i]==curPage)
			{
				farthest=i;
				return farthest;
			}
		}
		return Integer.MAX_VALUE;
	}
}
