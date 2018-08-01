public class Lab3
{
	public static String[] theArray = {"one","two","three","four","five","six","seven","eight","nine","ten"};

	public static void recQuickSort(int left, int right) {

		 if(right-left <= 0) // if size <= 1,
		 return; // already sorted
		 else{ // size is 2 or larger

			 String pivot = theArray[right]; // rightmost item
			 // partition range
			 int partition = partitionIt(left, right, pivot);
			 recQuickSort(left, partition-1); // sort left side
			 recQuickSort(partition+1, right); // sort right side
		 }
	}

	public static int partitionIt(int left, int right, String pivot){

	 int leftPtr = left-1; // left (after ++)
	 int rightPtr = right; // right-1 (after --)
	 while(true)
	 {
		 while( compare(theArray[++leftPtr],pivot) < 0 ){} // scan to the right
		 while(rightPtr > 0 && compare(theArray[--rightPtr],pivot) > 0){}
		// scan to the left

		 if(leftPtr >= rightPtr) // if pointers cross,
		 break; // partition done
		 else
		 {
		 	swap(leftPtr, rightPtr,theArray); // swap elements
		 	} // not crossed, so
	 }
	 swap(leftPtr, right,theArray); // swap pivot into correct place
	 return leftPtr; // return pivot location
	}


	public static void swap(int a, int b, String theArray[])
	{
		String temp = theArray[a];
		theArray[a] = theArray[b];
		theArray[b] = temp;
	}

	public static int compare(String a, String b)
	{
		int largestA = 0, largestB = 0;
		for(int i = 0; i < a.length(); i++)
		{
			if((int)a.charAt(i) > largestA) largestA = (int)a.charAt(i);
		}
		for(int i = 0; i < b.length(); i++)
		{
			if((int)b.charAt(i) > largestB) largestB = (int)b.charAt(i);
		}
		if(largestA > largestB) return 1;
		else if(largestA < largestB) return -1;
		else
		{
			return a.compareTo(b);
		}
	}

	public static void main(String args[])
	{
		recQuickSort(0,theArray.length-1);
		for(int i=0;i<theArray.length;i++)
		{
			System.out.println(theArray[i]);
		}
	}
}