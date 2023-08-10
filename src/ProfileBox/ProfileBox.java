package ProfileBox;

import UserRelated.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProfileBox extends  JPanel {
    private JPanel panel1;
    private JLabel nameSurnameLabel;
    private JLabel emailLabel;
    private JLabel profilePhotoLabel;



    /*public ProfileBox(User user) {
        add(panel1);
        nameSurnameLabel.setText(user.getName());
        emailLabel.setText(user.getEmail());
        InputStream is = new ByteArrayInputStream(user.getProfilePhoto());
        BufferedImage image;
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
        ImageIcon icon = new ImageIcon(image);
        profilePhotoLabel.setIcon(icon);
    }

    public JLabel getProfilePhotoLabel() {
        return profilePhotoLabel;
    }

     */
}

