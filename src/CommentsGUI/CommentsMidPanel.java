package CommentsGUI;

import CommentsRelated.Comment;
import HomePage.Main.Main;
import PostComponents.ActivitiesPostViewer;
import PostComponents.LessonPostViewer;
import PostComponents.StudiesPostViewer;
import Posts.*;
import UserRelated.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommentsMidPanel extends JFrame {
    private JButton postButton;
    private JPanel postDisplayPanel;
    private JPanel commentPosting;
    private JButton postButton1;
    private JPanel insideScrollPanel;
    private JPanel mP;
    private JTextArea textArea1;
    private JPanel innerPanel;
    private ArrayList<Comment> comments;
    private  Post REQUESTABLE_POST;
    private Main main;

    public CommentsMidPanel(Post post, Main main) throws ParseException {
        REQUESTABLE_POST = post;
        setSize(700,700);
        this.main = main;
        Student student1 = new Student("Erdem", "", 1, "", "", "", "");
        Student student2 = new Student("Ufuk", "", 1, "", "", "", "");
        Comment comment = new Comment(student1, "asdfasdfasdöfmsadgfnaksdfjgnşkjfsdglkqjflkjqvlksjdfvkqşjrkbjqkşjbfkja" +
                "ksajfnlkajsfşdakjsdfnjkavnkdsjfnvajknsdflkavnlkjsdfnlavndlkfjvnalkjdfkavndkfvnakjdvakjndfkvnakdfnvjnad" +
                "avsojvnasjnvakjsndvkajsnjkdvansljkdaljsfnvajnsfk0anksfjdvanlkjsfvnkajsfn0anfdlvanlkdfjvalkdjfvnlakjvlkn");
        add(mP);
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat x = new SimpleDateFormat(pattern);
        GridBagConstraints g2 = new GridBagConstraints();
        //g2.ipady = 200;
        //g2.ipadx = 100;
        g2.gridx = 0;
        g2.anchor = GridBagConstraints.LINE_START;
        g2.fill = GridBagConstraints.HORIZONTAL;
        g2.gridwidth =2;
        if (REQUESTABLE_POST instanceof LessonPost)
            postDisplayPanel.add(new LessonPostViewer((LessonPost) REQUESTABLE_POST,null),g2);
        else if (REQUESTABLE_POST instanceof ActivityPost)
            postDisplayPanel.add(new ActivitiesPostViewer((ActivityPost) REQUESTABLE_POST,null),g2);
        else
            postDisplayPanel.add(new StudiesPostViewer((StudyPost) REQUESTABLE_POST,null),g2);


        comments = post.getCommentCollection();
        for (Comment c :
                comments) {
            addCommentsPanel(new CommentsPanel(c,main.getCurrentUser()));
        }
        addCommentsPanel(new CommentsPanel(comment,main.getCurrentUser()));
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        postButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textArea1.getText().isBlank()) {
                    Comment temp = new Comment(main.getCurrentUser(), textArea1.getText());
                    post.addComment(temp);
                    addCommentsPanel(new CommentsPanel(temp,main.getCurrentUser()));
                    main.update();
                }
            }
        });
    }

    private void addCommentsPanel(CommentsPanel commentsPanel) {
        JPanel panel = new JPanel();

        panel.add(commentsPanel);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx =0;
        g.anchor = GridBagConstraints.NORTHWEST;
        insideScrollPanel.add(panel, g);
    }
    public JPanel getInnerPanel(){
        return innerPanel;
    }
}
