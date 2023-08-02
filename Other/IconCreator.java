import javax.swing.*;
import java.awt.*;
import java.io.File;

public class IconCreator extends JFrame {
    public static final File f = new File("src/Icons/house-solid.png");
      public static final ImageIcon houseIcon = new ImageIcon(f.getAbsolutePath());

      public static final ImageIcon backIcon = new ImageIcon(f.getAbsolutePath().replace("house", "arrow-left"));
      public static final ImageIcon notificationsIcon = new ImageIcon(f.getAbsolutePath().replace("house", "bell"));
      public static final ImageIcon checkIcon = new ImageIcon(f.getAbsolutePath().replace("house", "check"));
      public static final ImageIcon messageIcon = new ImageIcon(f.getAbsolutePath().replace("house", "envelope"));
      public static final ImageIcon filterIcon = new ImageIcon(f.getAbsolutePath().replace("house", "filter"));
      public static final ImageIcon requestSecIcon = new ImageIcon(f.getAbsolutePath().replace("house", "heart-circle-check"));
      public static final ImageIcon nonActiveLikeIcon = new ImageIcon(f.getAbsolutePath().replace("house-solid", "heart-regular"));
      public static final ImageIcon activeLikeIcon = new ImageIcon(f.getAbsolutePath().replace("house", "heart"));
      public static final ImageIcon searchIcon = new ImageIcon(f.getAbsolutePath().replace("house", "magnifying-glass"));
      public static final ImageIcon logOutIcon = new ImageIcon(f.getAbsolutePath().replace("house", "right-from-bracket"));
      public static final ImageIcon userIcon = new ImageIcon(f.getAbsolutePath().replace("house", "user"));
      public static final ImageIcon deniedIcon = new ImageIcon(f.getAbsolutePath().replace("house", "xmark"));
      public static final ImageIcon myTry = new ImageIcon(f.getAbsolutePath().replace("house-solid", "myTry"));

    public IconCreator() {

        System.out.println(f.getAbsolutePath());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new MPanel());
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new IconCreator();
    }

    private static class MPanel extends JPanel {
        public MPanel() {
            JLabel l = new JLabel("lol");
            l.setIcon(getIconWithSize(backIcon,100,100));
            add(l);
            add(new JLabel(getIconWithSize(userIcon,30,30)));
            add(new JLabel(getIconWithSize(checkIcon,30,30)));
            add(new JLabel(getIconWithSize(deniedIcon,30,30)));
            add(new JLabel(getIconWithSize(houseIcon,30,30)));
            add(new JLabel(getIconWithSize(logOutIcon,30,30)));
            add(new JLabel(getIconWithSize(activeLikeIcon,30,30)));
            add(new JLabel(getIconWithSize(notificationsIcon,30,30)));
            add(new JLabel(getIconWithSize(nonActiveLikeIcon,50,50)));
            add(new JLabel(getIconWithSize(messageIcon,100,100)));
            add(new JLabel(getIconWithSize(filterIcon,100,100)));
            add(new JLabel(getIconWithSize(requestSecIcon, 100, 100)));
            add(new JLabel(getIconWithSize(myTry, 100, 100)));

            add(new JLabel(getIconWithSize(searchIcon,50,50)));
        }
    }

    public static ImageIcon getIconWithSize(ImageIcon imageIcon, int width, int height) {
        return new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
    }
}
