public class Main {

  public static void main(String[] args) {
    Display display = new Display();
    display.setup();
    Turtle turtle = new Turtle("FGFGFGFGF",10, 3, 100, 100);
    turtle.rules();
    display.screen();
  }
}