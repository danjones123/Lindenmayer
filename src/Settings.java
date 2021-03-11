import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
  SettingsController setCont;
  private double newRatio = 1;
  private int currentClass = 1;
  private int presetNum = 0;
  private boolean centreSetTurtle = true;
  String word;
  double length;
  double angle;
  double coordX;
  double coordY;
  String[] drawRules;
  String[] moveRules;
  String[] rulesX;
  String[] rulesY;



  /**
   * Method for making the settings window and adding to it all of the buttons.
   */
  public Settings(SettingsController setCont) {
    this.setCont = setCont;

    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(Initialise.frameWidth, Initialise.frameHeight));
    setLayout(null);

    updateAxioms();
    linClassButtons();
    presetBox();
    lengthRatioButtons();
    axiomBoxes();
    saveChanges();
    stochasticAngleButtons();
    centreTurtle();
  }

  /**
   * Method that creates radio buttons to choose between which class of l-system the user would
   * like to use.
   */
  public void linClassButtons() {
    JRadioButton deterministic = new JRadioButton("Deterministic");
    JRadioButton stochastic = new JRadioButton("Stochastic");
    deterministic.setSelected(true);
    deterministic.setBounds((Initialise.frameWidth / 2) - 150, 50, 150, 30);
    stochastic.setBounds((Initialise.frameWidth / 2), 50, 150, 30);

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
    String[] presetNames = new String[presetNumber()];
    fillPresets(presetNames);

    JLabel presetLabel = new JLabel("Choose Preset");
    JComboBox<String> presets = new JComboBox<>(presetNames);
    presets.setBounds((Initialise.frameWidth / 2) - 50, 100, 150, 30);
    presetLabel.setBounds((Initialise.frameWidth / 2) - 100, 100, 150, 30);

    presets.addActionListener(e -> {
      if (e.getSource().equals(presets)) {
        presetNum = presets.getSelectedIndex();
        System.out.println(presets.getSelectedIndex());
      }
    });
    add(presets);
    add(presetLabel);
  }

  /**
   * Fills the presets array with the presets from savedShapes.
   *
   * @param stringToBeFilled is the array of names for the presetNames.
   */
  public void fillPresets(String[] stringToBeFilled) {
    Scanner savedNames;
    savedNames = new Scanner(Objects.requireNonNull(this.getClass().getClassLoader()
        .getResourceAsStream("SavedPresets")));
    int i = 0;

    savedNames.reset();
    while (savedNames.hasNextLine()) {
      String data = savedNames.nextLine();
      String[] tokens = data.split("/");
      stringToBeFilled[i] = tokens[0];
      i++;
    }
  }

  /**
   * Method that gives the number of presets that need to be displayed in the list to prevent lots
   * of nulls from being displayed.
   *
   * @return returns the number of presets in the saved file.
   */
  public int presetNumber() {
    Scanner savedCounter;
    savedCounter = new Scanner(Objects.requireNonNull(this.getClass().getClassLoader()
        .getResourceAsStream("SavedPresets")));
    int presetCount = 0;
    while (savedCounter.hasNextLine()) {
      String data = savedCounter.nextLine();
      String[] tokens = data.split("/");
      if (tokens[0] != null) {
        presetCount++;
      }
    }
    return presetCount;
  }

  /**
   * Method for creating the buttons for user input length ratio.
   */
  public void lengthRatioButtons() {
    JTextField lengthRatio = new JTextField("Input length scalar", 10);
    JButton enterButton = new JButton("Enter Ratio");
    lengthRatio.setBounds((Initialise.frameWidth / 2) - 150, 150, 150, 25);
    enterButton.setBounds((Initialise.frameWidth / 2), 150, 100, 25);

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
   * Method for the user to input if they want stochastic angle growth.
   */
  public void stochasticAngleButtons() {
    //Replace with checkbox
    JRadioButton stochAngleActivate = new JRadioButton("Use Stochastic Angle");
    JRadioButton stochAngleDeactivate = new JRadioButton("Do Not Use Stochastic Angle");
    stochAngleActivate.setBounds((Initialise.frameWidth / 2) - 150, 425, 150, 25);
    stochAngleDeactivate.setBounds((Initialise.frameWidth / 2), 425, 150, 25);
    JTextField minAngle = new JTextField("Input minimum angle", 10);
    JTextField maxAngle = new JTextField("Input minimum angle", 10);
    maxAngle.setBounds((Initialise.frameWidth / 2) - 150, 450, 150, 25);
    minAngle.setBounds((Initialise.frameWidth / 2), 450, 150, 25);

    maxAngle.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        JTextField source = (JTextField) e.getComponent();
        source.setText("");
        source.removeFocusListener(this);
      }
    });

    minAngle.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        JTextField source = (JTextField) e.getComponent();
        source.setText("");
        source.removeFocusListener(this);
      }
    });

    JButton enterButton = new JButton("Enter Ratio");
    enterButton.setBounds((Initialise.frameWidth / 2), 150, 100, 25);

    add(stochAngleActivate);
    add(stochAngleDeactivate);
    add(minAngle);
    add(maxAngle);
  }

  /**
   * Method to toggle centring the turtle in the screen.
   */
  public void centreTurtle() {
    JCheckBox centreTurtle = new JCheckBox();
    centreTurtle.setText("Centre turtle");
    centreTurtle.setFocusable(false);
    centreTurtle.setBounds((Initialise.frameWidth / 2), 500, 150, 25);

    centreTurtle.setSelected(true);
    centreTurtle.addActionListener(e -> {
      if (e.getSource() == centreTurtle) {
        centreSetTurtle = centreTurtle.isSelected();
      }
    });
    add(centreTurtle);
  }

  /**
   * Method for the boxes that show the axioms and productions rules for current turtle and
   * l-system.
   */
  public void axiomBoxes() {
    JTextField axiomString = new JTextField(word, 15);
    JLabel axiomStringLabel = new JLabel("Starting Axiom");
    axiomString.setBounds((Initialise.frameWidth / 2), 200, 300, 25);
    axiomStringLabel.setBounds((Initialise.frameWidth / 2) - 100, 200, 150, 25);

    JTextField axiomLength = new JTextField(Double.toString(length), 15);
    JLabel axiomLengthLabel = new JLabel("Starting Length");
    axiomLength.setBounds((Initialise.frameWidth / 2), 225, 300, 25);
    axiomLengthLabel.setBounds((Initialise.frameWidth / 2) - 100, 225, 150, 25);

    JTextField axiomAngle = new JTextField(Double.toString(angle), 15);
    JLabel axiomAngleLabel = new JLabel("Starting Angle");
    axiomAngle.setBounds((Initialise.frameWidth / 2), 250, 300, 25);
    axiomAngleLabel.setBounds((Initialise.frameWidth / 2) - 100, 250, 150, 25);

    JTextField startingCoordX = new JTextField(Double.toString(coordX), 15);
    JLabel startingCoordLabelX = new JLabel("Starting X Co-ordinate");
    startingCoordX.setBounds((Initialise.frameWidth / 2), 275, 300, 25);
    startingCoordLabelX.setBounds((Initialise.frameWidth / 2) - 100, 275, 150, 25);

    JTextField startingCoordY = new JTextField(Double.toString(coordY), 15);
    JLabel startingCoordLabelY = new JLabel("Starting Y Co-ordinate");
    startingCoordY.setBounds((Initialise.frameWidth / 2), 300, 300, 25);
    startingCoordLabelY.setBounds((Initialise.frameWidth / 2) - 100, 300, 150, 25);

    JTextField drawRules = new JTextField(Arrays.toString(this.drawRules), 15);
    JLabel drawRulesLabel = new JLabel("Drawing Rules");
    drawRules.setBounds((Initialise.frameWidth / 2), 325, 300, 25);
    drawRulesLabel.setBounds((Initialise.frameWidth / 2) - 100, 325, 150, 25);

    JTextField moveRules = new JTextField(Arrays.toString(this.moveRules), 15);
    JLabel moveRulesLabel = new JLabel("Moving Rules");
    moveRules.setBounds((Initialise.frameWidth / 2), 350, 300, 25);
    moveRulesLabel.setBounds((Initialise.frameWidth / 2) - 100, 350, 150, 25);

    JTextField rulesX = new JTextField(Arrays.toString(this.rulesX), 15);
    JLabel rulesLabelX = new JLabel("X Rules");
    rulesX.setBounds((Initialise.frameWidth / 2), 375, 300, 25);
    rulesLabelX.setBounds((Initialise.frameWidth / 2) - 100, 375, 150, 25);

    JTextField rulesY = new JTextField(Arrays.toString(this.rulesY), 15);
    JLabel rulesLabelY = new JLabel("Y Rules");
    rulesY.setBounds((Initialise.frameWidth / 2), 400, 300, 25);
    rulesLabelY.setBounds((Initialise.frameWidth / 2) - 100, 400, 150, 25);

    add(axiomString);
    add(axiomStringLabel);
    add(axiomLength);
    add(axiomLengthLabel);
    add(axiomAngle);
    add(axiomAngleLabel);
    add(startingCoordX);
    add(startingCoordLabelX);
    add(startingCoordY);
    add(startingCoordLabelY);
    add(drawRules);
    add(drawRulesLabel);
    add(moveRules);
    add(moveRulesLabel);
    add(rulesX);
    add(rulesLabelX);
    add(rulesY);
    add(rulesLabelY);
  }

  public void updateAxioms() {
    word = setCont.getWord();
    length = setCont.getLength();
    angle = setCont.getAngle();
    coordX = setCont.getCoordX();
    coordY = setCont.getCoordY();
    drawRules = setCont.getDrawRules();
    moveRules = setCont.getMoveRules();
    rulesX = setCont.getRulesX();
    rulesY = setCont.getRulesY();
  }

  /**
   * Updates the l-sys and turtle for any changes that have been made in the settings page.
   */
  public void saveChanges() {
    JButton save = new JButton("Save Changes");
    save.setBounds((Initialise.frameWidth / 2) - 75, 750, 150, 50);
    save.addActionListener(e -> {

      setCont.saveChanges(currentClass, newRatio, presetNum, centreSetTurtle);
      setCont.init();
      updateAxioms();
      axiomBoxes();

    });
    add(save);
  }
}
