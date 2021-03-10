import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The Buttons class creates the button panel and assigns the action to be performed when a
 * given button is pressed.
 *
 * @author Daniel Jones
 */
public class Buttons extends JPanel implements ActionListener {
  Turtle turtle;
  Turtle previousTurtle = new Turtle(Color.BLUE);
  Lindenmayer linSys;
  String[] drawRules;
  String[] moveRules;
  String[] rulesX;
  String[] rulesY;
  boolean centreTurtle = true;
  private final Painting painting;
  private int iterations = 1;
  TwoQueue tq = new TwoQueue();
  boolean drawPrev = false;


  /**
   * Constructor for Button which takes the display as a parameter.
   * Calls createButton to create buttons.
   */
  public Buttons(Painting painting) {
    this.painting = painting;

    add(createButton("Generate"));
    add(createButton("Undo"));
    add(createButton("Clear Drawing"));
    add(createButton("Toggle Show Previous"));
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
    tq.resetQueue();

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
   * Creates buttons with a given label and adds an actionListener to the button.
   *
   * @param label is the name of the button.
   * @return returns a button with an actionListener attached.
   */
  private JButton createButton(String label) {
    JButton button = new JButton(label);
    button.addActionListener(this);

    return button;
  }

  /**
   * Checks which button was pressed and calls the correct methods for that button.
   * "Generate" generates through the L-System.
   * "Undo" undoes the previous generation.
   * "Clear Drawing" removes the drawing from the screen.
   *
   * <p>
   * Generate and undo check what to do using a queue that always holds two values of the last
   * two buttons that were pressed.
   * </p>
   *
   * @param e is the ActionEvent, meaning the button was pressed.
   */
  public void actionPerformed(ActionEvent e) {
    if ("Generate".equals((e.getActionCommand()))) {
      switch (tq.lastTwo("g")) {
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
    } else if ("Undo".equals(e.getActionCommand())) {
      if (iterations > 1) {
        switch (tq.lastTwo("u")) {
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
    } else if ("Clear Drawing".equals(e.getActionCommand())) {
      iterations = 1;
      turtle.reset();
      updatePrevTurtle();
      painting.clear();
    } else if ("Toggle Show Previous".equals(e.getActionCommand())) {
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
      previousTurtle.centre(Initialise.frameWidth, Initialise.frameHeight);
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

  public void setCentreTurtle(boolean centreTurtle) {
    this.centreTurtle = centreTurtle;
  }
}