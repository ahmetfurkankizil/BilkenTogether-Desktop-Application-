package UserProfileGUI;

import DatabaseRelated.DatabaseConnection;
import HomePage.Main.HomeMain;
import Icons.IconCreator;
import PostComponents.ActivitiesPostViewer;
import PostComponents.LessonPostViewer;
import PostComponents.StudiesPostViewer;
import Posts.ActivityPost;
import Posts.LessonPost;
import Posts.StudyPost;
import ProfileBox.ProfileBox;
import UserRelated.FacultyMember;
import UserRelated.Student;
import UserRelated.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UserProfilePage extends JPanel {

    private JButton editProfileButton;
    private JButton lessonsButton;
    private JButton activitiesButton;
    private JButton studiesButton;

    private JLabel nameSurnameLabel;
    private JLabel eMailLabel;
    private JLabel ratingStarsLabel;
    private JPanel mainPanel;
    private JPanel profilePhotoPanel;
    private JPanel historyLessons;
    private JPanel personalInfoPanel;
    private JLabel bioLabel;
    private JLabel biographyLabel;
    private JLabel resIntLabel;
    private JPanel inPanel;
    private JLabel backGroundPhotoLabel;
    private JLabel profilePhotoLabel;
    private JPanel HistoryPanel;
    private JPanel lessonsHistoryPanel;
    private JPanel activitiesHistoryPanel;
    private JPanel studiesHistoryPanel;


    private JPanel lolPane;
    private User user;
    private HomeMain main;
    private ProfileBox profileBox;

    public UserProfilePage() {
        add(mainPanel);
        setName("Profile");
        // Implementations are empty

    }

    public UserProfilePage(HomeMain main, ProfileBox profileBox) {
        this();
        this.main = main;
        this.user = main.getCurrentUser();
        setUpHistory();
        this.profileBox = profileBox;
        createActionListeners();
        setDefaultPhotos();
        setPersonalInformation();
        if (user instanceof FacultyMember){
            lessonsButton.setVisible(false);
            activitiesButton.setVisible(false);
        }
        setUpHistory();
    }

    private void setUpHistory() {
        if (user instanceof Student){
            setUpLessonsHistory();
            setUpActivitiesHistory();
        }
        //setUpStuiesHistory();
    }

    private void setUpLessonsHistory() {
        ArrayList<LessonPost> lessonPosts= user.pullFromLessonsPostTable();
        lessonsHistoryPanel.setLayout(new GridLayout(0,1));
        for (int i = 0; i < lessonPosts.size(); i++) {
            lessonsHistoryPanel.add(new LessonPostViewer(lessonPosts.get(i),main));
        }
    }
    private void setUpActivitiesHistory() {

        DatabaseConnection c = new DatabaseConnection();
        ArrayList<ActivityPost> activityPosts= user.pullFromActivitiesPostTable();
        activitiesHistoryPanel.setLayout(new GridLayout(0,1));
        for (int i = 0; i < activityPosts.size(); i++) {
            activitiesHistoryPanel.add(new ActivitiesPostViewer(activityPosts.get(i),main));
        }
    }
    private void setUpStuiesHistory() {
        DatabaseConnection c = new DatabaseConnection();
        ArrayList<StudyPost> activityPosts= user.pullFromStudyPostTable();
        studiesHistoryPanel.setLayout(new GridLayout(0,1));
        for (int i = 0; i < activityPosts.size(); i++) {
            studiesHistoryPanel.add(new StudiesPostViewer(activityPosts.get(i),main));
        }
    }
    public void refresh(){
        lessonsHistoryPanel.removeAll();
        activitiesHistoryPanel.removeAll();
        studiesHistoryPanel.removeAll();
        setUpHistory();
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
    private void setDefaultPhotos(){
        if (user.getProfilePhoto() != null) {
            InputStream is = new ByteArrayInputStream(user.getProfilePhoto());
            BufferedImage defaultProfilePhotoImg = null;
            try {
                defaultProfilePhotoImg = ImageIO.read(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ImageIcon icon = IconCreator.getIconWithSize(new ImageIcon(defaultProfilePhotoImg),40,40);
            profilePhotoLabel.setIcon(icon);

            InputStream si = new ByteArrayInputStream(user.getBackgroundPhoto());
            BufferedImage defaultBackGroundPhoto = null;
            try {
                defaultBackGroundPhoto = ImageIO.read(si);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ImageIcon icon2 = new ImageIcon(defaultBackGroundPhoto);
            backGroundPhotoLabel.setIcon(IconCreator.getIconWithSize(icon2, 800, 200));
        }
    }
    public static ImageIcon byteToImageIcon(byte[] image){
        InputStream is = new ByteArrayInputStream(image);
        BufferedImage handledImage = null;
        try {
            handledImage = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ImageIcon(handledImage);

    }

    private void openEditProfilePage() {
        JFrame frame = new UserEditProfilePage(this, profileBox,main );
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
                lessonsHistoryPanel.setVisible(true);
                activitiesHistoryPanel.setVisible(false);
                studiesHistoryPanel.setVisible(false);
            }
        });

        /*
         * adds listener to activitiesButton
         * shows user's old lessons post
         */
        activitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activitiesHistoryPanel.setVisible(true);
                studiesHistoryPanel.setVisible(false);
                lessonsHistoryPanel.setVisible(false);
            }
        });

        /*
         * adds listener
         * shows user's old studies post
         */
        studiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studiesHistoryPanel.setVisible(true);
                activitiesHistoryPanel.setVisible(false);
                lessonsHistoryPanel.setVisible(false);
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

    public JLabel getEMailLabel() {
        return eMailLabel;
    }

    public JLabel getBioLabel() {
        return bioLabel;
    }



    public JLabel getProfilePhotoLabel() {
        return profilePhotoLabel;
    }

    public JLabel getBackGroundPhotoLabel() {
        return backGroundPhotoLabel;
    }
}
