package CommentsGUI;

import HomePages.HomeMain.HomeMain;
import HomePages.HomeMain.MainInterface;
import Other.Icons.IconCreator;
import Posts.*;
import PostsGUI.ActivitiesPostViewer;
import PostsGUI.LessonPostViewer;
import PostsGUI.PostViewer;
import PostsGUI.StudiesPostViewer;
import SignupAndLogin.LoginFrame;
import TrialMain.TrialMain;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class CommentsMidPanel extends JFrame {
    private JButton backButton;
    private JPanel postDisplayPanel;
    private JPanel commentPosting;
    private JButton postButton1;
    private JPanel insideScrollPanel;
    private JPanel mP;
    private JTextArea textArea1;
    private JPanel innerPanel;
    private JPanel labelPanel;
    private ArrayList<Comment> comments;
    private  Post REQUESTABLE_POST;
    private MainInterface main;
    private boolean isReview;
    private ReviewPanel reviewPanel;
    private JPanel previousPanel;
    private User[] otherUsers;
    PostViewer viewer;
    public CommentsMidPanel(Post post, MainInterface main, JPanel prev)  {
        REQUESTABLE_POST = post;
        previousPanel = prev;
        this.main = main;
        isReview = false;
        if (post instanceof RequestablePost pos)
            isReview = pos.isItReviewer(main.getCurrentUser());

        backButton.setIcon(IconCreator.getIconWithSize(IconCreator.backIcon,50,50));
        GridBagConstraints g2 = new GridBagConstraints();
        //g2.ipady = 200;
        //g2.ipadx = 100;
        g2.gridx = 0;
        g2.anchor = GridBagConstraints.LINE_START;
        g2.fill = GridBagConstraints.HORIZONTAL;
        g2.gridwidth =2;
        if (main instanceof TrialMain trialMain) {
            otherUsers = trialMain.getOtherUsers();
        }else
            REQUESTABLE_POST.setUpPastCommentCollection();
        if (REQUESTABLE_POST instanceof LessonPost){
            viewer = new LessonPostViewer((LessonPost) REQUESTABLE_POST,main);
            postDisplayPanel.add(viewer,g2);}
        else if (REQUESTABLE_POST instanceof ActivityPost) {
            viewer = new ActivitiesPostViewer((ActivityPost) REQUESTABLE_POST, main);
            postDisplayPanel.add(viewer, g2);
        }else{
            viewer = new StudiesPostViewer((StudyPost) REQUESTABLE_POST,main);
            postDisplayPanel.add(viewer,g2);
        }

        comments = post.getCommentCollection();
        for (Comment c : comments) {
            addCommentsPanel(new CommentsPanel(c,main.getCurrentUser()));
        }
        postButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textArea1.getText().isBlank()) {
                    Comment temp = new Comment(main.getCurrentUser(), textArea1.getText());
                    if (isReview)
                        temp = new Review(main.getCurrentUser(), textArea1.getText(),reviewPanel.getRating(),0);
                    post.addComment(temp);
                    if (main instanceof TrialMain mm && main.getCurrentUser().getId() == post.getSender().getId())
                        mm.addNotification(temp,post.getSender());
                    addCommentsPanel(new CommentsPanel(temp,main.getCurrentUser(),isReview,reviewPanel));
                    textArea1.setText("");
                    main.update();
                }
            }
        });
        if (isReview){
            reviewPanel = new ReviewPanel(main,(Student) main.getCurrentUser());
            labelPanel.add(reviewPanel);
        }
        add(mP);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.goBack(viewer);
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
