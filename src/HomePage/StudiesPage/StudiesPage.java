package HomePage.StudiesPage;

import HomePage.Main.Main;
import PostComponents.StudiesPostViewer;
import Posts.StudyPost;
import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

public class StudiesPage {
    private Main main;
    private final String[] topics = {"MATH", "CS", "LINEAR ALGEBRA", "DEDIKODU", "PHYSICS", "CS BUT CURSED"};
    private final String[] updatedTopics = {"MATH", "CS", "LINEAR ALGEBRA", "DEDIKODU", "PHYSICS", "CS BUT CURSED"};
    int index;
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
    private JList <String>list1;
    private JButton instructorButton;
    private JButton studentButton;
    private JButton submitButton;
    private JPanel nonRemovableRightPanel;
    private JPanel qfPanel;
    private JPanel insideScrollPanel;
    private JLabel errorLabel;
    private byte[] uploadedPdf;
    private JList<String> list2;
    private DefaultListModel<String> listModel;
    private JLabel selectedOption;
    private JScrollPane listScroll;
    private JPanel topicPanel;
    private JLabel topicLabel1;
    private JLabel topicLabel2;
    private JLabel topicLabel3;
    private JLabel topicLabel4;
    private JLabel topicLabel5;
    private final JLabel[] topicLabels = {topicLabel1, topicLabel2, topicLabel3, topicLabel4, topicLabel5};
    private JLabel errorLabel2;
    private JTextField addAuthorsTextField;
    private JLabel errorLabel3;
    private JButton ResetButton;
    private JButton uploadFileButton;
    private JLabel selectedTopic;
    private JLabel filterBox1;
    private JLabel filterBox2;
    private JLabel filterBox3;
    private JLabel filterBox4;
    private JLabel filterBox5;
    private JLabel topicFilterLabel;
    private JScrollPane filterboxScroll;
    private JButton resetButtonInBox;
    private JTextArea addAuthoursTextArea;
    private GridBagConstraints g;

    int indexOfFilterBox;
    private User currentUser;
    private ArrayList<String> options;
  
