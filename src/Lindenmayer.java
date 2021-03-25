import java.util.Random;

/**
 * Class for instantiating L-System logic.
 *
 * @author Daniel Jones
 */
public class Lindenmayer {
  int currentClass = 1;
  private int iterations;
  private String[] drawRules;
  private String[] moveRules;
  private String[] rulesX;
  private String[] rulesY;
  private Double[] probDraw;
  private Double[] probMove;
  private Double[] probX;
  private Double[] probY;
  double lengthScaler = 1;
  Turtle turtle;
  boolean customRulesBool = false;


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
      next.setLength(0);
      changeLengthRatio();
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
        if (customRulesBool) {
          switch (c) {
            case 'F' -> next.append(customRandChar(drawRules, probDraw));
            case 'G' -> next.append(customRandChar(moveRules, probMove));
            case 'X' -> next.append(customRandChar(rulesX, probX));
            case 'Y' -> next.append(customRandChar(rulesY, probY));
            default -> next.append(c);
          }
        } else {
          switch (c) {
            case 'F' -> next.append(randomChar(drawRules));
            case 'G' -> next.append(randomChar(moveRules));
            case 'X' -> next.append(randomChar(rulesX));
            case 'Y' -> next.append(randomChar(rulesY));
            default -> next.append(c);
          }
        }
      }
      nextWord = next.toString();
      next.setLength(0);
      changeLengthRatio();
    }
    turtle.setWord(nextWord);
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
  public void changeLengthRatio() {
    turtle.setLength(turtle.getLength() * lengthScaler);
  }


  /**
   * Class for returning random char from a given array.
   *
   * @param rules is the array of rules given
   * @return returns a random element from the array.
   */
  public String randomChar(String[] rules) {
    int randomPosition = new Random().nextInt(rules.length);
    return rules[randomPosition];
  }

  /**
   * Takes the rule name and assigns the new rule probabilities to the local ones.
   *
   * @param ruleName is the name of the rule array being changed.
   * @param newProbs is the new set of probabilities for the rules.
   */
  public void customRuleProb(String ruleName, Double[] newProbs) {
    switch (ruleName) {
      case "draw" -> probDraw = newProbs;
      case "move" -> probMove = newProbs;
      case "x" -> probX = newProbs;
      case "y" -> probY = newProbs;
      default -> System.out.println("Unknown rule");
    }
  }

  /**
   * Method that assigns a different rule to the L-system depending on the rule chances.
   *
   * @param rules is the string array of strings that can be chosen to replace the current
   *              character.
   * @param ruleProb is the probability that a given string is applied to character.
   * @return returns the String that the current character will be appended by.
   */
  public String customRandChar(String[] rules, Double[] ruleProb) {
    double cumulative = 0;
    try {
      if (ruleProb.length == rules.length) {
        double randomPosition = Math.random();
        for (int i = 0; i < ruleProb.length; i++) {

          if (i == 0) {
            if (randomPosition < ruleProb[0]) {
              return rules[0];
            }
          } else if (i < ruleProb.length - 1) {
            if (randomPosition > cumulative && randomPosition < ruleProb[i] + cumulative) {
              return rules[i];
            }
          } else {
            return rules[ruleProb.length - 1];
          }
          cumulative += ruleProb[i];
        }
      }
    } catch (NullPointerException c) {
      return randomChar(rules);
    }
    return randomChar(rules);
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
   * Sets customRulesBool to true or false.
   *
   * @param bool is the boolean that customRulesBool is assigned to.
   */
  public void setCustomRulesBool(Boolean bool) {
    this.customRulesBool = bool;
  }

  /**
   * Sets the current class of the L-System.
   *
   * @param current is the number that the current class should be set to.
   */
  public void setCurrentClass(int current) {
    this.currentClass = current;
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


}
