package MessagesRelated;

import MessagesRelated.Message;
import UserRelated.User;

import java.util.ArrayList;

public class MessageConnection {
    private User sender;
    private User receiver;
    private ArrayList<Message> messages;

    public MessageConnection(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.messages = new ArrayList<>();
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessages(Message message) {
        messages.add(message);
    }
}



