import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JPanel;


/**
 * The Display class to create the display.
 *
 * @author Daniel Jones.
 */
public class Painting extends JPanel {
  private static final ArrayList<Line> lines = new ArrayList<>();
  boolean drawCentreLines;

  /**
   * Constructor for display sets the background color to white.
   */
  public Painting() {
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(Display.frameWidth, Display.frameHeight));
    drawCentre();
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




    if (drawCentreLines) {
      g.setColor(Color.RED);
      g.drawLine(0, Display.frameHeight / 2, Display.frameWidth, Display.frameHeight / 2);
      g.drawLine(Display.frameWidth / 2, 0, Display.frameWidth / 2, Display.frameHeight);
    }


    g.setColor(Color.BLACK);

    for (Line line : lines) {
      if (line != null) {
        g2d.setColor(line.color);
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
  public void addLine(double x1, double y1, double x2, double y2, Color color) {
    Line line = new Line(x1, y1, x2, y2, color);
    lines.add(line);
  }

  /**
   * Method for checking if the user would like the centre lines drawn.
   */
  public void drawCentre() {
    JCheckBox centreLineCheckBox = new JCheckBox();
    centreLineCheckBox.setText("Show centre lines");
    centreLineCheckBox.setFocusable(false);
    centreLineCheckBox.setBounds(370, 300, 130, 40);

    centreLineCheckBox.addActionListener(e -> {
      if (e.getSource() == centreLineCheckBox) {
        drawCentreLines = centreLineCheckBox.isSelected();
      }
    });
    add(centreLineCheckBox);
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

