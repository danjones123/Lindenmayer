import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Class for testing the ButtonsController.
 */
public class ButtonsControllerTest {
  Painting painting = new Painting();
  Turtle turtle = new Turtle();
  Lindenmayer linSysDet = new Lindenmayer(turtle);
  Lindenmayer linSysStoch = new Lindenmayer(turtle);
  ButtonsController button = new ButtonsController(painting);

  /**
   * Tests that a deterministic turtle can be initialised with the correct rules.
   */
  @Test
  public void testDetTurtleInit() {
    turtle.setWord("FFF");
    String[] drawRules = {"FFFFFF"};
    String[] moveRules = {"GG"};
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    button.turtleInit(turtle, linSysDet);
    assertArrayEquals(linSysDet.getDrawRules(), drawRules);
    assertArrayEquals(linSysDet.getMoveRules(), moveRules);
  }

  /**
   * Tests that a stochastic turtle can be initialised with the correct rules.
   */
  @Test
  public void testStochTurtleInit() {
    turtle.setWord("FFF");
    linSysStoch.setCurrentClass(2);
    String[] drawRules = {"FFFFFF"};
    String[] moveRules = {"GG"};
    linSysStoch.setDrawRules(drawRules);
    linSysStoch.setMoveRules(moveRules);
    button.turtleInit(turtle, linSysStoch);
    assertArrayEquals(linSysStoch.getDrawRules(), drawRules);
    assertArrayEquals(linSysStoch.getMoveRules(), moveRules);
  }

  /**
   * Tests that updatePrevTurtle updates the previous turtle.
   */
  @Test
  public void updatePrevTurtle() {
    turtle.setWord("FFFF");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.setCoords(100, 100);

    button.turtleInit(turtle, linSysDet);
    button.updatePrevTurtle();

    assertEquals("FFFF", button.previousTurtle.getWord());
    assertEquals(10, button.previousTurtle.getLength(), 1e-10);
    assertEquals(90, button.previousTurtle.getAngle(), 1e-10);
    assertEquals(100, button.previousTurtle.getCoordX(), 1e-10);
    assertEquals(100, button.previousTurtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that when generate is pressed the L-system is generated through.
   */
  @Test
  public void generatePressed() {
    turtle.setWord("FGF");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.setCoords(100, 100);
    turtle.saveStartingTurtle();
    String[] drawRules = {"FFF"};
    String[] moveRules = {"GG"};
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);

    button.turtleInit(turtle, linSysDet);

    button.buttonPressed(0, "gg");

    assertEquals("FFFFFFFFFGGGGFFFFFFFFF", turtle.getWord());

  }

  /**
   * Tests that when undo is pressed the correct result is given.
   */
  @Test
  public void undoPressed() {
    turtle.setWord("FGF");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.setCoords(100, 100);
    turtle.saveStartingTurtle();
    String[] drawRules = {"FFF"};
    String[] moveRules = {"GG"};
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);

    button.turtleInit(turtle, linSysDet);
    button.buttonPressed(0, "gg");
    assertEquals("FFFFFFFFFGGGGFFFFFFFFF", turtle.getWord());
    button.buttonPressed(1, "gn");
    assertEquals("FGF", turtle.getWord());
  }

  /**
   * Tests that when clear is pressed the Turtle is reset.
   */
  @Test
  public void clearPressed() {
    turtle.setWord("FGF");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.setCoords(100, 100);
    turtle.saveStartingTurtle();
    String[] drawRules = {"FFF"};
    String[] moveRules = {"GG"};
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);

    button.turtleInit(turtle, linSysDet);
    button.buttonPressed(0, "gg");
    button.buttonPressed(0, "gg");
    button.buttonPressed(0, "gg");


    assertEquals("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"
            + "FFFFGGGGGGGGGGGGGGGGFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"
            + "FFFFFFFFFFFFFFFFF", turtle.getWord());

    button.buttonPressed(2, "");

    assertEquals("FGF", turtle.getWord());
  }

  /**
   * Tests that drawPrev updates when the corresponding button is pressed.
   */
  @Test
  public void drawPrevPressed() {
    assertFalse(button.drawPrev);
    button.buttonPressed(3, "");
    assertTrue(button.drawPrev);
  }



  @Test
  public void testExternalReset() {
    turtle.setWord("FGF");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.setCoords(100, 100);
    turtle.saveStartingTurtle();
    String[] drawRules = {"FFF"};
    String[] moveRules = {"GG"};
    String[] rulesX = {"XFF"};
    String[] rulesY = {"YFY"};
    linSysDet.setDrawRules(drawRules);
    linSysDet.setMoveRules(moveRules);
    linSysDet.setRulesX(rulesX);
    linSysDet.setRulesY(rulesY);
    button.turtleInit(turtle, linSysDet);
    button.buttonPressed(0, "gg");

    assertEquals("FFFFFFFFFGGGGFFFFFFFFF", turtle.getWord());

    button.externalReset();

    assertEquals("FGF", turtle.getWord());
  }
}