import org.junit.Assert;
// import static org.junit.Assert.*;
// import org.junit.Before;
import org.junit.Test;


public class PointTest {

   /** slopeTo Test 1. **/
   @Test public void slopeTo_Test_01() {
      Point p = new Point(3, 3);
      Assert.assertEquals(1.0, p.slopeTo(new Point(1, 1)), 0.0001);
   }
   
   /** slopeTo Test 2. **/
   @Test public void slopeTo_Test_02() {
      Point p = new Point(3, 3);
      Assert.assertEquals(2.0, p.slopeTo(new Point(4, 5)), 0.0001);
   }
   
   /** slopeTo SPECIAL CASE (same point). **/
   @Test public void slopeTo_Test_03() {
      Point p = new Point(3, 3);
      Assert.assertEquals(Double.NEGATIVE_INFINITY, p.slopeTo(new Point(3, 3)), 0.0001);
   }
   
   /** slopeTo Test SPECIAL CASE (horizontal). **/
   @Test public void slopeTo_Test_04() {
      Point p = new Point(3, 3);
      Assert.assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(new Point(3, 1)), 0.0001);
   }
   
   /** slopeTo Test SPECIAL CASE (vertical). **/
   @Test public void slopeTo_Test_05() {
      Point p = new Point(3, 3);
      Assert.assertEquals(0.0, p.slopeTo(new Point(1, 3)), 0.0001);
   }
   
   /** slopeOrder Test 1. **/
   @Test public void slopeOrder_Test_01() {
      Point p = new Point(3, 3); 
      Assert.assertTrue(p.slopeOrder.compare(new Point(5, 2), new Point(1, 1)) < 0);
   }
}
