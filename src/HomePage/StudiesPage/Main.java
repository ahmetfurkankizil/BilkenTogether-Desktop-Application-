package HomePage.StudiesPage;

import HomePage.ActivityPage.ActivitiesPage;
import HomePage.LessonsPage.LessonsPage;
import Icons.IconCreator;
import MessagesGUI.*;
import NotificationRelated.NotificationHomePage;
import Request.RequestMidPanel;
import UserProfileGUI.PPImageHandler;
import UserProfileGUI.UserProfilePage;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class Main extends JFrame {
    private StudiesPage studies;
    private ActivitiesPage activities;
    private static final File LOGFILE= new File("src/HomePage/StudiesPage/logo.PNG");
    private static final ImageIcon  LOGO = IconCreator.getIconWithSize(new ImageIcon(LOGFILE.getAbsolutePath()),60,60);;



    NotificationHomePage notificationHomePage;
    private LessonsPage lessons;
    private JPanel mainPanel;
    private Client client;
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
    private UserProfilePage profilePage;
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
    private JPanel topVisiblisty;
    private JPanel invisibleAddablePanelLeft;
    private JPanel neverOpenCursed;
    private JPanel P;
    private JPanel parentP;
    private JPanel problematic;
    private JPanel invisibleAddablePanelRight;
    private JButton sendMessageButton;
    private JTextArea textInputArea;
    private JPanel textAreaPanel;
    private JPanel bPanel;
    private ArrayList<JButton> sectionButtons;
    private ArrayList<JLabel> leftPanelLabels;
    private MessagesGUI messagesGUI;

    private boolean messageSendButtonPressed;
    private RequestMidPanel requestsPage;

    public Main() {

        messageSendButtonPressed = false;
        setUpPages();
        logoLabel.setIcon(LOGO);
        client = new Client(messagesGUI.getConversationPanel(),this);
        setContentPane(mainPanel);
        insideScrollPanePanel.add(lessons.getInsideScrollPanePanel());
        removableRight.add(lessons.getQuickFiltersPanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        homeLabel.setFont(new Font("default",Font.BOLD,22));
        lessonsButton.setSelected(true);
        setSize(1500, 800);
        currentUser = new Student("Erdem", "erdem.p", 22203112, "l", "d", "p", "b");
        generalSetup();
        setUpLabelListeners();

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

        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageSendButtonPressed = true;
                if (!textInputArea.getText().isEmpty()){

                    messagesGUI.sendMessage(currentUser,textInputArea.getText());
                }
                revalidate();
                repaint();
            }
        });
        client.run();
    }

    private void setUpLabelListeners() {
        messagesLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkIfLabelAlreadySelected(e)){
                resetPanels();
                GridBagConstraints g2 = new GridBagConstraints();
                //g2.anchor = GridBagConstraints.NORTHWEST;
                //g2.gridx = 0;
                //g2.gridy = 0;
                JPanel left = messagesGUI.getLeftPanel();
                JPanel right = messagesGUI.getRightPanel();
                //g2.fill = GridBagConstraints.VERTICAL;
                invisibleAddablePanelLeft.add(left,g);
                setUpRightPanelLayout();
                right.setBounds(0,0,640,600);
                invisibleAddablePanelRight.add(right);
                rightPanel.setVisible(false);
                invisibleAddablePanelLeft.setVisible(true);
                invisibleAddablePanelRight.setVisible(true);
                textAreaPanel.setVisible(true);
                resetLabelFonts();
                messagesLabel.setFont(new Font("default",Font.BOLD,22));
                update();}
            }
        });
        homeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkIfLabelAlreadySelected(e)){
                resetPanels();
                insideScrollPanePanel.removeAll();
                flowScrollPane.setVisible(true);
                insideScrollPanePanel.add(lessons.getInsideScrollPanePanel());
                rightPanel.setVisible(true);
                removableRight.add(lessons.getQuickFiltersPanel());
                topVisiblisty.setVisible(true);
                resetLabelFonts();
                homeLabel.setFont(new Font("default",Font.BOLD,22));
                lessonsButton.setSelected(true);
                update();}
            }
        });
        profileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkIfLabelAlreadySelected(e)){
                resetPanels();
                GridBagConstraints g2 = new GridBagConstraints();
                invisibleAddablePanelLeft.add(profilePage.getInPanel(),g2);
                rightPanel.setVisible(true);
                invisibleAddablePanelLeft.setVisible(true);
                resetLabelFonts();
                profileLabel.setFont(new Font("default",Font.BOLD,22));
                update();}
            }
        });
        requestsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkIfLabelAlreadySelected(e)){
                resetPanels();
                GridBagConstraints g2 = new GridBagConstraints();

                g2.ipady = 800;

                invisibleAddablePanelLeft.add(requestsPage.getInPanel(),g2);
                invisibleAddablePanelLeft.setVisible(true);
                rightPanel.setVisible(true);
                resetLabelFonts();
                requestsLabel.setFont(new Font("default",Font.BOLD,22));
                update();}
            }
        });
        notificationsLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkIfLabelAlreadySelected(e)){
                resetPanels();
                invisibleAddablePanelLeft.add(notificationHomePage.getTopLabel());
                invisibleAddablePanelLeft.setVisible(true);
                JPanel tempP = notificationHomePage.getMainPanel();
                insideScrollPanePanel.add(tempP);
                insideScrollPanePanel.setVisible(true);
                flowScrollPane.setVisible(true);
                rightPanel.setVisible(true);
                resetLabelFonts();
                notificationsLabel.setFont(new Font("default",Font.BOLD,22));
                update();}


            }
        });

    }


    public void update(){
        repaint();
        revalidate();
    }


    private void resetPanels() {
        textAreaPanel.setVisible(false);
        topVisiblisty.setVisible(false);
        invisibleAddablePanelLeft.setVisible(false);
        invisibleAddablePanelRight.setVisible(false);
        invisibleAddablePanelRight.removeAll();
        invisibleAddablePanelLeft.removeAll();
        removableRight.removeAll();
        insideScrollPanePanel.removeAll();
        flowScrollPane.setVisible(false);
    }

    private void resetLabelFonts() {
        homeLabel.setFont(new Font("default",Font.PLAIN,22));
        notificationsLabel.setFont(new Font("default",Font.PLAIN,22));
        messagesLabel.setFont(new Font("default",Font.PLAIN,22));
        requestsLabel.setFont(new Font("default",Font.PLAIN,22));
        profileLabel.setFont(new Font("default",Font.PLAIN,22));
    }

    private void setUpPages() {
        activities = new ActivitiesPage();
        activities.setMain(this);
        studies = new StudiesPage();
        studies.setMain(this);
        lessons = new LessonsPage();
        lessons.setMain(this);
        messagesGUI = new MessagesGUI();
        messagesGUI.setMain(this);
        notificationHomePage = new NotificationHomePage();
        profilePage = new UserProfilePage();
        profilePage.setMain(this);
        requestsPage = new RequestMidPanel();
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

    public String getTextFieldText() {
        return textInputArea.getText();
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
    public boolean getButtonPressed(){
        return messageSendButtonPressed;
    }
    public void setButtonPressed(boolean b){
        messageSendButtonPressed = b;
    }
    private void setUpRightPanelLayout() {
        invisibleAddablePanelRight.setLayout(new LayoutManager() {
            @Override
            public void addLayoutComponent(String name, Component comp) {

            }

            @Override
            public void removeLayoutComponent(Component comp) {

            }

            @Override
            public Dimension preferredLayoutSize(Container parent) {
                return new Dimension(600,600);
            }

            @Override
            public Dimension minimumLayoutSize(Container parent) {
                return null;
            }

            @Override
            public void layoutContainer(Container parent) {

            }
        });
    }
    private boolean checkIfLabelAlreadySelected(MouseEvent e){
        JLabel label = (JLabel) e.getSource();
        return !label.getFont().isBold();
    }
}
