package MessagesGUI;

import HomePage.LessonsPage.LessonsPage;
import Icons.IconCreator;
import MessagesRelated.Message;
import UserProfileGUI.PPImageHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class MessagesViewer extends JPanel {
    private JLabel profilePhotoLabel;
    private final Font profileNameFont = new Font("Ariel", Font.BOLD, 16);
    private final Font dateFont = new Font("Ariel", Font.PLAIN, 10);

    private JLabel profileNameLabel;
    private final Color backGroundColor = new Color(230, 230, 230);
    private JLabel dateLabel;
    private JTextArea messageContent;
    private GridBagConstraints g;
    private Calendar calendar;
    private JPanel rightPane;
    private Message message;


    public MessagesViewer() {
        setLayout(new GridBagLayout());
        g = new GridBagConstraints();
        calendar = new GregorianCalendar();
        rightPane = new JPanel();
        rightPane.setLayout(new GridBagLayout());
        g.anchor = GridBagConstraints.NORTHWEST;
        String date = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + " at  0" + calendar.get(Calendar.HOUR) + ":0" + calendar.get(Calendar.MINUTE);
        //date =date.replace("TRT","");
        dateLabel = new JLabel(date);
        setUp();
        add(rightPane, g);
    }
    public MessagesViewer(Message message) {
        this.message = message;
        setLayout(new GridBagLayout());
        g = new GridBagConstraints();
        calendar = new GregorianCalendar();
        rightPane = new JPanel();
        rightPane.setLayout(new GridBagLayout());
        g.anchor = GridBagConstraints.NORTHWEST;
        String date = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + " at  0" + calendar.get(Calendar.HOUR) + ":0" + calendar.get(Calendar.MINUTE);
        //date =date.replace("TRT","");
        dateLabel = new JLabel(date);
        setUp(true);
        add(rightPane, g);
    }
    private void setUp(boolean b) {
        messageContent = new JTextArea(message.getContent());
        dateLabel.setFont(dateFont);
        dateLabel.setForeground(new Color(108, 108, 108));
        messageContent.setMargin(new Insets(7, 7, 7, 7));
        messageContent.setEditable(false);
        messageContent.setFocusable(false);
        messageContent.setColumns(20);
        messageContent.setRows(1);
        messageContent.setLineWrap(true);
        messageContent.setOpaque(false);
        g.gridx = 0;
        g.gridy = 0;

        g.insets = new Insets(0, 0, 0, 0);

        g.gridy += 1;
        rightPane.add(messageContent, g);
        g.insets = new Insets(0, 10, 0, 0);

        g.gridy += 1;
        rightPane.add(dateLabel, g);
        g.gridy = 0;
        g.gridx = 1;
        rightPane.setBackground(backGroundColor);
        g.insets = new Insets(0, 0, 0, 0);
    }
    private void setUp() {
        messageContent = new JTextArea("CONTENTCONTENTCONTENTCONTENTCONTENTCONTENT");
        dateLabel.setFont(dateFont);
        dateLabel.setForeground(new Color(108, 108, 108));
        messageContent.setMargin(new Insets(7, 7, 7, 7));
        messageContent.setEditable(false);
        messageContent.setFocusable(false);
        messageContent.setColumns(20);
        messageContent.setRows(1);
        messageContent.setLineWrap(true);
        messageContent.setOpaque(false);
        g.gridx = 0;
        g.gridy = 0;

        g.insets = new Insets(0, 0, 0, 0);

        g.gridy += 1;
        rightPane.add(messageContent, g);
        g.insets = new Insets(0, 10, 0, 0);

        g.gridy += 1;
        rightPane.add(dateLabel, g);
        g.gridy = 0;
        g.gridx = 1;
        rightPane.setBackground(backGroundColor);
        g.insets = new Insets(0, 0, 0, 0);
    }

    public MessagesViewer(Message m, boolean isItCurrentUser) {
        this.message = m;
        setLayout(new GridBagLayout());
        g = new GridBagConstraints();
        calendar = new GregorianCalendar();
        rightPane = new JPanel();
        rightPane.setLayout(new GridBagLayout());
        g.anchor = GridBagConstraints.NORTHWEST;
        String date = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + " at  0" + calendar.get(Calendar.HOUR) + ":0" + calendar.get(Calendar.MINUTE);
        //date =date.replace("TRT","");
        setUpNameLabel(isItCurrentUser);
        dateLabel = new JLabel(date);
        setUp(true);
        g.insets = new Insets(5,10,0,0);
        add(rightPane, g);
    }

    private void setUpNameLabel(boolean isItCurrentUser) {
        if (!isItCurrentUser) {
            profileNameLabel = new JLabel(message.getSender().getName());
            profileNameLabel.setFont(profileNameFont);
            profilePhotoLabel = new JLabel();
            profilePhotoLabel.setIcon(IconCreator.getIconWithSize(IconCreator.instructorIcon, 30, 30));
            add(profilePhotoLabel, g);
            g.insets = new Insets(0, 10, 0, 0);
            rightPane.add(profileNameLabel, g);
        }
    }

    public static void main(String[] args) {
        JFrame tryFrame = new JFrame();
        tryFrame.setSize(400, 400);
        tryFrame.add(new MessagesViewer());
        tryFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tryFrame.setVisible(true);
    }
}
