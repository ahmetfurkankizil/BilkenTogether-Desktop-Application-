package CommentsGUI;

import CommentsRelated.Comment;
import Icons.IconCreator;

import javax.swing.*;
import java.awt.*;

public class CommentsPanel extends JPanel{

    private final Comment COMMENT;

    public CommentsPanel(Comment comment) {
        super();
        COMMENT = comment;
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
        JLabel nameSurnameLabel = new JLabel(COMMENT.getCommenter().getName());
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(nameSurnameLabel, c);

        // Comment Text Area
        JTextArea commentTextArea = new JTextArea();
        commentTextArea.setColumns(50);
        commentTextArea.setText(COMMENT.getContent());
        commentTextArea.setLineWrap(true);
        commentTextArea.setEditable(false);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        add(commentTextArea, c);

        // Like Label
        JLabel hearthLabel = new JLabel(IconCreator.getIconWithSize(new ImageIcon("C:\\CS102_Project\\CS-Project-Repository\\src\\Icons\\heart-regular.png"),30,30));
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(hearthLabel, c);

        // like Count Label
        JLabel typeNameLabel = new JLabel(COMMENT.getLikeCount() + "");
        c.gridx = 2;
        add(typeNameLabel, c);

    }


}
