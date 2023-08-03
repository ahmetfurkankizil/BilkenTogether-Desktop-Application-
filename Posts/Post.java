package Posts;
import UserRelated.*;
import java.util.*;

import javax.xml.stream.events.Comment;

/**
 * Post
 */
public abstract class Post {
    private int postID;
    private User sender;
    private String postDescription;
    private ArrayList<Comment> commentCollection;

    public Post(User sender, String description) {
        /*
         * Post Id Oluşturma Nasıl Olacak? Database ile uyumlu olması gerekiyor.
         */
        this.sender = sender;
        this.postDescription = description;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public void addComment(Comment comment) {
        commentCollection.add(comment);
    }
}