package HomePages.LessonsPage;
import DatabaseRelated.DatabaseConnection;
import HomePages.HomeMain.HomeMain;
import HomePages.HomeMain.MainInterface;
import Other.Icons.IconCreator;
import Posts.ActivityPost;
import PostsGUI.LessonPostViewer;
import Posts.LessonPost;
import SignupAndLogin.LoginFrame;
import TrialMain.TrialMain;
import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class LessonsPage {
    private MainInterface main;
    private static final Cursor HANDCURSOR = new Cursor(Cursor.HAND_CURSOR);

    public static final Color PRIMARYBUTTONCOLOR = new Color(181, 181, 234);
    public static final Color SECONDARYBUTTONCOLOR = new Color(141,141,154);
    private ArrayList<Integer> allUsers;
    public static final int NUMOFPOSTSINAPAGE = 20;

    private JPanel mainPanel;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    public static final ImageIcon back = IconCreator.getIconWithSize(IconCreator.backIcon, 30, 30);
    private User currentUser;
    private JButton lessonsButton;
    private ArrayList<LessonPostViewer> lessonPostViewers;
    private JButton studiesButton;
    private JButton activitiesButton;
    private JButton profileBoxButton;
    private ArrayList<LessonPost> posts;
    private ArrayList<Integer> userIds;
    private JButton filterBoxButton;
    private PPImageHandler profilePhoto;
    private GridBagConstraints g;
    private JPanel rightPanel;
    private JPanel middlePanel;
    private JLabel bilkenTogetherLabel;
    private JScrollPane flowScrollPane;
    private JPanel insideScrollPanePanel;
    private JButton postLessonButton;
    private JButton requestLessonButton;
    private JPanel postingPanel;
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
    private JPanel postingButtonPanel;
    private JPanel profilePhotoPanel;
    private JPanel daysPanel;
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
    private JLabel filterErrorLabel;
    private ArrayList<JButton> sectionButtons;
    private ArrayList<JButton> dayButtons;
    private JButton[] filterDayButtons = {mondayFilterButton,TuesdayFilterButton,WednesdayFilterButton,thursdayFilterButton,fridayFilterButton,saturdayFilterButton,sundayFilterButton};

    private boolean isSubmitted = false;
    public JPanel getInsideScrollPanePanel() {
        return insideScrollPanePanel;
    }
    public JPanel getQuickFiltersPanel() {
        return quickFiltersPanel;
    }
    public LessonsPage(MainInterface main) {
        posts = new ArrayList<>();
        userIds = new ArrayList<>();
        this.main = main;
        currentUser = main.getCurrentUser();
        lessonPostViewers = new ArrayList<>();
        generalSetup();
        if (!(this.main instanceof TrialMain))
            getRandomPosts();
        else
            getRandomPosts(true);
        textArea1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                main.update();
            }
        });
        filtersSubmitButton.addActionListener(new FilterSubmitListener());
    }

    private int totalNumOfPosts(ArrayList<ArrayList< LessonPost >> userPostCollections){
        int total = 0;
        for (ArrayList<LessonPost> userPostCollection : userPostCollections) {
            total += userPostCollection.size();
        }
        return total;
    }
    private void getRandomPosts() {
        DatabaseConnection c = new DatabaseConnection();
        allUsers = currentUser.pullIDsFromUserInformationTable();
        ArrayList<ArrayList<LessonPost>> userPostCollections = new ArrayList<>();

        boolean exceed = false;
        for (int i = 0; i < allUsers.size(); i++) {
            userPostCollections.add(c.pullUserByIdFromDB(allUsers.get(i)).pullFromLessonsPostTable());
        }
        int max1 = userPostCollections.size();
        int rand1;
        int max2;
        int rand2;
        Random rand = new Random();
        for (int i = 0; i < LessonsPage.NUMOFPOSTSINAPAGE; i++) {
            rand1 = 0;
            rand2 = 0;
            if (max1 != 0)
                rand1= rand.nextInt(max1);
            max2= userPostCollections.get(rand1).size();
            if (max2 != 0)
                rand2= rand.nextInt(max2);
            if (!userPostCollections.get(rand1).isEmpty() && !posts.contains(userPostCollections.get(rand1).get(rand2))){
                addLessonPost(userPostCollections.get(rand1).get(rand2));
                posts.add(userPostCollections.get(rand1).get(rand2));
            }else if (exceed){
                i--;
            }
        }


    }
    private void getRandomPosts(boolean isItTrial) {
        TrialMain trial = (TrialMain) main;
        User[] allUsers2 = trial.getAllUsers();
        ArrayList<ArrayList<LessonPost>> userPostCollections = new ArrayList<>();
        int totalNum = totalNumOfPosts(userPostCollections);
        boolean exceed = true;
        if (totalNum < LessonsPage.NUMOFPOSTSINAPAGE)
            exceed = false;

        for (int i = 0; i < allUsers2.length; i++) {
            User temp13 = allUsers2[i];
            if (temp13 instanceof Student student)
                userPostCollections.add(student.getLessonPostCollection());
        }
        int max1 = userPostCollections.size();
        int rand1;
        int max2;
        int rand2;
        Random rand = new Random();
        for (int i = 0; i < LessonsPage.NUMOFPOSTSINAPAGE; i++) {
            rand1 = 0;
            rand2 = 0;
            if (max1 != 0)
                rand1= rand.nextInt(max1);
            max2= userPostCollections.get(rand1).size();
            if (max2 != 0)
                rand2= rand.nextInt(max2);
            if (!userPostCollections.get(rand1).isEmpty() && !posts.contains(userPostCollections.get(rand1).get(rand2))){
                addLessonPost(userPostCollections.get(rand1).get(rand2));
            }else if (exceed){
                i--;
            }
        }
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
    }
    private void setUpProfilePhoto() {
        errorLabel.setForeground(Color.red);
        errorLabel.setText(" ");
        profilePhoto = new PPImageHandler(currentUser);
        profilePhotoPanel.add(profilePhoto);
    }
    private void setUpActionListeners() {
        postButton.addActionListener(new LesssonPostPostingListener());
        postLessonButton.addActionListener(new requestActionListener());
        requestLessonButton.addActionListener(new requestActionListener());
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton but = (JButton) e.getSource();
                but.setSelected(!but.isSelected());
                but.setBackground(but.isSelected() ? SECONDARYBUTTONCOLOR : PRIMARYBUTTONCOLOR);
            }
        };

        mondayFilterButton.addActionListener(listener);
        TuesdayFilterButton.addActionListener(listener);
        WednesdayFilterButton.addActionListener(listener);
        fridayFilterButton.addActionListener(listener);
        thursdayFilterButton.addActionListener(listener);
        saturdayFilterButton.addActionListener(listener);
        sundayFilterButton.addActionListener(listener);
        givenButton.addActionListener(listener);
        requestedButton.addActionListener(listener);
        filterBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lessonsQFpanel.isVisible())
                    lessonsQFpanel.setVisible(false);
                else
                    lessonsQFpanel.setVisible(true);
            }
        });

    }
    public static void makeButtonMoreBeautiful(JButton button,Color primary){
        button.setCursor(HANDCURSOR);
        button.setFocusable(false);
        button.setBorder(new EmptyBorder(5,5,5,5));
        button.setOpaque(true);
        button.setBackground(primary);
    }
    public static void makeButtonMoreBeautiful(JButton button){
        button.setCursor(HANDCURSOR);
        button.setFocusable(false);
        button.setBorder(new EmptyBorder(5,5,5,5));
        button.setBackground(PRIMARYBUTTONCOLOR);
        button.setOpaque(true);
    }
    private void setUpCursors() {
        makeButtonMoreBeautiful(postLessonButton);
        makeButtonMoreBeautiful(requestLessonButton);
        makeButtonMoreBeautiful(filterBoxButton);
        makeButtonMoreBeautiful(filtersSubmitButton);
        makeButtonMoreBeautiful(mondayFilterButton);
        makeButtonMoreBeautiful(saturdayFilterButton);
        makeButtonMoreBeautiful(sundayFilterButton);
        makeButtonMoreBeautiful(TuesdayFilterButton);
        makeButtonMoreBeautiful(WednesdayFilterButton);
        makeButtonMoreBeautiful(thursdayFilterButton);
        makeButtonMoreBeautiful(fridayFilterButton);
        makeButtonMoreBeautiful(givenButton);
        makeButtonMoreBeautiful(requestedButton);
        makeButtonMoreBeautiful(clearButton,new Color(210, 138, 138));
        makeButtonMoreBeautiful(postButton,new Color(56, 161, 199));
        filterErrorLabel.setForeground(Color.red);
        setUpArrayLists();
        setUpDaysButtons();
        for (JButton j :
                sectionButtons) {
            makeButtonMoreBeautiful(j);
        }


    }
    private void setUpArrayLists() {
        sectionButtons = new ArrayList<>();
        sectionButtons.add(lessonsButton);
        sectionButtons.add(activitiesButton);
        sectionButtons.add(studiesButton);
    }
    private void setUpDaysButtons() {
        dayButtons = new ArrayList<>();
        ActionListener dayButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                b.setSelected(!b.isSelected());
                b.setBackground(b.isSelected() ? SECONDARYBUTTONCOLOR : PRIMARYBUTTONCOLOR);
            }
        };
        ActionListener resetButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton button :
                        dayButtons) {
                    button.setSelected(false);
                    button.setBackground(PRIMARYBUTTONCOLOR);
                }
                courseComboBox.setSelectedItem(0);
                postLessonButton.setSelected(false);
                postLessonButton.setBackground(PRIMARYBUTTONCOLOR);
                requestLessonButton.setSelected(false);
                requestLessonButton.setBackground(PRIMARYBUTTONCOLOR);
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
                makeButtonMoreBeautiful(tempButton);
            }
        }
        clearButton.setFocusable(false);
        clearButton.addActionListener(resetButtonListener);
        clearButton.setCursor(handCursor);
        postButton.setCursor(handCursor);
        postButton.setFocusable(false);
    }
    public boolean requestGiveButtonCheck() {
        return !(postLessonButton.isSelected()) && !requestLessonButton.isSelected();
    }
    public boolean requestGiveButtonCheckFilter() {
        return !(givenButton.isSelected()) && !requestedButton.isSelected();
    }
    public void setMain(HomeMain main) {
        this.main =main;
        setCurrentUser(main.getCurrentUser());
    }

    public void refreshProfilePhotos() {
        profilePhotoPanel.removeAll();
        profilePhoto = new PPImageHandler(currentUser);
        profilePhotoPanel.add(profilePhoto);
        profilePhotoPanel.revalidate();
        profilePhotoPanel.repaint();

    }

    private class requestActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if (b.isSelected()) {
                b.setSelected(false);
                b.setBackground(PRIMARYBUTTONCOLOR);
            }else{
                postLessonButton.setSelected(false);
                requestLessonButton.setSelected(false);
                postLessonButton.setBackground(PRIMARYBUTTONCOLOR);
                requestLessonButton.setBackground(PRIMARYBUTTONCOLOR);
                b.setSelected(true);
                b.setBackground(SECONDARYBUTTONCOLOR);
            }
        }
    }
    public void addLessonPost(LessonPost post) {
        g.gridx = 0;
        LessonPostViewer viewer = new LessonPostViewer(post,main);
        lessonPostViewers.add(viewer);
        insideScrollPanePanel.add(viewer, g);
    }
    public class LesssonPostPostingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (checkIfValid()) {
                // int postId = Database.getNewPostID();
                int postId = 0;

                LessonPost tempPost = new LessonPost(postId, currentUser, textArea1.getText().strip(), (String) courseTypeComboBox.getSelectedItem(), getSelectedDaysBinary(), !postLessonButton.isSelected(), new Date().toString(),true);
                Student temp = (Student) currentUser;
                if (!LoginFrame.isTrial)
                    temp.addToLessonsTable(tempPost);
                posts.add(tempPost);
                addLessonPost(tempPost);
                main.update();
            }

        }

        private boolean checkIfValid() {
            if (textArea1.getText().isBlank()) {
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
        return returned;
    }
    private void filterLessons(String selectedValue){
        for (int i = 0; i < lessonPostViewers.size(); i++) {
            if (!lessonPostViewers.get(i).getPost().getTypeFilter().equals(selectedValue)){
                lessonPostViewers.get(i).setVisible(false);
            }
        }
            insideScrollPanePanel.revalidate();
            insideScrollPanePanel.repaint();
            main.update();


    }
        private class FilterSubmitListener implements ActionListener{
            boolean[] filterDays;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!filterCheck()) {
                    filterErrorLabel.setText(" ");
                    refreshPosts();
                    filterWithRequestType();
                    if (!Objects.equals(courseComboBox.getSelectedItem(), "Select:")) {
                        filterLessons((String) courseComboBox.getSelectedItem());
                    } else if (!Objects.equals(artAndSportComboBox.getSelectedItem(), "Select:")) {
                        filterLessons((String) artAndSportComboBox.getSelectedItem());
                    }
                    if (isDayFilterSelected()){
                        for (LessonPostViewer viewer : lessonPostViewers) {
                            if (!viewer.getPost().matchesFilter(filterDays)){
                                viewer.setVisible(false);
                            }
                        }
                    }
                    main.update();
                }
                else {
                    filterErrorLabel.setText("Invalid! Choose Art&Sports OR a Course!");
                }
            }

            private boolean isDayFilterSelected() {
                filterDays = new boolean[7];
                boolean oneIsSelected = false;
                for (int i = 0; i < filterDayButtons.length; i++) {
                    filterDays[i] = filterDayButtons[i].isSelected();
                    if (filterDays[i])
                        oneIsSelected = true;
                }
                return oneIsSelected;
            }

            private boolean filterCheck(){
                return !Objects.equals(courseComboBox.getSelectedItem(), "Select:")&&!Objects.equals(artAndSportComboBox.getSelectedItem(), "Select:");
            }
            private void filterWithRequestType() {
                if (givenButton.isSelected() || requestedButton.isSelected()){
                    for (LessonPostViewer lessonPostViewer : lessonPostViewers) {
                        char posType = lessonPostViewer.getPost().getRequestType() ? 'R' : 'G';
                        char filType = requestedButton.isSelected() ? 'R' : 'G';
                        if (posType != filType)
                            lessonPostViewer.setVisible(false);
                    }
                }

            }
            private void refreshPosts(){
                for (LessonPostViewer lessonPostViewer : lessonPostViewers) {
                    lessonPostViewer.setVisible(true);
                }
            }
        }



}


