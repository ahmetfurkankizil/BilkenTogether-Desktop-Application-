package MessagesGUI;

import MessagesRelated.Message;
import UserRelated.User;

import java.util.ArrayList;

public class MessageConnection {
    private User otherUser;
    private User currentUser;
    private ArrayList<Message> messages;
    int port;

    public MessageConnection(User currentUser, User otherUser,int port) {
        this.otherUser = otherUser;
        this.currentUser = currentUser;
        this.messages = new ArrayList<>();
        this.port = port;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public User getOtherUser() {
        return otherUser;
    }
    public ArrayList<Message> getMessages() {
        return messages;
    }
    public void addMessages(Message message) {
        messages.add(message);
    }
}



