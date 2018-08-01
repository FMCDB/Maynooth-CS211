import java.util.*;
public class Lab7{
	public static void main (String[] args){
		FileIO reader = new FileIO();
 		String[] dictionary = reader.load("dictionary.txt");
 		for(int i = 0; i<dictionary.length; i++)
 		{
 			dictionary[i] = dictionary[i].replace("\n", "").replace("\r", "");
 		}
		//Scanner myscanner = new Scanner(System.in);
		int items = 49995;
  		//myscanner.nextLine();
		String[] contents = new String[items];
		for(int i=0;i<items;i++){
			contents[i]=dictionary[i];
		}
		HashTable mytable = new HashTable();
		Solution mysolution = new Solution(mytable);
		mysolution.fill(contents);
		mysolution = new Solution(mytable);
		for(int i=0;i<items;i++){
			int rand = (int)(Math.random()*items);
			String temp = contents[i];
			contents[i]=contents[rand];
			contents[rand]=temp;
		}
		for(int i=0;i<items;i++){
			int slot = mysolution.find(contents[i]);
			if(!mytable.check(slot,contents[i])){
				System.out.println("error!");
			}
		}
		System.out.println(mytable.gettotal());
	}
}

class HashTable{
	public int size = 99991;
	private String[] hashTable;
	private int total=0;

	public HashTable(){
		hashTable = new String[size];
		for(int i=0;i<size;i++){
		hashTable[i]="";
		}
	}

	public boolean check(int slot, String check){
		if(hashTable[slot].equals(check)){
			return true;
		}else{
			total++;
			return false;
		}
	}

	public void set(int slot, String word){
		hashTable[slot]=word;
	}

	public int gettotal(){
		return total;
	}
}

class Solution{
	HashTable mytable = new HashTable();

	public Solution(HashTable input){
		mytable = input;
		//this is the constructor
	}

	public int hash(String word){
		int unique = 0, index = 0;
		for(int j = 0; j<word.length(); j++)
		{
			int temp = (int)word.charAt(j)-96;
			unique += temp*Math.pow(3,j);
		}
		return unique;
	}

	public int find(String word){
		//fill this in so as to minimize collisions
		//this  method  should  return  the  slot  in  the  HashTable where the word is

		int unique = hash(word);
		int jump = 9001-(unique%9001);
		int index = unique%mytable.size;

		for(int i  = 0; i<mytable.size; i++)
		{
			if(mytable.check(index,"")){
				 return 0;
			}
			else if (mytable.check(index,word))
			{
				return index;
			}
			else if(!mytable.check(index,word))
			{
				index += jump;
				if(index > mytable.size) index = index % mytable.size;
			}
		}
		return 0;
	}




	public void fill(String[] array){
		//fill this in using some hashing strategy
		//this  should  add  all  the  words  in  the array  into  the HashTable
		for(int i= 0; i<array.length; i++)
		{

			boolean flag = true;
			int unique = hash(array[i]);
			int index = unique%mytable.size;
			int jump = 9001-(unique%9001);
			while(flag)
			{
				if(!mytable.check(index,"")){
					 index += jump;
					 if(index > mytable.size) index = index % mytable.size;
				}
				else
				{
					mytable.set(index,array[i]);
					flag = false;
				}
			}
		}
	}
}