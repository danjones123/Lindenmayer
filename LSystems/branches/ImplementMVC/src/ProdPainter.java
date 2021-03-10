import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class for displaying the production rules screen.
 *
 * @author Daniel Jones.
 */
public class ProdPainter extends JPanel {
  private static final ArrayList<Line> lines = new ArrayList<>();
  private static final ArrayList<Line> dashLines = new ArrayList<>();

  /**
   * Constructor for display sets the background color to white.
   */
  public ProdPainter() {
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(Initialise.frameWidth, Initialise.frameHeight));
    setLayout(null);
    JLabel drawLabel = new JLabel("Drawing Rules");
    drawLabel.setBounds(Initialise.frameWidth / 4 - 50, 0, 100, 30);
    JLabel moveLabel = new JLabel("Moving Rules");
    moveLabel.setBounds(3 * Initialise.frameWidth / 4 - 50, 0, 100, 30);
    JLabel labelX = new JLabel("X Rules");
    labelX.setBounds(Initialise.frameWidth / 4 - 50, Initialise.frameHeight / 2, 100, 30);
    JLabel labelY = new JLabel("Y Rules");
    labelY.setBounds(3 * Initialise.frameWidth / 4 - 50, Initialise.frameHeight / 2, 100, 30);

    add(drawLabel);
    add(moveLabel);
    add(labelX);
    add(labelY);
  }


  public void addLine(double x1, double y1, double x2, double y2, Color color) {
    Line line = new Line(x1, y1, x2, y2, color);
    lines.add(line);
  }

  public void addDashedLine(double x1, double y1, double x2, double y2, Color color) {
    Line dashLine = new Line(x1, y1, x2, y2, color);
    dashLines.add(dashLine);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    g.setColor(Color.BLACK);

    g.drawLine(0, Initialise.frameHeight / 2, Initialise.frameWidth, Initialise.frameHeight / 2);
    g.drawLine(Initialise.frameWidth / 2, 0, Initialise.frameWidth / 2, Initialise.frameHeight);


    for (Line line : lines) {
      if (line != null) {
        g2d.setColor(line.color);
        g2d.draw(new Line2D.Double(line.x1, line.y1, line.x2, line.y2));
      }
    }



    for (Line line : dashLines) {
      Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
          new float[]{3}, 0);
      if (line != null) {
        g2d.setColor(line.color);
        g2d.setStroke(dashed);
        g2d.draw(new Line2D.Double(line.x1, line.y1, line.x2, line.y2));
      }
    }
  }

  public void callPaint() {
    repaint();
  }

  /**
   * Empties the lines arraylist and removes any lines from the screen.
   */
  public void clear() {
    lines.clear();
    dashLines.clear();
    repaint();
  }


}
