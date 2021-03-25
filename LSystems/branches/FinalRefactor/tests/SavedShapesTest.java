import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for SavedShapes.
 */
public class SavedShapesTest {

  /**
   * Tests that initialise works for a random number in the SavedShape arrayList.
   */
  @Test
  public void initialise() {
    SavedShapes shape = new SavedShapes();
    shape.setPresetNo(12);
    shape.update();

    assertEquals("F", shape.getWord());
    assertEquals(5, shape.getLength(), 1e-10);
    assertEquals(25.7, shape.getAngle(), 1e-10);
    String[] draw = shape.getDrawRules();
    assertEquals("FF+[+F-F-F]-[-F+F+F]", draw[0]);
    String[] move = shape.getMoveRules();
    assertEquals("G", move[0]);
    String[] rulesX = shape.getRulesX();
    assertEquals("", rulesX[0]);
    String[] rulesY = shape.getRulesY();
    assertEquals("", rulesY[0]);
  }
}