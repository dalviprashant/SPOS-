
public class Schedule {
	public static void main(String [] args)
	{
		FCFS s1=new FCFS();
		//s1.exectue();
		PriorityScheduling s2=new PriorityScheduling();
		//s2.execute();
		RoundRobin s3=new RoundRobin();
		//s3.execute();
		SJF s4=new SJF();
		s4.execute();
	}
}
