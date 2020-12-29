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

  public void generate(int iterations, String[] genRules) {
    String nextWord = getWord();
    StringBuilder next = new StringBuilder();
    for (int j = 0; j < iterations; j++) {
      for (int i = 0; i < nextWord.length(); i++) {
        char c = nextWord.charAt(i);
        if (c == ('F') || c == 'G') {
          int randomPosition = new Random().nextInt(genRules.length);
          System.out.println(randomPosition);
          next.append(genRules[randomPosition]);
        }else {
            next.append(c);
        }
      }
      nextWord = next.toString();
      next.setLength(0);
    }
    setWord(nextWord);
  }
}