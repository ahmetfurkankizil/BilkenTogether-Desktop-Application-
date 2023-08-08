package MessagesGUI;

import javax.swing.*;
import java.awt.*;

public class MessagesMain extends JFrame {
    GridBagConstraints g;
    MessagesPanel leftPanel;
    ConversationPanel rightPanel;
    JPanel mainPanel ;


    public MessagesMain(){
        //setLayout(new GridBagLayout());
        //setSize(1000, 800);
        mainPanel = new JPanel(new GridBagLayout());
        g = new GridBagConstraints();
        g.anchor = GridBagConstraints.NORTHWEST;
        g.fill = GridBagConstraints.VERTICAL;
        g.gridx =0;
        g.gridy = 0;
        leftPanel = new MessagesPanel();
        rightPanel = new ConversationPanel();
        mainPanel.add(leftPanel,g);
        g.gridx++;
        mainPanel.add(rightPanel,g);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MessagesMain();
    }
}
