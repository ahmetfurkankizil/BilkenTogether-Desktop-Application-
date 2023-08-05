package HomePage;

import Icons.IconCreator;
import PostComponents.DayButtons;
import PostComponents.GeneralButton;
import PostComponents.RequestGiveButtons;
import PostComponents.SectionButton;
import Posts.LessonPost;
import UserRelated.Student;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StudiesPage extends JFrame {
    private JPanel sectionsTab;
    private JPanel filtersTab;
    private JPanel postsTab;
    private JLabel appNameLabel;

    private JScrollPane studiesScroll;
    private JPanel lessonsPane;
    private JPanel postingPanel1;
    private JPanel panel1;
    private JButton button6;
    private JLabel hLabel;
    private JLabel messagesLabel;
    private JLabel notificationsLabel;
    private JLabel profileLabel;
    private JLabel requestsLabel;
    private JLabel logOutButton;
    private JTextField topicField;
    private JTextArea postArea;
    private JTextArea textArea1;
    private GeneralButton postButton;
    private JLabel tru1Label;
    private JList topicsList;
    private JLabel topicNameLabel;
    private JLabel topicXmark;
    private JScrollPane topicPane;
    private JPanel topicNamePanel;
    private JPanel studiesPane;
    private JTextField authorField;
    private JTextField headerField;
    private JPanel authorPanel;
    private JLabel headerLabel;
    private JLabel authorLabel;
    private JPanel headerPanel;
    ArrayList<ImageIcon> unprocessedIcons;
    private PostsPanel lolPanel;
    GridBagConstraints g;
    ArrayList<GeneralButton> clearableButtons;
    private static int secPanelIconWidth = 30;

    public StudiesPage() {
        addIcons();


        topicPane.setVisible(false);
        topicNamePanel.setBackground(Color.cyan);
        topicXmark.setIcon(IconCreator.getIconWithSize(IconCreator.deniedIcon,10,10));
        topicXmark.setCursor(new Cursor(Cursor.HAND_CURSOR));
        topicNamePanel.setVisible(false);

        postingPanel1.setBorder(new sectionItemBorder());
        sectionsTab.setBackground(new Color(210, 210, 205));
        g = new GridBagConstraints();
        setSize(1400, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);
        g.gridx = 0;
        //g.insets = new Insets(0,0,0,0);
        //lessonsPane.add(new LessonPostViewer(new LessonPost(new Student(), "lol", "l", 1111000)), g);
        //textArea1.setMargin(new Insets(10, 10, 10, 10));

        setUpListeners();
        //setUpClearableButtons();
        setSectionsTab();

        setVisible(true);

    }

    public static void main(String[] args) {
        StudiesPage mainP = new StudiesPage();
    }


    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }


    public void addIcons() {
        hLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logOutButton.setIcon(IconCreator.getIconWithSize(IconCreator.logOutIcon, secPanelIconWidth, secPanelIconWidth));
        int a = (int) (secPanelIconWidth * 1.14);
        messagesLabel.setIcon(IconCreator.getIconWithSize(IconCreator.messageIcon, secPanelIconWidth, secPanelIconWidth));
        notificationsLabel.setIcon(IconCreator.getIconWithSize(IconCreator.notificationsIcon, secPanelIconWidth, (int) (secPanelIconWidth * 1.14)));
        profileLabel.setIcon(IconCreator.getIconWithSize(IconCreator.userIcon, secPanelIconWidth, (int) (secPanelIconWidth * 1.14)));
        requestsLabel.setIcon(IconCreator.getIconWithSize(IconCreator.requestSecIcon, secPanelIconWidth, (int) (secPanelIconWidth * .88)));
        hLabel.setIcon(IconCreator.getIconWithSize(IconCreator.houseIcon, secPanelIconWidth, (int) (secPanelIconWidth * .88)));

    }


    private class sectionItemBorder implements Border {


        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 0, 0);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.gray);
            g2d.drawLine(x, y + height, x + width + 10, y + height);
        }
    }

    public void setUpListeners(){


        topicsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedItem = (String) topicsList.getSelectedValue();
                    topicNameLabel.setText(selectedItem);
                    topicsList.setVisible(false);
                    topicPane.setVisible(false);
                    topicField.setText("");
                    topicField.setVisible(false);
                    topicNamePanel.setVisible(true);
                }
            }
        });


        topicField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                topicsList.setVisible(true);
                tru1Label.setText("Select:");
                revalidate();
                repaint();
                topicPane.setVisible(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                topicsList.setMaximumSize(new Dimension(30, 30));
                topicsList.setVisible(false);
                topicPane.setVisible(false);
                tru1Label.setText("");
                revalidate();
                repaint();
            }
        });
        topicXmark.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                topicField.setVisible(true);
                topicNameLabel.setText("");
                topicNamePanel.setVisible(false);
            }
        });
    }
    public void setSectionsTab(){
        for (Component component:
             sectionsTab.getComponents()) {
            if (component instanceof  JLabel){
                addCursor((JComponent) component);
            }

        }
    }
    public void addCursor(JComponent comp){
        comp.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public  class PostingButton extends GeneralButton {
        @Override
        public void actionPerformed(ActionEvent e) {
            super.actionPerformed(e);

                if (!textArea1.getText().isEmpty()) {

                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter A Description!", "No Description", JOptionPane.PLAIN_MESSAGE);
                }

        }
    }
}

