import javax.swing.JFrame;

public class Main {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Frame");
    frame.setSize(400,400);
    frame.setVisible(true);
    Display display = new Display();
    frame.add(display);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Turtle turtle = new Turtle("FF",10, 3, 100, 100);
    turtle.rules();
    display.drawing();
  }
}