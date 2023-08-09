package CommentsRelated;

import java.util.ArrayList;

import UserRelated.User;

public class Comment {
    // Properties (Instance Variables)
    private User commenter;
    private int likeCount;
    private String content;
    private ArrayList<Comment> comments;
    private ArrayList<User> usersWhoLiked;

    // Constructor
    public Comment( User commenter, String content) {
        this.commenter = commenter;
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
    public User getCommenter() {
        return commenter;
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


    public void decrementLikeCount(User user) {
        if (usersWhoLiked.contains(user)) {
            usersWhoLiked.remove(user);
            likeCount--;
        }
    }
}
