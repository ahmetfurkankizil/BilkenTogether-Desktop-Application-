package SignupAndLogin;

import DatabaseRelated.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpFrame extends JFrame{
    private SignUpHandler signUpHandler;
    private DatabaseHandler databaseHandler;
    private JPanel mainPanel;
    private JPanel signupPanel;
    private JTextField emailTextField;
    private JTextField nameTextField;
    private JTextField idTextField;
    private JComboBox genderComboBox;
    private JComboBox roleComboBox;
    private JComboBox departmentComboBox;
    private JPasswordField passwordField1;
    private JComboBox dayComboBox;
    private JComboBox monthComboBox;
    private JComboBox yearComboBox;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel nameMessageLabel;
    private JLabel emailMessageLabel;
    private JButton backButton;
    private JLabel idLabel;
    private JLabel idMessageLabel;
    private JLabel genderLabel;
    private JLabel genderMessageLabel;
    private JLabel roleLabel;
    private JLabel roleMessageLabel;
    private JLabel departmentLabel;
    private JLabel departmentMessageLabel;
    private JLabel passwordLabel;
    private JLabel dateOfBirthLabel;
    private JLabel passwordMessageLabel;
    private JLabel dateOfBirthMessageLabel;
    private JLabel signUpLabel;
    private JButton signupButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    public SignUpFrame(SignUpHandler signUpHandler){
        this.signUpHandler = signUpHandler;
        add(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signUpHandler.checkName(nameTextField, nameMessageLabel) && signUpHandler.checkUserEmail(emailTextField, emailMessageLabel) && signUpHandler.checkUserID(idTextField, idMessageLabel) && signUpHandler.checkGender(genderComboBox, genderMessageLabel) && signUpHandler.checkRole(roleComboBox, roleMessageLabel) && signUpHandler.checkDepartment(departmentComboBox, departmentMessageLabel) && signUpHandler.checkPassword(passwordField1, passwordMessageLabel) && signUpHandler.checkDateOfBirth(dayComboBox, monthComboBox, yearComboBox, dateOfBirthMessageLabel)) {

                    String dateString = "" + dayComboBox.getSelectedItem() + "/";
                    if (monthComboBox.getSelectedItem().equals("January")) {
                        dateString += "01/";
                    } else if (monthComboBox.getSelectedItem().equals("February")) {
                        dateString += "02/";
                    } else if (monthComboBox.getSelectedItem().equals("March")) {
                        dateString += "03/";
                    } else if (monthComboBox.getSelectedItem().equals("April")) {
                        dateString += "04/";
                    } else if (monthComboBox.getSelectedItem().equals("May")) {
                        dateString += "05/";
                    } else if (monthComboBox.getSelectedItem().equals("June")) {
                        dateString += "06/";
                    } else if (monthComboBox.getSelectedItem().equals("July")) {
                        dateString += "07/";
                    } else if (monthComboBox.getSelectedItem().equals("August")) {
                        dateString += "08/";
                    } else if (monthComboBox.getSelectedItem().equals("September")) {
                        dateString += "09/";
                    } else if (monthComboBox.getSelectedItem().equals("October")) {
                        dateString += "10/";
                    } else if (monthComboBox.getSelectedItem().equals("November")) {
                        dateString += "11/";
                    } else {
                        dateString += "12/";
                    }
                    dateString += yearComboBox.getSelectedItem();

                    User temp;
                    if (roleComboBox.getSelectedItem().equals("Student")) {
                        temp = signUpHandler.createStudent(nameTextField.getText(), emailTextField.getText(), Integer.valueOf(idTextField.getText()), String.valueOf(genderComboBox.getSelectedItem()), String.valueOf(departmentComboBox.getSelectedItem()), String.valueOf(passwordField1.getText()), dateString);
                    } else {
                        temp = signUpHandler.createFacultyMember(nameTextField.getText(), emailTextField.getText(), Integer.valueOf(idTextField.getText()), String.valueOf(genderComboBox.getSelectedItem()), String.valueOf(departmentComboBox.getSelectedItem()), String.valueOf(passwordField1.getText()), dateString);
                    }

                    VerificationFrame verificationFrame = new VerificationFrame(signUpHandler, temp);
                    setVisible(false);
                    verificationFrame.setSize(1500, 1000);
                    verificationFrame.setVisible(true);
                    signUpHandler.sendVerificationEmail(emailTextField.getText());
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginFrame loginFrame = new LoginFrame(signUpHandler);
                loginFrame.setSize(1500,1000);
                loginFrame.setTitle("BilkenTogether");
                loginFrame.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
