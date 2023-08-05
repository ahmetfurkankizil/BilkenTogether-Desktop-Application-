package Posts;

import java.sql.Date;
import UserRelated.*;
public class ActivityPost extends RequestablePost {
    private int numberOfAttendants;
    private String date;

    public ActivityPost(int postId, Student sender, String description, int numberOfAttendants, String date) {
        super(postId,sender, description,null);
        this.numberOfAttendants = numberOfAttendants;
        this.date = date;
    }
    public Student getSender(){
        return (Student) super.getSender();
    }

    public int getNumberOfAttendants() {
        return numberOfAttendants;
    }

    public void setNumberOfAttendants(int numberOfAttendants) {
        this.numberOfAttendants = numberOfAttendants;
    }

}
