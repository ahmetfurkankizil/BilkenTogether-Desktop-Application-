package Request.RequestsAndViewers;

import Posts.RequestablePost;
import Request.RequestsAndViewers.Request;
import UserRelated.Student;
import UserRelated.User;


public class UnansweredRequest extends Request {
    public UnansweredRequest(int requesterId) {
        super(requesterId);
    }
    public UnansweredRequest(User requester) {
        super(requester);
    }
}
