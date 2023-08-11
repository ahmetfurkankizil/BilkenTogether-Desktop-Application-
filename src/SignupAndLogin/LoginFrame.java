package SignupAndLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import HomePage.Main.HomeMain;
import UserRelated.*;

public class LoginFrame extends JFrame{
    User userWhichLogs;
    private SignUpHandler signUpHandler;
    private JPanel panel1;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JPanel centerPanel;
    private JButton signupButton;
    private JButton loginButton;
    private JButton forgotPasswordButton;
    private JLabel loginHeading;
    private JLabel emailLabel;
    private JLabel emailErrorLabel;
    private JLabel passwordLabel;
    private JLabel passwordErrorMessage;
    private JLabel loginErrorMesage;

    public LoginFrame(SignUpHandler signUpHandler){
        this.signUpHandler = signUpHandler;
        add(panel1);
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                SignUpFrame signUpFrame = new SignUpFrame(signUpHandler);
                signUpFrame.setSize(800,500);
                signUpFrame.setVisible(true);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String temp = "";
                String password = temp.valueOf(passwordField.getPassword());
                if (signUpHandler.checkWhetherEmailBlank(emailTextField, emailErrorLabel) && signUpHandler.checkWhetherPasswordBlank(passwordField, passwordErrorMessage))
                {
                    if (signUpHandler.checkUserRegisteredFromDB(emailTextField.getText(), password))
                    {
                        userWhichLogs = signUpHandler.getUserRegisteredFromDB(emailTextField.getText(), password);
                        loginErrorMesage.setText("Successful login");
                        loginErrorMesage.setForeground(Color.green);
                        System.out.println("Successful login");
                        //Home page pops up
                        HomeMain main = new HomeMain(userWhichLogs);
                        dispose();
                    }
                    else
                    {
                        loginErrorMesage.setForeground(Color.red);
                        loginErrorMesage.setText("Credentials are invalid!");
                    }
                }

            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
