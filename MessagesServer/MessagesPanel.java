package MessagesServer;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MessagesPanel extends JPanel {
    private ArrayList<UIMessage> messages;
    private GridBagConstraints g;
    private final int paddedWidth = 630;
    private JScrollPane scrollPane;

    public MessagesPanel() {
        scrollPane = new JScrollPane(this);
        

        
        messages = new ArrayList<UIMessage>();
        g = new GridBagConstraints();
        g.gridx = 0;
        g.anchor = GridBagConstraints.NORTHWEST;
        g.gridwidth = 800;
        g.gridy = 0;
        setBackground(Color.white);
        this.setLayout(new GridBagLayout());

    }

    public void addSenderMessage(UIMessage m) {
        g.insets = new Insets(10, 10, 10, paddedWidth);

        messages.add(m);

        add(m, g);
        g.gridy += 1;
        scrollPane.repaint();
        repaint();


    }

    public  void addGetterMessage(UIMessage m) {

        messages.add(m);
        g.insets = new Insets(10, paddedWidth - m.getText().length() * 8, 10, 10);

        add(m, g);

        g.gridy += 1;
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
        repaint();
        scrollPane.repaint();
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
    public static void receiveMessageFromServer(MessagesPanel mP1,String messag){
        mP1.addGetterMessage(new UIMessage(messag));
    }

}
