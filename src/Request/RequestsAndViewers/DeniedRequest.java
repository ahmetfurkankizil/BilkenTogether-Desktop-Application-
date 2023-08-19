package Request.RequestsAndViewers;

import Posts.RequestablePost;
import UserRelated.Student;
import UserRelated.User;

public class DeniedRequest extends Request {
    public DeniedRequest(int requesterId) {
        super(requesterId);
    }
    public DeniedRequest(User requester) {
        super(requester);
    }
}
