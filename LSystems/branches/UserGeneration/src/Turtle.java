import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class using turtle interpretation to display an LSystem.
 *
 * @author Daniel Jones
 */
public class Turtle {
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
  private String[] genRules;
  double oldX;
  double oldY;
  Deque<Point> pointStack = new ArrayDeque<>();

  /**
   * Sets the word of the turtle.
   *
   * @param word is the word to be set to.
   */
  public void setWord(String word) {
    this.word = word;
    this.initialWord = word;
  }

  /**
   * Sets the length of the turtle.
   *
   * @param length is the length to be set to.
   */
  public void setLength(double length) {
    this.length = length;
    this.initialLength = length;
  }

  /**
   * Sets the angle of the turtle.
   *
   * @param angle is the angle to be set to.
   */
  public void setAngle(double angle) {
    this.angle = angle;
    this.initialAngle = angle;
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
    this.initialCoordX = x;
    this.initialCoordY = y;
  }

  /**
   * Sets the rules for the generation of new L-Systems.
   *
   * @param genRules is the String array of rules for how the L-System should develop.
   */
  public void setGenRules(String[] genRules) {
    this.genRules = genRules;
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
   * Getter for the array of generation rules.
   *
   * @return returns the String array of generation rules.
   */
  public String[] getGenRules() {
    return genRules;
  }

  /**
   * Empty constructor allowing a new Turtle to be called from other classes.
   */
  public Turtle() {
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
   * @param length is the length for the coordinates to move.
   */
  public void draw(double length) {
    oldX = coordX;
    oldY = coordY;
    coordX += (length * Math.cos(currAngle));
    coordY += (length * Math.sin(currAngle));
    Line l = new Line(oldX, oldY, coordX, coordY);
    l.createLine();
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

  /**
   * Iterates through the string and creates a new string by applying the given rules.
   *
   * @param iterations is the number of times to iterate through the string.
   * @param genRules is an array of rules to be applied to the given characters.
   */
  public void generate(int iterations, String[] genRules) {
    String nextWord = word;
    StringBuilder next = new StringBuilder();
    for (int j = 0; j < iterations; j++) {
      for (int i = 0; i < nextWord.length(); i++) {
        char c = nextWord.charAt(i);
        switch (c) {
          case('F') -> next.append(genRules[0]);
          case('G') -> next.append(genRules[1]);
          default -> next.append(c);
        }
      }
      nextWord = next.toString();
      next.setLength(0);
    }
    word = nextWord;
  }

  /**
   * Resets the turtle back to the original inputs.
   */
  public void reset() {
    this.word = initialWord;
    this.length = initialLength;
    this.angle = initialAngle;
    this.coordX = initialCoordX;
    this.coordY = initialCoordY;
  }
}