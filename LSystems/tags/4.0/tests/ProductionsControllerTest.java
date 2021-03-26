import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import org.junit.Test;

/**
 * Tests class for testing the ProductionsController.
 */
public class ProductionsControllerTest {
  SavedShapes shapes = new SavedShapes();
  ProdPainter prodPainter = new ProdPainter();
  ProductionsController prodTest = new ProductionsController(shapes, prodPainter);

  /**
   * Tests that drawingPanel initialises the drawingPanel.
   */
  @Test
  public void drawingPanel() {
    shapes.setPresetNo(0);
    shapes.update();
    prodTest.update();
    prodTest.drawingPanel(shapes.getAngle());
    assertEquals("F--F--F--G", prodTest.prodTurtleF.getWord());
  }

  /**
   * Tests that movingPanel initialises the movingPanel.
   */
  @Test
  public void movingPanel() {
    shapes.setPresetNo(0);
    shapes.update();
    prodTest.update();
    prodTest.movingPanel(shapes.getAngle());
    assertEquals("GG", prodTest.prodTurtleG.getWord());
  }

  /**
   * Tests that panelX initialises the panelX.
   */
  @Test
  public void panelX() {
    shapes.setPresetNo(15);
    shapes.update();
    prodTest.update();
    prodTest.panelX(shapes.getAngle());
    assertEquals("X[-FFF][+FFF]FX", prodTest.prodTurtleX.getWord());
  }

  /**
   * Tests that panelY initialises the panelY.
   */
  @Test
  public void panelY() {
    shapes.setPresetNo(15);
    shapes.update();
    prodTest.update();
    prodTest.panelY(shapes.getAngle());
    assertEquals("YFX[+Y][-Y]", prodTest.prodTurtleY.getWord());
  }

  /**
   * Tests that update updates the correct parameters.
   */
  @Test
  public void update() {
    shapes.setPresetNo(0);
    shapes.update();
    prodTest.update();
    prodTest.drawingPanel(shapes.getAngle());
    assertEquals("F--F--F--G", prodTest.prodTurtleF.getWord());
    shapes.setPresetNo(12);
    prodTest.update();
    prodTest.drawingPanel(shapes.getAngle());
    assertEquals("FF+[+F-F-F]-[-F+F+F]", prodTest.prodTurtleF.getWord());
  }

  /**
   * Tests that prodQueue add the correct variables to the queue.
   */
  @Test
  public void prodQueue() {
    shapes.setPresetNo(23);
    shapes.update();
    prodTest.update();
    prodTest.drawingPanel(shapes.getAngle());
    assertEquals("F[-F]F", prodTest.drawQueue.poll());
    assertEquals("F[+F]F[-F]F", prodTest.drawQueue.poll());
    assertEquals("F[+F]F", prodTest.drawQueue.poll());
  }

  /**
   * Tests that resetQueue resets the queues.
   */
  @Test
  public void resetQueue() {
    shapes.setPresetNo(0);
    shapes.update();

    prodTest.drawingPanel(shapes.getAngle());
    prodTest.movingPanel(shapes.getAngle());
    prodTest.panelX(shapes.getAngle());
    prodTest.panelY(shapes.getAngle());

    assertEquals("F--F--F--G", prodTest.drawQueue.element());
    assertEquals("GG", prodTest.moveQueue.element());
    assertEquals("", prodTest.queueX.element());
    assertEquals("", prodTest.queueY.element());

    prodTest.resetQueue();

    assertTrue(prodTest.drawQueue.isEmpty());
    assertTrue(prodTest.moveQueue.isEmpty());
    assertTrue(prodTest.queueX.isEmpty());
    assertTrue(prodTest.queueY.isEmpty());

  }

  /**
   * Tests that setStartTurtle returns the correct turtle.
   */
  @Test
  public void setStartTurtle() {
    shapes.setPresetNo(0);
    shapes.update();
    prodTest.update();
    prodTest.startTurtleF = new Turtle(1, Color.RED);
    prodTest.setStartTurtle(prodTest.startTurtleF, "F", 30, shapes.getAngle(), 100, 100);

    assertEquals("F", prodTest.startTurtleF.getWord());
    assertEquals(30, prodTest.startTurtleF.getLength(), 1e-10);
    assertEquals(60, prodTest.startTurtleF.getAngle(), 1e-10);

  }

  /**
   * Tests that setProdTurtle returns the correct turtle.
   */
  @Test
  public void setProdTurtle() {
    shapes.setPresetNo(0);
    shapes.update();
    prodTest.update();
    prodTest.prodTurtleF = new Turtle(1, Color.RED);
    prodTest.setProdTurtle(prodTest.prodTurtleF, 30, shapes.getAngle(), 100, 100,
        prodTest.drawQueue, 1, 1);

    assertEquals("F--F--F--G", prodTest.prodTurtleF.getWord());
    assertEquals(30, prodTest.prodTurtleF.getLength(), 1e-10);
    assertEquals(60, prodTest.prodTurtleF.getAngle(), 1e-10);
  }

  /**
   * Tests that nextQueue cycles through the given queue.
   */
  @Test
  public void nextQueue() {
    shapes.setPresetNo(23);
    shapes.update();
    prodTest.update();
    prodTest.drawingPanel(shapes.getAngle());
    assertEquals("F[-F]F", prodTest.nextQueue(prodTest.drawQueue));
    assertEquals("F[+F]F[-F]F", prodTest.nextQueue(prodTest.drawQueue));
    assertEquals("F[+F]F", prodTest.nextQueue(prodTest.drawQueue));
  }
}