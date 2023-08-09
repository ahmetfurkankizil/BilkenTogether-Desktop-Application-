package UserRelated;

import CommentsRelated.Comment;
import DatabaseRelated.DatabaseConnection;
import Posts.Post;
import Posts.StudyPost;

import javax.management.Notification;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import DatabaseRelated.*;
import MessagesRelated.Message;
import MessagesGUI.MessageConnection;
import Posts.*;
import CommentsRelated.*;
import Request.RequestsAndViewers.AcceptedRequest;
import Request.RequestsAndViewers.DeniedRequest;
import Request.RequestsAndViewers.Request;
import Request.RequestsAndViewers.UnansweredRequest;

public abstract class User{
    private String[] studyTopics;
    protected DatabaseConnection databaseConnection;
    private int id;
    private String nameAndSurname;
    private String password;
    private String email;
    private String department;
    private String gender;
    private String dateOfBirth;
    private byte[] profilePhoto;
    private byte[] backGroundPhoto;
    private String biography;
    private ArrayList<String> researchInterests;
    private ArrayList<StudyPost> studyPostCollection;
    private ArrayList<Notification> notificationCollection;
    private ArrayList<MessageConnection> messageConnections;

    public User(String nameAndSurname, String email, int id, String gender, String department, String password, String dateOfBirth) {
        studyPostCollection = new ArrayList<>();
        researchInterests = new ArrayList<>();
        notificationCollection = new ArrayList<>();
        messageConnections = new ArrayList<>();
        setName(nameAndSurname);
        setEmail(email);
        setId(id);
        setGender(gender);
        setDepartment(department);
        setPassword(password);
        setDateOfBirth(dateOfBirth);
    }
    public void addMessageConnection(MessageConnection connection){
        messageConnections.add(connection);
    }

    public ArrayList<MessageConnection> getMessageConnections() {
        return messageConnections;
    }

    public void addStudyPost(StudyPost studyPost) {
        studyPostCollection.add(studyPost);
    }

    public int generateStudyPostId() {
        return studyPostCollection.size() +1;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return nameAndSurname;
    }

