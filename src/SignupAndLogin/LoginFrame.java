package SignupAndLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import HomePages.HomeMain.HomeMain;
import TrialMain.TrialMain;
import UserRelated.*;

public class LoginFrame extends JFrame{
    User userWhichLogs;
    public static boolean isTrial = false;
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
    private JButton tryAppButton;

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
        tryAppButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isTrial = true;
                Student customUser = new Student("Custom User","custom.user@bilkent.edu.tr",111112,"Other","Department","111111",new Date().toString(),null,null,true);
                Student customUser2 = new Student("Custom User2","custom.user@bilkent.edu.tr",111113,"Other","Department","111111",new Date().toString(),null,null,true);
                Student customUser3 = new Student("Custom User3","custom.user@bilkent.edu.tr",111114,"Other","Department","111111",new Date().toString(),null,null,true);
                Student customUser4 = new Student("Custom User4","custom.user@bilkent.edu.tr",111115,"Other","Department","111111",new Date().toString(),null,null,true);
                Student[] students = new Student[4];
                students[0] = customUser;
                students[1] = customUser2;
                students[2] = customUser3;
                students[3] = customUser4;
                setVisible(false);
                dispose();
                TrialMain trialMain = new TrialMain(students);

            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
