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
  Turtle turtle = new Turtle();
  String[] genRules;
  private final Display display;
  private int iterations = 1;

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
   *
   * @param e is the ActionEvent, meaning the button was pressed.
   */
  public void actionPerformed(ActionEvent e) {
    if ("Draw".equals(e.getActionCommand())) {
      display.clear();
      turtle.rules();
      display.callPaint();
    } else if ("Generate".equals((e.getActionCommand()))) {
      iterations++;
      turtle.reset();
      turtle.generate(iterations, genRules);
    } else if ("Clear Drawing".equals(e.getActionCommand())) {
      iterations = 1;
      turtle.reset();
      display.clear();
    }
  }
}