import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * RandomList.java. This class implements RandomizedList.java.
 * 
 * @author   Anastasia Kotova (azk0108@auburn.edu)
 * @version  2019-10-06
 * @param <T> type variable
 */
 
public class RandomList<T> implements RandomizedList<T> {
   // provide physical storage 
   private T[] elements;
   // the number of elements in the array
   private int size;
   /**
    * Defualt capacity is {@value}.
    *
    */
   private static final int DEFAULT_CAPACITY = 5;
   
   /*************************************************************************/
   /******************************CONSTRUCTORS*******************************/
   /*************************************************************************/
   
   /**
    * Constructor.
    *
    */
   @SuppressWarnings("unchecked")
   public RandomList() {
      elements = (T[]) new Object[DEFAULT_CAPACITY];
      size = 0;
   }
   
   /*************************************************************************/
   /****************************SERVICE METHODS******************************/
   /*************************************************************************/
   
   /**
    * Adds the specified element to this list. If the element is null, this
    * method throws an IllegalArgumentException.
    * 
    * @param element the element to be added
    */
   public void add(T element) {
   // element is null
      if (element == null) {
         throw new IllegalArgumentException("element is null");
      }
      // array is full
      if (isFull()) {
         resize(elements.length * 2);
      }
      elements[size++] = element; 
   }
  
   /**
    * Selects and removes an element selected uniformly at random from the
    * elements currently in the list. If the list is empty this method returns
    * null.
    * 
    * @return removed element from the collection
    */
   public T remove() {
      // array is empty 
      if (isEmpty()) {
         return null;
      }
      int index = search(sample());
      // unable to locate 
      if (index < 0) {
         throw new NoSuchElementException("unable to locate"); // handle this
      }
      // save for return 
      T ret = elements[index];
      // located, so remove
      elements[index] = elements[--size];
      elements[size] = null;
      if (size > 0 && size < elements.length / 4) {
         resize(elements.length / 2);
      }
      return ret;
   }
   
   /**
    * Selects but does not remove an element selected uniformly at random from
    * the elements currently in the list. If the list is empty this method
    * return null.
    * 
    * @return random element from the collection
    */
   public T sample() {
   // array is empty
      if (isEmpty()) {
         return null;
      }
      int randomNumber = new Random().nextInt(size);
      return elements[randomNumber];
   }
   
   /**
    * Returns the number of elements in this list.
    * 
    * @return the number of elements in the array
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
    * @return specified iterator
    */
   public Iterator<T> iterator() {
      return new RandomIterator<T>(elements, size);
   }
   
   /*************************************************************************/
   /****************************SUPPORT METHODS******************************/
   /*************************************************************************/
   
   /**
    * Checks if the collection is full.
    * 
    * @return boolean value
    */
   private boolean isFull() {
      return size == elements.length;
   }
   
   /**
    * Resizes the array is the number of objects in the array
    * equals to its capacity.
    * 
    * @param capacity specified capacity of the array
    */
   @SuppressWarnings("unchecked")
   private void resize(int capacity) {
      T[] copy = (T[]) new Object[capacity];
      for (int i = 0; i < size; i++) {
         copy[i] = elements[i];
      }
      elements = copy;
   }
   
   /**
    * Checks if the specified element is in the array by 
    * traversing through the collection.
    * 
    * @param element the element to be located
    */
   private int search(T element) {
      int index = -1;
      for (int i = 0; i < size; i++) {
         if (elements[i].equals(element)) {
            index = i;
         }
      }
      return index;
   }

   
   /*************************************************************************/
   /*****************************NESTED CLASSES******************************/
   /*************************************************************************/
   
/**
 * RandomListIterator.java. This class implements Iterator.java.
 * 
 * @param <T> type variable
 */
   private class RandomIterator<T> implements Iterator<T> {
      // array of elements to be iterated
      private T[] items;
      // the number of elements in the array
      private int count;
      // the current position in the iteration
      private int curr;
      
   /**
    * Constructor.
    *
    * @param items the array of elements
    * @param count the number of objects in the array
    */
      private RandomIterator(T[] elements, int size) {
         items = elements; // alias
         count = size;
         curr = 0;
      }
      
   /**
    * Returns true if there is more structure to be traversed.
    *
    * @return boolean value
    */
      public boolean hasNext() {
         return curr < count;
      }
      
   /**
    * MUST NOT REMOVE ANY ELEMENT.
    *
    */
      public void remove() {
         throw new UnsupportedOperationException();
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
         // choose random index from remaining elements
         int index = new Random().nextInt(count);
         // save for return 
         T ret = items[index];
         if (index != count - 1) {
            items[index] = items[count - 1];
            items[count - 1] = ret;
         }
         count--;
         return ret;
      }
      
   }
   
}