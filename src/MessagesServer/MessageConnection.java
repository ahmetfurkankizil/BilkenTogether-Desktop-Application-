package MessagesServer;

import java.util.ArrayList;

import UserRelated.User;

public class MessageConnection {
    User sender;
    User getter;
    ArrayList<UIMessage> messages;
    public MessageConnection( User sender, User getter) {
        this.sender = sender;
        this.getter = getter;
        messages = new ArrayList<>();
    }
    public void addMessage(UIMessage message){
        messages.add(message);
    }
}
