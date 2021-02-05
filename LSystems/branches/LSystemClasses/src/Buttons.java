import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
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
  String[] drawRules;
  String[] moveRules;
  private final Display display;
  private int iterations = 1;
  TwoQueue tq = new TwoQueue();


  /**
   * Initialises the local turtle as the turtle from main and the generation rules as those from
   * main as well.
   *
   * @param turtle is the turtle that is initialised in main.
   */
  public void turtleInit(Turtle turtle) {
    this.turtle = turtle;
    drawRules = turtle.getDrawRules();
    moveRules = turtle.getMoveRules();
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
   * @param e is the ActionEvent, meaning the button was pressed.
   */
  public void actionPerformed(ActionEvent e) {
    if ("Generate".equals((e.getActionCommand()))) {


      switch (tq.lastTwo("g")) {
        case ("uu"):
        case ("gu"):
          iterations++;
          turtle.pushTurtle();
          turtle.reset();
          turtle.generate(iterations, drawRules, moveRules);
          turtle.pushTurtle();
          draw();
          iterations++;
          turtle.reset();
          turtle.generate(iterations, drawRules, moveRules);

          break;
        default:
          turtle.pushTurtle();
          draw();
          iterations++;
          turtle.reset();
          turtle.generate(iterations, drawRules, moveRules);
      }

    } else if ("Undo".equals(e.getActionCommand())) {
      if (iterations > 1) {

        switch (tq.lastTwo("u")) {
          case ("gg"):
          case ("ug"):
            turtle.popTurtle();
            iterations--;
            turtle.popTurtle();
            iterations--;
            draw();
            break;
          default:
            turtle.popTurtle();
            iterations--;
            draw();
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