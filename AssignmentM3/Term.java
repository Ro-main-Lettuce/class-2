import java.util.Comparator;

/**
 * Autocomplete term representing a (query, weight) pair.
 * 
 */
public class Term implements Comparable<Term> {
   private String queryln;
   private long weightln;
   public int compareTo(Term t1, Term t2){
      return t1.compareTo(t2);
   }
   /**
    * Initialize a term with the given query and weight.
    * This method throws a NullPointerException if query is null,
    * and an IllegalArgumentException if weight is negative.
    */
   public Term(String query, long weight) {
      if (query == null) {
         throw new NullPointerException();}
      if(weight < 0){
         throw new IllegalArgumentException();}
      queryln = query;
      weightln = weight;
   }

   /**
    * Compares the two terms in descending order of weight.
    */
   public static Comparator<Term> byDescendingWeightOrder() {
      return 
         new Comparator<Term>() {
            public int compare(Term t1, Term t2){
               return Long.compare(t2.weightln, t1.weightln);
            }
         
         };
   }

   /**
    * Compares the two terms in ascending lexicographic order of query,
    * but using only the first length characters of query. This method
    * throws an IllegalArgumentException if length is less than or equal
    * to zero.
    */
   public static Comparator<Term> byPrefixOrder(int length) {
      if(length <= 0){
         throw new IllegalArgumentException();}
      return 
         new Comparator<Term>() {
            public int compare(Term t1, Term t2){
               if ((t1.getQuery().length()-1 >= length) && (t2.getQuery().length()-1 >= length)) {
                  return ((t1.getQuery()).substring(0, length)).compareTo((t2.getQuery()).substring(0,length));
               }
               else if (t1.getQuery().length()-1 < length) {
                  return ((t1.getQuery()).substring(0,t1.getQuery().length())).compareTo((t2.getQuery()).substring(0,t1.getQuery().length()));
               }
               return ((t1.getQuery()).substring(0, t2.getQuery().length())).compareTo((t2.getQuery()).substring(0, t2.getQuery().length()));
               
            }
         };     
            
   }
   public int getLength(){
      return queryln.length();
   }
   public String getQuery(){
      return queryln;
   }
   public long getWeight(){
      return weightln;
   }

   /**
    * Compares this term with the other term in ascending lexicographic order
    * of query.
    */
   @Override
   public int compareTo(Term other) {
      return queryln.compareTo(other.queryln);
   }

   /**
    * Returns a string representation of this term in the following format:
    * query followed by a tab followed by weight
    */
   @Override
   public String toString(){
      String output = getQuery() + "\t" + getWeight();
      return output;
   }

}
