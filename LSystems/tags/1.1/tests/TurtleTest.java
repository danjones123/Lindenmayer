import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Testing class for the Turtle class.
 *
 * @author Daniel Jones.
 */

public class TurtleTest {


  /**
   * Tests to make sure the values of a Turtle with three parameters is constructed properly.
   */
  @Test
  public void testTurtleThreeConst() {
    Turtle turtle3 = new Turtle("FGF+-[]", 7, 3.14);
    assertEquals("FGF+-[]", turtle3.word);
    assertEquals(7, turtle3.length, 1e-10);
    assertEquals(3.14, turtle3.angle, 1e-10);
    assertEquals(0, turtle3.coordX, 1e-10);
    assertEquals(0, turtle3.coordY, 1e-10);
    assertEquals(0, turtle3.currAngle, 1e-10);
  }

  /**
   * Tests to make sure the values of a Turtle with five parameters is constructed properly.
   */
  @Test
  public void testTurtleFiveConst() {
    Turtle turtle5 = new Turtle("FGF+-[]", 7, 3.14, 150, 100);
    assertEquals("FGF+-[]", turtle5.word);
    assertEquals(7, turtle5.length, 1e-10);
    assertEquals(3.14, turtle5.angle, 1e-10);
    assertEquals(150, turtle5.coordX, 1e-10);
    assertEquals(100, turtle5.coordY, 1e-10);
    assertEquals(0, turtle5.currAngle, 1e-10);
  }

  /**
   * Tests that the coordinates of the turtle are changed when a 'F' is given and draw is called.
   */
  @Test
  public void testDrawLength() {
    Turtle turtleDraw = new Turtle("F", 10, 90);
    turtleDraw.rules();
    assertEquals(10, turtleDraw.coordX, 1e-10);
    assertEquals(0, turtleDraw.coordY, 1e-10);
  }

  /**
   * Tests that the coordiantes of the turtle are changed multiple times when multiple F's are
   * given.
   */
  @Test
  public void testMultipleDraw() {
    Turtle multipleDraw = new Turtle("FFF", 10, 90);
    assertEquals(0, multipleDraw.coordX, 1e-10);
    assertEquals(0, multipleDraw.coordY, 1e-10);
    multipleDraw.rules();
    assertEquals(30, multipleDraw.coordX, 1e-10);
    assertEquals(0, multipleDraw.coordY, 1e-10);
  }

  /**
   * Tests that the move method words when a 'G' is given.
   */
  @Test
  public void testMove() {
    Turtle turtleMove = new Turtle("G", 10, 90);
    turtleMove.rules();
    assertEquals(10, turtleMove.coordX, 1e-10);
    assertEquals(0, turtleMove.coordY, 1e-10);
  }

  /**
   * Tests that the move method works with multiple G's given.
   */
  @Test
  public void testMultipleMove() {
    Turtle multipleMove = new Turtle("GGG", 10, 90);
    assertEquals(0, multipleMove.coordX, 1e-10);
    assertEquals(0, multipleMove.coordY, 1e-10);
    multipleMove.rules();
    assertEquals(30, multipleMove.coordX, 1e-10);
    assertEquals(0, multipleMove.coordY, 1e-10);
  }

  /**
   * Tests that the turtle coordinates are what they shoudl be when draw and move are both called.
   */
  @Test
  public void testDrawMoveDraw() {
    Turtle multipleDrawMoveDraw = new Turtle("FGF", 10, 90);
    assertEquals(0, multipleDrawMoveDraw.coordX, 1e-10);
    assertEquals(0, multipleDrawMoveDraw.coordY, 1e-10);
    multipleDrawMoveDraw.rules();
    assertEquals(30, multipleDrawMoveDraw.coordX, 1e-10);
    assertEquals(0, multipleDrawMoveDraw.coordY, 1e-10);
  }

