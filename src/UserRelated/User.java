package UserRelated;


import javax.management.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Date;
import DatabaseRelated.*;
import Icons.*;
import Posts.*;
import CommentsRelated.*;
import SignupAndLogin.*;
import MessagesRelated.*;
import MessagesServer.*;

public abstract class User implements DatabaseHandler {
    private String[] studyTopics;
    protected DatabaseConnection databaseConnection;
    private int id;
    private String name;
    private String password;
    private String mail;
    private String department;
    private String gender;
    private String dateOfBirth;

    //Added from Ufuk's User Class implementation

    private byte[] profilePhoto;

    private String biography;
    private ArrayList<String> researchInterests;
    private ArrayList<StudyPost> studyPostCollection;
    private ArrayList<Notification> notificationCollection;
    //private ArrayList<MessageConnection> messageCollection;

    public User() {
        studyPostCollection = new ArrayList<>();
        researchInterests = new ArrayList<>();
        notificationCollection = new ArrayList<>();
        //messageCollection = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
    public ArrayList<String> getResearchInterests() {
        return researchInterests;
    }

    // METHODS
    /**
     * Adds researchInterest into researchInterests ArrayList.
     * Will be used by both Student and Faculty Member
     *
     *  researchInterests indicates research interest of
     *                          a student or faculty member
     */
    public void addResearchInterest(String researchInterest) {
        researchInterests.add(researchInterest);
    }

    /**
     * This method will be used in both Student and FacultyMember classes.
     * The method receives a StudyPost object and adds it to the studyPostCollection
     *
     * @param studyPost is the study post that both student and faculty member can
     *                  share
     */
    public void postStudyPost(StudyPost studyPost) {
        studyPostCollection.add(studyPost);
    }

    /**
     * This method will first check if the passed StudyPost exists in the
     * studyPostCollection.
     * If it exists, it will delete the respective notification from the
     * studyPostCollection
     *
     * @param studyPost is the post that will be deleted in the collection
     */
    public void deleteStudyPost(StudyPost studyPost) {

        // Checking Process
        boolean isExist = false;
        for (StudyPost stuPost : studyPostCollection) {
            if (stuPost.equals(studyPost)) {
                isExist = true;
            }
        }

        if (isExist)
            studyPostCollection.remove(studyPost);
    }

    /**
     * The method receives a Notification object and adds it to the
     * notificationCollection
     * and the Notification Table of the SQL Database
     *
     * @param notification
     */
    public void addNotification(Notification notification) {
        notificationCollection.add(notification);
        // Cannot done adding it Notification Table of the SQL Database
    }

    /**
     * This method will first check if the passed Notification exists and if it
     * exists,
     * it will delete the respective notification from the NotificationCollection
     * and the Notifications Table of the SQL Database
     *
     * @param notification
     */
    public void deleteNotification(Notification notification) {

        // Checking Process
        boolean isExist = false;
        for (Notification not : notificationCollection) {
            if (not.equals(notification)) {
                isExist = true;
            }
        }

        if (isExist)
            notificationCollection.remove(notification);

        // Cannot do the second part again
    }

    /**
     * This method will receive a Comment and adds it to the respective
     * Post’s commentCollection array and Comment Table in SQL Database
     *
     * @param post
     * @param comment
     */
    public void commentToPost(Post post, Comment comment) {
        for (StudyPost stuPost : studyPostCollection) {
            if (stuPost.equals(post)) {
                //post.getCommentCollection().addCommnet(comment);
            }
        }

        // Cannot do second part;
    }

    /**
     * @see Comment class
     * @param mainComment       // Cannot finished
     * @param comment
     */
    public void commentToComment(Comment mainComment, Comment comment) {
        //mainComment.getCommentCollection().addCommnet(comment);
    }

    /**
     * @see Comment class
     * @param comment
     */
    public void likeComment(Comment comment) {
        comment.incrementLikeCount(this);
    }

    /**
     * listens to notifications constantly until the app is closed
     */
    public void checkNotifications() {
        // Don't know how to do
    }

    // DatabaseHandler Methods
    @Override
    public boolean createStudiesTable() {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "StudiesTable";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "postId INT PRIMARY KEY AUTO_INCREMENT,"
                        + "postType VARCHAR(50) NOT NULL,"
                        + "sender VARCHAR(50) NOT NULL,"
                        + "postHeading VARCHAR(150) NULL,"
                        + "postDescription  VARCHAR(250) NOT NULL,"
                        + "Topic1 VARCHAR(50)  NULL,"
                        + "Topic2 VARCHAR(50)  NULL,"
                        + "Topic3 VARCHAR(50)  NULL,"
                        + "Topic4 VARCHAR(50)  NULL,"
                        + "Topic5 VARCHAR(50)  NULL"
                        + ");";

                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createTableQuery);
                    System.out.println("Study Table created successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        return false;
    }

    /*

    @Override
    public boolean addToStudiesTable(StudyPost studyPost, ArrayList<String> topicsToBeAdded) {
        int numberOfPostTopics = topicsToBeAdded.size();
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "StudiesTable";
            if (connection != null) {
                String insertTableQuery = "INSERT INTO " + tableName + " (postId, postType, sender, postHeading, postDescription";

                for(int i=0; i<numberOfPostTopics; i++){
                    int topicNo = i+1;
                    insertTableQuery += "Topic" + topicNo + ", ";
                }
                insertTableQuery = insertTableQuery.substring(0, insertTableQuery.length() - 2);
                insertTableQuery += ") VALUES (?, ?, ?, ?, ? )";

                for(int i=0; i<numberOfPostTopics; i++){
                    insertTableQuery += "?, ";
                }
                insertTableQuery = insertTableQuery.substring(0, insertTableQuery.length() - 2);
                insertTableQuery += ")";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertTableQuery)) {
                    //Get methods of the StudyPost class will be inserted accordingly
                    preparedStatement.setInt(1, postId);
                    preparedStatement.setString(2, postType);
                    preparedStatement.setString(3, sender);
                    preparedStatement.setString(4, postHeading);
                    preparedStatement.setString(5, postDescription);

                    for(int i=0; i<numberOfPostTopics; i++){
                        int columnNumber = i+6;
                        String topicToAdd = topicsToBeAdded.get(i);
                        preparedStatement.setString(columnNumber, topicToAdd);
                    }

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Rows affected: " + rowsAffected);

                    return rowsAffected > 0;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        return false;
    }

     */

    /*
    @Override
    public boolean removeFromStudiesTable(int postId) {
        databaseConnection = new DatabaseConnection();
        String tableName = "" + getId() + "StudiesTable";
        String deleteQuery = "DELETE FROM " + tableName + " WHERE postId = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, postId);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Post deleted successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     */

    /*
    @Override
    public boolean addToMessagesTable() {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "MessagesTable";
            if (connection != null) {
                String insertQuery = "INSERT INTO " + tableName + " (postId, messageType, messageSender, messageReceiver, content, dateTime) VALUES (?, ?, ?, ?, ?, ?)";

                //The information will be taken from message class getters
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, );
                    preparedStatement.setString(2, );
                    preparedStatement.setString(3, );
                    preparedStatement.setString(4, );
                    preparedStatement.setString(5, );
                    preparedStatement.setString(6, );

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Rows affected: " + rowsAffected);
                    preparedStatement.executeUpdate(insertQuery);
                    System.out.println("Message values inserted successfully.");
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
    } */


    @Override
    public boolean createNotificationsTable() {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "NotificationsTable";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "notificationId INT PRIMARY KEY AUTO_INCREMENT,"
                        + "notificationSender VARCHAR(50) NOT NULL,"
                        + "notificationReceiver VARCHAR(50) NOT NULL,"
                        + "content VARCHAR(150) NOT NULL,"
                        + "isRead TINYINT(1) DEFAULT 0,"
                        + "isAccepted TINYINT(1) DEFAULT 0,"
                        + "dateTime  VARCHAR(250) NOT NULL"
                        + ");";

                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createTableQuery);
                    System.out.println("Notification Table created successfully.");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        return false;
    }


