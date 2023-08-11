package SignupAndLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UserRelated.*;

public class VerificationFrame extends JFrame{
    private JPanel panel1;
    private User tempUser;
    private SignUpHandler signUpHandler;
    public JButton verifyButton;
    private JLabel verificationHeading;
    private JLabel messageText;
    public JLabel errorMessage;
    private JLabel enterLabel;
    public JTextField codeTextField;

    private boolean verifyButtonPressed;

    public VerificationFrame(SignUpHandler signUpHandler, User userToBeChecked){
        this.tempUser = userToBeChecked;
        this.signUpHandler = signUpHandler;
        this.verifyButtonPressed = false;
        setTitle("Verification");
        panel1.setPreferredSize(new Dimension(300,300));
        add(panel1);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signUpHandler.checkVerificationCodeTextField(codeTextField, errorMessage)) {
                    System.out.println("Successful Verification");
                    setVisible(false);
                    LoginFrame loginFrame = new LoginFrame(signUpHandler);
                    loginFrame.setSize(1500,1000);
                    loginFrame.setVisible(true);
                    if (tempUser != null) {
                        signUpHandler.addToUltimateTable(tempUser);
                        if (tempUser instanceof Student) {
                            Student tempStudent = (Student) tempUser;
                            tempStudent.createStudiesTable();
                            tempStudent.createNotificationsTable();
                            tempStudent.createActivitiesTable();
                            tempStudent.createLessonsTable();
                            tempStudent.setDefaultPhotos();
                        }
                        else {
                            FacultyMember member = (FacultyMember) tempUser;
                            member.createStudiesTable();
                            member.createNotificationsTable();
                            member.setDefaultPhotos();
                        }

                    }
                }
            }
        });
    }
}
