package HomePage.StudiesPage;

import DatabaseRelated.DatabaseConnection;
import HomePage.LessonsPage.LessonsPage;
import HomePage.Main.HomeMain;
import PostComponents.StudiesPostViewer;
import Posts.StudyPost;
import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class StudiesPage {
    private HomeMain main;
    private String[] topics;
    private final String[] updatedTopics = {"MATH", "CS", "LINEAR ALGEBRA", "DEDIKODU", "PHYSICS", "CS BUT CURSED"};
    int index;
    ArrayList<StudyPost> studyPosts;
    ArrayList<StudiesPostViewer> studiesPostViewers;
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
    private ArrayList<Integer> allUsers;
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
    private JButton postingResetButton;
    private JButton uploadFileButton;
    private JLabel selectedTopic;
    private JLabel filterLabel1;
    private JLabel filterLabel2;
    private JLabel filterLabel3;
    private JLabel filterLabel4;
    private JLabel filterLabel5;
    private JLabel topicFilterLabel;
    private JScrollPane filterboxScroll;
    private JButton resetButtonInBox;
    private JTextField searchTopicField;
    private JTextField filterChooserSearchField;
    private JPanel top;
    private JPanel bottom;
    private JLabel profilePhotoLabel;
    private JPanel filtersTopicPanel;
    private JTextArea addAuthoursTextArea;
    private GridBagConstraints g;

    int indexOfFilterBox;
    private User currentUser;
    private ArrayList<String> options;
    private JLabel[] postingTopicLabels = {topicLabel1,topicLabel2,topicLabel3,topicLabel4,topicLabel5};
    private JLabel[] filterLabels ={filterLabel1,filterLabel2,filterLabel3,filterLabel4,filterLabel5};
    private ArrayList<String> postingFilters;
    private ArrayList<String> filterSideTopicFilters;
    public StudiesPage(HomeMain main) {
        studyPosts = new ArrayList<>();
        studiesPostViewers = new ArrayList<>();
        postingFilters = new ArrayList<String>();
        this.main = main;
        this.currentUser = main.getCurrentUser();

        filterSideTopicFilters = new ArrayList<>();
        initializeTopicArraylist();
        String[] temp = new String[postingFilters.size()];
        index = 0;
        indexOfFilterBox = 0;

        list2.setListData(postingFilters.toArray(temp));
        list1.setListData(postingFilters.toArray(temp));
        generalSetup();

        setUpUploadButton();
        makeAllButtonsBeatiful();
        addListeners();
        getRandomPosts();

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                b.setSelected(!b.isSelected());
                b.setBackground(b.isSelected() ? LessonsPage.SECONDARYBUTTONCOLOR : LessonsPage.PRIMARYBUTTONCOLOR);
            }
        };
        instructorButton.addActionListener(listener);
        studentButton.addActionListener(listener);
    }

    private void resetFilterLabels() {
        filterLabel1.setText("");
        filterLabel2.setText("");
        filterLabel3.setText("");
        filterLabel4.setText("");
        filterLabel5.setText("");
    }
    private void addListeners(){
        submitButton.addActionListener(new FilterListener());
        list2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = list2.getSelectedIndex();
                    String selectedValue = list2.getSelectedValue();
                    //ArrayList<String> listOfSelected= new ArrayList<String>(list2.getSelectedValuesList());
                    postingFilters.remove(selectedValue);
                    refreshPostingtopicsList();
                    topicLabels[index].setText(selectedValue);
                    topicLabels[index].setVisible(true);
                    if (index < 4) {
                        index++;
                    }
                }
            }
        });
        filterBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    collection[0] = topicLabel1.getText().length() <2 ? null : topicLabel1.getText();
                    collection[1] = topicLabel2.getText().length() <2 ? null : topicLabel2.getText();
                    collection[2] = topicLabel3.getText().length() <2 ? null : topicLabel3.getText();
                    collection[3] = topicLabel4.getText().length() <2 ? null : topicLabel4.getText();
                    collection[4] = topicLabel5.getText().length() <2 ? null : topicLabel5.getText();
                    StudyPost temp;

                    temp = new StudyPost(0, currentUser, addAuthors, heading, postText, uploadedPdf, new Date().toString(), collection,true);
                    StudiesPostViewer viewer = new StudiesPostViewer(temp,main);
                    GridBagConstraints g2 = new GridBagConstraints();
                    g2.gridx = 0;
                    studiesPostViewers.add(viewer);
                    studyPosts.add(temp);
                    currentUser.addStudyPost(temp);
                    insideScrollPanel.add(viewer,g2);
                    insideScrollPanel.repaint();
                    insideScrollPanel.revalidate();
                }
                main.update();
            }
        });


        addTopicLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                listScroll.setVisible(!listScroll.isVisible());
            }
        });


        postingResetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                postingFilters = resetTopicArraylist();
                refreshPostingtopicsList();
                index = 0;
                topicLabel1.setText(" ");
                topicLabel2.setText(" ");
                topicLabel3.setText(" ");
                topicLabel4.setText(" ");
                topicLabel5.setText(" ");
                searchTopicField.setText("");
            }
        });
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    String selectedValue = list1.getSelectedValue();
                    //ArrayList<String> listOfSelected= new ArrayList<String>(list2.getSelectedValuesList());
                    filterLabels[indexOfFilterBox].setText(selectedValue);
                    filterSideTopicFilters.remove(selectedValue);
                    refreshFiltersTopicsList();
                    if (indexOfFilterBox<4) {
                        indexOfFilterBox++;
                    }
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
        resetButtonInBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                filterSideTopicFilters = resetTopicArraylist();
                refreshFiltersTopicsList();
                resetFilterLabels();
                filterChooserSearchField.setText("");
            }
        });

        searchTopicField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (!searchTopicField.getText().isBlank()){
                    filterTopicChooser(searchTopicField.getText(),postingFilters);
                }
                else
                    postingFilters =resetTopicArraylist();
                refreshPostingtopicsList();
            }
        });
        filterChooserSearchField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!filterChooserSearchField.getText().isBlank()){
                    filterTopicChooser(filterChooserSearchField.getText(),filterSideTopicFilters);
                }
                else
                    filterSideTopicFilters =resetTopicArraylist();
                refreshFiltersTopicsList();
            }

        });


    }


    private void filterTopicChooser(String text,ArrayList<String> top) {
        for (int i = 0; i < top.size(); i++) {
            if (!(top.get(i).toLowerCase().contains(text.toLowerCase()))) {
                top.remove(i);
                i--;
            }
        }
    }

    private void refreshPostingtopicsList() {
        String[] temp = new String[postingFilters.size()];
        list2.setListData(postingFilters.toArray(temp));
    }
    private void refreshFiltersTopicsList() {
        String[] temp = new String[postingFilters.size()];
        list1.setListData(filterSideTopicFilters.toArray(temp));
    }

    private void initializeTopicArraylist() {

            File file = new File("src/Other/topics.txt");

            Scanner in = null;
            try {
                in = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (in.hasNextLine()){
                String temp = in.nextLine();
                postingFilters.add(temp);
                filterSideTopicFilters.add(temp);
            }
            Collections.sort(postingFilters);
            Collections.sort(filterSideTopicFilters);
            topics = new String[postingFilters.size()];
        for (int i = 0; i <topics.length; i++) {
            topics[i] = postingFilters.get(i)+"";
        }

    }
    private ArrayList<String> resetTopicArraylist() {
        return new ArrayList<>(Arrays.asList(topics));
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
    private void getRandomPosts() {
        DatabaseConnection c = new DatabaseConnection();
        allUsers = currentUser.pullIDsFromUserInformationTable();
        ArrayList<ArrayList<StudyPost>> userPostCollections = new ArrayList<>();

        boolean exceed = false;
        for (int i = 0; i < allUsers.size(); i++) {
            userPostCollections.add(c.pullUserByIdFromDB(allUsers.get(i)).pullFromStudyPostTable());
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
            if (!userPostCollections.get(rand1).isEmpty() && !studyPosts.contains(userPostCollections.get(rand1).get(rand2))){
                addStudyPost(userPostCollections.get(rand1).get(rand2));
                studyPosts.add(userPostCollections.get(rand1).get(rand2));
            }else if (exceed){
                i--;
            }
        }

    }
    private int totalNumOfPosts(ArrayList<ArrayList<StudyPost>> userPostCollections){
        int total = 0;
        for (ArrayList<StudyPost> userPostCollection : userPostCollections) {
            total += userPostCollection.size();
        }
        return total;
    }

    private void addStudyPost(StudyPost studyPost) {
        GridBagConstraints g2 = new GridBagConstraints();
        g2.gridx = 0;
        StudiesPostViewer viewer = new StudiesPostViewer(studyPost,main);
        studiesPostViewers.add(viewer);
        insideScrollPanel.add(viewer, g2);
    }


    private void filterStudies(String selectedValue) {
        Component[] components = insideScrollPanel.getComponents();
        for (Component component : components) {
            if (component instanceof StudiesPostViewer)
            {
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
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void generalSetup() {
        headingtextArea.setMargin(new Insets(5, 5, 5, 5));
        headingtextArea.setLineWrap(true);
        headingtextArea.setColumns(50);
        headingtextArea.setRows(7);
        profilePhotoLabel.setIcon(new PPImageHandler(currentUser).getIcon());
    }

    public JPanel getInsideScrollPanePanel() {
        return insideScrollPanel;
    }

    public JPanel getQfPanel() {
        return qfPanel;
    }

    public void setMain(HomeMain main) {
        this.main = main;
    }
    private void makeAllButtonsBeatiful(){
        LessonsPage.makeButtonMoreBeautiful(postButton);
        LessonsPage.makeButtonMoreBeautiful(resetButtonInBox);
        LessonsPage.makeButtonMoreBeautiful(postingResetButton);
        LessonsPage.makeButtonMoreBeautiful(instructorButton);
        LessonsPage.makeButtonMoreBeautiful(studentButton);
        LessonsPage.makeButtonMoreBeautiful(filterBoxButton);
        LessonsPage.makeButtonMoreBeautiful(uploadFileButton);
        LessonsPage.makeButtonMoreBeautiful(submitButton);

    }
    private class FilterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshPosts();
            if (getChosenTopic() != null){
                String[] choosenTopics = getChosenTopic();
                for (StudiesPostViewer viewer :
                        studiesPostViewers) {
                    if (!viewer.getPost().matchesFilter(choosenTopics))
                        viewer.setVisible(false);
                }
            }
            if (instructorButton.isSelected() || studentButton.isSelected()){
                for (StudiesPostViewer viewer : studiesPostViewers) {
                    if ((viewer.getPost().getSender() instanceof Student))
                        viewer.setVisible(studentButton.isSelected());
                    else
                        viewer.setVisible(instructorButton.isSelected());
                }
            }
            main.update();

        }

        private String[] getChosenTopic() {
            String[] temp = new String[5];
            int i = 0;
            for (Component c: filtersTopicPanel.getComponents()) {
                if (c instanceof JLabel topiclabel){
                    if (topiclabel.getText().length() > 1 && i<5){
                        temp[i] = topiclabel.getText();
                        i++;
                    }
                }
            }
            if (i != 0)
                return Arrays.copyOf(temp,i);
            return null;
        }
        private void refreshPosts(){
            for (StudiesPostViewer studiesPostViewer : studiesPostViewers) {
                studiesPostViewer.setVisible(true);
            }
        }

    }
    public void refreshProfilePhotos() {
        PPImageHandler profilePhoto = new PPImageHandler(currentUser);
        profilePhotoPanel.add(profilePhoto);
        profilePhotoPanel.revalidate();
        profilePhotoPanel.repaint();
    }
}
