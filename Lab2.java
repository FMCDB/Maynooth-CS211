import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args){
       Scanner in = new Scanner(System.in);

       String sentence = in.nextLine();
       String binaryString="";      //this stores the string of binary code


       for(int i=0; i < sentence.length(); i++){        //go through the sentence
           int decimalValue = (int)sentence.charAt(i);      //convert to decimal
           String binaryValue = Integer.toBinaryString(decimalValue);      //convert to binary
           for(int j=7;j>binaryValue.length();j--){
               binaryString+="0";           //this loop adds in those pesky leading zeroes
            }
           binaryString += binaryValue+" "; //add to the string of binary
       }



       int[] array = new int[256];      //an array to store all the frequencies

       for(int i=0; i < sentence.length(); i++){    //go through the sentence
           array[(int)sentence.charAt(i)]++;    //increment the appropriate frequencies

       }


       PriorityQueue < Tree >  PQ = new PriorityQueue < Tree >() ;  //make a priority queue to hold the forest of trees


       for(int i=0; i<array.length; i++){ //go through frequency array
           if(array[i]>0){ //print out non-zero frequencies - cast to a char

               //FILL THIS IN:

               //MAKE THE FOREST OF TREES AND ADD THEM TO THE PQ

               //create a new Tree
               Tree t = new Tree();
               //set the cumulative frequency of that Tree
               t.frequency = array[i];
               //insert the letter as the root node
			   Node n = new Node();
			   n.letter=(char)i;
               n.smallestLetter=(char)i;
			   t.root = n;
               //add the Tree into the PQ
               PQ.add(t);
           }
        }

        /*while(PQ.size() != 0)
        {
        	System.out.println(PQ.poll().frequency);
        }*/


        while(PQ.size()>1){             //while there are two or more Trees left in the forest

            //FILL THIS IN:

            //IMPLEMENT THE HUFFMAN ALGORITHM

            //when you're making the new combined tree, don't forget to assign a default root node (or else you'll get a null pointer exception)
            //if you like, to check if everything is working so far, try printing out the letter of the roots of the two trees you're combining
            Tree t = new Tree();
			Tree t1 = PQ.poll();
			Tree t2 = PQ.poll();


			int n1 = t1.frequency, n2 = t2.frequency;
			char c1 = t1.root.letter, c2 = t2.root.letter;
            t.frequency = n1 + n2;
            Node n = new Node();
            n.smallestLetter = (char)Math.min(t1.root.smallestLetter,t2.root.smallestLetter);
            n.leftChild = t1.root;
            n.rightChild = t2.root;
            t.root = n;
            PQ.add(t);
        }


        Tree HuffmanTree = PQ.poll();   //now there's only one tree left - get its codes
        //System.out.println(HuffmanTree.root.rightChild.leftChild.letter);


        //FILL THIS IN:

        //get all the codes for the letters and print them out
        //call the getCode() method on the HuffmanTree Tree object for each letter in the sentence
        for(int i = 0;i<sentence.length();i++)
        {
        	System.out.print(HuffmanTree.getCode(HuffmanTree.root,sentence.charAt(i),""));
        }

        //print out all the info

    }
}

class Node
   {

   public char letter='@';            //stores letter
   public char smallestLetter='@';

   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

}  // end class Node

class Tree implements Comparable<Tree>
   {
   public Node root;             // first node of tree
   public int frequency=0;

// -------------------------------------------------------------
   public Tree()                  // constructor
      {   root = null; }            // no nodes in tree yet
// -------------------------------------------------------------

//the PriorityQueue needs to be able to somehow rank the objects in it
//thus, the objects in the PriorityQueue must implement an interface called Comparable
//the interface requires you to write a compareTo() method so here it is:

   public int compareTo(Tree object){ //
       if(frequency-object.frequency>0){ //compare the cumulative frequencies of the tree
           return 1;
        }else if(frequency-object.frequency<0){
           return -1;   //return 1 or -1 depending on whether these frequencies are bigger or smaller
        }else{
        	char a = this.root.smallestLetter;
        	char b = object.root.smallestLetter;
        	if(a>b)
            	return 1;
            else if(a<b)
            	return -1;
            else
            	return 0;
        }
   }

// -------------------------------------------------------------

   //String path="error";     //this variable will track the path to the letter we're looking for

   public String getCode(Node n,char letter,String path){  //we want the code for this letter

       //FILL THIS IN:

       //How do you get the code for the letter? Maybe try a traversal of the tree
       //Track the path along the way and store the current path when you arrive at the right letter


       if(n != null)
       {
			if(n.letter == letter) return path;
			String p1 = getCode(n.leftChild,letter,path+"0");
			if(p1 != null) return p1;
			else return getCode(n.rightChild,letter,path+"1");
       }
       else	return null;
   }

}  // end class Tree