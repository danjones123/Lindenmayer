import java.util.Random;

public class Lindenmayer {
  int currentClass;
  int iterations;
  private String[] drawRules;
  private String[] moveRules;
  boolean apply = false;
  double minAngle;
  double maxAngle;
  double lengthScaler = 1;
  Turtle turtle;


  public Lindenmayer(int currentClass, Turtle turtle){
    this.currentClass = currentClass;
    this.turtle = turtle;
  }

  public void generate(int iterations) {
    this.iterations = iterations;
    if (currentClass == 1) {
      detGenerate();
    } if (currentClass == 2) {
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
          default -> next.append(c);
        }
      }
      nextWord = next.toString();
      next.setLength(0);
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
      double crippledAngle = Math.random() * (maxAngle - minAngle + 1) + minAngle;
      turtle.setAngle(crippledAngle);
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
    turtle.setLength(turtle.getLength() * lengthScaler);
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
    turtle.setWord(nextWord);
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
}
