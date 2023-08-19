package Request;

import HomePages.HomeMain.HomeMain;
import HomePages.HomeMain.MainInterface;
import Posts.ActivityPost;
import Posts.LessonPost;
import Request.RequestsAndViewers.*;
import TrialMain.TrialMain;
import UserRelated.Student;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class RequestMidPanel extends JFrame {
    private JButton activitiesButton;
    private JButton lessonsButton;
    private JPanel insideScrollPanel;
    private JPanel mP;
    private JPanel inPanel;
    private JPanel lessonsPostss;
    private JPanel activityPostss;
    private MainInterface main;
    private Student user;
    private RequestMiddlePanelUnanswered requestsExtended;
    private ArrayList<LessonPost> lessonPosts;
    private ArrayList<ActivityPost> activityPosts;

    public RequestMidPanel(MainInterface main) {
        this.main = main;
        this.user = (Student) main.getCurrentUser();
        setSize(700,700);
        add(mP);
        activityPostss.setVisible(false);

        lessonPosts = user.pullFromLessonsPostTable();
        activityPosts = user.pullFromActivitiesPostTable();
        if (lessonPosts != null && activityPosts != null)
            initialize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        lessonsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessonsPostss.setVisible(true);
                activityPostss.setVisible(false);
            }
        });
        activitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessonsPostss.setVisible(false);
                activityPostss.setVisible(true);
            }
        });
    }

    public JPanel getInPanel() {
        return inPanel;
    }


    private void addToLessonsPanel(RequestPanel requestPanel) {
        JPanel panel = new JPanel();
        panel.add(requestPanel);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx =0;
        g.anchor = GridBagConstraints.LINE_START;
        lessonsPostss.add(panel, g);
    }
    private void addToActivitiesPanel(RequestPanel requestPanel){
        JPanel panel = new JPanel();
        panel.add(requestPanel);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx =0;
        g.anchor = GridBagConstraints.LINE_START;
        activityPostss.add(panel, g);
    }
    public void refresh(){
        lessonsPostss.removeAll();
        activityPostss.removeAll();
        initialize();
        lessonsPostss.repaint();
        lessonsPostss.revalidate();
        activityPostss.repaint();
        activityPostss.revalidate();
    }
    private void initialize(){
        Random random = new Random();
        for (int i = 0; i < lessonPosts.size(); i++) {
            if (lessonPosts.get(i).getRequestCollection().isEmpty()&& main instanceof TrialMain trialMain)
                lessonPosts.get(i).addRequest(getRandomRequest(trialMain.getAllUsers()[random.nextInt(1,trialMain.getAllUsers().length)]));
            addToLessonsPanel(new RequestPanel(lessonPosts.get(i),main));
        }
        for (int i = 0; i < activityPosts.size(); i++) {
            if (activityPosts.get(i).getRequestCollection().isEmpty() && main instanceof TrialMain trialMain)
                activityPosts.get(i).addRequest(getRandomRequest(trialMain.getAllUsers()[random.nextInt(1,trialMain.getAllUsers().length)]));
            addToActivitiesPanel(new RequestPanel(activityPosts.get(i),main));
        }
    }
    public Request getRandomRequest(User user){
        Random random = new Random();
        if (random.nextBoolean())
            return new UnansweredRequest(user);
        else if (random.nextBoolean()) {
            return new DeniedRequest(user);
        }else
            return new AcceptedRequest(user);
    }
}
