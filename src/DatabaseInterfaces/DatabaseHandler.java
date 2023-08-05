package Authorization;

import javax.xml.crypto.Data;
import java.sql.*;
public interface DatabaseHandler {
    boolean createStudiesTable();

    //boolean addToStudiesTable(StudyPost studyPost);
    //boolean removeFromStudiesTable(int postId);
    boolean createNotificationsTable();
    //boolean addToNotificationsTable();
    //boolean deleteFromNotificationsTable(int notificationId);
    boolean createMessagesTable();
    //boolean addToMessagesTable();



}
