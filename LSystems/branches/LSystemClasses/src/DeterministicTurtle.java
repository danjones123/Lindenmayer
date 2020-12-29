import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class using turtle interpretation to display an LSystem.
 *
 * @author Daniel Jones
 */
public class DeterministicTurtle extends Turtle {

  /**
   * Empty constructor allowing a new Turtle to be called from other classes.
   */
  public DeterministicTurtle() {
  }


  public void generate(int iterations, String[] genRules) {
    String nextWord = getWord();
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
    setWord(nextWord);
  }
 }