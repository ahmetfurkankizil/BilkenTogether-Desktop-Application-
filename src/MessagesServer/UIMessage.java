package MessagesServer;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

public class UIMessage extends JTextArea implements Border {
    private String content;
    private boolean senderor;
    public UIMessage(String content, boolean sender) {
        super(content);
        this.content = content;
        this.senderor = sender;
        setBorder(this);
        setLineWrap(true);
        setBackground(Color.white);
        setEditable(false);
        setSize(200, 50);
        setMaximumSize(new Dimension(500, 1000));
        

    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        // TODO Auto-generated method stub
        g.setColor(new Color(128, 128, 128));
        g.drawRoundRect(x, y, width, height,10,10);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        // TODO Auto-generated method stub
        return new Insets(10, 5, 10, 5);
    }

    @Override
    public boolean isBorderOpaque() {
        // TODO Auto-generated method stub
        return true;
    }
    public void setSender(boolean sender) {
        this.senderor = sender;
    }
    public boolean getSender(){
        return senderor;
    }
}
