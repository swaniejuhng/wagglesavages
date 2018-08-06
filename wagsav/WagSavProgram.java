//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
//import java.swing.border;
import java.awt.Color;


class WagLoginFrame extends JFrame implements ActionListener{
  String introText = "<HTML><center>Hello, Waggle user!<br/>Enter your ID and password.</center></HTML>";
  String loginErrorMessage = "";
  JLabel welcomeLabel = new JLabel(introText);
  JLabel userLabel = new JLabel("USERNAME");
  JLabel passwordLabel = new JLabel("PASSWORD");
  JTextField userTextField = new JTextField();
  JPasswordField passwordField = new JPasswordField();
  JButton loginButton = new JButton("Go waggle!");
  final JLabel loginErrorLabel = new JLabel(loginErrorMessage);


  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
  int frameWidth = (int)(dim.getWidth() / 3);
  int frameHeight = (int)(dim.getHeight() / 3);

  public WagLoginFrame(){
    super("Waggle Savages");
    setBackground(Color.white);
    setLayoutManager();
    setLocationAndSize();
    addComponentsToContainer();
    setVisible(true);     // put this in the very last line
  }

  public void setLayoutManager(){
    //container.setLayout();
  }


  public void setLocationAndSize(){
    setLayout(null);
    setSize(frameWidth, frameHeight);
    setLocation(frameWidth, frameHeight);
    setResizable(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    welcomeLabel.setFont(new Font("Arial", Font.PLAIN, (int)(frameWidth / 29)));
    welcomeLabel.setBounds((int)(frameWidth * 0.25), (int)(frameHeight * 0.1), (int)(frameWidth * 0.6), (int)(frameHeight * 0.3));

    userLabel.setFont(new Font("Arial", Font.PLAIN, (int)(frameWidth / 36)));
    userTextField.setFont(new Font("Arial", Font.PLAIN, (int)(frameWidth / 36)));
    passwordLabel.setFont(new Font("Arial", Font.PLAIN, (int)(frameWidth / 36)));
    passwordField.setFont(new Font("Arial", Font.PLAIN, (int)(frameWidth / 36)));
    loginButton.setFont(new Font("Arial", Font.BOLD, (int)(frameWidth / 36)));

    userLabel.setBounds((int)(frameWidth * 0.07), (int)(frameHeight * 0.45), (int)(frameWidth * 0.2), (int)(frameHeight * 0.1));
    passwordLabel.setBounds((int)(frameWidth * 0.07), (int)(frameHeight * 0.6), (int)(frameWidth * 0.2), (int)(frameHeight * 0.1));
    userTextField.setBounds((int)(frameWidth * 0.25), (int)(frameHeight * 0.45), (int)(frameWidth * 0.4), (int)(frameHeight * 0.1));
    passwordField.setBounds((int)(frameWidth * 0.25), (int)(frameHeight * 0.6), (int)(frameWidth * 0.4), (int)(frameHeight * 0.1));
    loginButton.setBounds((int)(frameWidth * 0.7), (int)(frameHeight * 0.45), (int)(frameWidth * 0.2), (int)(frameHeight * 0.25));

    loginErrorLabel.setFont(new Font("Arial", Font.BOLD, (int)(frameWidth / 36)));
    loginErrorLabel.setForeground(Color.red);
    loginErrorLabel.setBounds((int)(frameWidth * 0.25), (int)(frameHeight * 0.7), (int)(frameWidth * 0.5), (int)(frameHeight * 0.175));

    loginButton.addActionListener(this);
  }

  public void addComponentsToContainer(){
    add(welcomeLabel);
    add(userLabel);
    add(passwordLabel);
    add(userTextField);
    add(passwordField);
    add(loginButton);
    add(loginErrorLabel);
  }

  public void actionPerformed(ActionEvent e){
    String username = userTextField.getText();
    String password = new String(passwordField.getPassword());

    if(username.compareTo("wagglesavages") == 0 && password.compareTo("abc1234") == 0){
      dispose();
      WagFrame1 wFrame = new WagFrame1("Waggle Savages");
    }

    else{
      loginErrorMessage = "<HTML><center>Username or password is wrong.<br/>Please try again.</center></HTML>";
      loginErrorLabel.setText(loginErrorMessage);
    }
  }
}

class WagFrame1 extends JFrame{
  public WagFrame1(String str){
    super(str);
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
    //WagFrameMain wFrameMain = new WagFrameMain();
    WagLoginFrame f = new WagLoginFrame();
  }
}
