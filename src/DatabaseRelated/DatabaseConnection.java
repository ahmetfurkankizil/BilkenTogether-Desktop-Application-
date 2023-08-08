package DatabaseRelated;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public void closeConnection() {
        try {
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


