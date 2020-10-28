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

  public Turtle(String word, int length, double angle) {
    this.word = word;
    this.length = length;
    this.angle = angle;
  }

}
