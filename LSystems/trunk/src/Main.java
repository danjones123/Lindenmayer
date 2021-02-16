import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;



/**
 * The class to initialise the GUI.
 *
 * @author Daniel Jones
 */

public class Main {
  static int frameWidth = 800;
  static int frameHeight = 800;


  /**
   * The main method calls the CreateAndShowGUI() method inside the run() method from Swing.
   *
   * @param args takes the argument for the method.
   */
  public static void main(String[] args) {

    SwingUtilities.invokeLater(Main::createAndShowGraphics);
  }


  /**
   * Method to initialise the Display, Turtle and Buttons and create the frame.
   */
  private static void createAndShowGraphics() {
    StochasticTurtle turtle = new StochasticTurtle();

    SavedShapes shape = new SavedShapes(3);

    turtle.setWord(shape.getWord());
    turtle.setLength(shape.getLength());
    turtle.setAngle(shape.getAngle());
    String[] drawRules = shape.getDrawRules();
    turtle.setDrawRules(drawRules);
    String[] moveRules = shape.getMoveRules();
    turtle.setMoveRules(moveRules);
    turtle.stochAngle(false, 0, 90);
    turtle.setLengthRatio(0.6);


    turtle.setCoords((double) frameWidth / 2, (double) frameHeight / 2);
    turtle.saveStartingTurtle();

    Display display = new Display();
    Buttons buttonPanel = new Buttons(display);
    buttonPanel.turtleInit(turtle);

    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("LSystem");
    frame.getContentPane().add(display);
    frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    frame.setSize(frameWidth, frameHeight);
    frame.setLocationRelativeTo(null);

    frame.setVisible(true);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}