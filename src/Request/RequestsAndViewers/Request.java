package Request.RequestsAndViewers;

public abstract class Request {
    private String requesterName;
    private int averageRating;

    public Request(String requesterName, int averageRating) {
        this.requesterName = requesterName;
        this.averageRating = averageRating;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }
}
