import java.util.Random;

/**
 * Class for instantiating L-System logic.
 *
 * @author Daniel Jones
 */
public class Lindenmayer {
  int currentClass = 1;
  int iterations;
  private String[] drawRules;
  private String[] moveRules;
  private String[] rulesX;
  private String[] rulesY;
  boolean apply = false;
  double minAngle;
  double maxAngle;
  double lengthScaler = 1;
  Turtle turtle;


  /**
   * Constructor for L-system.
   *
   * @param turtle is the turtle implementation that the L-system is drawn by.
   */
  public Lindenmayer(Turtle turtle) {
    this.turtle = turtle;
  }

  /**
   * The generate method calls one of the other generate methods depending on which the
   * user tells it to call.
   *
   * @param iterations the number of times to apply the given rule.
   */
  public void generate(int iterations) {
    this.iterations = iterations;
    if (currentClass == 1) {
      detGenerate();
    }
    if (currentClass == 2) {
      stochGenerate();
    }
  }

  /**
   * Generate method for a deterministic turtle.
   */
  public void detGenerate() {
    String nextWord = turtle.getWord();
    StringBuilder next = new StringBuilder();
    for (int j = 0; j < iterations; j++) {
      for (int i = 0; i < nextWord.length(); i++) {
        char c = nextWord.charAt(i);
        switch (c) {
          case ('F') -> next.append(drawRules[0]);
          case ('G') -> next.append(moveRules[0]);
          case ('X') -> next.append(rulesX[0]);
          case ('Y') -> next.append(rulesY[0]);
          default -> next.append(c);
        }
      }
      nextWord = next.toString();
      System.out.println(nextWord);
      next.setLength(0);
      changeRatio();
    }
    turtle.setWord(nextWord);
  }

  /**
   * Generate method for a stochastic turtle, can have different sets of drawing rules.
   */
  public void stochGenerate() {
    String nextWord = turtle.getWord();
    StringBuilder next = new StringBuilder();
    for (int j = 0; j < iterations; j++) {
      for (int i = 0; i < nextWord.length(); i++) {
        char c = nextWord.charAt(i);
        switch (c) {
          case 'F' -> next.append(randomChar(drawRules));
          case 'G' -> next.append(randomChar(moveRules));
          case 'X' -> next.append(randomChar(rulesX));
          case 'Y' -> next.append(randomChar(rulesY));
          case '+', '-' -> {
            angleVariance();
            next.append(c);
          }
          default -> next.append(c);
        }
      }
      nextWord = next.toString();
      next.setLength(0);
      changeRatio();
    }
    turtle.setWord(nextWord);
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
      double randomAngle = Math.random() * (maxAngle - minAngle + 1) + minAngle;
      turtle.setAngle(randomAngle);
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
   * Sets the length to the current length multiplied by the length scalar.
   */
  public void changeRatio() {
    turtle.setLength(turtle.getLength() * lengthScaler);
  }


  /**
   * Class for returning random char form a given array.
   *
   * @param rules is the array of rules given
   * @return returns a random element from the array.
   */
  public String randomChar(String[] rules) {
    int randomPosition = new Random().nextInt(rules.length);
    return rules[randomPosition];
  }

  /**
   * Sets the drawing rules for the generation of new L-Systems.
   *
   * @param drawRules is the String array of rules for how the L-System should draw.
   */
  public void setDrawRules(String[] drawRules) {
    this.drawRules = drawRules;
  }

  /**
   * Sets the moving rules for the generation of new L-Systems.
   *
   * @param moveRules is the String array of rules for how the L-System should move.
   */
  public void setMoveRules(String[] moveRules) {
    this.moveRules = moveRules;
  }

  /**
   * Sets the rules for X, used in node rewriting.
   *
   * @param rulesX is the array of rules to be applied to X
   */
  public void setRulesX(String[] rulesX) {
    this.rulesX = rulesX;
  }

  /**
   * Sets the rules for Y, used in node rewriting.
   *
   * @param rulesY is the array of rules to be applied to Y
   */
  public void setRulesY(String[] rulesY) {
    this.rulesY = rulesY;
  }

  /**
   * Getter for the array of drawing rules.
   *
   * @return returns the String array of drawing rules.
   */
  public String[] getDrawRules() {
    return drawRules;
  }

  /**
   * Getter for the array of moving rules.
   *
   * @return returns the String array of moving rules.
   */
  public String[] getMoveRules() {
    return moveRules;
  }

  /**
   * Gets the rules for X.
   */
  public String[] getRulesX() {
    return rulesX;
  }

  /**
   * Gets the rules for Y.
   */
  public String[] getRulesY() {
    return rulesY;
  }

  /**
   * Sets the current class of the L-System.
   *
   * @param current is the number that the current class should be set to.
   */
  public void setCurrentClass(int current) {
    this.currentClass = current;
  }
}
