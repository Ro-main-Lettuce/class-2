import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class WordSearchGameVroomTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test (expected = IllegalStateException.class)
    public void validWordTest2() {
      WordSearchGameVroom game = new WordSearchGameVroom();
      game.isValidWord("Meow");
   }
   
   //normal tests
   @Test public void testIsValidWord_1() {
      WordSearchGameVroom game = new WordSearchGameVroom();
      game.loadLexicon("words_medium.txt");
      Assert.assertEquals(true, game.isValidWord("abhorrence"));
   }
   
   @Test public void testIsValidWord_2() {
      WordSearchGameVroom game = new WordSearchGameVroom();
      game.loadLexicon("CSW12.txt");
      Assert.assertEquals(true, game.isValidWord("BUMP"));
   }
   @Test public void testIsValidWord_3() {
      WordSearchGameVroom game = new WordSearchGameVroom();
      game.loadLexicon("CSW12.txt");
      Assert.assertEquals(false, game.isValidWord("BRUU"));
   }
   
   
   @Test
   public void testIsValidPrefix_1() {
      WordSearchGameVroom game = new WordSearchGameVroom();
      game.loadLexicon("words_medium.txt");
      assertTrue(game.isValidWord("abhor"));
   
   }
   
}