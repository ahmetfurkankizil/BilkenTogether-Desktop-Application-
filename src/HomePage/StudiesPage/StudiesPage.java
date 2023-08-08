package HomePage.StudiesPage;

import HomePage.ActivityPage.ActivitiesPage;
import HomePage.LessonsPage.LessonsPage;
import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class StudiesPage extends JFrame{
    private JPanel mainPanel;
    private JFrame activities;

    public void setActivitiesPage(ActivitiesPage activities) {
        this.activities = activities;
    }

    public void setLessonsPage(LessonsPage lessons) {
        this.lessons = lessons;
    }

    private JFrame lessons;
    private JPanel secondMainPanel;
    private JPanel rightPanel;
    private JButton profileBoxButton;
    private JPanel middlePanel;
    private JLabel bilkenTogetherLabel;
    private JPanel buttonPanel;
    private JButton lessonsButton;
    private JButton studiesButton;
    private JButton activitiesButton;
    private JScrollPane flowScrollPane;
    private JPanel profilePhotoPanel;
    private JTextArea headingtextArea;
    private JLabel addAuthorsLabel;
    private JLabel headingLabel;
    private JLabel addTopicLabel;
    private JPanel studiesPostArea;
    private JButton postButton;
    private JTextArea textArea2;
    private JButton filterBoxButton;
    private JPanel studiesQFPanel;
    private JList list1;
    private JButton instructorButton;
    private JButton studentButton;
    private JButton submitButton;
    private JPanel nonRemovableRightPanel;
    private JPanel qfPanel;
    private JPanel insideScrollPanel;
    private JLabel errorLabel;
    private JList <String>list2;
    private DefaultListModel<String> listModel;
    private JLabel selectedOption;
    private JScrollPane listScroll;
    private JPanel topicPanel;
    private JLabel topicLabel1;
    private JLabel topicLabel2;
    private JLabel topicLabel3;
    private JLabel topicLabel4;
    private JLabel topicLabel5;
    private JLabel errorLabel2;
    private JTextField textField1;
    private JTextArea addAuthoursTextArea;
    private GridBagConstraints g;
    private User currentUser;
    public StudiesPage() {

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 800);
        currentUser = new Student("Erdem", "erdem.p", 22203112, "l", "d", "p", "b");
        generalSetup();
        listModel = new DefaultListModel<>();
        list2 = new JList<>(listModel);



        //setVisible(true);
        filterBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();

                if (studiesQFPanel.isVisible()){
                    studiesQFPanel.setVisible(false);
                } else {
                    studiesQFPanel.setVisible(true);
                }


            }
        });
        lessonsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(lessons.getContentPane());
            }
        });
        activitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(activities.getContentPane());

            }

        });

        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String postText = textArea2.getText();
                String heading = headingtextArea.getText();
                //String addAuthors = addAuthoursTextArea.getText();
                if(postText.isEmpty()) {
                    String errorMessage = "Post content cannot be empty.";
                    errorLabel.setText(errorMessage);
                    errorLabel.setForeground(Color.RED);
                }
                if(heading.isEmpty()){
                    String errorMessage = "Heading cannot be empty.";
                    errorLabel2.setText(errorMessage);
                    errorLabel2.setForeground(Color.RED);

                }



                }





        });
        list2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                list2.getSelectedValue();
                String selectedValue = list2.getSelectedValue();

                if (selectedValue != null) {
                    topicLabel1.setText(selectedValue);
                }






                selectedOption = new JLabel("Selected Option");
            }
        });

        addTopicLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("clicked!");
                if(listScroll.isVisible()){
                    listScroll.setVisible(false);
                }
                else{
                    listScroll.setVisible(true);

                }
            }
        });
        list2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("1");

                if (e.getClickCount() == 2) {
                    String selectedIndex = list2.getSelectedValue();
                    //String selectedValue = listModel.get(selectedIndex);
                        topicLabel1.setText(selectedIndex);
                        topicLabel1.setVisible(true);
                    }


                }

        });
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void generalSetup() {
        g = new GridBagConstraints();
        headingtextArea.setMargin(new Insets(5, 5, 5, 5));
        headingtextArea.setLineWrap(true);
        headingtextArea.setColumns(50);
        PPImageHandler profilePhoto = new PPImageHandler();
        //profilePhotoPanel.add(profilePhoto);


    }

    public static void main(String[] args) {
        StudiesPage page = new StudiesPage();
    }

    public JPanel getInsideScrollPanePanel() {
        return insideScrollPanel;
    }
    public JPanel getQfPanel(){
        return qfPanel;
    }
    private Main main;
    public void setMain(Main main) {
        this.main = main;
    }
}
