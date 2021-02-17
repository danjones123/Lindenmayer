import org.junit.Test;

import static org.junit.Assert.*;

public class LindenmayerTest {

  Turtle turtle = new Turtle();
  Lindenmayer lSysDet = new Lindenmayer(1, turtle);
  Lindenmayer lSysStoch = new Lindenmayer(2, turtle);



  /**
   * Tests that you can set generation rules for both draw- and moveRules.
   */
  @Test
  public void testSetGenRules() {
    String[] drawRules = {"FF"};
    String[] moveRules = {"FF"};
    turtle.setWord("F");
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    lSysDet.generate(1);
    assertEquals("FF", turtle.getWord());
  }

  /**
   * Tests that both drawRules and moveRules are applied
   */
  @Test
  public void testSetDiffGenRules() {
    String[] drawRules = {"FFF"};
    String[] moveRules = {"GGG"};
    turtle.setWord("FG");
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    lSysDet.generate(1);
    assertEquals("FFFGGG", turtle.getWord());
  }

  /**
   * Test for setting and getting the generation and move rules.
   */
  @Test
  public void testSetGetGenRules() {
    String[] drawRules = {"FFF"};
    String[] moveRules = {"GGG"};
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    assertArrayEquals(lSysDet.getDrawRules(), drawRules);
    assertArrayEquals(lSysDet.getMoveRules(), moveRules);

  }



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
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    lSysDet.generate(1);
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
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    lSysDet.generate(1);
    assertEquals("F[F+F+F]F", turtle.getWord());
  }

  /**
   * Tests that generate works when there are multiple rules in the rule-set.
   */
  @Test
  public void testTwoRulesInGenRules() {
    turtle.setWord("FGF");
    turtle.setLength(0);
    turtle.setAngle(0);
    String[] drawRules = {"FF"};
    String[] moveRules = {"GG"};
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    lSysDet.generate(1);
    assertEquals("FFGGFF", turtle.getWord());
  }

  /**
   * Tests that generate can use complex strings and rules to give the correct output.
   */
  @Test
  public void testComplexMultipleRules() {
    turtle.setWord("FF+[F+F+FGGF]");
    turtle.setLength(0);
    turtle.setAngle(0);
    String[] drawRules = {"F+G+F"};
    String[] moveRules = {"GGGGGGGG"};
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    lSysDet.generate(1);
    assertEquals("F+G+FF+G+F+[F+G+F+F+G+F+F+G+FGGGGGGGGGGGGGGGGF+G+F]",
        turtle.getWord());
  }

  /**
   * Tests that generate works with multiple iterations.
   */
  @Test
  public void testMoreIterations() {
    turtle.setWord("F");
    turtle.setLength(0);
    turtle.setAngle(0);
    String[] drawRules = {"FF"};
    String[] moveRules = {"GG"};
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    lSysDet.generate(2);
    assertEquals("FFFF", turtle.getWord());
  }

  /**
   * Tests that generate works with multiple iterations and more complex strings/rules.
   */
  @Test
  public void testMoreComplexIterations() {
    turtle.setWord("FGF");
    turtle.setLength(0);
    turtle.setAngle(0);
    String[] drawRules = {"FF"};
    String[] moveRules = {"GG"};
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    lSysDet.generate(2);
    assertEquals("FFFFGGGGFFFF", turtle.getWord());
  }

  /**
   * Tests that given the rules for a Sierpinski triangle the generate method returns what it
   * should.
   */
  @Test
  public void testSierpinskiTriangle() {
    turtle.setWord("F--F--F");
    turtle.setLength(5);
    turtle.setAngle(60);
    String[] drawRules = {"F--F--F--G"};
    String[] moveRules = {"GG"};
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    lSysDet.generate(3);
    assertEquals("F--F--F--G--F--F--F--G--F--F--F--G--GG--F--F--F--G--F--F--F--G--F"
            + "--F--F--G--GG--F--F--F--G--F--F--F--G--F--F--F--G--GG--GGGG--F--F--F--G--F--F"
            + "--F--G--F--F--F--G--GG--F--F--F--G--F--F--F--G--F--F--F--G--GG--F--F--F--G--F"
            + "--F--F--G--F--F--F--G--GG--GGGG--F--F--F--G--F--F--F--G--F--F--F--G--GG--F--F"
            + "--F--G--F--F--F--G--F--F--F--G--GG--F--F--F--G--F--F--F--G--F--F--F--G--GG--GGGG",
        turtle.getWord());
  }

  /**
   * Tests that the reset method sets the parameters in a turtle back to the initially given turtle
   * with 3 parameters.
   */
  @Test
  public void testResetThreeParam() {
    turtle.setWord("F--F--F");
    turtle.setLength(5);
    turtle.setAngle(60);
    turtle.saveStartingTurtle();
    String[] drawRules = {"F--F--F--G"};
    String[] moveRules = {"GG"};
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    lSysDet.generate(1);

    assertEquals("F--F--F--G--F--F--F--G--F--F--F--G", turtle.getWord());

    turtle.reset();

    assertEquals("F--F--F", turtle.getWord());
    assertEquals(5, turtle.getLength(), 1e-10);
    assertEquals(60, turtle.getAngle(), 1e-10);
  }

  /**
   * Tests that the reset method sets the parameters in a turtle back to the initially given turtle
   * with 5 parameters.
   */
  @Test
  public void testResetFiveParam() {
    turtle.setWord("F--F--F");
    turtle.setLength(5);
    turtle.setAngle(60);
    turtle.setCoords(250, 300);
    turtle.saveStartingTurtle();
    String[] drawRules = {"F--F--F--G"};
    String[] moveRules = {"GG"};
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    lSysDet.generate(1);

    assertEquals("F--F--F--G--F--F--F--G--F--F--F--G", turtle.getWord());

    turtle.reset();

    assertEquals("F--F--F", turtle.getWord());
    assertEquals(5, turtle.getLength(), 1e-10);
    assertEquals(60, turtle.getAngle(), 1e-10);
    assertEquals(250, turtle.getCoordX(), 1e-10);
    assertEquals(300, turtle.getCoordY(), 1e-10);
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
    lSysStoch.setDrawRules(drawRules);
    lSysStoch.setMoveRules(moveRules);
    lSysStoch.generate(1);
    assertEquals("YFX[+Y][-Y]", turtle.getWord());
    lSysStoch.generate(1);
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
    lSysStoch.setDrawRules(drawRules);
    lSysStoch.setMoveRules(moveRules);
    lSysStoch.generate(1);
    if (!turtle.getWord().equals("F+F") && !turtle.getWord().equals("F-F") && !turtle.getWord().equals("FF")) {
      fail();
    }

    for (int i = 0; i < 1000; i++) {
      turtle.reset();
      lSysStoch.generate(2);
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
    turtle.setAngle(100);
    lSysStoch.stochAngle(true, 25, 45);
    lSysStoch.angleVariance();
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
    turtle.setLength(100);
    lSysStoch.setLengthRatio(0.5);
    lSysStoch.changeRatio();
    assertEquals(50, turtle.getLength(), 1e-10);
  }

}