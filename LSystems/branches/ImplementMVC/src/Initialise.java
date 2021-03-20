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
  static SavedShapes shape;

  /**
   * Simple constructor for Initialise.
   */
  public Initialise() {

  }

  /**
   * Updates savedShapes.
   */
  public static void updateShapes() {
    shape = new SavedShapes();
  }

  /**
   * Method to initialise the Display and create the frame.
   */
  public static void createAndShowGui() {
    updateShapes();
    initialiseShapeTurtleLinden();
    Painting painting = new Painting();
    ButtonsController buttonsController = new ButtonsController(painting);
    Buttons buttonPanel = new Buttons(buttonsController);
    buttonsController.turtleInit(turtle, linSys);


    JPanel mainPanel = new JPanel(false);
    mainPanel.add(painting);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);


    ProdPainter prodPaint = new ProdPainter();
    ProductionsController productionsController = new ProductionsController(shape, prodPaint);
    Productions productions = new Productions(productionsController);
    JPanel productionsTab = new JPanel(false);
    productionsTab.add(productions);
    productionsTab.add(prodPaint);

    SettingsController settingsController = new SettingsController(turtle, linSys, shape,
        buttonsController, productionsController);
    settingsController.init();
    Settings settings = new Settings(settingsController);
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
  public static void initialiseShapeTurtleLinden() {
    shape.update();
    turtle.setWord(shape.getWord());
    turtle.setLength(shape.getLength());
    turtle.setAngle(shape.getAngle());

    linSys.setDrawRules(shape.getDrawRules());
    linSys.setMoveRules(shape.getMoveRules());
    linSys.setRulesX(shape.getRulesX());
    linSys.setRulesY(shape.getRulesY());

    turtle.setCoords((double) frameWidth / 2, (double) frameHeight / 2);
    turtle.saveStartingTurtle();
  }

  /**
   * Initialises a Turtle with customised shape values.
   *
   * @param localShape is the shape that has had its values changed.
   * @param startX is the starting X co-ordinate
   * @param startY is the starting Y co-ordinate
   */
  public static void initialiseNewerTurtleLinden(SavedShapes localShape, double startX,
                                                 double startY) {
    turtle.setWord(localShape.getWord());
    turtle.setLength(localShape.getLength());
    turtle.setAngle(localShape.getAngle());

    linSys.setDrawRules(localShape.getDrawRules());
    linSys.setMoveRules(localShape.getMoveRules());
    linSys.setRulesX(localShape.getRulesX());
    linSys.setRulesY(localShape.getRulesY());


    turtle.setCoords(startX, startY);
    turtle.saveStartingTurtle();
  }


}
