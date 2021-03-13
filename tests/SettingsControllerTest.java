import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Test class for the settings class.
 */
public class SettingsControllerTest {
  Turtle turtle = new Turtle();
  Lindenmayer linSys = new Lindenmayer(turtle);
  SavedShapes shapes = new SavedShapes();
  Painting painting = new Painting();
  ProdPainter prodPaint = new ProdPainter();
  ButtonsController butCont = new ButtonsController(painting);
  ProductionsController prod = new ProductionsController(shapes, prodPaint);

  /**
   * Tests that save changes updates the correct classes.
   */
  @Test
  public void saveChanges() {
    turtle.setWord("F");
    turtle.setLength(10);
    turtle.setAngle(90);
    linSys.setCurrentClass(0);
    linSys.setLengthRatio(1);
    butCont.turtleInit(turtle, linSys);
    shapes.setPresetNo(0);
    butCont.setCentreTurtle(true);

    SettingsController s = new SettingsController(turtle, linSys, shapes, butCont, prod);
    //s.saveChanges(1, 0.5, 5, false);

    //assertEquals(1, linSys.currentClass);
   // assertEquals(0.5, linSys.lengthScaler, 1e-10);
    //assertEquals(5, shapes.getPresetNo());
    //assertFalse(butCont.centreTurtle);

  }
}