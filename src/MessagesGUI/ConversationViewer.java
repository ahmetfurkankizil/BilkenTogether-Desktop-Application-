package MessagesGUI;

import Icons.IconCreator;
import MessagesRelated.Message;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Calendar;

public class ConversationViewer extends JPanel {
    private JLabel profilePhotoLabel;
    private final Font profileNameFont = new Font("Ariel", Font.BOLD, 16);
    private final Font dateFont = new Font("Ariel", Font.PLAIN, 10);

    private JLabel profileNameLabel;
    private final Color backGroundColor = new Color(230, 230, 230);
    private JTextArea messageContent;
    private GridBagConstraints g;
    private Message m;
    public ConversationViewer(){
        setLayout(new GridBagLayout());
        g = new GridBagConstraints();
        setUp();
        setBorder(new BottomBorder());
    };
    private void setUp(){
        profilePhotoLabel = new JLabel(IconCreator.getIconWithSize(IconCreator.starIcon,20,20));
        profileNameLabel = new JLabel("Name Name sth");
        profileNameLabel.setFont(profileNameFont);
        messageContent = new JTextArea();
        //messageContent.setText("CONTENT DE BACIM CONTENTCONTENT DE BACIM CONTENTCONTENT DE BACIM CONTENTCONTENT DE BACIM CONTENTCONTENT DE BACIM CONTENTCONTENT DE BACIM CONTENT");
        messageContent.setText("LOL");
        messageContent.setMargin(new Insets(7, 7, 7, 7));
        messageContent.setEditable(false);
        messageContent.setFocusable(false);
        messageContent.setColumns(32);
        messageContent.setRows(1);
        messageContent.setLineWrap(true);
        messageContent.setOpaque(false);

        g.gridy = 0;
        g.gridx = 0;
        g.insets = new Insets(8,8,5,8);
        add(profilePhotoLabel,g);
        g.gridx += 1;
        g.anchor = GridBagConstraints.CENTER;
        g.insets = new Insets(0,0,0,0);
        add(profileNameLabel,g);
        g.gridy += 1;
        g.gridx -=1;
        g.gridwidth =2;
        g.fill = GridBagConstraints.BOTH;
        g.insets = new Insets(3,8,3,3);
        add(messageContent,g);
    }
}
