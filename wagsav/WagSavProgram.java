import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//import java.swing.border;
import java.awt.Color;

class WindowDestroyer extends WindowAdapter{
  public void windowClosing(WindowEvent e){
    System.exit(0);
  }
}

class WagFrameMain extends JFrame{
  public WagFrameMain(){
    setTitle("Waggle Savages");

    setVisible(true);
  }
}

class WagFrame1 extends JFrame{
  public WagFrame1(){
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
    WindowDestroyer wDestroyer = new WindowDestroyer();
    addWindowListener(wDestroyer);
    setTitle(waggleName);
    setSize(600, 1200);
    setVisible(true);
  }
}

public class WagSavProgram{
  public static void main(String[] args){
    WagFrame1 wFrameMain = new WagFrame1();
  }
}
