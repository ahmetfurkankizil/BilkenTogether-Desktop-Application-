package MessagesGUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Random;

import Icons.IconCreator;
import MessagesRelated.Message;
import UserRelated.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MessagesPanel extends JPanel {
    GridBagConstraints g;
    ArrayList<ConversationViewer> conversationViewers;
    JPanel mainPanel;
    User currentUser;
    private int currentReceiverID;
    ArrayList<MessageConnection> pastMessageConnections;
    private User currentReceiver;

    public MessagesPanel(User currentUser,ConversationPanel panel) {
        currentReceiverID = 0;
        this.currentUser = currentUser;
        setLayout(new GridBagLayout());
        mainPanel = new JPanel(new GridLayout(0, 1));
        conversationViewers = new ArrayList<>();
        resetBackgrounds();
        g = new GridBagConstraints();
        g.gridx = 0;
        g.fill = GridBagConstraints.HORIZONTAL;

        pastMessageConnections = new ArrayList<>();
        pastMessageConnections = currentUser.getMessageConnections();
        conversationViewers = new ArrayList<>();
        for (MessageConnection m : pastMessageConnections) {
            ConversationViewer temp = new ConversationViewer(m, panel);
            conversationViewers.add(temp);
        }
        for (ConversationViewer conversationViewer : conversationViewers) add(conversationViewer, g);
    }

    public void refresh(User currentUser, ConversationPanel panel) {
        g = new GridBagConstraints();
        g.gridx = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        ArrayList<MessageConnection> pastMessageConnections2 = currentUser.getMessageConnections();
        MessageConnection currentMConnection = null;
        for (MessageConnection m : pastMessageConnections) {
            if (m.getOtherUser().getId() == currentReceiverID)
                currentMConnection = m;
        }
        for (ConversationViewer viewer: conversationViewers) {
            if (viewer.getOtherUserId() == currentReceiverID && currentMConnection != null)
                viewer.setMessageContent(currentMConnection.getMessages().get(currentMConnection.getMessages().size()-1).getContent());
        }
        repaint();
        revalidate();
    }

    private void removeViewers() {
        for (Component c :
                getComponents()) {
            if (c instanceof ConversationViewer viewer){
                remove(viewer);
            }
        }
        repaint();
        revalidate();
    }



    public ArrayList<ConversationViewer> getConversationViewers() {
        return conversationViewers;
    }

    public class ConversationViewer extends JPanel {
        private JLabel profilePhotoLabel;
        private final Font profileNameFont = new Font("Ariel", Font.BOLD, 16);
        private final Font dateFont = new Font("Ariel", Font.PLAIN, 10);

        private JLabel profileNameLabel;
        private final Color backGroundColor = new Color(230, 230, 230);
        private JTextArea messageContent;
        private MessageConnection messageConnection;
        private GridBagConstraints g;
        private Message m;
        private ConversationPanel panel;

        public ConversationViewer(MessageConnection messageConnection,ConversationPanel panel) {
            this.messageConnection = messageConnection;
            setLayout(new GridBagLayout());
            g = new GridBagConstraints();
            setUp(true);
            setBorder(new BottomBorder());
            this.panel = panel;
            addListener();
        }
        public int getOtherUserId(){
            return messageConnection.getOtherUser().getId();
        }






        private void setUp(boolean b) {
            profilePhotoLabel = new JLabel(IconCreator.getIconWithSize(new ImageIcon(messageConnection.getOtherUser().getProfilePhoto()), 20, 20));
            profileNameLabel = new JLabel(messageConnection.getOtherUser().getName());
            profileNameLabel.setFont(profileNameFont);
            messageContent = new JTextArea();
            messageContent.setMargin(new Insets(7, 7, 7, 7));
            messageContent.setEditable(false);
            messageContent.setFocusable(false);
            messageContent.setColumns(32);
            messageContent.setRows(1);
            messageContent.setLineWrap(true);
            messageContent.setOpaque(false);
            if (!messageConnection.getMessages().isEmpty()) {
                messageContent.setText(messageConnection.getMessages().get(messageConnection.getMessages().size() - 1).getContent());
            } else {
                messageContent.setText("");
            }
                g.gridy = 0;
                g.gridx = 0;
                g.insets = new Insets(8, 8, 5, 8);
                add(profilePhotoLabel, g);
                g.gridx += 1;
                g.anchor = GridBagConstraints.CENTER;
                g.insets = new Insets(0, 0, 0, 0);
                add(profileNameLabel, g);
                g.gridy += 1;
                g.gridx -= 1;
                g.gridwidth = 2;
                g.fill = GridBagConstraints.BOTH;
                g.insets = new Insets(3, 8, 3, 3);
                add(messageContent, g);

        }

        public void setMessageContent(String str) {
            this.messageContent.setText(str);
        }

        public String getName() {
            return profileNameLabel.getText();
        }

        public String getMessageContent() {
            return messageContent.getText();
        }

        private void addListener() {
            for (Component c :
                    getComponents()) {
                c.setCursor(new Cursor(Cursor.HAND_CURSOR));
                c.addMouseListener(new mouseList());
            }
            addMouseListener(new mouseList());
        }
        private void createContent(ArrayList<Message> messages){
            panel.addPastMessages(messages);
        }

        private class mouseList extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (!getBackground().equals(new Color(239, 143, 143))) {
                    resetBackgrounds();
                    setBackground(new Color(239, 143, 143));
                    panel.setCurrentReceiver(messageConnection);
                    currentReceiverID = messageConnection.getOtherUser().getId();
                    createContent(currentUser.pullMessageHistoryFromDB(messageConnection.id));
                    panel.setVisible(true);

                }
            }
        }
    }
    private void resetBackgrounds(){
        for (ConversationViewer c : conversationViewers) {
            c.setBackground(Color.white);
        }
    }

    private class BottomBorder implements Border {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.gray);
            g.drawLine(x, y + height, x + width, y + height);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(2, 5, 5, 5);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    }
}





