import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Ro-Main_Lettuce
* @author   Professor code template 
* Hendrix is creator of the blank file
* Student job was to create methods
* @version  24/01/23
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if((a == null)){
         throw new IllegalArgumentException();}
   
      if((a.length == 0)){
         throw new IllegalArgumentException();}
      int min = a[a.length-1];
               
      if(a.length == 1) {
         min = a[0];
      }
          
      for (int i = 0; i < a.length; i++) {
         if(a[i] < min) {
            min = a[i];
         }
                   
      
      }
   
      return min;
   }      


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a)throws NullPointerException  {
      if((a == null)){
         throw new IllegalArgumentException();}
   
      if((a.length == 0)){
         throw new IllegalArgumentException();}
   
      int max = a[a.length-1];
     
      if(a.length == 1) {
         max = a[0];
      }   
             
      for (int i = 0; i < a.length; i++) {
         if(a[i] > max) {
            max = a[i];
         }
      }
   
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k){
      if((a == null) || (a.length == 0) || (a.length < k)){
         throw new IllegalArgumentException();}
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);
      int count = 1;
      int i = 0;
      while ((count < k ) && (i < b.length-1)){
         if(b[i] != b[i++]) {
            count++;;
         }
      }
      if (count != k) {
         throw new IllegalArgumentException();}
    
      return b[i];
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      if((a == null) || (a.length == 0) || (a.length < k)){
         throw new IllegalArgumentException();}
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);
      int count = 1;
      int i = b.length-1;
      while ((count < k ) && (i > 0)){
         if(b[i] != b[i--]) {
            count= count++;
         }
      }
      if (count != k) {
         throw new IllegalArgumentException();}
    
      return b[i];
   }

   


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high)throws IllegalArgumentException {
      if((a == null)){
         throw new IllegalArgumentException();}
      if((a.length == 0)){
         throw new IllegalArgumentException();}
      int temp = 0;
   
      for(int i = 0; i <= a.length-1; i++){
         if((a[i] >= low) && (a[i] <= high)) {
            temp++;
         }
      }
         
      int[] b = new int[temp];
      for(int i = 0; i <= b.length -1; i++){
         if((a[i] >= low) && (a[i] <= high)) {
            b[i] = a[i];
         }
      
      }
      return b; 
   
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) throws IllegalArgumentException {
      if((a == null)){
         throw new IllegalArgumentException();}
      if((a.length == 0)){
         throw new IllegalArgumentException();}
      
      int trueKey = 0;
      if (key < 0){
         trueKey = key * -1;
      }
      int distance = 0;
      int ceiling = 0;
      
      for (int i = 0; i < a.length; i++) {
         if (key > 0){            
            if(a[i] == 0){
               distance = (a[i] - key);
            }
            else if (a[i] < 0){
               distance = (a[i] + key);
            }
            
            else 
            {
               distance = a[i] - key;
            }
         }
         else{
            if(a[i] == 0){
               distance = (a[i] - trueKey);
            }
            else if (a[i] < 0){
               distance = (a[i] + trueKey);
            }
               
            else 
            {
               distance = a[i] - key;
            }
            
         }
      
         if ((distance > 0) && (distance < 5)) {
            ceiling = a[i];
         }
         
         else if (a[i] == key) {
            ceiling = a[i];
         }
      
      }
      if(ceiling < key){
         throw new IllegalArgumentException();}
   
         
      return ceiling;
   
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) throws IllegalArgumentException{
   
      int floor = Integer.MIN_VALUE;
   
      if((a == null)){
         throw new IllegalArgumentException();}
      if((a.length == 0)){
         throw new IllegalArgumentException();}
     
      
      for (int i = 0; i <= a.length-1; i++) {
         
         if((a[i] <= key) && (a[i] >= floor)) {
            floor = a[i];
         }            
      
      }
    
      if(floor == Integer.MIN_VALUE){
         throw new IllegalArgumentException();}
      else{
         return floor;
      }
   
         
   
   }   }
