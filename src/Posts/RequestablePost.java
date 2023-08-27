package Posts;

import DatabaseRelated.DatabaseConnection;
import Request.RequestsAndViewers.AcceptedRequest;
import Request.RequestsAndViewers.DeniedRequest;
import Request.RequestsAndViewers.Request;
import Request.RequestsAndViewers.UnansweredRequest;
import SignupAndLogin.LoginFrame;
import UserRelated.Student;
import UserRelated.User;
import com.mysql.cj.log.Log;

import java.sql.*;
import java.util.ArrayList;

public abstract class RequestablePost extends Post {
    private String typeFilter;
    private ArrayList<Request> requestCollection;
    private ArrayList<Request> deniedCollection;
    private ArrayList<Request> agreementCollection;
    private DatabaseConnection databaseConnection;
    private Student realSender;

    public RequestablePost(int postId, User sender, String description, String typeFilter, String dateOfPost,boolean isItNew) {
        super(postId, sender, description, dateOfPost,isItNew);
        this.typeFilter = typeFilter;
        this.realSender = (Student) sender;
        requestCollection = new ArrayList<Request>();
        deniedCollection = new ArrayList<Request>();
        agreementCollection = new ArrayList<Request>();
        if (isItNew && !LoginFrame.isTrial){
            createRequestsTable();
        } else if (!LoginFrame.isTrial) {
            setUpCollections((Student) sender);
        }
    }

    private void setUpCollections( Student user) {
        requestCollection = new ArrayList<Request>();
        deniedCollection = new ArrayList<Request>();
        agreementCollection = new ArrayList<Request>();
        ArrayList<Request> requests =  pullTheRequestsFromDB();
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i) instanceof UnansweredRequest r)
                requestCollection.add(r);
            else if(requests.get(i) instanceof DeniedRequest r)
                deniedCollection.add(r);
            else if(requests.get(i) instanceof AcceptedRequest r)
                agreementCollection.add(r);
        }
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
                String insertQuery = "UPDATE " + tableName + " SET accepted = ?, unanswered = ?, denied = ? WHERE requesterId = ?;";

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
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RequestablePost temp){
            return super.equals(obj) && temp.getTypeFilter().equals(getTypeFilter());
        }
        return false;
    }

    public ArrayList<Request> pullTheRequestsFromDB() {
        if (LoginFrame.isTrial)
            return requestCollection;
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
        requestCollection.add(request);
        if (!LoginFrame.isTrial)
            addToRequestTable(request);

        // (This method receives a student object and adds it to the requestCollection.
        // From this
        // collection, the provider of the lesson will be able to see the Students which
        // requested to the
        // specific lesson)
    }

    public void acceptRequest(Request request) {
        // (This method will first check whether the request is in the requestCollection
        // or not. If it's there, then the passed Student will be added to the agreementCollection.)
        agreementCollection.add(request);
        requestCollection.remove(request);

        if (!LoginFrame.isTrial)
            acceptTheRequest(request);
        else
            request = new AcceptedRequest(request.getRequester());
        requestCollection.add(request);
    }

    public void denyRequest(Request request) {
        deniedCollection.add(request);
        requestCollection.remove(request);
        if (!LoginFrame.isTrial)
            denyTheRequest(request);
        else
            request = new DeniedRequest(request.getRequester());
    }

    public ArrayList<Request> getRequestCollection() {
        if (!LoginFrame.isTrial)
            setUpCollections(realSender);
        return requestCollection;
    }

    public ArrayList<Request> getAgreementCollection() {
        return agreementCollection;
    }
    public ArrayList<Integer> getAgreementCollection( boolean isInteger) {
        ArrayList<Integer> requesterIDs = new ArrayList<>();
        for (int i = 0; i < agreementCollection.size(); i++) {
            requesterIDs.add(agreementCollection.get(i).getRequesterID());
        }
        return requesterIDs;
    }
    public ArrayList<Integer> getRequestCollection( boolean isInteger) {
        ArrayList<Integer> requesterIDs = new ArrayList<>();
        for (int i = 0; i < requestCollection.size(); i++) {
            requesterIDs.add(requestCollection.get(i).getRequesterID());
        }
        return requesterIDs;
    }
    public ArrayList<Integer> getDeniedCollection( boolean isInteger) {
        ArrayList<Integer> requesterIDs = new ArrayList<>();
        for (int i = 0; i < deniedCollection.size(); i++) {
            requesterIDs.add(deniedCollection.get(i).getRequesterID());
        }
        return requesterIDs;
    }
    public ArrayList<Request> getDeniedCollection() {
        return deniedCollection;
    }

    public int getNumOfUnansweredRequests() {
        int count = 0;
        for (Request request : requestCollection) {
            if (request instanceof UnansweredRequest)
                count++;
        }
        return count;
    }

    public Request isUserInRequests(Student user) {
        for (int i = 0; i < requestCollection.size(); i++) {
            if (requestCollection.get(i).getRequesterID() == user.getId()){
                return requestCollection.get(i);
            }
        }
        for (int i = 0; i < deniedCollection.size(); i++) {
            if (deniedCollection.get(i).getRequesterID() == user.getId()){
                return deniedCollection.get(i);
            }
        }
        for (int i = 0; i < agreementCollection.size(); i++) {
            if (agreementCollection.get(i).getRequesterID() == user.getId()){
                return agreementCollection.get(i);
            }
        }
        return null;
    }

    public boolean isItReviewer(User currentUser) {
        for (Request request : agreementCollection) {
            if (request.getRequesterID() == currentUser.getId()){
                return true;
            }
        }
        return false;
    }
}
