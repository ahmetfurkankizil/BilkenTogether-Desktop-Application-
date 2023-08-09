package UserProfileGUI;

import HomePage.Main.Main;
import Icons.IconCreator;
import ProfileBox.ProfileBox;
import UserRelated.Student;
import UserRelated.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UserProfilePage extends JPanel {

    private JButton editProfileButton;
    private JButton lessonsButton;
    private JButton activitiesButton;
    private JButton studiesButton;

    private JLabel nameSurnameLabel;
    private JLabel eMailLabel;
    private JLabel ratingStarsLabel;
    private JPanel mainPanel;
    private JPanel historyLessons;
    private JPanel personalInfoPanel;
    private JLabel bioLabel;
    private JLabel biographyLabel;
    private JLabel resIntLabel;
    private JPanel inPanel;
    private JLabel backGroundPhotoLabel;
    private JLabel profilePhotoLabel;
    private User user;
    private Main main;
    private ProfileBox profileBox;

    public UserProfilePage() {
        add(mainPanel);
        setName("Profile");
        // Implementations are empty

    }

    public void setMain(Main main) {
        this.main = main;
    }

    public UserProfilePage(User user, ProfileBox profileBox) {
        this();
        this.user = user;
        this.profileBox = profileBox;
        setDefaultPhotos();
        setPersonalInformation();
        createActionListeners(); // Implementations are empty

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

        InputStream is = new ByteArrayInputStream(user.getProfilePhoto());
        BufferedImage defaultProfilePhotoImg = null;
        try {
            defaultProfilePhotoImg = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icon  = new ImageIcon(defaultProfilePhotoImg);
        profilePhotoLabel.setIcon(icon);

        InputStream si = new ByteArrayInputStream(user.getBackGroundPhoto());
        BufferedImage defaultBackGroundPhoto = null;
        try {
            defaultBackGroundPhoto = ImageIO.read(si);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icon2 = new ImageIcon(defaultBackGroundPhoto);
        backGroundPhotoLabel.setIcon(IconCreator.getIconWithSize(icon2, 800, 200));
    }


    private void openEditProfilePage() {
        JFrame frame = new UserEditProfilePage(this, profileBox );

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
