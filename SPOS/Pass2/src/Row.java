
public class Row {
	String sym;
	int address;
	public Row()
	{
		
	}
	public Row(String sym,int address)
	{
		this.sym=sym;
		this.address=address;
	}
	public String getSym() {
		return sym;
	}
	public void setSym(String sym) {
		this.sym = sym;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
}
