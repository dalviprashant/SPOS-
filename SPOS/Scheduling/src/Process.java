import java.util.Comparator;

public class Process {
	int BT,CT,TAT,WT,AT,remBT,priority;
	String ID;
	public Process() {
		
	}
	public Process(String ID,int AT,int BT)
	{
		this.ID=ID;
		this.AT=AT;
		this.BT=BT;
	}
	public Process(String ID,int AT,int BT,int priority)
	{
		this.ID=ID;
		this.AT=AT;
		this.BT=BT;
		this.priority=priority;
	}
	public void display()
	{
		System.out.println(ID+"\t"+BT+"\t"+AT+"\t"+CT+"\t"+TAT+"\t"+WT+"\t"+priority);
	}
}
class sortByArrival implements Comparator<Process>
{

	@Override
	public int compare(Process p1, Process p2) {
		// TODO Auto-generated method stub
		return p1.AT-p2.AT;
	}
	
}
class sortByPriority implements Comparator<Process>
{

	@Override
	public int compare(Process p1, Process p2) {
		// TODO Auto-generated method stub
		return p2.priority-p1.priority;
	}
	
}
