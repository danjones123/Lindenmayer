import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class to save inputs for the L-System that can be put into the turtle.
 *
 * @author Daniel Jones.
 */
public class SavedShapes {
  String word;
  double length;
  double angle;
  String[] drawRules;
  String[] moveRules;
  String[] rulesX;
  String[] rulesY;
  ArrayList<String[][]> shapes = new ArrayList<>();
  String[][] shape;
  String[] shapeDraw;
  String[] shapeMove;
  String[] shapeX;
  String[] shapeY;
  private int presetNo = 0;

  /**
   * Constructor for saved shapes which initialises the arrayList and sets the values in
   * SavedShapes to the current int.
   */
  public SavedShapes() {
    presetsFromFile();

    update();
  }

  /**
   * Initialises and updates the rules and axioms with the current preset number.
   */
  public void update() {
    shape = shapes.get(getPresetNo());
    this.word = shape[0][0];
    this.length = Double.parseDouble(shape[0][1]);
    this.angle = Double.parseDouble(shape[0][2]);
    shapeDraw = new String[shape[1].length];
    shapeMove = new String[shape[2].length];
    shapeX = new String[shape[3].length];
    shapeY = new String[shape[4].length];

    System.arraycopy(shape[1], 0, shapeDraw, 0, shape[1].length);
    System.arraycopy(shape[2], 0, shapeMove, 0, shape[2].length);
    System.arraycopy(shape[3], 0, shapeX, 0, shape[3].length);
    System.arraycopy(shape[4], 0, shapeY, 0, shape[4].length);

    drawRules = shapeDraw;
    moveRules = shapeMove;
    rulesX = shapeX;
    rulesY = shapeY;
  }

  /**
   * Pulls the presets from a file and adds them to shapes with the correct separation.
   */
  public void presetsFromFile() {
    Scanner saver;
    saver = new Scanner(Objects.requireNonNull(this.getClass().getClassLoader()
        .getResourceAsStream("SavedPresets")));
    while (saver.hasNextLine()) {
      String data = saver.nextLine();
      String[] tokens = data.split("/");
      removeSpace(tokens);
      String[] axiomSplit = tokens[1].split(",");
      String[] splitF = tokens[2].split(",");
      String[] splitG =  tokens[3].split(",");
      String[] splitX =  tokens[4].split(",");
      String[] splitY =  tokens[5].split(",");
      String[][] newPreset = {axiomSplit, splitF, splitG, splitX, splitY};
      shapes.add(newPreset);
    }
    saver.close();
  }

  /**
   * Removes the space from a given array.
   *
   * @param arr the array to have the spaces removed.
   */
  public void removeSpace(String[] arr) {
    for (int i = 0; i < arr.length; i++) {
      arr[i] = arr[i].replaceAll("\\s+", "");
    }

  }

  /**
   * Gets the word for the turtle.
   *
   * @return returns the word.
   */
  public String getWord() {
    return word;
  }

  /**
   * Gets the length for the turtle.
   *
   * @return returns the length.
   */
  public double getLength() {
    return length;
  }

  /**
   * Gets the angle for the turtle.
   *
   * @return returns the angle.
   */
  public double getAngle() {
    return angle;
  }

  /**
   * Gets the drawRules for the turtle.
   *
   * @return returns the drawRules.
   */
  public String[] getDrawRules() {
    return drawRules;
  }

  /**
   * Gets the moveRules for the turtle.
   *
   * @return returns the moveRules.
   */
  public String[] getMoveRules() {
    return moveRules;
  }

  /**
   * Gets the X Rules for the turtle.
   *
   * @return returns the X Rules.
   */
  public String[] getRulesX() {
    return rulesX;
  }

  /**
   * Gets the Y Rules for the turtle.
   *
   * @return returns the Y Rules.
   */
  public String[] getRulesY() {
    return rulesY;
  }

  /**
   * Sets the preset Number.
   *
   * @param presetNo is the number which presetNumber is set to.
   */
  public void setPresetNo(int presetNo) {
    this.presetNo = presetNo;
  }

  /**
   * Returns the preset number.
   *
   * @return returns presetNo
   */
  public int getPresetNo() {
    return presetNo;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public void setAngle(double angle) {
    this.angle = angle;
  }

  public void setDrawRules(String[] drawRules) {
    this.drawRules = drawRules;
  }

  public void setMoveRules(String[] moveRules) {
    this.moveRules = moveRules;
  }

  public void setRulesX (String[] rulesX) {
    this.rulesX = rulesX;
  }

  public void setRulesY (String[] rulesY) {
    this.rulesY = rulesY;
  }
}
