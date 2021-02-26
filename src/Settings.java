import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Class for creating the settings window and controlling what the buttons do.
 *
 * @author Daniel Jones.
 */
public class Settings extends JPanel {
  Turtle turtle;
  Lindenmayer linSys;
  SavedShapes shapes;
  Buttons buttons;
  private double newRatio = 1;
  private int currentClass = 1;
  private int presetNum = 0;


  /**
   * Method for making the settings window and adding to it all of the buttons.
   *
   * @param turtle is the turtle in use.
   * @param linSys is the l-system in use.
   * @param shapes is the shapes class in use
   * @param buttons is the buttons class.
   */
  public Settings(Turtle turtle, Lindenmayer linSys, SavedShapes shapes, Buttons buttons) {
    this.linSys = linSys;
    this.turtle = turtle;
    this.shapes = shapes;
    this.buttons = buttons;

    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(Display.frameWidth, Display.frameHeight));
    setLayout(null);
    linClassButtons();
    presetBox();
    lengthRatioButtons();
    axiomBoxes();
    saveChanges();
  }

  /**
   * Method that creates radio buttons to choose between which class of l-system the user would
   * like to use.
   */
  public void linClassButtons() {
    JRadioButton deterministic = new JRadioButton("Deterministic");
    JRadioButton stochastic = new JRadioButton("Stochastic");
    deterministic.setSelected(true);
    deterministic.setBounds((Display.frameWidth / 2) - 150, 50, 150, 30);
    stochastic.setBounds((Display.frameWidth / 2), 50, 150, 30);

    ButtonGroup linClasses = new ButtonGroup();
    linClasses.add(deterministic);
    linClasses.add(stochastic);
    deterministic.addActionListener(e -> currentClass = 1);
    stochastic.addActionListener(e -> currentClass = 2);

    add(deterministic);
    add(stochastic);
  }

  /**
   * Method that creates a dropdown list of the possible presets for the user to choose from.
   */
  public void presetBox() {
    JLabel presetLabel = new JLabel("Presets");
    String[] presetNames = {"squares", "sierpinski", "lakes", "scaryTree", "stochastic",
        "shuriken", "moreSquares", "rectangles", "sparse", "idk", "idk2", "kochIsland",
        "kochSnowflake", "ecksAndWhy", "nonStochXy", "nonStochXy2", "nonStochXy3", "hilbert",
        "turtletoynet", "handDrawn", "ecksAndWhyStochastic"};

    JComboBox<String> presets = new JComboBox<>(presetNames);
    presets.setBounds((Display.frameWidth / 2) - 50, 100, 150, 30);
    presetLabel.setBounds((Display.frameWidth / 2) - 100, 100, 150, 30);

    presets.addActionListener(e -> {
      if (e.getSource().equals(presets)) {
        presetNum = presets.getSelectedIndex();
        //shapes.setPresetNo(presets.getSelectedIndex());
        //buttons.externalReset();
        //Display.initialiseTurtleLinden();
        System.out.println(presets.getSelectedIndex());
      }
    });
    add(presets);
    add(presetLabel);
  }

  /**
   * Method for creating the buttons for user input length ratio.
   */
  public void lengthRatioButtons() {
    JTextField lengthRatio = new JTextField("Input length scaler", 10);
    JButton enterButton = new JButton("Enter Ratio");
    lengthRatio.setBounds((Display.frameWidth / 2) - 150, 150, 150, 25);
    enterButton.setBounds((Display.frameWidth / 2), 150, 100, 25);

    lengthRatio.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        JTextField source = (JTextField) e.getComponent();
        source.setText("");
        source.removeFocusListener(this);
      }
    });

    enterButton.addActionListener(e -> {
      try {
        newRatio = Double.parseDouble(lengthRatio.getText());
        //linSys.setLengthRatio(newRatio);
        //buttons.externalReset();
      } catch (NumberFormatException c) {
        System.out.println("Not a number");
      }
    });

    add(lengthRatio);
    add(enterButton);
  }

  /**
   * Method for the boxes that show the axioms and productions rules for current turtle and
   * l-system.
   */
  public void axiomBoxes() {
    JTextField axiomString = new JTextField(turtle.getWord(), 15);
    JLabel axiomStringLabel = new JLabel("Starting Axiom");
    axiomString.setBounds((Display.frameWidth / 2), 200, 300, 25);
    axiomStringLabel.setBounds((Display.frameWidth / 2) - 100, 200, 150, 25);

    JTextField axiomLength = new JTextField(Double.toString(turtle.getLength()), 15);
    JLabel axiomLengthLabel = new JLabel("Starting Length");
    axiomLength.setBounds((Display.frameWidth / 2), 225, 300, 25);
    axiomLengthLabel.setBounds((Display.frameWidth / 2) - 100, 225, 150, 25);

    JTextField axiomAngle = new JTextField(Double.toString(turtle.getAngle()), 15);
    JLabel axiomAngleLabel = new JLabel("Starting Angle");
    axiomAngle.setBounds((Display.frameWidth / 2), 250, 300, 25);
    axiomAngleLabel.setBounds((Display.frameWidth / 2) - 100, 250, 150, 25);

    JTextField drawRules = new JTextField(Arrays.toString(linSys.getDrawRules()), 15);
    JLabel drawRulesLabel = new JLabel("Drawing Rules");
    drawRules.setBounds((Display.frameWidth / 2), 275, 300, 25);
    drawRulesLabel.setBounds((Display.frameWidth / 2) - 100, 275, 150, 25);

    JTextField moveRules = new JTextField(Arrays.toString(linSys.getMoveRules()), 15);
    JLabel moveRulesLabel = new JLabel("Moving Rules");
    moveRules.setBounds((Display.frameWidth / 2), 300, 300, 25);
    moveRulesLabel.setBounds((Display.frameWidth / 2) - 100, 300, 150, 25);

    JTextField rulesX = new JTextField(Arrays.toString(linSys.getRulesX()), 15);
    JLabel rulesLabelX = new JLabel("X Rules");
    rulesX.setBounds((Display.frameWidth / 2), 325, 300, 25);
    rulesLabelX.setBounds((Display.frameWidth / 2) - 100, 325, 150, 25);

    JTextField rulesY = new JTextField(Arrays.toString(linSys.getRulesY()), 15);
    JLabel rulesLabelY = new JLabel("Y Rules");
    rulesY.setBounds((Display.frameWidth / 2), 350, 300, 25);
    rulesLabelY.setBounds((Display.frameWidth / 2) - 100, 350, 150, 25);

    add(axiomString);
    add(axiomStringLabel);
    add(axiomLength);
    add(axiomLengthLabel);
    add(axiomAngle);
    add(axiomAngleLabel);
    add(drawRules);
    add(drawRulesLabel);
    add(moveRules);
    add(moveRulesLabel);
    add(rulesX);
    add(rulesLabelX);
    add(rulesY);
    add(rulesLabelY);
  }

  /**
   * Updates the l-sys and turtle for any changes that have been made in the settings page.
   */
  public void saveChanges() {
    JButton save = new JButton("Save Changes");
    save.setBounds((Display.frameWidth / 2) - 75, 750, 150, 50);
    save.addActionListener(e -> {
      linSys.setCurrentClass(currentClass);

      linSys.setLengthRatio(newRatio);
      //buttons.externalReset();

      shapes.setPresetNo(presetNum);
      Display.initialiseTurtleLinden();

      buttons.externalReset();

      axiomBoxes();
    });
    add(save);
  }
}

//Text boxes ect for axioms
//Combobox for presets