    private JLabel[] filterLabels ={topicLabel1,topicLabel2,topicLabel3,topicLabel4,topicLabel4,topicLabel5};
    private String[] filteredTopics = {"LINEAR ALGEBRA","CALCULUS","COMPLEX ANALYSIS","ALGEBRA","RECURSION","TURING"};
    private String[] updatedFilteredTopics = {"LINEAR ALGEBRA","CALCULUS","COMPLEX ANALYSIS","ALGEBRA","RECURSION","TURING"};
    public StudiesPage() {

        index = 0;
        indexOfFilterBox = 0;
        list2.setListData(topics);
        list1.setListData(filteredTopics);
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

                    if (index < 4) {
                        index++;

                    }
                    ArrayList<String> list = new ArrayList<String>(list2.getSelectedValuesList());
                    System.out.println(list);



                    list1.addMouseListener(new MouseAdapter() 
                    {
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if (e.getClickCount() == 2) 
                            {
                                int selectedIndex = list1.getSelectedIndex();
                                if (selectedIndex >= 0) 
                                {
                                    String selectedValue = list1.getSelectedValue();
                                    filterStudies(selectedValue);
                                }
                            }
                        }
                    });





                }


            }

        });

        currentUser = new Student("Erdem", "erdem.p", 22203112, "l", "d", "p", "b");


        //setVisible(true);
        filterBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();

                studiesQFPanel.setVisible(!studiesQFPanel.isVisible());


            }
        });

        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String postText = textArea2.getText();
                String heading = headingtextArea.getText();
                String addAuthors = addAuthorsTextField.getText();
                if (postText.isBlank()) {
                    String errorMessage = "Post content cannot be empty.";
                    errorLabel.setText(errorMessage);
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setSize(30, 30);
                } else if (heading.isEmpty()) {
                    String errorMessage = "Heading cannot be empty.";
                    errorLabel2.setText(errorMessage);
                    errorLabel2.setForeground(Color.RED);
                    errorLabel2.setSize(30, 30);

                } else if (addAuthors.isEmpty()) {
                    String errorMessage = "Authors cannot be empty.";
                    errorLabel3.setText(errorMessage);
                    errorLabel3.setForeground(Color.RED);
                    errorLabel2.setSize(30, 30);
                }else {
                    String[] collection = new String[5];
                    collection[0] = topicLabel1.getText();
                    collection[1] = topicLabel2.getText();
                    collection[2] = topicLabel3.getText();
                    collection[3] = topicLabel4.getText();
                    collection[4] = topicLabel5.getText();
                    StudyPost temp;
                    if (uploadedPdf != null)
                       temp = new StudyPost(2, currentUser, addAuthors, heading, postText, uploadedPdf, "2002", collection);
                    else
                        temp = new StudyPost(2, currentUser, addAuthors, heading, postText, null, "2002", collection);
                    StudiesPostViewer viewer = new StudiesPostViewer(temp,main);
                    GridBagConstraints g2 = new GridBagConstraints();
                    g2.gridx = 0;

                    insideScrollPanel.add(viewer,g2);
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
                listScroll.setVisible(!listScroll.isVisible());
            }
        });
        listScroll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }


        });


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
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    int selectedIndex = list1.getSelectedIndex();

                    String selectedValue = list1.getSelectedValue();
                    //ArrayList<String> listOfSelected= new ArrayList<String>(list2.getSelectedValuesList());


                    filteredTopics[selectedIndex] = null;

                    list1.setListData(filteredTopics);
                    filterLabels[indexOfFilterBox].setText(selectedValue);
                    filterLabels[indexOfFilterBox].setVisible(true);

                    if (indexOfFilterBox<4) {
                        indexOfFilterBox++;

                    }
                    ArrayList<String> list = new ArrayList<String>(list1.getSelectedValuesList());
                    System.out.println(list);
            }}
        });
        topicFilterLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(filterboxScroll.isVisible()){
                    filterboxScroll.setVisible(false);
                }
                else{
                    filterboxScroll.setVisible(true);

                }
            }
        });
        filterboxScroll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        resetButtonInBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                list1.setListData(updatedFilteredTopics);
                for (int i = 0; i < updatedFilteredTopics.length; i++) {
                    filteredTopics[0] = updatedFilteredTopics[0];
                }
                if (e.getClickCount() == 2) {


                    for (int i = 0; i < filterLabels.length; i++) {
                        filterLabels[i].setText("");

                    }



                    indexOfFilterBox = 0;

                }
            }
        });
        setUpUploadButton();
    }

    private void setUpUploadButton() {
        uploadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.addChoosableFileFilter(new FileNameExtensionFilter("File.pdf",  "pdf"));
                int choice = fc.showOpenDialog(new JPanel());
                if (choice == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    File file = new File(selectedFile.getAbsolutePath());
                    byte[] bytes = new byte[(int) file.length()];
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(file);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    int read;
                    while(true) {
                        try {
                            if (!((read = fis.read(bytes)) != -1)) break;
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        os.write(bytes, 0, read);
                    }
                    uploadedPdf = bytes;
                    try {
                        fis.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        os.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }


                }


            }
        });
    }


    private void filterStudies(String selectedValue) {
        Component[] components = insideScrollPanel.getComponents();
        for (Component component : components) {
            if (component instanceof StudiesPostViewer) {
                StudiesPostViewer posts = (StudiesPostViewer) component;
                StudyPost post = posts.getStudyPost();

                // Adjust the following line based on how you want to filter based on studyFile
                //boolean matchesFilter = post.getStudyFile().getName().contains(selectedValue);

                //if (matchesFilter) {
                  //  posts.setVisible(true);
                //} else {
                  //  posts.setVisible(false);
                //}
            }
        }
        insideScrollPanel.revalidate();
        insideScrollPanel.repaint();
        main.update();
    }




    public static void main(String[] args) {
        StudiesPage page = new StudiesPage();
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
        profilePhotoPanel.add(profilePhoto);


    }

    public JPanel getInsideScrollPanePanel() {
        return insideScrollPanel;
    }

    public JPanel getQfPanel() {
        return qfPanel;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
