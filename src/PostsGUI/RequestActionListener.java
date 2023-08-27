package PostsGUI;

import Posts.RequestablePost;
import Request.RequestsAndViewers.UnansweredRequest;
import UserRelated.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequestActionListener implements ActionListener {
    RequestablePost post;
    Student user;
    public RequestActionListener(RequestablePost post, Student user){
        this.post = post;
        this.user = user;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        button.setText("Request Sent!");
        button.setEnabled(false);
        post.addRequest(new UnansweredRequest(user.getId()));
        button.setBackground(new Color(228, 232, 183));
        button.setOpaque(true);
        button.setBorder(new EmptyBorder(5,5,5,5));
    }
}
