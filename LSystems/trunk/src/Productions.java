import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class for the Productions generate Button.
 *
 * @author Daniel Jones.
 */

public class Productions extends JPanel {
  private final ProductionsController prodCont;


  /**
   * Constructor for Productions which takes a productionController and adds a generate Button.
   *
   * @param prodCont is the Production Controller.
   */
  public Productions(ProductionsController prodCont) {
    this.prodCont = prodCont;
    generateButton();
  }

  /**
   * A JButton which calls ProductionController to update the drawing.
   */
  public void generateButton() {

    JButton nextProd = new JButton("Show next production");

    nextProd.addActionListener(e -> prodCont.showProductions());
    add(nextProd);
  }
}
