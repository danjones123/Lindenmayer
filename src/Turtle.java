/**
 * Class for attempting to use the turtle interpretation to display an LSystem.
 *
 * @author Daniel Jones
 */

//Turtle holds rules eg F = move forward etc
//Rules are the rules for the given attempt eg F = F+F
//Display displays the sequence using Swing.

public class Turtle {
  String word;
  int length;
  double angle;
  int x, y;

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
    x = x0;
    y = y0;
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
    this.length =length;
    this.angle =angle;
    x = 0;
    y = 0;
  }

  //Ignore TDD for this
  public void rules() {
    for (int i = 0; i < word.length(); i++) {
      char current = word.charAt(i);
      switch(current) {
        case 'F':
          draw(length);
          break;
        case 'G':
          move(length);
          break;
        case '+':
          rotate(angle);
          break;
        case '-':
          rotate(-angle);
          break;
      }
    }
  }

  public void draw(int length) {
    int oldx = x;
    int oldy = y;
    x += length;
    y += length;
    Display.setCoords(oldx, oldy, x, y);
    System.out.println("draw");
  }

  public void move(int length) {
    x += length;
    y += length;
    System.out.println("move");
  }

  public void rotate(double angle) {
    angle += angle;
  }
}