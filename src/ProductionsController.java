import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Controller class for showing what each production rule returns.
 *
 * @author Daniel Jones.
 */
public class ProductionsController {
  Turtle startTurtleF;
  Turtle prodTurtleF;
  Turtle startTurtleG;
  Turtle prodTurtleG;
  Turtle startTurtleX;
  Turtle prodTurtleX;
  Turtle startTurtleY;
  Turtle prodTurtleY;

  SavedShapes shapes;
  private final ProdPainter prodPaint;
  Queue<String> drawQueue = new LinkedList<>();
  Queue<String> moveQueue = new LinkedList<>();
  Queue<String> queueX = new LinkedList<>();
  Queue<String> queueY = new LinkedList<>();

  /**
   * Constructor that initialises the rules panels with the correct rules and angles.
   *
   * @param shapes is the current shape being displayed
   * @param prodPaint is the painting class for drawing the productions rules to be displayed.
   */
  public ProductionsController(SavedShapes shapes, ProdPainter prodPaint) {
    shapes.update();
    this.shapes = shapes;
    initialiseQueues();
    this.prodPaint = prodPaint;

    prodPaint.clear();

    showProductions();
  }

  /**
   * The code to draw a single F and then the production rules for drawing.
   *
   * @param angle the angle for the rules to rotate
   */
  public void drawingPanel(double angle) {
    startTurtleF = new Turtle(1, Color.RED);
    double startFirstX = ((double) Initialise.frameWidth / 4) - 15;
    double startFirstY = ((double) Initialise.frameHeight / 4) - 150;
    setStartTurtle(startTurtleF, "F", 30, angle, startFirstX, startFirstY);


    prodTurtleF = new Turtle(1);
    double startLastX = ((double) Initialise.frameWidth / 4);
    double startLastY = ((double) Initialise.frameHeight / 4);
    setProdTurtle(prodTurtleF, 30, angle, startLastX, startLastY, drawQueue, 1, 1);
  }

  /**
   * The code to draw a single G and then the production rules for moving without drawing.
   *
   * @param angle the angle for the rules to rotate
   */
  public void movingPanel(double angle) {
    startTurtleG = new Turtle(2, Color.RED);
    double startX = ((double) 3 * Initialise.frameWidth / 4) - 15;
    double startY = ((double) Initialise.frameHeight / 4) - 150;
    setStartTurtle(startTurtleG, "G", 30, angle, startX, startY);


    prodTurtleG = new Turtle(2);
    double startLastX = ((double) 3 * Initialise.frameWidth / 4);
    double startLastY = ((double) Initialise.frameHeight / 4);
    setProdTurtle(prodTurtleG, 30, angle, startLastX, startLastY, moveQueue, 3, 1);
  }

  /**
   * The code to draw a single X and then the production rules for X.
   *
   * @param angle the angle for the rules to rotate
   */
  public void panelX(double angle) {
    startTurtleX = new Turtle(1, Color.RED);
    double startX = (double) Initialise.frameWidth / 4;
    double startY = ((double) 3 * Initialise.frameHeight / 4) - 150;
    setStartTurtle(startTurtleX, "X", 30, angle, startX, startY);


    prodTurtleX = new Turtle(1);
    double startLastX = ((double) Initialise.frameWidth / 4);
    double startLastY = ((double) 3 * Initialise.frameHeight / 4);
    setProdTurtle(prodTurtleX, 30, angle, startLastX, startLastY, queueX, 1, 3);
  }

  /**
   * The code to draw a single Y and then the production rules for Y.
   *
   * @param angle the angle for the rules to rotate
   */
  public void panelY(double angle) {
    startTurtleY = new Turtle(1, Color.RED);
    double startX = ((double) 3 * Initialise.frameWidth / 4);
    double startY = ((double) 3 * Initialise.frameHeight / 4) - 150;
    setStartTurtle(startTurtleY, "Y", 30, angle, startX, startY);


    prodTurtleY = new Turtle(1);
    double startLastX = ((double) 3 * Initialise.frameWidth / 4);
    double startLastY = ((double) 3 * Initialise.frameHeight / 4);
    setProdTurtle(prodTurtleY, 30, angle, startLastX, startLastY, queueY, 3, 3);
  }


