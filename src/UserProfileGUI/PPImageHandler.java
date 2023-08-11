package UserProfileGUI;

import Icons.IconCreator;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PPImageHandler extends JLabel {
    private Icon image;
    public static final File f = new File("src/UserProfileGUI/profile-phot.png");

    public PPImageHandler(User user){
        super();
        setIcon(IconCreator.getIconWithSize((new ImageIcon(user.pullTheProfilePhotoFromDB(user.getId()))),70,70));
        image = getIcon();
    }
    public PPImageHandler(ImageIcon i){
        super();
        setIcon(IconCreator.getIconWithSize(i,60,60));
        image = getIcon();
    }

    @Override
    public void paint(Graphics g) {
        if (image != null) {
            int width = image.getIconWidth();
            int height = image.getIconHeight();
            int diameter = Math.min(width, height);
            BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = mask.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);    //  for image smooth
            g2d.fillOval(0, 0, diameter - 1, diameter - 1);
            g2d.dispose();
            BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
            g2d = masked.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);    //  for image smooth
            int x = (diameter - width) / 2;
            int y = (diameter - height) / 2;
            g2d.drawImage(toImage(image), x, y, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
            g2d.drawImage(mask, 0, 0, null);
            g2d.dispose();
            Icon icon = new ImageIcon(masked);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(toImage(icon),0, 0, null);
        }
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }
}
