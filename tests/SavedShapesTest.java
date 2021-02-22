import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for SavedShapes
 */
public class SavedShapesTest {

  /**
   * Tests that initialise works for a random number in the SavedShape arrayList.
   */
  @Test
  public void initialise() {
    SavedShapes shape = new SavedShapes();
    shape.setPresetNo(3);

    assertEquals("F", shape.getWord());
    assertEquals(5, shape.getLength(), 1e-10);
    assertEquals(25.7, shape.getAngle(), 1e-10);
    String[] draw = shape.getDrawRules();
    assertEquals("FF+[+F-F-F]-[-F+F+F]", draw[0]);
    String[] move = shape.getMoveRules();
    assertEquals("G", move[0]);
  }
}