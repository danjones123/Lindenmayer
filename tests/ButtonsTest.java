import org.junit.Test;
import static org.junit.Assert.*;

public class ButtonsTest {

  Display display = new Display();
  Turtle detTurtle = new DeterministicTurtle();
  Turtle stochTurtle = new StochasticTurtle();
  Buttons b = new Buttons(display);

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