package Posts;
import CommentsRelated.Comment;
import UserRelated.*;
import java.util.*;


/**
 * Post
 */
public abstract class Post {
    private int postID;
    private User sender;
    private String dateOfPost;
    private String postDescription;
    private ArrayList<Comment> commentCollection;

    public Post(int postID, User sender, String description, String dateOfPost) {
        /*
         * Post Id Oluşturma Nasıl Olacak? Database ile uyumlu olması gerekiyor.
         */
        this.postID = postID;
        this.sender = sender;
        commentCollection = new ArrayList<>();
        this.dateOfPost = dateOfPost;
        this.postDescription = description;
        System.out.println();
    }
    public int getPostID() {
        return postID;
    }

    public String getDateOfPost() {
        return dateOfPost;
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

    public ArrayList<Comment> getCommentCollection() {
        return commentCollection;
    }

    public User getSender() {
        return sender;
    }
}