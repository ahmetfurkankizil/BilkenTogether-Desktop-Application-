package HomePage.LessonsPage;
import HomePage.Main.Main;
import Icons.IconCreator;
import PostComponents.LessonPostViewer;
import Posts.LessonPost;
import Posts.RequestablePost;
import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Date;
import Posts.LessonPost;
import PostComponents.LessonPostViewer;
public class LessonsPage {
    private Main main;

    private JPanel mainPanel;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    public static final ImageIcon back = IconCreator.getIconWithSize(IconCreator.backIcon, 30, 30);
    private User currentUser;
    private JButton lessonsButton;
    private ArrayList<LessonPost> lessonPostArrayList;
    private JButton studiesButton;
    private JButton activitiesButton;
    private JButton profileBoxButton;
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
    private ArrayList<JButton> sectionButtons;
    private ArrayList<JButton> dayButtons;

    private boolean isSubmitted = false;
    public JPanel getInsideScrollPanePanel() {
        return insideScrollPanePanel;
    }
    public JPanel getQuickFiltersPanel() {
        return quickFiltersPanel;
    }
    public LessonsPage() {

        currentUser = new Student("Erdem", "erdem.p", 22203112, "l", "d", "p", "b");
        generalSetup();
        lessonPostArrayList = new ArrayList<LessonPost>();


        filtersSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSubmitted = true; // Set the flag to true after clicking the submit button
            }
        });



        textArea1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                main.update();
            }
        });
        filtersSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {





            }
        });
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
        profilePhoto = new PPImageHandler();
        profilePhotoPanel.add(profilePhoto);
    }
    private void setUpActionListeners() {
        postButton.addActionListener(new LesssonPostPostingListener());
        postLessonButton.addActionListener(new requestActionListener());
        requestLessonButton.addActionListener(new requestActionListener());
        filterBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton but = (JButton) e.getSource();
                but.setSelected(!but.isSelected());
            }
        };

        mondayFilterButton.addActionListener(listener);
        TuesdayFilterButton.addActionListener(listener);
        WednesdayFilterButton.addActionListener(listener);
        fridayFilterButton.addActionListener(listener);
        thursdayFilterButton.addActionListener(listener);
        saturdayFilterButton.addActionListener(listener);
        sundayFilterButton.addActionListener(listener);
        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                if (requestGiveButtonCheckFilter()) {
                    b.setSelected(true);
                } else if (b.isSelected()) {
                    b.setSelected(false);
                }
            }
        };
        givenButton.addActionListener(listener1);
        requestedButton.addActionListener(listener1);
        requestedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (requestedButton.isSelected() &&  isSubmitted) {
                    filterLessons("Requested");
                }
            }
        });
        courseComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (requestGiveButtonCheckFilter() && isSubmitted) {
                    String selectedValue = (String) courseComboBox.getSelectedItem();
                    if (selectedValue != null && !selectedValue.equals("Select:")) {
                        filterLessons(selectedValue);
                    }
                }
            }
        });





        givenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (givenButton.isSelected() && isSubmitted) {
                    filterLessons("Given");
                }
            }
        });



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
    private void setUpCursors() {
        postLessonButton.setFocusable(false);
        requestLessonButton.setFocusable(false);
        postLessonButton.setCursor(handCursor);
        requestLessonButton.setCursor(handCursor);
        filterBoxButton.setCursor(handCursor);
        filtersSubmitButton.setCursor(handCursor);
        mondayFilterButton.setCursor(handCursor);
        saturdayFilterButton.setCursor(handCursor);
        sundayFilterButton.setCursor(handCursor);
        sundayFilterButton.setFocusable(false);

        setUpArrayLists();
        setUpDaysButtons();
        for (JButton j :
                sectionButtons) {
            j.setFocusable(false);
            j.setCursor(handCursor);
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
            }
        };
        ActionListener resetButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton button :
                        dayButtons) {
                    button.setSelected(false);
                }
                postLessonButton.setSelected(false);
                requestLessonButton.setSelected(false);
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
    public void setMain(Main main) {
        this.main =main;
    }

    public RequestablePost getPost() {
        return new LessonPost(1,currentUser,"desc","a",1,false,"S");
    }

    private class requestActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if (requestGiveButtonCheck()) {
                b.setSelected(true);
            } else if (b.isSelected()) {
                b.setSelected(false);
            }
        }
    }
    public void addLessonPost(LessonPost post) {
        g.gridx = 0;
        LessonPostViewer viewer6 = new LessonPostViewer(post,main);
        insideScrollPanePanel.add(viewer6, g);
    }
    public class LesssonPostPostingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (checkIfValid()) {
                // int postId = Database.getNewPostID();
                int postId = 0;
                LessonPost tempPost = new LessonPost(postId, currentUser, textArea1.getText().strip(), (String) courseTypeComboBox.getSelectedItem(), getSelectedDaysBinary(), !postLessonButton.isSelected(), new Date().toString());

                lessonPostArrayList.add(tempPost);

                addLessonPost(tempPost);

                Student s = (Student) currentUser;
                main.update();
                s.addToLessonsTable(tempPost);
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
        System.out.println(returned);
        return returned;
    }
    private void filterLessons(String selectedValue){
        if (isSubmitted) {
        Component[]components = insideScrollPanePanel.getComponents();
        for(Component component: components){
            if( component instanceof LessonPostViewer) {
                LessonPostViewer posts = (LessonPostViewer) component;
                LessonPost post = posts.getLesPost();
                boolean matchesFilter = post.getLessonPost().contains(selectedValue);
                if (matchesFilter) {
                    posts.setVisible(true);
                } else {
                    posts.setVisible(false);
                }

            }
        }insideScrollPanePanel.revalidate();
        insideScrollPanePanel.repaint();
        main.update();
    }}
}


