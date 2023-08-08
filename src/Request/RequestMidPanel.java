package Request;

import Posts.LessonPost;
import Posts.RequestablePost;
import UserRelated.Student;

import javax.swing.*;
import java.awt.*;

public class RequestMidPanel extends JFrame {
    private JButton activitiesButton;
    private JButton lessonsButton;
    private JPanel insideScrollPanel;
    private JPanel mP;

    public RequestMidPanel() {
        setSize(700,700);
        Student stu = new Student("Ali", "ali@gmial.com", 123, "Male", "cs", "1"
                ,"1/1/2001");
        RequestablePost post = new LessonPost(1, stu , "asdfasdfasdfasdfaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaf", "Requested",
                61, true, "2/1/2001");

        add(mP);
        addRequestPanel(new RequestPanel(post));
        addRequestPanel(new RequestPanel(post));
        addRequestPanel(new RequestPanel(post));
        addRequestPanel(new RequestPanel(post));
        addRequestPanel(new RequestPanel(post));
        addRequestPanel(new RequestPanel(post));
        addRequestPanel(new RequestPanel(post));
        addRequestPanel(new RequestPanel(post));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addRequestPanel(RequestPanel requestPanel) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.add(requestPanel);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx =0;
        g.anchor = GridBagConstraints.LINE_START;
        insideScrollPanel.add(panel, g);
    }


    public static void main(String[] args) {
        new RequestMidPanel();
    }

}
