import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Anastasiia Kotova (azk0108@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 09/07/2019
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
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param <T>     This is the type parameter
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
     // coll || comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("null");
      }
     // size == 0
      if (coll.size() == 0) {
         throw new NoSuchElementException("zero length");
      }

      T min = coll.iterator().next();
     // size == 1
      if (coll.size() == 1) {
         return min;
      }
      for (T val : coll) {
         if (comp.compare(val, min) < 0) {
            min = val;
         }
      }
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param <T>     This is the type parameter
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
     // coll || comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("null");
      }
     // size == 0
      if (coll.size() == 0) {
         throw new NoSuchElementException("zero length");
      }

      T max = coll.iterator().next();
     // size == 1
      if (coll.size() == 1) {
         return max;
      }
     // size > 1
      for (T val : coll) {
         if (comp.compare(val, max) > 0) {
            max = val;
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param <T>     This is the type parameter
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
   // coll || comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("null");
      }

   // size == 0 || no kth min
      if (coll.size() == 0 || k < 1 || k > coll.size()) {
         throw new NoSuchElementException("zero length || no kth min");
      }

      List<T> copy = new ArrayList<T>(coll.size());
      copy.addAll(coll);

      // size == 1
      if (copy.size() == 1) {
         return copy.get(0);
      }

      int distinctVals = 1;
      int retLength = copy.size();

      java.util.Collections.sort(copy, comp);

      for (int i = 0; i < copy.size() - 1; i++) {
         if (copy.get(i) != copy.get(i + 1)) {
            distinctVals++;
         }
         else {
            retLength--;
         }
      }

      // no kth min
      if (k > distinctVals) {
         throw new NoSuchElementException("no kth min");
      }

      List<T> ret = new ArrayList<T>(retLength);
      ret.add(copy.get(0));
      int j = 1;
      for (int i = 1; i < copy.size(); i++) {
         if (copy.get(i) != copy.get(i - 1)) {
            ret.add(j++, copy.get(i));
         }
      }
      return ret.get(k - 1);
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param <T>     This is the type parameter
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
     // coll || comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("null");
      }

     // size == 0 || no kth min
      if (coll.size() == 0 || k < 1 || k > coll.size()) {
         throw new NoSuchElementException("zero length || no kth min");
      }

      List<T> copy = new ArrayList<T>(coll.size());
      copy.addAll(coll);

        // size == 1
      if (copy.size() == 1) {
         return copy.get(0);
      }

      int distinctVals = 1;
      int retLength = copy.size();

      java.util.Collections.sort(copy, comp);

      for (int i = 0; i < copy.size() - 1; i++) {
         if (copy.get(i) != copy.get(i + 1)) {
            distinctVals++;
         }
         else {
            retLength--;
         }
      }

        // no kth min
      if (k > distinctVals) {
         throw new NoSuchElementException("no kth min");
      }

      List<T> ret = new ArrayList<T>(retLength);
      ret.add(copy.get(0));
      int j = 1;
      for (int i = 1; i < copy.size(); i++) {
         if (copy.get(i) != copy.get(i - 1)) {
            ret.add(j++, copy.get(i));
         }
      }
      return ret.get(retLength - k);

   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param <T>     This is the type parameter
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      // coll || comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("null");
      }

      // size == 0
      if (coll.size() == 0) {
         throw new NoSuchElementException("zero length");
      }

      // size > 0
      int count = 0;
      for (T val : coll) {
         if (comp.compare(val, low) >= 0
               && comp.compare(val, high) <= 0) {
            count++;
         }
      }

      // no qualifying value
      if (count == 0) {
         throw new NoSuchElementException("no vals");
      }

      Collection<T> copy = new ArrayList<T>(count);
      for (T val : coll) {
         if (comp.compare(val, low) >= 0
               && comp.compare(val, high) <= 0) {
            copy.add(val);
         }
      }
      return copy;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param <T>     This is the type parameter
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
     // coll || comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("null");
      }

      // size == 0
      if (coll.size() == 0) {
         throw new NoSuchElementException("zero length");
      }

      // size > 0
      boolean cond = false;
      T ceil = max(coll, comp);
      for (T val : coll) {
         if (comp.compare(val, key) >= 0
                  && comp.compare(val, ceil) <= 0) {
            ceil = val;
            cond = true;
         }
      }

      // no qualifying value
      if (!cond) {
         throw new NoSuchElementException("no vals");
      }
      return ceil;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param <T>     This is the type parameter
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      // coll || comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("null");
      }

      // size == 0
      if (coll.size() == 0) {
         throw new NoSuchElementException("zero length");
      }

      // size > 0
      boolean cond = false;
      T floor = min(coll, comp);
      for (T val : coll) {
         if (comp.compare(val, key) <= 0
                  && comp.compare(val, floor) >= 0) {
            floor = val;
            cond = true;
         }
      }
      // no qualifying value
      if (!cond) {
         throw new NoSuchElementException("no vals");
      }
      return floor;
   }

}
