import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
    try {
      Scanner saver = new Scanner(new File("src\\SavedPresets"));

      while (saver.hasNextLine()) {
        String data = saver.nextLine();
        String[] tokens = data.split("/");
        removeSpace(tokens);
        String[] axiomSplit = tokens[1].split(",");
        String[] splitF = tokens[2].split(",");
        String[] splitG = tokens[3].split(",");
        String[] splitX = tokens[4].split(",");
        String[] splitY = tokens[5].split(",");
        String[][] newPreset = {axiomSplit, splitF, splitG, splitX, splitY};
        shapes.add(newPreset);
        System.out.println(tokens[0]);
      }
      saver.close();
    } catch (FileNotFoundException c) {
      System.out.println("File not found");
    }
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
   * Method to test if the given preset is a duplicate and if not to add it to the SavedPresets
   * file.
   *
   * @param name is the name of the new preset.
   * @param word is the word axiom
   * @param length is the length of the lines
   * @param angle is the angle between the lines
   * @param drawRules is the array of drawing rules
   * @param moveRules is the array of moving rules
   * @param rulesX is the array of rules for X
   * @param rulesY is the array of rules for Y
   */
  public boolean addPreset(String name, String word,  double length, double angle,
                        String[] drawRules, String[] moveRules, String[] rulesX, String[] rulesY) {
    String draw = Arrays.toString(drawRules);
    draw = draw.substring(1, draw.length() - 1);
    String move = Arrays.toString(moveRules);
    move = move.substring(1, move.length() - 1);
    String presetX = Arrays.toString(rulesX);
    presetX = presetX.substring(1, presetX.length() - 1);
    String presetY = Arrays.toString(rulesY);
    presetY = presetY.substring(1, presetY.length() - 1);

    if (!testDuplicate(word, drawRules, moveRules, rulesX, rulesY)) {
      try {
        String newPreset = ("\n" + name + "/ " + word + ", " + length + ", " + angle + "/ "
            + draw + "/ " + move + "/ " + presetX + "/ " + presetY + "/");
        String filename = "src\\SavedPresets";
        FileWriter fw = new FileWriter(filename, true);
        fw.write(newPreset);
        fw.flush();
        fw.close();
      } catch (IOException ioe) {
        System.err.println("IOException: " + ioe.getMessage());
        return false;
      }

      shapes.clear();
      presetsFromFile();
      update();
      return true;
    } else {
      return false;
    }
  }

  /**
   * Method to test if the current preset is a duplicate.
   *
   * @param word is the new preset word to be checked
   * @param draw is the array of drawing rules to be checked
   * @param move is the array of moving rules to be checked
   * @param rulesX is the array of X rules to be checked
   * @param rulesY is the array of Y rules to be checked
   * @return returns true if the new preset is a duplicate.
   */
  public boolean testDuplicate(String word, String[] draw, String[] move, String[] rulesX,
                               String[] rulesY) {
    Scanner saver = new Scanner(Objects.requireNonNull(this.getClass().getClassLoader()
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
      if (word.equals(axiomSplit[0]) && Arrays.equals(splitF, draw) && Arrays.equals(splitG, move)
          && Arrays.equals(splitX, rulesX) && Arrays.equals(splitY, rulesY)) {
        saver.close();
        return true;
      }
    }
    saver.close();
    return false;
  }

  /**
   * Deletes the entry which has the name given.
   *
   * @param name is the name of the entry to be deleted.
   */
  public void deleteEntry(String name) {
    try {

      File original = new File("C:\\Users\\i_lik\\IdeaProjects\\MVCWork\\src\\SavedPresets");
      File tempFile = new File(original.getAbsolutePath() + ".tmp");
      Scanner findFile = new Scanner(original);
      String deleting = null;
      while (findFile.hasNextLine()) {

        String data = findFile.nextLine();
        String[] tokens = data.split("/");
        String findLine = tokens[0];
        if (findLine.equals(name)) {
          deleting = data;
        }
      }
      findFile.close();
      
      BufferedReader reader = new BufferedReader(new FileReader(original));
      PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

      String line;
      int newCounter = 0;
      while ((line = reader.readLine()) != null) {
        String trimmedLine = line.trim();

        if (trimmedLine.equals(deleting)) {
          continue;
        }

        if (newCounter == 0) {
          writer.write(line);
        } else {
          writer.write("\n" + line);
        }
        newCounter++;
      }


      writer.close();
      reader.close();

      boolean deleted = original.delete();
      boolean successful = tempFile.renameTo(original);

      System.out.println("Deleted: " + deleted);
      System.out.println("Renamed: " + successful);
    } catch (Exception c) {
      System.out.println("ERROR");
    }
    shapes.clear();
    presetsFromFile();
    presetNo = 0;
    update();
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

  /**
   * Sets the word to the given word.
   *
   * @param word is the word to be set to.
   */
  public void setWord(String word) {
    this.word = word;
  }

  /**
   * Sets the length to the given length.
   *
   * @param length is the length to be set to.
   */
  public void setLength(double length) {
    this.length = length;
  }

  /**
   * Sets the angle to the given angle.
   *
   * @param angle is the angle to be set to.
   */
  public void setAngle(double angle) {
    this.angle = angle;
  }

  /**
   * Sets the drawRules to the given drawRules.
   *
   * @param drawRules is the drawRules to be set to.
   */
  public void setDrawRules(String[] drawRules) {
    this.drawRules = drawRules;
  }

  /**
   * Sets the moveRules to the given moveRules.
   *
   * @param moveRules is the moveRules to be set to.
   */
  public void setMoveRules(String[] moveRules) {
    this.moveRules = moveRules;
  }

  /**
   * Sets the rulesX to the given rulesX.
   *
   * @param rulesX is the rulesX to be set to.
   */
  public void setRulesX(String[] rulesX) {
    this.rulesX = rulesX;
  }

  /**
   * Sets the rulesY to the given rulesY.
   *
   * @param rulesY is the rulesY to be set to.
   */
  public void setRulesY(String[] rulesY) {
    this.rulesY = rulesY;
  }
}
