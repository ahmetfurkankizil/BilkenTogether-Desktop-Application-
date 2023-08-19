package Request.RequestsAndViewers;

import Posts.RequestablePost;
import UserRelated.Student;
import UserRelated.User;

public class AcceptedRequest extends Request{

    public AcceptedRequest(int requesterId) {
        super(requesterId);
    }
    public AcceptedRequest(User requester) {
        super(requester);
    }
}
