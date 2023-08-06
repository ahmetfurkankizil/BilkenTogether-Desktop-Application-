package HomePage.StudiesPage;

import HomePage.ActivityPage.ActivitiesPage;
import HomePage.LessonsPage.ActivitiesPostViewer;
import HomePage.LessonsPage.LessonPostViewer;
import HomePage.LessonsPage.LessonsPage;
import HomePage.LessonsPage.StudiesPostViewer;
import Icons.IconCreator;
import Posts.ActivityPost;
import Posts.LessonPost;
import Posts.StudyPost;
import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;
import java.util.Date;

public class Main extends JFrame {
    private StudiesPage studies;
    private ActivitiesPage activities;

    private LessonsPage lessons;
    private JPanel mainPanel;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    public static final ImageIcon back = IconCreator.getIconWithSize(IconCreator.backIcon, 30, 30);
    private User currentUser;
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
    private PPImageHandler profilePhoto;
    private JPanel secondMainPanel;
    private GridBagConstraints g;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel middlePanel;
    private JLabel bilkenTogetherLabel;
    private JScrollPane flowScrollPane;
    private JPanel insideScrollPanePanel;
    private JButton postLessonButton;
    private JButton requestLessonButton;
    private JPanel postingPanel;
    private JLabel logOutLabel;
    private JPanel homeLabelPanel;
    private JPanel messagesLabelPanel;
    private JPanel notificationsLabelPanel;
    private JPanel profileLabelPanel;
    private JPanel requestLabelPanel;
    private JPanel logOutLabelPanel;
    private JPanel buttonPanel;
    private JTextArea textArea1;
    private JComboBox courseTypeComboBox;
    private JButton postButton;
    private JButton mondayButton;
    private JButton tuesdayButton;
    private JButton wednesdayButton;
    private JButton thursdayButton;
    private JButton fridayButton;
    private JButton saturdayButton;
    private JButton sundayButton;
    private JPanel profilePhotoPanel;
    private JPanel daysPanel;
    private JLabel logoLabel;
    private JButton clearButton;
    private JLabel errorLabel;
    private JPanel lessonsQFpanel;
    private JButton givenButton;
    private JButton requestedButton;
    private JButton filtersSubmitButton;
    private JButton mondayFilterButton;
    private JButton TuesdayFilterButton;
    private JButton WednesdayFilterButton;
    private JButton thursdayFilterButton;
    private JButton fridayFilterButton;
    private JButton saturdayFilterButton;
    private JButton sundayFilterButton;
    private JPanel quickFiltersPanel;
    private JPanel removableRight;
    private ArrayList<JButton> sectionButtons;
    private ArrayList<JLabel> leftPanelLabels;

    public Main() {
        setUpPages();
        setContentPane(mainPanel);
        insideScrollPanePanel.add(lessons.getInsideScrollPanePanel());
        removableRight.add(lessons.getQuickFiltersPanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 800);
        currentUser = new Student("Erdem", "erdem.p", 22203112, "l", "d", "p", "b");
        generalSetup();


        setVisible(true);
        ActionListener sectionButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton tempButton = (JButton) e.getSource();

                if (!tempButton.isSelected()) {
                    String section = tempButton.getText();
                    switch (section.toLowerCase().charAt(0)) {
                        case 'a':
                            insideScrollPanePanel.removeAll();
                            insideScrollPanePanel.add(activities.getInsideScrollPanePanel());
                            removableRight.removeAll();
                            removableRight.add(activities.getQuickFiltersPanel());
                            break;
                        case 's':
                            insideScrollPanePanel.removeAll();
                            insideScrollPanePanel.add(studies.getInsideScrollPanePanel());
                            removableRight.removeAll();
                            removableRight.add(studies.getQfPanel());
                            break;
                        case 'l':
                            System.out.println("Lessons");
                            insideScrollPanePanel.removeAll();
                            insideScrollPanePanel.add(lessons.getInsideScrollPanePanel());
                            removableRight.removeAll();
                            removableRight.add(lessons.getQuickFiltersPanel());
                            break;
                    }
                    lessonsButton.setSelected(false);
                    activitiesButton.setSelected(false);
                    studiesButton.setSelected(false);
                    tempButton.setSelected(true);
                    repaint();
                    revalidate();
                }
            }
        };
        lessonsButton.addActionListener(sectionButtonListener);
        studiesButton.addActionListener(sectionButtonListener);
        activitiesButton.addActionListener(sectionButtonListener);
    }

    private void setUpPages() {
        activities = new ActivitiesPage();
        activities.setMain(this);
        studies = new StudiesPage();
        studies.setMain(this);
        lessons = new LessonsPage();
        lessons.setMain(this);
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void generalSetup() {
        g = new GridBagConstraints();
        setUpCursors();
        setUpSectionLabels();
    }


    private void setUpCursors() {
        sectionButtons = new ArrayList<>();
        sectionButtons.add(lessonsButton);
        sectionButtons.add(activitiesButton);
        sectionButtons.add(studiesButton);

        leftPanelLabels = new ArrayList<>();
        leftPanelLabels.add(homeLabel);
        leftPanelLabels.add(notificationsLabel);
        leftPanelLabels.add(messagesLabel);
        leftPanelLabels.add(profileLabel);
        leftPanelLabels.add(requestsLabel);
        leftPanelLabels.add(logOutLabel);


        for (JButton j :
                sectionButtons) {
            j.setFocusable(false);
            j.setCursor(handCursor);
        }
        for (JLabel label :
                leftPanelLabels) {
            label.setCursor(handCursor);
        }

    }


    private void setUpSectionLabels() {
        int secPanelIconWidth = 30;
        leftPanel.setBackground(new Color(203, 205, 208));
        logOutLabel.setIcon(IconCreator.getIconWithSize(IconCreator.logOutIcon, secPanelIconWidth, secPanelIconWidth));
        int a = (int) (secPanelIconWidth * 1.14);
        messagesLabel.setIcon(IconCreator.getIconWithSize(IconCreator.messageIcon, secPanelIconWidth, secPanelIconWidth));
        notificationsLabel.setIcon(IconCreator.getIconWithSize(IconCreator.notificationsIcon, secPanelIconWidth, (int) (secPanelIconWidth * 1.14)));
        profileLabel.setIcon(IconCreator.getIconWithSize(IconCreator.userIcon, secPanelIconWidth, (int) (secPanelIconWidth * 1.14)));
        requestsLabel.setIcon(IconCreator.getIconWithSize(IconCreator.requestSecIcon, secPanelIconWidth, (int) (secPanelIconWidth * .88)));
        homeLabel.setIcon(IconCreator.getIconWithSize(IconCreator.houseIcon, secPanelIconWidth, (int) (secPanelIconWidth * .88)));
        setUpBorders();
    }

    private void setUpBorders() {
        homeLabelPanel.setBorder(new SectionItemBorder());
        requestLabelPanel.setBorder(new SectionItemBorder());
        profileLabelPanel.setBorder(new SectionItemBorder());
        messagesLabelPanel.setBorder(new SectionItemBorder());
        notificationsLabelPanel.setBorder(new SectionItemBorder());

    }


    public static void main(String[] args) {
        Main lessonsPage = new Main();
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
            g2d.drawLine(x, y + height, x + width + 10, y + height);
        }
    }

}
