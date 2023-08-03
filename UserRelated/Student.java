package UserRelated;

import java.util.ArrayList;
import Posts.*;
import DatabaseRelated.*;
/**
 * Concrete Student class which extends User implements StudentDatabaseHandler
 * 
 * @author Ufuk Baran Güler
 * @since 8/2/2023
 * @version v1
 */
public class Student extends User implements StudentDatabaseHandler {

    // Properties (Instance Variables)
    private double averageRating;
    private ArrayList<Integer> ratingCollection;
    private ArrayList<LessonPost> lessonPostCollection;
    private ArrayList<ActivityPost> activityPostCollection;

    // Methods

    /**
     * 
     * @param rating rating that
     */
    public void addRating(int rating) {
        ratingCollection.add(rating);
    }

    /**
     * 
     * @return
     */
    public double calculateAverageRating() {
        double total = 0;
        for (Integer rate : ratingCollection) {
            total += rate;
        }
        return total / ratingCollection.size();
    }

    /**
     * This method will be used only for Student classes.
     * The method receives a LessonPost object and adds it to the
     * lessonPostCollection
     * 
     * @param lessonPost
     */
    public void postLessonPost(LessonPost lessonPost) {
        lessonPostCollection.add(lessonPost);
    }

    /**
     * This method will first check if the passed LessonPost exists in the lessonPostCollection 
     * and if it exists, it will delete the respective notification from the lessonPostCollection
     * @param lessonPost
     */
    public void deleteLessonPost(LessonPost lessonPost) {
         // Checking Process
        boolean isExist = false;
        for (LessonPost lesPost : lessonPostCollection) {
            if (lesPost.equals(lessonPost)) {
                isExist = true;
            }
        }
        if (isExist)
            lessonPostCollection.remove(lessonPost);
    }

    public void sendJoinRequest(RequestablePost reqPost) {
        reqPost.addRequest(this);
    }

    public void acceptJoinRequest(RequestablePost reqPost) {
        reqPost.acceptRequest(this);
    }

    public void removeJoinRequest(RequestablePost reqPost) {
        // Cannot fide suitable method
    }

    public void denyJoinRequest(RequestablePost reqPost) {
        reqPost.denyRequest(this);
    }

    /*
     *Interface Methods Student (DatabaseHandler)
     *  
     * createLessonsTable(): void
     * 
     * addToLessonsTable(LessonPost): void
     * 
     * removeFromLessonsTable(LessonPost): void
     * 
     * createActivitiesTable(id): void
     * 
     * addToActivitiesTable(ActivityPost): void
     * 
     * removeFromActivitiesTable(ActivityPost): void
     */

}
