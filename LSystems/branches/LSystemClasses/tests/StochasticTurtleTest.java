import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Testing class for the Turtle class.
 *
 * @author Daniel Jones.
 */
public class StochasticTurtleTest {

  Turtle turtle = new StochasticTurtle();

  /**
   * Tests that the generate method works for turning a character into a given string.
   */
  @Test
  public void testSimpleDrawGenerate() {
    turtle.setWord("F");
    turtle.setLength(0);
    turtle.setAngle(0);
    String[] genRules = {"FFF"};
    turtle.generate(1, genRules);
    assertEquals("FFF", turtle.getWord());
  }

  /**
   * Tests that generate works with more complicated rules.
   */
  @Test
  public void testMoreComplexDrawGenerate() {
    turtle.setWord("F");
    turtle.setLength(0);
    turtle.setAngle(0);
    String[] genRules = {"F[F+F+F]F"};
    turtle.generate(1, genRules);
    assertEquals("F[F+F+F]F", turtle.getWord());
  }

  /**
   * Tests that the outcome of the generate methods is one of the expected values.
   */
  @Test
  public void testGenIsRandom() {
    turtle.setWord("FGF");
    turtle.setLength(0);
    turtle.setAngle(0);
    String[] genRules = {"FF", "GG"};
    turtle.generate(1, genRules);
    if ((!turtle.getWord().equals("FFFFFF")) && (!turtle.getWord().equals("FFFFGG")) && (!turtle.getWord().equals("FFGGFF"))
        && (!turtle.getWord().equals("FFGGGG")) && (!turtle.getWord().equals("GGFFFF")) && (!turtle.getWord().equals("GGFFGG"))
        && (!turtle.getWord().equals("GGGGFF")) && (!turtle.getWord().equals("GGGGGG"))) {
      fail();
    }
  }

}