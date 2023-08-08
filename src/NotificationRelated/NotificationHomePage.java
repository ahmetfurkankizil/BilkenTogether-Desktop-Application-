package NotificationRelated ;

import HomePage.LessonsPage.LessonsPage;
import Icons.IconCreator;
import MessagesRelated.Notification;
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

    private Notification notification;
    private JPanel requestLabelPanel;
    private JPanel logOutLabelPanel;
    private JPanel addablePanel;
    private JPanel TopLabel;
    private JLabel TopTopLabel;
    private ArrayList<JButton> sectionButtons;
    private GridBagConstraints gridBagConstraints;
    private ArrayList<JLabel> leftPanelLabels;

    public NotificationHomePage()
    {
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(1500, 800);
        currentUser = new Student("Erdem", "erdem.p", 1, "l", "d", "p", "b");
        notification = new Notification(currentUser, currentUser, 1);
        notification.setContent("Sample notification content");
        g = new GridBagConstraints();
        g.gridx = 0;
        g.fill = GridBagConstraints.VERTICAL;
        g.anchor = GridBagConstraints.NORTHWEST;
        insideScrollPanePanel.add(new GeneralNotificationPanel(notification),g);
        insideScrollPanePanel.add(new GeneralNotificationPanel(notification),g);
        insideScrollPanePanel.add(new GeneralNotificationPanel(notification),g);
        insideScrollPanePanel.add(new GeneralNotificationPanel(notification),g);
        insideScrollPanePanel.add(new GeneralNotificationPanel(notification),g);

        insideScrollPanePanel.add(new GeneralNotificationPanel(notification),g);



        //generalSetup();

        //setVisible(true);
    }

    public JPanel getMainPanel() {
        return insideScrollPanePanel;
    }

    public void generalSetup() {
        g = new GridBagConstraints();
        sectionButtons = new ArrayList<>();
        leftPanelLabels = new ArrayList<>();
        TopTopLabel.setIcon(LessonsPage.back);

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

    public JPanel getTopLabel() {
        return TopLabel;
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
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
        {

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.gray);
            g2d.drawLine(x,y+height,x+width+10,y+height);

        }
    }
}
