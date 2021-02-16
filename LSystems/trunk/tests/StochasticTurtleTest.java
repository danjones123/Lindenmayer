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
  public void testXYGen() {
    turtle.setWord("Y");
    turtle.setLength(0);
    turtle.setAngle(0);
    String[] drawRules = {"F-F-F-F"};
    String[] moveRules = {"X[-FFF][+FFF]FX", "YFX[+Y][-Y]"};
    turtle.generate(1, drawRules, moveRules);
    assertEquals("YFX[+Y][-Y]", turtle.getWord());
    turtle.generate(1, drawRules, moveRules);
    assertEquals("YFX[+Y][-Y]F-F-F-FX[-FFF][+FFF]FX[+YFX[+Y][-Y]][-YFX[+Y][-Y]]", turtle.getWord());
  }

  /**
   * Tests that the stochastic turtle gives the correct outputs
   */
  @Test
  public void testStochastic() {
    turtle.setWord("F");
    turtle.setLength(0);
    turtle.setAngle(0);
    turtle.saveStartingTurtle();
    String[] drawRules = {"F+F", "F-F", "FF"};
    String[] moveRules = {""};
    turtle.generate(1, drawRules, moveRules);
    if (!turtle.getWord().equals("F+F") && !turtle.getWord().equals("F-F") && !turtle.getWord().equals("FF")) {
      fail();
    }

    for (int i = 0; i < 1000; i++) {
      turtle.reset();
      turtle.generate(2, drawRules, moveRules);
      if (!turtle.getWord().equals("F+F+F+F") && !turtle.getWord().equals("F+F+F-F") && !turtle.getWord().equals("F+F+FF") &&
          !turtle.getWord().equals("F-F+F+F") && !turtle.getWord().equals("F-F+F-F") && !turtle.getWord().equals("F-F+FF") &&
          !turtle.getWord().equals("F-F-F+F") && !turtle.getWord().equals("F-F-F-F") && !turtle.getWord().equals("F-F-FF") &&
          !turtle.getWord().equals("F+F-F+F") && !turtle.getWord().equals("F+F-F-F") && !turtle.getWord().equals("F+F-FF") &&
          !turtle.getWord().equals("FFF+F") && !turtle.getWord().equals("FFF-F") && !turtle.getWord().equals("FFFF") &&
          !turtle.getWord().equals("FF+F+F") && !turtle.getWord().equals("FF+F-F") && !turtle.getWord().equals("FF-F+F") &&
          !turtle.getWord().equals("FF-F-F") && !turtle.getWord().equals("F+FF+F") && !turtle.getWord().equals("F+FF-F") &&
          !turtle.getWord().equals("F-FF+F") && !turtle.getWord().equals("F-FF-F") && !turtle.getWord().equals("FF+FF") &&
          !turtle.getWord().equals("FF-FF") && !turtle.getWord().equals("F+FFF") && !turtle.getWord().equals("F-FFF")) {
        fail();
      }
    }
  }

  /**
   * Tests that stochAngle sets angles within the correct range.
   */
  @Test
  public void testAngleSetting() {
    StochasticTurtle turtle = new StochasticTurtle();
    turtle.setAngle(100);
    turtle.stochAngle(true, 25, 45);
    turtle.angleVariance();
    System.out.println(turtle.getAngle());
    if (turtle.getAngle() < 25 || turtle.getAngle() > 46) {
      fail();
    }
  }

  /**
   * Tests that changeRatio() scales down the length by the correct amount.
   */
  @Test
  public void testLengthChanger() {
    StochasticTurtle turtle = new StochasticTurtle();
    turtle.setLength(100);
    turtle.setLengthRatio(0.5);
    turtle.changeRatio();
    assertEquals(50, turtle.getLength(), 1e-10);
  }
}








