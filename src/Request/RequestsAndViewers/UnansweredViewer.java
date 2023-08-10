package Request.RequestsAndViewers;

import DatabaseRelated.DatabaseConnection;
import Posts.RequestablePost;
import UserRelated.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnansweredViewer extends RequestConfirmationPanelViewer {
    private Request request;
    public DatabaseConnection databaseConnection;
    private RequestablePost requestablePost;
    public UnansweredViewer(Request request, RequestablePost requestablePost) {
        super(request);
        this.requestablePost = requestablePost;
        this.request = request;
        tickButton.addActionListener(new TickButtonListener());
        buttonsPanel.add(tickButton);
        buttonsPanel.add(crossButton);
        deniedPanel.add(buttonsPanel);
        add(deniedPanel);

    }

    private class TickButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            requestablePost.acceptRequest(request);
            setVisible(false);

        }
    }

}
