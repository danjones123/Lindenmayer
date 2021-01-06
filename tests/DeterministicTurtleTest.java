import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Testing class for the Turtle class.
 *
 * @author Daniel Jones.
 */
public class DeterministicTurtleTest {

  Turtle turtle = new DeterministicTurtle();

  /**
   * Tests that all default values of a Turtle are 0/null.
   */
  @Test
  public void testEmptyTurtle() {
    assertEquals("", turtle.getWord());
    assertEquals(0, turtle.getLength(), 1e-10);
    assertEquals(0, turtle.getAngle(), 1e-10);
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that setWord and getWord work.
   */
  @Test
  public void testSetGetWord() {
    assertEquals("", turtle.getWord());
    turtle.setWord("FF");
    assertEquals("FF", turtle.getWord());
  }

  /**
   * Tests that setLength and getLength work.
   */
  @Test
  public void testSetGetLength() {
    assertEquals(0, turtle.getLength(), 1e-10);
    turtle.setLength(10);
    assertEquals(10, turtle.getLength(), 1e-10);
  }

  /**
   * Tests that setAngle and getAngle work.
   */
  @Test
  public void testSetGetAngle() {
    assertEquals(0, turtle.getAngle(), 1e-10);
    turtle.setAngle(90);
    assertEquals(90, turtle.getAngle(), 1e-10);
  }

  /**
   * Tests that setCoords and getCoords work.
   */
  @Test
  public void testSetGetCoords() {
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
    turtle.setCoords(30, 45);
    assertEquals(30, turtle.getCoordX(), 1e-10);
    assertEquals(45, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that setGenRules works.
   */
  @Test
  public void testSetGenRules() {
    String[] drawRules = {"FF"};
    String[] moveRules = {"FF"};
    turtle.setWord("F");
    turtle.setDrawRules(drawRules);
    turtle.setMoveRules(moveRules);
    turtle.generate(1, drawRules, moveRules);
    assertEquals("FF", turtle.getWord());
  }

  /**
   * Tests that the coordinates of the turtle are changed when a 'F' is given and draw is called.
   */
  @Test
  public void testDrawLength() {
    turtle.setWord("F");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.rules();
    assertEquals(10, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that the coordiantes of the turtle are changed multiple times when multiple F's are
   * given.
   */
  @Test
  public void testMultipleDraw() {
    turtle.setWord("FFF");
    turtle.setLength(10);
    turtle.setAngle(90);
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
    turtle.rules();
    assertEquals(30, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that the move method words when a 'G' is given.
   */
  @Test
  public void testMove() {
    turtle.setWord("G");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.rules();
    assertEquals(10, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that the move method works with multiple G's given.
   */
  @Test
  public void testMultipleMove() {
    turtle.setWord("GGG");
    turtle.setLength(10);
    turtle.setAngle(90);
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
    turtle.rules();
    assertEquals(30, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that the turtle coordinates are what they shoudl be when draw and move are both called.
   */
  @Test
  public void testDrawMoveDraw() {
    turtle.setWord("FGF");
    turtle.setLength(10);
    turtle.setAngle(90);
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
    turtle.rules();
    assertEquals(30, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that rotate changes the currAngle in turtle and does not change the global angle.
   */
  @Test
  public void testPositiveRotate() {
    turtle.setWord("+");
    turtle.setLength(10);
    turtle.setAngle(90);
    assertEquals(90, turtle.getAngle(), 1e-10);
    assertEquals(0, turtle.currAngle, 1e-10);
    turtle.rules();
    assertEquals(90, turtle.getAngle(), 1e-10);
    assertEquals(Math.toRadians(90), turtle.currAngle, 1e-10);
  }

  /**
   * Tests that rotate also works with negative values.
   */
  @Test
  public void testNegativeRotate() {
    turtle.setWord("-");
    turtle.setLength(10);
    turtle.setAngle(90);
    assertEquals(90, turtle.getAngle(), 1e-10);
    assertEquals(0, turtle.currAngle, 1e-10);
    turtle.rules();
    assertEquals(90, turtle.getAngle(), 1e-10);
    assertEquals(Math.toRadians(-90), turtle.currAngle, 1e-10);
  }

  /**
   * Tests that a negative and positive rotation cancel each other out, as they should.
   */
  @Test
  public void cancelRotations() {
    turtle.setWord("+-");
    turtle.setLength(10);
    turtle.setAngle(90);
    assertEquals(90, turtle.getAngle(), 1e-10);
    assertEquals(0, turtle.currAngle, 1e-10);
    turtle.rules();
    assertEquals(90, turtle.getAngle(), 1e-10);
    assertEquals(0, turtle.currAngle, 1e-10);
  }

  /**
   * Tests that the currAngle is being used to allow a draw/rotate/draw to work.
   */
  @Test
  public void drawingWithAngle() {
    turtle.setWord("F+F");
    turtle.setLength(10);
    turtle.setAngle(90);
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
    assertEquals(90, turtle.getAngle(), 1e-10);
    assertEquals(0, turtle.currAngle, 1e-10);
    turtle.rules();
    assertEquals(10, turtle.getCoordX(), 1e-10);
    assertEquals(10, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that draw can work with multiple angles being called.
   */
  @Test
  public void drawingWithTwoAngle() {
    turtle.setWord("F+F+F");
    turtle.setLength(10);
    turtle.setAngle(90);
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
    assertEquals(90, turtle.getAngle(), 1e-10);
    assertEquals(0, turtle.currAngle, 1e-10);
    turtle.rules();
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(10, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that if a square is given that the starting and ending co-ordinates are the same.
   */
  @Test
  public void sqaureCoords() {
    turtle.setWord("F+F+F+F");
    turtle.setLength(10);
    turtle.setAngle(90);
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
    assertEquals(90, turtle.getAngle(), 1e-10);
    assertEquals(0, turtle.currAngle, 1e-10);
    turtle.rules();
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that the square also works with negative values.
   */
  @Test
  public void negativeSquare() {
    turtle.setWord("F-F-F-F");
    turtle.setLength(10);
    turtle.setAngle(90);
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
    assertEquals(90, turtle.getAngle(), 1e-10);
    assertEquals(0, turtle.currAngle, 1e-10);
    turtle.rules();
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that draw records both old and new coordinates as it needs both to draw a line.
   */
  @Test
  public void drawHasLineCoords() {
    turtle.setWord("F");
    turtle.setLength(10);
    turtle.setAngle(90);
    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
    turtle.rules();
    assertEquals(0, turtle.oldX, 1e-10);
    assertEquals(0, turtle.oldY, 1e-10);
    assertEquals(10, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that when draw holds old and new coordinates it still works with angles.
   */
  @Test
  public void drawWithAngleHasLineCoords() {
    turtle.setWord("F+F");
    turtle.setLength(10);
    turtle.setAngle(90);

    assertEquals(0, turtle.getCoordX(), 1e-10);
    assertEquals(0, turtle.getCoordY(), 1e-10);
    turtle.rules();
    assertEquals(10, turtle.oldX, 1e-10);
    assertEquals(0, turtle.oldY, 1e-10);
    assertEquals(10, turtle.getCoordX(), 1e-10);
    assertEquals(10, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that the push method works and that the stack is not empty when it is called.
   */
  @Test
  public void simplePush() {
    turtle.setWord("[");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.setCoords(100, 100);
    assertTrue(turtle.pointStack.isEmpty());
    turtle.rules();
    assertFalse(turtle.pointStack.isEmpty());
  }

  /**
   * Tests that the pop method works and pops what it is given when called.
   */
  @Test
  public void simplePushPop() {
    turtle.setWord("[]");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.setCoords(100, 100);
    assertTrue(turtle.pointStack.isEmpty());
    assertEquals(turtle.getCoordX(), 100, 1e-10);
    assertEquals(turtle.getCoordY(), 100, 1e-10);
    turtle.rules();
    assertTrue(turtle.pointStack.isEmpty());
    assertEquals(turtle.getCoordX(), 100, 1e-10);
    assertEquals(turtle.getCoordY(), 100, 1e-10);
  }

  /**
   * Tests that push and draw can work and that push/draw/pop work and given the correct
   * coordinates.
   */
  @Test
  public void pushDrawPop() {
    turtle.setWord("[F");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.setCoords(100, 100);
    assertTrue(turtle.pointStack.isEmpty());
    assertEquals(turtle.getCoordX(), 100, 1e-10);
    assertEquals(turtle.getCoordY(), 100, 1e-10);
    turtle.rules();
    assertFalse(turtle.pointStack.isEmpty());
    assertEquals(turtle.getCoordX(), 110, 1e-10);
    assertEquals(turtle.getCoordY(), 100, 1e-10);

    DeterministicTurtle deterministicTurtle2 = new DeterministicTurtle();
    deterministicTurtle2.setWord("[F]");
    deterministicTurtle2.setLength(10);
    deterministicTurtle2.setAngle(90);
    deterministicTurtle2.setCoords(100, 100);
    assertTrue(deterministicTurtle2.pointStack.isEmpty());
    assertEquals(deterministicTurtle2.getCoordX(), 100, 1e-10);
    assertEquals(deterministicTurtle2.getCoordY(), 100, 1e-10);
    deterministicTurtle2.rules();
    assertTrue(deterministicTurtle2.pointStack.isEmpty());
    assertEquals(deterministicTurtle2.getCoordX(), 100, 1e-10);
    assertEquals(deterministicTurtle2.getCoordY(), 100, 1e-10);
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
   * Tests that generate works when there are multiple rules in the rule-set.
   */
  @Test
  public void testTwoRulesInGenRules() {
    turtle.setWord("FGF");
    turtle.setLength(0);
    turtle.setAngle(0);
    String[] drawRules = {"FF"};
    String[] moveRules = {"GG"};
    turtle.generate(1, drawRules, moveRules);
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
    turtle.generate(1, drawRules, moveRules);
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
    turtle.generate(2, drawRules, moveRules);
    assertEquals("FFFF", turtle.getWord());
  }

  /**
   * Tests that generate works with multiple iterations and more complex strings/rules.
   */
  @Test
  public void testMoreComplextIterations() {
    turtle.setWord("FGF");
    turtle.setLength(0);
    turtle.setAngle(0);
    String[] drawRules = {"FF"};
    String[] moveRules = {"GG"};
    turtle.generate(2, drawRules, moveRules);
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
    turtle.generate(3, drawRules, moveRules);
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
    turtle.generate(1, drawRules, moveRules);

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
    turtle.generate(1, drawRules, moveRules);

    assertEquals("F--F--F--G--F--F--F--G--F--F--F--G", turtle.getWord());

    turtle.reset();

    assertEquals("F--F--F", turtle.getWord());
    assertEquals(5, turtle.getLength(), 1e-10);
    assertEquals(60, turtle.getAngle(), 1e-10);
    assertEquals(250, turtle.getCoordX(), 1e-10);
    assertEquals(300, turtle.getCoordY(), 1e-10);
  }

  /**
   * Tests that the resetBearing method resets the current angle of the turtle back to 0.
   */
  @Test
  public void testResetBearing() {
    turtle.setWord("F--F--F");
    turtle.setLength(5);
    turtle.setAngle(60);
    assertEquals(0, turtle.currAngle, 1e-10);

    turtle.rules();
    assertNotEquals(0, turtle.currAngle, 1e-10);

    turtle.resetBearing();
    assertEquals(0, turtle.currAngle, 1e-10);
  }
}