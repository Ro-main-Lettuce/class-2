import java.util.Arrays;


/**
 * 
 * Autocomplete for a search engine.
 * An array is made of terms which is then made into another array
 * This is then sorted into "matcha" array
 * 
 */
public class Autocomplete {

   private Term[] terms;

   

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
   public Autocomplete(Term[] terms) {
      if(terms == null){
         throw new NullPointerException();}
      Term[] copy = new Term[terms.length];
      copy = Arrays.copyOf(terms, copy.length);
      Arrays.sort(copy);
      matcha = copy;
      
   
   }
  
	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
  	 * matcha is matches
	 */
   public Term[] allMatches(String prefix) {
      if (prefix == null) {
         throw new NullPointerException();
      }
   
      Term prefixTerm = new Term(prefix, 0);
      int firstIndex = BinarySearch.firstIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));
      int lastIndex = BinarySearch.lastIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));
   
      if (firstIndex == -1 || lastIndex == -1) {
         return new Term[0];
      }
   
      Term[] matcha = Arrays.copyOfRange(terms, firstIndex, lastIndex + 1);
      Arrays.sort(matcha, Term.byDescendingWeightOrder());
      return matcha;
   }


}

   
   


