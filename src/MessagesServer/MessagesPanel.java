package MessagesServer;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MessagesPanel extends JPanel {
    private ArrayList<UIMessage> messages;
    private GridBagConstraints g;
    private final int paddedWidth = 630;
    private JScrollPane scrollPane;
    private JPanel senderPannel;
    private JPanel receiverPannel;
    private JPanel emptyPanel;

    public MessagesPanel() {
        this.setLayout(new GridBagLayout());

        scrollPane = new JScrollPane(this);
        senderPannel = new JPanel();
        receiverPannel = new JPanel();
        receiverPannel.setLayout(new GridBagLayout());
        senderPannel.setLayout(new GridBagLayout());
        emptyPanel = new JPanel();
        messages = new ArrayList<UIMessage>();
        emptyPanel.setBackground(Color.white);
        g = new GridBagConstraints();
        g.gridx = 0;
        g.anchor = GridBagConstraints.NORTHWEST;
        g.gridy = 0;
        g.ipadx = 100;
        add(receiverPannel, g);
        g.gridx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(10, 70, 10, 70);
        g.weighty = 3;
        g.ipadx = 0;
        add(emptyPanel, g);
        g.ipady = 0;
        g.ipadx = 0;
        g.gridx = 2;
        g.fill = GridBagConstraints.NONE;
        g.insets = new Insets(10, 10, 10, 10);
        g.weighty = 3;
        g.ipadx = 100;
        senderPannel.setBackground(Color.white);
        receiverPannel.setBackground(Color.white);
        add(senderPannel, g);
        setBackground(Color.white);
        scrollPane.setBackground(Color.white);
        g.ipadx = 0;
    }

    public void addSenderMessage(UIMessage m) {
        g.insets = new Insets(10, 10, 10, 10);
        g.anchor = GridBagConstraints.NORTHWEST;
        messages.add(m);
        g.gridx = 0;
        receiverPannel.add(m, g);
        g.gridy += 1;

        scrollPane.repaint();
        repaint();
        revalidate();

    }

    public void addGetterMessage(UIMessage m) {
        if (messages.size() > 1) {

            UIMessage prevMessage = messages.get(messages.size() - 1);
            messages.add(m);
            if (prevMessage.getSender()) {
                g.insets = new Insets(prevMessage.getHeight() + 10, 10, 10, 10);
            }
            else{
                g.insets = new Insets(10, 10, 10, 10);
            }
        }
        g.anchor = GridBagConstraints.NORTHEAST;
        g.gridx = 0;
        senderPannel.add(m, g);

        g.gridy += 1;
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
        repaint();
        scrollPane.repaint();
        revalidate();
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public static void receiveMessageFromServer(MessagesPanel mP1, String messag) {
        mP1.addGetterMessage(new UIMessage(messag,false));
    }

}
