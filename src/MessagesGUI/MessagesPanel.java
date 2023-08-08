package MessagesGUI;
import java.util.Random;
import Icons.IconCreator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MessagesPanel extends JPanel {
    GridBagConstraints g;
    ArrayList<ConversationViewer> conversationViewers;
    JPanel mainPanel;
    public MessagesPanel(){
        setLayout(new GridLayout(0,1));
        g = new GridBagConstraints();
        mainPanel = new JPanel(new GridLayout(0,1));
        g.gridy = 0;

        g.fill = GridBagConstraints.HORIZONTAL;
        /*
        add(new ConversationViewer());

        add(new ConversationViewer());
        add(new ConversationViewer());
        add(new ConversationViewer());
        add(new ConversationViewer());
        add(new ConversationViewer());
        add(new ConversationViewer());
        add(new ConversationViewer());
        add(new ConversationViewer());
        add(new ConversationViewer());
        add(new ConversationViewer());
*/

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

            add(conversationViewers.get(i));

        }

    }

    public ArrayList<ConversationViewer> getConversationViewers()
    {
        return conversationViewers;
    }
}





