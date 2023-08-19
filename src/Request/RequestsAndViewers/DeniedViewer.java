package Request.RequestsAndViewers;

import Posts.RequestablePost;
import UserRelated.Student;
import UserRelated.User;

public class DeniedViewer extends RequestConfirmationPanelViewer {
    public DeniedViewer(Request request) {
        super(request);
        buttonsPanel.add(crossButton);
        deniedPanel.add(buttonsPanel);
        add(deniedPanel);
    }
    public DeniedViewer(Request request, User requester) {
        super(request);
        buttonsPanel.add(crossButton);
        deniedPanel.add(buttonsPanel);
        add(deniedPanel);
    }
}
