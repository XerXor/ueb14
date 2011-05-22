package base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

/**
 * Allows to gernarte a testfile
 * Allowed names 403291461126605635584000000=26!;
 * @author Daniel
 *
 */
public class Testfilegenerator {

	private enum characters
	{
		A,B,C,D,E,
		F,G,H,I,J,
		K,L,M,N,O,
		P,Q,R,S,T,
		U,V,W,X,Y,
		Z;
		
	}
	
	/**
	 * Erstelle eine Datei
	 * mit genau der Anzahl der namen
	 * @param file
	 * @param names (anzahl der namen)
	 */
	public void Testfilegenerator(String file,BigInteger names)
	{

	}
	
	public void generateFile(String pathtofile,BigInteger names) throws IOException
	{
		if (pathtofile==null) throw new IllegalArgumentException();
		if (names==null) throw new IllegalArgumentException();
		File file = new File(pathtofile);
		if (!file.exists()) file.createNewFile();
		if (file.exists())
		{	
		 if (file.isFile())
		 {
			 if (file.canRead())
			{
				FileWriter filewriter = new FileWriter(file);
				BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
				for (BigInteger i=BigInteger.ZERO;i.compareTo(names)==-1;i=i.add(BigInteger.ONE))
				{
					String name = "";
					name = zufallsname(25);
					bufferedwriter.write("+");
					bufferedwriter.write(name);
					bufferedwriter.newLine();
					bufferedwriter.flush();
					System.out.println("Write "+ i + ". line +" + name + "into file.(" + i + "\\" + names +")");
				}
				bufferedwriter.close();
				filewriter.close();
			}
		 }
		}
	}
	
	public void generateAlternatingFile(String pathtofile,BigInteger names) throws IOException
	{
		if (pathtofile==null) throw new IllegalArgumentException();
		if (names==null) throw new IllegalArgumentException();
		File file = new File(pathtofile);
		if (!file.exists()) file.createNewFile();
		if (file.exists())
		{	
		 if (file.isFile())
		 {
			 if (file.canRead())
			{
				FileWriter filewriter = new FileWriter(file);
				BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
				int o=-1;
				for (BigInteger i=BigInteger.ZERO;i.compareTo(names)==-1;i=i.add(BigInteger.ONE))
				{
				
					String name = "";
					String operation="";
					if (o==-1) 
					{
						operation="+";
						o=1;
					}
					else
					{
						operation="-";
						o=-1;
					}
					name = zufallsname(25);
					bufferedwriter.write(operation);
					bufferedwriter.write(name);
					bufferedwriter.newLine();
					bufferedwriter.flush();
					System.out.println("Write "+ i + ". line "+operation+ " " + name + "into file.(" + i + "\\" + names +")");
				}
				bufferedwriter.close();
				filewriter.close();
			}
		 }
		}
	}
	
	/**
	 * Erstellt einen zufallsnamen der laenge x.
	 * @return
	 * @throws IllegalArgumentException
	 */
	public String zufallsname(int length) throws IllegalArgumentException
	{
		if (length<0) throw new IllegalArgumentException("Laenge muss groesser als 0 sein.");
		StringBuilder stringbuilder = new StringBuilder();
		for (int i = 0;i<length;i++)
		{
		stringbuilder.append(characters.values()[erstelleZufallsZahl()].name());
		}
		return stringbuilder.toString();
	}
	
	 public int erstelleZufallsZahl()
	 {
	        Random zufallsgenerator = new Random();
	        int zahl = zufallsgenerator.nextInt(25);
	        return zahl;        
	 }
	 
	 public static void main(String[] args) {
		Testfilegenerator tg = new Testfilegenerator();
		BigInteger bi = new BigInteger("1000000");
		try {
			tg.generateFile("greatefile+",bi );
			tg.generateAlternatingFile("greatefile+-",bi );
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
