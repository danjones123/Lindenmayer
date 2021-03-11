import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ButtonsController {
  Turtle turtle;
  Turtle previousTurtle = new Turtle(Color.BLUE);
  Lindenmayer linSys;
  Painting painting;
  String[] drawRules;
  String[] moveRules;
  String[] rulesX;
  String[] rulesY;
  double prevStartingCoordX;
  double prevStartingCoordY;
  boolean centreTurtle = true;
  private int iterations = 1;
  boolean drawPrev = false;



  public ButtonsController(Painting painting) {
    this.painting = painting;

  }

  /**
   * Initialises the local turtle as the turtle from main and the generation rules as those from
   * main as well. Also initialises the queue.
   *
   * @param turtle is the turtle that is initialised in main.
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
        turtle.resetStack();
        painting.clear();
      }
    } else if (buttonKey == 2) { //Clear Drawing
      iterations = 1;
      turtle.reset();
      turtle.resetStack();
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
    thisTurtle.resetHighLow();
    thisTurtle.resetBearing();
    painting.clear();
    thisTurtle.rules();
    if (centreTurtle) {
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
    thisTurtle.resetHighLow();
    thisTurtle.resetBearing();
    previousTurtle.resetHighLow();
    previousTurtle.resetBearing();
    painting.clear();
    thisTurtle.rules();
    previousTurtle.rules();
    if (centreTurtle) {
      thisTurtle.resetBearing();
      previousTurtle.resetBearing();
      thisTurtle.centre(Initialise.frameWidth, Initialise.frameHeight);
      //previousTurtle.centre(Initialise.frameWidth, Initialise.frameHeight);
      previousTurtle.setCoords(prevStartingCoordX, prevStartingCoordY);
      prevStartingCoordX = thisTurtle.getCoordX();
      prevStartingCoordY = thisTurtle.getCoordY();
      painting.clear();
      thisTurtle.rules();
      previousTurtle.rules();
    }
    painting.callPaint();
  }

  /**
   * Used to reset the turtle and sets iterations to 1 from outside of the Buttons class.
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

  /**
   * Sets the value of centreTurtle to true or false to decide if the turtle should always be
   * centred or should grow from its starting co-ordinates.
   *
   * @param centreTurtle is the boolean for centring the turtle
   */
  public void setCentreTurtle(boolean centreTurtle) {
    this.centreTurtle = centreTurtle;
  }

}
