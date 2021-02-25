import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Settings extends JPanel {
  Turtle turtle;
  Lindenmayer linSys;
  SavedShapes shapes;
  Painting painting = new Painting();
  Buttons buttons = new Buttons(painting);


  public Settings(Turtle turtle, Lindenmayer linSys, SavedShapes shapes, Buttons buttons) {
    this.linSys = linSys;
    this.turtle = turtle;
    this.shapes = shapes;
    this.buttons = buttons;

    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(Display.frameWidth, Display.frameHeight));
    setLayout(null);
    linClassButtons();
    presetBox();
    textFields();
    axiomBoxes();
    centreLineCheckBox();
  }

  public void linClassButtons() {
    JRadioButton deterministic = new JRadioButton("Deterministic");
    JRadioButton stochastic = new JRadioButton("Stochastic");
    deterministic.setSelected(true);
    deterministic.setBounds((Display.frameWidth / 2) - 150, 50, 150, 30);
    stochastic.setBounds((Display.frameWidth / 2), 50, 150, 30);

    ButtonGroup linClasses = new ButtonGroup();
    linClasses.add(deterministic);
    linClasses.add(stochastic);
    deterministic.addActionListener(e -> linSys.setCurrentClass(1));
    stochastic.addActionListener(e -> linSys.setCurrentClass(2));

    add(deterministic);
    add(stochastic);
  }

  public void presetBox() {
    JLabel presetLabel = new JLabel("Presets");
    String[] presetNames = {"squares", "sierpinski", "lakes", "scaryTree", "stochastic", "shuriken", "moreSquares",
        "rectangles", "sparse", "idk", "idk2", "kochIsland", "kochSnowflake", "ecksAndWhy", "nonStochXy",
        "nonStochXy2", "nonStochXy3", "hilbert", "turtletoynet", "handDrawn", "ecksAndWhyStochastic"};

    JComboBox<String> presets = new JComboBox<>(presetNames);
    presets.setBounds((Display.frameWidth / 2) - 50, 100, 150, 30);
    presetLabel.setBounds((Display.frameWidth / 2) - 100, 100, 150, 30);

    presets.addActionListener(e -> {
      if (e.getSource().equals(presets)) {
        shapes.setPresetNo(presets.getSelectedIndex());
        buttons.externalReset();
        Display.initialiseTurtleLinden();
        System.out.println(presets.getSelectedIndex());
      }
    });
    add(presets);
    add(presetLabel);
  }

  public void textFields() {
    JTextField lengthRatio = new JTextField("Input length scaler", 10);
    JButton enterButton = new JButton("Enter Ratio");
    lengthRatio.setBounds((Display.frameWidth / 2) - 150, 150, 150, 25);
    enterButton.setBounds((Display.frameWidth / 2), 150, 100, 25);

    lengthRatio.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        JTextField source = (JTextField) e.getComponent();
        source.setText("");
        source.removeFocusListener(this);
      }
    });

    enterButton.addActionListener(e -> {
      try {
        double newRatio = Double.parseDouble(lengthRatio.getText());
        linSys.setLengthRatio(newRatio);
        buttons.externalReset();
      } catch (NumberFormatException c) {
        System.out.println("Not a number");
      }
    });

    add(lengthRatio);
    add(enterButton);
  }


  public void axiomBoxes() {
    JTextField axiomString = new JTextField(turtle.getWord(), 15);
    JLabel axiomLabel = new JLabel("Starting Axiom");
    axiomString.setBounds((Display.frameWidth / 2), 200, 150, 25);
    axiomLabel.setBounds((Display.frameWidth / 2) - 100, 200, 150, 25);
    add(axiomString);
    add(axiomLabel);



  }

  public void centreLineCheckBox() {
    JCheckBox check = new JCheckBox();
    JButton updateCheck = new JButton("Update");
    check.setText("Show centre lines");
    check.setFocusable(false);
    check.setBounds(300, 300, 130, 40);
    updateCheck.setBounds(450, 300, 75, 40);

    //updateCheck.addActionListener(new ActionListener() {
    //  @Override
    //  public void actionPerformed(ActionEvent e) {
     //   if (e.getSource() == updateCheck) {
     //     if (check.isSelected()) {
    //        painting.drawCentre(true);
    //      } else {
    //        painting.drawCentre(false);
    //      }
    //    }
    //  }
    //});

    add(updateCheck);
    add(check);

  }
}

//Text boxes ect for axioms
//Combobox for presets