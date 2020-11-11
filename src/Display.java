import java.awt.Color;

/**
 * The GUI class to display the canvas and draw the lines.
 */
public class Display {

  static int startX1 = 0;
  static int startY1 = 0;
  static int endX1 = 0;
  static int endY1 = 0;

  /**
   * Sets up the canvas to be drawn onto.
   */
  public void setup() {
    StdDraw.show();
    StdDraw.setPenColor(Color.BLACK);
    StdDraw.setPenRadius(1);
    StdDraw.setCanvasSize(400, 400);
    StdDraw.setXscale(0, 400);
    StdDraw.setYscale(0, 400);
    StdDraw.enableDoubleBuffering();
  }

  /**
   * Sets the coordinates to be drawn onto by taking the start and finish x and y.
   *
   * @param startX is the initial x position.
   * @param startY in the inital y position.
   * @param endX is the end x position.
   * @param endY is the end y position.
   */
  public static void setCoords(int startX, int startY, int endX, int endY) {
    startX1 = startX;
    startY1 = startY;
    endX1 = endX;
    endY1 = endY;
    StdDraw.line(startX1, startY1, endX1, endY1);
  }

  public void screen() {
    StdDraw.show();
  }

}
