import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JPanel {
  Turtle turtle;
  Lindenmayer linSys;
  SavedShapes shapes;
  JRadioButton deterministic;
  JRadioButton stochastic;
  JComboBox<String> presets;


  public Settings(Turtle turtle, Lindenmayer linSys, SavedShapes shapes) {
    this.linSys = linSys;
    this.turtle = turtle;
    this.shapes = shapes;
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(Display.frameWidth, Display.frameHeight));
    setLayout(new FlowLayout());
    linClassButtons();
    presetBox();

  }

  public void linClassButtons() {

    deterministic = new JRadioButton("Deterministic");
    stochastic = new JRadioButton("Stochastic");
    add(deterministic);
    add(stochastic);
    ButtonGroup linClasses = new ButtonGroup();

    linClasses.add(deterministic);
    linClasses.add(stochastic);
    deterministic.addActionListener(e -> linSys.setCurrentClass(1));
    stochastic.addActionListener(e -> linSys.setCurrentClass(2));
  }

  public void presetBox() {
    String[] presetNames = {"squares", "sierpinski", "lakes", "scaryTree", "stochastic"};
    presets = new JComboBox<>(presetNames);
    presets.addActionListener(e -> {
      if (e.getSource().equals(presets)) {
        shapes.setPresetNo(presets.getSelectedIndex());
        Display.initialiseTurtleLinden();
        System.out.println(presets.getSelectedIndex());
      }
    });
    add(presets);

  }
}

//Text boxes ect for axioms
//Combobox for presets