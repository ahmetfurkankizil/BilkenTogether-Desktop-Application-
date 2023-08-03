package Posts;

import java.sql.Date;
import UserRelated.*;
public class ActivityPost extends RequestablePost {
    private int numberOfAttendants;
    private Date date;

    private ActivityPost(User sender, String description, int numberOfAttendants, Date date) {
        super(sender, description,null);
        this.numberOfAttendants = numberOfAttendants;
        this.date = date;
    }

    public int getNumberOfAttendants() {
        return numberOfAttendants;
    }

    public void setNumberOfAttendants(int numberOfAttendants) {
        this.numberOfAttendants = numberOfAttendants;
    }

}
