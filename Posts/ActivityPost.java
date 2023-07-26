package Posts;

import java.sql.Date;

public class ActivityPost extends RequestablePost {
    private int numberOfAttendants;
    private Date date;

    private ActivityPost(User sender, String description, int numberOfAttendants, Date date) {
        super(sender, description);
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
