public class Main {

  public static void main(String[] args) {
    Display display = new Display();
    display.setup();
    Turtle turtle = new Turtle("F+F+F+F-F+F+F+F",30, 45, 200, 200);
    turtle.rules();
    display.screen();
  }
}
