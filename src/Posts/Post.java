package Posts;
import CommentsGUI.Comment;
import CommentsGUI.Review;
import DatabaseRelated.DatabaseConnection;
import NotificationRelated.Notification;
import SignupAndLogin.LoginFrame;
import UserRelated.*;

import java.sql.*;
import java.util.*;
import java.util.Date;

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

    public Post(int postID, User sender, String description, String dateOfPost,boolean isItNew) {
        /*
         * Post Id Oluşturma Nasıl Olacak? Database ile uyumlu olması gerekiyor.
         */
        this.sender = sender;
        commentCollection = new ArrayList<>();
        this.dateOfPost = dateOfPost;
        this.postDescription = description;


        if (isItNew && !LoginFrame.isTrial){
            this.postID = setPostID();
            createCommentsTable();
        }else {
            this.postID = postID;
        }
    }
    public void addPastComents() {
        //ArrayListi commentCollectiona atıcak (=)
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
        User notificationReceiver = sender;
        User notificationSender = comment.getCommenter();
        String notificationContent = comment.getContent();
        Notification notificationToBeAdded = new Notification(notificationSender, notificationReceiver, notificationContent, new Date().toString());
        if (!LoginFrame.isTrial){
            addToCommentsTable(comment);
            sender.addToNotificationsTable(notificationToBeAdded);
        }else{
            sender.addNotification(notificationToBeAdded);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Post){
            Post temp = (Post) obj;
            return getPostDescription().equals(temp.getPostDescription());
        }
        return false;
    }

    public ArrayList<Comment> getCommentCollection() {
        return commentCollection;
    }

    public User getSender() {
        return sender;
    }
    public  int setPostID(){
        return sender.generateNewPostID();
    };

    public boolean createCommentsTable() {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getSender().getId() + "x" + postID + "CommentsTable";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "commenterId INT AUTO_INCREMENT PRIMARY KEY,"
                        + "reviewBoolean BOOLEAN,"
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
                String insertQuery = "INSERT INTO " + tableName + " (commenterId, reviewBoolean, content) VALUES (?, ?, ?)";

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

    public void setUpPastCommentCollection() {
        commentCollection = getCommentsFromTable();
    }
    public ArrayList<Comment> getCommentsFromTable(){
        ArrayList<Comment> commentArrayList = new ArrayList<>();
        String tableName = "" + getSender().getId() + "x" + getPostID() + "CommentsTable";
        String selectQuery = "SELECT * FROM " + tableName;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("commenterId");
                String postDescription = resultSet.getString("content");
                boolean reviewId = resultSet.getBoolean("reviewBoolean");
                Comment temp = new Comment(databaseConnection.pullUserByIdFromDB(userId),postDescription);
                commentArrayList.add(temp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commentArrayList;
    }
}