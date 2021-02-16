import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * The Display class to create the display.
 *
 * @author Daniel Jones.
 */
public class Display extends JPanel {
  private static final ArrayList<Line> lines = new ArrayList<>();

  /**
   * Constructor for display sets the background color to white.
   */
  public Display() {
    setBackground(Color.WHITE);
  }

  /**
   * Overrides paintComponent and tells it to print the co-ordinates from lines when they are
   * called.
   *
   * @param g is the graphics component of Swing.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    g.setColor(Color.RED);
    g.drawLine(0, Main.frameHeight / 2, Main.frameWidth, Main.frameHeight / 2);
    g.drawLine(Main.frameWidth / 2, 0, Main.frameWidth / 2, Main.frameHeight);



    g.setColor(Color.BLACK);
    g.drawString("Press generate to draw Lindenmayer System!", 40, 15);

    for (Line line : lines) {
      if (line != null) {
        g2d.draw(new Line2D.Double(line.x1, line.y1, line.x2, line.y2));
      }
    }
  }

  /**
   * Creates a new line with the given parameters and adds the line to the lines array list.
   *
   * @param x1 is the starting x co-ordinate.
   * @param y1 is the starting x co-ordinate.
   * @param x2 is the ending x co-ordinate.
   * @param y2 is the ending x co-ordinate.
   */
  public void addLine(double x1, double y1, double x2, double y2) {
    Line line = new Line(x1, y1, x2, y2);
    lines.add(line);
  }

  /**
   * Calls paintComponent to draw any new lines.
   */
  public void callPaint() {
    repaint();
  }

  /**
   * Empties the lines arraylist and removes any lines from the screen.
   */
  public void clear() {
    lines.clear();
    repaint();
  }
}

