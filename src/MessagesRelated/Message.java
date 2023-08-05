package MessagesRelated;

import UserRelated.User;

import java.util.Date;

public class Message
{
    private User sender;
    private User receiver;
    private String content;
    private Date dateTime;

    public Message(User sender, User receiver, String content, Date dateTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.dateTime = dateTime;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return dateTime;
    }
}

