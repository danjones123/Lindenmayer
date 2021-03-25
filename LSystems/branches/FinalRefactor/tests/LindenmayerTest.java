import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for the Lindenmayer class.
 */
public class LindenmayerTest {

  Turtle turtle = new Turtle();
  Lindenmayer linSysDet = new Lindenmayer(turtle);
  Lindenmayer linSysStoch = new Lindenmayer(turtle);




  /**
   * Tests that you can set generation rules for both draw- and moveRules.
   */
  @Test
  public void testSetGenRules() {
    String[] drawRules = {"FF"};
    String[] moveRules = {"FF"};
    turtle.setWord("F");
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.generate(1);
    assertEquals("FF", turtle.getWord());
  }

  /**
   * Tests that both drawRules and moveRules are applied.
   */
  @Test
  public void testSetDiffGenRules() {
    String[] drawRules = {"FFF"};
    String[] moveRules = {"GGG"};
    turtle.setWord("FG");
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.generate(1);
    assertEquals("FFFGGG", turtle.getWord());
  }

  /**
   * Test for setting and getting the generation, move, X and Y rules.
   */
  @Test
  public void testSetGetGenRules() {
    String[] drawRules = {"FFF"};
    String[] moveRules = {"GGG"};
    String[] rulesX = {"XXX"};
    String[] rulesY = {"YYY"};
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.setRulesX(rulesX);
    linSysDet.setRulesY(rulesY);

    assertArrayEquals(linSysDet.getDrawRules(), drawRules);
    assertArrayEquals(linSysDet.getMoveRules(), moveRules);
    assertArrayEquals(linSysDet.getRulesX(), rulesX);
    assertArrayEquals(linSysDet.getRulesY(), rulesY);
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
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.generate(1);
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
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.generate(1);
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
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.generate(1);
    assertEquals("FFGGFF", turtle.getWord());
  }

