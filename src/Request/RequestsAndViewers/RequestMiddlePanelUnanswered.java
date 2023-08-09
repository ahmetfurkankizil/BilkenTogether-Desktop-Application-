package Request.RequestsAndViewers;

import HomePage.Main.Main;
import Posts.LessonPost;
import Posts.RequestablePost;
import UserRelated.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RequestMiddlePanelUnanswered {
    private JPanel panel1;
    private JPanel buttonPanel;
    private JLabel requestLabel;
    private JPanel middlePanel;
    private JButton unansweredButton;
    private JButton deniedButton;
    private JPanel insideScrollPanel;
    private JButton acceptedButton;
    private JButton backButton;
    private JPanel holdingPanel;
    private  RequestMiddlePanelDenied deniedPanel;
    private RequestMiddlePanelAccepted acceptedPanel;
    private RequestablePost requestablePost;
    private ArrayList<Request> unansweredRequests;

    public RequestMiddlePanelUnanswered(RequestablePost requestablePost){

        this.requestablePost = requestablePost;

        Student tutor = new Student("Tutor", null, 22203112, null, null, null, null);

        Student student1 = new Student("Jack", null, 22203112, null, null, null, null);
        Student student2 = new Student("Saul", null, 11, null, null, null, null);
        Student student3 = new Student("Heisenberg", null, 12, null, null, null, null);

        student1.setAverageRating(2);
        student2.setAverageRating(3);
        student3.setAverageRating(4);
        GridBagConstraints g2 = new GridBagConstraints();
        g2.gridx = 0;
        g2.anchor = GridBagConstraints.NORTHWEST;
        g2.ipadx = 100;
        g2.ipady  = 10;

        requestablePost.addRequest(new UnansweredRequest(22103566));
        this.unansweredRequests = requestablePost.getRequestCollection();
        holdingPanel.add(new JLabel("ekleniyor"),g2);
        deniedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideScrollPanel.removeAll();
                insideScrollPanel.add(deniedPanel.getInsideScrollPanel(),g2);
                insideScrollPanel.repaint();
                insideScrollPanel.revalidate();
                deniedPanel.refresh();
            }
        });
        unansweredButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideScrollPanel.removeAll();
                insideScrollPanel.add(holdingPanel,g2);
                insideScrollPanel.repaint();
                insideScrollPanel.revalidate();
            }
        });
        acceptedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideScrollPanel.removeAll();
                insideScrollPanel.add(acceptedPanel.getInsideScrollPanel(),g2);
                insideScrollPanel.repaint();
                insideScrollPanel.revalidate();
                acceptedPanel.refresh();
            }
        });
        refresh();
        setUpPages();
    }

    public void refresh() {
        GridBagConstraints g2 = new GridBagConstraints();

        g2.gridx = 0;
        g2.anchor = GridBagConstraints.NORTHWEST;
        g2.ipadx = 100;
        g2.ipady = 10;

        for (int i = 0; i < requestablePost.pullTheRequestsFromDB().size(); i++) {
            Request request = requestablePost.pullTheRequestsFromDB().get(i);
            if (request instanceof UnansweredRequest && unansweredRequests.contains(request)) {

                holdingPanel.add(new UnansweredViewer(request, requestablePost), g2);
                System.out.println("grdi");

            }
        }
    }
    private void setUpPages() {
        acceptedPanel = new RequestMiddlePanelAccepted(requestablePost);
        deniedPanel = new RequestMiddlePanelDenied(requestablePost);
    }
    public JPanel getMiddlePanel(){
        return middlePanel;
    }



    public JPanel getInsideScrollPanel() {
        return holdingPanel;
    }
    private Main main;
    public void setMain(Main main) {
        this.main = main;
    }
}
