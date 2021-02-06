import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class for testing the Buttons class.
 */
public class ButtonsTest {

  Display display = new Display();
  Turtle detTurtle = new DeterministicTurtle();
  Turtle stochTurtle = new StochasticTurtle();
  Buttons b = new Buttons(display);

  /**
   * Tests that a deterministic turtle can be initialised with the correct rules
   */
  @Test
  public void testDetTurtleInit() {
    detTurtle.setWord("FFF");
    String[] drawRules = {"FFFFFF"};
    String[] moveRules = {"GG"};
    detTurtle.setDrawRules(drawRules);
    detTurtle.setMoveRules(moveRules);
    b.turtleInit(detTurtle);
    assertArrayEquals(detTurtle.getDrawRules(), drawRules);
    assertArrayEquals(detTurtle.getMoveRules(), moveRules);
  }

  /**
   * Tests that a stochastic turtle can be initialised with the correct rules
   */
  @Test
  public void testStochTurtleInit() {
    stochTurtle.setWord("FFF");
    String[] drawRules = {"FFFFFF"};
    String[] moveRules = {"GG"};
    stochTurtle.setDrawRules(drawRules);
    stochTurtle.setMoveRules(moveRules);
    b.turtleInit(stochTurtle);
    assertArrayEquals(stochTurtle.getDrawRules(), drawRules);
    assertArrayEquals(stochTurtle.getMoveRules(), moveRules);
  }

  @Test
  public void testActionPerformed() {

  }

}