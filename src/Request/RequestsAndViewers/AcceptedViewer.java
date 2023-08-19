package Request.RequestsAndViewers;

import Posts.RequestablePost;
import UserRelated.Student;
import UserRelated.User;

public class AcceptedViewer extends RequestConfirmationPanelViewer {

    public AcceptedViewer(Request request) {
        super(request);
        buttonsPanel.add(tickButton);
        deniedPanel.add(buttonsPanel);
        add(deniedPanel);
    }
    public AcceptedViewer(Request request, User requester) {
        super(request,requester);
        buttonsPanel.add(tickButton);
        deniedPanel.add(buttonsPanel);
        add(deniedPanel);
    }
}
