package DatabaseRelated;

import Posts.ActivityPost;
import Posts.LessonPost;
import Posts.StudyPost;

public interface StudentDatabaseHandler {

    boolean createLessonsTable();
    boolean addToLessonsTable(LessonPost lessonPost);
    boolean removeFromLessonsTable(int lessonPostId);
    boolean createActivitiesTable();
    boolean addToActivitiesTable(ActivityPost activityPost);
    boolean removeFromActivitiesTable(int userId, int activitiesPostId);

}
