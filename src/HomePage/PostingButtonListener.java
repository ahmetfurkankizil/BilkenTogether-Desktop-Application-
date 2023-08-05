package HomePage;

import UserRelated.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PostingButtonListener implements ActionListener {
    JTextArea postTextArea;
    ArrayList<JButton> filButtons;
    User user;
    JPanel panel;
    String typeFilter;
    int requestGiveBinaryBoolean;
    public PostingButtonListener(JPanel postsPanel, JTextArea postTextArea, ArrayList<JButton> filButtons, User user){
        this.postTextArea = postTextArea;
        this.filButtons = filButtons;
        this.user = user;
        this.panel = postsPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
