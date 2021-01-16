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


  public void generate(int iterations, String[] drawRule, String[] moveRule) {
    String nextWord = getWord();
    StringBuilder next = new StringBuilder();
    for (int j = 0; j < iterations; j++) {
      for (int i = 0; i < nextWord.length(); i++) {
        char c = nextWord.charAt(i);
        switch (c) {
          case('F') -> next.append(drawRule[0]);
          case('G') -> next.append(moveRule[0]);
          default -> next.append(c);
        }
      }
      nextWord = next.toString();
      next.setLength(0);
    }
    setWord(nextWord);
  }
}