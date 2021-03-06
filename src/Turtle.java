import java.awt.Color;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;


/**
 * Class for the Turtle which follow the Turtle rules to draw the L-Systems.
 *
 * @author Daniel Jones
 */
public class Turtle {
  private final DecimalFormat df = new DecimalFormat("0.000000");
  private String initialWord;
  private double initialLength;
  private double initialAngle;
  private double initialCoordX;
  private double initialCoordY;
  private String word = "";
  private double length = 0;
  private double angle = 0;
  double currAngle = 0;
  private double coordX = 0;
  private double coordY = 0;
  double oldX;
  double oldY;
  double lowestCoordX = 1 * 10e10;
  double highestCoordX = 0;
  double lowestCoordY = 1 * 10e10;
  double highestCoordY = 0;
  double startingCoordX;
  double startingCoordY;
  private boolean stochAngle = false;
  private double minAngle;
  private double maxAngle;
  Deque<Point> pointStack = new ArrayDeque<>();
  Deque<Double> angleStack = new ArrayDeque<>();
  Deque<String[]> turtleStack = new ArrayDeque<>();
  Color turtleColor;
  private int screen = 0;

  /**
   * Constructor for Turtle which sets the turtleColor to black by default.
   */
  public Turtle() {
    turtleColor = Color.BLACK;
  }

  /**
   * Constructor for Turtle which sets the turtleColor to the given colour.
   *
   * @param turtleColor is the colour for turtleColor to be set to.
   */
  public Turtle(Color turtleColor) {
    this.turtleColor = turtleColor;
  }

  /**
   * Constructor for Turtle which specifies which "screen" to use.
   *
   * @param screen is the int corresponding to the screen number.
   */
  public Turtle(int screen) {
    this.screen = screen;
    turtleColor = Color.BLACK;
  }

  /**
   * Constructor for Turtle which species screen number and colour.
   *
   * @param screen int corresponding to screen number.
   * @param color the colour for the lines to be drawn.
   */
  public Turtle(int screen, Color color) {
    this.screen = screen;
    turtleColor = color;
  }


  /**
   * Sets the word of the turtle.
   *
   * @param word is the word to be set to.
   */
  public void setWord(String word) {
    this.word = word;
  }

  /**
   * Sets the length of the turtle.
   *
   * @param length is the length to be set to.
   */
  public void setLength(double length) {
    this.length = length;
  }

  /**
   * Sets the angle of the turtle.
   *
   * @param angle is the angle to be set to.
   */
  public void setAngle(double angle) {
    this.angle = angle;
  }

  /**
   * Sets the starting co-ordinates of the turtle.
   *
   * @param x is the starting x co-ordinate.
   * @param y is the starting y co-ordinate.
   */
  public void setCoords(double x, double y) {
    this.coordX = x;
    this.coordY = y;
  }

  /**
   * Saves the starting point of the turtle to allow the turtle to be reset.
   */
  public void saveStartingTurtle() {
    this.initialWord = word;
    this.initialLength = length;
    this.initialAngle = angle;
    this.initialCoordX = coordX;
    this.initialCoordY = coordY;
  }

  /**
   * Getter for word.
   *
   * @return returns the word, the string of the turtle.
   */
  public String getWord() {
    return word;
  }

  /**
   * Getter for length.
   *
   * @return returns the length, the length of the turtle lines.
   */
  public double getLength() {
    return length;
  }

  /**
   * Getter for angle.
   *
   * @return returns the angle.
   */
  public double getAngle() {
    return angle;
  }

  /**
   * Getter for x co-ordinate.
   *
   * @return returns the x co-ordinate.
   */
  public double getCoordX() {
    return coordX;
  }

  /**
   * Getter for y co-ordinate.
   *
   * @return returns the y co-ordinate.
   */
  public double getCoordY() {
    return coordY;
  }

  /**
   * Returns the centre along the X axis of the current turtle drawing.
   *
   * @return returns the midpoint of the highest and lowest co-ordinates.
   */
  public double getMiddleX() {
    return (highestCoordX + lowestCoordX) / 2;
  }

  /**
   * Returns the centre along the Y axis of the current turtle drawing.
   *
   * @return returns the midpoint of the highest and lowest co-ordinates.
   */
  public double getMiddleY() {
    return (highestCoordY + lowestCoordY) / 2;
  }



