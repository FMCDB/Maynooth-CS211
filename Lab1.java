import java.util.*;
public class Lab1
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your sentence: ");
		String s = sc.nextLine();
		int[] ASCIIarray = new int[256];
		for(int i = 0; i < s.length(); i++)
		{
			int letter = (int)s.charAt(i);
			ASCIIarray[letter] += 1;
			String bin = Integer.toBinaryString(letter);
			System.out.print(bin+" ");
		}
		System.out.println();
		for(int i = 0; i < ASCIIarray.length; i++)
		{
			if(ASCIIarray[i] != 0)
			{
				int count = ASCIIarray[i];
				char letter = (char) i;
				if(count > 1) System.out.println("'"+letter+"' appeared "+count+" times");
				else System.out.println("'"+letter+"' appeared "+count+" time");
			}
		}
	}
}