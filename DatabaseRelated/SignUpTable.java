package DatabaseRelated;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/* This particular class has been created for the Login Page
 * The users will write their UserID, UserEmail and UserDepartment and their datas will be
 * sent to the Database Table named "userInformationTable".
 */
public class SignUpTable{  

    private DatabaseConnection databaseConnection;

    public void addSignUpInformation (int UserId, String userEmail){

        databaseConnection = new DatabaseConnection();
        
        String insertQuery = "INSERT INTO userInformationTable (UserID, UserEmail) VALUES (?, ?)";

        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(insertQuery)) {
             
            // Set parameter values
            statement.setInt(1, UserId);
            statement.setString(2, userEmail);
            
            // Execute the statement
            statement.executeUpdate();
            
            System.out.println("UserID and UserEmail are imported successfully!");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
