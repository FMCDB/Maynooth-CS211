import java.util.*;
import java.io.*;
import javax.sound.sampled.*;
public class Lab5
{
	public static void tone(int hz, int msecs, double vol) throws LineUnavailableException{
		float SAMPLE_RATE = 8000f;
		byte[] buf = new byte[1];
		AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,true,false);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl.open(af);
		sdl.start();
		for (int i=0; i < msecs*8; i++) {
		double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
		buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
		sdl.write(buf,0,1);
		}
		sdl.drain();
		sdl.stop();
		sdl.close();
	}

	public static void code(char c)
	{
		try
		{
			File openFile = new File("codes.txt");
	        Scanner sc = new Scanner(openFile);
	        boolean flag = false;
	        while(sc.hasNextLine() && flag == false)
	        {
	        	String line[] = sc.nextLine().split("");
	        	if(line[0].charAt(0) == c)
	        	{
	        		for(int x = 1; x<line.length; x++)
	        		{
	        			try
	        			{
							if(line[x].equals("1")) tone(500,50,10);
		        			else if(line[x].equals("0")) tone(500,150,10);
		        			if(x != line.length-1) tone(500,50,0);
	        			}
	        			catch (LineUnavailableException e)
						{
							System.out.println("Line Unavailable");
						}
	        		}
	        		flag = true;
	        	}
	        }
		}
		catch (FileNotFoundException ex) {
	      System.out.println("Can't find the file");
	      ex.printStackTrace();
	    }
	}

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		String words[] = sc.nextLine().split(" ");
		try
		{
			for(int i = 0; i<words.length; i++)
			{
				char letters[] = words[i].toCharArray();
				for(int j = 0; j<letters.length; j++)
				{
					code(letters[j]);
					tone(500,150,0);
				}
				tone(500,350,0);
			}
		}
		catch (LineUnavailableException e)
		{
			System.out.println("Line Unavailable");
		}
	}
}
