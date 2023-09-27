import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** Test given values from vevooo. **/
  
   @Test public void testFloor_33() {
      int[] a = {9, 7};
      Assert.assertEquals(9, Selector.floor(a, 11));
   }
   @Test public void testFloor5() {
      int[] a = {-3, 3, 9, 7, 0};
      Assert.assertEquals(9, Selector.floor(a, 11));
   }
   @Test public void testFloor_444() {
      int[] a = {9, 7};
      Assert.assertEquals(9, Selector.floor(a, 9));
   }
   @Test public void testFloor_4444() {
      int[] a = {9, 7};
      Assert.assertEquals(7, Selector.floor(a, 8));
   }
   @Test public void testFloor3() {
      int[] a = {7};
      Assert.assertEquals(7, Selector.floor(a, 7));
   }
   @Test public void testFloor33() {
      int[] a = {7};
      Assert.assertEquals(7, Selector.floor(a, 11));
   }
   @Test public void testRange3() {
      int[] a = {7};
      Assert.assertEquals(7, Selector.range(a, 3, 4));
   }
   @Test public void testRange44() {
      int[] a = {7, 5};
      int[] b = {7, 5};
      Assert.assertEquals(b, Selector.range(a, 5, 7));
   }


   
    
      

}
