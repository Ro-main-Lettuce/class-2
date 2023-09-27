import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (Professor, provide course code minus some methods)
 * @author Ro-main_Letuce
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
  
    

   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
      if (element == null || contains(element)) {
         return false;
      }
   
   
      Node n = new Node(element);
      Node current = front;
      
      while (current != null && current.element.compareTo(element) < 0) {
         current = current.next;
      }
      if (current == null) {
         n.prev = rear;
         if(rear != null){
            rear.next = n;
         }
         else{
            front = n;
         }
         rear = n;
      }
      else{
         if(current.prev != null){
            current.prev.next = n;
         }
         else{
            front = n;
         }
         n.prev = current.prev;
         n.next = current;
         current.prev = n;
      }
      size++;
      return true;
   }
                 
         
   
         /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
      if(isEmpty()){
         return false;
      }
      if(element == null){
         throw new NullPointerException();
      }
      Node p = front;
      while(p != null && p.element.compareTo(element) < 0){
         p = p.next;
      }
      if(p != null && p.element.equals(element)){
         if(p.prev == null){
            front = p.next;
         }
         else {
            p.prev.next = p.next;
         }
         if(p.next == null){
            rear = p.prev;
         }
         else{ 
            p.next.prev = p.prev;
         }
         size--;
         return true;
      }
      return false;
   }
      
       
     
     


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
      if(isEmpty()) {
         return false;
      }
      if (front == null){
         return false;
      }
      Node n = front;
      while (n != null){
         if(n.element.equals(element)){
            return true;
         }
         n = n.next;
      }
      return false;
      
   }
    
   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      if (s == null || size() != s.size()) {
         return false;
      }
   
     
      
    // Check if this set contains all elements in s
      for (T element : s) {
         if (!contains(element)) {
            return false;
         }
      }
   
    // Check if s contains all elements in this set
      for (T element : this) {
         if (!s.contains(element)) {
            return false;
         }
      }
   
      return true;
   } 

   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   private Node getFront(){
      return front;
   }
   public boolean equals(LinkedSet<T> s) {
      if (s == null || size() != s.size()) {
         return false;
      }
   
      Node thisNode = front;
      Node notNode = s.getFront();
   
      while (thisNode != null && notNode != null) {
         if (!thisNode.element.equals(notNode.element)) {
            return false;
         }
         thisNode = thisNode.next;
         notNode = notNode.next;
      }
   
      return true;
   }  
   

   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){
      if( s == null){
         throw new NullPointerException();
      }
      LinkedSet<T> returnS = new LinkedSet<T>();
      Node q = front;
      while(q!=null){
         returnS.add(q.element);
         q = q.next;
      }
      Iterator<T> i = s.iterator();
      while( i.hasNext()){
         returnS.add(i.next());
      }
      return returnS;
   }
      
   


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
      if( s == null){
         throw new NullPointerException();
      }
      LinkedSet<T> returnS = new LinkedSet<T>();
      Node q = front;
      while(q!=null){
         returnS.add(q.element);
         q = q.next;
      }
      Iterator<T> i = s.iterator();
      while( i.hasNext()){
         returnS.add(i.next());
      }
      return returnS;
   }
   


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      if(s == null){
         throw new NullPointerException();
      }
      LinkedSet<T> returnS = new LinkedSet<T>();
      Node q = front;
      while(q!=null){
         if(s.contains((T)q.element)){
            returnS.add((T)q.element);
         }
         q = q.next;
      }
      return returnS;
   
   }


   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      if(s == null){
         throw new NullPointerException();
      }
      LinkedSet<T> returnS = new LinkedSet<T>();
      Node q = front;
      while(q!=null){
         if(s.contains((T)q.element)){
            returnS.add((T)q.element);
         }
         q = q.next;
      }
      return returnS;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      Set<T> set = new LinkedSet<T>();
   
      Iterator<T> iter1 = this.iterator();
   
      while (iter1.hasNext()) {
         set.add(iter1.next());
      }  
   
      Iterator<T> iter2 = s.iterator();
   
      while (iter2.hasNext()) {
         set.remove(iter2.next());
      }
      return set;
   }      

   


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      if(s == null){
         throw new NullPointerException();
      }
      LinkedSet<T> returnS = new LinkedSet<T>();
      Node q = front;
      while(q!=null){
         if(s.contains((T)q.element)){
            returnS.add((T)q.element);
         }
         q = q.next;
      }
      return returnS;
   }



   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      return new LinkedIterator();
   }
   private class LinkedIterator implements Iterator<T> {
      private Node current = front;
      public boolean hasNext(){
         return current != null;
      }
      public T next(){
         if(!hasNext())
            throw new NoSuchElementException();
         T result = current.element;
         current = current.next;
         return result;
      }
   }
   


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return new descendingIterator(rear);
   }
   private class descendingIterator implements Iterator<T> {
      Node t;
      public descendingIterator(Node rear){
         t = rear;
      }
      public boolean hasNext(){
         return t != null && t.element != null;
      }
      public T next(){
         if(t != null){
            T r = t.element;
            t = t.prev;
            return r;
         }
         return null;
      }
   }
   


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return new PowerSetIterator(front, size);
   }

   private class PowerSetIterator implements Iterator<Set<T>> {
      private Node current;
      private int size;
      private int count;
   
      public PowerSetIterator(Node front, int size) {
         this.current = front;
         this.size = size;
         this.count = 0;
      }
   
      @Override
      public boolean hasNext() {
         return count < Math.pow(2, size);
      }
   
      @Override
      public Set<T> next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
      
         Set<T> subset = new LinkedSet<>();
         int counter = count++;
      
         for (int i = 0; i < size; i++) {
            if ((counter & 1) == 1) {
               subset.add(current.element);
            }
         
            current = current.next;
            counter >>= 1;
         }
      
         current = front;
         return subset;
      }
   }   





   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.

   ////////////////////
   // Nested classes //
   ////////////////////

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
