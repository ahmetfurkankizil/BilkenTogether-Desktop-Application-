package ActivitiesPost;

import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;

public class ActivitiesPage extends JFrame {
    private JPanel mainPanel;
    private JPanel secondMainPanel;
    private JPanel leftPanel;
    private JLabel logoLabel;
    private JPanel homeLabelPanel;
    private JLabel homeLabel;
    private JPanel messagesLabelPanel;
    private JLabel messagesLabel;
    private JPanel notificationsLabelPanel;
    private JLabel notificationsLabel;
    private JPanel profileLabelPanel;
    private JLabel profileLabel;
    private JPanel requestLabelPanel;
    private JLabel requestsLabel;
    private JPanel logOutLabelPanel;
    private JLabel logOutLabel;
    private JPanel rightPanel;
    private JButton profileBoxButton;
    private JButton filterBoxButton;
    private JPanel middlePanel;
    private JLabel bilkenTogetherLabel;
    private JPanel buttonPanel;
    private JButton lessonsButton;
    private JButton studiesButton;
    private JButton activitiesButton;
    private JScrollPane flowScrollPane;
    private JPanel insideScrollPanePanel;
    private JPanel postingPanel;
    private JTextArea textArea1;
    private JLabel activityType;
    private JComboBox activityTypeComboBox;
    private JButton postButton;
    private JLabel errorLabel;
    private JComboBox peopleCountComboBox;
    private JPanel profilePhotoPanel;
    User currentUser;
    private GridBagConstraints g;
    public ActivitiesPage() {

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 800);
        currentUser = new Student("Erdem", "erdem.p", 22203112, "l", "d", "p", "b");
        generalSetup();


        setVisible(true);
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void generalSetup() {
        g = new GridBagConstraints();
        textArea1.setMargin(new Insets(5, 5, 5, 5));
        textArea1.setLineWrap(true);
        textArea1.setColumns(50);
        PPImageHandler profilePhoto = new PPImageHandler();
        profilePhotoPanel.add(profilePhoto);

        for (int i = 1; i < 16; i++) {
            peopleCountComboBox.addItem(i);
        }
    }

    public static void main(String[] args) {
        ActivitiesPage page = new ActivitiesPage();
    }
}


