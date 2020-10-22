/** A class adapted by an example by Daniel Shiffman, created to help me understand the basic
 *  programming behind the most simple L-System.
 *
 * @author Daniel Jones, adapted from code by Daniel Shiffman.
 */

public class LinSystemFirstTest {
  String current = "A";

  int count = 0;

  /**
   * This class creates a string buffer and then creates each generation of the L-system by applying
   * the rules based on the letter being either A or B.
   * */
  void linSystem() {
    StringBuffer next = new StringBuffer();

    for (int i = 0; i < current.length(); i++) {
      char c = current.charAt(i);
      if (c == 'A') {
        next.append("ABA");
      } else if (c == 'B') {
        next.append("BBB");
      }
    }
    current = next.toString();
    count++;
    System.out.println("Generation " + count + ": " + current);
  }

  /**
   * Main class for running program, does a for loop to run the lSystem() method i amount of times,
   * this could be coded better.
   * but the example was simply to help me understand the logic in the lSystem() class
   *
   * @param args stores command line arguments.
   */
  public static void main(String[] args) {
    LinSystemFirstTest demo = new LinSystemFirstTest();
    for (int i = 0; i < 10; i++) {
      demo.linSystem();
    }
  }
}
