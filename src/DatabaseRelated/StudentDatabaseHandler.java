package DatabaseRelated;

import Posts.LessonPost;
import Posts.StudyPost;

public interface StudentDatabaseHandler {

    boolean createLessonsTable();
    boolean addToLessonsTable(LessonPost lessonPost);

    //boolean removeFromLessonsTable(int postId);
    boolean createActivitiesTable();
    //boolean addToActivitiesTable();
    //boolean removeFromActivitiesTable(int postId);

}
