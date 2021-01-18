import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Deque;

public abstract class Turtle {

  private String initialWord;
  private double initialLength;
  private double initialAngle;
  private double initialCoordX;
  private double initialCoordY;
  private String word = "";
  private double length = 0;
  private double angle = 0;
  double currAngle = 0;
  private double coordX = 0;
  private double coordY = 0;
  private String[] drawRules;
  private String[] moveRules;
  double oldX;
  double oldY;
  private double lowestCoordX;
  private double highestCoordX;
  private double lowestCoordY;
  private double highestCoordY;
  int growthHighX;
  int growthHighY;
  int growthLowX;
  int growthLowY;
  Deque<Point> pointStack = new ArrayDeque<>();

  /**
   * Sets the word of the turtle.
   *
   * @param word is the word to be set to.
   */
  public void setWord(String word) {
    this.word = word;
    //this.initialWord = word;
  }

  /**
   * Sets the length of the turtle.
   *
   * @param length is the length to be set to.
   */
  public void setLength(double length) {
    this.length = length;
    //this.initialLength = length;
  }

  /**
   * Sets the angle of the turtle.
   *
   * @param angle is the angle to be set to.
   */
  public void setAngle(double angle) {
    this.angle = angle;
    //this.initialAngle = angle;
  }

  /**
   * Sets the starting co-ordinates of the turtle.
   *
   * @param x is the starting x co-ordinate.
   * @param y is the startng y co-ordinate.
   */
  public void setCoords(double x, double y) {
    this.coordX = x;
    this.coordY = y;
    //this.initialCoordX = x;
    //this.initialCoordY = y;
  }

  /**
   * Sets the drawing rules for the generation of new L-Systems.
   *
   * @param drawRules is the String array of rules for how the L-System should draw.
   */
  public void setDrawRules(String[] drawRules) {
    this.drawRules = drawRules;
  }

  /**
   * Sets the moving rules for the generation of new L-Systems.
   *
   * @param moveRules is the String array of rules for how the L-System should move.
   */
  public void setMoveRules(String[] moveRules) {
    this.moveRules = moveRules;
  }

  public void saveStartingTurtle() {
    this.initialWord = word;
    this.initialLength = length;
    this.initialAngle = angle;
    this.initialCoordX = coordX;
    this.initialCoordY = coordY;
  }

  /**
   * Getter for word.
   *
   * @return returns the word, the string of the turtle.
   */
  public String getWord() {
    return word;
  }

  /**
   * Getter for length.
   *
   * @return returns the length, the length of the turtle lines.
   */
  public double getLength() {
    return length;
  }

  /**
   * Getter for angle.
   *
   * @return returns the angle.
   */
  public double getAngle() {
    return angle;
  }

  /**
   * Getter for x co-ordinate.
   *
   * @return returns the x co-ordinate.
   */
  public double getCoordX() {
    return coordX;
  }

  /**
   * Getter for y co-ordinate.
   *
   * @return returns the y co-ordinate.
   */
  public double getCoordY() {
    return coordY;
  }

  /**
   * Getter for the array of drawing rules.
   *
   * @return returns the String array of drawing rules.
   */
  public String[] getDrawRules() {
    return drawRules;
  }

  /**
   * Getter for the array of moving rules.
   *
   * @return returns the String array of moving rules.
   */
  public String[] getMoveRules() {
    return moveRules;
  }

  /**
   * Rules class to iterate through the String and tell the program what to do at each character.
   */
  public void rules() {
    for (int i = 0; i < word.length(); i++) {
      char current = word.charAt(i);
      switch (current) {
        case 'F' -> draw(length);
        case 'G' -> move(length);
        case '+' -> rotate(angle);
        case '-' -> rotate(-angle);
        case '[' -> pushCoords();
        case ']' -> popCoords();
        default -> System.out.println("Invalid character");
      }
    }
  }

  /**
   * Stores the original x and y coordinates and then transforms the new ones by adding them to
   * length multiplied with the sin/cos of currAngle so as to give it a distance to move and a
   * direction for it to move to.
   *
   * The method also sets the highest and lowest coordinates which are used to help centre the object in the middle of the screen.
   *
   * @param length is the length for the coordinates to move.
   */
  public void draw(double length) {
    //REFACTOR OUT COMPARING HIGHEST/LOWEST COORDS
    if (coordX > highestCoordX) {
      highestCoordX = coordX;
    }
    if (coordX < lowestCoordX) {
      lowestCoordX = coordX;
    }
    if (coordY > highestCoordY) {
      highestCoordY = coordY;
    }
    if (coordY < lowestCoordY) {
      lowestCoordY = coordY;
    }

    oldX = coordX;
    oldY = coordY;
    coordX += (length * Math.cos(currAngle));
    coordY += (length * Math.sin(currAngle));
    Line l = new Line(oldX, oldY, coordX, coordY);
    l.createLine();

    if (coordX > highestCoordX) {
      highestCoordX = coordX;
    }
    if (coordX < lowestCoordX) {
      lowestCoordX = coordX;
    }
    if (coordY > highestCoordY) {
      highestCoordY = coordY;
    }
    if (coordY < lowestCoordY) {
      lowestCoordY = coordY;
    }
    //System.out.println("Highest X " + highestCoordX);
    //System.out.println("Highest Y " + highestCoordY);
    //System.out.println("Lowest X " + lowestCoordX);
    //System.out.println("Lowest Y " + lowestCoordY);
  }

