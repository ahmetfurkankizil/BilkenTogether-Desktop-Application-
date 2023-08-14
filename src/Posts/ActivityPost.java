package Posts;

import java.sql.Date;
import UserRelated.*;
public class ActivityPost extends RequestablePost {
    private int numberOfAttendants;
    private String activityDate;

    public ActivityPost(int postId, Student sender, String description, int numberOfAttendants, String dateOfPost, String typeFilter, String activityDate,boolean isItNew) {
        super(postId,sender, description,typeFilter, dateOfPost,isItNew);
        this.numberOfAttendants = numberOfAttendants;
        this.activityDate = activityDate;
        sender.addActivityPost(this);
    }
    public Student getSender(){
        return (Student) super.getSender();
    }

    @Override
    public int setPostID() {
        Student a = (Student) super.getSender();
        return a.getActivityCollectionSize() +1;
    }

    public int getNumberOfAttendants() {
        return numberOfAttendants;
    }

    public void setNumberOfAttendants(int numberOfAttendants) {
        this.numberOfAttendants = numberOfAttendants;
    }
    public String getActivityDate(){
        return activityDate;
    }
}
