import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Class for testing the Buttons class.
 */
public class ButtonsTest {

  Display display = new Display();
  Turtle turtle = new Turtle();
  Lindenmayer lSysDet = new Lindenmayer(1, turtle);
  Lindenmayer lSysStoch = new Lindenmayer(2, turtle);
  Buttons b = new Buttons(display);

  /**
   * Tests that a deterministic turtle can be initialised with the correct rules
   */
  @Test
  public void testDetTurtleInit() {
    turtle.setWord("FFF");
    String[] drawRules = {"FFFFFF"};
    String[] moveRules = {"GG"};
    lSysDet.setDrawRules(drawRules);
    lSysDet.setMoveRules(moveRules);
    b.turtleInit(turtle, lSysDet);
    assertArrayEquals(lSysDet.getDrawRules(), drawRules);
    assertArrayEquals(lSysDet.getMoveRules(), moveRules);
  }

  /**
   * Tests that a stochastic turtle can be initialised with the correct rules
   */
  @Test
  public void testStochTurtleInit() {
    turtle.setWord("FFF");
    String[] drawRules = {"FFFFFF"};
    String[] moveRules = {"GG"};
    lSysStoch.setDrawRules(drawRules);
    lSysStoch.setMoveRules(moveRules);
    b.turtleInit(turtle, lSysStoch);
    assertArrayEquals(lSysStoch.getDrawRules(), drawRules);
    assertArrayEquals(lSysStoch.getMoveRules(), moveRules);
  }

  @Test
  public void testActionPerformed() {

  }

}