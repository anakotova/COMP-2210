import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Anastasia Kotova (azk0108@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  08/23/2019
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    * 
    * @param a array
    * @return The minimum value in the array
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int i = 0;
      int min = a[i];
      for (i = 1; i < a.length; i++) {
         if (min > a[i]) {
            min = a[i];
         }
      }
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    * 
    * @param a array
    * @return The maximum value in the array
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int i = 0;
      int max = a[i];
      for (i = 1; i < a.length; i++) {
         if (max < a[i]) {
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
    * 
    * @param a array
    * @param k number
    * @return The kth minimum value in the array
    */
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      
      if (a.length == 1) {
         return a[0];
      }
      
      int[] b = new int[a.length];
      for (int i = 0; i < b.length; i++) {
         b[i] = a[i];
      }
      int distinctVals = 1;
      int retLength = b.length;
      
      Arrays.sort(b);
      
      for (int i = 0; i < b.length - 1; i++) {
         if (b[i] != b[i + 1]) {
            distinctVals++;
         }
         else {
            retLength--;
         }
      }
      if (k > distinctVals) {
         throw new IllegalArgumentException();
      }
         
      int[] ret = new int[retLength];
      ret[0] = b[0];
      int j = 1;
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            ret[j++] = b[i];
         }
      }
      return ret[k - 1];
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    * 
    * @param a array
    * @param k number
    * @return The kth maximum value in the array
    */
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0 || k > a.length || k <= 0) {
         throw new IllegalArgumentException();
      }
      
      if (a.length == 1) {
         return a[0];
      }
      
      int[] b = new int[a.length];
      for (int i = 0; i < b.length; i++) {
         b[i] = a[i];
      }
      int distinctVals = 1;
      int retLength = b.length;
      
      Arrays.sort(b);
      
      for (int i = 0; i < b.length - 1; i++) {
         if (b[i] != b[i + 1]) {
            distinctVals++;
         }
         else {
            retLength--;
         }
      }
      
      if (k > distinctVals) {
         throw new IllegalArgumentException();
      }
         
      int[] ret = new int[retLength];
      ret[0] = b[0];
      int j = 1;
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            ret[j++] = b[i];
         }
      }
      return ret[ret.length - k];
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
    * 
    * @param a array
    * @param low The lower boundary of the interval 
    * @param high The upper boundary of the interval
    * @return The array containing all the values in the range 
    * [low..high]
    */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int count = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            count++;
         }
      }
      int[] ret = new int[count];
      int j = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            ret[j++] = a[i];
         }
      }
      return ret;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    * 
    * @param a array
    * @param key number
    * @return The smallest value in a that is greater than or equal to
    * the given key
    */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int ceil = Integer.MAX_VALUE;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= key && ceil > a[i]) {
            ceil = a[i];
         }
      } 
      if (ceil == Integer.MAX_VALUE) {
         throw new IllegalArgumentException();
      }
      return ceil;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    * 
    * @param a array
    * @param key number
    * @return The largest value in a that is less than or equal to
    * the given key
    */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int floor = Integer.MIN_VALUE;
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= key && floor < a[i]) {
            floor = a[i];
         }
      } 
      if (floor == Integer.MIN_VALUE) {
         throw new IllegalArgumentException();
      }
      return floor;
   }

}
