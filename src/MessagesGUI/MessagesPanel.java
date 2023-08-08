package MessagesGUI;

import Icons.IconCreator;

import javax.swing.*;
import java.awt.*;

public class MessagesPanel extends JPanel {
    GridBagConstraints g;
    JPanel mainPanel;
    public MessagesPanel(){
        setLayout(new GridLayout(0,1));
        g = new GridBagConstraints();
        mainPanel = new JPanel(new GridLayout(0,1));
        g.gridy = 0;

        g.fill = GridBagConstraints.HORIZONTAL;
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





    }
}
