import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DoubleEndList.java. This class implements DoubleEndedList.java.
 * 
 * @author   Anastasia Kotova (azk0108@auburn.edu)
 * @version  2019-10-07
 * @param <T> type variable
 */
 
public class DoubleEndList<T> implements DoubleEndedList<T> {
   private Node<T> head;
   private Node<T> tail;
   private int size;
   
   /*************************************************************************/
   /******************************CONSTRUCTORS*******************************/
   /*************************************************************************/

   /**
    * Constructor.
    *
    */
   public DoubleEndList() {
      head = null;
      tail = null;
      size = 0;
   }
   
   /*************************************************************************/
   /****************************SERVICE METHODS******************************/
   /*************************************************************************/
   
   /**
    * Adds element to the front of the list. If element is null,
    * this method throws an IllegalArgumentException.
    * 
    * @param element new element to add at front
    */
   public void addFirst(T element) {
      if (element == null) {
         throw new IllegalArgumentException("element is null");
      }
      Node<T> newnode = new Node<T>(element);
      if (isEmpty()) { // list is empty
         tail = newnode;
      }
      else {
         head.prev = newnode;
      }
      newnode.next = head;
      head = newnode;
      size++;
   }
   
   /**
    * Adds element to the end of the list. If element is null,
    * this method throws an IllegalArgumentException.
    * 
    * @param element new element to add at rear
    */
   public void addLast(T element) {
      if (element == null) {
         throw new IllegalArgumentException("element is null");
      }
      Node<T> newnode = new Node<T>(element);
      if (isEmpty()) { // list is empty
         head = newnode;
      }
      else {
         tail.next = newnode;
         newnode.prev = tail;
      }
      tail = newnode;
      size++;
   }
      
   /**
    * Delete and return the element at the front of the list.
    * If the list is empty, this method returns null.
    * 
    * @return deleted node at front
    */
   public T removeFirst() {
      if (isEmpty()) { // list is empty
         return null;
      }
      Node<T> oldnode = head;
      if (head == tail) { // one entry
         tail = null;
      }
      else {
         head.next.prev = null;
      }
      head = head.next;
      oldnode.next = null;
      size--;
      return oldnode.data;
   }
   
   /**
    * Delete and return the element at the end of the list.
    * If the list is empty, this method returns null.
    * 
    * @return deleted node at rear
    */
   public T removeLast() {
      if (isEmpty()) { // list is empty
         return null;
      }
      Node<T> oldnode = tail;
      if (head == tail) { // one entry
         head = null;
      }
      else {
         tail.prev.next = null;
      }
      tail = tail.prev;
      oldnode.prev = null;
      size--;
      return oldnode.data;
   }
   
   /**
    * Returns the number of elements in this list.
    * 
    * @return number of elements in the list
    */
   public int size() {
      return size;
   }
 
   /**
    * Returns true if this list contains no elements, false otherwise.
    * 
    * @return boolean value
    */
   public boolean isEmpty() {
      return size == 0;
   }
   
   /**
    * Creates and returns an iterator over the elements of this list.
    * 
    * @return Iterator object
    */
   public Iterator<T> iterator() {
      return new LinkedIterator();
   }
   
   /*************************************************************************/
   /*****************************NESTED CLASSES******************************/
   /*************************************************************************/
   
   /**
    * Node.java. A Java class for a node.
    * 
    * @param <T> type variable
    */
   private class Node<T> {
      private T data;
      private Node<T> next;
      private Node<T> prev;
   
   /**
    * Constructor.
    *
    */
      Node(T data) {
         this.data = data;
         next = null;
         prev = null;
      }
   } 
   
  /**
   * LinkedIterator.java. This class implements Iterator.java.
   * 
   */
   private class LinkedIterator implements Iterator<T> {
      private Node<T> curr = head;
      
    /**
     * Returns true if there is more node to be traversed.
     *
     * @return boolean value
     */
      public boolean hasNext() {
         return curr != null;
      }
      
    /**
     * Increments the iterated traversal.
     *
     * @return the next element in the list
     */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException("no elememts left to process");
         }
         T ret = curr.data;
         curr = curr.next;
         return ret;
      }
      
    /**
     * MUST NOT REMOVE ANY ELEMENT.
     *
     */
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
   
}