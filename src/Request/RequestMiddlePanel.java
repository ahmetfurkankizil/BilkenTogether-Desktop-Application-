package Request;

import MessagesGUI.ConversationPanel;

import javax.swing.*;

public class RequestMiddlePanel {
    private JPanel panel1;
    private JPanel buttonPanel;
    private JLabel requestLabel;
    private JPanel middlePanel;
    private JButton unansweredButton;
    private JButton deniedButton;
    private JPanel insideScrollPanel;
    private JButton acceptedButton;
    private JButton backButton;
    public RequestMiddlePanel(){

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("RequestMiddlePanel");
        frame.setContentPane(new RequestMiddlePanel().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
