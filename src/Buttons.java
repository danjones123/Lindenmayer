import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Deque;
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
  private int undoCounter = 0;
  Deque<Turtle> turtleStack = new ArrayDeque<>();


  /**
   * Initialises the local turtle as the turtle from main and the generation rules as those from
   * main aswell.
   *
   * @param turtle is the turlte that is initialised in main.
   */
  public void turtleInit(Turtle turtle) {
    this.turtle = turtle;
    drawRules = turtle.getDrawRules();
    moveRules = turtle.getMoveRules();
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
   * "Draw" draws the turtle intepretation.
   * "Generate" generates through the L-System.
   * "Undo" undoes the previous generation.
   * "Clear Drawing" removes the drawing from the screen.
   *
   * @param e is the ActionEvent, meaning the button was pressed.
   */
  public void actionPerformed(ActionEvent e) {
    if ("Generate".equals((e.getActionCommand()))) {
      undoCounter = 0;
      pushTurtle();

      turtle.resetHighLow();

      //turtle.setLength(turtle.getLength()*0.1);
      turtle.resetBearing();
      display.clear();
      turtle.rules();
      turtle.resetBearing();
      turtle.centre();
      display.clear();
      turtle.rules();

      display.callPaint();

      iterations++;
      turtle.reset();
      turtle.generate(iterations, drawRules, moveRules);
    } else if ("Undo".equals(e.getActionCommand())) {
      if (iterations > 1) {
        //if (undoCounter == 0) {
        //  popTurtle();
        //}
        iterations--;
        popTurtle();

        turtle.resetBearing();
        display.clear();
        turtle.rules();
        display.callPaint();
        undoCounter++;
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
   * Creates a new Turtle with the current parameters and adds it to the stack.
   */
  public void pushTurtle() {
    Turtle pushTurtle = new DeterministicTurtle();
    pushTurtle.setWord(turtle.getWord());
    pushTurtle.setLength(turtle.getLength());
    pushTurtle.setAngle(turtle.getAngle());
    pushTurtle.setCoords(turtle.getCoordX(), turtle.getCoordY());

    turtleStack.push(pushTurtle);
  }

  /**
   * Pops the top turtle off the stack and sets the main turtle to its parameters.
   */
  public void popTurtle() {
    Turtle popTurtle = turtleStack.pop();
    turtle.setWord(popTurtle.getWord());
    turtle.setLength(popTurtle.getLength());
    turtle.setAngle(popTurtle.getAngle());
    turtle.setCoords(popTurtle.getCoordX(), popTurtle.getCoordY());
  }
}