  /**
   * Moves the coordinates by the given length multiplied by the given angle but does not draw
   * anything.
   *
   * @param length is the distance to move.
   */
  public void move(double length) {
    coordX += (length * Math.cos(currAngle));
    coordY += (length * Math.sin(currAngle));
  }

  /**
   * Changes the current angle of the line by adding the radian version of the given angle.
   *
   * @param angle is the angle to rotate the coordinates.
   */
  public void rotate(double angle) {
    currAngle += Math.toRadians(angle);
  }

  /**
   * Creates a Point object with the coordinates taken at the time the [ is used and then pushes
   * them into a stack.
   */
  public void pushCoords() {
    Point pushP = new Point();
    pushP.setLocation(coordX, coordY);
    pointStack.push(pushP);
  }

  /**
   * Pops the point from the stack and then sets the coordinates back to those that were pushed
   * into the stack.
   */
  public void popCoords() {
    Point popP = pointStack.pop();
    coordX =  popP.getX();
    coordY =  popP.getY();
  }

  abstract void generate(int iterations, String[] drawRules, String[] moveRules);

  /**
   * Resets the turtle back to the original inputs.
   */
  public void reset() {
    this.word = initialWord;
    this.length = initialLength;
    this.angle = initialAngle;
    this.coordX = initialCoordX;
    this.coordY = initialCoordY;
    this.currAngle = 0;
    resetHighLow();
    resetBearing();
  }

  /**
   * Sets the currAngle to 0, ensuring that the starting position is the same each time the program
   * is run.
   */
  public void resetBearing() {
    this.currAngle = 0;
  }

  public void centre() {
    System.out.println("Highest X " + highestCoordX);
    System.out.println("Highest Y " + highestCoordY);
    System.out.println("Lowest X " + lowestCoordX);
    System.out.println("Lowest Y " + lowestCoordY);

    double middleX = (highestCoordX - lowestCoordX) / 2;
    double middleY = (highestCoordY - lowestCoordY) / 2;
    double frameMidX = (double) Main.frameWidth / 2;
    double frameMidY = (double) Main.frameHeight / 2;

    System.out.println("Mid X coord = " + middleX);
    System.out.println("Mid Y coord = " + middleY);

    this.coordX = frameMidX - middleX;
    this.coordY = frameMidY + middleY;

    System.out.println("Coord X = " + coordX);
    System.out.println("Coord Y = " + coordY);

    //System.out.println("mid frame X - mid X = " + (frameMidX - middleX));
    //System.out.println("mid frame Y - mid Y = " + (frameMidY - middleY));

    System.out.println("\n");
  }

  public void resetHighLow() {
    highestCoordX = 0;
    highestCoordY = 0;
    lowestCoordX = 1 * 10e10;
    lowestCoordY = 1 * 10e10;
  }

  public void growthDirection() {
    /**
     *
    int startCoordX = (int) initialCoordX;
    int startCoordY = (int) initialCoordY;
    rules();
    int endCoordX = (int) coordX;
    int endCoordY = (int) coordY;

    if (endCoordX > startCoordX) {
      growthX = 1;
    } else if (endCoordX < startCoordX) {
      growthX = -1;
    } else {
      growthX = 0;
    }

    if (endCoordY > startCoordY) {
      growthY = 1;
    } else if (endCoordY < startCoordY) {
      growthY = -1;
    } else {
      growthY = 0;
    }
*/



    int startCoordHighX = (int) coordX;
    int startCoordHighY = (int) coordY;
    int startCoordLowX = (int) coordX;
    int startCoordLowY = (int) coordY;
    rules();
    int endCoordHighX = (int) highestCoordX;
    int endCoordHighY = (int) highestCoordY;
    int endCoordLowX = (int) lowestCoordX;
    int endCoordLowY = (int) lowestCoordY;


    growthHighX = Integer.compare(endCoordHighX, startCoordHighX);
    growthHighY = Integer.compare(endCoordHighY, startCoordHighY);
    growthLowX = Integer.compare(startCoordLowX, endCoordLowX);
    growthLowY = Integer.compare(startCoordLowY, endCoordLowY);
  }
}