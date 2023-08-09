package Posts;
import CommentsRelated.Comment;
import CommentsRelated.Review;
import DatabaseRelated.DatabaseConnection;
import Request.RequestsAndViewers.AcceptedRequest;
import Request.RequestsAndViewers.DeniedRequest;
import Request.RequestsAndViewers.Request;
import Request.RequestsAndViewers.UnansweredRequest;
import UserRelated.*;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.*;

public abstract class RequestablePost extends Post {
    private String typeFilter;
    private ArrayList<Request> requestCollection;
    private ArrayList<Request> deniedCollection;
    private ArrayList<Request> agreementCollection;
    private DatabaseConnection databaseConnection;

    public RequestablePost(int postId, User sender, String description, String typeFilter, String dateOfPost) {
        super(postId, sender, description, dateOfPost);
        this.typeFilter = typeFilter;
        /*
         * Date filter LessonPost ve ActivityPost için ayrı ayrı uygulandı
         */
        requestCollection = new ArrayList<Request>();
        deniedCollection = new ArrayList<Request>();
        agreementCollection = new ArrayList<Request>();
        createRequestsTable();
    }

    public boolean createRequestsTable() {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + super.getSender().getId() + "x" + super.getPostID() + "RequestsTable";
            if (connection != null) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                        + "requesterId INT AUTO_INCREMENT PRIMARY KEY,"
                        + "unanswered BOOLEAN,"
                        + "accepted BOOLEAN,"
                        + "denied BOOLEAN"
                        + ");";

                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createTableQuery);
                    System.out.println("Requests Table created successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //databaseConnection.closeConnection();
        return false;
    }

    // When a join button is clicked
    public boolean addToRequestTable(Request request) {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + super.getSender().getId() + "x" + super.getPostID() + "RequestsTable";
            if (connection != null) {
                String insertQuery = "INSERT INTO " + tableName + " (requesterId, unanswered, accepted, denied) VALUES (?, ?, ?, ?)";

                //The information will be taken from message class getters
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, request.getRequesterID());  //generated requestId will be added
                    preparedStatement.setInt(2,1);
                    preparedStatement.setInt(3,0);
                    preparedStatement.setInt(4,0);

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Request is inserted successfully.");
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

    //When accept button is clicked in requests tab
    public boolean acceptTheRequest(Request request) {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + super.getSender().getId() + "x" + super.getPostID() + "RequestsTable";
            if (connection != null) {
                String insertQuery = "UPDATE " + tableName + " SET accepted = ?, unanswered = ?, denied = ? WHERE requesterId = ?;";

                //The information will be taken from message class getters
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1,1);
                    preparedStatement.setInt(2,0);
                    preparedStatement.setInt(3,0);
                    preparedStatement.setInt(4, request.getRequesterID());

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Request is accepted successfully.");
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

    //When deny button is clicked in requests tab
    public boolean denyTheRequest(Request request) {
        databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String tableName = "" + super.getSender().getId() + "x" + super.getPostID() + "RequestsTable";
            if (connection != null) {
                String insertQuery = "UPDATE " + tableName + " SET accepted = ?, unanswered = ?, denied = ? WHERE requesterId = ? AND postId = ?;";

                //The information will be taken from message class getters
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1,0);
                    preparedStatement.setInt(2,0);
                    preparedStatement.setInt(3,1);
                    preparedStatement.setInt(4, request.getRequesterID());

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Request is changed successfully.");
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

    public ArrayList<Request> pullTheRequestsFromDB() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String tableName = "" + super.getSender().getId() + "x" + super.getPostID() + "RequestsTable";
        String selectQuery = "SELECT * FROM " + tableName;

        ArrayList<Request> requests = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int requesterId = resultSet.getInt("requesterId");

                if (resultSet.getBoolean("unanswered")) {
                    UnansweredRequest unansweredRequest = new UnansweredRequest(requesterId);
                    requests.add(unansweredRequest);
                } else if (resultSet.getBoolean("accepted")) {
                    AcceptedRequest acceptedRequest = new AcceptedRequest(requesterId);
                    requests.add(acceptedRequest);
                } else {
                    DeniedRequest deniedRequest = new DeniedRequest(requesterId);
                    requests.add(deniedRequest);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return requests;
    }
    public String getTypeFilter() {
        return typeFilter;
    }

    public void setTypeFilter(String typeFilter) {
        this.typeFilter = typeFilter;
    }

    public void addRequest(Request request) {
        if (!isItInRequests(request)) {
            requestCollection.add(request);
            addToRequestTable(request);
        }
        // (This method receives a student object and adds it to the requestCollection.
        // From this
        // collection, the provider of the lesson will be able to see the Students which
        // requested to the
        // specific lesson)
    }

    public void acceptRequest(Request request) {
        // (This method will first check whether the request is in the requestCollection
        // or not. If it's there, then the passed Student will be added to the agreementCollection.)
        if (isItInRequests(request)) {
            agreementCollection.add(request);
            acceptTheRequest(request);
        }
    }

    public void denyRequest(Request request) {
        if (isItInRequests(request)) {
            deniedCollection.add(request);
            denyTheRequest(request);
        }
    }

    public boolean isItInRequests(Request request) {
        return requestCollection.contains(request);
    }
}
