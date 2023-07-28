package MessagesServer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
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
        setTitle("Little Messages Try");
                addTextArea();

        setVisible(true);
    }

    public void addMessagess() {
        this.mPanel = new MessagesPanel();
        UIMessage m1 = new UIMessage("LOLLOLLOLLOLLOLLOLLO");
        UIMessage m2 = new UIMessage("LOL");
        UIMessage m3 = new UIMessage("LOL");
        UIMessage m4 = new UIMessage("LOL");
        UIMessage m5 = new UIMessage("LOL");
        mPanel.getScrollPane().setBounds(80, 80, 800, 600);

        add(mPanel.getScrollPane());
    }

    public void addTextArea() {
        sendButton = new JButton("Send");
        this.textField = new JTextField();
        textField.setBounds(80, 700, 700, 50);
        sendButton.setBounds(800, 700, 60, 50);
        sendButton.addActionListener(this);
        add(sendButton);
        add(textField);
    }
    public MessagesPanel getmPanel() {
        return mPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getmPanel().addGetterMessage(new UIMessage(textField.getText()));
        revalidate();
        buttonPressed = true;
        System.out.println(buttonPressed);
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
