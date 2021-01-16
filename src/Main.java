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

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGraphics();
      }
    });
  }

  /**
   * Method to initialise the Display, Turtle and Buttons and create the frame.
   */
  private static void createAndShowGraphics() {
    Turtle turtle = new DeterministicTurtle();

    //turtle.setWord("F-F-F-F");
    //turtle.setLength(10);
    //turtle.setAngle(90);
    //String[] drawRules = {"FF-FF"};
    //String[] moveRules = {"GG"};

    //turtle.setWord("F--F--F");
    //turtle.setLength(10);
    //turtle.setAngle(60);
    //String[] drawRules = {"F--F--F--G"};
    //String[] moveRules = {"GG"};

    turtle.setWord("F+F+F+F");
    turtle.setLength(1);
    turtle.setAngle(90);
    String[] drawRules = {"F+G-FF+F+FF+FG+FF-G+FF-F-FF-FG-FFF"};
    String[] moveRules = {"GGGGGG"};

    turtle.setCoords((double) frameWidth / 2, (double) frameHeight / 2);
    turtle.saveStartingTurtle();
    //String[] drawRules = {"F--F--F--G"};
    //String[] moveRules = {"GG"};
    turtle.setDrawRules(drawRules);
    turtle.setMoveRules(moveRules);

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

//islands"F+F+F+F", 90, "F+G-FF+F+FF+FG+FF-G+FF-F-FF-FG-FFF", "GGGGGG"
//squarey "F-F-F-F", 90, "FF-F-F-F-FF"
//quadratic koch island "F-F-F-F", 90, "F-F+F+FF-F-F+F"
//sierpinski F--F--F, 60, "F--F--F--G", "GG"
//tree 1 "F", 25.7, "FF+[+F-F-F]-[-F+F+F]"