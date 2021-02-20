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
  Lindenmayer linSys;
  String[] drawRules;
  String[] moveRules;
  String[] rulesX;
  String[] rulesY;
  private final Display display;
  private int iterations = 1;
  TwoQueue tq = new TwoQueue();


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
   * Constructor for Button which takes the display as a parameter.
   * Calls createButton to create buttons.
   */
  public Buttons(Display display) {
    this.display = display;

    add(createButton("Generate"));
    add(createButton("Undo"));
    add(createButton("Clear Drawing"));
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
          draw();
          iterations++;
          turtle.reset();
          linSys.generate(iterations);
        }
        default -> {
          turtle.pushTurtle();
          draw();
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
            draw();
          }
          default -> {
            turtle.popTurtle();
            iterations--;
            draw();
          }
        }
      } else {
        turtle.reset();
        display.clear();
      }
    } else if ("Clear Drawing".equals(e.getActionCommand())) {
      iterations = 1;
      turtle.reset();
      display.clear();
    }
  }

  /**
   * Method for drawing the turtle. Ensures that it is always drawn in the same direction
   * and that it is centred.
   */
  public void draw() {
    turtle.resetHighLow();
    turtle.resetBearing();
    display.clear();
    turtle.rules();
    turtle.resetBearing();
    turtle.centre();
    display.clear();
    turtle.rules();

    display.callPaint();
  }


}