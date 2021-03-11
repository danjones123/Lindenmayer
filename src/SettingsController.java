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
     * Method for making the settings window and adding to it all of the buttons.
     *
     * @param turtle is the turtle in use.
     * @param linSys is the l-system in use.
     * @param shapes is the shapes class in use
     * @param butCont is the buttons class.
     */
    public SettingsController(Turtle turtle, Lindenmayer linSys, SavedShapes shapes, ButtonsController butCont,
                    ProductionsController prod) {
      this.linSys = linSys;
      this.turtle = turtle;
      this.shapes = shapes;
      this.butCont = butCont;
      this.prod = prod;
    }

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
    public void saveChanges(int currentClass, double newRatio, int presetNum, boolean centreSetTurtle) {
        linSys.setCurrentClass(currentClass);

        linSys.setLengthRatio(newRatio);
        shapes.setPresetNo(presetNum);
        Initialise.initialiseTurtleLinden();
        prod.update();
        butCont.setCentreTurtle(centreSetTurtle);

        butCont.externalReset();
    }

    public String getWord() {
        return word;
    }

    public double getLength() {
        return length;
    }

    public double getAngle() {
        return angle;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public String[] getDrawRules() {
        return drawRules;
    }

    public String[] getMoveRules() {
        return moveRules;
    }

    public String[] getRulesX() {
        return rulesX;
    }

    public String[] getRulesY() {
        return rulesY;
    }
}
