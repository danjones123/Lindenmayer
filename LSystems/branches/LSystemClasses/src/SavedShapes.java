import java.util.ArrayList;
import java.util.Map;

public class SavedShapes {
  String word;
  double length;
  double angle;
  String[] drawRules;
  String[] moveRules;
  ArrayList<String[][]> shapes = new ArrayList<>();
  String[][] shape;
  String[] shapeDraw;
  String[] shapeMove;

  public SavedShapes(int corresponding) {
    initialise();

    shape = shapes.get(corresponding);
    this.word = shape[0][0];
    this.length = Double.parseDouble(shape[0][1]);
    this.angle = Double.parseDouble(shape[0][2]);
    shapeDraw = new String[shape[1].length];
    shapeMove = new String[shape[2].length];

    for (int i = 0; i < shape[1].length; i++) {
      shapeDraw[i] = shape[1][i];
    }

    for (int j = 0; j < shape[2].length; j++) {
      shapeMove[j] = shape[2][j];
    }

    drawRules = shapeDraw;
    moveRules = shapeMove;
  }

  public void initialise() {
    String[][] squares = {{"F-F-F-F", "5", "90"}, {"FF-FF"}, {"G"}};
    String[][] sierpinski = {{"F--F--F", "10", "60"}, {"F--F--F--G"}, {"GG"}};
    String[][] lakes = {{"F+F+F+F", "2", "90"}, {"F+G-FF+F+FF+FG+FF-G+FF-F-FF-FG-FFF"}, {"GGGGGG"}};
    String[][] scaryTree = {{"F", "5", "25.7"}, {"FF+[+F-F-F]-[-F+F+F]"}, {"G"}};
    String[][] stochastic = {{"F", "2", "30"}, {"F[+F]F[-F]F", "F[+F]F", "F[-F]F"}, {"G"}};
    String[][] shuriken = {{"F-F-F-F", "2", "90"}, {"FF-F-F-F-F-F+F"}, {"G"}};
    String[][] moreSquares = {{"F-F-F-F", "5", "90"}, {"FF-F-F-F-FF"}, {"G"}};
    String[][] rectangles = {{"F-F-F-F", "5", "90"}, {"FF-F+F-F-FF"}, {"G"}};
    String[][] sparse = {{"F-F-F-F", "1", "90"}, {"FF-F--F-F"}, {"G"}};
    String[][] idk = {{"F-F-F-F", "5", "90"}, {"F-FF--F-F"}, {"G"}};
    String[][] idk2 = {{"F-F-F-F", "5", "90"}, {"F-F+F-F-F"}, {"G"}};


    shapes.add(squares);
    shapes.add(sierpinski);
    shapes.add(lakes);
    shapes.add(scaryTree);
    shapes.add(stochastic);
    shapes.add(shuriken);
    shapes.add(moreSquares);
    shapes.add(rectangles);
    shapes.add(sparse);
    shapes.add(idk);
    shapes.add(idk2);


  }

//squarey "F-F-F-F", 90, "FF-F-F-F-FF"




  public String getWord() {
    return word;
  }

  public double getLength() {
    return length;
  }

  public double getAngle() {
    return angle;
  }

  public String[] getDrawRules() {
    return drawRules;
  }

  public String[] getMoveRules() {
    return moveRules;
  }
}
