import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class for attempting to use the turtle interpretation to display an LSystem.
 *
 * @author Daniel Jones
 */

public class Turtle extends Main {
  String word;
  int length;
  double angle;
  int coordX;
  int coordY;
  Deque<Point> stack = new ArrayDeque<>();

  /**
   * Constructs a turtle that takes parameters word, length, angle, x0 and y0.
   *
   * @param word   is the string given to the turtle for it to translate into an LSystem.
   * @param length is the length of the lines to be drawn by the turtle.
   * @param angle  is the angle that the turtle should rotate to avoid only drawing a straight line.
   * @param x0     is the starting x coordinate of Turtle
   * @param y0     is starting y coordinate of Turtle
   */
  public Turtle(String word, int length, double angle, int x0, int y0) {
    this.word = word;
    this.length = length;
    this.angle = angle;
    coordX = x0;
    coordY = y0;
  }

  /**
   * Constructs a turtle that takes parameters word, length and angle. x0 and y0 are set to 0.
   *
   * @param word   is the string given to the turtle for it to translate into an LSystem.
   * @param length is the length of the lines to be drawn by the turtle.
   * @param angle  is the angle that the turtle should rotate to avoid only drawing a straight line.
   */
  public Turtle(String word, int length, double angle) {
    this.word = word;
    this.length = length;
    this.angle = angle;
    coordX = 0;
    coordY = 0;
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
   * Draws the length of the line in the given direction.
   *
   * @param length is the length of the line drawn.
   */
  public void draw(int length) {
    int oldx = coordX;
    int oldy = coordY;
    coordX += (length * Math.cos(angle));
    coordY += (length * Math.sin(angle));
    Display.setCoords(oldx, oldy, coordX, coordY);
  }

  /**
   * Moves the turtle position without drawing.
   *
   * @param length is the length to move.
   */
  public void move(int length) {
    coordX += length * Math.cos(angle);
    coordY += length * Math.sin(angle);
  }

  /**
   * Changes the direction of the turtle by taking the given angle, converting
   * it to radians and setting the global angle to that value.
   *
   * @param newAngle is the angle to rotate the position by.
   */
  public void rotate(double newAngle) {
    double radAngle = Math.toRadians(newAngle);
    angle += radAngle;
  }

  /**
   * Adds the points x and y to a Point and then pushes them to the stack to save their position.
   */
  public void pushCharacter() {
    stack.push(new Point(coordX, coordY));
  }

  /**
   * Pops the Point and sets x and y to the int value stored at X and Y in the Point.
   */
  public void popCharacter() {
    Point p = stack.pop();
    coordX = (int) p.getX();
    coordY = (int) p.getY();
  }
}