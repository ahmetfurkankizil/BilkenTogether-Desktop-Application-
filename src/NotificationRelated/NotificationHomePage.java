package NotificationRelated;

import HomePage.HomeScreen;
import HomePage.*;
import Icons.IconCreator;
import Posts.LessonPost;
import UserRelated.Student;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NotificationHomePage extends JFrame {
    private JPanel mainPanel;
    private Student currentUser;
    private JButton lessonsButton;
    private JButton studiesButton;
    private JButton activitiesButton;
    private JButton profileBoxButton;
    private JButton filterBoxButton;
    private JLabel homeLabel;
    private JLabel messagesLabel;
    private JLabel notificationsLabel;
    private JLabel profileLabel;
    private JLabel requestsLabel;
    private JLabel profilePhotoLabel;
    private JPanel secondMainPanel;
    private GridBagConstraints g;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel middlePanel;
    private JPanel buttonPanel;
    private JScrollPane flowScrollPane;
    private JLabel courseType;
    private JComboBox courseTypeComboBox;
    private JButton postButton;
    private JLabel availableDaysLabel;
    private JComboBox availableDaysComboBox;
    private JPanel insideScrollPanePanel;
    private JButton postLessonButton;
    private JButton requestLessonButton;
    private JTextArea textArea1;
    private JPanel postingPanel;
    private JLabel logOutLabel;
    private JPanel homeLabelPanel;
    private JPanel messagesLabelPanel;
    private JPanel notificationsLabelPanel;
    private JPanel profileLabelPanel;
    private JPanel requestLabelPanel;
    private JPanel logOutLabelPanel;
    private JPanel MainPanel;
    private JPanel addablePanel;
    private JPanel TopLabel;
    private JLabel TopTopLabel;
    private JPanel MidPanel;
    private JPanel MidTopPanel;
    private JPanel MidBottomPanel;
    private JLabel MidBottomLabel;
    private JPanel BottomPanel;
    private JPanel BottomTopPanel;
    private JPanel BottomBottomPanel;
    private JLabel BottomBottomLabel;
    private JLabel MidTopLabel;
    private JLabel BottomTopLabel;
    private ArrayList<JButton> sectionButtons;
    private ArrayList<JLabel> leftPanelLabels;

    public NotificationHomePage() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 800);
        currentUser = new Student("Erdem","erdem.p",1,"l","d","p","b");
        generalSetup();



        setVisible(true);
    }

    public void generalSetup() {
        g = new GridBagConstraints();
        sectionButtons = new ArrayList<>();
        leftPanelLabels = new ArrayList<>();
        TopTopLabel.setIcon(HomeScreen.back);

        leftPanelLabels.add(homeLabel);
        leftPanelLabels.add(notificationsLabel);
        leftPanelLabels.add(messagesLabel);
        leftPanelLabels.add(profileLabel);
        leftPanelLabels.add(requestsLabel);
        leftPanelLabels.add(logOutLabel);

        for (JLabel label :
                leftPanelLabels) {
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        }




        int secPanelIconWidth = 30;
        leftPanel.setBackground(new Color(203,203,203));
        logOutLabel.setIcon(IconCreator.getIconWithSize(IconCreator.logOutIcon, secPanelIconWidth, secPanelIconWidth));
        int a = (int) (secPanelIconWidth * 1.14);
        messagesLabel.setIcon(IconCreator.getIconWithSize(IconCreator.messageIcon, secPanelIconWidth, secPanelIconWidth));
        notificationsLabel.setIcon(IconCreator.getIconWithSize(IconCreator.notificationsIcon, secPanelIconWidth, (int) (secPanelIconWidth * 1.14)));
        profileLabel.setIcon(IconCreator.getIconWithSize(IconCreator.userIcon, secPanelIconWidth, (int) (secPanelIconWidth * 1.14)));
        requestsLabel.setIcon(IconCreator.getIconWithSize(IconCreator.requestSecIcon, secPanelIconWidth, (int) (secPanelIconWidth * .88)));
        homeLabel.setIcon(IconCreator.getIconWithSize(IconCreator.houseIcon, secPanelIconWidth, (int) (secPanelIconWidth * .88)));
        homeLabelPanel.setBorder(new SectionItemBorder());
        requestLabelPanel.setBorder(new SectionItemBorder());
        profileLabelPanel.setBorder(new SectionItemBorder());
        messagesLabelPanel.setBorder(new SectionItemBorder());
        notificationsLabelPanel.setBorder(new SectionItemBorder());




    }

    public static void main(String[] args) {
        NotificationHomePage homeScreen = new NotificationHomePage();
    }
    public boolean requestGiveButtonCheck(){
        return !(postLessonButton.isSelected()) && !requestLessonButton.isSelected();
    }
    private class requestActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            System.out.println(requestGiveButtonCheck());
            if (requestGiveButtonCheck()){
                b.setSelected(true);
            }else if (b.isSelected()){
                b.setSelected(false);
            }

        }
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
}
