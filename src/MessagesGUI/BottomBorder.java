package MessagesGUI;

import javax.swing.border.Border;
import java.awt.*;

public class BottomBorder implements Border {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.gray);
        g.drawLine(x,y+height,x+width,y+height);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(2,5,5,5);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
