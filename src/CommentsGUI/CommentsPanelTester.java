package CommentsGUI;

import CommentsRelated.Comment;
import UserRelated.Student;

import javax.swing.*;

public class CommentsPanelTester {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Comment Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 400);

        Student student1 = new Student("Erdem", "", 1, "", "", "", "");
        Student student2 = new Student("Ufuk", "", 1, "", "", "", "");
        Comment comment = new Comment(student1, "asdfasdfasdöfmsadgfnaksdfjgnşkjfsdglkqjflkjqvlksjdfvkqşjrkbjqkşjbfkja" +
                "ksajfnlkajsfşdakjsdfnjkavnkdsjfnvajknsdflkavnlkjsdfnlavndlkfjvnalkjdfkavndkfvnakjdvakjndfkvnakdfnvjnad" +
                "avsojvnasjnvakjsndvkajsnjkdvansljkdaljsfnvajnsfk0anksfjdvanlkjsfvnkajsfn0anfdlvanlkdfjvalkdjfvnlakjvlkn");

        CommentsPanel commentsPanel = new CommentsPanel(comment,student1);
        frame.add(commentsPanel);
        frame.setVisible(true);
    }
}
