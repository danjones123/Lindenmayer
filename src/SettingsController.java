import java.util.Arrays;

/**
 * Class for the SettingsController to call the Settings page.
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
   * Initiliases the local variables to those taken from the Turtle and Lindenmayer System.
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
  public void saveChanges(int currentClass, double newRatio, int presetNum,
                          boolean centreSetTurtle, double newCoordX, double newCoordY) {
    linSys.setCurrentClass(currentClass);

    linSys.setLengthRatio(newRatio);
    shapes.setPresetNo(presetNum);

    shapes.update();
    Initialise.initialiseNewerTurtleLinden(shapes, newCoordX, newCoordY);


    prod.update();
    butCont.setCentreTurtle(centreSetTurtle);


    butCont.externalReset();
  }


  public void changeTurtleLin(String newWord, double newLength,
                              double newAngle, double newCoordX, double newCoordY,
                              String[] newDrawRules, String[] newMoveRules, String[] newRulesX,
                              String[] newRulesY) {
    //Put changes to axioms in here instead
    //Call saveShapes to update and then put the values into the correct boxes.
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
