import org.junit.Test;

import static org.junit.Assert.*;

public class ProductionsTest {
  SavedShapes shapes = new SavedShapes();
  ProdPainter prodPainter = new ProdPainter();


  @Test
  public void drawingPanel() {
    shapes.setPresetNo(0);
    shapes.update();
    Productions prodTest = new Productions(shapes, prodPainter);
    prodTest.drawingPanel(shapes.getDrawRules(), shapes.getAngle());
    assertEquals("F--F--F--G", prodTest.prodTurtleF.getWord());
  }

  @Test
  public void movingPanel() {
    shapes.setPresetNo(0);
    shapes.update();
    Productions prodTest = new Productions(shapes, prodPainter);
    prodTest.movingPanel(shapes.getMoveRules(), shapes.getAngle());
    assertEquals("GG", prodTest.prodTurtleG.getWord());
  }

  @Test
  public void panelX() {
    shapes.setPresetNo(12);
    shapes.update();
    Productions prodTest = new Productions(shapes, prodPainter);
    prodTest.panelX(shapes.getRulesX(), shapes.getAngle());
    assertEquals("X[-FFF][+FFF]FX", prodTest.prodTurtleX.getWord());
  }

  @Test
  public void panelY() {
    shapes.setPresetNo(12);
    shapes.update();
    Productions prodTest = new Productions(shapes, prodPainter);
    prodTest.panelY(shapes.getRulesY(), shapes.getAngle());
    assertEquals("YFX[+Y][-Y]", prodTest.prodTurtleY.getWord());
  }

  @Test
  public void update() {
    shapes.setPresetNo(0);
    shapes.update();
    Productions prodTest = new Productions(shapes, prodPainter);
    prodTest.drawingPanel(shapes.getDrawRules(), shapes.getAngle());
    assertEquals("F--F--F--G", prodTest.prodTurtleF.getWord());
    shapes.setPresetNo(3);
    prodTest.update();
    prodTest.drawingPanel(shapes.getDrawRules(), shapes.getAngle());
    assertEquals("FF+[+F-F-F]-[-F+F+F]", prodTest.prodTurtleF.getWord());
  }

  @Test
  public void smallUpdate() {
    shapes.setPresetNo(0);
    shapes.update();
    Productions prodTest = new Productions(shapes, prodPainter);
    prodTest.drawingPanel(shapes.getDrawRules(), shapes.getAngle());
    assertEquals("F--F--F--G", prodTest.prodTurtleF.getWord());
    assertEquals("F--F--F--G", prodTest.drawQueue.poll());
    prodTest.showProductions();
    assertEquals("F--F--F--G", prodTest.drawQueue.element());
  }

  @Test
  public void prodQueue() {
    shapes.setPresetNo(18);
    shapes.update();
    Productions prodTest = new Productions(shapes, prodPainter);
    prodTest.drawingPanel(shapes.getDrawRules(), shapes.getAngle());
    assertEquals("F[+F]F[-F]F", prodTest.drawQueue.poll());
    assertEquals("F[+F]F[-F]F", prodTest.drawQueue.poll());
    assertEquals("F[+F]F", prodTest.drawQueue.poll());
    assertEquals("F[-F]F", prodTest.drawQueue.poll());
  }

  @Test
  public void resetQueue() {
    shapes.setPresetNo(0);
    shapes.update();
    Productions prodTest = new Productions(shapes, prodPainter);
    prodTest.drawingPanel(shapes.getDrawRules(), shapes.getAngle());
    prodTest.movingPanel(shapes.getMoveRules(), shapes.getAngle());
    prodTest.panelX(shapes.getRulesX(), shapes.getAngle());
    prodTest.panelY(shapes.getRulesY(), shapes.getAngle());

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
}