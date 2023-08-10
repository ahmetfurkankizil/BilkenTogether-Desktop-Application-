package DatabaseRelated;

import Posts.LessonPost;
import UserRelated.FacultyMember;
import UserRelated.Student;
import UserRelated.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    private byte[] readBytesFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
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
                InputStream inputStreamProfilePhoto = resultSetOfUser.getBinaryStream("profilePhoto");
                InputStream inputStreamBackgroundPhoto = resultSetOfUser.getBinaryStream("backgroundPhoto");
                byte[] profilePhoto = null;
                byte[] backgroundPhoto = null;
                try {
                    profilePhoto = readBytesFromInputStream(inputStreamProfilePhoto);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    backgroundPhoto = readBytesFromInputStream(inputStreamBackgroundPhoto);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (role.equals("Student")) {
                    if (profilePhoto != null) {
                        user = new Student(nameAndSurname,email,id,gender,department,password,dateOfBirth, profilePhoto, backgroundPhoto);
                    }
                } else {
                    if (profilePhoto != null) {
                        user = new FacultyMember(nameAndSurname, email, id, gender, department, password, dateOfBirth, profilePhoto, backgroundPhoto);
                    }
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


