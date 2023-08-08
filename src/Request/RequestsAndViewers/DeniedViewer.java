package Request.RequestsAndViewers;

import Posts.RequestablePost;
import UserRelated.Student;

public class DeniedViewer extends RequestConfirmationPanelViewer {
    public DeniedViewer(Request request) {
        super(request);
        buttonsPanel.add(crossButton);
        deniedPanel.add(buttonsPanel);
        add(deniedPanel);
    }
}
