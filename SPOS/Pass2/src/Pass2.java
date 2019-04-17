import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Pass2 {
	public static void main(String [] args) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("IC"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("pass2"));
		BufferedReader st=new BufferedReader(new FileReader("symbol.txt"));
		BufferedReader lt=new BufferedReader(new FileReader("literal.txt"));
		LinkedList<Row> symtab=new LinkedList<Row>();
		LinkedList<Row> littab=new LinkedList<Row>();
		
		String line=null;
		while((line=st.readLine())!=null)
		{
			String p[]=line.split("\\s+");
			
			symtab.add(new Row(p[1],Integer.parseInt(p[2])));
		}
		while((line=lt.readLine())!=null)
		{
			String p[]=line.split("\\s+");

			symtab.add(new Row(p[0],Integer.parseInt(p[1])));
		}
		
		while((line=br.readLine())!=null)
		{
			String [] parts=line.split("\\s+");
			//System.out.println(parts.length);
			if((parts[0].contains("AD"))||(parts[0].contains("(DL,02)")))
			{
				bw.write("\n");
				continue;
			}
			else if(parts.length==2)
			{
				if(parts[0].contains("DL"))
				{
					parts[1]=parts[1].replaceAll("[^0-9]", "");
					String code="00\t0\t"+String.format("%03d", Integer.parseInt(parts[1]));
					bw.write(code);
				}
				else if(parts[0].contains("IS"))
				{
					int opcode=Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
					System.out.println(opcode);
					if(parts[1].contains("S"))
					{
						int sybindex=Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
						String code=opcode+"\t0\t"+String.format("%03d", symtab.get(sybindex-1).getAddress());
						bw.write(code);
					
					}
					if(parts[1].contains("L"))
					{
						int sybindex=Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
						String code=opcode+"\t0\t"+String.format("%03d", littab.get(sybindex-1).getAddress());
						bw.write(code);

					}
				}
			}
			else if(parts.length==1)
			{
				String code="00\t0\t000";
				bw.write(code);
			}
	
			else if((parts[0].contains("IS"))&&(parts.length==3))
			{
				int opcode=Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
				//System.out.println(opcode);
				int reg=Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
				//System.out.println(reg);
				if(parts[2].contains("S"))
				{
					int sybindex=Integer.parseInt(parts[2].replaceAll("[^0-9]", ""));
					String code=String.format("%02d", opcode)+"\t"+reg+"\t"+String.format("%03d",symtab.get(sybindex-1).getAddress());
					bw.write(code);
				}
				if(parts[2].contains("L"))
				{
					int sybindex=Integer.parseInt(parts[2].replaceAll("^[0-9]", ""));
					String code=String.format("%02d", opcode)+"\t"+reg+"\t"+String.format("%03d",littab.get(sybindex-1).getAddress());
					bw.write(code);
				}
			}
			bw.write("\n");
		}
		br.close();
		bw.close();
		st.close();
		lt.close();
	}
	
}
