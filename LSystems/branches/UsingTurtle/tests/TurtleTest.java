import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



public class TurtleTest {

  //private Turtle globTurtle = new Turtle("FG+-[]", 5, 90, 150, 150);
  @Before
  public void setUp() throws Exception {
  }

  /**
   * Tests to make sure the values of a Turtle with three parameters is constructed properly.
   */
  @Test
  public void testTurtleThreeConst() {
    Turtle turtle3 = new Turtle("FGF+-[]", 7, 3.14);
    assertEquals("FGF+-[]", turtle3.word);
    assertEquals(7, turtle3.length, 1e-15);
    assertEquals(3.14, turtle3.angle, 1e-15);
    assertEquals(0, turtle3.coordX, 1e-15);
    assertEquals(0, turtle3.coordY, 1e-15);
  }

  /**
   * Tests to make sure the values of a Turtle with five parameters is constructed properly.
   */
  @Test
  public void testTurtleFiveConst() {
    Turtle turtle5 = new Turtle("FGF+-[]", 7, 3.14, 150, 100);
    assertEquals("FGF+-[]", turtle5.word);
    assertEquals(7, turtle5.length, 1e-15);
    assertEquals(3.14, turtle5.angle, 1e-15);
    assertEquals(150, turtle5.coordX, 1e-15);
    assertEquals(100, turtle5.coordY, 1e-15);
  }

  /**
   * Tests to make sure the switch statement in rules for F goes to the draw method.
   */
  @Test
  public void testRulesCallsDraw() {
    Turtle drawTurtle = new Turtle("F", 5, 90, 150, 150);
    assertEquals(drawTurtle.coordX, 150, 1e-15);
    assertEquals(drawTurtle.coordY, 150, 1e-15);
    drawTurtle.rules();
    assertEquals(drawTurtle.coordX, 150 + (5 * Math.cos(90)), 1e-15);
    assertEquals(drawTurtle.coordY, 150 + (5 * Math.sin(90)), 1e-15);
  }

  /**
   * Tests to make sure the switch statement in rules calls draw twice if there are two 'F's.
   */
  @Test
  public void testRulesCallsDrawTwice() {
    Turtle drawTwoTurtle = new Turtle("FF", 5, 90, 150, 150);
    assertEquals(drawTwoTurtle.coordX, 150, 1e-15);
    assertEquals(drawTwoTurtle.coordY, 150, 1e-15);
    drawTwoTurtle.rules();
    assertEquals(drawTwoTurtle.coordX, 150 + (5 * Math.cos(90) + (5 * Math.cos(90))), 1e-15);
    assertEquals(drawTwoTurtle.coordY, 150 + (5 * Math.sin(90) + (5 * Math.sin(90))), 1e-15);
  }

  /**
   * Tests to make sure that rules calls move when given a G.
   */
  @Test
  public void testRulesCallsMove() {
    Turtle moveTurtle = new Turtle("G", 5, 90, 150, 150);
    assertEquals(moveTurtle.coordX, 150, 1e-15);
    assertEquals(moveTurtle.coordY, 150, 1e-15);
    moveTurtle.rules();
    assertEquals(moveTurtle.coordX, 150 + (5 * Math.cos(90)), 1e-15);
    assertEquals(moveTurtle.coordY, 150 + (5 * Math.sin(90)), 1e-15);
  }

  /**
   * Test to make sure that rules calls draw twice if there are two 'G's.
   */
  @Test
  public void testRulesCallsMoveTwice() {
    Turtle moveTwoTurtle = new Turtle("GG", 5, 90, 150, 150);
    assertEquals(moveTwoTurtle.coordX, 150, 1e-15);
    assertEquals(moveTwoTurtle.coordY, 150, 1e-15);
    moveTwoTurtle.rules();
    assertEquals(moveTwoTurtle.coordX, 150 + (5 * Math.cos(90) + (5 * Math.cos(90))), 1e-15);
    assertEquals(moveTwoTurtle.coordY, 150 + (5 * Math.sin(90) + (5 * Math.sin(90))), 1e-15);
  }

  /**
   * Tests that the rotation is applied when a '+' is given.
   */
  @Test
  public void testRulesCallsRotate() {
    Turtle rotateTurtle = new Turtle("+", 5, 90, 150, 150);
    assertEquals(rotateTurtle.angle, 90, 1e-15);
    rotateTurtle.rules();
    assertEquals(rotateTurtle.angle, 90 + Math.toRadians(90), 1e-15);
  }

  /**
   * Tests that a negative rotation happens when '-' is given.
   */
  @Test
  public void testRulesCallsNegativeRotate() {
    Turtle negativeRotateTurtle = new Turtle("-", 5, 90, 150, 150);
    assertEquals(negativeRotateTurtle.angle, 90, 1e-15);
    negativeRotateTurtle.rules();
    assertEquals(negativeRotateTurtle.angle, 90 + Math.toRadians(-90), 1e-15);
  }

  /**
   * Test to make sure that when a square is given, the starting point is equal to the
   * finishing point.
   */
  @Test
  public void testSquare() {
    //Turtle square = new Turtle("F+F+F+F")
  }
}