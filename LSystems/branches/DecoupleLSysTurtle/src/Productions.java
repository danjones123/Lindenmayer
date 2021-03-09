import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Class that shows what each production rule returns.
 *
 * @author Daniel Jones.
 */
public class Productions extends JPanel {
  Turtle startTurtle;
  Turtle prodTurtle;
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
  public Productions(SavedShapes shapes, ProdPainter prodPaint) {
    shapes.update();
    this.shapes = shapes;
    this.prodPaint = prodPaint;

    prodPaint.clear();
    drawingPanel(shapes.getDrawRules(), shapes.getAngle());
    movingPanel(shapes.getMoveRules(), shapes.getAngle());
    panelX(shapes.getRulesX(), shapes.getAngle());
    panelY(shapes.getRulesY(), shapes.getAngle());

    generateButton();
  }

  /**
   * The code to draw a single F and then the production rules for drawing.
   *
   * @param drawRules the array of drawingRules
   * @param angle the angle for the rules to rotate
   */
  public void drawingPanel(String[] drawRules, double angle) {
    startTurtle = new Turtle(1, Color.RED);
    startTurtle.setWord("F");
    startTurtle.setLength(30);
    startTurtle.setAngle(angle);

    startTurtle.setCoords(((double) Initialise.frameWidth / 4),
        ((double) Initialise.frameHeight / 4) - 150);

    startTurtle.rules();

    prodTurtle = new Turtle(1);
    prodTurtle.setWord("F");
    prodTurtle.setLength(30);
    prodTurtle.setAngle(angle);
    prodQueue(drawRules, drawQueue);
    prodTurtle.setWord(nextQueue(drawQueue));
    prodTurtle.setCoords(((double) Initialise.frameWidth / 4 - 75),
        ((double) Initialise.frameHeight / 4));

    prodTurtle.rules();

    prodPaint.callPaint();
  }

  /**
   * The code to draw a single G and then the production rules for moving without drawing.
   *
   * @param moveRules the array of movingRules
   * @param angle the angle for the rules to rotate
   */
  public void movingPanel(String[] moveRules, double angle) {
    startTurtle = new Turtle(2, Color.RED);
    startTurtle.setWord("G");
    startTurtle.setLength(30);
    startTurtle.setAngle(angle);

    startTurtle.setCoords(((double) 3 * Initialise.frameWidth / 4),
        ((double) Initialise.frameHeight / 4) - 150);

    startTurtle.rules();

    prodTurtle = new Turtle(2);
    prodTurtle.setWord("G");
    prodTurtle.setLength(30);
    prodTurtle.setAngle(angle);
    prodQueue(moveRules, moveQueue);
    prodTurtle.setWord(nextQueue(moveQueue));
    prodTurtle.setCoords(((double) 3 * Initialise.frameWidth / 4),
        ((double) Initialise.frameHeight / 4));

    prodTurtle.rules();

    prodPaint.callPaint();
  }

  /**
   * The code to draw a single X and then the production rules for X.
   *
   * @param rulesX the array of X rules
   * @param angle the angle for the rules to rotate
   */
  public void panelX(String[] rulesX, double angle) {
    startTurtle = new Turtle(2, Color.RED);
    startTurtle.setWord("X");
    startTurtle.setLength(30);
    startTurtle.setAngle(angle);

    startTurtle.setCoords(((double) Initialise.frameWidth / 4),
        ((double) 3 * Initialise.frameHeight / 4) - 150);

    startTurtle.rules();

    prodTurtle = new Turtle(2);
    prodTurtle.setWord("X");
    prodTurtle.setLength(30);
    prodTurtle.setAngle(angle);
    prodQueue(rulesX, queueX);
    prodTurtle.setWord(nextQueue(queueX));
    prodTurtle.setCoords(((double) Initialise.frameWidth / 4),
        ((double) 3 * Initialise.frameHeight / 4));

    prodTurtle.rules();

    prodPaint.callPaint();
  }

  /**
   * The code to draw a single Y and then the production rules for Y.
   *
   * @param rulesY the array of Y rules
   * @param angle the angle for the rules to rotate
   */
  public void panelY(String[] rulesY, double angle) {
    startTurtle = new Turtle(2, Color.RED);
    startTurtle.setWord("Y");
    startTurtle.setLength(30);
    startTurtle.setAngle(angle);

    startTurtle.setCoords(((double) 3 * Initialise.frameWidth / 4),
        ((double) 3 * Initialise.frameHeight / 4) - 150);

    startTurtle.rules();

    prodTurtle = new Turtle(2);
    prodTurtle.setWord("Y");
    prodTurtle.setLength(30);
    prodTurtle.setAngle(angle);
    prodQueue(rulesY, queueY);
    prodTurtle.setWord(nextQueue(queueY));
    prodTurtle.setCoords(((double) 3 * Initialise.frameWidth / 4),
        ((double) 3 * Initialise.frameHeight / 4));

    prodTurtle.rules();

    prodPaint.callPaint();

  }

  /**
   * Calls shapes.update to check what the current selected shape is and then sets the panels to
   * have the correct rules.
   */
  public void update() {
    shapes.update();
    prodPaint.clear();

    drawingPanel(shapes.getDrawRules(), shapes.getAngle());
    movingPanel(shapes.getMoveRules(), shapes.getAngle());
    panelX(shapes.getRulesX(), shapes.getAngle());
    panelY(shapes.getRulesY(), shapes.getAngle());

    resetQueue();
  }

  /**
   * A local update that calls the drawing panels without resetting the queues.
   */
  public void smallUpdate() {
    prodPaint.clear();

    drawingPanel(shapes.getDrawRules(), shapes.getAngle());
    movingPanel(shapes.getMoveRules(), shapes.getAngle());
    panelX(shapes.getRulesX(), shapes.getAngle());
    panelY(shapes.getRulesY(), shapes.getAngle());
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
    assert currentProd != null;
    if (currentProd.equals(queue.element()) && queue.size() > 1) {
      currentProd = queue.poll();
    }
    return currentProd;
  }

  /**
   * A JButton which calls smallUpdate to update the drawing a poll the queue for the next
   * production rule.
   */
  public void generateButton() {
    JButton nextProd = new JButton("Show next production");

    nextProd.addActionListener(e -> smallUpdate());
    add(nextProd);
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
