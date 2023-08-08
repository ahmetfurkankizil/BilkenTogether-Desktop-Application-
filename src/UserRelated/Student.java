package UserRelated;

import java.sql.*;
import java.util.ArrayList;
import DatabaseRelated.*;
import Posts.*;

public class Student extends User implements StudentDatabaseHandler{

    // Properties (Instance Variables)
    private double averageRating;
    private ArrayList<Integer> ratingCollection;
    private ArrayList<LessonPost> lessonPostCollection;
    private ArrayList<ActivityPost> activityPostCollection;
    public Student(String name, String email, int id, String gender, String department, String password, String dateOfBirth) {
        super(name, email, id, gender, department, password, dateOfBirth);
        this.ratingCollection = new ArrayList<>();
        this.lessonPostCollection = new ArrayList<>();
        this.activityPostCollection = new ArrayList<>();
    }

    public void addLessonPost(LessonPost lessonPost) {
        lessonPostCollection.add(lessonPost);
    }

    public int generateLessonPostId() {
        return lessonPostCollection.size() + 1;
    }

    public void addActivityPost(ActivityPost activityPost) {
        activityPostCollection.add(activityPost);
    }

    public int generateActivityPostId() {
        return activityPostCollection.size() + 1;
    }

    // Student Methods
    public void addRating(int rating) {
        ratingCollection.add(rating);
    }

    /**
     *
     * @return
     */
    public double calculateAverageRating() {
        double total = 0;
        for (Integer rate : ratingCollection) {
            total += rate;
        }
        return (total / ratingCollection.size());
    }

    /**
     * This method will be used only for Student classes.
     * The method receives a LessonPost object and adds it to the
     * lessonPostCollection
     *
     * @param lessonPost
     */
    public void postLessonPost(LessonPost lessonPost) {
        lessonPostCollection.add(lessonPost);
    }

    /**
     * This method will first check if the passed LessonPost exists in the lessonPostCollection
     * and if it exists, it will delete the respective notification from the lessonPostCollection
     * @param lessonPost
     */
    public void deleteLessonPost(LessonPost lessonPost) {
        // Checking Process
        boolean isExist = false;
        for (LessonPost lesPost : lessonPostCollection) {
            if (lesPost.equals(lessonPost)) {
                isExist = true;
            }
        }
        if (isExist)
            lessonPostCollection.remove(lessonPost);
    }

    public void sendJoinRequest(RequestablePost reqPost) {
        reqPost.addRequest(this);
    }

    public void acceptJoinRequest(RequestablePost reqPost) {
        reqPost.acceptRequest(this);
    }

    public void withdrawJoinRequest(RequestablePost reqPost) {
        reqPost.withdrawRequest(this);
    }

    public void denyJoinRequest(RequestablePost reqPost) {
        reqPost.denyRequest(this);
    }

    @Override
    public boolean createLessonsTable() {
        super.databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "LessonsTable";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "postId INT PRIMARY KEY AUTO_INCREMENT,"
                        + "sender VARCHAR(150) NOT NULL,"
                        + "postDescription VARCHAR(350) NOT NULL,"
                        + "typeFilter VARCHAR(100) NOT NULL,"
                        + "dateBinaryBoolean INT,"
                        + "requestType TINYINT(0) NOT NULL,"
                        + "dateOfPost VARCHAR(50) NOT NULL"
                        + ");";

                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createTableQuery);
                    System.out.println("Lessons Table created successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        return false;
    }

    @Override
    public boolean createActivitiesTable() {
        super.databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "TableOfActivities";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "postId INT PRIMARY KEY AUTO_INCREMENT,"
                        + "sender VARCHAR(50) NOT NULL,"
                        + "postDescription VARCHAR(50) NOT NULL,"
                        + "numberOfAttendants INT,"
                        + "dateOfPost VARCHAR(50) NOT NULL,"
                        + "typeFilter VARCHAR(50) NOT NULL,"
                        + "activityDate VARCHAR(50) NOT NULL"
                        + ");";

                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createTableQuery);
                    System.out.println("Activities Table created successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
        return false;
    }


