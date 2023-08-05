package DatabaseRelated;

import Posts.StudyPost;

public interface DatabaseHandler {
    boolean createStudiesTable();

    boolean addToStudiesTable(StudyPost studyPost);
    boolean removeFromStudiesTable(StudyPost studypost);
    boolean createNotificationsTable();

    //boolean addToNotificationsTable();
    //boolean deleteFromNotificationsTable(int notificationId);
    boolean createMessagesTable();
    //boolean addToMessagesTable();



}
