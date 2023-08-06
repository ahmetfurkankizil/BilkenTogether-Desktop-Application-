package HomePage.LessonsPage;

import HomePage.ActivityPage.ActivitiesPage;
import HomePage.StudiesPage.Main;
import HomePage.StudiesPage.StudiesPage;
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

public class LessonsPage extends JFrame {
    private StudiesPage studies;
    private ActivitiesPage activities;
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
    private JLabel courseType;
    private JComboBox courseTypeComboBox;
    private JButton postButton;
    private JButton mondayButton;
    private JButton tuesdayButton;
    private JButton wednesdayButton;
    private JButton thursdayButton;
    private JButton fridayButton;
    private JButton saturdayButton;
    private JButton sundayButton;
    private JPanel postingButtonPanel;
    private JPanel profilePhotoPanel;
    private JPanel daysPanel;
    private JLabel logoLabel;
    private JButton clearButton;
    private JLabel errorLabel;
    private JPanel lessonsQFpanel;
    private JLabel quickFiltersLabel;
    private JButton givenButton;
    private JButton requestedButton;
    private JLabel courseLabel;
    private JComboBox courseComboBox;
    private JComboBox artAndSportComboBox;
    private JPanel daysButtonPanel;
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
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JPanel MainPanel;
    private JPanel addablePanel;
    private JPanel TopLabel;
    private JLabel TopTopLabel;
    private JPanel MidPanel;
    private JPanel MidTopPanel;
    private JLabel MidTopLabel;
    private JPanel MidBottomPanel;
    private JLabel MidBottomLabel;
    private JPanel BottomPanel;
    private JPanel BottomTopPanel;
    private JLabel BottomTopLabel;
    private JPanel BottomBottomPanel;
    private JLabel BottomBottomLabel;
    private ArrayList<JButton> sectionButtons;
    private ArrayList<JLabel> leftPanelLabels;
    private ArrayList<JButton> postingComponentButtons;
    private ArrayList<JButton> dayButtons;

    public JPanel getInsideScrollPanePanel() {
        return insideScrollPanePanel;
    }

    public JPanel getQuickFiltersPanel() {
        return quickFiltersPanel;
    }

