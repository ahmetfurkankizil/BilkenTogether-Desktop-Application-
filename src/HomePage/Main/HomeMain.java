package HomePage.Main;

import CommentsGUI.CommentsMidPanel;
import DatabaseRelated.DatabaseConnection;
import HomePage.ActivityPage.ActivitiesPage;
import HomePage.LessonsPage.LessonsPage;
import HomePage.StudiesPage.StudiesPage;
import Icons.IconCreator;
import MessagesGUI.Client;
import MessagesGUI.MessageConnection;
import MessagesGUI.MessagesGUI;
import MessagesGUI.Server;
import MessagesRelated.Message;
import NotificationRelated.NotificationHomePage;
import PostComponents.ActivitiesPostViewer;
import PostComponents.LessonPostViewer;
import PostComponents.PostViewer;
import Posts.Post;
import Posts.RequestablePost;
import ProfileBox.ProfileBox;
import Request.RequestMidPanel;
import Request.RequestsAndViewers.RequestMiddlePanelUnanswered;
import SignupAndLogin.LoginFrame;
import SignupAndLogin.SignUpHandler;
import UserProfileGUI.PPImageHandler;
import UserProfileGUI.UserProfilePage;
import UserRelated.Student;
import UserRelated.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class HomeMain extends JFrame {
    private StudiesPage studies;
    private ActivitiesPage activities;
    private static final File LOGFILE= new File("src/HomePage/Main/logo.PNG");
    private static final ImageIcon  LOGO = IconCreator.getIconWithSize(new ImageIcon(LOGFILE.getAbsolutePath()),60,60);;
    private NotificationHomePage notificationHomePage;
    private LessonsPage lessons;
    private JPanel mainPanel;
    private Client client;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    public static final ImageIcon back = IconCreator.getIconWithSize(IconCreator.backIcon, 30, 30);
    private User currentUser;
    private JButton lessonsButton;
    private JButton studiesButton;
    private JButton activitiesButton;
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
    private JPanel profileBoxPanel;
    private JPanel rightAndMiddle;
    private ArrayList<JButton> sectionButtons;
    private ArrayList<JLabel> leftPanelLabels;
    private MessagesGUI messagesGUI;
    private boolean canRate;
    private boolean messageSendButtonPressed;
    private RequestMidPanel requestsPage;
    private Server server;
    private ProfileBox profileBox;

    public HomeMain(User user) {
        currentUser = user;//new Student("Erdem Pülat", "erdem.pulat@ug.bilkent.edu.tr", 22103566, "l", "d", "p", "b",null,null,true);
        //Adding profile photo (photo to byte)
        //ppHandler();
        //setUpPastMessages();
        canRate = false;
        messageSendButtonPressed = false;
        resetLabelFonts();
        profileBox = new ProfileBox(currentUser);
        profileBoxPanel.add(profileBox);
        setUpPages();
        logoLabel.setIcon(LOGO);
        //server = new Server(22);

        client = new Client(messagesGUI.getConversationPanel(),this,server);

        setContentPane(mainPanel);
        if (currentUser instanceof Student){
        insideScrollPanePanel.add(lessons.getInsideScrollPanePanel());
        removableRight.add(lessons.getQuickFiltersPanel());}
        else {
            insideScrollPanePanel.add(studies.getInsideScrollPanePanel());
            removableRight.add(studies.getQfPanel());
            requestsLabel.setVisible(false);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        resetLabelFonts();
        homeLabel.setFont(new Font("default",Font.BOLD,22));
        lessonsButton.setSelected(true);
        setSize(1500, 800);
        generalSetup();

        setUpLabelListeners();
        //LessonPost tempPost = new LessonPost(8, currentUser, "textArea1.getText().strip()", "(String) courseTypeComboBox.getSelectedItem()", 1, true, new Date().toString());
        //lessons.addLessonPost(tempPost);
        //tempPost.addComment(new Comment(currentUser,"lol so cool"));
        goTop();
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
                            goTop();
                            break;
                        case 's':
                            insideScrollPanePanel.removeAll();
                            insideScrollPanePanel.add(studies.getInsideScrollPanePanel());
                            removableRight.removeAll();
                            removableRight.add(studies.getQfPanel());
                            goTop();
                            break;
                        case 'l':
                            insideScrollPanePanel.removeAll();
                            insideScrollPanePanel.add(lessons.getInsideScrollPanePanel());
                            removableRight.removeAll();
                            removableRight.add(lessons.getQuickFiltersPanel());
                            goTop();
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
        studiesButton.addActionListener(sectionButtonListener);

        if (currentUser instanceof Student) {
            lessonsButton.addActionListener(sectionButtonListener);

            activitiesButton.addActionListener(sectionButtonListener);
        }else {
            lessonsButton.setVisible(false);
            activitiesButton.setVisible(false);
        }
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textInputArea.getText().isEmpty()){
                    client.setCurrentRecipient(messagesGUI.getCurrentReceiver());
                    messageSendButtonPressed = true;

                    messagesGUI.sendMessage(currentUser,messagesGUI.getCurrentReceiver(),textInputArea.getText());

                    messagesGUI.refreshLeft();
                }
               update();
            }
        });
        client.run();
        logOutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(JOptionPane.showConfirmDialog(new JFrame(),"Are You Sure To Log Out?") == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    dispose();
                    SignUpHandler sh = new SignUpHandler();
                    LoginFrame loginFrame = new LoginFrame(sh);
                    loginFrame.setTitle("BilkenTogether");
                    loginFrame.setSize(500,300);
                    loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    loginFrame.setVisible(true);
                }
            }
        });
    }

    private void goTop() {
        JScrollBar verBar = flowScrollPane.getVerticalScrollBar();
        verBar.setValue(0);
    }

    private void ppHandler() {
        BufferedImage bi = null;
        File f = new File("src/ProfilePictureTester/Tatice-Cristal-Intense-Java.64.png");
        try {
            bi = ImageIO.read(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi,"png",os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = os.toByteArray();
        currentUser.setProfilePhoto(bytes,false);

        // Adding Background Photo (photo to byte[])
        BufferedImage ib = null;
        try {
            ib = ImageIO.read( new File("src/ProfilePictureTester/trava-pole-polya-kholmy-nebo-oblako-oblaka.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayOutputStream so = new ByteArrayOutputStream();
        try {
            ImageIO.write(ib,"png",so);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes1 = so.toByteArray();
        currentUser.setBackgroundPhoto(bytes1,false);

    }

    private void setUpLabelListeners() {
        messagesLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkIfLabelAlreadySelected(e)){
                resetPanels();
                GridBagConstraints g2 = new GridBagConstraints();
                    messagesGUI.refreshLeft();
                JPanel left = messagesGUI.getLeftPanel();
                JPanel right = messagesGUI.getRightPanel();
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
                if (currentUser instanceof Student){
                        insideScrollPanePanel.add(lessons.getInsideScrollPanePanel());
                        removableRight.add(lessons.getQuickFiltersPanel());}
                else {
                        insideScrollPanePanel.add(studies.getInsideScrollPanePanel());
                        removableRight.add(studies.getQfPanel());
                }
                insideScrollPanePanel.setVisible(true);
                rightPanel.setVisible(true);
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
                profilePage.setUpHistory();
                profileLabel.setFont(new Font("default",Font.BOLD,22));
                update();}
            }
        });
        if (currentUser instanceof Student){
        requestsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkIfLabelAlreadySelected(e)){
                    resetPanels();
                    GridBagConstraints g2 = new GridBagConstraints();


                    g2.anchor = GridBagConstraints.NORTHWEST;
                    g2.gridx = 0;
                    g2.gridy = 0;
                    invisibleAddablePanelLeft.add(requestsPage.getInPanel(),g2);
                    invisibleAddablePanelLeft.setVisible(true);
                    rightPanel.setVisible(true);
                    resetLabelFonts();
                    requestsLabel.setFont(new Font("default",Font.BOLD,22));
                    update();
                }
            }
        });}
        profileBoxPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!profileLabel.getFont().isBold()){
                    resetPanels();
                    GridBagConstraints g2 = new GridBagConstraints();
                    invisibleAddablePanelLeft.add(profilePage.getInPanel(),g2);
                    rightPanel.setVisible(true);
                    invisibleAddablePanelLeft.setVisible(true);
                    resetLabelFonts();
                    profileLabel.setFont(new Font("default",Font.BOLD,22));
                    profilePage.setUpHistory();
                    update();}
            }
        });
        profileBoxPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        notificationsLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkIfLabelAlreadySelected(e)){
                resetPanels();
                invisibleAddablePanelLeft.add(notificationHomePage.getTopLabel());
                invisibleAddablePanelLeft.setVisible(true);
                JPanel tempP = notificationHomePage.getMainPanel();
                GridBagConstraints g2 = new GridBagConstraints();
                //tempP.setBounds(0,0,600,600);
                g2.gridx = 0;
                notificationHomePage.addPastNotifications(currentUser);
                invisibleAddablePanelLeft.add(tempP,g2);
                //invisibleAddablePanelRight.setVisible(true);
                //flowScrollPane.setVisible(true);
                rightPanel.setVisible(true);
                resetLabelFonts();
                notificationsLabel.setFont(new Font("default",Font.BOLD,22));
                update();
                }
            }});

    }


    public void update(){
        repaint();
        revalidate();
    }


    private void resetPanels() {
        invisibleAddablePanelLeft.setLayout(new GridBagLayout());
        textAreaPanel.setVisible(false);
        topVisiblisty.setVisible(false);
        invisibleAddablePanelLeft.setVisible(false);
        invisibleAddablePanelRight.setVisible(false);
        invisibleAddablePanelRight.removeAll();
        invisibleAddablePanelLeft.removeAll();
        removableRight.removeAll();
        insideScrollPanePanel.removeAll();
        flowScrollPane.setVisible(false);
        goTop();
    }

    private void resetLabelFonts() {
        homeLabel.setFont(new Font("default",Font.PLAIN,22));
        notificationsLabel.setFont(new Font("default",Font.PLAIN,22));
        messagesLabel.setFont(new Font("default",Font.PLAIN,22));
        requestsLabel.setFont(new Font("default",Font.PLAIN,22));
        profileLabel.setFont(new Font("default",Font.PLAIN,22));
    }
    private RequestMiddlePanelUnanswered requestExtended;
    private void setUpPages() {
        setUpPastMessages();
        if (currentUser instanceof Student) {
            activities = new ActivitiesPage(this);
            lessons = new LessonsPage(this);
            requestsPage = new RequestMidPanel(this);
        }
        notificationHomePage = new NotificationHomePage(this);
        messagesGUI = new MessagesGUI(this);
        studies = new StudiesPage(this);
        profilePage = new UserProfilePage(this, profileBox);
    }
    public void setUpPastMessages(){
        ArrayList<Integer> otherUsers = currentUser.pullIDsFromUserInformationTable();
        DatabaseConnection tempConnection = new DatabaseConnection();
        currentUser.resetMessageConnections();
        for (int i = 0; i < otherUsers.size(); i++) {
            if (otherUsers.get(i) != currentUser.getId()){
                MessageConnection temp = new MessageConnection(currentUser, tempConnection.pullUserByIdFromDB(otherUsers.get(i)),22 , false);
                ArrayList<Message> messages = currentUser.pullMessageHistoryFromDB(otherUsers.get(i) +currentUser.getId());
                for (int j = 0; j < messages.size(); j++) {
                    temp.addMessages(messages.get(j),false);
                }
                currentUser.addMessageConnection(temp);
            }
        }
    }
    public void refreshProfilePhotos(){
        if (currentUser instanceof Student){
            lessons.refreshProfilePhotos();
            activities.refreshProfilePhotos();

        }
        studies.refreshProfilePhotos();

    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void generalSetup() {
        g = new GridBagConstraints();
        setUpCursors();
        setUpSectionLabels();
    }

    public static byte[] readPDFToByteArray(String filePath) throws IOException {
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            fileInputStream = new FileInputStream(filePath);
            byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            return byteArrayOutputStream.toByteArray();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
    }


    private void setUpCursors() {
        logoLabel.setCursor(handCursor);
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
        homeLabel.setIcon(IconCreator.getIconWithSize(IconCreator.houseIcon, secPanelIconWidth, (int) (secPanelIconWidth * .88)));

        if (currentUser instanceof Student){
        requestsLabel.setIcon(IconCreator.getIconWithSize(IconCreator.requestSecIcon, secPanelIconWidth, (int) (secPanelIconWidth * .88)));
        setUpBorders();
        }
    }

    private void setUpBorders() {
        homeLabelPanel.setBorder(new SectionItemBorder());
        requestLabelPanel.setBorder(new SectionItemBorder());
        profileLabelPanel.setBorder(new SectionItemBorder());
        messagesLabelPanel.setBorder(new SectionItemBorder());
        notificationsLabelPanel.setBorder(new SectionItemBorder());
    }
    public void goBack(PostViewer viewer){

        resetPanels();
        insideScrollPanePanel.setVisible(true);
        flowScrollPane.setVisible(true);
        topVisiblisty.setVisible(true);
        String section = "";
        if (viewer instanceof LessonPostViewer)
            section = "lessons";
        else if (viewer instanceof ActivitiesPostViewer)
            section = "activities";
        else
            section = "studies";
        switch (section.toLowerCase().charAt(0)) {
                case 'a':
                    goTop();
                    insideScrollPanePanel.removeAll();
                    insideScrollPanePanel.add(activities.getInsideScrollPanePanel());
                    removableRight.removeAll();
                    removableRight.add(activities.getQuickFiltersPanel());
                    break;
                case 's':
                    goTop();
                    insideScrollPanePanel.removeAll();
                    insideScrollPanePanel.add(studies.getInsideScrollPanePanel());
                    removableRight.removeAll();
                    removableRight.add(studies.getQfPanel());
                    break;
                case 'l':
                    goTop();
                    insideScrollPanePanel.removeAll();
                    insideScrollPanePanel.add(lessons.getInsideScrollPanePanel());
                    removableRight.removeAll();
                    removableRight.add(lessons.getQuickFiltersPanel());
                    break;
        }
    }
    public void expandPost(PostViewer p){
        Post tempPost = p.getPost();
        JPanel prev = (JPanel) insideScrollPanePanel.getComponents()[0];

        CommentsMidPanel tempPanel = null;
        tempPanel = new CommentsMidPanel(tempPost,this,prev);


        resetPanels();
        invisibleAddablePanelLeft.setLayout(new GridLayout());
        invisibleAddablePanelLeft.add(tempPanel.getInnerPanel());
        invisibleAddablePanelLeft.setVisible(true);
        update();

    }



    public static void main(String[] args) {
        //Main lessonsPage = new Main();
    }

    public String getTextFieldText() {
        return textInputArea.getText();
    }

    public User getCurrentUser() {

        return currentUser;
    }
    public void goBacTokRequests(){
        resetPanels();
        GridBagConstraints g2 = new GridBagConstraints();
        g2.anchor = GridBagConstraints.NORTHWEST;
        g2.gridx = 0;
        g2.gridy = 0;
        invisibleAddablePanelLeft.add(requestsPage.getInPanel(),g2);
        invisibleAddablePanelLeft.setVisible(true);
        rightPanel.setVisible(true);
        resetLabelFonts();
        requestsLabel.setFont(new Font("default",Font.BOLD,22));
        update();
        update();
    }
    public void extendRequest(RequestablePost panel) {
        requestExtended = new RequestMiddlePanelUnanswered(panel,this, requestsPage.getInPanel());
        resetPanels();
        GridBagConstraints g2 = new GridBagConstraints();


        g2.anchor = GridBagConstraints.NORTHWEST;
        g2.gridx = 0;
        g2.gridy = 0;
        invisibleAddablePanelLeft.add(requestExtended.getMiddlePanel(),g2);
        invisibleAddablePanelLeft.setVisible(true);
        rightPanel.setVisible(true);
        resetLabelFonts();
        requestsLabel.setFont(new Font("default",Font.BOLD,22));
        resetLabelFonts();
        update();}
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


