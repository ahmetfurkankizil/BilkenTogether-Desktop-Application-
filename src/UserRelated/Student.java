package UserRelated;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
            String tableName = "" + getId() + "LessonTable";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "postId INT PRIMARY KEY AUTO_INCREMENT,"
                        + "typeFilter VARCHAR(50) NOT NULL,"
                        + "dateFilter VARCHAR(50) NOT NULL,"
                        + "content VARCHAR(150) NULL,"
                        + "requestGiveBoolean  TINYINT(1) DEFAULT 0"
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

    /*
    @Override
    public boolean addToLessonsTable() {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "LessonsTable";
            if (connection != null) {
                String insertQuery = "INSERT INTO " + tableName + " (postId, typeFilter, dateFilter, content, requestGiveBoolean) VALUES (?, ?, ?, ?, ?)";

                //The information will be taken from message class getters
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, );
                    preparedStatement.setString(2, );
                    preparedStatement.setString(3, );
                    preparedStatement.setString(4, );
                    preparedStatement.setBoolean(5, );

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Rows affected: " + rowsAffected);
                    preparedStatement.executeUpdate(insertQuery);
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

     */

    /*
    @Override
    public boolean removeFromLessonsTable(int postId) {
        String tableName = "" + getId() + "LessonsTable";
        String deleteQuery = "DELETE FROM " + tableName + " WHERE postId = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, postId);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Lesson deleted successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     */

    @Override
    public boolean createActivitiesTable() {
        super.databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "ActivitesTable";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "postId INT PRIMARY KEY AUTO_INCREMENT,"
                        + "typeFilter VARCHAR(50) NOT NULL,"
                        + "dateFilter VARCHAR(50) NOT NULL,"
                        + "content VARCHAR(150) NULL,"
                        + "requestGiveBoolean  TINYINT(1) DEFAULT 0"
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

    /*
    @Override
    public boolean addToActivitiesTable() {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + getId() + "ActivitiesTable";
            if (connection != null) {
                String insertQuery = "INSERT INTO " + tableName + " (postId, typeFilter, dateFilter, content, requestGiveBoolean) VALUES (?, ?, ?, ?, ?)";

                //The information will be taken from message class getters
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, );
                    preparedStatement.setString(2, );
                    preparedStatement.setString(3, );
                    preparedStatement.setString(4, );
                    preparedStatement.setBoolean(5, );

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Rows affected: " + rowsAffected);
                    preparedStatement.executeUpdate(insertQuery);
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

    @Override
    public boolean removeFromActivitiesTable(int postId) {
        String tableName = "" + getId() + "ActivitiesTable";
        String deleteQuery = "DELETE FROM " + tableName + " WHERE postId = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, postId);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Activity deleted successfully");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     */
}
