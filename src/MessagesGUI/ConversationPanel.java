package MessagesGUI;

import Other.Icons.IconCreator;
import UserProfileGUI.UserProfilePage;
import UserRelated.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConversationPanel extends JPanel {
    private JPanel mainPanel;
    private GridBagConstraints g;
    JScrollPane pane;
    JScrollBar bar;
    JTextArea written;
    JButton sendButton;
    JPanel scrollPanePanel;
    User currentUser;
    User otherUser;
    ArrayList<Message> pastMessages;
    private MessagesGUI messagesGUI;

    public ConversationPanel(MessageConnection messageConnection, MessagesGUI messagesGUI) {
        this.messagesGUI = messagesGUI;
        mainPanel = new JPanel();
        pastMessages = messageConnection.getMessages();
        mainPanel.setLayout(new GridLayout(0, 2));
        this.currentUser = messageConnection.getCurrentUser();
        this.otherUser = messageConnection.getOtherUser();
        this.temp = messageConnection;
        g = new GridBagConstraints();
        g.gridy = 0;
        g.gridx = 0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        JTextArea empty = new JTextArea();
        empty.setColumns(50);
        empty.setOpaque(false);


        mainPanel.add(empty, g);
        g.gridx = 0;
        g.gridy += 1;
        JTextArea empty2 = new JTextArea();
        empty2.setColumns(25);
        empty2.setEditable(false);
        empty2.setOpaque(false);
        g.gridwidth = 1;
        mainPanel.add(empty2, g);
        //g.gridx = 1;
        //addMessage(new MessagesViewer(null, true), true);
        g.gridx = 0;
        add(mainPanel);
        addPastMessages();
    }

    private void addPastMessages() {
        for (Message message : pastMessages) {
            if (message.getSender() == currentUser)
                sendMessage(currentUser, otherUser, message.getContent());
            else
                getMessage(message);
        }
    }

    public void setCurrentReceiver(MessageConnection messageConnection) {
        this.temp = messageConnection;
    }

    MessageConnection temp;

    public void addPastMessages(MessageConnection connection) {
        temp = connection;
        mainPanel.removeAll();
        pastMessages = connection.getMessages();
        for (Message message : pastMessages) {
            if (message.getSender() == currentUser)
                sendMessage(currentUser, otherUser, message.getContent());
            else
                getMessage(message);
        }
        repaint();
        revalidate();
        mainPanel.setVisible(true);
    }

    public void addPastMessages(ArrayList<Message> messages) {

        mainPanel.removeAll();
        pastMessages = messages;
        for (Message message : pastMessages) {
            if (message.getSender().getId() == currentUser.getId())
                sendMessage(currentUser, otherUser, message.getContent());
            else
                getMessage(message);
        }
        repaint();
        revalidate();
        mainPanel.setVisible(true);
    }

    public void sendMessage(User user, User receiver, String message) {
        g.insets = new Insets(3, 10, 10, 10);
        g.gridy += 1;
        user.addMessageToMessageConnection(receiver, message);
        Message message1 = new Message(user, null, message, new Date().toString());
        MessagesViewer m = new MessagesViewer(message1, true);
        g.gridx = 1;
        mainPanel.add(new JLabel(" "));
        mainPanel.add(m, g);
    }

    public void getMessage(Message message) {
        g.insets = new Insets(3, 10, 10, 10);
        g.gridy += 1;
        MessagesViewer m = new MessagesViewer(message, false);
        g.gridx = 0;
        mainPanel.add(m, g);
        mainPanel.add(new JLabel(" "));

    }

    public User getCurrentReceiver() {
        return temp.getOtherUser();
    }

    private class MessagesViewer extends JPanel {
        private JLabel profilePhotoLabel;
        private final Font profileNameFont = new Font("Ariel", Font.BOLD, 16);
        private final Font dateFont = new Font("Ariel", Font.PLAIN, 10);

        private JLabel profileNameLabel;
        private final Color backGroundColor = new Color(230, 230, 230);
        private JLabel dateLabel;
        private JTextArea messageContent;
        private GridBagConstraints g;
        private Calendar calendar;
        private JPanel rightPane;
        private Message message;

        private void setUp() {
            messageContent = new JTextArea(message.getContent());
            dateLabel.setFont(dateFont);
            dateLabel.setForeground(new Color(108, 108, 108));
            messageContent.setMargin(new Insets(7, 7, 7, 7));
            messageContent.setEditable(false);
            messageContent.setFocusable(false);
            messageContent.setColumns(20);
            messageContent.setRows(1);
            messageContent.setLineWrap(true);
            messageContent.setOpaque(false);
            g.gridx = 0;
            g.gridy = 0;

            g.insets = new Insets(0, 0, 0, 0);

            g.gridy += 1;
            rightPane.add(messageContent, g);
            g.insets = new Insets(0, 10, 0, 0);

            g.gridy += 1;
            rightPane.add(dateLabel, g);
            g.gridy = 0;
            g.gridx = 1;
            rightPane.setBackground(backGroundColor);
            g.insets = new Insets(0, 0, 0, 0);
        }

        public MessagesViewer(Message m, boolean isItCurrentUser) {
            this.message = m;
            setLayout(new GridBagLayout());
            g = new GridBagConstraints();

            calendar = new GregorianCalendar();
            rightPane = new JPanel();
            rightPane.setLayout(new GridBagLayout());
            g.anchor = GridBagConstraints.NORTHWEST;

            String date = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + " at ";
            date += calendar.get(Calendar.HOUR) > 9 ? "" : "0";
            date += calendar.get(Calendar.HOUR) + ":";
            date += calendar.get(Calendar.MINUTE) > 9 ? "" : "0";
            date += calendar.get(Calendar.MINUTE);
            //date =date.replace("TRT","");
            setUpNameLabel(isItCurrentUser);
            dateLabel = new JLabel(date);
            setUp();
            g.insets = new Insets(5, 10, 0, 0);
            add(rightPane, g);

        }


        private void setUpNameLabel(boolean isItCurrentUser) {
            if (!isItCurrentUser) {
                profileNameLabel = new JLabel(message.getSender().getName());
                profileNameLabel.setFont(profileNameFont);
                profilePhotoLabel = new JLabel();
                profilePhotoLabel.setIcon(IconCreator.getIconWithSize(UserProfilePage.byteToImageIcon(message.getSender().getProfilePhoto()), 30, 30));
                add(profilePhotoLabel, g);
                g.insets = new Insets(0, 10, 0, 0);
                rightPane.add(profileNameLabel, g);
            }

        }


    }
}
