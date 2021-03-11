import javax.swing.*;

public class Productions extends JPanel {
  private ProductionsController prodCont;


  public Productions(ProductionsController prodCont) {
    this.prodCont = prodCont;
    generateButton();
  }

  /**
   * A JButton which calls smallUpdate to update the drawing a poll the queue for the next
   * production rule.
   */
  public void generateButton() {
    JButton nextProd = new JButton("Show next production");

    nextProd.addActionListener(e -> prodCont.showProductions());
    add(nextProd);
  }
}
