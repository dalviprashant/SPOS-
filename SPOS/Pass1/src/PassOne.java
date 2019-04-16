import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PassOne {

	public static void main(String [] args) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("input.asm"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("IC.txt"));
		BufferedWriter lit=new BufferedWriter(new FileWriter("literal.txt"));
		BufferedWriter sym=new BufferedWriter(new FileWriter("symbol.txt"));
		BufferedWriter pool=new BufferedWriter(new FileWriter("pool.txt"));
		
		Hashtable<String, String> ad = new Hashtable<String, String>();
		Hashtable<String, String> dl = new Hashtable<String, String>();
		Hashtable<String, String> is = new Hashtable<String, String>();
		LinkedHashMap<String , String >symtab = new LinkedHashMap();
		List<String > ll= new ArrayList<String>();
		LinkedHashMap<String , String >littab = new LinkedHashMap();
		List<String > ll1= new ArrayList<String>();
		
		ad.put("START", "01");
		ad.put("END","02");
		ad.put("ORIGIN", "03");
		ad.put("EQU","04");
		ad.put("LTORG", "05");
		is.put("STOP","0");
		is.put("ADD","1");
		is.put("SUB","2");
		is.put("MULT","3");
		is.put("MOVER","4");
		is.put("MOVEM","5");
		is.put("COMP","6");
		is.put("BC","7");
		is.put("DIV","8");
		is.put("READ","9");
		is.put("PRINT","10");
		dl.put("DC", "01");
		dl.put("DS", "02");
		String line;
		String parts[]=br.readLine().split("\\s+");
		int lc=0;
		int symptr=1;
		int last=1;
		int litptr=1;
		if(parts[1].equals("START"))
		{
			bw.write("(IS,01)\t");
			bw.write("(C,"+parts[2]+")\n");
			lc=Integer.parseInt(parts[2]);
		}
		while((line=br.readLine())!=null)
		{
			String type=null;
			String part[]=line.split("\\s+");
			if(part[0].length()>0)
			{
				symtab.put(part[0],Integer.toString(lc));
				ll.add(part[0]);
				symptr++;
			}
			for(Map.Entry m: is.entrySet())
			{
				if(part[1].equals(m.getKey()))
				{
					type="is";
					System.out.println("IS");
					bw.write("(IS,"+m.getValue()+")\t");
					lc++;
				}
				
			
			}
			for(Map.Entry m: ad.entrySet())
			{
				if(part[1].equals(m.getKey()))
				{
					type="ad";
					System.out.println("AD");
					bw.write("(AD,"+m.getValue()+")\t");
				}
			}
			if(part[1].equals("EQU"))
			{
				String pp1[]=part[2].split("\\+");
				int addr=Integer.parseInt(symtab.get(pp1[0]));
				symtab.put(part[0],Integer.toString(addr+Integer.parseInt(pp1[1])));
				bw.write("(S,"+Integer.toString(1+ll.indexOf(pp1[0]))+")+"+pp1[1]);
			}
			for(Map.Entry m: dl.entrySet())
			{
				if(part[1].equals(m.getKey()))
				{
					System.out.println("DL");
					type="dl";
					bw.write("(DL,"+m.getValue()+")\t");
					bw.write("(C,"+part[2]+")");
					if(part[1].equals("DS"))
					{
						
						lc+=Integer.parseInt(part[2]);
						System.out.println(lc);
					}
					else
						lc++;
				}
				
			}
			if(part[1].equals("LTORG"))
			{
				pool.write("#"+last+"\n");
				for(Map.Entry m: littab.entrySet())
				{
					if(m.getValue().equals("-"))
					{
						System.out.println(m.getKey());
						litptr++;
						littab.put((String) m.getKey(),Integer.toString(lc));
						lit.write(m.getKey()+"\t"+m.getValue());
						lit.write("\n");
						lc++;
						bw.write("(DL,02)\t(C,"+((String) m.getValue())+")\n");
					}
				}
				last=litptr;
				
				littab.clear();
			}
			if(part[1].equals("END"))
			{
				pool.write("#"+last+"\n");
				for(Map.Entry m: littab.entrySet())
				{
					if(m.getValue().equals("-"))
					{
						litptr++;
						System.out.println(m.getKey());
						littab.put((String) m.getKey(),Integer.toString(lc));
						lit.write(m.getKey()+"\t"+m.getValue());
						lit.write("\n");
						lc++;
						bw.write("(DL,02)\t(C,"+((String) m.getValue())+")\n");

					}
				}
			}
			if(part[1].equals("ORIGIN"))
			{
				String pp[]=part[2].split("\\+");
				int add=Integer.parseInt(pp[1]);
				int addr=Integer.parseInt(symtab.get(pp[0]));
				bw.write("(S,"+Integer.toString(ll.indexOf(pp[0]))+")+"+add);
				
			}
			if(part[1].equals("STOP") || part[1].equals("PRINT"))
			{
				if(part[1].equals("PRINT"))
				bw.write("(S,"+Integer.toString(1+ll.indexOf(part[2]))+")");
				bw.write("\n");
				continue;
			}
			
			if((type.equals("is"))&&(part[2].length()>0))
			{
				switch(part[2])
				{
				case "AREG,":
					bw.write("(R,1)\t");
					break;

				case "BREG,":
					bw.write("(R,2)\t");
					break;

				case "CREG,":
					bw.write("(R,3)\t");
					break;

				case "DREG,":
					bw.write("(R,4)\t");
					break;
					
				}
				if(part[3].length()>0)
				{
					if(!part[3].contains("="))
					{
						symtab.put(part[3],"");
						ll.add(part[3]);
						bw.write("(S,"+Integer.toString(1+ll.indexOf(part[3]))+")");

					}
					else
					{
						littab.put(part[3],"-");
						ll1.add(part[3]);
						bw.write("(L,"+Integer.toString(1+ll1.indexOf(part[3]))+")");
					}
				}
			}
			
			bw.write("\n");
		}

		System.out.println("SYMBOL TABLE");
		for(Map.Entry m:symtab.entrySet())
		{
			System.out.println(m.getKey()+"\t"+m.getValue());
			sym.write(m.getKey()+"\t"+m.getValue());
			sym.write("\n");
			
		}
		System.out.println("LITERAL TABLE");
		for(Map.Entry m:littab.entrySet())
		{
			System.out.println(m.getKey()+"\t"+m.getValue());
		}
		
		bw.close();
		lit.close();
		sym.close();
		pool.close();
		
		
	}
}
