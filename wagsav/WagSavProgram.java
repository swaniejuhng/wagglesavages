//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
//import java.swing.border;
import java.awt.Color;

class WagFrameMain extends JFrame implements ActionListener{
  JPanel cards = new JPanel(new CardLayout());
  private 

  public WagFrameMain(){
    super("Waggle Savages");

    setSize(600, 600);
    setResizable(true);

    JPanel mainPanel = new JPanel(new GridLayout(2, 1));

    String introText = "<HTML><center>Hello, Waggle user!<br/>Select Waggle ID to access information.</center></HTML>";
    JLabel introTextLabel = new JLabel(introText);
    introTextLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    introTextLabel.setHorizontalAlignment(JLabel.CENTER);
    introTextLabel.setVerticalAlignment(JLabel.CENTER);
    mainPanel.add(introTextLabel);




/*
    JButton startButton = new JButton("Start!");
    startButton.setFont(new Font("Arial", Font.PLAIN, 20));
    startButton.addActionListener(this);
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(startButton);
    mainPanel.add(buttonPanel);
*/
    add(mainPanel);



    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e){
    setVisible(false);
    WagFrame1 wFrame1 = new WagFrame1("Waggle Savages");
  }
}

class WagFrame1 extends JFrame{
  public WagFrame1(String str){
    super(str);
    WindowDestroyer wDestroyer = new WindowDestroyer();
    addWindowListener(wDestroyer);
    setSize(600, 1200);
    setBackground(Color.white);
    setLayout(new GridLayout(10, 1));
    setVisible(true);

    WagButtonListener wButtonListener = new WagButtonListener();
    Button b[] = new Button[5];
    for(int i = 0; i < 5; i++){
      b[i] = new Button("waggle" + (i + 1));
      b[i].addActionListener(wButtonListener);
      add(b[i]);
    }
  }
}

class WagButtonListener implements ActionListener{
  private String waggleName;

  public void actionPerformed(ActionEvent e){
    waggleName = e.getActionCommand();
    WagFrame2 wFrame2 = new WagFrame2(waggleName);
  }
}



class WagFrame2 extends JFrame{
  public WagFrame2(String waggleName){
    super("Waggle Savages - " + waggleName);
    WindowDestroyer wDestroyer = new WindowDestroyer();
    addWindowListener(wDestroyer);
    setSize(600, 1200);
    setVisible(true);
  }
}

public class WagSavProgram{
  public static void main(String[] args){
    WagFrameMain wFrameMain = new WagFrameMain();
  }
}
