/**
 * The Main class to initialise the turtle constructor and start the display.
 *
 * @author Daniel Jones.
 */
public class Main {

  /**
   * Main method to initialise the Display and Turtle.
   *
   * @param args takes the arguments for the method.
   */
  public static void main(String[] args) {
    Display display = new Display();
    display.setup();
    Turtle turtle = new Turtle("FF", 50, 0, 300, 600);
    //turtle.generate(2);
    turtle.rules();

    display.screen();
  }
}
