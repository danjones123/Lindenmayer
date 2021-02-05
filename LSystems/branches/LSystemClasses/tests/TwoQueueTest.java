import org.junit.Test;

import static org.junit.Assert.*;

public class TwoQueueTest {
  TwoQueue tq = new TwoQueue();

  @Test
  public void resetQueue() {
    tq.resetQueue();
    assertEquals("n", tq.queue.poll());
    assertEquals("n", tq.queue.poll());

  }

  @Test
  public void lastTwo() {
    tq.resetQueue();

    assertEquals("nn", tq.lastTwo("g"));
    assertEquals("ng", tq.lastTwo("g"));
    assertEquals("gg", tq.lastTwo("u"));
    assertEquals("gu", tq.lastTwo("u"));
    assertEquals("uu", tq.lastTwo("u"));
  }
}