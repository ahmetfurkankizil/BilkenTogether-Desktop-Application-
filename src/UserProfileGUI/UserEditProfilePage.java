package UserProfileGUI;

import HomePages.HomeMain.HomeMain;
import HomePages.HomeMain.MainInterface;
import Other.Icons.IconCreator;
import ProfileBox.ProfileBox;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class UserEditProfilePage extends JFrame {
    private JButton backGPhotoChangeButton;

    private JButton saveChangesButton;
    private JPanel mainPanel;
    private JComboBox dayComboBox;
    private JComboBox monthComboBox;
    private MainInterface main;
    private JComboBox yearComboBox;
    private JButton profilePictureChangeButton;
    private JTextPane bioTextPane;
    private JComboBox departmentComboBox;
    private JLabel researchInterestsLabel;
    private JComboBox genderComboBox;

    private UserProfilePage userProfilePage;
    private ProfileBox profileBox;

    public UserEditProfilePage(UserProfilePage userProfilePage, ProfileBox profileBox, MainInterface main) {
        this.main = main;
        this.userProfilePage = userProfilePage;
        this.profileBox = profileBox;
        setTitle("Edit User Profile");
        setSize(900,600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);

        createListeners();

    }

    private void createListeners() {
        backGPhotoChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.addChoosableFileFilter(new FileNameExtensionFilter("File.png",  "png"));
                fc.addChoosableFileFilter(new FileNameExtensionFilter("File.jpg",  "jpg"));
                int choice = fc.showOpenDialog(new JPanel());
                if (choice == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    BufferedImage img = null;
                    try {
                        img = ImageIO.read( new File(selectedFile.getAbsolutePath()));
                        BufferedImage resized = new BufferedImage(40, 40, img.getType());
                        Graphics2D g = resized.createGraphics();
                        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        g.drawImage(img, 0, 0, 40, 40, 0, 0, img.getWidth(),
                                img.getHeight(), null);
                        g.dispose();
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    try {
                        ImageIO.write(img,"png",os);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                    byte[] bytes = os.toByteArray();
                    userProfilePage.getUser().setBackgroundPhoto(bytes,true);

                    InputStream is = new ByteArrayInputStream(userProfilePage.getUser().getBackgroundPhoto());
                    BufferedImage image;
                    try {
                        image = ImageIO.read(is);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                    ImageIcon icon = new ImageIcon(image);
                    userProfilePage.getBackGroundPhotoLabel().setIcon(IconCreator.getIconWithSize(icon,800,300));
                }

            }
        });

        profilePictureChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.addChoosableFileFilter(new FileNameExtensionFilter("File.png",  "png"));
                fc.addChoosableFileFilter(new FileNameExtensionFilter("File.jpg",  "jpg"));
                int choice = fc.showOpenDialog(new JPanel());
                if (choice == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    BufferedImage img = null;
                    try {
                        img = ImageIO.read( new File(selectedFile.getAbsolutePath()));
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    try {
                        ImageIO.write(img,"png",os);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                    byte[] bytes = os.toByteArray();
                    userProfilePage.getUser().setProfilePhoto(bytes,true);


                    InputStream is = new ByteArrayInputStream(userProfilePage.getUser().getProfilePhoto());
                    BufferedImage image;
                    try {
                        image = ImageIO.read(is);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                    ImageIcon icon = new ImageIcon(image);
                    userProfilePage.getProfilePhotoLabel().setIcon(IconCreator.getIconWithSize(icon,60,60));
                    profileBox.getProfilePhotoLabel().setIcon(IconCreator.getIconWithSize(icon,60,60));
                    main.refreshProfilePhotos();
                }

            }
        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userProfilePage.getBioLabel().setText(bioTextPane.getText()); //Change also database
                userProfilePage.getUser().setGender(genderComboBox.getSelectedItem() +"");
                userProfilePage.getUser().setDateOfBirth(dayComboBox.getSelectedItem() + "/"
                + monthComboBox.getSelectedItem() + "/" + yearComboBox.getSelectedItem());
                userProfilePage.getUser().setDepartment(departmentComboBox.getSelectedItem() + "");
                System.out.println(userProfilePage.getUser().getDateOfBirth());
                dispose();
            }
        });
    }


}
