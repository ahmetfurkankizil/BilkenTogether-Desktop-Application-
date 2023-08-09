package DatabaseRelated;

import Posts.LessonPost;
import UserRelated.FacultyMember;
import UserRelated.Student;
import UserRelated.User;

import java.sql.*;

public class DatabaseConnection {

    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/projectDatabase";
    private String username = "root";   
    private String password = "mkA23.jfk";

    public DatabaseConnection() {
        // Database credentials
        try {
            connection = DriverManager.getConnection(url, username, password);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return this.connection;
    }

    public User pullUserByIdFromDB(int userId) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        User user = null;
        String tableName = "userInformationTable";
        String insertQuery = "SELECT * FROM " + tableName + " WHERE id=?;";

        try (Connection connection = databaseConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setInt(1, userId);

            ResultSet resultSetOfUser = preparedStatement.executeQuery();
            if (resultSetOfUser.next()) {
                String role = resultSetOfUser.getString("role");

                int id = resultSetOfUser.getInt("id");
                String nameAndSurname = resultSetOfUser.getString("nameAndSurname");
                String email = resultSetOfUser.getString("email");
                String gender = resultSetOfUser.getString("gender");
                String department = resultSetOfUser.getString("department");
                String password = resultSetOfUser.getString("password");
                String dateOfBirth = resultSetOfUser.getString("dateOfBirth");

                if (role.equals("Student")) {
                    user = new Student(nameAndSurname,email,id,gender,department,password,dateOfBirth);
                } else {
                    user = new FacultyMember(nameAndSurname, email, id, gender, department, password, dateOfBirth);
                }
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void closeConnection() {
        try {
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


