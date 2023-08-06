package UserEditProfilePageGUI;

import UserProfileGUI.src.UserProfilePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserEditProfilePage extends JFrame {
    private JButton backGPhotoChangeButton;
    private JTextField nameSurnameTextField;
    private JTextField bilkentEduTextField;
    private JTextField a22001734TextField;
    private JComboBox gendeCcomboBox;
    private JButton saveChangesButton;
    private JPanel mainPanel;
    private JButton exitButton;
    private JComboBox dayComboBox;
    private JComboBox monthComboBox;
    private JComboBox yearComboBox;
    private JButton profilePictureChangeButton;
    private JTextPane bioTextPane;
    private JPanel datePanel;
    private JComboBox DepartmentComboBox;
    private JLabel emailErrorLabel;
    private JLabel ıdErrorLabel;
    private JLabel nameErrorLabel;

    private UserProfilePage userProfilePage;

    public UserEditProfilePage(UserProfilePage userProfilePage) {
        this.userProfilePage = userProfilePage;
        setTitle("Edit User Profile");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        createListeners();

    }

    private void createListeners() {
        backGPhotoChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(new JPanel());
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        profilePictureChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userProfilePage.getNameSurnameLabel().setText(nameSurnameTextField.getText()); //Change also database
                userProfilePage.geteMailLabel().setText(bilkentEduTextField.getText()); //Change also database
                userProfilePage.getBioLabel().setText(bioTextPane.getText()); //Change also database
                dispose();
            }
        });
    }


}
