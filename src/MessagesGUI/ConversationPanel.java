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
        g.gridx =0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        JTextArea empty = new JTextArea();
        empty.setColumns(50);
        empty.setOpaque(false);


        mainPanel.add(empty,g);
        g.gridx = 0;
        g.gridy += 1;
        JTextArea empty2 = new JTextArea();
        empty2.setColumns(25);
        empty2.setEditable(false);
        empty2.setOpaque(false);
        g.gridwidth = 1;
        mainPanel.add(empty2,g);
        //g.gridx = 1;
        //addMessage(new MessagesViewer(null, true), true);
        g.gridx = 0;
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
