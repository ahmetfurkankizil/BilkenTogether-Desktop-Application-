package PostsGUI;

import HomePages.HomeMain.HomeMain;
import Other.Icons.IconCreator;
import Posts.StudyPost;
import UserProfileGUI.PPImageHandler;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StudiesPostViewer extends PostViewer {


    private JLabel header;
    private JLabel fileNameLabel;
    private JLabel authorLabel;
    private StudyPost lesPost;
    private User sender;
    private JButton fileOpenerButton;


    public StudiesPostViewer(StudyPost p, HomeMain main) {
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
        proPhoto = new PPImageHandler(sender);
        header = new JLabel(lesPost.getStudyPostHeading());
        header.setFont(headerFont);
    }

    @Override
    public StudyPost getPost() {
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
        add(bottomIformationPanel, g);
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
            }
        }
        if (lesPost.getStudyFile() != null) {
            fileOpenerButton = new JButton("File");
            fileOpenerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    byte data[] = lesPost.getStudyFile();
                    JFileChooser fileChooser = new JFileChooser();
                    // Some init code, if you need one, like setting title
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                    int returnVal = fileChooser.showOpenDialog(new JFrame());
                    File destination1;
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        destination1 = fileChooser.getSelectedFile();
                    } else
                        return;
                    File destination = new File(destination1.getAbsolutePath() + "/downloaded.pdf");
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(destination);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        fos.write(data);
                        JOptionPane.showMessageDialog(null, "PDF Saved");
                        fos.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });
            bottomIformationPanel.add(fileOpenerButton);
        }
    }

    public StudyPost getStudyPost() {
        return lesPost;
    }
}
