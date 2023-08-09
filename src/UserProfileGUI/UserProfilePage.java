package UserProfileGUI;

import HomePage.Main.Main;
import PostComponents.LessonPostViewer;
import PostComponents.StudiesPostViewer;
import Posts.LessonPost;
import Posts.StudyPost;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class UserProfilePage extends JPanel {

    private JButton editProfileButton;
    private JButton lessonsButton;
    private JButton activitiesButton;
    private JButton studiesButton;

    private JLabel nameSurnameLabel;
    private JLabel eMailLabel;
    private JLabel researchInterestsLabel;
    private JLabel ratingStarsLabel;
    private JPanel mainPanel;
    private JPanel profilePhotoPanel;
    private JPanel personalInfoPanel;
    private JPanel backgroundPhotoPanel;
    private JLabel bioLabel;
    private JLabel biographyLabel;
    private JLabel resIntLabel;
    private JPanel inPanel;
    private JPanel HistoryPanel;
    private JPanel LessonsHistoryPanel;
    private JPanel ActivitiesHistoryPanel;
    private JPanel StudiesHistoryPanel;


    private JButton button1;
    private JButton button2;
    private User user;
    private Main main;

    public UserProfilePage() {
        add(mainPanel);
        setName("Profile");
        setDefaultPhotos(); // Implementations are empty
        createActionListeners(); // Implementations are empty
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
        GridBagConstraints g2 = new GridBagConstraints();
        g2.gridx = 0;
        g2.ipadx = 300;
        g2.ipady = 300;
        LessonsHistoryPanel.add(new LessonPostViewer(new LessonPost(1, main.getCurrentUser(), "lol","l",1,true,"1"),main),g2);
        StudiesHistoryPanel.add(new StudiesPostViewer(new StudyPost(1, main.getCurrentUser(), "lol","l","aa",null ,"",null),main),g2);
        ActivitiesHistoryPanel.add(new LessonPostViewer(new LessonPost(1, main.getCurrentUser(), "lol","l",1,true,"1"),main),g2);
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
         * adds listener to lessonsButton
         * shows user's old lessons post
         */
        lessonsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LessonsHistoryPanel.setVisible(true);
                ActivitiesHistoryPanel.setVisible(false);
                StudiesHistoryPanel.setVisible(false);
            }
        });

        /*
         * adds listener to activitiesButton
         * shows user's old lessons post
         */
        activitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActivitiesHistoryPanel.setVisible(true);
                StudiesHistoryPanel.setVisible(false);
                LessonsHistoryPanel.setVisible(false);
            }
        });

        /*
         * adds listener
         * shows user's old studies post
         */
        studiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudiesHistoryPanel.setVisible(true);
                ActivitiesHistoryPanel.setVisible(false);
                LessonsHistoryPanel.setVisible(false);
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
