package CommentsGUI;

import HomePages.HomeMain.HomeMain;
import Other.Icons.IconCreator;
import PostsGUI.ActivitiesPostViewer;
import PostsGUI.LessonPostViewer;
import PostsGUI.PostViewer;
import PostsGUI.StudiesPostViewer;
import Posts.ActivityPost;
import Posts.LessonPost;
import Posts.Post;
import Posts.StudyPost;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CommentsMidPanel extends JFrame {
    private JButton backButton;
    private JPanel postDisplayPanel;
    private JPanel commentPosting;
    private JButton postButton1;
    private JPanel insideScrollPanel;
    private JPanel mP;
    private JTextArea textArea1;
    private JPanel innerPanel;
    private ArrayList<Comment> comments;
    private  Post REQUESTABLE_POST;
    private HomeMain main;
    private boolean isReview;
    private ReviewPanel reviewPanel;
    private JPanel previousPanel;
    PostViewer viewer;
    public CommentsMidPanel(Post post, HomeMain main, JPanel prev)  {
        REQUESTABLE_POST = post;
        previousPanel = prev;
        this.main = main;
        isReview = false;
        //reviewPanel =new ReviewPanel(main,(Student) main.getCurrentUser());
     /*   if (post instanceof LessonPost ){
            LessonPost temp = (LessonPost) post;
            Student acceptor= temp.getAccepter();
            if (acceptor!= null && acceptor.getId() == main.getCurrentUser().getId()){
                GridBagConstraints g2 = new GridBagConstraints();
                g2.gridx = 0;
                commentPosting.add(reviewPanel);
                isReview= true;
            }
        }
      */
        backButton.setIcon(IconCreator.getIconWithSize(IconCreator.backIcon,50,50));
        GridBagConstraints g2 = new GridBagConstraints();
        //g2.ipady = 200;
        //g2.ipadx = 100;
        g2.gridx = 0;
        g2.anchor = GridBagConstraints.LINE_START;
        g2.fill = GridBagConstraints.HORIZONTAL;
        g2.gridwidth =2;
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
        for (Comment c :
                comments) {
            addCommentsPanel(new CommentsPanel(c,main.getCurrentUser(),isReview,reviewPanel));
        }
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        postButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textArea1.getText().isBlank()) {
                    Comment temp = new Comment(main.getCurrentUser(), textArea1.getText());
                    post.addComment(temp);
                    if (isReview){
                        add(new JLabel());
                    }
                    addCommentsPanel(new CommentsPanel(temp,main.getCurrentUser(),isReview,reviewPanel));
                    textArea1.setText("");
                    main.update();
                }
            }
        });
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
