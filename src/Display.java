import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Display extends JPanel {

  static int startX1 = 0;
  static int startY1 = 0;
  static int endX1 = 0;
  static int endY1 = 0;

  public void drawing() {
    repaint();
  }

  public static void setCoords(int startX, int startY, int endX, int endY) {
    startX1 = startX;
    startY1 = startY;
    endX1 = endX;
    endY1 = endY;
  }


  public void paint(Graphics g) {

    Graphics2D graph2d = (Graphics2D)g;

    Shape drawLine = new Line2D.Float(startX1, startY1, endX1, endY1);

    graph2d.draw(drawLine);
  }

}