    @Override
    public boolean addToLessonsTable(LessonPost lessonPost) {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "LessonsTable";
            if (connection != null) {
                String insertQuery = "INSERT INTO " + tableName + " (postId, sender, postDescription, typeFilter, dateBinaryBoolean, requestType, dateOfPost) VALUES (?, ?, ?, ?, ?, ?, ?)";

                //The information will be taken from message class getters
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, lessonPost.getPostID());
                    preparedStatement.setString(2, lessonPost.getSender().getName());
                    preparedStatement.setString(3, lessonPost.getPostDescription());
                    preparedStatement.setString(4, lessonPost.getTypeFilter());
                    preparedStatement.setInt(5, lessonPost.getDateBinaryBoolean());
                    if (lessonPost.getRequestType() == true) {
                        preparedStatement.setInt(6, 1);
                    } else {
                        preparedStatement.setInt(6, 0);
                    }
                    preparedStatement.setString(7, lessonPost.getDateOfPost());
                    preparedStatement.executeUpdate();
                    System.out.println("Lesson is inserted successfully.");
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

    public LessonPost pullLessonPostFromDB(int userId, int lessonPostID) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        LessonPost lessonPost = null;
        String tableName = "" + userId + "LessonsTable";
        String insertQuery = "SELECT * FROM " + tableName + " WHERE postId=?;";

        try (Connection connection = databaseConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setInt(1, lessonPostID);

            ResultSet resultSetOfUser = preparedStatement.executeQuery();
            if (resultSetOfUser.next()) {
                int postId = resultSetOfUser.getInt("postId");
                String senderName = resultSetOfUser.getString("sender");
                String postDescription = resultSetOfUser.getString("postDescription");
                String typeFilter = resultSetOfUser.getString("typeFilter");
                int dateBinaryBoolean = resultSetOfUser.getInt("dateBinaryBoolean");
                boolean requestType = resultSetOfUser.getBoolean("requestType");
                String postDate = resultSetOfUser.getString("dateOfPost");


                Student u = new Student(senderName,null,0,null,null,null,null);
                lessonPost = new LessonPost(postId, u, postDescription, typeFilter, dateBinaryBoolean, requestType, postDate);
            } else {
                System.out.println("sent null");
                return null;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lessonPost;
    }


    public boolean removeFromLessonsTable(int lessonPostId) {
        String tableName = "" + getId() + "LessonsTable";
        String deleteQuery = "DELETE FROM " + tableName + " WHERE postId = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, lessonPostId);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Lesson deleted successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addToActivitiesTable(ActivityPost activityPost) {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "TableOfActivities";
            if (connection != null) {
                String insertQuery = "INSERT INTO " + tableName + " (postId, sender, postDescription, numberOfAttendants, dateOfPost, typeFilter, activityDate) VALUES (?, ?, ?, ?, ?, ?, ?)";

                //The information will be taken from message class getters
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    activityPost.setPostID(generateActivityPostId());
                    preparedStatement.setInt(1, activityPost.getPostID());
                    preparedStatement.setString(2, activityPost.getSender().getName());
                    preparedStatement.setString(3, activityPost.getPostDescription());
                    preparedStatement.setInt(4, activityPost.getNumberOfAttendants());
                    preparedStatement.setString(5, activityPost.getDateOfPost());
                    preparedStatement.setString(6, activityPost.getTypeFilter());
                    preparedStatement.setString(7, activityPost.getActivityDate());

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Activity is inserted successfully.");
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

    public boolean removeFromActivitiesTable(int userId, int activitiesPostId) {
        String tableName = "" + getId() + "TableOfActivities";
        String deleteQuery = "DELETE FROM " + tableName + " WHERE postId = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, activitiesPostId);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Activity deleted successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ActivityPost pullActivityPostFromDB(int userId, int activityPostID) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ActivityPost activityPost = null;
        String tableName = "" + userId + "TableOfActivities";
        String insertQuery = "SELECT * FROM " + tableName + " WHERE postId=?;";

        try (Connection connection = databaseConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setInt(1, activityPostID);

            ResultSet resultSetOfUser = preparedStatement.executeQuery();
            if (resultSetOfUser.next()) {
                int postId = resultSetOfUser.getInt("postId");
                String senderName = resultSetOfUser.getString("sender");
                String postDescription = resultSetOfUser.getString("postDescription");
                int numberOfAttendants = resultSetOfUser.getInt("numberOfAttendants");
                String dateOfPost = resultSetOfUser.getString("dateOfPost");
                String typeFilter = resultSetOfUser.getString("typeFilter");
                String activityDate = resultSetOfUser.getString("activityDate");


                Student u = new Student(senderName,null,0,null,null,null,null);
                activityPost = new ActivityPost(postId, u, postDescription, numberOfAttendants, dateOfPost, typeFilter, activityDate);
            } else {
                return null;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return activityPost;
    }
}


