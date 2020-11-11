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
    Turtle turtle = new Turtle("F++++GF", 30, 90, 200, 200);
    turtle.rules();
    display.screen();
  }
}
