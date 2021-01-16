import org.junit.Test;
import java.awt.event.ActionEvent;
import static org.junit.Assert.*;

public class ButtonsTest {

  Display display = new Display();
  Turtle detTurlte = new DeterministicTurtle();
  Turtle stochTurtle = new StochasticTurtle();
  Buttons b = new Buttons(display);

  @Test
  public void testDetTurtleInit() {
    detTurlte.setWord("FFF");
    String[] drawRules = {"FFFFFF"};
    String[] moveRules = {"GG"};
    detTurlte.setDrawRules(drawRules);
    detTurlte.setMoveRules(moveRules);
    b.turtleInit(detTurlte);
    assertArrayEquals(detTurlte.getDrawRules(), drawRules);
    assertArrayEquals(detTurlte.getMoveRules(), moveRules);
  }

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

  /**
   * Tests that the push and pop function of the Turtle works as intended and overwrites the current turlte
   */
  @Test
  public void pushPopTurtle() {
    detTurlte.setWord("FFF");
    detTurlte.setLength(10);
    detTurlte.setAngle(90);
    b.turtleInit(detTurlte);
    b.pushTurtle();
    detTurlte.setWord("ABCD");
    detTurlte.setLength(90);
    detTurlte.setAngle(180);
    assertEquals("ABCD", detTurlte.getWord());
    assertEquals(90, detTurlte.getLength(), 1e-10);
    assertEquals(180, detTurlte.getAngle(), 1e-10);
    b.popTurtle();
    assertEquals("FFF", detTurlte.getWord());
    assertEquals(10, detTurlte.getLength(), 1e-10);
    assertEquals(90, detTurlte.getAngle(), 1e-10);
  }

}