  /**
   * Tests that rotate changes the currAngle in turtle and does not change the global angle.
   */
  @Test
  public void testPositiveRotate() {
    Turtle posRotate = new Turtle("+", 10, 90);
    assertEquals(90, posRotate.angle, 1e-10);
    assertEquals(0, posRotate.currAngle, 1e-10);
    posRotate.rules();
    assertEquals(90, posRotate.angle, 1e-10);
    assertEquals(Math.toRadians(90), posRotate.currAngle, 1e-10);
  }

  /**
   * Tests that rotate also works with negative values.
   */
  @Test
  public void testNegativeRotate() {
    Turtle negRotate = new Turtle("-", 10, 90);
    assertEquals(90, negRotate.angle, 1e-10);
    assertEquals(0, negRotate.currAngle, 1e-10);
    negRotate.rules();
    assertEquals(90, negRotate.angle, 1e-10);
    assertEquals(Math.toRadians(-90), negRotate.currAngle, 1e-10);
  }

  /**
   * Tests that a negative and positive rotation cancel each other out, as they should.
   */
  @Test
  public void cancelRotations() {
    Turtle cancelRotate = new Turtle("+-", 10, 90);
    assertEquals(90, cancelRotate.angle, 1e-10);
    assertEquals(0, cancelRotate.currAngle, 1e-10);
    cancelRotate.rules();
    assertEquals(90, cancelRotate.angle, 1e-10);
    assertEquals(0, cancelRotate.currAngle, 1e-10);
  }

  /**
   * Tests that the currAngle is being used to allow a draw/rotate/draw to work.
   */
  @Test
  public void drawingWithAngle() {
    Turtle drawingWithAngle = new Turtle("F+F", 10, 90);
    assertEquals(0, drawingWithAngle.coordX, 1e-10);
    assertEquals(0, drawingWithAngle.coordY, 1e-10);
    assertEquals(90, drawingWithAngle.angle, 1e-10);
    assertEquals(0, drawingWithAngle.currAngle, 1e-10);
    drawingWithAngle.rules();
    assertEquals(10, drawingWithAngle.coordX, 1e-10);
    assertEquals(10, drawingWithAngle.coordX, 1e-10);
  }

  /**
   * Tests that draw can work with multiple angles being called.
   */
  @Test
  public void drawingWithTwoAngle() {
    Turtle drawingWithTwoAngle = new Turtle("F+F+F", 10, 90);
    assertEquals(0, drawingWithTwoAngle.coordX, 1e-10);
    assertEquals(0, drawingWithTwoAngle.coordY, 1e-10);
    assertEquals(90, drawingWithTwoAngle.angle, 1e-10);
    assertEquals(0, drawingWithTwoAngle.currAngle, 1e-10);
    drawingWithTwoAngle.rules();
    assertEquals(0, drawingWithTwoAngle.coordX, 1e-10);
    assertEquals(10, drawingWithTwoAngle.coordY, 1e-10);
  }

  /**
   * Tests that if a square is given that the starting and ending co-ordinates are the same.
   */
  @Test
  public void sqaureCoords() {
    Turtle squareCoords = new Turtle("F+F+F+F", 10, 90);
    assertEquals(0, squareCoords.coordX, 1e-10);
    assertEquals(0, squareCoords.coordY, 1e-10);
    assertEquals(90, squareCoords.angle, 1e-10);
    assertEquals(0, squareCoords.currAngle, 1e-10);
    squareCoords.rules();
    assertEquals(0, squareCoords.coordX, 1e-10);
    assertEquals(0, squareCoords.coordY, 1e-10);
  }

  /**
   * Tests that the square also works with negative values.
   */
  @Test
  public void negativeSquare() {
    Turtle negativeSquare = new Turtle("F-F-F-F", 10, 90);
    assertEquals(0, negativeSquare.coordX, 1e-10);
    assertEquals(0, negativeSquare.coordY, 1e-10);
    assertEquals(90, negativeSquare.angle, 1e-10);
    assertEquals(0, negativeSquare.currAngle, 1e-10);
    negativeSquare.rules();
    assertEquals(0, negativeSquare.coordX, 1e-10);
    assertEquals(0, negativeSquare.coordY, 1e-10);
  }

