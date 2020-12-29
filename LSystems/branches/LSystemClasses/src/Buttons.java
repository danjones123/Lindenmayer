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
  Turtle turtle = new DeterministicTurtle();
  String[] genRules;
  private final Display display;
  private int iterations = 1;
  Deque<DeterministicTurtle> turtleStack = new ArrayDeque<>();

  /**
   * Initialises the local turtle as the turtle from main and the generation rules as those from
   * main aswell.
   *
   * @param turtle is the turlte that is initialised in main.
   */
  public void turtleInit(Turtle turtle) {
    this.turtle = turtle;
    genRules = turtle.getGenRules();
  }


  /**
   * Constructor for Button which takes the display as a parameter.
   * Calls createButton to create buttons.
   */
  public Buttons(Display display) {
    this.display = display;

    add(createButton("Draw"));
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
    if ("Draw".equals(e.getActionCommand())) {
      turtle.resetBearing();
      display.clear();
      turtle.rules();
      display.callPaint();
    } else if ("Generate".equals((e.getActionCommand()))) {
      pushTurtle();
      iterations++;
      turtle.reset();
      turtle.generate(iterations, genRules);
    } else if ("Undo".equals(e.getActionCommand())) {
        if (iterations > 1) {
          popTurtle();
          iterations--;
          display.callPaint();
        } else {
          display.clear();
        }
    } else if ("Clear Drawing".equals(e.getActionCommand())) {
      iterations = 1;
      turtle.reset();
      display.clear();
    } //Take out draw button and just make undo and generate call it automatically.
  }

  /**
   * Creates a new Turtle with the current parameters and adds it to the stack.
   */
  public void pushTurtle() {
    DeterministicTurtle pushDeterministicTurtle = new DeterministicTurtle();
    pushDeterministicTurtle.setWord(turtle.getWord());
    pushDeterministicTurtle.setLength(turtle.getLength());
    pushDeterministicTurtle.setAngle(turtle.getAngle());
    pushDeterministicTurtle.setCoords(turtle.getCoordX(), turtle.getCoordY());

    turtleStack.push(pushDeterministicTurtle);
  }

  /**
   * Pops the top turtle off the stack and sets the main turtle to its parameters.
   */
  public void popTurtle() {
    DeterministicTurtle popDeterministicTurtle = turtleStack.pop();
    turtle.setWord(popDeterministicTurtle.getWord());
    turtle.setLength(popDeterministicTurtle.getLength());
    turtle.setAngle(popDeterministicTurtle.getAngle());
    turtle.setCoords(popDeterministicTurtle.getCoordX(), popDeterministicTurtle.getCoordY());
  }
}