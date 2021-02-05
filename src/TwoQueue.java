import java.util.LinkedList;
import java.util.Queue;


public class TwoQueue {
  Queue<String> queue = new LinkedList<>();

  public TwoQueue() {
  }

  public Queue<String> resetQueue() {
    queue.add("n");
    queue.add("n");
    return queue;
  }

  public String lastTwo(String current) {
    queue.add(current);
    String first = queue.poll();
    String second = queue.element();

    return (first + second);
  }
}