  /**
   * Tests that draw records both old and new coordinates as it needs both to draw a line.
   */
  @Test
  public void drawHasLineCoords() {
    Turtle drawHasLineCoords = new Turtle("F", 10, 90);
    assertEquals(0, drawHasLineCoords.coordX, 1e-10);
    assertEquals(0, drawHasLineCoords.coordY, 1e-10);
    drawHasLineCoords.rules();
    assertEquals(0, drawHasLineCoords.oldX, 1e-10);
    assertEquals(0, drawHasLineCoords.oldY, 1e-10);
    assertEquals(10, drawHasLineCoords.coordX, 1e-10);
    assertEquals(0, drawHasLineCoords.coordY, 1e-10);
  }

  /**
   * Tests that when draw holds old and new coordinates it still works with angles.
   */
  @Test
  public void drawWithAngleHasLineCoords() {
    Turtle drawWithAngleHasLineCoords = new Turtle("F+F", 10, 90);
    assertEquals(0, drawWithAngleHasLineCoords.coordX, 1e-10);
    assertEquals(0, drawWithAngleHasLineCoords.coordY, 1e-10);
    drawWithAngleHasLineCoords.rules();
    assertEquals(10, drawWithAngleHasLineCoords.oldX, 1e-10);
    assertEquals(0, drawWithAngleHasLineCoords.oldY, 1e-10);
    assertEquals(10, drawWithAngleHasLineCoords.coordX, 1e-10);
    assertEquals(10, drawWithAngleHasLineCoords.coordY, 1e-10);
  }

  /**
   * Tests that the push method works and that the stack is not empty when it is called.
   */
  @Test
  public void simplePush() {
    Turtle simplePush = new Turtle("[", 10, 90, 100, 100);
    assertTrue(simplePush.stack.isEmpty());
    simplePush.rules();
    assertFalse(simplePush.stack.isEmpty());
  }

  /**
   * Tests that the pop method works and pops what it is given when called.
   */
  @Test
  public void simplePushPop() {
    Turtle simplePushPop = new Turtle("[]", 10, 90, 100, 100);
    assertTrue(simplePushPop.stack.isEmpty());
    assertEquals(simplePushPop.coordX, 100, 1e-10);
    assertEquals(simplePushPop.coordY, 100, 1e-10);
    simplePushPop.rules();
    assertTrue(simplePushPop.stack.isEmpty());
    assertEquals(simplePushPop.coordX, 100, 1e-10);
    assertEquals(simplePushPop.coordY, 100, 1e-10);
  }

  /**
   * Tests that push and draw can work and that push/draw/pop work and given the correct
   * coordinates.
   */
  @Test
  public void pushDrawPop() {
    Turtle pushDrawPop = new Turtle("[F", 10, 90, 100, 100);
    assertTrue(pushDrawPop.stack.isEmpty());
    assertEquals(pushDrawPop.coordX, 100, 1e-10);
    assertEquals(pushDrawPop.coordY, 100, 1e-10);
    pushDrawPop.rules();
    assertFalse(pushDrawPop.stack.isEmpty());
    assertEquals(pushDrawPop.coordX, 110, 1e-10);
    assertEquals(pushDrawPop.coordY, 100, 1e-10);

    Turtle pushDrawPop2 = new Turtle("[F]", 10, 90, 100, 100);
    assertTrue(pushDrawPop2.stack.isEmpty());
    assertEquals(pushDrawPop2.coordX, 100, 1e-10);
    assertEquals(pushDrawPop2.coordY, 100, 1e-10);
    pushDrawPop2.rules();
    assertTrue(pushDrawPop2.stack.isEmpty());
    assertEquals(pushDrawPop2.coordX, 100, 1e-10);
    assertEquals(pushDrawPop2.coordY, 100, 1e-10);
  }

