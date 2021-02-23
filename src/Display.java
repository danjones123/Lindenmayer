import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Display extends JFrame {
  static int frameWidth = 800;
  static int frameHeight = 800;
  static Turtle turtle = new Turtle();
  static Lindenmayer linSys = new Lindenmayer(turtle);
  static SavedShapes shape = new SavedShapes();

  public Display() {

  }

  /**
   * Method to initialise the Display and create the frame.
  */
  public static void createAndShowGui() {
    initialiseTurtleLinden();
    Painting painting = new Painting();
    Buttons buttonPanel = new Buttons(painting);
    Settings settings = new Settings(turtle, linSys, shape, buttonPanel);
    buttonPanel.turtleInit(turtle, linSys);
    JFrame frame = new JFrame();
    frame.setSize(frameWidth, frameHeight + 120);


    JPanel mainPanel = new JPanel(false);
    JPanel settingsTab = new JPanel(false);
    JTabbedPane tabs = new JTabbedPane();

    mainPanel.add(painting);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    settingsTab.add(settings);

    tabs.addTab("Drawing", mainPanel);
    tabs.addTab("Settings", settingsTab);


    setDefaultLookAndFeelDecorated(true);
    frame.add(tabs);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void initialiseTurtleLinden() {
    shape.update();
    turtle.setWord(shape.getWord());
    turtle.setLength(shape.getLength());
    turtle.setAngle(shape.getAngle());
    String[] drawRules = shape.getDrawRules();
    String[] moveRules = shape.getMoveRules();
    String[] rulesX = shape.getRulesX();
    String[] rulesY = shape.getRulesY();


    linSys.setDrawRules(drawRules);
    linSys.setMoveRules(moveRules);
    linSys.setRulesX(rulesX);
    linSys.setRulesY(rulesY);
    linSys.stochAngle(false, 0, 90);


    turtle.setCoords((double) frameWidth / 2, (double) frameHeight / 2);
    turtle.saveStartingTurtle();
  }
}
