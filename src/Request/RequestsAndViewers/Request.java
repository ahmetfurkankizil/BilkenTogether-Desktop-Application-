package Request.RequestsAndViewers;

import Posts.RequestablePost;
import SignupAndLogin.LoginFrame;
import UserRelated.Student;
import UserRelated.User;
import com.mysql.cj.log.Log;

import java.util.Random;

public abstract class Request {
    private int requesterId;
    private User requester;


    public Request(int requesterId) {
        this.requesterId = requesterId;
    }
    public Request(User requester){
        this.requester = requester;
    }
    public User getRequester(){
        return requester;
    }
    public int getRequesterID() {
        return this.requesterId;
    }

    public void setRequesterID(int requesterId) {
        this.requesterId = requesterId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  Request request){
            if (LoginFrame.isTrial)
                return this.getRequester().getId() == request.getRequester().getId();
            return this.requesterId == request.getRequesterID() ;
        }
        return false;
    }
}
