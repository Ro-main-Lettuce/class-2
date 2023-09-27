import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

   /**
    * Returns the index of the first key in a[] that equals the search key, 
    * or -1 if no such key exists. This method throws a NullPointerException
    * if any parameter is null.
    */
   public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
      if (a == null || key == null || comparator == null){
         throw new NullPointerException();}
      int left = 0, right = a.length-1;
      int start = -1;
      int end = -1;
      while (left <= right) {
         int middle = (right - left) / 2 +left;
         if (comparator.compare(a[middle], key) > 0){
            right = middle -1;}
         else if (comparator.compare(key, a[middle]) == 0){
            start = middle;
            right = middle - 1;}
         else
            left = middle + 1;
      }
      while (left <= right) {
         int middle = (right - left) /2 + left;
         if (comparator.compare(a[middle], key) > 0){
            right = middle -1;}
         else if (comparator.compare(key, a[middle]) == 0){
            end = middle;
            left = middle + 1;}
         else
            left = middle + 1;
      }
      if (start != -1 && end != -1){
         for(int i = 0;i<= a.length-1 ;i++){
            if(a[i] == key){
               return i;
            }
         }
      }
      
      return -1;
      
   }

   /**
    * Returns the index of the last key in a[] that equals the search key, 
    * or -1 if no such key exists. This method throws a NullPointerException
    * if any parameter is null.
    */
   public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
      if (a == null || key == null || comparator == null){
         throw new NullPointerException();}
      int left = 0, right = a.length-1;
      int start = -1;
      int end = -1;
      while (left <= right) {
         int middle = (right - left) /2 + left;
         if (comparator.compare(a[middle], key) > 0){
            right = middle -1;}
         else if (comparator.compare(key, a[middle]) == 0){
            start = middle;
            right = middle - 1;}
         else
            left = middle + 1;
      }
      while (left <= right) {
         int middle = (right - left) /2+left;
         if (comparator.compare(a[middle], key) > 0){
            right = middle -1;}
         else if (comparator.compare(key, a[middle]) == 0){
            end = middle;
            left = middle + 1;}
         else
            left = middle + 1;
      }
      for (int i = a.length-1; i >= 0; i--){
         if (a[i] == key){
            return i;
         }
      }
      return -1;
   }

}