  /**
   * Tests that the generate method works for turning a character into a given string.
   */
  @Test
  public void testSimpleDrawGenerate() {
    Turtle testSimpleDrawGenerate = new Turtle("F", 0, 0);
    String[] genRules = {"FFF"};
    testSimpleDrawGenerate.generate(1, genRules);
    assertEquals("FFF", testSimpleDrawGenerate.word);
  }

  /**
   * Tests that generate works with more complicated rules.
   */
  @Test
  public void testMoreComplexDrawGenerate() {
    Turtle testMoreComplexDrawGenerate = new Turtle("F", 0, 0);
    String[] genRules = {"F[F+F+F]F"};
    testMoreComplexDrawGenerate.generate(1, genRules);
    assertEquals("F[F+F+F]F", testMoreComplexDrawGenerate.word);
  }

  /**
   * Tests that generate works when there are multiple rules in the rule-set.
   */
  @Test
  public void testTwoRulesInGenRules() {
    Turtle testMoreComplexDrawGenerate = new Turtle("FGF", 0, 0);
    String[] genRules = {"FF", "GG"};
    testMoreComplexDrawGenerate.generate(1, genRules);
    assertEquals("FFGGFF", testMoreComplexDrawGenerate.word);
  }

  /**
   * Tests that generate can use complex strings and rules to give the correct output.
   */
  @Test
  public void testComplexMultipleRules() {
    Turtle testMoreComplexDrawGenerate = new Turtle("FF+[F+F+FGGF]", 0, 0);
    String[] genRules = {"F+G+F", "GGGGGGGG"};
    testMoreComplexDrawGenerate.generate(1, genRules);
    assertEquals("F+G+FF+G+F+[F+G+F+F+G+F+F+G+FGGGGGGGGGGGGGGGGF+G+F]",
        testMoreComplexDrawGenerate.word);
  }

  /**
   * Tests that generate works with multiple iterations.
   */
  @Test
  public void testMoreIterations() {
    Turtle testMoreIterations = new Turtle("F", 0, 0);
    String[] genRules = {"FF", "GG"};
    testMoreIterations.generate(2, genRules);
    assertEquals("FFFF", testMoreIterations.word);
  }

  /**
   * Tests that generate works with multiple iterations and more complex strings/rules.
   */
  @Test
  public void testMoreComplextIterations() {
    Turtle testMoreComplexIterations = new Turtle("FGF", 0, 0);
    String[] genRules = {"FF", "GG"};
    testMoreComplexIterations.generate(2, genRules);
    assertEquals("FFFFGGGGFFFF", testMoreComplexIterations.word);
  }

  /**
   * Tests that given the rules for a Sierpinski triangle the generate method returns what it
   * should.
   */
  @Test
  public void testSierpinskiTriangle() {
    Turtle testSierpinskiTriangle = new Turtle("F--F--F", 5, 60);
    String[] genRules = {"F--F--F--G", "GG"};
    testSierpinskiTriangle.generate(3, genRules);
    assertEquals("F--F--F--G--F--F--F--G--F--F--F--G--GG--F--F--F--G--F--F--F--G--F"
           + "--F--F--G--GG--F--F--F--G--F--F--F--G--F--F--F--G--GG--GGGG--F--F--F--G--F--F"
           + "--F--G--F--F--F--G--GG--F--F--F--G--F--F--F--G--F--F--F--G--GG--F--F--F--G--F"
           + "--F--F--G--F--F--F--G--GG--GGGG--F--F--F--G--F--F--F--G--F--F--F--G--GG--F--F"
           + "--F--G--F--F--F--G--F--F--F--G--GG--F--F--F--G--F--F--F--G--F--F--F--G--GG--GGGG",
        testSierpinskiTriangle.word);
  }
}