import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Class to try to get very simple visualisation of an LSystem.
 * Starting off by drawing a simple line I am going to see if I can improve from this
 * into drawing several lines for a basic L-System tree.
 *
 * @author Daniel Jones.
 */

public class LearnSwingDraw extends JFrame {

  /**
   * Class to initialise the GUI.
   */
  public LearnSwingDraw() {

    JPanel panel = new JPanel();
    getContentPane().add(panel);
    setSize(1000, 1000);
  }

  /**
   * Paint class to say what to draw onto the panel.
   *
   * @param g is the parameter for the graphics.
   */
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D graphics = (Graphics2D) g;
    Line2D line = new Line2D.Float(500, 400, 200, 200);
    graphics.draw(line);
  }

  public static void main(String[] args) {
    LearnSwingDraw tut = new LearnSwingDraw();
    tut.setVisible(true);
  }


}
