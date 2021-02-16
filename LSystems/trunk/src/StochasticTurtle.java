import java.util.Random;

/**
 * Class using turtle interpretation to display an LSystem.
 *
 * @author Daniel Jones
 */
public class StochasticTurtle extends Turtle {
  boolean apply = false;
  double minAngle;
  double maxAngle;
  double lengthScaler = 1;


  /**
   * Empty constructor allowing a new Turtle to be called from other classes.
   */
  public StochasticTurtle() {
  }

  /**
   * Class for taking a user-defined range of angles for the lines to be drawn at.
   *
   * @param apply boolean to check if the user wants to use stochastic angles.
   * @param minAngle the minimum angle in the range.
   * @param maxAngle the maximum angle in the range.
   */
  public void stochAngle(boolean apply, double minAngle, double maxAngle) {
    this.apply = apply;
    this.minAngle = minAngle;
    this.maxAngle = maxAngle;
  }

  /**
   * Checks whether apply is true or false, if it is true it creates a random angle between the
   * limits and sets angle to that value.
   */
  public void angleVariance() {
    if (apply) {
      double crippledAngle = Math.random() * (maxAngle - minAngle + 1) + minAngle;
      setAngle(crippledAngle);
    }
  }

  /**
   * Sets the lengthScalar to the given amount.
   *
   * @param lengthScaler is the amount that the length is scaled down by per iteration.
   */
  public void setLengthRatio(double lengthScaler) {
    this.lengthScaler = lengthScaler;
  }

  /**
   * Sets the length to the current length mulitplied by the length scalar.
   */
  public void changeRatio() {
    setLength(getLength() * lengthScaler);
  }

  /**
   * Generate method for a stochastic turtle, can have different sets of drawing rules.
   *
   * @param iterations the number of times the generation rules are to be iterated through.
   * @param drawRules the rules for drawing lines.
   * @param moveRules the rules for moving.
   */
  public void generate(int iterations, String[] drawRules, String[] moveRules) {
    String nextWord = getWord();
    StringBuilder next = new StringBuilder();
    for (int j = 0; j < iterations; j++) {
      for (int i = 0; i < nextWord.length(); i++) {
        char c = nextWord.charAt(i);
        if (c == ('F')) {
          int randomPosition = new Random().nextInt(drawRules.length);
          next.append(drawRules[randomPosition]);
        } else if (c == ('X')) {
          next.append(moveRules[0]);
        } else if (c == 'Y') {
          next.append(moveRules[1]);
        } else if (c == '+' || c == '-') {
          angleVariance();
          next.append(c);
        } else {
          next.append(c);
        }

      }
      nextWord = next.toString();
      next.setLength(0);
      changeRatio();
      System.out.println(nextWord);
    }
    setWord(nextWord);
  }
}