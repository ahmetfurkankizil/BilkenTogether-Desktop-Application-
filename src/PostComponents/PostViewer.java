package PostComponents;

import Icons.IconCreator;
import UserProfileGUI.PPImageHandler;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class PostViewer extends JPanel {
    public static final Font headerFont = new Font("Ariel",Font.BOLD,15);
    public static final Font profileNameFont = new Font("Ariel",Font.BOLD,18);
    public static final Font textFont = new Font("Ariel",Font.PLAIN,12);
    protected GridBagConstraints g;
    protected JLabel commentLabel;
    protected Color informationBackground;
    public static final ImageIcon comment = IconCreator.getIconWithSize(IconCreator.commentIcon,25,22);
    public static final ImageIcon envelope = IconCreator.getIconWithSize(IconCreator.messageIcon,25,22);
    public JPanel topInformationPanel;
    protected JLabel proName;
    protected PPImageHandler proPhoto;
    protected JPanel bottomIformationPanel;
    protected JLabel messageLabel;
    protected JTextArea textArea2;

    public PostViewer() {

    }
    protected void setUp(){
        g = new GridBagConstraints();

        setMinimumSize(new Dimension(300,30));
        setLayout(new GridBagLayout());
        setBorder(new SectionItemBorder());
        informationBackground = new Color(194, 175, 151);
        topInformationPanel = new JPanel();
        textArea2 = new JTextArea();
        proName = new JLabel();
        proName.setFont(profileNameFont);
        textArea2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textArea2.setBackground(getBackground());
        textArea2.setEditable(false);
        textArea2.setFocusable(false);
        textArea2.setColumns(55);
        textArea2.setLineWrap(true);
        textArea2.setMargin(new Insets(0,0,10,0));
        commentLabel = new JLabel(comment);
    }
    private class SectionItemBorder implements Border {
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 10, 0);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.gray);
            g2d.drawLine(x,y+height,x+width+10,y+height);
        }
    }
    public static void addPadding(JComponent comp){
        comp.setBorder(new EmptyBorder(3,3,3,3));
    }
    public static void addPadding(JComponent comp, int top, int left, int bottom, int right) {
        comp.setBorder(new EmptyBorder(top, left, bottom, right));
    }

}
