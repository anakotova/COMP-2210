import org.junit.Assert;
// import static org.junit.Assert.*;
// import org.junit.Before;
import org.junit.Test;


public class SelectorTest {

  /** A test. **/
   @Test public void testMin1() {
      int[] arr = {2, 2, 3};
      Assert.assertEquals(2, Selector.min(arr));
   }

   /** A test. **/
   @Test public void testKMax_1() {
      int[] arr = {4, 4};
      Assert.assertEquals(4, Selector.kmax(arr, 1));
   }

   /** A test. **/
   @Test public void testKMax_2() {
      int[] arr = {3, 7, 3, 3, 1, 9, 1, 1, 1, 5};
      Assert.assertEquals(9, Selector.kmax(arr, 1));
   }
}
