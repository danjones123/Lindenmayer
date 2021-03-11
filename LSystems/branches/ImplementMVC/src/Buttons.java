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
  ButtonsController butCont;
  TwoQueue tq = new TwoQueue();


  /**
   * Constructor for Button which takes the display as a parameter.
   * Calls createButton to create buttons.
   */
  public Buttons(ButtonsController butCont) {
    this.butCont = butCont;
    tq.resetQueue();

    add(createButton("Generate"));
    add(createButton("Undo"));
    add(createButton("Clear Drawing"));
    add(createButton("Toggle Show Previous"));
  }


  public void resetQueue() {
    tq.resetQueue();
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
      butCont.buttonPressed(0, tq.lastTwo("g"));
    } else if ("Undo".equals(e.getActionCommand())) {
      butCont.buttonPressed(1, tq.lastTwo("u"));
    } else if ("Clear Drawing".equals(e.getActionCommand())) {
      butCont.buttonPressed(2, "");
      resetQueue();
    } else if ("Toggle Show Previous".equals(e.getActionCommand())) {
      butCont.buttonPressed(3, "");
    }
  }

}