  @Test
  public void testAllRulesInGen() {
    turtle.setWord("FGXYF");
    turtle.setLength(0);
    turtle.setAngle(0);
    String[] drawRules = {"FF"};
    String[] moveRules = {"GG"};
    String[] rulesX = {"XX"};
    String[] rulesY = {"YY"};
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.setRulesX(rulesX);
    linSysDet.setRulesY(rulesY);
    linSysDet.generate(1);
    assertEquals("FFGGXXYYFF", turtle.getWord());
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
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.generate(1);
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
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.generate(2);
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
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.generate(2);
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
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.generate(3);
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
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.generate(1);

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
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.generate(1);

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
  public void testEcksWhyGen() {
    turtle.setWord("Y");
    turtle.setLength(0);
    turtle.setAngle(0);
    linSysStoch.setCurrentClass(2);
    String[] drawRules = {"F-F-F-F"};
    String[] moveRules = {""};
    String[] rulesX = {"X[-FFF][+FFF]FX"};
    String[] rulesY = {"YFX[+Y][-Y]"};
    linSysStoch.setDrawRules(drawRules);
    linSysStoch.setMoveRules(moveRules);
    linSysStoch.setRulesX(rulesX);
    linSysStoch.setRulesY(rulesY);
    linSysStoch.generate(1);
    assertEquals("YFX[+Y][-Y]", turtle.getWord());
    linSysStoch.generate(1);
    assertEquals("YFX[+Y][-Y]F-F-F-FX[-FFF][+FFF]FX[+YFX[+Y][-Y]][-YFX[+Y][-Y]]", turtle.getWord());
  }

  /**
   * Tests that the stochastic turtle gives the correct outputs.
   */
  @Test
  public void testStochastic() {
    turtle.setWord("F");
    turtle.setLength(0);
    turtle.setAngle(0);
    turtle.saveStartingTurtle();
    String[] drawRules = {"F+F", "F-F", "FF"};
    String[] moveRules = {""};
    linSysStoch.setDrawRules(drawRules);
    linSysStoch.setMoveRules(moveRules);
    linSysStoch.generate(1);
    if (!turtle.getWord().equals("F+F") && !turtle.getWord().equals("F-F")
        && !turtle.getWord().equals("FF")) {
      fail();
    }

    for (int i = 0; i < 1000; i++) {
      turtle.reset();
      linSysStoch.generate(2);
      if (!turtle.getWord().equals("F+F+F+F") && !turtle.getWord().equals("F+F+F-F")
          && !turtle.getWord().equals("F+F+FF") && !turtle.getWord().equals("F-F+F+F")
          && !turtle.getWord().equals("F-F+F-F") && !turtle.getWord().equals("F-F+FF")
          && !turtle.getWord().equals("F-F-F+F") && !turtle.getWord().equals("F-F-F-F")
          && !turtle.getWord().equals("F-F-FF") && !turtle.getWord().equals("F+F-F+F")
          && !turtle.getWord().equals("F+F-F-F") && !turtle.getWord().equals("F+F-FF")
          && !turtle.getWord().equals("FFF+F") && !turtle.getWord().equals("FFF-F")
          && !turtle.getWord().equals("FFFF") && !turtle.getWord().equals("FF+F+F")
          && !turtle.getWord().equals("FF+F-F") && !turtle.getWord().equals("FF-F+F")
          && !turtle.getWord().equals("FF-F-F") && !turtle.getWord().equals("F+FF+F")
          && !turtle.getWord().equals("F+FF-F") && !turtle.getWord().equals("F-FF+F")
          && !turtle.getWord().equals("F-FF-F") && !turtle.getWord().equals("FF+FF")
          && !turtle.getWord().equals("FF-FF") && !turtle.getWord().equals("F+FFF")
          && !turtle.getWord().equals("F-FFF")) {
        fail();
      }
    }
  }

  /**
   * Tests that changeRatio() scales down the length by the correct amount.
   */
  @Test
  public void testLengthChanger() {
    turtle.setLength(100);
    linSysStoch.setLengthRatio(0.5);
    linSysStoch.changeLengthRatio();
    assertEquals(50, turtle.getLength(), 1e-10);
  }

  @Test
  public void testSetCustomRulesBool() {
    assertFalse(linSysStoch.customRulesBool);
    linSysStoch.setCustomRulesBool(true);
    assertTrue(linSysStoch.customRulesBool);
  }

  @Test
  public void testRandRules() {
    turtle.setWord("FGXY");
    turtle.setLength(0);
    turtle.setAngle(0);
    turtle.saveStartingTurtle();
    String[] drawRules = {"A", "B", "C"};
    String[] moveRules = {"E", "H", "I"};
    String[] rulesX = {"K", "L", "M"};
    String[] rulesY = {"O", "P", "Q"};
    linSysStoch.setDrawRules(drawRules);
    linSysStoch.setMoveRules(moveRules);
    linSysStoch.setRulesX(rulesX);
    linSysStoch.setRulesY(rulesY);
    linSysStoch.setCurrentClass(2);

    linSysStoch.generate(1);

    for (int i = 0; i < 1000; i++) {
      turtle.reset();
      linSysStoch.generate(1);
      if (!turtle.getWord().equals("AEKO") && !turtle.getWord().equals("AEKP")
          && !turtle.getWord().equals("AEKQ") && !turtle.getWord().equals("AELO")
          && !turtle.getWord().equals("AELP") && !turtle.getWord().equals("AELQ")
          && !turtle.getWord().equals("AEMO") && !turtle.getWord().equals("AEMP")
          && !turtle.getWord().equals("AEMQ") && !turtle.getWord().equals("AHKO")
          && !turtle.getWord().equals("AHKP") && !turtle.getWord().equals("AHKQ")
          && !turtle.getWord().equals("AHLO") && !turtle.getWord().equals("AHLP")
          && !turtle.getWord().equals("AHLQ") && !turtle.getWord().equals("AHMO")
          && !turtle.getWord().equals("AHMP") && !turtle.getWord().equals("AHMQ")
          && !turtle.getWord().equals("AIKO") && !turtle.getWord().equals("AIKP")
          && !turtle.getWord().equals("AIKQ") && !turtle.getWord().equals("AILO")
          && !turtle.getWord().equals("AILP") && !turtle.getWord().equals("AILQ")
          && !turtle.getWord().equals("AIMO") && !turtle.getWord().equals("AIMP")
          && !turtle.getWord().equals("AIMQ") && !turtle.getWord().equals("BEKO")
          && !turtle.getWord().equals("BEKP") && !turtle.getWord().equals("BEKQ")
          && !turtle.getWord().equals("BELO") && !turtle.getWord().equals("BELP")
          && !turtle.getWord().equals("BELQ") && !turtle.getWord().equals("BEMO")
          && !turtle.getWord().equals("BEMP") && !turtle.getWord().equals("BEMQ")
          && !turtle.getWord().equals("BHKO") && !turtle.getWord().equals("BHKP")
          && !turtle.getWord().equals("BHKQ") && !turtle.getWord().equals("BHLO")
          && !turtle.getWord().equals("BHLP") && !turtle.getWord().equals("BHLQ")
          && !turtle.getWord().equals("BHMO") && !turtle.getWord().equals("BHMP")
          && !turtle.getWord().equals("BHMQ") && !turtle.getWord().equals("BIKO")
          && !turtle.getWord().equals("BIKP") && !turtle.getWord().equals("BIKQ")
          && !turtle.getWord().equals("BILO") && !turtle.getWord().equals("BILP")
          && !turtle.getWord().equals("BILQ") && !turtle.getWord().equals("BIMO")
          && !turtle.getWord().equals("BIMP") && !turtle.getWord().equals("BIMQ")
          && !turtle.getWord().equals("CEKO") && !turtle.getWord().equals("CEKP")
          && !turtle.getWord().equals("CEKQ") && !turtle.getWord().equals("CELO")
          && !turtle.getWord().equals("CELP") && !turtle.getWord().equals("CELQ")
          && !turtle.getWord().equals("CEMO") && !turtle.getWord().equals("CEMP")
          && !turtle.getWord().equals("CEMQ") && !turtle.getWord().equals("CHKO")
          && !turtle.getWord().equals("CHKP") && !turtle.getWord().equals("CHKQ")
          && !turtle.getWord().equals("CHLO") && !turtle.getWord().equals("CHLP")
          && !turtle.getWord().equals("CHLQ") && !turtle.getWord().equals("CHMO")
          && !turtle.getWord().equals("CHMP") && !turtle.getWord().equals("CHMQ")
          && !turtle.getWord().equals("CIKO") && !turtle.getWord().equals("CIKP")
          && !turtle.getWord().equals("CIKQ") && !turtle.getWord().equals("CILO")
          && !turtle.getWord().equals("CILP") && !turtle.getWord().equals("CILQ")
          && !turtle.getWord().equals("CIMO") && !turtle.getWord().equals("CIMP")
          && !turtle.getWord().equals("CIMQ")) {
        fail();
      }
      System.out.println(i);
    }
  }

  @Test
  public void testCustomRules() {
    turtle.setWord("FGXY");
    turtle.setLength(0);
    turtle.setAngle(0);
    turtle.saveStartingTurtle();
    String[] drawRules = {"A", "B", "C"};
    String[] moveRules = {"E", "H", "I"};
    String[] rulesX = {"K", "L", "M"};
    String[] rulesY = {"O", "P", "Q"};
    linSysStoch.setDrawRules(drawRules);
    linSysStoch.setMoveRules(moveRules);
    linSysStoch.setRulesX(rulesX);
    linSysStoch.setRulesY(rulesY);
    linSysStoch.setCurrentClass(2);

    linSysStoch.setCustomRulesBool(true);
    linSysStoch.customRuleProb("draw", new Double[]{0.0, 1.0, 0.0});

    linSysStoch.generate(1);

    for (int i = 0; i < 1000; i++) {
      turtle.reset();
      linSysStoch.generate(1);
      if (turtle.getWord().equals("AEKO") || turtle.getWord().equals("AEKP")
          || turtle.getWord().equals("AEKQ") || turtle.getWord().equals("AELO")
          || turtle.getWord().equals("AELP") || turtle.getWord().equals("AELQ")
          || turtle.getWord().equals("AEMO") || turtle.getWord().equals("AEMP")
          || turtle.getWord().equals("AEMQ") || turtle.getWord().equals("AHKO")
          || turtle.getWord().equals("AHKP") || turtle.getWord().equals("AHKQ")
          || turtle.getWord().equals("AHLO") || turtle.getWord().equals("AHLP")
          || turtle.getWord().equals("AHLQ") || turtle.getWord().equals("AHMO")
          || turtle.getWord().equals("AHMP") || turtle.getWord().equals("AHMQ")
          || turtle.getWord().equals("AIKO") || turtle.getWord().equals("AIKP")
          || turtle.getWord().equals("AIKQ") || turtle.getWord().equals("AILO")
          || turtle.getWord().equals("AILP") || turtle.getWord().equals("AILQ")
          || turtle.getWord().equals("AIMO") || turtle.getWord().equals("AIMP")
          || turtle.getWord().equals("AIMQ") || turtle.getWord().equals("CEKO")
          || turtle.getWord().equals("CEKP") || turtle.getWord().equals("CEKQ")
          || turtle.getWord().equals("CELO") || turtle.getWord().equals("CELP")
          || turtle.getWord().equals("CELQ") || turtle.getWord().equals("CEMO")
          || turtle.getWord().equals("CEMP") || turtle.getWord().equals("CEMQ")
          || turtle.getWord().equals("CHKO") || turtle.getWord().equals("CHKP")
          || turtle.getWord().equals("CHKQ") || turtle.getWord().equals("CHLO")
          || turtle.getWord().equals("CHLP") || turtle.getWord().equals("CHLQ")
          || turtle.getWord().equals("CHMO") || turtle.getWord().equals("CHMP")
          || turtle.getWord().equals("CHMQ") || turtle.getWord().equals("CIKO")
          || turtle.getWord().equals("CIKP") || turtle.getWord().equals("CIKQ")
          || turtle.getWord().equals("CILO") || turtle.getWord().equals("CILP")
          || turtle.getWord().equals("CILQ") || turtle.getWord().equals("CIMO")
          || turtle.getWord().equals("CIMP") || turtle.getWord().equals("CIMQ")) {
        fail();
      }
      System.out.println(i);
    }
  }
}