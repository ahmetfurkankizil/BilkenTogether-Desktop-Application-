package UserRelated;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import DatabaseRelated.*;
import Posts.*;
import Request.RequestsAndViewers.AcceptedRequest;
import Request.RequestsAndViewers.DeniedRequest;
import Request.RequestsAndViewers.Request;
import Request.RequestsAndViewers.UnansweredRequest;
import SignupAndLogin.LoginFrame;

public class Student extends User{

    // Properties (Instance Variables)
    private double averageRating;
    private ArrayList<Integer> ratingCollection;

    private ArrayList<LessonPost> lessonPostCollection;
    private ArrayList<ActivityPost> activityPostCollection;
    public Student(String name, String email, int id, String gender, String department, String password, String dateOfBirth, byte[] profilePhoto, byte[] backGroundPhoto, boolean isItNew) {
        super(name, email, id, gender, department, password, dateOfBirth, profilePhoto, backGroundPhoto ,isItNew);
        this.ratingCollection = new ArrayList<>();
        this.lessonPostCollection = new ArrayList<>();
        this.activityPostCollection = new ArrayList<>();
        ratingCollection.add(5);
    }
    public int getLessonPostId(){
        return pullFromLessonsPostTable().size();
    }
    public int getActivityPostId(){
        return pullFromActivitiesPostTable().size();
    }

    public void addLessonPost(LessonPost lessonPost) {
        lessonPostCollection.add(lessonPost);
        if (!LoginFrame.isTrial)
            addToLessonsTable(lessonPost);
    }

    public ArrayList<Integer> getRatingCollection() {
        return ratingCollection;
    }

    public int generateLessonPostId() {
        int count = 0;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String tableName = "" + getId() + "LessonsTable";
        String selectQuery = "SELECT * FROM " + tableName;
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public double getAverageRating() {
        averageRating = calculateAverageRating();
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void addActivityPost(ActivityPost activityPost) {
        activityPostCollection.add(activityPost);

    }

    public int generateActivityPostId() {
        int count = 0;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String tableName = "" + getId() + "TableOfActivities";
        String selectQuery = "SELECT * FROM " + tableName;
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
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


    public boolean addToLessonsTable(LessonPost lessonPost) {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = super.getId() + "LessonsTable";
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


                Student u = new Student(senderName,null,userId,null,null,null,null, null, null,false);
                lessonPost = new LessonPost(postId, u, postDescription, typeFilter, dateBinaryBoolean, requestType, postDate,false);
            } else {
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


                Student u = new Student(senderName,null,0,null,null,null,null, null, null,false);
                activityPost = new ActivityPost(postId, u, postDescription, numberOfAttendants, dateOfPost, typeFilter, activityDate,false);
            } else {
                return null;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return activityPost;
    }


    public ArrayList<LessonPost> getLessonPostCollection() {
        return lessonPostCollection;
    }
    public ArrayList<ActivityPost> getActivityPostCollection() {
        return activityPostCollection;
    }
    public void initalizeRandomPosts() {
        Random random = new Random();
        LessonPost[] posts = {
                new LessonPost(0,this,"Custom Description1","MATH",1001010,true,new java.util.Date().toString(),false),
                new LessonPost(1,this,"Custom Description2","PHYSICS",101000,true,new java.util.Date().toString(),false),
                new LessonPost(2,this,"Custom Description3","BIOLOGY",1101010,true,new java.util.Date().toString(),false),
                new LessonPost(3,this,"Custom Description4","TENNIS",1110,true,new java.util.Date().toString(),false),
                new LessonPost(4,this,"Custom Description5","CHEMISTRY",1111,true,new java.util.Date().toString(),false),
                new LessonPost(5,this,"Custom Description6","MATH",10010,true,new java.util.Date().toString(),false),
                new LessonPost(6,this,"Custom Description7","PHYSICS",10110,true,new java.util.Date().toString(),false),
                new LessonPost(7,this,"Custom Description8","BIOLOGY",11010,true,new java.util.Date().toString(),false),
                new LessonPost(8,this,"Custom Description9","MATH",111110,true,new java.util.Date().toString(),false),
                new LessonPost(9,this,"Custom Description10","MATH",1110100,true,new Date().toString(),false)
        };
        ActivityPost[] activityPosts = {
                new ActivityPost(0,this,"Custom Description1",2,new Date().toString(),"Concert","23/07/2023",false),
                new ActivityPost(1,this,"Custom Description1",3,new Date().toString(),"Basketball","23/07/2023",false),
                new ActivityPost(2,this,"Custom Description1",4,new Date().toString(),"Reading","23/07/2023",false),
                new ActivityPost(3,this,"Custom Description1",5,new Date().toString(),"Cooking","16/08/2023",false),
                new ActivityPost(4,this,"Custom Description1",6,new Date().toString(),"Volleyball","17/07/2023",false),
                new ActivityPost(5,this,"Custom Description1",7,new Date().toString(),"Yoga","18/07/2023",false),
                new ActivityPost(6,this,"Custom Description1",8,new Date().toString(),"Cinema","19/07/2023",false),
                new ActivityPost(7,this,"Custom Description1",9,new Date().toString(),"Camping","20/07/2023",false),
                new ActivityPost(8,this,"Custom Description1",10,new Date().toString(),"Tennis","21/07/2023",false),
                new ActivityPost(9,this,"Custom Description1",11,new Date().toString(),"Yoga","22/07/2023",false),
        };
        for (int i = 0; i < 3; i++) {
            lessonPostCollection.add(posts[random.nextInt(10)]);
        }
    }
    @Override
    public ArrayList<LessonPost> pullFromLessonsPostTable() {
        if (LoginFrame.isTrial)
            return lessonPostCollection;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String tableName = "" + getId() + "LessonsTable";
        String selectQuery = "SELECT * FROM " + tableName;

        ArrayList<LessonPost> lessons = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int postId = resultSet.getInt("postId");
                String postDescription = resultSet.getString("postDescription");
                String typeFilter = resultSet.getString("typeFilter");
                int dateBinaryBoolean = resultSet.getInt("dateBinaryBoolean");
                int requestNum = resultSet.getInt("requestType");
                boolean requestType;
                if (requestNum == 0) {
                    requestType = false;
                } else {
                    requestType = true;
                }
                String date = resultSet.getString("dateOfPost");

                LessonPost lp1 = new LessonPost(postId, (Student) this,postDescription,typeFilter,dateBinaryBoolean,requestType,date,false);
                lessons.add(lp1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;
    }
    @Override
    public ArrayList<ActivityPost> pullFromActivitiesPostTable() {
        if (LoginFrame.isTrial)
            return activityPostCollection;
        String tableName = "" + getId() + "TableOfActivities";
        String selectQuery = "SELECT * FROM " + tableName;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ArrayList<ActivityPost> lessons = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int postId = resultSet.getInt("postId");
                String postDescription = resultSet.getString("postDescription");
                int numberOfAttendants = resultSet.getInt("numberOfAttendants");
                String dateOfPost = resultSet.getString("dateOfPost");
                String typeFilter = resultSet.getString("typeFilter");
                String activityDate = resultSet.getString("activityDate");

                System.out.println("Activity Post Returned Successfully");
                ActivityPost lp1 = new ActivityPost(postId, (Student) this,postDescription,numberOfAttendants,dateOfPost,typeFilter,activityDate,false);
                lessons.add(lp1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;
    }
}


