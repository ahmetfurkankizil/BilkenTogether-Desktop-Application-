package HomePage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HomeScreen extends JFrame {
    private JPanel mainPanel;
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
    private JLabel profilePhotoLabel;
    private JPanel secondMainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel middlePanel;
    private JLabel bilkenTogetherLabel;
    private JPanel buttonPanel;
    private JScrollPane flowScrollPane;
    private JLabel courseType;
    private JComboBox courseTypeComboBox;
    private JButton postButton;
    private JLabel availableDaysLabel;
    private JComboBox availableDaysComboBox;
    private JPanel insideScrollPanePanel;
    private JButton postLessonButton;
    private JButton requestLessonButton;
    private JTextArea textArea1;
    private ArrayList<JButton> sectionButtons;
    private ArrayList<JLabel>  leftPanelLabels;

    public HomeScreen(){
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,800);

        setVisible(true);
    }
    public void generalSetup(){
        sectionButtons = new ArrayList<>();
        leftPanelLabels = new ArrayList<>();
        sectionButtons.add(lessonsButton);
        sectionButtons.add(activitiesButton);
        sectionButtons.add(studiesButton);

        leftPanelLabels.add(homeLabel);
        leftPanelLabels.add(notificationsLabel);
        leftPanelLabels.add(messagesLabel);
        leftPanelLabels.add(profileLabel);
        leftPanelLabels.add(requestsLabel);

        for (JButton j:
             sectionButtons) {
            j.setFocusable(false);
            j.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

    }

    public static void main(String[] args) {
        HomeScreen homeScreen = new HomeScreen();
    }
}
