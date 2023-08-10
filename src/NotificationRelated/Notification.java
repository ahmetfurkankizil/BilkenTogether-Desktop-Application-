package NotificationRelated;

import UserRelated.User;

import java.util.Date;
import java.util.Random;

public class Notification {
    private int notificationID;
    private User sender;
    private User receiver;
    private String commentContent;
    private boolean isRead;
    private String dateOfNotificaiton;

    public Notification(User sender, User receiver, String commentContent, String dateOfNotification) {
        setNotificationID();
        this.sender = sender;
        this.receiver = receiver;
        //sender.readTheNotification()
        this.isRead = false;
        this.commentContent = commentContent;
        this.dateOfNotificaiton = dateOfNotification;
        // Assign a unique notificationID here
        // ...
    }
    public Notification(User sender, User receiver, String commentContent, String dateOfNotification, int id) {
        //setNotificationID();
        this.notificationID = id;
        this.sender = sender;
        this.receiver = receiver;
        //sender.readTheNotification()
        this.isRead = false;
        this.commentContent = commentContent;
        this.dateOfNotificaiton = dateOfNotification;
        // Assign a unique notificationID here
        // ...
    }

    // Set the content according to the notification type and users

    public void setContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setNotificationID() {
        Random rand = new Random();
        this.notificationID = rand.nextInt(1000000);
    }

    public int getNotificationID() {
        return this.notificationID;
    }
    public User getSender() {
        return sender;
    }

    public String getDateOfNotificaiton() {
        return dateOfNotificaiton;
    }

    public void setDateOfNotificaiton(String dateOfNotificaiton) {
        this.dateOfNotificaiton = dateOfNotificaiton;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public boolean getReadCondition() {
        return isRead;
    }

    public void setReadCondition(boolean isRead) {
        this.isRead = isRead;
    }

    public String getPostContent() {
        return "";
    }
}
