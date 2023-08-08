package HomePage.StudiesPage;

import HomePage.ActivityPage.ActivitiesPage;
import HomePage.LessonsPage.LessonsPage;
import Icons.IconCreator;
import MessagesGUI.*;
import MessagesRelated.Message;
import NotificationRelated.NotificationHomePage;
import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

public class Main extends JFrame {
    private StudiesPage studies;
    private ActivitiesPage activities;
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
    private JPanel topVisiblisty;
    private JPanel invisibleAddablePanelLeft;
    private JPanel neverOpenCursed;
    private JPanel P;
    private JPanel invisibleAddablePanelRight;
    private JButton sendMessageButton;
    private JTextArea textInputArea;
    private JPanel textAreaPanel;
    private JPanel problematicPanel;
    private ArrayList<JButton> sectionButtons;
    private ArrayList<JLabel> leftPanelLabels;
    private MessagesGUI messagesGUI;

    private boolean messageSendButtonPressed;

    public Main() {
        messageSendButtonPressed = false;
        setUpPages();
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
        messagesLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                topVisiblisty.setVisible(false);
                insideScrollPanePanel.removeAll();
                g.anchor = GridBagConstraints.NORTHWEST;
                flowScrollPane.setVisible(false);
                rightPanel.setVisible(false);
                g.gridx = 0;
                g.gridy = 0;
                JPanel left = messagesGUI.getLeftPanel();
                //left.setMinimumSize(new Dimension(600,800));
                JPanel right = messagesGUI.getRightPanel();
                g.fill = GridBagConstraints.VERTICAL;
                invisibleAddablePanelLeft.add(left,g);
                g.gridx++;
                g.gridheight = 3;
                g.gridy++;
                g.ipady = 50;
                //invisibleAddablePanelLeft.add(new JTextArea(),g);
                g.ipadx = 50;
                g.ipady = 0;
                g.gridy--;
                invisibleAddablePanelRight.add(right,g);
                g.ipadx = 0;
                g.gridheight = 1;
                g.gridy ++;

                invisibleAddablePanelLeft.setVisible(true);
                invisibleAddablePanelRight.setVisible(true);
                textAreaPanel.setVisible(true);
                resetLabelFonts();

                messagesLabel.setFont(new Font("default",Font.BOLD,22));
                g.anchor = GridBagConstraints.NONE;
                repaint();
                revalidate();
            }
        });
        homeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                insideScrollPanePanel.removeAll();
                flowScrollPane.setVisible(true);
                invisibleAddablePanelRight.removeAll();
                invisibleAddablePanelLeft.removeAll();
                textAreaPanel.setVisible(false);
                invisibleAddablePanelLeft.setVisible(false);
                invisibleAddablePanelRight.setVisible(false);
                insideScrollPanePanel.add(lessons.getInsideScrollPanePanel());
                rightPanel.setVisible(true);
                removableRight.removeAll();
                removableRight.add(lessons.getQuickFiltersPanel());
                topVisiblisty.setVisible(true);
                resetLabelFonts();

                homeLabel.setFont(new Font("default",Font.BOLD,22));
                lessonsButton.setSelected(true);
                repaint();
                revalidate();
            }
        });
        notificationsLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                topVisiblisty.setVisible(false);
                insideScrollPanePanel.removeAll();
                resetPanels();

                invisibleAddablePanelRight.add(notificationHomePage.getTopLabel());
                invisibleAddablePanelRight.setVisible(true);
                JPanel tempP = notificationHomePage.getMainPanel();
                insideScrollPanePanel.add(tempP);
                flowScrollPane.setVisible(true);
                insideScrollPanePanel.setVisible(true);
                resetLabelFonts();
                notificationsLabel.setFont(new Font("default",Font.BOLD,22));

                repaint();
                revalidate();

            }
        });
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageSendButtonPressed = true;
                if (!textInputArea.getText().isEmpty()){
                    Message message = new Message(currentUser,null,textInputArea.getText(),new Date());
                    messagesGUI.sendMessage(message);
                }

            }
        });
        client.run();
    }

    private void resetPanels() {
        textAreaPanel.setVisible(false);
        invisibleAddablePanelLeft.setVisible(false);
        invisibleAddablePanelRight.setVisible(false);
        invisibleAddablePanelRight.removeAll();
        invisibleAddablePanelLeft.removeAll();
        removableRight.removeAll();
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

}
