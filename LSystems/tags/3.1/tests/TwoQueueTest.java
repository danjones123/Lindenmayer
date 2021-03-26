import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class for testing the TwoQueue class.
 */
public class TwoQueueTest {
  TwoQueue tq = new TwoQueue();

  /**
   * Tests the values of the queue after the queue is reset/initialised.
   */
  @Test
  public void resetQueue() {
    tq.resetQueue();
    assertEquals("n", tq.queue.poll());
    assertEquals("n", tq.queue.poll());

  }

  /**
   * Tests that the last two of the queue are recorded in the correct order.
   */
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