package Request.RequestsAndViewers;

import Posts.RequestablePost;
import UserRelated.Student;

public class AcceptedViewer extends RequestConfirmationPanelViewer {

    public AcceptedViewer(Request request) {
        super(request);
        buttonsPanel.add(tickButton);
        deniedPanel.add(buttonsPanel);
        add(deniedPanel);
    }
}
