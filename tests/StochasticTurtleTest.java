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
    String[] drawRules = {"FFF"};
    String[] moveRules = {};
    turtle.generate(1, drawRules, moveRules);
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
    String[] drawRules = {"F[F+F+F]F"};
    String[] moveRules = {};
    turtle.generate(1, drawRules, moveRules);
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
    String[] drawRules = {"FF"};
    String[] moveRules = {"GG"};
    turtle.generate(1, drawRules, moveRules);
    if ((!turtle.getWord().equals("FFFFFF")) && (!turtle.getWord().equals("FFFFGG")) && (!turtle.getWord().equals("FFGGFF"))
        && (!turtle.getWord().equals("FFGGGG")) && (!turtle.getWord().equals("GGFFFF")) && (!turtle.getWord().equals("GGFFGG"))
        && (!turtle.getWord().equals("GGGGFF")) && (!turtle.getWord().equals("GGGGGG"))) {
      fail();
    }
  }

}