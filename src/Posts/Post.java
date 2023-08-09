package Posts;
import CommentsRelated.Comment;
import CommentsRelated.Review;
import DatabaseRelated.DatabaseConnection;
import UserRelated.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
    private DatabaseConnection databaseConnection;

    public Post(int postID, User sender, String description, String dateOfPost) {
        /*
         * Post Id Oluşturma Nasıl Olacak? Database ile uyumlu olması gerekiyor.
         */
        this.postID = postID;
        this.sender = sender;
        commentCollection = new ArrayList<>();
        this.dateOfPost = dateOfPost;
        this.postDescription = description;
        createCommentsTable();
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

    public boolean createCommentsTable() {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getSender().getId() + "x" + getPostID() + "CommentsTable";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "commenterId INT AUTO_INCREMENT PRIMARY KEY,"
                        + "reviewBoolean BOOLEAN,"
                        + "unanswered BOOLEAN,"
                        + "content VARCHAR(350) NOT NULL"
                        + ");";

                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createTableQuery);
                    System.out.println("Comment Table created successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        return false;
    }

    public boolean addToCommentsTable(Comment comment) {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getSender().getId() + "x" + getPostID() + "CommentsTable";
            if (connection != null) {
                String insertQuery = "INSERT INTO " + tableName + " (requesterId, reviewBoolean, content) VALUES (?, ?, ?)";

                //The information will be taken from message class getters
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, comment.getCommenter().getId());
                    if (comment instanceof Review) {
                        preparedStatement.setInt(2, 1);
                    } else {
                        preparedStatement.setInt(2, 0);
                    }
                    preparedStatement.setString(3,comment.getContent());


                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Inserted to Comments Table successfully.");
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        return false;
    }
}