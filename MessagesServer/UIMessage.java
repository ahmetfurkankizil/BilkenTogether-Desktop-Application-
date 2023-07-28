package MessagesServer;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

public class UIMessage extends JLabel implements Border {
    private String content;

    public UIMessage(String content) {
        super(content);
        this.content = content;
        setBorder(this);

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

}
