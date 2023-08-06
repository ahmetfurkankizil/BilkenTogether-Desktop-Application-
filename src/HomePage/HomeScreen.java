package HomePage;

import Icons.IconCreator;
import Posts.LessonPost;
import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class HomeScreen extends JFrame {
    private JPanel mainPanel;
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

    public HomeScreen() {

        postButton.addActionListener(new LesssonPostPostingListener());
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 800);
        currentUser = new Student("Erdem", "erdem.p", 1, "l", "d", "p", "b");
        generalSetup();


        setVisible(true);
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

        postLessonButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        requestLessonButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        setUpDaysButtons();
        for (JButton j :
                sectionButtons) {
            j.setFocusable(false);
            j.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        for (JLabel label :
                leftPanelLabels) {
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
                tempButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tempButton.addActionListener(dayButtonListener);
            }
        }
        clearButton.setFocusable(false);
        clearButton.addActionListener(resetButtonListener);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        postButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        HomeScreen homeScreen = new HomeScreen();
    }

    public boolean requestGiveButtonCheck() {
        return !(postLessonButton.isSelected()) && !requestLessonButton.isSelected();
    }


    private class requestActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            System.out.println(requestGiveButtonCheck());
            if (requestGiveButtonCheck()) {
                b.setSelected(true);
            } else if (b.isSelected()) {
                b.setSelected(false);
            }
        }
    }

    private void addLessonPost() {
        g.gridx = 0;
        //ActivitiesPostViewer viewer2 = new ActivitiesPostViewer(new ActivityPost(2, (Student) currentUser, "a little post des", 3, "20/01/2023", "Concert"));
        //String[] topicColl = {"Algebra","Complex Analysis"};
        //StudiesPostViewer viewer3 = new StudiesPostViewer(new StudyPost(2, (Student) currentUser, "Author", "Very Important Header", " Very L" +makeItLong("OOOOOOOOOOOOO",10) +"NG DESCRIPTION", null,"23:05", topicColl));
        //LessonPostViewer viewer = new LessonPostViewer(new LessonPost(1, (Student) currentUser, "a little post des", "MAth", 11111, true));
        //insideScrollPanePanel.add(viewer, g);
        //insideScrollPanePanel.add(viewer2, g);
        //insideScrollPanePanel.add(viewer3,g);

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

    public  class LesssonPostPostingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (checkIfValid()){
                // int postId = Database.getPostID();
                int postId = 0;
                LessonPost tempPost = new LessonPost(postId,currentUser,textArea1.getText(),(String)courseTypeComboBox.getSelectedItem(),getSelectedDaysBinary(),!postLessonButton.isSelected(),new Date().toString());
                addLessonPost(tempPost);
                System.out.println("printed this many times");
                repaint();
                revalidate();
            }
        }
        private boolean checkIfValid(){
            if (textArea1.getText().isEmpty()){
                errorLabel.setText("Please Enter A Description!");
                return false;
            }if (!checkPostButtons()) {
                errorLabel.setText("Please Select A Post Type!");
                return false;
            }
            if (getSelectedDaysBinary() == 0) {
                errorLabel.setText("Please Select At Least One Day!");
                return false;
            }
            if (courseTypeComboBox.getSelectedItem() == null || courseTypeComboBox.getSelectedItem().equals("Select:")){
                errorLabel.setText("Please Select A Course Type!");
                return false;

            }

            errorLabel.setText(" ");
            return true;
        }
        public boolean checkPostButtons(){
            return postLessonButton.isSelected() || requestLessonButton.isSelected();
        }

    }
    private int getSelectedDaysBinary(){
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
        return  returned;
    }
}
