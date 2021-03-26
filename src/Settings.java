import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
  private JComboBox<String> presets;
  JRadioButton deterministic;
  JRadioButton stochastic;
  private boolean useStochAngles = false;
  private String word;
  private double length;
  private double angle;
  private double coordX;
  private double coordY;
  private String[] drawRules;
  private String[] moveRules;
  private String[] rulesX;
  private String[] rulesY;
  private String newWord;
  private double newLength;
  private double newAngle;
  private double newCoordX;
  private double newCoordY;
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
  private String[] presetNames;
  private JCheckBox stochAngle;
  private JTextField minAngle;
  private JTextField maxAngle;
  private double minStochAngle = newAngle;
  private double maxStochAngle = newAngle;
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
    stochAngleCheckBox();
    stochasticAngleMinMax();
    restoreDefault();
    saveNewPreset();
    saveChangesButton();
    deleteCurrent();
    changeStochRulesButton(changeDrawRulesProb, drawRulesProbs, 350);
    newProbabilities(drawRulesProbs, drawRulesText, 350, "draw");
    changeStochRulesButton(changeMoveRulesProb, moveRulesProbs, 385);
    newProbabilities(moveRulesProbs, moveRulesText, 385, "move");
    changeStochRulesButton(changeEcksRulesProb, ecksRulesProbs, 420);
    newProbabilities(ecksRulesProbs, rulesTextX, 420, "x");
    changeStochRulesButton(changeWhyRulesProb, whyRulesProbs, 455);
    newProbabilities(whyRulesProbs, rulesTextY, 455, "y");
    createInfoBox("Customise the probabilities of each rule being applied by inputting a "
        + "probability corresponding to each rule, separated by a comma. \n For example, '0.25, "
        + "0.5, 0.25' for three rules where the middle one should have twice as much chance as the "
        + "other two.", Initialise.frameWidth / 2 + 320, 465);
  }

  /**
   * Class to create a popup infobox to explain what different buttons do.
   *
   * @param infoMessage Is the message to be displayed.
   * @param valX is the x co-ordinates of the message box
   * @param valY is the y co-ordinates of the message box
   */
  public void createInfoBox(String infoMessage, int valX, int valY) {
    JButton infoButton = new JButton();
    infoButton.setIcon(new ImageIcon("src\\redrawnQ.jpg"));
    infoButton.setFocusable(false);
    infoButton.setBounds(valX, valY, 18, 15);

    infoButton.addActionListener(e -> JOptionPane.showMessageDialog(null, infoMessage, "INFO",
        JOptionPane.INFORMATION_MESSAGE));

    add(infoButton);
  }

  /**
   * Method that creates radio buttons to choose between which class of l-system the user would
   * like to use.
   */
  public void linClassButtons() {
    deterministic = new JRadioButton("Deterministic");
    stochastic = new JRadioButton("Stochastic");
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

    createInfoBox("Choose whether the current L-system will be deterministic or stochatic "
            + "\n A deterministic class will always output the same end result"
            + "\n A stochastic class can have randomness in the production rules or between the "
            + "lines",
        (Initialise.frameWidth / 2) + 150, 65);
    add(deterministic);
    add(stochastic);
  }

  /**
   * Method that creates a dropdown list of the possible presets for the user to choose from.
   */
  public void presetBox() {
    presetArrLength = setCont.presetNumber();
    presetNames = new String[presetArrLength];
    setCont.fillPresets(presetNames);

    JLabel presetLabel = new JLabel("Choose Preset");
    presets = new JComboBox<>(presetNames);
    presets.setBounds((Initialise.frameWidth / 2) - 50, 125, 150, 30);
    presetLabel.setBounds((Initialise.frameWidth / 2) - 150, 125, 150, 30);

    presets.addActionListener(e -> {
      if (e.getSource().equals(presets)) {
        presetNum = presets.getSelectedIndex();
      }
    });
    createInfoBox("Choose from a variety of pre-made L-systems to autofill the required "
        + "parameters.", (Initialise.frameWidth / 2) + 100, 140);
    add(presets);
    add(presetLabel);
  }


  /**
   * Method for creating the buttons for user input length ratio.
   */
  public void lengthRatioButtons() {
    lengthRatio = new JTextField("Input length ratio", 10);
    lengthRatio.setBounds((Initialise.frameWidth / 2) + 100, 210, 150, 25);
    clearTextWhenClicked(lengthRatio);
    createInfoBox("Multiplies the length of the lines by the scalar so that the L-systems do not "
        + "get too big for the screen.", (Initialise.frameWidth / 2) + 250, 219);
    add(lengthRatio);
  }

  /**
   * Creates a checkbox that sets the min and max angle boxes to enabled if the box is ticked.
   */
  public void stochAngleCheckBox() {
    stochAngle = new JCheckBox();
    stochAngle.setFocusable(false);
    stochAngle.setBounds((Initialise.frameWidth / 2) + 100, 245, 25, 25);
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
    minAngle = new JTextField("Min angle", 10);
    maxAngle = new JTextField("Max angle", 10);
    maxAngle.setBounds((Initialise.frameWidth / 2) + 225, 245, 100, 25);
    minAngle.setBounds((Initialise.frameWidth / 2) + 125, 245, 100, 25);
    minAngle.setEnabled(false);
    maxAngle.setEnabled(false);

    clearTextWhenClicked(minAngle);
    clearTextWhenClicked(maxAngle);

    createInfoBox("Input a minimum and maximum value for the angle that will be randomly chosen "
        + "from between the min and max.", (Initialise.frameWidth / 2) + 325, 255);
    add(minAngle);
    add(maxAngle);
  }

  /**
   * Method for the boxes that show the axioms and productions rules for current turtle and
   * l-system.
   */
  public void axiomBoxes() {
    int boundX = (Initialise.frameWidth / 2) - 200;
    int boundY = 175;

    wordAxiom = createParamText(word, boundX, boundY);
    newWord = wordAxiom.getText();
    add(wordAxiom);

    lengthAxiom = createParamText(Double.toString(length), boundX, boundY + 35);
    newLength = Double.parseDouble(lengthAxiom.getText());
    add(lengthAxiom);

    angleAxiom = createParamText(Double.toString(angle), boundX, boundY + 70);
    newAngle = Double.parseDouble(angleAxiom.getText());
    add(angleAxiom);

    coordAxiomX = createParamText(Double.toString(coordX), boundX, boundY + 105);
    newCoordX = Double.parseDouble(coordAxiomX.getText());
    add(coordAxiomX);

    coordAxiomY = createParamText(Double.toString(coordY), boundX, boundY + 140);
    newCoordY = Double.parseDouble(coordAxiomY.getText());
    add(coordAxiomY);

    drawRulesText =  createParamText((Arrays.toString(this.drawRules)), boundX,
        boundY + 175);
    newDrawRules = drawRulesText.getText().split(",");
    add(drawRulesText);

    moveRulesText = createParamText((Arrays.toString(this.moveRules)), boundX,
        boundY + 210);
    newMoveRules = moveRulesText.getText().split(",");
    add(moveRulesText);

    rulesTextX = createParamText((Arrays.toString(this.rulesX)), boundX, boundY + 245);
    newRulesX = rulesTextX.getText().split(",");
    add(rulesTextX);

    rulesTextY = createParamText((Arrays.toString(this.rulesY)), boundX, boundY + 280);
    newRulesY = rulesTextY.getText().split(",");
    add(rulesTextY);

    createInfoBox("This program works by drawing fractals and Lindenmayer systems based on the "
        + "words it is given. \nF tells the program to draw a line of the given length. \nG tells "
        + "the program to move by the given length but not draw a line. \nX and Y create nodes for "
        + "node rewriting.  \nThe starting axiom is the initial word and the rules rewrite the "
        + "current words. \nLength is the length of the lines the program will draw. \nAngle "
        + "is the angle between the lines being created.\n '+' and '-' correspond to a clockwise "
        + "or anticlockwise application of the angle.\nDrawing rules replace the F character"
        + " with the string they hold, as does moving rules with G, and x and y rules with X "
        + "and Y.", boundX + 300, boundY + 9);

    add(createParamLabel("Starting Axiom", boundX, boundY));
    add(createParamLabel("Line Length", boundX, boundY + 35));
    add(createParamLabel("Starting Angle", boundX, boundY + 75));
    add(createParamLabel("Starting X co-ordinate", boundX, boundY + 105));
    add(createParamLabel("Starting Y co-ordinate", boundX, boundY + 140));
    add(createParamLabel("Drawing Rules", boundX, boundY + 175));
    add(createParamLabel("Moving Rules", boundX, boundY + 210));
    add(createParamLabel("X Rules", boundX, boundY + 245));
    add(createParamLabel("Y Rules", boundX, boundY + 280));
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
    clearTextWhenClicked(rulesProbs);

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
            DecimalFormat df = new DecimalFormat("0.000");
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
        System.out.println("NULL Exception" + c);
      }
    } catch (NumberFormatException c) {
      System.out.println("NUMBER FORMAT Exception " + c);
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
    saveNew.setBounds(310, 490, 180, 25);
    saveNew.addActionListener(e -> {
      saveChanges();
      String name = JOptionPane.showInputDialog("Input preset name", null);
      if (name != null && !name.replaceAll("\\s", "").equals("")) {
        if (setCont.newPreset(name)) {
          presets.addItem(name);
          presets.setSelectedIndex(presetArrLength);
          addPresetName(name);
          presetArrLength++;
          saveChanges();
        } else {
          JOptionPane.showConfirmDialog(null, "This preset already exists",
              "ERROR", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
      } else {
        JOptionPane.showConfirmDialog(null, "You did not input a valid name",
            "ERROR", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
      }
    });
    createInfoBox("Saves a new preset with the current parameters for future use.", 490, 500);
    add(saveNew);
  }

  /**
   * Increases the length of presetNames by 1 and adds the newName to the array.
   *
   * @param name is the newName to be added.
   */
  public void addPresetName(String name) {
    String[] newName = Arrays.copyOf(presetNames, presetNames.length + 1);
    newName[presetNames.length] = name;
    presetNames = Arrays.copyOf(newName, newName.length);
  }

  /**
   * Deletes the currently selected Preset.
   */
  public void deleteCurrent() {
    JButton deletePreset = new JButton("Delete Selected Preset");
    deletePreset.setBounds(310, 515, 180, 25);
    deletePreset.addActionListener(e -> {
      saveChanges();
      try {
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently "
            + "delete " + presetNames[presets.getSelectedIndex()] + "? \nThis action cannot be "
            + "undone.");

        if (option == 0) {
          try {
            String name = presetNames[presets.getSelectedIndex()];
            setCont.deletePreset(presetNames[presets.getSelectedIndex()]);
            presets.removeItem(name);
            removePresetName(name);
            presets.setSelectedIndex(0);
            presetArrLength--;
            restoreDefault();
            saveChanges();
          } catch (IndexOutOfBoundsException c) {
            JOptionPane.showConfirmDialog(null, "There was an error finding this preset.",
                "ERROR", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
          } catch (IllegalArgumentException c) {
            System.out.println("IllegalArgument: " + c);
          }
        }
      } catch (ArrayIndexOutOfBoundsException c) {
        System.out.println("Index out of bounds: " + c);
        setCont.updateShapes();
        renewPresetBox();
        saveChanges();
        JOptionPane.showMessageDialog(null, "The preset list was emptied and so it has been reset "
            + "to the default list.");
      }
    });
    createInfoBox("Permanently deletes the selected preset from the preset list", 490, 525);
    add(deletePreset);
  }

  /**
   * Removes the current name from the presetNames array and decreases its length by 1.
   *
   * @param name Is the name to be removed from the array.
   */
  public void removePresetName(String name) {
    String[] removeName = new String[presetNames.length - 1];
    for (int i = 0, j = 0; i < presetNames.length; i++) {
      if (!presetNames[i].equals(name)) {
        removeName[j++] = presetNames[i];
      }
    }
    presetNames = Arrays.copyOf(removeName, removeName.length);
  }

  /**
   * Resets the preset variables back to the original preset list.
   */
  public void renewPresetBox() {
    presetArrLength = setCont.presetNumber();
    presetNames = new String[presetArrLength];
    setCont.fillPresets(presetNames);

    for (String presetName : presetNames) {
      presets.addItem(presetName);
    }

    presets.setSelectedIndex(0);
  }

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
      useStochAngles = false;
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

    if (drawRulesProbs.isEnabled()) {
      pollCustomRules(drawRulesProbs, drawRulesText, "draw");
    }
    if (moveRulesProbs.isEnabled()) {
      pollCustomRules(moveRulesProbs, moveRulesText, "move");
    }
    if (ecksRulesProbs.isEnabled()) {
      pollCustomRules(ecksRulesProbs, rulesTextX, "x");
    }
    if (whyRulesProbs.isEnabled()) {
      pollCustomRules(whyRulesProbs, rulesTextY, "y");
    }

    setCont.saveChanges(currentClass, newRatio, presetNum, newCoordX, newCoordY,
        useStochAngles, minStochAngle, maxStochAngle);


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
  }

  /**
   * Restores the values within settings to their default values.
   */
  public void restoreDefault() {
    JButton restoreDefault = new JButton("Restore Default");
    restoreDefault.setBounds((Initialise.frameWidth / 2), 750, 150, 50);
    restoreDefault.addActionListener(e -> {
      newRatio = 1;
      lengthRatio.setText("Input length ratio");
      currentClass = 1;
      deterministic.setSelected(true);
      presetNum = 0;
      useStochAngles = false;
      presets.setSelectedIndex(0);

      stochAngle.setEnabled(false);
      stochAngle.setSelected(false);
      minAngle.setEnabled(false);
      maxAngle.setEnabled(false);
      minAngle.setText("Min angle");
      maxAngle.setText("Max angle");


      changeDrawRulesProb.setEnabled(false);
      changeDrawRulesProb.setSelected(false);
      changeMoveRulesProb.setEnabled(false);
      changeMoveRulesProb.setSelected(false);
      changeEcksRulesProb.setEnabled(false);
      changeEcksRulesProb.setSelected(false);
      changeWhyRulesProb.setEnabled(false);
      changeWhyRulesProb.setSelected(false);
      drawRulesProbs.setText("Input rule probabilities");
      drawRulesProbs.setEnabled(false);
      moveRulesProbs.setText("Input rule probabilities");
      moveRulesProbs.setEnabled(false);
      ecksRulesProbs.setText("Input rule probabilities");
      ecksRulesProbs.setEnabled(false);
      whyRulesProbs.setText("Input rule probabilities");
      whyRulesProbs.setEnabled(false);

      clearTextWhenClicked(lengthRatio);
      clearTextWhenClicked(minAngle);
      clearTextWhenClicked(maxAngle);
      clearTextWhenClicked(drawRulesProbs);
      clearTextWhenClicked(moveRulesProbs);
      clearTextWhenClicked(ecksRulesProbs);
      clearTextWhenClicked(whyRulesProbs);


      initialiseParams();
      updateAxioms();

      setCont.saveChanges(currentClass, newRatio, presetNum,
          (double) Initialise.frameWidth / 2, (double) Initialise.frameHeight / 2, useStochAngles,
          minStochAngle, maxStochAngle);
      initialiseParams();
      setCont.init();

      updateAxioms();

    });
    add(restoreDefault);
  }

  /**
   * Method to remove the text when a textfield that contains text is clicked.
   *
   * @param field The JTextField that should have its text removed
   */
  public void clearTextWhenClicked(JTextField field) {
    field.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        JTextField source = (JTextField) e.getComponent();
        source.setText("");
        source.removeFocusListener(this);
      }
    });
  }
}
