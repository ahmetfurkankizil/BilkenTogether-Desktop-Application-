package CommentsGUI;

import CommentsGUI.Comment;
import UserRelated.User;

public class Review extends Comment {
    // Properties (Instance Variables)
    private int senderReview;
    private int receiverReview;

    // Constructor
    public Review( User reviewer,String content, int senderReview, int receiverReview) {
        super( reviewer, content);
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
