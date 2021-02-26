import javax.swing.SwingUtilities;

/**
 * The class to initialise the GUI.
 *
 * @author Daniel Jones
 */
public class Main {

  /**
   * The main method calls the CreateAndShowGUI() method inside the run() method from Swing.
   *
   * @param args takes the argument for the method.
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(Display::createAndShowGui);
  }
}