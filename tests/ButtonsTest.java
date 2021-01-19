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

}