import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TurtleTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void TestTurtle() {
    Turtle turtle = new Turtle("FFGF", 7, 3.14);
    assertEquals("FFGF", turtle.word);
    assertEquals(7, turtle.length);
  }
}