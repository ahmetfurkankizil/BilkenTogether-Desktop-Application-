package Request.RequestsAndViewers;

import Posts.RequestablePost;
import UserRelated.Student;

public class UnansweredViewer extends RequestConfirmationPanelViewer {
    public UnansweredViewer(Request request) {
        super(request);
        buttonsPanel.add(tickButton);
        buttonsPanel.add(crossButton);
        deniedPanel.add(buttonsPanel);
        add(deniedPanel);

    }
}
