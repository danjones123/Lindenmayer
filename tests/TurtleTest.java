import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

  @Test
  public void testDrawLength() {
    Turtle turtleDraw = new Turtle("F", 10, 90);
    turtleDraw.rules();
    assertEquals(10, turtleDraw.coordX, 1e-10);
    assertEquals(0, turtleDraw.coordY, 1e-10);
  }

  @Test
  public void testMultipleDraw() {
    Turtle multipleDraw = new Turtle("FFF", 10, 90);
    assertEquals(0, multipleDraw.coordX, 1e-10);
    assertEquals(0, multipleDraw.coordY, 1e-10);
    multipleDraw.rules();
    assertEquals(30, multipleDraw.coordX, 1e-10);
    assertEquals(0, multipleDraw.coordY, 1e-10);

  }



  @Test
  public void testMove() {
    Turtle turtleMove = new Turtle("G", 10, 90);
    turtleMove.rules();
    assertEquals(10, turtleMove.coordX, 1e-10);
    assertEquals(0, turtleMove.coordY, 1e-10);
  }

  @Test
  public void testMultipleMove() {
    Turtle multipleMove = new Turtle("GGG", 10, 90);
    assertEquals(0, multipleMove.coordX, 1e-10);
    assertEquals(0, multipleMove.coordY, 1e-10);
    multipleMove.rules();
    assertEquals(30, multipleMove.coordX, 1e-10);
    assertEquals(0, multipleMove.coordY, 1e-10);
  }

  @Test
  public void testDrawMoveDraw() {
    Turtle multipleDrawMoveDraw = new Turtle("FGF", 10, 90);
    assertEquals(0, multipleDrawMoveDraw.coordX, 1e-10);
    assertEquals(0, multipleDrawMoveDraw.coordY, 1e-10);
    multipleDrawMoveDraw.rules();
    assertEquals(30, multipleDrawMoveDraw.coordX, 1e-10);
    assertEquals(0, multipleDrawMoveDraw.coordY, 1e-10);
  }

  @Test
  public void testPositiveRotate() {
    Turtle posRotate = new Turtle("+", 10, 90);
    assertEquals(90, posRotate.angle, 1e-10);
    assertEquals(0, posRotate.currAngle, 1e-10);
    posRotate.rules();
    assertEquals(90, posRotate.angle, 1e-10);
    assertEquals(Math.toRadians(90), posRotate.currAngle, 1e-10);
  }

  @Test
  public void testNegativeRotate() {
    Turtle negRotate = new Turtle("-", 10, 90);
    assertEquals(90, negRotate.angle, 1e-10);
    assertEquals(0, negRotate.currAngle, 1e-10);
    negRotate.rules();
    assertEquals(90, negRotate.angle, 1e-10);
    assertEquals(Math.toRadians(-90), negRotate.currAngle, 1e-10);
  }

  @Test
  public void cancelRotations() {
    Turtle cancelRotate = new Turtle("+-", 10, 90);
    assertEquals(90, cancelRotate.angle, 1e-10);
    assertEquals(0, cancelRotate.currAngle, 1e-10);
    cancelRotate.rules();
    assertEquals(90, cancelRotate.angle, 1e-10);
    assertEquals(0, cancelRotate.currAngle, 1e-10);
  }

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


  @Test
  public void simplePush() {
    Turtle simplePush = new Turtle("[", 10, 90, 100, 100);
    assertTrue(simplePush.stack.isEmpty());
    simplePush.rules();
    assertFalse(simplePush.stack.isEmpty());
  }

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
   * Tests to make sure the switch statement in rules for F goes to the draw method.
   */
  //  @Test
 // public void testRulesCallsDraw() {
  //  Turtle drawTurtle = new Turtle("F", 5, 90, 150, 150);
    //assertEquals(drawTurtle.coordX, 150, 1e-15);
    //assertEquals(drawTurtle.coordY, 150, 1e-15);
    //drawTurtle.rules();
    //assertEquals(drawTurtle.coordX, 150 + (5 * Math.cos(90)), 1e-15);
    //assertEquals(drawTurtle.coordY, 150 + (5 * Math.sin(90)), 1e-15);
  //}

  /**
   * Tests to make sure the switch statement in rules calls draw twice if there are two 'F's.
   */
  //@Test
  //public void testRulesCallsDrawTwice() {
  //  Turtle drawTwoTurtle = new Turtle("FF", 5, 90, 150, 150);
  //  assertEquals(drawTwoTurtle.coordX, 150, 1e-15);
   // assertEquals(drawTwoTurtle.coordY, 150, 1e-15);
   // drawTwoTurtle.rules();
   // assertEquals(drawTwoTurtle.coordX, 150 + (5 * Math.cos(90) + (5 * Math.cos(90))), 1e-15);
   // assertEquals(drawTwoTurtle.coordY, 150 + (5 * Math.sin(90) + (5 * Math.sin(90))), 1e-15);
  //}

  /**
   * Tests to make sure that rules calls move when given a G.
   */
  //@Test
  //public void testRulesCallsMove() {
  //  Turtle moveTurtle = new Turtle("G", 5, 90, 150, 150);
  //  assertEquals(moveTurtle.coordX, 150, 1e-15);
   // assertEquals(moveTurtle.coordY, 150, 1e-15);
   // moveTurtle.rules();
   // assertEquals(moveTurtle.coordX, 150 + (5 * Math.cos(90)), 1e-15);
   // assertEquals(moveTurtle.coordY, 150 + (5 * Math.sin(90)), 1e-15);
 // }

  /**
   * Test to make sure that rules calls draw twice if there are two 'G's.
   */
 // @Test
 // public void testRulesCallsMoveTwice() {
 //   Turtle moveTwoTurtle = new Turtle("GG", 5, 90, 150, 150);
  //  assertEquals(moveTwoTurtle.coordX, 150, 1e-15);
  //  assertEquals(moveTwoTurtle.coordY, 150, 1e-15);
  //  moveTwoTurtle.rules();
  //  assertEquals(moveTwoTurtle.coordX, 150 + (5 * Math.cos(90) + (5 * Math.cos(90))), 1e-15);
  //  assertEquals(moveTwoTurtle.coordY, 150 + (5 * Math.sin(90) + (5 * Math.sin(90))), 1e-15);
 // }

  /**
   * Tests that the rotation is applied when a '+' is given.
   */
 // @Test
 // public void testRulesCallsRotate() {
 //   Turtle rotateTurtle = new Turtle("+", 5, 90, 150, 150);
 //   assertEquals(rotateTurtle.angle, 90, 1e-15);
 //   rotateTurtle.rules();
 //   assertEquals(rotateTurtle.angle, 90 + Math.toRadians(90), 1e-15);
 // }

  /**
   * Tests that a negative rotation happens when '-' is given.
   */
 // @Test
 // public void testRulesCallsNegativeRotate() {
 //   Turtle negativeRotateTurtle = new Turtle("-", 5, 90, 150, 150);
 //   assertEquals(negativeRotateTurtle.angle, 90, 1e-15);
 //   negativeRotateTurtle.rules();
 //   assertEquals(negativeRotateTurtle.angle, Math.toRadians(90) + Math.toRadians(-90), 1e-15);
 // }

  /**
   * Test to make sure that when a square is given, the starting point is equal to the
   * finishing point.
   */
  //@Test
  //public void testSquare() {
  //  Turtle square = new Turtle("F+F+F+F", 20, 90, 100, 100);
  //  assertEquals(square.coordX, 100, 1e-15);
  //  assertEquals(square.coordY, 100, 1e-15);
  //  square.rules();
  //  assertEquals(square.coordX, 100, 1e-15);
  //  assertEquals(square.coordY, 100, 1e-15);
  //}
}