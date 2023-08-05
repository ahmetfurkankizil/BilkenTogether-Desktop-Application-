package UserProfileGUI.src;

import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;

public class UserProfilePage extends JFrame {
    private JButton arrowIconButton;
    private JButton editProfileButton;
    private JButton lessonsButton;
    private JButton activitiesButton;
    private JButton studiesButton;
    private JLabel backgroundPhotoLabel;
    private JLabel backLabel;
    private JLabel profilePhotoLabel;
    private JLabel nameSurnameLabel;
    private JLabel eMailLabel;
    private JLabel researchInterestsLabel;
    private JLabel biographyLabel;
    private JLabel ratingStarsLabel;
    private JPanel mainPanel;
    private JPanel historyLessons;

    private User user;
    public UserProfilePage() {
        setName("User Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        add(mainPanel);
        setUser();

        setVisible(true);
    }

    public void setUser() {
        user = new Student("Ufuk Baran Güler", "ufukbaranguler@ug.bilkent.edu.tr", 22001734, "Male", "CS", "1234", "06/05/2001");
    }
    public static void main(String[] args) {
        JFrame frame = new UserProfilePage();
    }



}
