package MessagesGUI;

import MessagesRelated.Message;
import UserRelated.User;

import java.util.ArrayList;

public class MessageConnection {
    private User otherUser;
    private User currentUser;
    private ArrayList<Message> messages;
    int port;
    int id;

    public MessageConnection(User currentUser, User otherUser,int port) {
        this.otherUser = otherUser;
        this.currentUser = currentUser;
        this.messages = new ArrayList<>();
        this.id = (int)(Math.random()*100000000);
        this.port = port;
        //currentUser.insertToMessageConnectionTable(this.id,currentUser,otherUser,port);
        //currentUser.createMessageHistory(this.id);
    }
    public void setMessages(){

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
        currentUser.insertToMessageHistoryTable(this.id,message);
    }
}