  /**
   * Sets the parameters to the starting turtle which just shows a single character.
   *
   * @param turtle is the turtle to be initialised.
   * @param word is the word to give the turtle.
   * @param length is the length of the line.
   * @param angle is the angle of the turtle.
   * @param startX is the starting X co-ordinate
   * @param startY is the starting Y co-ordinate
   */
  public void setStartTurtle(Turtle turtle, String word, double length, double angle,
                             double startX, double startY) {
    turtle.setWord(word);
    turtle.setLength(length);
    turtle.setAngle(angle);
    turtle.setCoords(startX, startY);
  }

  /**
   * Initialises the production turtle which shows the result of applying one production rule.
   *
   * @param turtle is the turtle to be initialised.
   * @param length is the length of the lines.
   * @param angle is the angle between the lines.
   * @param startX is the starting X co-ordinate (this will be overwritten by centre)
   * @param startY is the starting Y co-ordinate (this will be overwritten by centre)
   * @param queue is the queue that will hold and cycle through the production rules.
   * @param sideX set to 1 if on the left of centre and 3 if on the right.
   * @param sideY set to 1 if above centre and 3 if below.
   */
  public void setProdTurtle(Turtle turtle, double length, double angle,
                            double startX, double startY, Queue<String> queue,
                            int sideX, int sideY) {
    turtle.setLength(length);
    turtle.setAngle(angle);
    turtle.setWord(nextQueue(queue));

    turtle.setCoords(startX, startY);

    turtle.rules();
    turtle.centre(((double) sideX * Initialise.frameWidth / 2),
        ((double) sideY * Initialise.frameHeight / 2));
    prodPaint.clear();
  }



  /**
   * Calls shapes.update to check what the current selected shape is and then sets the panels to
   * have the correct rules.
   */
  public void update() {
    shapes.update();
    resetQueue();
    initialiseQueues();
    showProductions();
  }

  /**
   * Updates the shapes with customised values.
   *
   * @param shapes is the SavedShapes object that has been customised by the user.
   */
  public void customUpdate(SavedShapes shapes) {
    this.shapes = shapes;
    resetQueue();
    initialiseQueues();
    showProductions();
  }


  /**
   * Clears the screen before calling the methods to initialise all the turtles and draw the
   * productions.
   */
  public void showProductions() {
    prodPaint.clear();

    drawingPanel(shapes.getAngle());
    movingPanel(shapes.getAngle());
    panelX(shapes.getAngle());
    panelY(shapes.getAngle());

    startTurtleF.rules();
    prodTurtleF.rules();
    startTurtleG.rules();
    prodTurtleG.rules();
    startTurtleX.rules();
    prodTurtleX.rules();
    startTurtleY.rules();
    prodTurtleY.rules();

    prodPaint.callPaint();
  }


  /**
   * Produces the queue for a given set of rules and adds all the production rules from the array
   * into it.
   *
   * @param rules the production rules.
   * @param queue the queue for the rules to be added to.
   */
  public void prodQueue(String[] rules, Queue<String> queue) {
    int ruleNum = rules.length;
    queue.addAll(Arrays.asList(rules).subList(0, ruleNum));
  }

  /**
   * Polls the top element of the queue and then adds it to the back.
   * Also checks that the current element is not the same as the previous to prevent repeats.
   *
   * @param queue is the queue to be polled.
   * @return returns the string that was at the top of the queue.
   */
  public String nextQueue(Queue<String> queue) {
    String currentProd = queue.poll();
    queue.add(currentProd);
    return currentProd;
  }

  /**
   * Calls prodQueue for each of the rule arrays and queues to fill the queues with the correct
   * values.
   */
  public void initialiseQueues() {
    prodQueue(shapes.getDrawRules(), drawQueue);
    prodQueue(shapes.getMoveRules(), moveQueue);
    prodQueue(shapes.getRulesX(), queueX);
    prodQueue(shapes.getRulesY(), queueY);
  }

  /**
   * Resets all of the queue's.
   */
  public void resetQueue() {
    drawQueue.clear();
    moveQueue.clear();
    queueX.clear();
    queueY.clear();
  }
}
