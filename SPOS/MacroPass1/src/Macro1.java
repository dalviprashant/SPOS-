import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Macro1 {
	public static Scanner scan=new Scanner(System.in);
	public static void main(String [] args) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("macro"));
		BufferedWriter mdt=new BufferedWriter(new FileWriter("mdt"));
		BufferedWriter mnt=new BufferedWriter(new FileWriter("mnt"));

		BufferedWriter kpt=new BufferedWriter(new FileWriter("kpdtab"));

		BufferedWriter ptab=new BufferedWriter(new FileWriter("pntab"));
		LinkedHashMap<String, Integer> pntab=new LinkedHashMap<String, Integer>();
		mnt.write("Macro\t#PP\t#KP\t#MDTP\t#KPDTP\n");
		String line=null,Macroname=null;
		int kpCount=0;
		int pnCount=0;
		int mdtp=1;
		int kpdtp=0;
		int ppC=0;
		int flag=0;
		while((line=br.readLine())!=null)
		{
			String parts[]=line.split("\\s+");
			if(parts[0].equals("MACRO"))
			{
				flag=1;
				line=br.readLine();
				String part[]=line.split("\\s+");
				Macroname=part[0];
				for(int i=1;i<part.length;i++)
				{
					part[i]=part[i].replaceAll("[&,]", "");
					if(part[i].contains("="))
					{
						kpCount++;
						String kp[]=part[i].split("=");
						pntab.put(kp[0],++pnCount);
						if(kp.length<2)
						{
							kpt.write(kp[0]+"\t-\n");
							
						}
						else
						{
							kpt.write(kp[0]+"\t"+kp[1]+"\n");
						}
						
					}
					else
					{
						ppC++;
						pntab.put(part[i],++pnCount);
					}
				}
				mnt.write(Macroname+"\t"+ppC+"\t"+kpCount+"\t"+mdtp+"\t"+(kpCount==0?kpdtp:(kpdtp+1)));
				mnt.write("\n");
				kpdtp+=kpCount;
				
			}
			else if(parts[0].equals("MEND"))
			{
				flag=0;
				pnCount=0;
				mdtp++;
				ppC=0;
				kpCount=0;
				mdt.write(parts[0]+"\n");
				ptab.write(Macroname+":\n");
				for(Map.Entry m:pntab.entrySet())
				{
					ptab.write(m.getKey()+"\n");
				}
				pntab.clear();
				
			}
			else if(flag==1)
			{
				for(int i=0;i<parts.length;i++)
				{
					parts[i]=parts[i].replaceAll("[,]","");

					if(parts[i].contains("&"))
					{
						String pp[]=parts[i].split("&");
						pp[1].replaceAll(",", "");
						mdt.write("(P,"+pntab.get(pp[1])+")\t");
					}
					else 
					{
						mdt.write(parts[i]+"\t");
					}
					
				}
				mdt.write("\n");

				mdtp++;
			}
		}
		for(Map.Entry m:pntab.entrySet())
		{
			System.out.println(m.getKey()+"\t "+m.getValue());
		}
		br.close();
		mdt.close();
		kpt.close();
		ptab.close();
		mnt.close();
		
		
	}
}
