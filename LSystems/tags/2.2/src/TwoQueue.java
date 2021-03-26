import java.util.LinkedList;
import java.util.Queue;

/**
 * Class for a queue that only holds two values.
 *
 * @author Daniel Jones.
 */
public class TwoQueue {
  Queue<String> queue = new LinkedList<>();

  /**
   * Constructor for TwoQueue.
   */
  public TwoQueue() {
  }

  /**
   * Resets/initialises the queue with two n's. These are useless values and
   * are essentially place holders.
   *
   */
  public void resetQueue() {
    queue.add("n");
    queue.add("n");
  }

  /**
   * Removes the first element in the queue and reads the second (which has become the first)
   * and returns them.
   * Also adds the current element to the queue to replace the one that was removed.
   *
   * @param current is the string given. Corresponds to which button was pressed in the Buttons
   *               class.
   * @return returns a string of the first and second element concatenated.
   */
  public String lastTwo(String current) {
    queue.add(current);
    String first = queue.poll();
    String second = queue.element();

    return (first + second);
  }
}
