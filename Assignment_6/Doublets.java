
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Your Name (you@auburn.edu)
 */
public class Doublets implements WordLadderGame {
	private Object element;
	private Node next;
	private TreeSet<String>  lexicon;
	private int count;
	private boolean bo;
	private int length;
	private int Slength;
	private int hamDist;
	private List<String> neighbors;
	private List<String> test;
	private TreeSet<String> dummy;
    private ArrayDeque<String> validSequence;

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
    /////////////////////////////////////////////////////////////////////////////
    // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
    // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
    // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
    // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
    // table with chaining).
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public Doublets(InputStream in) {
        try {
            //////////////////////////////////////
            // INSTANTIATE lexicon OBJECT HERE  //
            //////////////////////////////////////
        	lexicon = new TreeSet<>();
            Scanner s =
                new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();
                /////////////////////////////////////////////////////////////
                // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
                /////////////////////////////////////////////////////////////
                lexicon.add(str.toLowerCase());
                s.nextLine();
            }
            in.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }


    //////////////////////////////////////////////////////////////
    // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
    //////////////////////////////////////////////////////////////
    /**
     * Returns the total number of words in the current lexicon.
     *
     * @return number of words in the lexicon
     */
    public int getWordCount() {
    	return lexicon.size();
    }
    /**
     * Checks to see if the given string is a word.
     *
     * @param  str the string to check
     * @return     true if str is a word, false otherwise
     */
    public boolean isWord(String str) {
    	if(lexicon.contains(str.toLowerCase())) {
    		return true;
    	}
    	return false;
    }
    /**
     * Returns the Hamming distance between two strings, str1 and str2. The
     * Hamming distance between two strings of equal length is defined as the
     * number of positions at which the corresponding symbols are different. The
     * Hamming distance is undefined if the strings have different length, and
     * this method returns -1 in that case. See the following link for
     * reference: https://en.wikipedia.org/wiki/Hamming_distance
     *
     * @param  str1 the first string
     * @param  str2 the second string
     * @return      the Hamming distance between str1 and str2 if they are the
     *                  same length, -1 otherwise
     */
    public int getHammingDistance(String str1, String str2) {
    	int hamDist = 0;
    if(str1.length()!=str2.length()) {
    	return -1;
    }
    	for(int i = 0; i < str1.length();i++) {
    		if (str1.charAt(i) != str2.charAt(i)) {
    			hamDist++;	
    		}
    	}
		return hamDist;

    }
    
    
    
    /**
     * Returns all the words that have a Hamming distance of one relative to the
     * given word.
     *
     * @param  word the given word
     * @return      the neighbors of the given word
     */
    public List<String> getNeighbors(String word){
neighbors = new ArrayList<String>(); 
if(word == null) {
	return new ArrayList<String>();

}
for(String w : lexicon) {
	if(getHammingDistance(word, w)== 1) {
		neighbors.add(w);
	}
	
}
return neighbors;
    	
    }
    /**
     * Checks to see if the given sequence of strings is a valid word ladder.
     *
     * @param  sequence the given sequence of strings
     * @return          true if the given sequence is a valid word ladder,
     *                       false otherwise
     */
   public boolean isWordLadder(List<String> sequence) {
	   if(sequence == null || sequence.isEmpty()) {
		   return false;
	   }
	   for(int i = 0; i < sequence.size()-1; i++) {
		   if(isWord(sequence.get(i))!= true || isWord(sequence.get(i+1))!= true ||getHammingDistance(sequence.get(i),sequence.get(i+1)) != 1) {
			   return false;
		   }
	   }
	   return true;

   }


   /**
    * Returns a minimum-length word ladder from start to end. If multiple
    * minimum-length word ladders exist, no guarantee is made regarding which
    * one is returned. If no word ladder exists, this method returns an empty
    * list.
    *
    * Breadth-first search must be used in all implementing classes.
    *
    * @param  start  the starting word
    * @param  end    the ending word
    * @return        a minimum length word ladder from start to end
    */
    public List<String> getMinLadder(String start, String end){
    List<String> ladder = new ArrayList<String>();
    if(start.equals(end)) {
    	ladder.add(start);
    	return ladder;
    }
    else if(start.length() != end.length()) {
    	return new ArrayList<String>();
    }
    else if(!isWord(start) || !isWord(end)) {
    	return new ArrayList<String>();
    }
    	Deque<Node> queue = new ArrayDeque<>();
    	TreeSet<String> minLadder = new TreeSet<>();
    	minLadder.add(start);
    	queue.addLast(new Node(start, null));
    	while(!queue.isEmpty()) {
    		Node node = queue.removeFirst();
    		 String positon = node.position;
    		 for(String lad : getNeighbors(positon)) {
    			 if(!minLadder.contains(lad)) {
    				 minLadder.add(lad);
    				 queue.addLast(new Node(lad, node));
    			 }
    			 if(lad.equals(end)) {
    				 Node m = queue.removeLast();
    				 while(m != null) {
    					 ladder.add(0, m.position);
    					 m = m.next;
    				 }
    				 return ladder;
    			 }
    		 }
    	}
    	return new ArrayList<String>();
    }
    			 
    /**
     * Node class to easily keep track of the ladder progression.
     * @author rosalynnavarrete
     *
     */
    private class Node{
    	private String position;
    	 private Node next;
    public Node(String p, Node n) {
	position = p;
     next = n;
    }
    }

}