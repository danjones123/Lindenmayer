import org.junit.Test;

import static org.junit.Assert.*;

public class TurtleTest {

  Turtle turtle = new Turtle();

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
   * Tests that getMiddleX and Y both return the midpoint.
   */
  @Test
  public void testGetMiddle() {
    turtle.setWord("F+F+F+F");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.setCoords(20, 20);
    turtle.rules();
    turtle.centre();
    assertEquals(25, turtle.getMiddleX(), 1e-10);
    assertEquals(25, turtle.getMiddleY(), 1e-10);
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
   * Tests that the coordinates of the turtle are changed multiple times when multiple F's are
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
   * Tests that the turtle coordinates are what they should be when draw and move are both called.
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
  public void squareCoords() {
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

    Turtle turtle2 = new Turtle();
    turtle2.setWord("[F]");
    turtle2.setLength(10);
    turtle2.setAngle(90);
    turtle2.setCoords(100, 100);
    assertTrue(turtle2.pointStack.isEmpty());
    assertEquals(turtle2.getCoordX(), 100, 1e-10);
    assertEquals(turtle2.getCoordY(), 100, 1e-10);
    turtle2.rules();
    assertTrue(turtle2.pointStack.isEmpty());
    assertEquals(turtle2.getCoordX(), 100, 1e-10);
    assertEquals(turtle2.getCoordY(), 100, 1e-10);
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

  /**
   * Tests that the push and pop function of the Turtle works as intended and overwrites the current turtle
   */
  @Test
  public void pushPopTurtle() {
    turtle.setWord("FFF");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.pushTurtle();
    turtle.setWord("ABCD");
    turtle.setLength(90);
    turtle.setAngle(180);
    assertEquals("ABCD", turtle.getWord());
    assertEquals(90, turtle.getLength(), 1e-10);
    assertEquals(180, turtle.getAngle(), 1e-10);
    turtle.popTurtle();
    assertEquals("FFF", turtle.getWord());
    assertEquals(10, turtle.getLength(), 1e-10);
    assertEquals(90, turtle.getAngle(), 1e-10);
  }

  /**
   * Tests to see that moving one space in any direction the turtle goes to the centre.
   */
  @Test
  public void testSimpleCentre() {
    turtle.setWord("F");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.setCoords(400,400);
    turtle.saveStartingTurtle();
    turtle.rules();
    turtle.centre();
    turtle.resetHighLow();
    turtle.rules();
    assertEquals((double) ((Main.frameWidth)/2)-5, turtle.startingCoordX, 1e-10);
    assertEquals((double) Main.frameHeight/2, turtle.startingCoordY, 1e-10);
    assertEquals((double) ((Main.frameWidth)/2), turtle.getMiddleX(), 1e-10);
    assertEquals((double) Main.frameHeight/2, turtle.getMiddleY(), 1e-10);

    turtle.reset();
    turtle.setWord("+F");
    turtle.saveStartingTurtle();
    turtle.rules();
    turtle.centre();
    turtle.resetHighLow();
    turtle.resetBearing();
    turtle.rules();
    assertEquals((double) Main.frameWidth/2, turtle.startingCoordX, 1e-10);
    assertEquals((double) ((Main.frameHeight)/2)-5, turtle.startingCoordY, 1e-10);
    assertEquals((double) ((Main.frameWidth)/2), turtle.getMiddleX(), 1e-10);
    assertEquals((double) Main.frameHeight/2, turtle.getMiddleY(), 1e-10);

    turtle.reset();
    turtle.setWord("-F");
    turtle.saveStartingTurtle();
    turtle.rules();
    turtle.centre();
    turtle.resetHighLow();
    turtle.resetBearing();
    turtle.rules();
    assertEquals((double) ((Main.frameHeight)/2), turtle.startingCoordX, 1e-10);
    assertEquals((double) ((Main.frameHeight)/2)+5, turtle.startingCoordY, 1e-10);
    assertEquals((double) ((Main.frameWidth)/2), turtle.getMiddleX(), 1e-10);
    assertEquals((double) Main.frameHeight/2, turtle.getMiddleY(), 1e-10);

    turtle.reset();
    turtle.setWord("--F");
    turtle.saveStartingTurtle();
    turtle.rules();
    turtle.centre();
    turtle.resetHighLow();
    turtle.resetBearing();
    turtle.rules();
    assertEquals((double) ((Main.frameHeight)/2)+5, turtle.startingCoordX, 1e-10);
    assertEquals((double) ((Main.frameHeight)/2), turtle.startingCoordY, 1e-10);
    assertEquals((double) ((Main.frameWidth)/2), turtle.getMiddleX(), 1e-10);
    assertEquals((double) Main.frameHeight/2, turtle.getMiddleY(), 1e-10);

    turtle.reset();
    turtle.setWord("+F");
    turtle.setLength(10);
    turtle.setAngle(45);
    turtle.saveStartingTurtle();
    turtle.rules();
    turtle.centre();
    turtle.resetHighLow();
    turtle.resetBearing();
    turtle.rules();
    assertEquals((double) ((Main.frameWidth)/2), turtle.getMiddleX(), 1e-10);
    assertEquals((double) Main.frameHeight/2, turtle.getMiddleY(), 1e-10);

    turtle.reset();
    turtle.setWord("-F");
    turtle.saveStartingTurtle();
    turtle.rules();
    turtle.centre();
    turtle.resetHighLow();
    turtle.resetBearing();
    turtle.rules();
    assertEquals((double) ((Main.frameWidth)/2), turtle.getMiddleX(), 1e-10);
    assertEquals((double) Main.frameHeight/2, turtle.getMiddleY(), 1e-10);

    turtle.reset();
    turtle.setWord("---F");
    turtle.saveStartingTurtle();
    turtle.rules();
    turtle.centre();
    turtle.resetHighLow();
    turtle.resetBearing();
    turtle.rules();
    assertEquals((double) ((Main.frameWidth)/2), turtle.getMiddleX(), 1e-10);
    assertEquals((double) Main.frameHeight/2, turtle.getMiddleY(), 1e-10);

    turtle.reset();
    turtle.setWord("+++F");
    turtle.saveStartingTurtle();
    turtle.rules();
    turtle.centre();
    turtle.resetHighLow();
    turtle.resetBearing();
    turtle.rules();
    assertEquals((double) ((Main.frameWidth)/2), turtle.getMiddleX(), 1e-10);
    assertEquals((double) Main.frameHeight/2, turtle.getMiddleY(), 1e-10);
  }

  /**
   * Tests that the calcHighLow class works
   */
  @Test
  public void testCalculateHighLow() {
    turtle.setWord("F+F+F+F");
    turtle.setLength(10);
    turtle.setAngle(90);
    turtle.setCoords(20, 20);
    turtle.rules();
    turtle.calcHighLowCoord();
    assertEquals(20, turtle.lowestCoordX, 1e-10);
    assertEquals(20, turtle.lowestCoordY, 1e-10);
    assertEquals(30, turtle.highestCoordX, 1e-10);
    assertEquals(30, turtle.highestCoordY, 1e-10);
  }
}