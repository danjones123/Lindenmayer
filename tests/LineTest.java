import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.awt.*;

/**
 * Testing class for the Line class.
 *
 * @author Daniel Jones.
 */

public class LineTest {


  /**
   * Test to make sure the Line can be constructed.
   */
  @Test
  public void constructLine() {
    Line line = new Line(3, 5, 10, 10, Color.BLACK);
    assertEquals(3, line.x1, 1e-10);
    assertEquals(5, line.y1, 1e-10);
    assertEquals(10, line.x2, 1e-10);
    assertEquals(10, line.y2, 1e-10);
  }
}