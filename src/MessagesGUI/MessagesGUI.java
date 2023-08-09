package MessagesGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import HomePage.Main.Main;
import Icons.IconCreator;
import UserRelated.User;

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
    private JScrollPane scrollLeft;
    private final Color searchPanelColor = new Color(200,205,209);
    JButton searchButton;
    JLabel searchLabel;
    JTextField searchField;
    private Main main;
    ConversationPanel conversationPanel;
    MessagesPanel m;
    MessagesPanel n;
    User currentUser;

    public MessagesGUI(User currentUser){
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
        this.currentUser = currentUser;
        // Adding ActionListener to the search button
        searchButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        // Adding KeyListener to the search field
        searchField.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performSearch();
                    main.update();
                }
            }
        });
        searchField.setColumns(20);
        conversationPanel = new ConversationPanel(currentUser.getMessageConnections().get(0));
        m = new MessagesPanel(currentUser,conversationPanel);
        //textInputArea.setMargin(new Insets(5,5,5,5));
        addablePanelLeft.add(m);
        addablePanelRight.add(conversationPanel);
        //conversationPanel.setVisible(false);

        //setSize(1200,800);
        //sendMessageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //sendMessageButton.setFocusable(false);

        //setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(panel1);
        //setVisible(true);
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


    public void sendMessage(User sender, String message) {
        conversationPanel.sendMessage(sender,message);
        //main.repaint();
        //main.revalidate();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ConversationPanel getConversationPanel() {
        return conversationPanel;
    }

    private void performSearch() {
        String searchTerm = searchField.getText().toLowerCase();
        System.out.println(searchTerm);
        for (int i = 0; i < m.getConversationViewers().size(); i++)
        {
            System.out.println("lolkl");
            String messageContent = m.getConversationViewers().get(i).getMessageContent().toLowerCase();
            String senderName = n.getConversationViewers().get(i).getName().toLowerCase();
            if (senderName.contains(searchTerm) || messageContent.contains(searchTerm))
            {

                m.getConversationViewers().get(i).setVisible(true);
            } else {
                System.out.println("lol");
                m.getConversationViewers().get(i).setVisible(false);
            }

        }
        m.repaint();
        m.revalidate();
        scrollLeft.repaint();
        scrollLeft.revalidate();
    }

}






