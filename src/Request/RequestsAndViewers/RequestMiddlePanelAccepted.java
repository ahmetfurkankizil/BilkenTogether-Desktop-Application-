package Request.RequestsAndViewers;

import Posts.RequestablePost;
import UserRelated.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RequestMiddlePanelAccepted {
    private JPanel panel1;
    private JPanel middlePanel;
    private JLabel requestLabel;
    private JPanel buttonPanel;
    private JButton backButton;
    private JButton unansweredButton;
    private JButton deniedButton;
    private JButton acceptedButton;
    private JPanel insideScrollPanel;
    private RequestablePost requestablePost;
    private ArrayList<Request> acceptedRequest;
    public RequestMiddlePanelAccepted(RequestablePost requestablePost) {
        this.acceptedRequest = requestablePost.getAgreementCollection();
        this.requestablePost = requestablePost;
        GridBagConstraints g2 = new GridBagConstraints();
        g2.gridx = 0;
        g2.anchor = GridBagConstraints.NORTHWEST;
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
            Request request = requestablePost.pullTheRequestsFromDB().get(i);
            if (request instanceof AcceptedRequest) {
                insideScrollPanel.add(new AcceptedViewer(request),g2);
            }
        }
        insideScrollPanel.repaint();
        insideScrollPanel.revalidate();
    }

    public JPanel getInsideScrollPanel() {
        return insideScrollPanel;
    }

}
