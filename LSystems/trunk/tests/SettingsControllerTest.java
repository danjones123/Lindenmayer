import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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


  @Test
  public void testInit() {
    turtle.setWord("FF");
    turtle.setLength(10);
    turtle.setAngle(90);
    linSys.setDrawRules(new String[]{"FFF", "FGF"});
    linSys.setMoveRules(new String[] {"GGG", "GFG"});
    linSys.setRulesX(new String[]{"XXX", "XYX"});
    linSys.setRulesY(new String[] {"YYY", "YXY"});

    SettingsController s = new SettingsController(turtle, linSys, shapes, butCont, prod);

    s.init();
    assertEquals("FF", s.getWord());
    assertEquals(10, s.getLength(), 1e-10);
    assertEquals(90, s.getAngle(), 1e-10);
    assertArrayEquals(new String[]{"FFF", "FGF"}, s.getDrawRules());
    assertArrayEquals(new String[]{"GGG", "GFG"}, s.getMoveRules());
    assertArrayEquals(new String[]{"XXX", "XYX"}, s.getRulesX());
    assertArrayEquals(new String[]{"YYY", "YXY"}, s.getRulesY());
  }

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

    SettingsController s = new SettingsController(turtle, linSys, shapes, butCont, prod);
    s.saveChanges(1, 0.5, 5, 400, 400, false, 90, 90);

    assertEquals(1, linSys.currentClass);
    assertEquals(0.5, linSys.lengthScaler, 1e-10);
    assertEquals(5, shapes.getPresetNo());

  }

  /**
   * Tests that changeTurtleLin() updates the turtle and LinSys rules.
   */
  @Test
  public void testChangeShape() {
    shapes.setPresetNo(2);
    shapes.update();
    turtle.setWord(shapes.getWord());
    turtle.setLength(shapes.getLength());
    turtle.setAngle(shapes.getAngle());
    linSys.setDrawRules(shapes.getDrawRules());
    linSys.setMoveRules(shapes.getMoveRules());
    linSys.setRulesX(shapes.getRulesX());
    linSys.setRulesY(shapes.getRulesY());

    assertEquals("F+F+F+F", turtle.getWord());
    assertEquals(2, turtle.getLength(), 1e-10);
    assertEquals(90, turtle.getAngle(), 1e-10);
    assertArrayEquals(new String[]{"F+G-FF+F+FF+FG+FF-G+FF-F-FF-FG-FFF"}, linSys.getDrawRules());
    assertArrayEquals(new String[]{"GGGGGG"}, linSys.getMoveRules());
    assertArrayEquals(new String[]{""}, linSys.getRulesX());
    assertArrayEquals(new String[]{""}, linSys.getRulesY());

    SettingsController s = new SettingsController(turtle, linSys, shapes, butCont, prod);

    s.changeTurtleLin("F+F++++++F+F", 150, 70, 400, 400, new String[]{"F+F+F+F"},
        new String[]{"F+F+F+F"}, new String[]{"F+F+F+F"}, new String[]{"F+F+F+F"});

    turtle.setWord(shapes.getWord());
    turtle.setLength(shapes.getLength());
    turtle.setAngle(shapes.getAngle());
    linSys.setDrawRules(shapes.getDrawRules());
    linSys.setMoveRules(shapes.getMoveRules());
    linSys.setRulesX(shapes.getRulesX());
    linSys.setRulesY(shapes.getRulesY());

    assertEquals("F+F++++++F+F", turtle.getWord());
    assertEquals(150, turtle.getLength(), 1e-10);
    assertEquals(70, turtle.getAngle(), 1e-10);
    assertArrayEquals(new String[]{"F+F+F+F"}, linSys.getDrawRules());
    assertArrayEquals(new String[]{"F+F+F+F"}, linSys.getMoveRules());
    assertArrayEquals(new String[]{"F+F+F+F"}, linSys.getRulesX());
    assertArrayEquals(new String[]{"F+F+F+F"}, linSys.getRulesY());
  }
}