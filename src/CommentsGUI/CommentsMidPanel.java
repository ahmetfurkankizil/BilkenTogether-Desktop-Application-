package CommentsGUI;

import CommentsRelated.Comment;
import HomePage.LessonsPage.LessonPostViewer;
import Posts.LessonPost;
import Posts.RequestablePost;
import UserRelated.Student;

import javax.swing.*;
import java.awt.*;

public class CommentsMidPanel extends JFrame {
    private JButton postButton;
    private JPanel postDisplayPanel;
    private JPanel commentPosting;
    private JButton postButton1;
    private JPanel insideScrollPanel;
    private JPanel mP;
    private JTextArea textArea1;

    private  RequestablePost REQUESTABLE_POST;

    public CommentsMidPanel(RequestablePost requestablePost){
        REQUESTABLE_POST = requestablePost;
        setSize(700,700);

        Student student1 = new Student("Erdem", "", 1, "", "", "", "");
        Student student2 = new Student("Ufuk", "", 1, "", "", "", "");
        Comment comment = new Comment(student1,student2 , "asdfasdfasdöfmsadgfnaksdfjgnşkjfsdglkqjflkjqvlksjdfvkqşjrkbjqkşjbfkja" +
                "ksajfnlkajsfşdakjsdfnjkavnkdsjfnvajknsdflkavnlkjsdfnlavndlkfjvnalkjdfkavndkfvnakjdvakjndfkvnakdfnvjnad" +
                "avsojvnasjnvakjsndvkajsnjkdvansljkdaljsfnvajnsfk0anksfjdvanlkjsfvnkajsfn0anfdlvanlkdfjvalkdjfvnlakjvlkn");
        add(mP);
        commentPosting.setBackground(Color.GRAY);
        addCommentsPanel(new CommentsPanel(comment));
        addCommentsPanel(new CommentsPanel(comment));
        addCommentsPanel(new CommentsPanel(comment));
        addCommentsPanel(new CommentsPanel(comment));
        addCommentsPanel(new CommentsPanel(comment));
        addCommentsPanel(new CommentsPanel(comment));
        addCommentsPanel(new CommentsPanel(comment));
        addCommentsPanel(new CommentsPanel(comment));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (REQUESTABLE_POST instanceof LessonPost)
            postDisplayPanel.add(new LessonPostViewer((LessonPost) REQUESTABLE_POST));
        else
            postDisplayPanel.add(new LessonPostViewer((LessonPost) REQUESTABLE_POST));
        setVisible(true);
    }

    private void addCommentsPanel(CommentsPanel commentsPanel) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.add(commentsPanel);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx =0;
        g.anchor = GridBagConstraints.LINE_START;
        insideScrollPanel.add(panel, g);
    }
    public static void main(String[] args) {
        Student stu = new Student("Ali", "ali@gmial.com", 123, "Male", "cs", "1"
                ,"1/1/2001");
        RequestablePost post = new LessonPost(1, stu , "asdfasdfasdfasdfaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaf", "Requested",
                61, true, "2/1/2001");
        new CommentsMidPanel(post);
    }
}
