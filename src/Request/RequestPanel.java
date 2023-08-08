package Request;

import Posts.ActivityPost;
import Posts.LessonPost;
import Posts.RequestablePost;

import javax.swing.*;
import java.awt.*;

public class RequestPanel extends JPanel {

    private final RequestablePost REQ_POST;

    public RequestPanel(RequestablePost reqPost) {
        super();
        this.REQ_POST = reqPost;
        setLayout(new GridBagLayout());
        addComponents();
    }

    private void addComponents() {
        GridBagConstraints c = new GridBagConstraints();

        // Profile Photo Label
        JLabel profilePhotoLabel = new JLabel("Profile Photo");
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,5,5,5);
        add(profilePhotoLabel, c);

        // Name and Surname Label
        JLabel nameSurnameLabel = new JLabel(REQ_POST.getSender().getName());
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(nameSurnameLabel, c);

        // Request Text Area
        JTextArea requestTextArea = new JTextArea();
        requestTextArea.setColumns(50);
        requestTextArea.setText(REQ_POST.getPostDescription());
        requestTextArea.setLineWrap(true);
        requestTextArea.setEditable(false);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        add(requestTextArea, c);



        // Request Status Label
        JLabel requestStatusLabel = new JLabel();
        if (REQ_POST instanceof LessonPost lesPost) {
            requestStatusLabel.setText(lesPost.getRequestType() ? "LESSON REQUEST" : "LESSON GIVE");
        } else if (REQ_POST instanceof ActivityPost actPost) {
            requestStatusLabel.setText(actPost.getTypeFilter());
        }
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(requestStatusLabel, c);

        // Type Name Label
        JLabel typeNameLabel = new JLabel(REQ_POST.getTypeFilter());
        c.gridx = 2;
        add(typeNameLabel, c);

        // Request Number Label
        JLabel requestNumberLabel = new JLabel("Number");
        c.gridx = 3;
        add(requestNumberLabel, c);
    }

}
