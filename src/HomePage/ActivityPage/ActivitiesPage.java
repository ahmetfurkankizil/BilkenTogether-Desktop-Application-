package HomePage.ActivityPage;

import HomePage.Main.Main;
import PostComponents.ActivitiesPostViewer;
import Posts.ActivityPost;
import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class ActivitiesPage {
    private Main main;
    private JPanel mainPanel;
    private JButton profileBoxButton;
    private JButton filterBoxButton;
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
    private JComboBox dayComboBox;
    private JComboBox monthComboBox;
    private JComboBox yearComboBox;
    private JPanel quickFiltersPanel;
    private JPanel removableRight;
    private JComboBox eventDaycomboBox;
    private JComboBox eventMonthcomboBox;
    private JComboBox eventYearcomboBox;
    private JLabel filterErrorLabel;
    private JButton resetButton;
    User currentUser;

    private ArrayList<ActivitiesPostViewer> activitiesPostViewerArrayList;
    private GridBagConstraints g;
    public ActivitiesPage() {
        activitiesPostViewerArrayList = new ArrayList<ActivitiesPostViewer>();
        JScrollBar bar = flowScrollPane.getVerticalScrollBar();
        errorLabel.setText(" ");
        currentUser = new Student("Erdem", "erdem.p", 22203112, "l", "d", "p", "b",null,null);
        generalSetup();
        filterBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activitiesQFpanel.isVisible())
                    activitiesQFpanel.setVisible(false);
                else
                    activitiesQFpanel.setVisible(true);
            }
        });

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider1.getValue();
                numberOfPeople.setText("" + value);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isTypeSelected = !typeComboBox.getSelectedItem().equals("Select:");
                boolean isZero = numberOfPeople.getText().equals("0");
                boolean isDaySelected = !dayComboBox.getSelectedItem().equals("Day:");
                boolean isMonthSelected = !monthComboBox.getSelectedItem().equals("Month:");
                boolean isYearSelected = !yearComboBox.getSelectedItem().equals("Year:");
                boolean isDateSelected = false;
                String type = "";
                int numOfPeople = 0;
                String date = "";
                if(isTypeSelected) {
                    type = typeComboBox.getSelectedItem() +"";
                    for (int i = 0; i < activitiesPostViewerArrayList.size(); i++) {
                        if(!activitiesPostViewerArrayList.get(i).getPost().getTypeFilter().equals(type)) {
                            activitiesPostViewerArrayList.get(i).setVisible(false);
                        }
                    }
                }
                if (!isZero) {
                    numOfPeople = Integer.parseInt(numberOfPeople.getText());
                    for (int i = 0; i < activitiesPostViewerArrayList.size(); i++) {
                        if(activitiesPostViewerArrayList.get(i).getPost().getNumberOfAttendants() != numOfPeople) {
                            activitiesPostViewerArrayList.get(i).setVisible(false);
                        }
                    }
                }
                if(isDaySelected && isMonthSelected && isMonthSelected) {
                    isDateSelected = true;
                    date = dayComboBox.getSelectedItem() +"/"+ monthComboBox.getSelectedItem() +"/"+ yearComboBox.getSelectedItem();
                    for (int i = 0; i < activitiesPostViewerArrayList.size(); i++) {
                        if(!activitiesPostViewerArrayList.get(i).getPost().getActivityDate().equals(date)) {
                            activitiesPostViewerArrayList.get(i).setVisible(false);
                        }
                    }
                }
                if(!isTypeSelected && isZero && !isDaySelected && !isMonthSelected && !isYearSelected) {
                    for (int i = 0; i < activitiesPostViewerArrayList.size(); i++) {
                        activitiesPostViewerArrayList.get(i).setVisible(true);
                    }
                }
                main.repaint();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < activitiesPostViewerArrayList.size(); i++) {
                    activitiesPostViewerArrayList.get(i).setVisible(true);
                }
                typeComboBox.setSelectedIndex(0);
                slider1.setValue(-1);
                dayComboBox.setSelectedIndex(0);
                monthComboBox.setSelectedIndex(0);
                yearComboBox.setSelectedIndex(0);
            }
        });
    }

    public JPanel getInsideScrollPanePanel(){
        return insideScrollPanePanel;
    }
    public JPanel getActivitiesQFpanel(){
        return quickFiltersPanel;
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
    }
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
                String activityDay = eventDaycomboBox.getSelectedItem() + "";
                String activityMonth = eventMonthcomboBox.getSelectedItem() + "";
                String activityYear = eventYearcomboBox.getSelectedItem() + "";
                String activityDate = activityDay + "/" + activityMonth + "/" + activityYear;
                ActivityPost tempPost = new ActivityPost(0,tempStudent,textArea1.getText().strip(),peopleCount,date.toString(),type, activityDate);
                //tempStudent.addToActivitiesTable(tempPost);
                ActivitiesPostViewer viewer2 = new ActivitiesPostViewer(tempPost,main);
                activitiesPostViewerArrayList.add(viewer2);
                insideScrollPanePanel.add(viewer2,g);
                main.update();
            }

        }
        private boolean checkPost(){
            if (textArea1.getText().isBlank()){
                errorLabel.setForeground(Color.RED);
                errorLabel.setText("Please Enter A Description!");
                return false;
            }
            if (activityTypeComboBox.getSelectedItem().equals("Select:")) {
                errorLabel.setForeground(Color.RED);
                errorLabel.setText("Please Select Activity Type!");
                return false;
            }
            if (peopleCountComboBox.getSelectedItem().equals("Select:")){
                errorLabel.setForeground(Color.RED);
                errorLabel.setText("Please Select How Many People!");
                return false;
            }
            // Validating Date Start
            if((eventDaycomboBox.getSelectedItem()).equals("Day:")) {
                errorLabel.setForeground(Color.RED);
                errorLabel.setText("Please Select Day!");
                return false;
            }
            if((eventMonthcomboBox.getSelectedItem()).equals("Month:")) {
                errorLabel.setForeground(Color.RED);
                errorLabel.setText("Please Select Month!");
                return false;
            }

            if((eventYearcomboBox.getSelectedItem()).equals("Year:")) {
                errorLabel.setForeground(Color.RED);
                errorLabel.setText("Please Select Year!");
                return false;
            }
            // Validating Date End
            return true;
        }
    }
}


