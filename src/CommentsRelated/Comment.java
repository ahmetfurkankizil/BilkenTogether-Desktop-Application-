package CommentsRelated;

import java.util.ArrayList;

import UserRelated.User;

public class Comment {
    // Properties (Instance Variables)
    private User sender;
    private User receiver;
    private int likeCount;
    private String content;
    private ArrayList<Comment> comments;
    private ArrayList<User> usersWhoLiked;

    // Constructor
    public Comment( User sender, User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.likeCount = 0;
        this.comments = new ArrayList<>();
        this.usersWhoLiked = new ArrayList<>();
    }

    // Methods
    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(ArrayList<User> usersWhoLiked) {
        this.likeCount = usersWhoLiked.size();
        this.usersWhoLiked = new ArrayList<>(usersWhoLiked);
    }

    public void incrementLikeCount(User user) {
        if (!usersWhoLiked.contains(user)) {
            usersWhoLiked.add(user);
            likeCount++;
        }
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public boolean checkIfUserAlreadyLiked(User user) {
        return usersWhoLiked.contains(user);
    }

    
}
