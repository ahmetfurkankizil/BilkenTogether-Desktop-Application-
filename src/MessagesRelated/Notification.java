package MessagesRelated;

import UserRelated.User;

public class Notification {
    private User sender;
    private User receiver;
    private int type;
    private String content;
    private boolean isRead;
    private boolean isAccepted;
    private int notificationID;

    public Notification(User sender, User receiver, int type) {
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.isRead = false;
        this.isAccepted = false;
        // Assign a unique notificationID here
        // ...
    }

    // Set the content according to the notification type and users
    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public boolean getReadCondition() {
        return isRead;
    }

    public void setReadCondition(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean getAcceptedCondition() {
        return isAccepted;
    }

    public void setAcceptedCondition(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
}
