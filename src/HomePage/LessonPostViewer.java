package HomePage;

import PostComponents.DayButtons;
import Posts.LessonPost;
import UserProfileGUI.PPImageHandler;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LessonPostViewer extends PostViewer {
    private JLabel typeLabel;
    private JLabel topicLabel;
    private ArrayList<DayButtons> dayButtonsList;
    private LessonPost lesPost;
    private JButton requestButton;
    private User sender;

    public LessonPostViewer() {

    }

    public LessonPostViewer(LessonPost p) {
        this.lesPost = p;

        setUp();
        contentSetUp();

        g.fill = GridBagConstraints.NONE;
        g.gridy += 1;
        g.gridx = 1;
        setUpInformationPanel();

        g.gridy += 1;
        g.gridx = 1;
        addDaysWithBinaryBoolean();
        setUpDays();
    }

    public void setUp() {
        super.setUp();
        proPhoto = new PPImageHandler();
        requestButton = new JButton("Send Request");
        requestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sender = lesPost.getSender();
    }

    public void contentSetUp() {

        proName.setText(lesPost.getSender().getName());
        g.gridx = 0;
        g.gridy = 0;
        g.insets = new Insets(10, 0, 0, 10);
        add(proPhoto, g);

        g.gridx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.ipady = 0;
        g.insets = new Insets(10, 0, 10, 0);
        add(proName, g);

        g.gridy = 1;
        g.insets = new Insets(0, 0, 0, 0);
        g.fill = GridBagConstraints.BOTH;
        textArea2.setText(lesPost.getPostDescription());
        add(textArea2, g);
    }

    public void setUpInformationPanel() {
        g.anchor = GridBagConstraints.WEST;
        messageLabel = new JLabel(envelope);

        topInformationPanel.add(commentLabel);
        topInformationPanel.add(messageLabel);

        // will insert p.getType() here
        typeLabel = new JLabel(lesPost.getRequestType() ? "LESSON REQUEST" : "LESSON GIVE");
        typeLabel.setBackground(informationBackground);
        typeLabel.setOpaque(true);
        addPadding(typeLabel);
        topInformationPanel.add(typeLabel);

        // will insert p.getTopic() here
        topicLabel = new JLabel(lesPost.getTypeFilter());
        topicLabel.setBackground(informationBackground);
        addPadding(topicLabel);
        topicLabel.setOpaque(true);
        topInformationPanel.add(topicLabel, g);
        requestButton.addActionListener(new RequestActionListener());
        topInformationPanel.add(requestButton);
        add(topInformationPanel, g);

    }

    public void setUpDays() {
        bottomIformationPanel = new JPanel();
        for (DayButtons d :
                dayButtonsList) {
            d.removeActionListener(d);
            bottomIformationPanel.add(d);
        }
        add(bottomIformationPanel, g);

    }


    private void addDaysWithBinaryBoolean() {
        int binaryboolean = lesPost.getDateBinaryBoolean();

        dayButtonsList = new ArrayList<>();
        boolean[] daysAvailable = lesPost.getDaysAvailable();
        String day = "";
        for (int i = 0; i < daysAvailable.length; i++) {
            if (daysAvailable[i]) {
                switch (i) {
                    case 0:
                        day = "Monday";
                        break;
                    case 1:
                        day = "Tuesday";
                        break;
                    case 2:
                        day = "Wednesday";
                        break;
                    case 3:
                        day = "Thursday";
                        break;
                    case 4:
                        day = "Friday";
                        break;
                    case 5:
                        day = "Saturday";
                        break;
                    case 6:
                        day = "Sunday";
                        break;
                }
                dayButtonsList.add(new DayButtons(day));
            }
        }


    }
}
