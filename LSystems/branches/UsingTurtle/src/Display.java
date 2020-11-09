import java.awt.*;

public class Display {

  static int startX1 = 0;
  static int startY1 = 0;
  static int endX1 = 0;
  static int endY1 = 0;

  public void setup() {
    StdDraw.show();
    StdDraw.setPenColor(Color.BLACK);
    StdDraw.setPenRadius(1);
    StdDraw.setCanvasSize(400, 400);
    StdDraw.setXscale(0, 400);
    StdDraw.setYscale(0, 400);
    StdDraw.enableDoubleBuffering();
  }


  public static void setCoords(int startX, int startY, int endX, int endY) {
    startX1 = startX;
    startY1 = startY;
    endX1 = endX;
    endY1 = endY;
    StdDraw.line(startX1, startY1, endX1, endY1);
    System.out.println("setcords");
  }

  public void screen(){
    StdDraw.show();
  }

}
