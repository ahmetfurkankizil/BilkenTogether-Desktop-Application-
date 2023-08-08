package UserProfileGUI.src;

import HomePage.StudiesPage.Main;
import UserEditProfilePageGUI.UserEditProfilePage;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfilePage extends JFrame {
    private JButton arrowIconButton;
    private JButton editProfileButton;
    private JButton lessonsButton;
    private JButton activitiesButton;
    private JButton studiesButton;

    private JLabel backLabel;
    private JLabel nameSurnameLabel;
    private JLabel eMailLabel;
    private JLabel researchInterestsLabel;
    private JLabel ratingStarsLabel;
    private JPanel mainPanel;
    private JPanel historyLessons;
    private JPanel profilePhotoPanel;
    private JPanel backButtonPanel;
    private JPanel personalInfoPanel;
    private JPanel backgroundPhotoPanel;
    private JLabel bioLabel;
    private JLabel biographyLabel;
    private JLabel resIntLabel;
    private JPanel inPanel;
    private User user;
    private Main main;

    public UserProfilePage() {
        add(mainPanel);
        setName("Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(900, 600);

        setDefaultPhotos(); // Implementations are empty
        createActionListeners(); // Implementations are empty

        //setVisible(true);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public UserProfilePage(User user) {
        this();
        this.user = user;
        user.setBiography("asdasdasdasd");
        user.addResearchInterest("cs");
        setPersonalInformation();

    }


    public static void main(String[] args) {
        JFrame frame = new UserProfilePage(new User("Ufuk Baran Güler",
                "baran.guler@gmail.com", 22001734, "Male", "CS", "1234", "06/05/2001") {
        });
    }

    /*
     * This method takes user's information and sets
     * related label
     */
    private void setPersonalInformation() {
        nameSurnameLabel.setText(user.getName());
        eMailLabel.setText(user.getEmail());
        resIntLabel.setText(user.getResearchInterests().toString());
        bioLabel.setText(user.getBiography());
    }

    /**
     * Sets rating setRatingStarLabel according to users average rating
     */
    private void setRatingStarsLabel() {

        if(user instanceof Student) {
            Student s = (Student) user;
            double average = s.calculateAverageRating();
            //
        } else {
            // For example faculty member set label different
        }

    }

    /**
     * Sets profile photo and background photo
     * If user don't have any just use default ones
     */
    private void setDefaultPhotos() {
        // Takes default profile photos and background photos and set
    }

    private void openEditProfilePage() {
        JFrame frame = new UserEditProfilePage(this);

        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    public JPanel getInPanel() {
        return inPanel;
    }

    /**
     * This method creates action listeners for sufficient buttons
     */
    private void createActionListeners() {

        /*
         * adds listener to editProfileButton
         * When user click this button listener opens edit profile page
         */
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditProfilePage();
            }
        });

        /*
         * adds listener to arrowIconButton
         * Navigate user back to home page when user click this button
         */
        arrowIconButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementation is empty
            }
        });

        /*
         * adds listener to lessonsButton
         * shows user's old lessons post
         */
        lessonsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementation is empty
            }
        });

        /*
         * adds listener to activitiesButton
         * shows user's old lessons post
         */
        activitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementation is empty
            }
        });

        /*
         * adds listener
         * shows user's old studies post
         */
        studiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // To do
            }
        });
    }

    // Getters
    public User getUser() {
        return user;
    }

    public JLabel getNameSurnameLabel() {
        return nameSurnameLabel;
    }

    public JLabel geteMailLabel() {
        return eMailLabel;
    }

    public JLabel getBioLabel() {
        return bioLabel;
    }
}