    public LessonsPage() {
        setUpPages();

        postButton.addActionListener(new LesssonPostPostingListener());
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 800);
        currentUser = new Student("Erdem", "erdem.p", 22203112, "l", "d", "p", "b");
        generalSetup();
        //setVisible(true);
        filterBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton but = (JButton) e.getSource();
                but.setSelected(!but.isSelected());
            }
        };
        mondayFilterButton.addActionListener(listener);
        TuesdayFilterButton.addActionListener(listener);
        WednesdayFilterButton.addActionListener(listener);
        fridayFilterButton.addActionListener(listener);
        thursdayFilterButton.addActionListener(listener);
        saturdayFilterButton.addActionListener(listener);
        sundayFilterButton.addActionListener(listener);
        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                if (requestGiveButtonCheckFilter()) {
                    b.setSelected(true);
                } else if (b.isSelected()) {
                    b.setSelected(false);
                }
            }
        };
        givenButton.addActionListener(listener1);
        requestedButton.addActionListener(listener1);

        filterBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lessonsQFpanel.isVisible())
                    lessonsQFpanel.setVisible(false);
                else
                    lessonsQFpanel.setVisible(true);
            }
        });

        studiesButton.addComponentListener(new ComponentAdapter() {
        });
        studiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(studies.getContentPane());
            }
        });
        activitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideScrollPanePanel.removeAll();
                insideScrollPanePanel.add(activities.getInsideScrollPanePanel());
                removableRight.remove(quickFiltersPanel);
                removableRight.add(activities.getActivitiesQFpanel());

                repaint();
                revalidate();

            }
        });
    }

    private void setUpPages() {
        activities = new ActivitiesPage();
        studies = new StudiesPage();
        activities.setStudiesPage(studies);
        studies.setLessonsPage(this);
        activities.setLessonsPage(this);
        studies.setActivitiesPage(activities);

    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void generalSetup() {
        g = new GridBagConstraints();
        textArea1.setMargin(new Insets(5, 5, 5, 5));
        textArea1.setLineWrap(true);
        textArea1.setColumns(50);
        setUpCursors();
        setUpProfilePhoto();
        setUpActionListeners();
        setUpPostComponentButtons();
        setUpSectionLabels();
    }

    private void setUpProfilePhoto() {
        errorLabel.setForeground(Color.red);
        errorLabel.setText(" ");
        profilePhoto = new PPImageHandler();
        profilePhotoPanel.add(profilePhoto);
    }

    private void setUpActionListeners() {
        postLessonButton.addActionListener(new requestActionListener());
        requestLessonButton.addActionListener(new requestActionListener());

    }

    private void setUpCursors() {
        sectionButtons = new ArrayList<>();
        sectionButtons.add(lessonsButton);
        sectionButtons.add(activitiesButton);
        sectionButtons.add(studiesButton);
        postLessonButton.setFocusable(false);
        requestLessonButton.setFocusable(false);

        leftPanelLabels = new ArrayList<>();
        leftPanelLabels.add(homeLabel);
        leftPanelLabels.add(notificationsLabel);
        leftPanelLabels.add(messagesLabel);
        leftPanelLabels.add(profileLabel);
        leftPanelLabels.add(requestsLabel);
        leftPanelLabels.add(logOutLabel);

        postLessonButton.setCursor(handCursor);
        requestLessonButton.setCursor(handCursor);

        filterBoxButton.setCursor(handCursor);
        filtersSubmitButton.setCursor(handCursor);
        mondayFilterButton.setCursor(handCursor);
        saturdayFilterButton.setCursor(handCursor);
        sundayFilterButton.setCursor(handCursor);
        sundayFilterButton.setFocusable(false);


        setUpDaysButtons();
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

    private void setUpDaysButtons() {
        dayButtons = new ArrayList<>();
        ActionListener dayButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                b.setSelected(!b.isSelected());
            }
        };
        ActionListener resetButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton button :
                        dayButtons) {
                    button.setSelected(false);
                }
                postLessonButton.setSelected(false);
                requestLessonButton.setSelected(false);
            }
        };
        for (Component c :
                daysPanel.getComponents()) {
            if (c instanceof JButton) {
                JButton tempButton = (JButton) c;
                dayButtons.add(tempButton);
                tempButton.setFocusable(false);
                tempButton.setCursor(handCursor);
                tempButton.addActionListener(dayButtonListener);
            }
        }
        clearButton.setFocusable(false);
        clearButton.addActionListener(resetButtonListener);
        clearButton.setCursor(handCursor);
        postButton.setCursor(handCursor);
        postButton.setFocusable(false);

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
        postingPanel.setBorder(new SectionItemBorder());

    }

    private void setUpPostComponentButtons() {
        postingComponentButtons = new ArrayList<>();
    }

    public static void main(String[] args) {
        LessonsPage lessonsPage = new LessonsPage();
    }

    public boolean requestGiveButtonCheck() {
        return !(postLessonButton.isSelected()) && !requestLessonButton.isSelected();
    }

    public boolean requestGiveButtonCheckFilter() {
        return !(givenButton.isSelected()) && !requestedButton.isSelected();
    }
    private Main main;
    public void setMain(Main main) {
        this.main =main;
    }


    private class requestActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if (requestGiveButtonCheck()) {
                b.setSelected(true);
            } else if (b.isSelected()) {
                b.setSelected(false);
            }
        }
    }

    private void addLessonPost() {
        g.gridx = 0;

        ActivitiesPostViewer viewer2 = new ActivitiesPostViewer(new ActivityPost(2, (Student) currentUser, "a little post des", 3, "20/01/2023", "Concert", "25/09/2003"));
        String[] topicColl = {"Algebra", "Complex Analysis"};
        LessonPostViewer viewer = new LessonPostViewer(new LessonPost(1, (Student) currentUser, "a little post des", "MAth", 11111, true, "25/09/2003"));
        //insideScrollPanePanel.add(viewer, g);
        currentUser.addToStudiesTable(new StudyPost(2, (Student) currentUser, "Author", "Very Important Header", " Very L" + makeItLong("K", 10) + "NG DESCRIPTION", null, "23:05", topicColl));
        currentUser.addToStudiesTable(new StudyPost(3, (Student) currentUser, "Author", "Very Important Header", " Very L" + makeItLong("FOR", 10) + "NG DESCRIPTION", null, "23:05", topicColl));
        ((Student) currentUser).addToLessonsTable(new LessonPost(4, (Student) currentUser, "This is description", "This is type filter", 3131, true, "25/09/2003"));
        ((Student) currentUser).addToLessonsTable(new LessonPost(5, (Student) currentUser, "This is description", "This is type filter", 3131, true, "25/09/2003"));
        ((Student) currentUser).addToLessonsTable(new LessonPost(6, (Student) currentUser, "This is description", "This is type filter", 3131, true, "25/09/2003"));
        ((Student) currentUser).addToLessonsTable(new LessonPost(7, (Student) currentUser, "This is description", "This is type filter", 3131, true, "25/09/2003"));
        StudyPost tempPost = currentUser.pullStudyPostFromDB(22203112, 2);
        StudyPost tempPost1 = currentUser.pullStudyPostFromDB(22203112, 3);
        LessonPost tempPost2 = ((Student) currentUser).pullLessonPostFromDB(22203112, 4);
        LessonPost tempPost3 = ((Student) currentUser).pullLessonPostFromDB(22203112, 5);
        LessonPost tempPost4 = ((Student) currentUser).pullLessonPostFromDB(22203112, 6);
        LessonPost tempPost5 = ((Student) currentUser).pullLessonPostFromDB(22203112, 7);
        //insideScrollPanePanel.add(viewer2, g);
        StudiesPostViewer viewer3 = new StudiesPostViewer(tempPost);
        StudiesPostViewer viewer4 = new StudiesPostViewer(tempPost1);
        LessonPostViewer viewer5 = new LessonPostViewer(tempPost2);
        LessonPostViewer viewer6 = new LessonPostViewer(tempPost3);
        LessonPostViewer viewer7 = new LessonPostViewer(tempPost4);
        LessonPostViewer viewer8 = new LessonPostViewer(tempPost5);

        insideScrollPanePanel.add(viewer3, g);
        insideScrollPanePanel.add(viewer4, g);
        insideScrollPanePanel.add(viewer5, g);
        insideScrollPanePanel.add(viewer6, g);
        insideScrollPanePanel.add(viewer7, g);
        insideScrollPanePanel.add(viewer8, g);

    }

    public String makeItLong(String str, int repeat) {
        String returnS = "";
        for (int i = 0; i < repeat; i++) {
            returnS += str;
        }
        return returnS;
    }

    private void addLessonPost(LessonPost post) {
        g.gridx = 0;
        LessonPostViewer viewer6 = new LessonPostViewer(post);
        insideScrollPanePanel.add(viewer6, g);

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

    public class LesssonPostPostingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (checkIfValid()) {
                // int postId = Database.getNewPostID();
                int postId = 0;
                LessonPost tempPost = new LessonPost(postId, currentUser, textArea1.getText(), (String) courseTypeComboBox.getSelectedItem(), getSelectedDaysBinary(), !postLessonButton.isSelected(), new Date().toString());
                addLessonPost(tempPost);
                repaint();
                revalidate();
                main.repaint();
                main.revalidate();
            }
        }

        private boolean checkIfValid() {
            if (textArea1.getText().isEmpty()) {
                errorLabel.setText("Please Enter A Description!");
                return false;
            }
            if (!checkPostButtons()) {
                errorLabel.setText("Please Select A Post Type!");
                return false;
            }
            if (getSelectedDaysBinary() == 0) {
                errorLabel.setText("Please Select At Least One Day!");
                return false;
            }
            if (courseTypeComboBox.getSelectedItem() == null || courseTypeComboBox.getSelectedItem().equals("Select:")) {
                errorLabel.setText("Please Select A Course Type!");
                return false;

            }

            errorLabel.setText(" ");
            return true;
        }

        public boolean checkPostButtons() {
            return postLessonButton.isSelected() || requestLessonButton.isSelected();
        }

    }

    private int getSelectedDaysBinary() {
        int returned = 0;
        if (mondayButton.isSelected())
            returned += 1000000;
        if (tuesdayButton.isSelected())
            returned += 100000;
        if (wednesdayButton.isSelected())
            returned += 10000;
        if (thursdayButton.isSelected())
            returned += 1000;
        if (fridayButton.isSelected())
            returned += 100;
        if (saturdayButton.isSelected())
            returned += 10;
        if (sundayButton.isSelected())
            returned += 1;
        System.out.println(returned);
        return returned;
    }
}
