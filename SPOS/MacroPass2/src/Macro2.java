import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Macro2 {
	public static void main(String [] args) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("mdt"));
		BufferedReader ic=new BufferedReader(new FileReader("IC"));
		BufferedReader kptab=new BufferedReader(new FileReader("kpdtab"));
		BufferedReader mntf=new BufferedReader(new FileReader("mnt"));
		
		BufferedWriter bw=new BufferedWriter(new FileWriter("pass2"));
		HashMap<Integer,String> aptab=new HashMap<Integer, String>();
		HashMap<String,Integer>aptabInverse=new HashMap<String, Integer>();
		HashMap<String, MNTEntry> mnt = new HashMap<String, MNTEntry>();
		Vector<String> kptab_vector=new Vector<String>();
		Vector<String> mdt=new Vector<String>();
		String line=mntf.readLine();
		while((line=mntf.readLine())!=null)
		{
			String [] parts=line.split("\\s+");
			mnt.put(parts[0],new MNTEntry(parts[0],Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),Integer.parseInt(parts[3]),Integer.parseInt(parts[4])));
			
		}
		while((line=br.readLine())!=null)
		{
			mdt.add(line);
		}
		while((line=kptab.readLine())!=null)
		{
			kptab_vector.add(line);
		}
		int pp=0,kp=0,kpdtp=0,mdtp=0;
		while((line=ic.readLine())!=null)
		{
			String [] parts=line.split("\\s+");
			if(mnt.containsKey(parts[0]))
			{
				pp=mnt.get(parts[0]).getPp();
				kp=mnt.get(parts[0]).getKp();
				kpdtp=mnt.get(parts[0]).getKpdtp();
				mdtp=mnt.get(parts[0]).getMdtp();
				int ppCount=1;
				for(int i=0;i<pp;i++)
				{
					parts[ppCount]=parts[ppCount].replace(",", "");
					aptab.put(ppCount,parts[ppCount]);
					aptabInverse.put(parts[ppCount],ppCount);
					ppCount++;
					
				}
				for(int i=0;i<kp;i++)
				{
					String ll[]=kptab_vector.get(kpdtp-1+i).split("\t");
					aptab.put(ppCount,ll[1]);
					aptabInverse.put(ll[0],ppCount);

					ppCount++;
				}
				for(int i=pp+1;i<parts.length;i++)
				{
					String spp[]=parts[i].split("=");
					spp[0]=spp[0].replace("&", "");
					System.out.println(spp[0]);
					spp[1]=spp[1].replace(",", "");
		
					aptab.put(aptabInverse.get(spp[0]),spp[1]);
					
				}
				int i=mdtp-1;
				while(!mdt.get(i).equals("MEND"))
				{
					bw.write("+");
					String p1[]=mdt.get(i).split("\\s+");
					for(int k=0;k<p1.length;k++)
					{
						if(p1[k].contains("(P,"))
						{
							p1[k]=p1[k].replaceAll("[^0-9]", "");
							System.out.println(p1[k]);
							bw.write(aptab.get(Integer.parseInt(p1[k]))+"\t");
						}
						else
						{
							bw.write(p1[k]+"\t");
						}
					}
					bw.write("\n");
					i++;
				}
				bw.write("\n\n");
				
			}
			for(Map.Entry m:aptab.entrySet())
			{
				System.out.println(m.getKey()+"\t"+m.getValue());
			}
			aptab.clear();
			aptabInverse.clear();
			System.out.println("\n\n");
			
		}
	
		br.close();
		ic.close();
		mntf.close();
		bw.close();
	}
	
}
