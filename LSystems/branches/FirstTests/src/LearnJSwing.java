import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Class for learning the basics of JavaSwing as I currently plan to use this for a GUI.
 * Taken from Alex Lee on Youtube.
 */

public class LearnJSwing implements ActionListener {

  int count = 0;
  JLabel label;
  JFrame frame;
  JPanel panel;

  public LearnJSwing() {
    frame = new JFrame();

    JButton button = new JButton("Click me");
    button.addActionListener(this);
    label = new JLabel("Number of clicks: 0");

    panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(300,300,100,300));
    panel.setLayout(new GridLayout(0, 1));
    panel.add(button);
    panel.add(label);

    frame.add(panel,BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Our GUI");
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    new LearnJSwing();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    count++;
    label.setText("Number of clicks: " + count);
  }
}
