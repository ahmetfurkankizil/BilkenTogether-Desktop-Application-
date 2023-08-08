package MessagesGUI;
import java.util.Random;
import Icons.IconCreator;
import MessagesRelated.Message;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MessagesPanel extends JPanel {
    GridBagConstraints g;
    ArrayList<ConversationViewer> conversationViewers;
    JPanel mainPanel;
    public MessagesPanel(){
        setLayout(new GridBagLayout());
        g = new GridBagConstraints();
        mainPanel = new JPanel(new GridLayout(0,1));
        g.gridx = 0;

        g.fill = GridBagConstraints.HORIZONTAL;

        conversationViewers = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            conversationViewers.add(new ConversationViewer());
        }
        for (int i = 0; i < conversationViewers.size(); i++)
        {

            Random random = new Random();
            String[] randomMessages = {
                    "Hello there!",
                    "How's your day going?",
                    "Which classes are you taking this semester?",
                    "Do you like to travel?",
                    "What activities did you choose?",
                    "Are you free tomorrow?"
            };

            for (int j = 0; j < conversationViewers.size(); j++)
            {
                int randomIndex = random.nextInt(randomMessages.length);
                String randomMessage = randomMessages[randomIndex];

                conversationViewers.get(j).setMessageContent(randomMessage);
            }

            add(conversationViewers.get(i),g);

        }

    }

    public ArrayList<ConversationViewer> getConversationViewers()
    {
        return conversationViewers;
    }

    private class ConversationViewer extends JPanel {
        private JLabel profilePhotoLabel;
        private final Font profileNameFont = new Font("Ariel", Font.BOLD, 16);
        private final Font dateFont = new Font("Ariel", Font.PLAIN, 10);

        private JLabel profileNameLabel;
        private final Color backGroundColor = new Color(230, 230, 230);
        private JTextArea messageContent;
        private GridBagConstraints g;
        private Message m;
        public ConversationViewer(){
            setLayout(new GridBagLayout());
            g = new GridBagConstraints();
            setUp();
            setBorder(new BottomBorder());
        };
        private void setUp(){
            profilePhotoLabel = new JLabel(IconCreator.getIconWithSize(IconCreator.starIcon,20,20));
            profileNameLabel = new JLabel("Name Name sth");
            profileNameLabel.setFont(profileNameFont);
            messageContent = new JTextArea();
            //messageContent.setText("CONTENT DE BACIM CONTENTCONTENT DE BACIM CONTENTCONTENT DE BACIM CONTENTCONTENT DE BACIM CONTENTCONTENT DE BACIM CONTENTCONTENT DE BACIM CONTENT");
            messageContent.setText("LOL");
            messageContent.setMargin(new Insets(7, 7, 7, 7));
            messageContent.setEditable(false);
            messageContent.setFocusable(false);
            messageContent.setColumns(32);
            messageContent.setRows(1);
            messageContent.setLineWrap(true);
            messageContent.setOpaque(false);

            g.gridy = 0;
            g.gridx = 0;
            g.insets = new Insets(8,8,5,8);
            add(profilePhotoLabel,g);
            g.gridx += 1;
            g.anchor = GridBagConstraints.CENTER;
            g.insets = new Insets(0,0,0,0);
            add(profileNameLabel,g);
            g.gridy += 1;
            g.gridx -=1;
            g.gridwidth =2;
            g.fill = GridBagConstraints.BOTH;
            g.insets = new Insets(3,8,3,3);
            add(messageContent,g);
        }

        public void setMessageContent(String str) {
            this.messageContent.setText(str);
        }

        public String getMessageContent() {
            return messageContent.getText();
        }
    }

    private   class BottomBorder implements Border {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.gray);
            g.drawLine(x,y+height,x+width,y+height);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(2,5,5,5);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    }
}





