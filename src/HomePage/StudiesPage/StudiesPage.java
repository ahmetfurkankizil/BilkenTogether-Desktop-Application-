package HomePage.StudiesPage;

import HomePage.Main.Main;
import PostComponents.StudiesPostViewer;
import Posts.ActivityPost;
import Posts.StudyPost;
import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudiesPage {
    private JPanel mainPanel;
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
    private JTextField addAuthorsTextField;
    private JLabel errorLabel3;
    private JButton ResetButton;
    private JTextArea addAuthoursTextArea;
    private GridBagConstraints g;
    int index;
    private User currentUser;
    private ArrayList<String> options;
    private String[] topics = {"MATH", "CS", "LINEAR ALGEBRA", "DEDIKODU", "PHYSICS","CS BUT CURSED"};
    private String[] updatedTopics = {"MATH", "CS", "LINEAR ALGEBRA", "DEDIKODU", "PHYSICS","CS BUT CURSED"};
    private JLabel[] topicLabels = {topicLabel1, topicLabel2, topicLabel3, topicLabel4, topicLabel5};
    public StudiesPage() {

        index = 0;
        list2.setListData(topics);
        list2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                if (e.getClickCount() == 2) {
                    int selectedIndex = list2.getSelectedIndex();

                    String selectedValue = list2.getSelectedValue();
                    //ArrayList<String> listOfSelected= new ArrayList<String>(list2.getSelectedValuesList());

                   topics[selectedIndex] = null;

                    list2.setListData(topics);
                    topicLabels[index].setText(selectedValue);
                    topicLabels[index].setVisible(true);

                    if (index<4) {
                        index++;

                    }
                   ArrayList<String> list = new ArrayList<String>(list2.getSelectedValuesList());
                    System.out.println(list);








                }


            }

        });

        currentUser = new Student("Erdem", "erdem.p", 22203112, "l", "d", "p", "b");






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

        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String postText = textArea2.getText();
                String heading = headingtextArea.getText();
               String addAuthors = addAuthorsTextField.getText();
                if(postText.isBlank()) {
                    String errorMessage = "Post content cannot be empty.";
                    errorLabel.setText(errorMessage);
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setSize(30,30);
                }
                else if(heading.isEmpty()){
                    String errorMessage = "Heading cannot be empty.";
                    errorLabel2.setText(errorMessage);
                    errorLabel2.setForeground(Color.RED);
                    errorLabel2.setSize(30,30);

                }
                else if(addAuthors.isEmpty()){
                    String errorMessage = "Authors cannot be empty.";
                    errorLabel3.setText(errorMessage);
                    errorLabel3.setForeground(Color.RED);
                    errorLabel2.setSize(30,30);
                }else {
                    String[] collection = new String[5];
                    collection[0] = topicLabel1.getText();
                    collection[1] = topicLabel2.getText();
                    collection[2] = topicLabel3.getText();
                    collection[3] = topicLabel4.getText();
                    collection[4] = topicLabel5.getText();
                    StudyPost temp = new StudyPost(2,currentUser,addAuthors, heading,postText,null,"2002",collection);
                    //StudiesPostViewer viewer = new StudiesPostViewer(temp);
                    GridBagConstraints g2 = new GridBagConstraints();
                    g2.gridx =0;

                    //insideScrollPanel.add(viewer,g2);
                    insideScrollPanel.repaint();
                    insideScrollPanel.revalidate();
                    main.update();
                }
                main.update();
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
        listScroll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }




        });
        generalSetup();

        ResetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
                list2.setListData(updatedTopics);
                for (int i = 0; i < updatedTopics.length; i++) {
                    topics[0] = updatedTopics[0];
                }
                if (e.getClickCount() == 2) {


                    for (int i = 0; i < topicLabels.length; i++) {
                        topicLabels[i].setText("");

                    }



                    index = 0;

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
