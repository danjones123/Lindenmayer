import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
  String newWord;
  double newLength;
  double newAngle;
  double newCoordX;
  double newCoordY;
  String[] newDrawRules;
  String[] newMoveRules;
  String[] newRulesX;
  String[] newRulesY;
  JTextField wordAxiom;
  JTextField lengthAxiom;
  JTextField angleAxiom;
  JTextField coordAxiomX;
  JTextField coordAxiomY;
  JTextField drawRulesText;
  JTextField moveRulesText;
  JTextField rulesTextX;
  JTextField rulesTextY;




  /**
   * Method for making the settings window and adding to it all of the buttons.
   */
  public Settings(SettingsController setCont) {
    this.setCont = setCont;

    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(Initialise.frameWidth, Initialise.frameHeight));
    setLayout(null);

    initialiseParams();
    linClassButtons();
    presetBox();
    lengthRatioButtons();
    axiomBoxes();
    updateAxioms();
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
        updateNewValues();
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
    int boundX = Initialise.frameWidth / 2;
    int boundY = 200;


    wordAxiom = createParamText(word, boundX, boundY);
    newWord = wordAxiom.getText();
    add(wordAxiom);

    lengthAxiom = createParamText(Double.toString(length), boundX, boundY + 25);
    newLength = Double.parseDouble(lengthAxiom.getText());
    add(lengthAxiom);

    angleAxiom = createParamText(Double.toString(angle), boundX, boundY + 50);
    newAngle = Double.parseDouble(angleAxiom.getText());
    add(angleAxiom);

    coordAxiomX = createParamText(Double.toString(coordX), boundX, boundY + 75);
    newCoordX = Double.parseDouble(coordAxiomX.getText());
    add(coordAxiomX);

    coordAxiomY = createParamText(Double.toString(coordY), boundX, boundY + 100);
    newCoordY = Double.parseDouble(coordAxiomY.getText());
    add(coordAxiomY);

    drawRulesText =  createParamText((Arrays.toString(this.drawRules)), boundX,
        boundY + 125);
    newDrawRules = drawRulesText.getText().split(",");
    add(drawRulesText);

    moveRulesText = createParamText((Arrays.toString(this.moveRules)), boundX,
        boundY + 150);
    newMoveRules = moveRulesText.getText().split(",");
    add(moveRulesText);

    rulesTextX = createParamText((Arrays.toString(this.rulesX)), boundX, boundY + 175);
    newRulesX = rulesTextX.getText().split(",");
    add(rulesTextX);

    rulesTextY = createParamText((Arrays.toString(this.rulesY)), boundX, boundY + 200);
    newRulesY = rulesTextY.getText().split(",");
    add(rulesTextY);



    add(createParamLabel("Starting Axiom", boundX, boundY));
    add(createParamLabel("Starting Length", boundX, boundY + 25));
    add(createParamLabel("Starting Angle", boundX, boundY + 50));
    add(createParamLabel("Starting X Co-ordinate", boundX, boundY + 75));
    add(createParamLabel("Starting Y co-ordinate", boundX, boundY + 100));
    add(createParamLabel("Drawing Rules", boundX, boundY + 125));
    add(createParamLabel("Moving Rules", boundX, boundY + 150));
    add(createParamLabel("X Rules", boundX, boundY + 175));
    add(createParamLabel("Y Rules", boundX, boundY + 200));

    JButton updateButton = new JButton("Update");
    updateButton.addActionListener(e -> {
      try {
        newWord = wordAxiom.getText();
        newLength = Double.parseDouble(lengthAxiom.getText());
        newAngle = Double.parseDouble(angleAxiom.getText());
        newCoordX = Double.parseDouble(coordAxiomX.getText());
        newCoordY = Double.parseDouble(coordAxiomY.getText());
        newDrawRules = drawRulesText.getText().substring(1, drawRulesText.getText().length() - 1).split(",");
        newMoveRules = moveRulesText.getText().substring(1, moveRulesText.getText().length() - 1).split(",");
        newRulesX = rulesTextX.getText().substring(1, rulesTextX.getText().length() - 1).split(",");
        newRulesY = rulesTextY.getText().substring(1, rulesTextY.getText().length() - 1).split(",");
      } catch (Exception c) {
        System.out.println("Invalid Entry");
      }
    });

    updateButton.setBounds(boundX, boundY + 400, 100, 30);
    add(updateButton);
  }


  public JTextField createParamText(String values, int boundX, int boundY) {
    JTextField axiomString = new JTextField(values, 15);

    axiomString.setBounds(boundX, boundY, 300, 25);

    return axiomString;
  }

  public JLabel createParamLabel(String label, int boundX, int boundY) {
    JLabel axiomStringLabel = new JLabel(label);
    axiomStringLabel.setBounds(boundX - 100, boundY, 150, 25);

    return axiomStringLabel;
  }

  public void updateNewValues() {

  }

  public void initialiseParams() {
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
   * Calls SettingsController to update the local parameters.
   */
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
    wordAxiom.setText(word);
    lengthAxiom.setText(Double.toString(length));
    angleAxiom.setText(Double.toString(angle));
    coordAxiomX.setText(Double.toString(coordX));
    coordAxiomY.setText(Double.toString(coordY));
    drawRulesText.setText(Arrays.toString(drawRules));
    moveRulesText.setText(Arrays.toString(moveRules));
    rulesTextX.setText(Arrays.toString(rulesX));
    rulesTextY.setText(Arrays.toString(rulesY));
    newWord = wordAxiom.getText();
    newLength = Double.parseDouble(lengthAxiom.getText());
    newAngle = Double.parseDouble(angleAxiom.getText());
    newCoordX = Double.parseDouble(coordAxiomX.getText());
    newCoordY = Double.parseDouble(coordAxiomY.getText());
    newDrawRules = drawRulesText.getText().substring(1, drawRulesText.getText().length() - 1).split(",");
    newMoveRules = moveRulesText.getText().substring(1, moveRulesText.getText().length() - 1).split(",");
    newRulesX = rulesTextX.getText().substring(1, rulesTextX.getText().length() - 1).split(",");
    newRulesY = rulesTextY.getText().substring(1, rulesTextY.getText().length() - 1).split(",");

  }

  /**
   * Updates the l-sys and turtle for any changes that have been made in the settings page to
   * SettingsController.
   */
  public void saveChanges() {
    JButton save = new JButton("Save Changes");
    save.setBounds((Initialise.frameWidth / 2) - 75, 750, 150, 50);
    save.addActionListener(e -> {

      setCont.saveChanges(currentClass, newRatio, presetNum, centreSetTurtle, newCoordX, newCoordY);


      if ((!newWord.equals(word) || newLength != length
          || newAngle != angle || newCoordX != coordX
          || newCoordY != coordY) || !Arrays.equals(newDrawRules, drawRules)
          || !Arrays.equals(newMoveRules, moveRules)
          || !Arrays.equals(newRulesX, rulesX)
          || !Arrays.equals(newRulesY, rulesY)) {
        setCont.changeTurtleLin(newWord, newLength, newAngle, newCoordX, newCoordY, newDrawRules,
            newMoveRules, newRulesX, newRulesY);
      }
      setCont.init();
      updateAxioms();
      //axiomBoxes();

    });
    add(save);
  }
}
