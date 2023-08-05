import java.util.ArrayList;

import UserRelated.User;

public class Review extends Comment {
    // Properties (Instance Variables)
    private int senderReview;
    private int receiverReview;

    // Constructor
    public Review( User sender, User receiver,String content, int senderReview, int receiverReview) {
        super( sender, receiver, content);
        this.senderReview = senderReview;
        this.receiverReview = receiverReview;
    }

    // Methods
    public int getSenderReview() {
        return this.senderReview;
    }

    public int getReceiverReview() {
        return this.receiverReview;
    }
    
}
