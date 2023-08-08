package MessagesGUI;

import javax.swing.*;
import java.awt.*;

public class ConversationPanel extends JPanel {
    private JPanel mainPanel;
    private GridBagConstraints g;
    JScrollPane pane;
    JScrollBar bar;
    JTextArea written;
    JButton sendButton;
    JPanel scrollPanePanel;

    public ConversationPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        g = new GridBagConstraints();
        g.gridy = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        addMessage(new MessagesViewer(null, false), false);
        addMessage(new MessagesViewer(null, true), true);
        addMessage(new MessagesViewer(null, true), true);
        addMessage(new MessagesViewer(null, true), true);
        addMessage(new MessagesViewer(null, true), true);
        addMessage(new MessagesViewer(null, true), true);
        addMessage(new MessagesViewer(null, true), true);
        addMessage(new MessagesViewer(null, true), true);



        add(mainPanel);
    }

    public void addMessage(MessagesViewer m, boolean isItCurrentUser) {
        g.insets = new Insets(3, 10, 10, 10);
        g.gridy += 1;
        if (isItCurrentUser) {
            g.gridx = 1;
            mainPanel.add(m, g);
        } else {
            g.gridx = 0;
            mainPanel.add(m, g);

        }

    }

}
