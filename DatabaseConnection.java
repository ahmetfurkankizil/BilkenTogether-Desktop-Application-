import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  public static void main(String[] args) {
    // Database credentials
    String url = "jdbc:mysql://localhost:3306/projectConnection";  // Replace 'mydatabase' with your actual database name
    String username = "root";  // Replace with your MySQL username
    String password = "mkA23.jfk";  // Replace with your MySQL password

    try {
      // Register the JDBC driver
      Class.forName("com.mysql.cj.jdbc.Driver");
      
      // Create a connection
      Connection connection = DriverManager.getConnection(url, username, password);

      // Performing the database operations
      
      // Closing the connection
      System.out.println("Connection successful");
      connection.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