  /**
   * Rules class to iterate through the String and tell the program what to do at each character.
   */
  public void rules() {
    startingCoordX = coordX;
    startingCoordY = coordY;

    for (int i = 0; i < word.length(); i++) {
      char current = word.charAt(i);
      switch (current) {
        case 'F' -> draw(length, turtleColor);
        case 'G' -> move(length, turtleColor);
        case '+' -> rotate(angle);
        case '-' -> rotate(-angle);
        case '[' -> pushCoords();
        case ']' -> popCoords();
        default -> { }
      }
    }
  }

  /**
   * Stores the original x and y coordinates and then transforms the new ones by adding them to
   * length multiplied with the sin/cos of currAngle so as to give it a distance to move and a
   * direction for it to move to.
   *
   * @param length is the length for the coordinates to move.
   */
  public void draw(double length, Color color) {
    calcHighLowCoord();

    oldX = Double.parseDouble(df.format(coordX));
    oldY = Double.parseDouble(df.format(coordY));
    coordX += Double.parseDouble(df.format(length * Math.cos(currAngle)));
    coordY += Double.parseDouble(df.format(length * Math.sin(currAngle)));
    Line l = new Line(oldX, oldY, coordX, coordY, color);
    if (screen == 0) {
      l.createLine();
    } else {
      l.prodLine();
    }

    calcHighLowCoord();
  }

  /**
   * Moves the coordinates by the given length multiplied by the given angle but does not draw
   * anything.
   *
   * @param length is the distance to move.
   */
  public void move(double length, Color color) {
    calcHighLowCoord();

    oldX = Double.parseDouble(df.format(coordX));
    oldY = Double.parseDouble(df.format(coordY));
    coordX += Double.parseDouble(df.format(length * Math.cos(currAngle)));
    coordY += Double.parseDouble(df.format(length * Math.sin(currAngle)));

    if (screen == 2) {
      Line l = new Line(oldX, oldY, coordX, coordY, color);
      l.prodDashedLine();
    }

    calcHighLowCoord();
  }

  /**
   * Changes the current angle of the line by adding the radian version of the given angle.
   *
   * @param angle is the angle to rotate the coordinates.
   */
  public void rotate(double angle) {
    if (!stochAngle) {
      currAngle += Math.toRadians(angle);
    } else {
      if (angle >= 0) {
        currAngle += Math.toRadians(Math.random() * (maxAngle - minAngle + 1) + minAngle);
      } else {
        currAngle += Math.toRadians(-(Math.random() * (maxAngle - minAngle + 1) + minAngle));
      }
    }
  }

  /**
   * Class for taking a user-defined range of angles for the lines to be drawn at.
   *
   * @param stochAngle boolean to check if the user wants to use stochastic angles.
   * @param minAngle the minimum angle in the range.
   * @param maxAngle the maximum angle in the range.
   */
  public void stochAngleMethod(boolean stochAngle, double minAngle, double maxAngle) {
    this.stochAngle = stochAngle;
    this.minAngle = minAngle;
    this.maxAngle = maxAngle;
  }




  /**
   * Creates a Point object with the coordinates taken at the time the [ is used and then pushes
   * them into a stack.
   */
  public void pushCoords() {
    Point pushP = new Point();
    pushP.setLocation(coordX, coordY);
    pointStack.push(pushP);
    angleStack.push(currAngle);
  }

  /**
   * Pops the point from the stack and then sets the coordinates back to those that were pushed
   * into the stack.
   */
  public void popCoords() {
    Point popP = pointStack.pop();
    coordX =  popP.getX();
    coordY =  popP.getY();
    currAngle = angleStack.pop();
  }

  /**
   * Resets the turtle back to the original inputs.
   */
  public void reset() {
    this.word = initialWord;
    this.length = initialLength;
    this.angle = initialAngle;
    this.coordX = initialCoordX;
    this.coordY = initialCoordY;
    this.currAngle = 0;
    resetHighLow();
    resetBearing();
  }

  /**
   * Sets the currAngle to 0, ensuring that the starting position is the same each time the program
   * is run.
   */
  public void resetBearing() {
    this.currAngle = 0;
  }

