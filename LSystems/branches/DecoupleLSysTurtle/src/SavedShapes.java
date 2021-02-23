import java.util.ArrayList;

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
    savedPresets();

    update();
  }

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
   * Initialises all of the 2D arrays and adds them to the shapes arrayList.
   */
  public void savedPresets() {
    String[][] squares = {{"F-F-F-F", "5", "90"}, {"FF-FF"}, {"G"}, {""}, {""}}; //0
    String[][] sierpinski = {{"F--F--F", "10", "60"}, {"F--F--F--G"}, {"GG"}, {""}, {""} }; //1
    String[][] lakes = {{"F+F+F+F", "2", "90"}, {"F+G-FF+F+FF+FG+FF-G+FF-F-FF-FG-FFF"},
        {"GGGGGG"}, {""}, {""}}; //2
    String[][] scaryTree = {{"F", "5", "25.7"}, {"FF+[+F-F-F]-[-F+F+F]"}, {"G"}, {""}, {""}}; //3
    String[][] stochastic = {{"F", "10", "30"}, {"F[+F]F[-F]F", "F[+F]F", "F[-F]F"}, {"G"},
        {""}, {""}}; //4
    String[][] shuriken = {{"F-F-F-F", "5", "90"}, {"FF-F-F-F-F-F+F"}, {"G"}, {""}, {""}}; //5
    String[][] moreSquares = {{"F-F-F-F", "5", "90"}, {"FF-F-F-F-FF"}, {"G"}, {""}, {""}}; //6
    String[][] rectangles = {{"F-F-F-F", "5", "90"}, {"FF-F+F-F-FF"}, {"G"}, {""}, {""}}; //7
    String[][] sparse = {{"F-F-F-F", "1", "90"}, {"FF-F--F-F"}, {"G"}, {""}, {""}}; //8
    String[][] idk = {{"F-F-F-F", "5", "90"}, {"F-FF--F-F"}, {"G"}, {""}, {""}}; //9
    String[][] idk2 = {{"F-F-F-F", "5", "90"}, {"F-F+F-F-F"}, {"G"}, {""}, {""}}; //10
    String[][] kochIsland = {{"F-F-F-F", "5", "90"}, {"F-F+F+FF-F-F+F"}, {"G"}, {""}, {""}}; //11
    String[][] kochSnowflake = {{"F++F++F", "5", "60"}, {"F-F++F-F"}, {"G"}, {""}, {""}}; //12
    String[][] ecksAndWhy = {{"---YYY", "50", "30"}, {"FF"}, {"",
        ""}, {"X[-FFF][+FFF]FX"}, {"YFX[+Y][-Y]"}}; //13
    String[][] nonStochXy = {{"-----X", "50", "20"}, {"FF"}, {""}, {"F[+X]F[-X]+X"}, {"Y"}}; //14
    String[][] nonStochXy2 = {{"----X", "50", "25.7"}, {"FF"}, {""}, {"F[+X][-X]FX"}, {""}}; //15
    String[][] nonStochXy3 = {{"----X", "50", "22.5"}, {"FF"}, {""}, {"F-[[X]+X]+F[+FX]-X"},
        {""}}; //16
    String[][] hilbert = {{"X", "15", "90"}, {"F"}, {""}, {"+YF-XFX-FY+"}, {"-XF+YFY+FX-"}}; //17
    String[][] turtletoynet = {{"X", "15", "22"}, {"F+[F]F[-F]F", "FF-[-F+F+F]+[+F-F-F]",
        "FF+[+F-F-F]-[F+F+F]"}, {""}, {"F+[[X]-X]-F[-FX]+X"}, {"-XF+YFY+FX-"}}; //18
    String[][] handDrawn = {{"X", "15", "90"}, {"XF+XF", "XF-XF", "XFXXX"}, {""}, {"XF"},
        {""}}; //19
    String[][] ecksAndWhyStochastic = {{"---YYY", "50", "30"}, {"F[+F]F[-F]F", "F[+F]F",
        "F[-F]F"}, {""}, {"X[-FFF][+FFF]FX"}, {"YFX[+Y][-Y]"}}; //20



    shapes.add(squares);
    shapes.add(sierpinski);
    shapes.add(lakes);
    shapes.add(scaryTree);
    shapes.add(stochastic);
    shapes.add(shuriken);
    shapes.add(moreSquares);
    shapes.add(rectangles);
    shapes.add(sparse);
    shapes.add(idk);
    shapes.add(idk2);
    shapes.add(kochIsland);
    shapes.add(kochSnowflake);
    shapes.add(ecksAndWhy);
    shapes.add(nonStochXy);
    shapes.add(nonStochXy2);
    shapes.add(nonStochXy3);
    shapes.add(hilbert);
    shapes.add(turtletoynet);
    shapes.add(handDrawn);
    shapes.add(ecksAndWhyStochastic);
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

  public void setPresetNo(int presetNo) {
    this.presetNo = presetNo;
  }

  public int getPresetNo() {
    return presetNo;
  }
}
