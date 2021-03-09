import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Class for the initialisation of the Display and turtle/L-system in the program.
 *
 * @author Daniel Jones.
 */
public class Initialise extends JFrame {
  static int frameWidth = 800;
  static int frameHeight = 800;
  static Turtle turtle = new Turtle();
  static Lindenmayer linSys = new Lindenmayer(turtle);
  static SavedShapes shape = new SavedShapes();

  public Initialise() {

  }

  /**
   * Method to initialise the Display and create the frame.
  */
  public static void createAndShowGui() {
    initialiseTurtleLinden();
    Painting painting = new Painting();
    Buttons buttonPanel = new Buttons(painting);
    buttonPanel.turtleInit(turtle, linSys);


    JPanel mainPanel = new JPanel(false);
    mainPanel.add(painting);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);


    ProdPainter prodPaint = new ProdPainter();
    Productions productions = new Productions(shape, prodPaint);
    JPanel productionsTab = new JPanel(false);
    productionsTab.add(productions);
    productionsTab.add(prodPaint);

    Settings settings = new Settings(turtle, linSys, shape, buttonPanel, productions);
    JPanel settingsTab = new JPanel(false);
    settingsTab.add(settings);




    JTabbedPane tabs = new JTabbedPane();
    tabs.addTab("Drawing", mainPanel);
    tabs.addTab("Productions", productionsTab);
    tabs.addTab("Settings", settingsTab);


    JFrame frame = new JFrame();
    frame.setSize(frameWidth, frameHeight + 120);
    setDefaultLookAndFeelDecorated(true);
    frame.add(tabs);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Class for initialising the turtle and L-system implementation.
   */
  public static void initialiseTurtleLinden() {
    shape.update();
    turtle.setWord(shape.getWord());
    turtle.setLength(shape.getLength());
    turtle.setAngle(shape.getAngle());

    linSys.setDrawRules(shape.getDrawRules());
    linSys.setMoveRules(shape.getMoveRules());
    linSys.setRulesX(shape.rulesX);
    linSys.setRulesY(shape.rulesY);
    linSys.stochAngle(false, 0, 0);


    turtle.setCoords((double) frameWidth / 2, (double) frameHeight / 2);
    turtle.saveStartingTurtle();
  }
}
