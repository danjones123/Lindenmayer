import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class for the SettingsController to call the Settings page.
 *
 * @author Daniel Jones.
 */
public class SettingsController {
  Turtle turtle;
  Lindenmayer linSys;
  SavedShapes shapes;
  ButtonsController butCont;
  ProductionsController prod;
  private String word;
  private double length;
  private double angle;
  private double coordX;
  private double coordY;
  private String[] drawRules;
  private String[] moveRules;
  private String[] rulesX;
  private String[] rulesY;


  /**
   * Method adding the parameters to the SettingsController.
   *
   * @param turtle is the turtle in use.
   * @param linSys is the l-system in use.
   * @param shapes is the shapes class in use
   * @param butCont is the buttons class.
   */
  public SettingsController(Turtle turtle, Lindenmayer linSys, SavedShapes shapes,
                            ButtonsController butCont, ProductionsController prod) {
    this.linSys = linSys;
    this.turtle = turtle;
    this.shapes = shapes;
    this.butCont = butCont;
    this.prod = prod;
  }

  /**
   * Initialises the local variables to those taken from the Turtle and Lindenmayer System.
   */
  public void init() {
    word = turtle.getWord();
    length = turtle.getLength();
    angle = turtle.getAngle();
    coordX = turtle.getCoordX();
    coordY = turtle.getCoordY();
    drawRules = linSys.getDrawRules();
    moveRules = linSys.getMoveRules();
    rulesX = linSys.getRulesX();
    rulesY = linSys.getRulesY();
  }

  /**
   * Updates the l-sys and turtle for any changes that have been made in the settings page.
   */
  public void saveChanges(int currentClass, double newRatio, int presetNum, double newCoordX,
                          double newCoordY, boolean useStochAngle, double minAngle,
                          double maxAngle) {
    linSys.setCurrentClass(currentClass);
    linSys.stochAngle(useStochAngle, minAngle, maxAngle);
    linSys.setLengthRatio(newRatio);


    shapes.setPresetNo(presetNum);
    shapes.update();

    Initialise.initialiseNewerTurtleLinden(shapes, newCoordX, newCoordY);

    prod.update();

    butCont.externalReset();
  }

  /**
   * Changes the turtle axioms and Lindenmayer rules to those given by the user to Settings.
   *
   * @param newWord is the new word to replace word.
   * @param newLength is the new length to replace length.
   * @param newAngle is the new angle to replace angle.
   * @param newCoordX is the new coordX to replace coordX.
   * @param newCoordY is the new coordYto replace coordY.
   * @param newDrawRules are the new drawRules to replace drawRules.
   * @param newMoveRules are the new moveRules to replace moveRules.
   * @param newRulesX are the new X Rules to replace X Rules.
   * @param newRulesY are the new Y Rules to replace Y Rules.
   */
  public void changeTurtleLin(String newWord, double newLength,
                              double newAngle, double newCoordX, double newCoordY,
                              String[] newDrawRules, String[] newMoveRules, String[] newRulesX,
                              String[] newRulesY) {
    shapes.update();

    if (!shapes.getWord().equals(newWord)) {
      shapes.setWord(newWord);
    }
    if (shapes.getLength() != newLength) {
      shapes.setLength(newLength);
    }
    if (shapes.getAngle() != newAngle) {
      shapes.setAngle(newAngle);
    }
    if (!Arrays.equals(newDrawRules, shapes.getDrawRules())) {
      shapes.setDrawRules(newDrawRules);
    }
    if (!Arrays.equals(newMoveRules, shapes.getMoveRules())) {
      shapes.setMoveRules(newMoveRules);
    }
    if (!Arrays.equals(newRulesX, shapes.getRulesX())) {
      shapes.setRulesX(newRulesX);
    }
    if (!Arrays.equals(newRulesY, shapes.getRulesY())) {
      shapes.setRulesY(newRulesY);
    }
    Initialise.initialiseNewerTurtleLinden(shapes, newCoordX, newCoordY);

    prod.customUpdate(shapes);
  }

  /**
   * Method to take variables for giving custom production rules to Lindenmayer and passes them to
   * Lindenmayer.
   *
   * @param ruleName is the rule array to be given custom values.
   * @param enable is the boolean to say whether or not custom rules should be applied.
   * @param newRatios is the double array of new ratios for the production rules.
   */
  public void linCustomRules(String ruleName, boolean enable, Double[] newRatios) {
    linSys.setCustomRulesBool(enable);
    linSys.customRuleProb(ruleName, newRatios);
  }

  /**
   * Saves a new preset with the given name into the shapes class.
   *
   * @param name is the name of the new preset.
   */
  public boolean newPreset(String name) {
    if (shapes.addPreset(name, word, length, angle, drawRules, moveRules, rulesX, rulesY)) {
      shapes.update();
      return true;
    } else {
      return false;
    }
  }

  /**
   * Calls shapes with a given name to delete the entry with that name.
   *
   * @param name is the name to be deleted.
   */
  public void deletePreset(String name) {
    shapes.deleteEntry(name);
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
      savedNames.close();
    } catch (FileNotFoundException c) {
      System.out.println("File not found Exception " + c);
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
      savedCounter.close();
    } catch (FileNotFoundException c) {
      System.out.println("File not found Exception " + c);
    }
    return presetCount;
  }


  /**
   * Returns the word variable.
   *
   * @return returns word.
   */
  public String getWord() {
    return word;
  }

  /**
   * Returns the length variable.
   *
   * @return returns word.
   */
  public double getLength() {
    return length;
  }

  /**
   * Returns the angle variable.
   *
   * @return returns angle
   */
  public double getAngle() {
    return angle;
  }

  /**
   * Returns the coordX variable.
   *
   * @return returns X co-ordinate
   */
  public double getCoordX() {
    return coordX;
  }

  /**
   * Returns the coordY variable.
   *
   * @return returns Y co-ordinate
   */
  public double getCoordY() {
    return coordY;
  }

  /**
   * Returns the drawRules array.
   *
   * @return returns drawRules
   */
  public String[] getDrawRules() {
    return drawRules;
  }

  /**
   * Returns the moveRules array.
   *
   * @return returns moveRules
   */
  public String[] getMoveRules() {
    return moveRules;
  }

  /**
   * Returns the rulesX array.
   *
   * @return returns rulesX
   */
  public String[] getRulesX() {
    return rulesX;
  }

  /**
   * Returns the rulesY array.
   *
   * @return returns rulesY
   */
  public String[] getRulesY() {
    return rulesY;
  }
}
