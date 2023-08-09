package Request.RequestsAndViewers;

import Posts.RequestablePost;
import UserRelated.Student;

import java.util.Random;

public abstract class Request {
    private int requesterId;

    public Request(int requesterId) {
        this.requesterId = requesterId;
    }

    public int getRequesterID() {
        return this.requesterId;
    }

    public void setRequesterID(int requesterId) {
        this.requesterId = requesterId;
    }
}
