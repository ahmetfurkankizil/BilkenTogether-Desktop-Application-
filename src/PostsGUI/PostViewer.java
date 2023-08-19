package PostsGUI;

import HomePages.HomeMain.HomeMain;
import HomePages.HomeMain.MainInterface;
import Other.Icons.IconCreator;
import Posts.Post;
import UserProfileGUI.PPImageHandler;
import UserRelated.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class PostViewer extends JPanel {
    private  JButton deleteButton = new JButton(IconCreator.getIconWithSize(IconCreator.deleteIcon,22,25));
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
    protected MainInterface main;
    private User u1;

    public PostViewer(MainInterface main) {
        this.main = main;
        this.u1 = main.getCurrentUser();
    }
    protected void setUp(){
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //u1.deleteStudyPost(this);
                // TO BE IMPLEMENTED
            }
        });
        g = new GridBagConstraints();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setMinimumSize(new Dimension(300,30));
        setLayout(new GridBagLayout());
        setBorder(new SectionItemBorder());
        informationBackground = new Color(194, 175, 151);
        topInformationPanel = new JPanel();
        textArea2 = new JTextArea();
        proName = new JLabel();
        proName.setFont(profileNameFont);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textArea2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textArea2.setBackground(getBackground());
        textArea2.setEditable(false);
        textArea2.setFocusable(false);
        textArea2.setColumns(55);
        textArea2.setLineWrap(true);
        textArea2.setMargin(new Insets(0,0,10,0));
        commentLabel = new JLabel(comment);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.expandPost(getThis());

            }
        });
    }
    public PostViewer getThis(){return  this;}
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
    public abstract Post getPost();
}