    @Override
    public boolean createMessagesTable() {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "MessagesTable";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "postId INT PRIMARY KEY AUTO_INCREMENT,"
                        + "messageType VARCHAR(50) NOT NULL,"
                        + "messageSender VARCHAR(50) NOT NULL,"
                        + "messageReceiver VARCHAR(50) NOT NULL,"
                        + "content VARCHAR(150) NOT NULL,"
                        + "dateTime  VARCHAR(250) NOT NULL"
                        + ");";

                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createTableQuery);
                    System.out.println("Message Table created successfully.");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        return false;
    }


    /*
    @Override
    public boolean addToNotificationsTable() {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "NotificationsTable";
            if (connection != null) {
                String insertQuery = "INSERT INTO " + tableName + " (notificationId, notificationSender, notificationReceiver, content, isRead, isAccepted, dateTime) VALUES (?, ?, ?, ?, ?, ?, ?)";

                //The information will be taken from message class getters
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, );
                    preparedStatement.setString(2, );
                    preparedStatement.setString(3, );
                    preparedStatement.setString(4, );
                    preparedStatement.setBoolean(5, false);
                    preparedStatement.setBoolean(6, false);
                    preparedStatement.setString(7, );

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Rows affected: " + rowsAffected);
                    preparedStatement.executeUpdate(insertQuery);
                    System.out.println("Message values inserted successfully.");
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
    */

    /*
    @Override
    public boolean deleteFromNotificationsTable(int notificationId) {
        databaseConnection = new DatabaseConnection();
        String tableName = "" + getId() + "NotificationsTable";
        String deleteQuery = "DELETE FROM " + tableName + " WHERE notificationId = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, notificationId);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Notification deleted successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     */
}
