package Request.RequestsAndViewers;

import Posts.RequestablePost;
import UserRelated.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RequestMiddlePanelDenied {
    private JPanel panel1;
    private JPanel middlePanel;
    private JLabel requestLabel;
    private JButton backButton;
    private JButton unansweredButton;
    private JButton deniedButton;
    private JButton acceptedButton;
    private JPanel insideScrollPanel;
    private JPanel buttonPanel;
    private RequestablePost requestablePost;
    private ArrayList<Request> deniedRequests;

    public RequestMiddlePanelDenied(RequestablePost requestablePost) {
        this.requestablePost = requestablePost;
        this.deniedRequests = requestablePost.getDeniedCollection();
        Student tutor = new Student("Tutor", null, 22203112, null, null, null, null, null,null);

        Student student1 = new Student("Jack", null, 10, null, null, null, null,null,null);
        Student student2 = new Student("Saul", null, 11, null, null, null, null,null,null);
        Student student3 = new Student("Heisenberg", null, 12, null, null, null, null,null,null);

        student1.setAverageRating(2);
        student2.setAverageRating(3);
        student3.setAverageRating(4);

        GridBagConstraints g2 = new GridBagConstraints();
        g2.gridx = 0;
        g2.anchor = GridBagConstraints.NORTHWEST;
        //g2.ipadx = 100;
        g2.ipady  = 10;


        refresh();
    }

    public void refresh() {
        GridBagConstraints g2 = new GridBagConstraints();
        g2.gridx = 0;
        g2.anchor = GridBagConstraints.NORTHWEST;
        //g2.ipadx = 100;
        g2.ipady  = 10;
        g2.insets = new Insets(0,20,10,0);
        insideScrollPanel.removeAll();
        for (int i = 0; i < requestablePost.pullTheRequestsFromDB().size(); i++) {
            System.out.println("invalid" + i);
            Request request = requestablePost.pullTheRequestsFromDB().get(i);
            if (request instanceof DeniedRequest) {
                insideScrollPanel.add(new DeniedViewer(request),g2);
            }
        }
        insideScrollPanel.repaint();
        insideScrollPanel.revalidate();
    }


    public JPanel getInsideScrollPanel() {
        return insideScrollPanel;
    }
}
