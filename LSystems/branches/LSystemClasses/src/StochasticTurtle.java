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
  double lengthScaler = 0.6;


  /**
   * Empty constructor allowing a new Turtle to be called from other classes.
   */
  public StochasticTurtle() {
  }

  //Make separate getRules or something to allow user to decide how much weight each rule holds
  //rather that it always being equal chance

  public void stochAngle(boolean apply, double minAngle, double maxAngle) {
    this.apply = apply;
    this.minAngle = minAngle;
    this.maxAngle = maxAngle;
  }

  public void angleVariance() {
    if (apply) {
      double crippledAngle = Math.random() * (maxAngle - minAngle + 1) + minAngle;
      setAngle(crippledAngle);
      //System.out.println(crippledAngle);
    }
  }

  public void setLengthRatio(double lengthScaler) {
    this.lengthScaler = lengthScaler;
  }

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
          //System.out.println(randomPosition);
          next.append(drawRules[randomPosition]);
          //double randomPosition = Math.random();
          //if (randomPosition < 0.2) {
          //  next.append(drawRules[0]);
          //} else if (randomPosition < 0.6 && randomPosition >= 20) {
          //  next.append(drawRules[1]);
          //} else {
          //  next.append(drawRules[2]);
          //}
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