    public void setName(String name) {
        this.nameAndSurname = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public byte[] getBackGroundPhoto() {
        return backGroundPhoto;
    }

    public void setBackGroundPhoto(byte[] backGroundPhoto) {
        this.backGroundPhoto = backGroundPhoto;
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
        // Cannot  adding it Notification Table of the SQL Database
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
    public boolean createStudiesTable() {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "StudiesTable";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "postId INT PRIMARY KEY AUTO_INCREMENT,"
                        + "sender VARCHAR(50) NOT NULL,"
                        + "author VARCHAR(50) NOT NULL,"
                        + "postHeading VARCHAR(150) NULL,"
                        + "postDescription  VARCHAR(250) NOT NULL,"
                        + "postDate  VARCHAR(250) NOT NULL,"
                        + "file  VARCHAR(250) NOT NULL,"
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


    public boolean addToStudiesTable(StudyPost studyPost) {
        String[] topicsToBeAdded = studyPost.getTopicCollection();
        int numberOfPostTopics = topicsToBeAdded.length;
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "StudiesTable";
            if (connection != null) {
                String insertTableQuery = "INSERT INTO " + tableName + " (postId, sender, author, postHeading, postDescription, postDate, file, ";

                for(int i=0; i<numberOfPostTopics; i++){
                    int topicNo = i+1;
                    insertTableQuery += "Topic" + topicNo + ", ";
                }
                insertTableQuery = insertTableQuery.substring(0, insertTableQuery.length() - 2);
                insertTableQuery += ") VALUES (?, ?, ?, ?, ?, ?, ?, ";

                for(int i=0; i<numberOfPostTopics; i++){
                    insertTableQuery += "?, ";
                }
                insertTableQuery = insertTableQuery.substring(0, insertTableQuery.length() - 2);
                insertTableQuery += ")";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertTableQuery)) {
                    //Get methods of the StudyPost class will be inserted accordingly
                    preparedStatement.setInt(1, studyPost.getPostID());
                    preparedStatement.setString(2, studyPost.getSender().getName());
                    preparedStatement.setString(3, studyPost.getAuthor());
                    preparedStatement.setString(4, studyPost.getStudyPostHeading());
                    preparedStatement.setString(5, studyPost.getPostDescription());
                    preparedStatement.setString(6, studyPost.getDateOfPost());
                    preparedStatement.setString(7, "File is not added yet");

                    for(int i=0; i<numberOfPostTopics; i++){
                        int columnNumber = i+8;
                        String topicToAdd = topicsToBeAdded[i];
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

    public boolean removeFromStudiesTable(StudyPost studyPost) {
        databaseConnection = new DatabaseConnection();
        String tableName = "" + getId() + "StudiesTable";
        String deleteQuery = "DELETE FROM " + tableName + " WHERE postId = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, studyPost.getPostID());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Post deleted successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public StudyPost pullStudyPostFromDB(int userId, int studyPostID) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        StudyPost studyPost = null;
        String[] topicCollection = new String[5];
        String tableName = "" + userId + "StudiesTable";
        String insertQuery = "SELECT * FROM " + tableName + " WHERE postId=?;";

        try (Connection connection = databaseConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setInt(1, studyPostID);

            ResultSet resultSetOfUser = preparedStatement.executeQuery();
            if (resultSetOfUser.next()) {
                int postId = resultSetOfUser.getInt("postId");
                String senderName = resultSetOfUser.getString("sender");
                String author = resultSetOfUser.getString("author");
                String postHeading = resultSetOfUser.getString("postHeading");
                String postDesctiption = resultSetOfUser.getString("postDescription");
                String fileString = resultSetOfUser.getString("file");
                String postDate = resultSetOfUser.getString("postDate");

                if (resultSetOfUser.getString("Topic1") != null) {
                    topicCollection[0] = resultSetOfUser.getString("Topic1");
                    if (resultSetOfUser.getString("Topic2") != null) {
                        topicCollection[1] = resultSetOfUser.getString("Topic2");
                        if (resultSetOfUser.getString("Topic3") != null) {
                            topicCollection[2] = resultSetOfUser.getString("Topic3");
                            if (resultSetOfUser.getString("Topic4") != null) {
                                topicCollection[3] = resultSetOfUser.getString("Topic4");
                                if (resultSetOfUser.getString("Topic5") != null) {
                                    topicCollection[4] = resultSetOfUser.getString("Topic5");
                                }
                            }
                        }
                    }
                }
                User u = new Student(senderName,null,0,null,null,null,null);
                studyPost = new StudyPost(postId, u, author, postHeading, postDesctiption, null, postDate, topicCollection);
            } else {
                return null;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studyPost;
    }

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

    //Right
    public boolean createMessageHistory(int connectionId) {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + connectionId + "MessageHistory";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "senderId INT NOT NULL,"
                        + "receiverId INT NOT NULL,"
                        + "content VARCHAR(250) NOT NULL,"
                        + "date VARCHAR(50) NOT NULL"
                        + ");";

                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createTableQuery);
                    System.out.println("Message History created successfully.");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        return false;
    }

    //Right
    public boolean insertToMessageHistoryTable(int connectionId, Message message) {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + connectionId + "MessageHistory";
            if (connection != null) {
                String insertQuery = "INSERT INTO " + tableName + " (senderId, receiverId, content, date) VALUES (?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, message.getSender().getId());
                    preparedStatement.setInt(2, message.getReceiver().getId());
                    preparedStatement.setString(3, message.getContent());
                    preparedStatement.setString(4, message.getTime().toString());

                    preparedStatement.executeUpdate();
                    System.out.println("Inserted to message connection successfuly");
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

    public ArrayList<Message> pullMessageHistoryFromDB(int connectionId) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String tableName = "" + connectionId + "MessageHistory";
        String selectQuery = "SELECT * FROM " + tableName;

        ArrayList<Message> messages = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int senderId = resultSet.getInt("senderId");
                User sender = databaseConnection.pullUserByIdFromDB(senderId);
                int receiverId = resultSet.getInt("receiverId");
                User receiver = databaseConnection.pullUserByIdFromDB(receiverId);
                String content = resultSet.getString("content");
                String date = resultSet.getString("date");

                Message m1 = new Message(sender,receiver,content,date);
                messages.add(m1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }

    //Left
    public boolean createMessagesConnectionTable() {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "UserMessageConnectionTable";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "connectionId INT PRIMARY KEY AUTO_INCREMENT,"
                        + "id1 INT NOT NULL,"
                        + "id2 INT NOT NULL,"
                        + "port INT NOT NULL"
                        + ");";

                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createTableQuery);
                    System.out.println("User Message Connection Table created successfully.");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        return false;
    }

    //Left
    public boolean insertToMessageConnectionTable(int connectionId, User user1, User user2, int port) {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "UserMessageConnectionTable";
            if (connection != null) {
                String insertQuery = "INSERT INTO " + tableName + " (connectionId, id1, id2, port) VALUES (?, ?, ?, ?)";

                //The information will be taken from message class getters
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, connectionId);
                    preparedStatement.setInt(2, user1.getId());
                    preparedStatement.setInt(3, user2.getId());
                    preparedStatement.setInt(4, port);

                    preparedStatement.executeUpdate();
                    System.out.println("Inserted to message connection successfuly");
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
