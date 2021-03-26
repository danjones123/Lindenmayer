import java.awt.Color;

/**
 * Controller for the buttons that can be pressed on the main drawing screen.
 *
 * @author Daniel Jones
 */
public class ButtonsController {
  Turtle turtle;
  Turtle previousTurtle = new Turtle(Color.BLUE);
  Lindenmayer linSys;
  Painting painting;
  String[] drawRules;
  String[] moveRules;
  String[] rulesX;
  String[] rulesY;
  private double prevStartingCoordX;
  private double prevStartingCoordY;
  private int iterations = 1;
  boolean drawPrev = false;


  /**
   * Initialises the ButtonController with painting.
   *
   * @param painting is the painting to be called when buttons have been pressed.
   */
  public ButtonsController(Painting painting) {
    this.painting = painting;
  }

  /**
   * Initialises the local turtle as the turtle from main and the generation rules as those from
   * main as well.
   *
   * @param turtle is the turtle that is initialised in main.
   * @param linSys is the Lindenmayer system to apply the growth rules to the turtle.
   */
  public void turtleInit(Turtle turtle, Lindenmayer linSys) {
    this.turtle = turtle;
    this.linSys = linSys;
    drawRules = linSys.getDrawRules();
    moveRules = linSys.getMoveRules();
    rulesX = linSys.getRulesX();
    rulesY = linSys.getRulesY();
  }

  /**
   * Updates the previous turtle to equal what the current turtle is before it is generated.
   */
  public void updatePrevTurtle() {
    previousTurtle.setWord(turtle.getWord());
    previousTurtle.setLength(turtle.getLength());
    previousTurtle.setAngle(turtle.getAngle());
    previousTurtle.setCoords(turtle.getCoordX(), turtle.getCoordY());
  }

  /**
   * Calls different methods depending on which button is pressed.
   *
   * @param buttonKey is a key for which button was pressed, eg if "Generate" was pressed,
   *                  buttonkey = 0.
   * @param lastTwo is the last two characters in the TwoQueue.
   */
  public void buttonPressed(int buttonKey, String lastTwo) {
    if (buttonKey == 0) { //Generate
      switch (lastTwo) {
        case ("uu"), ("gu") -> {
          iterations++;
          turtle.pushTurtle();
          turtle.reset();
          linSys.generate(iterations);
          turtle.pushTurtle();

          if (drawPrev) {
            doubleDraw(turtle, previousTurtle);
          } else {
            singleDraw(turtle);
          }
          updatePrevTurtle();
          iterations++;
          turtle.reset();
          linSys.generate(iterations);
        }
        default -> {
          turtle.pushTurtle();
          if (drawPrev) {
            doubleDraw(turtle, previousTurtle);
          } else {
            singleDraw(turtle);
          }
          updatePrevTurtle();
          iterations++;
          turtle.reset();
          linSys.generate(iterations);
        }
      }
    } else if (buttonKey == 1) { //Undo
      if (iterations > 1) {
        switch (lastTwo) {
          case ("gg"), ("ug") -> {
            turtle.popTurtle();
            iterations--;
            turtle.popTurtle();
            iterations--;
            singleDraw(turtle);
            updatePrevTurtle();
          }
          default -> {
            turtle.popTurtle();
            iterations--;
            singleDraw(turtle);
            updatePrevTurtle();
          }
        }
      } else {
        turtle.reset();
        previousTurtle.reset();
        turtle.resetStack();
        previousTurtle.resetStack();
        painting.clear();
      }
    } else if (buttonKey == 2) { //Clear Drawing
      iterations = 1;
      turtle.reset();
      previousTurtle.reset();
      turtle.resetStack();
      previousTurtle.resetStack();
      updatePrevTurtle();
      painting.clear();
    } else if (buttonKey == 3) { //Toggle Previous
      drawPrev = !drawPrev;
    }
  }

  /**
   * Method for drawing the main turtle. Ensures that it is always drawn in the same direction
   * and that it is centred.
   *
   * @param thisTurtle is the turtle to be drawn.
   */
  public void singleDraw(Turtle thisTurtle) {
    if (painting.centreSetTurtle) {
      thisTurtle.setCoords((double) Initialise.frameWidth / 2, (double) Initialise.frameHeight / 2);
    }
    thisTurtle.resetHighLow();
    thisTurtle.resetBearing();
    painting.clear();
    thisTurtle.rules();
    if (painting.centreSetTurtle) {
      thisTurtle.resetBearing();
      thisTurtle.centre(Initialise.frameWidth, Initialise.frameHeight);
      previousTurtle.setCoords(prevStartingCoordX, prevStartingCoordY);
      prevStartingCoordX = thisTurtle.getCoordX();
      prevStartingCoordY = thisTurtle.getCoordY();
      painting.clear();
      thisTurtle.rules();
    }
    painting.callPaint();
  }

  /**
   * Method for drawing the main turtle and it's previous iteration.
   *
   * @param thisTurtle is the main turtle implementing an l-system.
   * @param previousTurtle is the previous iteration of the main turtle.
   */
  public void doubleDraw(Turtle thisTurtle, Turtle previousTurtle) {
    if (painting.centreSetTurtle) {
      thisTurtle.setCoords((double) Initialise.frameWidth / 2, (double) Initialise.frameHeight / 2);
    }
    thisTurtle.resetHighLow();
    thisTurtle.resetBearing();
    previousTurtle.resetHighLow();
    previousTurtle.resetBearing();
    painting.clear();
    thisTurtle.rules();
    rulesIfNotNull(previousTurtle);
    if (painting.centreSetTurtle) {
      thisTurtle.resetBearing();
      previousTurtle.resetBearing();
      thisTurtle.centre(Initialise.frameWidth, Initialise.frameHeight);
      previousTurtle.setCoords(prevStartingCoordX, prevStartingCoordY);
      prevStartingCoordX = thisTurtle.getCoordX();
      prevStartingCoordY = thisTurtle.getCoordY();
      painting.clear();
      thisTurtle.rules();
      rulesIfNotNull(previousTurtle);
    }
    painting.callPaint();
  }

  /**
   * Method to check if the current turtle is null and only to call rules if it is not.
   *
   * @param turtle is the turtle being checked.
   */
  public void rulesIfNotNull(Turtle turtle) {
    if (turtle.getWord() != null) {
      turtle.rules();
    }
  }

  /**
   * Used to reset the turtle and sets iterations to 1 from outside of the ButtonsController class.
   */
  public void externalReset() {
    iterations = 1;
    turtle.reset();
    turtle.resetStack();
    previousTurtle.reset();
    previousTurtle.resetStack();
    updatePrevTurtle();
    painting.clear();
  }
}
