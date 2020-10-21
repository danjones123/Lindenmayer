//This is an adaptation of the code by Daniel Shiffman to help me understand how to program the fundamentals of LSystems.
//It is adapted from the code at 

public class LSystemFirstTest {

    String current = "A";

    int count = 0;

    void lSystem() {
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

    public static void main(String[] args) {
       LSystemFirstTest demo = new LSystemFirstTest();
       for (int i = 0; i < 10; i++) {
           demo.lSystem();
       }


    }
}
