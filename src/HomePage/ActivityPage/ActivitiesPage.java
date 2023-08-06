package HomePage.ActivityPage;

import HomePage.LessonsPage.ActivitiesPostViewer;
import HomePage.LessonsPage.LessonsPage;
import HomePage.StudiesPage.Main;
import HomePage.StudiesPage.StudiesPage;
import Posts.ActivityPost;
import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ActivitiesPage extends JFrame {
    private StudiesPage studies;
    private LessonsPage lessons;

    public void setStudiesPage(StudiesPage studies) {
        this.studies = studies;
    }

    public void setLessonsPage(LessonsPage lessons) {
        this.lessons = lessons;
    }

    private JPanel mainPanel;
    private JPanel secondMainPanel;
    private JPanel leftPanel;
    private JLabel logoLabel;
    private JPanel homeLabelPanel;
    private JLabel homeLabel;
    private JPanel messagesLabelPanel;
    private JLabel messagesLabel;
    private JPanel notificationsLabelPanel;
    private JLabel notificationsLabel;
    private JPanel profileLabelPanel;
    private JLabel profileLabel;
    private JPanel requestLabelPanel;
    private JLabel requestsLabel;
    private JPanel logOutLabelPanel;
    private JLabel logOutLabel;
    private JPanel rightPanel;
    private JButton profileBoxButton;
    private JButton filterBoxButton;
    private JPanel middlePanel;
    private JLabel bilkenTogetherLabel;
    private JPanel buttonPanel;
    private JButton lessonsButton;
    private JButton studiesButton;
    private JButton activitiesButton;
    private JScrollPane flowScrollPane;
    private JPanel insideScrollPanePanel;
    private JPanel postingPanel;
    private JTextArea textArea1;
    private JLabel activityType;
    private JComboBox activityTypeComboBox;
    private JButton postButton;
    private JLabel errorLabel;
    private JComboBox peopleCountComboBox;
    private JPanel profilePhotoPanel;
    private JPanel activitiesQFpanel;
    private JPanel typePanel;
    private JComboBox typeComboBox;
    private JPanel peopleCountPanel;
    private JLabel numberOfPeople;
    private JSlider slider1;
    private JPanel dateIntervalPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton submitButton;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JPanel quickFiltersPanel;
    private JPanel removableRight;
    User currentUser;
    private GridBagConstraints g;
    public ActivitiesPage() {
        JScrollBar bar = flowScrollPane.getVerticalScrollBar();
        errorLabel.setText(" ");
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 800);
        currentUser = new Student("Erdem", "erdem.p", 22203112, "l", "d", "p", "b");
        generalSetup();


        //setVisible(true);
        filterBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activitiesQFpanel.isVisible())
                    activitiesQFpanel.setVisible(false);
                else
                    activitiesQFpanel.setVisible(true);
            }
        });
        studiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(studies.getContentPane());
            }
        });
        lessonsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(lessons.getContentPane());
            }
        });
    }
    public JPanel getInsideScrollPanePanel(){
        return insideScrollPanePanel;
    }
    public JPanel getActivitiesQFpanel(){
        return quickFiltersPanel;
    }
    public JPanel getRightPanel(){
        return rightPanel;
    }
    public void setCurrentUser(User user) {
        currentUser = user;
    }
    public JPanel getQuickFiltersPanel(){
        return quickFiltersPanel;
    }
    public void generalSetup() {

        peopleCountComboBox.setSelectedItem(1);
        postButton.addActionListener(new postButtonListener());
        g = new GridBagConstraints();
        textArea1.setMargin(new Insets(5, 5, 5, 5));
        textArea1.setLineWrap(true);
        textArea1.setColumns(50);
        PPImageHandler profilePhoto = new PPImageHandler();
        profilePhotoPanel.add(profilePhoto);
        peopleCountComboBox.addItem("Select:");
        for (int i = 1; i < 16; i++) {
            peopleCountComboBox.addItem(i +"");
        }
        lessonsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessons.setVisible(true);
                setVisible(false);
                repaint();
                revalidate();

            }
        });
    }

    public static void main(String[] args) {
        ActivitiesPage page = new ActivitiesPage();
    }
    private Main main;
    public void setMain(Main main) {
        this.main = main;
    }
    private class postButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (checkPost()) {
                String type = (String) activityTypeComboBox.getSelectedItem();
                int peopleCount = Integer.parseInt((String) peopleCountComboBox.getSelectedItem());
                Student tempStudent = (Student) currentUser;
                Date date = new Date();
                g.gridx = 0;
                ActivityPost tempPost = new ActivityPost(0,tempStudent,textArea1.getText(),peopleCount,date.toString(),type,"23/03/2023");
                ActivitiesPostViewer viewer2 = new ActivitiesPostViewer(tempPost);
                insideScrollPanePanel.add(viewer2,g);
                main.repaint();
                main.revalidate();
            }

        }
        private boolean checkPost(){
            if (textArea1.getText().isEmpty()){
                errorLabel.setText("Please Enter A Description!");
            }
            if (activityTypeComboBox.getSelectedItem().equals("Select:")) {
                errorLabel.setText("Please Select Activity Type!");
                return false;
            }
            if (peopleCountComboBox.getSelectedItem().equals("Select:")){
                errorLabel.setText("Please Select How Many People!");
                return false;
            }
            return true;
        }
    }
}


