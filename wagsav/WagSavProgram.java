import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

public class WagSavProgram{
  public static void main(String[] args) {
    WagLoginFrame f = new WagLoginFrame();
  }
}

class WagLoginFrame extends JFrame implements ActionListener {
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

  public WagLoginFrame() {
    super("Waggle Savages");
    getContentPane().setBackground(new Color(217, 222, 235));
    setLocationAndSize();
    addComponentsToContainer();
    setVisible(true);     // put this in the very last line
  }

  public void setLocationAndSize() {
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

    loginButton.setBackground(new Color(198, 211, 234));
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

  public void addComponentsToContainer() {
    add(welcomeLabel);
    add(userLabel);
    add(passwordLabel);
    add(userTextField);
    add(passwordField);.
    add(loginButton);
    add(loginErrorLabel);
  }

  public void actionPerformed(ActionEvent e) {
    String username = userTextField.getText();
    String password = new String(passwordField.getPassword());

    if(username.compareTo("wagglesavages") == 0 && password.compareTo("abc1234") == 0) {
      dispose();
      //WagFrame1 wFrame = new WagFrame1("Waggle Savages");
      SwingUtilities.invokeLater(WaggleList3::new);
    }

    else{
      loginErrorMessage = "<HTML><center>Username or password is wrong.<br/>Please try again.</center></HTML>";
      loginErrorLabel.setText(loginErrorMessage);
    }
  }

/*
  public void keyTyped(KeyEvent e) {

    String username = userTextField.getText();
    String password = new String(passwordField.getPassword());

    if(username.compareTo("wagglesavages") == 0 && password.compareTo("abc1234") == 0) {
      dispose();
      WagFrame1 wFrame = new WagFrame1("Waggle Savages");
    }

    else{
      loginErrorMessage = "<HTML><center>Username or password is wrong.<br/>Please try again.</center></HTML>";
      loginErrorLabel.setText(loginErrorMessage);

    }
  }

  public void keyPressed(KeyEvent e) {}

  public void keyReleased(KeyEvent e) {}
    */
}
