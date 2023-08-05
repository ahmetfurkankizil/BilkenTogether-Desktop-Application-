import javax.swing.*;
import java.awt.*;

public class PostsPanel extends JPanel {
    private static final int headerFontSize = 30;
    private static final int textFontSize = 18;

    public static final Font profileNameFont = new Font("serif",Font.BOLD,headerFontSize);
    public static final Font textFont = new Font("Times",Font.PLAIN,textFontSize);

    public PostsPanel() {
        add(new postText());
        setFont(profileNameFont);
    }

    public class postText extends JTextArea {
        public postText(){
            setRows(6);
            setColumns(50);
            setFont(textFont);
        }
    }
}
