package MessagesServer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class MessagesFrame extends JFrame implements ActionListener {
    private MessagesPanel mPanel;
    private JTextField textField;
    private JButton sendButton;
    public boolean buttonPressed;
    public MessagesFrame() {
        buttonPressed = false;
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        addMessagess();
        setBackground(Color.white);
        setTitle("Little Messages Try");
        addTextArea();
        setVisible(true);
    }

    public void addMessagess() {
        this.mPanel = new MessagesPanel();

        mPanel.getScrollPane().setBounds(80, 80, 800, 600);

        add(mPanel.getScrollPane());
    }

    public void addTextArea() {
        sendButton = new JButton("Send");
        this.textField = new JTextField();
        textField.setBounds(80, 700, 700, 50);
        sendButton.setBounds(800, 700, 60, 50);
        sendButton.addActionListener(this);
        JTextArea ta = new JTextArea("lol");
        ta.setLineWrap(true);
        add(sendButton);
        add(textField);
    }
    public MessagesPanel getmPanel() {
        return mPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getmPanel().addGetterMessage(new UIMessage(textField.getText(),false));
        revalidate();
        buttonPressed = true;
    }

    public String getTextFieldText() {
        return textField.getText();

    }
    public boolean getButtonPressed() {
        return buttonPressed;
    }
    public void setButtonPressed(boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }
}
