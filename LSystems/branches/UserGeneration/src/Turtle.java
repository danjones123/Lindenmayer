import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class for attempting to use the turtle interpretation to display an LSystem.
 *
 * @author Daniel Jones
 */
public class Turtle {
  String initialWord;
  double initialLength;
  double initialAngle;
  double initialCoordX;
  double initialCoordY;
  String word;
  double length;
  double angle;
  double currAngle = 0;
  double coordX;
  double coordY;
  double oldX;
  double oldY;
  Deque<Point> stack = new ArrayDeque<>();

  /**
   * Constructs a turtle that takes parameters word, length, angle, coordX and coordY.
   * Also creates the variables for the initial turtle.
   *
   * @param word   is the string given to the turtle for it to translate into an LSystem.
   * @param length is the length of the lines to be drawn by the turtle.
   * @param angle  is the angle that the turtle should rotate to avoid only drawing a straight line.
   * @param coordX     is the starting x coordinate of Turtle
   * @param coordY     is starting y coordinate of Turtle
   */
  public Turtle(String word, double length, double angle, double coordX, double coordY) {
    this.word = word;
    this.length = length;
    this.angle = angle;
    this.coordX = coordX;
    this.coordY = coordY;

    initialWord = word;
    initialLength = length;
    initialAngle = angle;
    initialCoordX = coordX;
    initialCoordY = coordY;
  }

  /**
   * Constructs a turtle that takes parameters word, length and angle. coordX and coordY are set
   * to 0.
   * Also creates the variables for the initial turtle.
   *
   * @param word   is the string given to the turtle for it to translate into an LSystem.
   * @param length is the length of the lines to be drawn by the turtle.
   * @param angle  is the angle that the turtle should rotate to avoid only drawing a straight line.
   */
  public Turtle(String word, double length, double angle) {
    this.word = word;
    this.length = length;
    this.angle = angle;
    coordX = 0;
    coordY = 0;

    initialWord = word;
    initialLength = length;
    initialAngle = angle;
    initialCoordX = 0;
    initialCoordY = 0;
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
        case '[' -> pushCharacter();
        case ']' -> popCharacter();
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
  public void pushCharacter() {
    Point pushP = new Point();
    pushP.setLocation(coordX, coordY);
    stack.push(pushP);
  }

  /**
   * Pops the point from the stack and then sets the coordinates back to those that were pushed
   * into the stack.
   */
  public void popCharacter() {
    Point popP = stack.pop();
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