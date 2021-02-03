import java.util.Random;

/**
 * Class using turtle interpretation to display an LSystem.
 *
 * @author Daniel Jones
 */
public class StochasticTurtle extends Turtle {


  /**
   * Empty constructor allowing a new Turtle to be called from other classes.
   */
  public StochasticTurtle() {
  }

  //Make separate getRules or something to allow user to decide how much weight each rule holds
  //rather that it always being equal chance

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
          System.out.println(randomPosition);
          next.append(drawRules[randomPosition]);
        } else if (c == ('G')) {
          int randomPosition = new Random().nextInt(moveRules.length);
          System.out.println(randomPosition);
          next.append(moveRules[randomPosition]);
        } else {
          next.append(c);
        }
      }
      nextWord = next.toString();
      next.setLength(0);
    }
    setWord(nextWord);
  }
}