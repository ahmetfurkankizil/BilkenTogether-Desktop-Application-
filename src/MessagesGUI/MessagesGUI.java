package MessagesGUI;

import HomePage.StudiesPage.Main;
import Icons.IconCreator;

import javax.swing.*;
import java.awt.*;

public class MessagesGUI extends JFrame {
    private JPanel panel1;
    private JPanel addablePanelLeft;
    private JPanel addablePanelRight;
    private JPanel contentMessages;
    private JPanel leftPanel;
    private JPanel panel2;
    private JPanel rightPanel;
    private JTextArea textInputArea;
    private JButton sendMessageButton;
    private JPanel searchPanel;
    private JScrollPane belowBorderedPane;
    private final Color searchPanelColor = new Color(200,205,209);
    JButton searchButton;
    JLabel searchLabel;
    JTextField searchField;

    public MessagesGUI(){

        searchPanel.setBackground(searchPanelColor);
        searchLabel = new JLabel("Search Messages :");
        searchButton = new JButton(IconCreator.getIconWithSize(IconCreator.searchIcon,10,10));
        searchField = new JTextField();
        searchField.setMargin(new Insets(3,3,3,3));
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(new JLabel("    "));
        searchPanel.add(new JLabel("    "));
        searchPanel.add(new JLabel("    "));





        searchField.setColumns(20);
        ConversationPanel c = new ConversationPanel();
        MessagesPanel m = new MessagesPanel();
        //textInputArea.setMargin(new Insets(5,5,5,5));
        addablePanelLeft.add(m);
        addablePanelRight.add(c);
        //setSize(1200,800);
        //sendMessageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //sendMessageButton.setFocusable(false);

        //setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(panel1);
        //setVisible(true);
    }
    public static void main(String[] args) {
         new MessagesGUI();
    }
    public JPanel getPanel(){
        return contentMessages;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }


}
