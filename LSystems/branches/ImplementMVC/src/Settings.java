import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
  JComboBox<String> presets;
  private boolean centreSetTurtle = true;
  boolean useStochAngles = false;
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
  private String[] newDrawRules;
  private String[] newMoveRules;
  private String[] newRulesX;
  private String[] newRulesY;
  private JTextField wordAxiom;
  private JTextField lengthAxiom;
  private JTextField angleAxiom;
  private JTextField coordAxiomX;
  private JTextField coordAxiomY;
  private JTextField drawRulesText;
  private JTextField moveRulesText;
  private JTextField rulesTextX;
  private JTextField rulesTextY;
  private JTextField lengthRatio;
  private int presetArrLength = 0;
  String[] presetNames;
  JCheckBox stochAngle;
  JTextField minAngle;
  JTextField maxAngle;
  double minStochAngle = newAngle;
  double maxStochAngle = newAngle;
  JCheckBox changeDrawRulesProb = new JCheckBox();
  JCheckBox changeMoveRulesProb = new JCheckBox();
  JCheckBox changeEcksRulesProb = new JCheckBox();
  JCheckBox changeWhyRulesProb = new JCheckBox();
  JTextField drawRulesProbs = new JTextField();
  JTextField moveRulesProbs = new JTextField();
  JTextField ecksRulesProbs = new JTextField();
  JTextField whyRulesProbs = new JTextField();


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
    //saveChanges();
    stochAngleCheckBox();
    stochasticAngleMinMax();
    centreTurtle();
    restoreDefault();
    saveNewPreset();
    saveChangesButton();
    //deleteCurrent();
    changeStochRulesButton(changeDrawRulesProb, drawRulesProbs, 325);
    newProbabilities(drawRulesProbs, drawRulesText, 325, "draw");
    changeStochRulesButton(changeMoveRulesProb, moveRulesProbs, 350);
    newProbabilities(moveRulesProbs, moveRulesText, 350, "move");
    changeStochRulesButton(changeEcksRulesProb, ecksRulesProbs, 375);
    newProbabilities(ecksRulesProbs, rulesTextX, 375, "x");
    changeStochRulesButton(changeWhyRulesProb, whyRulesProbs, 400);
    newProbabilities(whyRulesProbs, rulesTextY, 400, "y");

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
    deterministic.addActionListener(e -> {
      currentClass = 1;
      stochAngle.setEnabled(false);
      minAngle.setEnabled(false);
      maxAngle.setEnabled(false);
      changeDrawRulesProb.setEnabled(false);
      changeMoveRulesProb.setEnabled(false);
      changeEcksRulesProb.setEnabled(false);
      changeWhyRulesProb.setEnabled(false);
    });
    stochastic.addActionListener(e -> {
      currentClass = 2;
      stochAngle.setEnabled(true);
      changeDrawRulesProb.setEnabled(true);
      changeMoveRulesProb.setEnabled(true);
      changeEcksRulesProb.setEnabled(true);
      changeWhyRulesProb.setEnabled(true);
    });

    add(deterministic);
    add(stochastic);
  }

  /**
   * Method that creates a dropdown list of the possible presets for the user to choose from.
   */
  public void presetBox() {
    presetArrLength = presetNumber();
    presetNames = new String[presetArrLength];
    fillPresets(presetNames);

    JLabel presetLabel = new JLabel("Choose Preset");
    presets = new JComboBox<>(presetNames);
    presets.setBounds((Initialise.frameWidth / 2) - 50, 100, 150, 30);
    presetLabel.setBounds((Initialise.frameWidth / 2) - 150, 100, 150, 30);

    presets.addActionListener(e -> {
      if (e.getSource().equals(presets)) {
        presetNum = presets.getSelectedIndex();
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
    try {
      savedNames = new Scanner(new File("src\\SavedPresets"));
      int i = 0;

      savedNames.reset();
      while (savedNames.hasNextLine()) {
        String data = savedNames.nextLine();
        String[] tokens = data.split("/");
        stringToBeFilled[i] = tokens[0];
        i++;
      }
    } catch (FileNotFoundException c) {
      System.out.println("File not found");
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
    int presetCount = 0;
    try {
      savedCounter = new Scanner(new File("src\\SavedPresets"));
      while (savedCounter.hasNextLine()) {
        String data = savedCounter.nextLine();
        String[] tokens = data.split("/");
        if (tokens[0] != null) {
          presetCount++;
        }
      }
    } catch (FileNotFoundException c) {
      System.out.println("File not found");
    }
    return presetCount;
  }

  /**
   * Method for creating the buttons for user input length ratio.
   */
  public void lengthRatioButtons() {
    lengthRatio = new JTextField("Input length scalar", 10);
    lengthRatio.setBounds((Initialise.frameWidth / 2) - 75, 150, 150, 25);
    lengthRatio.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        JTextField source = (JTextField) e.getComponent();
        source.setText("");
        source.removeFocusListener(this);
      }
    });
    add(lengthRatio);
  }

  /**
   * Creates a checkbox that sets the min and max angle boxes to enabled if the box is ticked.
   */
  public void stochAngleCheckBox() {
    stochAngle = new JCheckBox();
    stochAngle.setText("Use Stochastic angles");
    stochAngle.setFocusable(false);
    stochAngle.setBounds((Initialise.frameWidth / 2) - 150, 625, 300, 25);
    stochAngle.setEnabled(false);

    stochAngle.addActionListener(e -> {
      if (e.getSource() == stochAngle) {
        useStochAngles = stochAngle.isSelected();
        minAngle.setEnabled(useStochAngles);
        maxAngle.setEnabled(useStochAngles);
      }
    });
    add(stochAngle);
  }

  /**
   * Method for the user to input if they want stochastic angles.
   */
  public void stochasticAngleMinMax() {
    minAngle = new JTextField("Input minimum angle", 10);
    maxAngle = new JTextField("Input minimum angle", 10);
    maxAngle.setBounds((Initialise.frameWidth / 2) - 150, 650, 150, 25);
    minAngle.setBounds((Initialise.frameWidth / 2), 650, 150, 25);
    minAngle.setEnabled(false);
    maxAngle.setEnabled(false);

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
    int boundX = (Initialise.frameWidth / 2) - 200;
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
    add(createParamLabel("Line Length", boundX, boundY + 25));
    add(createParamLabel("Starting Angle", boundX, boundY + 50));
    add(createParamLabel("Starting X co-ordinate", boundX, boundY + 75));
    add(createParamLabel("Starting Y co-ordinate", boundX, boundY + 100));
    add(createParamLabel("Drawing Rules", boundX, boundY + 125));
    add(createParamLabel("Moving Rules", boundX, boundY + 150));
    add(createParamLabel("X Rules", boundX, boundY + 175));
    add(createParamLabel("Y Rules", boundX, boundY + 200));
  }

  /**
   * Creates a JTextField with a given String and bounds.
   *
   * @param values is the value to be displayed by the TextField
   * @param boundX is the X co-ordinate
   * @param boundY is the Y co-ordinate
   * @return returns the created JTextField
   */
  public JTextField createParamText(String values, int boundX, int boundY) {
    JTextField axiomString = new JTextField(values, 15);

    axiomString.setBounds(boundX, boundY, 300, 25);

    return axiomString;
  }

  /**
   * Creates a JLabel with a given label.
   *
   * @param label is the String to be displayed
   * @param boundX is the x co-ordinate of the label
   * @param boundY is the y co-ordinate of the label
   * @return returns the JLabel.
   */
  public JLabel createParamLabel(String label, int boundX, int boundY) {
    JLabel axiomStringLabel = new JLabel(label);
    axiomStringLabel.setBounds(boundX - 130, boundY, 150, 25);

    return axiomStringLabel;
  }

  /**
   * Method to create checkboxes that enable TextFields if the user has ticked the box.
   *
   * @param changeRulesBox is the JCheckBox to be created.
   * @param probsText is the TextField to be initialised if the box is ticked.
   * @param valY is the y-coordinate for the box to be created on.
   */
  public void changeStochRulesButton(JCheckBox changeRulesBox, JTextField probsText, int valY) {
    changeRulesBox.setFocusable(false);
    changeRulesBox.setBounds((Initialise.frameWidth / 2) + 100, valY, 20, 25);
    changeRulesBox.setEnabled(false);

    changeRulesBox.addActionListener(e -> {
      if (e.getSource() == changeRulesBox) {
        probsText.setEnabled(changeRulesBox.isSelected());
      }
    });
    add(changeRulesBox);
  }

  /**
   * Method to call pollCustomRules if the user writes in the JTextField.
   *
   * @param rulesProbs is the TextField for the user to write into.
   * @param rulesText is the TextField that contains the rules that the user types probabilities
   *                  for.
   * @param valY is the y co-ordinate for the JTextField to be initialised at.
   * @param whichRule is the String which corresponds to the rule being changed.
   */
  public void newProbabilities(JTextField rulesProbs, JTextField rulesText, int valY,
                               String whichRule) {
    rulesProbs.setText("Input rule probabilities");
    rulesProbs.setBounds((Initialise.frameWidth / 2) + 120, valY, 200, 25);
    rulesProbs.setEnabled(false);
    rulesProbs.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        JTextField source = (JTextField) e.getComponent();
        source.setText("");
        source.removeFocusListener(this);
      }
    });

    rulesProbs.addActionListener(e -> pollCustomRules(rulesProbs, rulesText, whichRule));

    add(rulesProbs);
  }

  /**
   * Takes the custom rule probabilities assigned by the user and checks if they are valid, if they
   * are it calls SettingsController to call Lindenmayer to apply the new rule probabilities.
   *
   * @param rulesProbs is the JTextField of rule probabilities
   * @param rulesText is the JTextField of rules for the probabilities to be applied to.
   * @param whichRule is the String of which rule is being used.
   */
  public void pollCustomRules(JTextField rulesProbs, JTextField rulesText, String whichRule) {
    String[] ruleProbabilitiesString;
    Double[] ruleProbabilitiesDub;
    try {
      ruleProbabilitiesString = rulesProbs.getText().split(",");
      ruleProbabilitiesDub = new Double[ruleProbabilitiesString.length];
      double totalNum = 0;
      for (int i = 0; i < ruleProbabilitiesString.length; i++) {
        ruleProbabilitiesDub[i] = Double.parseDouble(ruleProbabilitiesString[i]);
        totalNum += ruleProbabilitiesDub[i];
      }
      try {
        if (rulesProbs.getText().split(",").length != rulesText.getText().split(",").length) {
          JOptionPane.showConfirmDialog(null, "You did not input one probability per rule",
              "ERROR", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        } else {
          if (totalNum != 1) {
            DecimalFormat df = new DecimalFormat("0.00");
            df.format(totalNum);
            for (Double formatDouble : ruleProbabilitiesDub) {
              df.format(formatDouble);
            }
            for (int i = 0; i < ruleProbabilitiesDub.length; i++) {
              ruleProbabilitiesDub[i] = ruleProbabilitiesDub[i] / totalNum;
            }
          }
          setCont.linCustomRules(whichRule, rulesProbs.isEnabled(), ruleProbabilitiesDub);
        }
      } catch (NullPointerException c) {
        System.out.println("NULL");
      }
    } catch (NumberFormatException c) {
      System.out.println("NUMBER FORMAT");
    }

  }

  /**
   * Initialises the parameters to the default preset values.
   */
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
    initialiseParams();
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
    newDrawRules = getNewRules(drawRulesText);
    newMoveRules = getNewRules(moveRulesText);
    newRulesX = getNewRules(rulesTextX);
    newRulesY = getNewRules(rulesTextY);
  }

  /**
   * Method to retrieve an array from the JTextField split by , and without whitespace.
   *
   * @param rules is the JTextField of rules to be converted to a String array.
   * @return returns the new array.
   */
  public String[] getNewRules(JTextField rules) {
    String[] newRules = rules.getText().substring(1, rules.getText().length() - 1).split(",");
    for (int i = 0; i < newRules.length; i++) {
      newRules[i] = newRules[i].replaceAll("\\s+", "");
    }
    return newRules;
  }

  /**
   * Saves a new preset with the given name and adds it to the presets combobox.
   */
  public void saveNewPreset() {
    JButton saveNew = new JButton("Save New Preset");
    saveNew.setBounds(325, 425, 150, 25);
    saveNew.addActionListener(e -> {
      saveChanges();
      String name = JOptionPane.showInputDialog("Input preset name", null);
      if (name != null) {
        if (setCont.newPreset(name)) {
          presets.addItem(name);
          presets.setSelectedIndex(presetArrLength);
          saveChanges();
        } else {
          JOptionPane.showConfirmDialog(null, "This preset already exists",
              "ERROR", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    add(saveNew);
  }

  /*public void deleteCurrent() {
    JButton deletePreset = new JButton("Delete Selected Preset");
    deletePreset.setBounds(475, 425, 175, 25);
    deletePreset.addActionListener(e -> {
      saveChanges();
      int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete");
      System.out.println(option);
      if (option == 0) {
        setCont.deletePreset(presetNames[presets.getSelectedIndex()]);
      }
      /*try {
        //setCont.newPreset(name);
        //presets.addItem(name);
        //presets.setSelectedIndex(presetArrLength);
        saveChanges();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    });
    add(deletePreset);
  }
    */


  /**
   * Button for calling the methods in save changes.
   */
  public void saveChangesButton() {
    JButton save = new JButton("Save Changes");
    save.setBounds((Initialise.frameWidth / 2) - 150, 750, 150, 50);
    save.addActionListener(e -> saveChanges());
    add(save);
  }

  /**
   * Updates the l-sys and turtle for any changes that have been made in the settings page to
   * SettingsController.
   */
  public void saveChanges() {
    try {
      newRatio = Double.parseDouble(lengthRatio.getText());
    } catch (NumberFormatException c) {
      newRatio = 1;
    }

    try {
      minStochAngle = Double.parseDouble(minAngle.getText());
      maxStochAngle = Double.parseDouble(maxAngle.getText());
    }  catch (NumberFormatException c) {
      minStochAngle = newAngle;
      maxStochAngle = newAngle;
    }

    try {
      newWord = wordAxiom.getText();
      newLength = Double.parseDouble(lengthAxiom.getText());
      newAngle = Double.parseDouble(angleAxiom.getText());
      newCoordX = Double.parseDouble(coordAxiomX.getText());
      newCoordY = Double.parseDouble(coordAxiomY.getText());
      newDrawRules = getNewRules(drawRulesText);
      newMoveRules = getNewRules(moveRulesText);
      newRulesX = getNewRules(rulesTextX);
      newRulesY = getNewRules(rulesTextY);
    } catch (Exception c) {
      System.out.println("Invalid Entry");
    }

    pollCustomRules(drawRulesProbs, drawRulesText, "draw");
    pollCustomRules(moveRulesProbs, moveRulesText, "move");
    pollCustomRules(ecksRulesProbs, rulesTextX, "x");
    pollCustomRules(whyRulesProbs, rulesTextY, "y");


    if (centreSetTurtle) {
      setCont.saveChanges(currentClass, newRatio, presetNum, true, (double)
              Initialise.frameWidth / 2, (double) Initialise.frameHeight / 2, useStochAngles,
          minStochAngle, maxStochAngle);
    } else {
      setCont.saveChanges(currentClass, newRatio, presetNum, false, newCoordX, newCoordY,
          useStochAngles, minStochAngle, maxStochAngle);
    }

    if ((!newWord.equals(word) || newLength != length
        || newAngle != angle || newCoordX != coordX
        || newCoordY != coordY) || !Arrays.equals(newDrawRules, drawRules)
        || !Arrays.equals(newMoveRules, moveRules)
        || !Arrays.equals(newRulesX, rulesX)
        || !Arrays.equals(newRulesY, rulesY)) {
      if (centreSetTurtle) {
        setCont.changeTurtleLin(newWord, newLength, newAngle, (double) Initialise.frameWidth / 2,
            (double) Initialise.frameHeight / 2, newDrawRules, newMoveRules, newRulesX, newRulesY);
      } else {
        setCont.changeTurtleLin(newWord, newLength, newAngle, newCoordX, newCoordY, newDrawRules,
            newMoveRules, newRulesX, newRulesY);
      }
    }
    setCont.init();
    updateAxioms();
  }

  /**
   * Restores the values within settings to their default values.
   */
  public void restoreDefault() {
    JButton restoreDefault = new JButton("Restore Default");
    restoreDefault.setBounds((Initialise.frameWidth / 2), 750, 150, 50);
    restoreDefault.addActionListener(e -> {
      newRatio = 1;
      currentClass = 1;
      presetNum = 0;
      centreSetTurtle = true;
      useStochAngles = false;
      presets.setSelectedIndex(0);
      stochAngle.setEnabled(false);
      minAngle.setEnabled(false);
      maxAngle.setEnabled(false);
      initialiseParams();
      updateAxioms();

      setCont.saveChanges(currentClass, newRatio, presetNum, centreSetTurtle,
          (double) Initialise.frameWidth / 2, (double) Initialise.frameHeight / 2, useStochAngles,
          minStochAngle, maxStochAngle);
      initialiseParams();
      setCont.init();

      updateAxioms();

    });
    add(restoreDefault);
  }
}