  /**
   * Class to centre the turtle drawing in the frame.
   * It does this by adjusting the start of the turtle by the offset from the starting co-ordinate
   * to the midpoint and the offset from the midpoint of the drawing to the midpoint of the frame.
   */
  public void centre(double frameWidth, double frameHeight) {
    double middleX = (highestCoordX + lowestCoordX) / 2;
    double middleY = (highestCoordY + lowestCoordY) / 2;

    double frameMidX = frameWidth / 2;
    double frameMidY = frameHeight / 2;

    double offsetFromStartToMidX = startingCoordX - middleX;
    double offsetFromStartToMidY = startingCoordY - middleY;

    double offsetFromMidFrameToMidCoordX = frameMidX - middleX;
    double offsetFromMidFrameToMidCoordY = frameMidY - middleY;

    //Works out new X coordinate
    if (offsetFromStartToMidX > 0 && offsetFromMidFrameToMidCoordX > 0) {
      this.coordX = frameMidX + Math.abs(offsetFromMidFrameToMidCoordX);
    } else if (offsetFromStartToMidX > 0 && offsetFromMidFrameToMidCoordX < 0) {
      this.coordX = frameMidX - Math.abs(offsetFromMidFrameToMidCoordX);
    } else if (offsetFromStartToMidX < 0 && offsetFromMidFrameToMidCoordX > 0) {
      this.coordX = frameMidX + Math.abs(offsetFromMidFrameToMidCoordX);
    } else if (offsetFromStartToMidX < 0 && offsetFromMidFrameToMidCoordX < 0) {
      this.coordX = frameMidX - Math.abs(offsetFromMidFrameToMidCoordX);
    }

    //Works out new Y coordinate
    if (offsetFromStartToMidY > 0 && offsetFromMidFrameToMidCoordY > 0) {
      this.coordY = frameMidY + Math.abs(offsetFromMidFrameToMidCoordY);
    } else if (offsetFromStartToMidY > 0 && offsetFromMidFrameToMidCoordY < 0) {
      this.coordY = frameMidY - Math.abs(offsetFromMidFrameToMidCoordY);
    } else if (offsetFromStartToMidY < 0 && offsetFromMidFrameToMidCoordY > 0) {
      this.coordY = frameMidY + Math.abs(offsetFromMidFrameToMidCoordY);
    } else if (offsetFromStartToMidY < 0 && offsetFromMidFrameToMidCoordY < 0) {
      this.coordY = frameMidY - Math.abs(offsetFromMidFrameToMidCoordY);
    }
  }

  /**
   * Method to reset the highest and lowest X and Y coordinates back to the original numbers.
   */
  public void resetHighLow() {
    highestCoordX = 0;
    highestCoordY = 0;
    lowestCoordX = 1 * 10e10;
    lowestCoordY = 1 * 10e10;
  }

  /**
   * Creates a new Turtle with the current parameters and adds it to the stack.
   */
  public void pushTurtle() {
    String[] pushTurtle = {word, Double.toString(length), Double.toString(angle),
        Double.toString(coordX), Double.toString(coordY)};

    turtleStack.push(pushTurtle);
  }

  /**
   * Pops the top turtle off the stack and sets the main turtle to its parameters.
   */
  public void popTurtle() {
    try {
      String[] popTurtle = turtleStack.pop();
      word = popTurtle[0];
      length = Double.parseDouble(popTurtle[1]);
      angle = Double.parseDouble(popTurtle[2]);
      coordX = Double.parseDouble(popTurtle[3]);
      coordY = Double.parseDouble(popTurtle[4]);
    } catch (NoSuchElementException c) {
      System.out.println("No Element Found " + c);
    }
  }

  /**
   * Resets the turtle stack.
   */
  public void resetStack() {
    turtleStack.clear();
  }

  /**
   * Calculates the highest and lowest co-ordinates in the current drawing.
   */
  public void calcHighLowCoord() {
    if (coordX > highestCoordX) {
      highestCoordX = coordX;
    }
    if (coordX < lowestCoordX) {
      lowestCoordX = coordX;
    }
    if (coordY > highestCoordY) {
      highestCoordY = coordY;
    }
    if (coordY < lowestCoordY) {
      lowestCoordY = coordY;
    }
  }
}