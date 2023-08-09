package PostComponents;

import HomePage.Main.Main;
import Icons.IconCreator;
import Posts.Post;
import Posts.StudyPost;
import UserProfileGUI.PPImageHandler;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;

public class StudiesPostViewer extends PostViewer {


    private JLabel header;
    private JLabel fileNameLabel;
    private JLabel authorLabel;
    private StudyPost lesPost;
    private User sender;


    public StudiesPostViewer(StudyPost p, Main main) {
        super(main);
        this.lesPost = p;
        setUp();
        contentSetUp();

        g.fill = GridBagConstraints.NONE;
        g.gridy += 1;
        g.gridx = 1;
        setUpInformationPanel();

        g.gridy += 1;
        g.gridx = 1;
    }

    public void setUp() {
        super.setUp();
        sender = lesPost.getSender();
        proName.setText(sender.getName());
        //proPhoto = new JLabel(sender.getProfilePhoto());
        proPhoto = new PPImageHandler();
        header = new JLabel(lesPost.getStudyPostHeading());
        header.setFont(headerFont);
    }

    @Override
    public Post getPost() {
        return lesPost;
    }

    public void contentSetUp() {
        g.weightx = 2;

        g.gridx = 0;
        g.gridy = 0;
        g.insets = new Insets(10, 0, 0, 10);
        add(proPhoto, g);
        g.gridx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.ipady = 0;
        g.insets = new Insets(0, 0, 4, 0);
        add(proName, g);
        g.gridy = 1;
        add(header, g);

        g.gridy += 1;
        g.insets = new Insets(0, 0, 0, 0);
        g.fill = GridBagConstraints.BOTH;
        textArea2.setMargin(new Insets(4, 0, 10, 0));
        //textArea2.setMaximumSize(new Dimension(500,300));
        textArea2.setText("    " + lesPost.getPostDescription());
        add(textArea2, g);
        g.weightx = 1;
    }

    public void setUpInformationPanel() {
        g.anchor = GridBagConstraints.WEST;

        topInformationPanel.add(commentLabel);
        authorLabel = new JLabel(lesPost.getAuthor());
        authorLabel.setIcon(IconCreator.getIconWithSize(IconCreator.userIcon, 10, 12));
        authorLabel.setBackground(informationBackground);
        authorLabel.setOpaque(true);
        addPadding(authorLabel);
        topInformationPanel.add(authorLabel);
        addTopics();
        add(topInformationPanel, g);
        g.gridy += 1;
        //add(bottomIformationPanel, g);
    }

    private void addTopics() {
        bottomIformationPanel = new JPanel();
        for (String topic : lesPost.getTopicCollection()) {
            if (topic != null) {
                JLabel topicLabel = new JLabel(topic);
                topicLabel.setBackground(new Color(213,190,213));
                addPadding(topicLabel,3,5,3,5);
                topicLabel.setOpaque(true);
                topInformationPanel.add(topicLabel);
                System.out.println("Not Empty");
            }
        }

    }

    public StudyPost getStudyPost() {
        return lesPost;
    }
}
