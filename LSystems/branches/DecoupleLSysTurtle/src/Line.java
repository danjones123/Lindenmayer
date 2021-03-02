import java.awt.Color;

/**
 * Class to create Lines to be used in the Display of L-Systems.
 *
 * @author Daniel Jones
 */
public class Line {
  double x1;
  double y1;
  double x2;
  double y2;
  Color color;


  Painting painting = new Painting();

  /**
   * Constructor sets the x1, x2, y1 and y2 variables to those given.
   *
   * @param x1 is the value for x1 to take.
   * @param y1 is the value for y1 to take.
   * @param x2 is the value for x2 to take.
   * @param y2 is the value for y2 to take.
   */
  public Line(double x1, double y1, double x2, double y2, Color color) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.color = color;
  }

  /**
   * Add a line with the given values to the display where they will be added to an ArrayList.
   */
  public void createLine() {
    painting.addLine(x1, y1, x2, y2, color);
  }
}