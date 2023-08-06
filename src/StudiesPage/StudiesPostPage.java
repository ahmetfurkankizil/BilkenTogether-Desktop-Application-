package StudiesPage;

import UserProfileGUI.PPImageHandler;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudiesPostPage extends JFrame{
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
    private GridBagConstraints g;
    private User currentUser;
    public StudiesPostPage() {

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 800);
        currentUser = new Student("Erdem", "erdem.p", 22203112, "l", "d", "p", "b");
        generalSetup();


        setVisible(true);
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
        StudiesPostPage page = new StudiesPostPage();
    }
}
