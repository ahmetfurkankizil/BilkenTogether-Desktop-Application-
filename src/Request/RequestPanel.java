package Request;

import Posts.ActivityPost;
import Posts.LessonPost;
import Posts.RequestablePost;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;

public class RequestPanel extends JPanel {

    private JLabel profilePhotoLabel;
    private JLabel nameSurnameLabel;
    private JTextArea requestTextArea;
    private JLabel requestStatusLabel;
    private JLabel typeNameLabel;
    private JLabel requestNumberLabel;
    private RequestablePost reqPost;

    public RequestPanel(RequestablePost reqPost) {
        super();
        this.reqPost = reqPost;
        setLayout( new GridBagLayout());
        addComponents();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 400);
        frame.setLocationRelativeTo(null);
        User sender = new Student("Ufuk", "a", 1,"male","cs", "132", "06/05/2001");
        RequestablePost requestablePost = new LessonPost(1, sender, "xxxxx", "CS",
                123, true, "1/1/2001");
        RequestPanel requestPanel = new RequestPanel(requestablePost);
        requestPanel.setPreferredSize( new Dimension(900, 300));
        requestPanel.setForeground(Color.BLUE);
        frame.add(requestPanel);
        frame.setVisible(true);
    }
    private void addComponents() {
        GridBagConstraints constrains = new GridBagConstraints();
        constrains.fill = GridBagConstraints.BOTH;
        constrains.insets = new Insets(5,5,5,5); // Padding

        // Add components with appropriate constrains
        // profilePhotoLabel
        profilePhotoLabel = new JLabel("Profile Photo");
        constrains.gridx = 0;
        constrains.gridy = 0;
        add(profilePhotoLabel,constrains);

        // nameSurnameLabel
        nameSurnameLabel = new JLabel(reqPost.getSender().getName());
        constrains.gridx = 1; //constrains.gridy = 0;
        add(nameSurnameLabel,constrains);

        // requestTextArea
        requestTextArea = new JTextArea();
        requestTextArea.setText(reqPost.getPostDescription());
        requestTextArea.setLineWrap(true);
        requestTextArea.setEditable(false);
        constrains.gridx = 0;
        constrains.gridy = 1;
        add(requestTextArea, constrains);

        // requestStatusLabel
        requestStatusLabel = new JLabel();
        if(reqPost instanceof LessonPost) {
            LessonPost lesPost = (LessonPost) reqPost;
            requestStatusLabel.setText(lesPost.getRequestType() ? "LESSON REQUEST" : "LESSON GIVE");
        } else if(reqPost instanceof ActivityPost) {
            ActivityPost actPost = (ActivityPost) reqPost;
            requestStatusLabel.setText(actPost.getTypeFilter());
        }
        constrains.gridx = 0;
        constrains.gridy = 2;
        add(requestStatusLabel, constrains);

        // lessonsNameLabel
        typeNameLabel = new JLabel(reqPost.getTypeFilter());
        constrains.gridx = 1; // constrains.gridy = 2;
        add(typeNameLabel, constrains);

        // requestNumberLabel
        requestNumberLabel = new JLabel("Number");
        constrains.gridx = 2; // constrains.gridy = 2;
        add(requestNumberLabel, constrains);

    }

}
