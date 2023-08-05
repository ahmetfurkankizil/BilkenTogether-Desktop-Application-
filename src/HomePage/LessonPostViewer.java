package HomePage;

import Icons.IconCreator;
import PostComponents.DayButtons;
import Posts.LessonPost;
import UserRelated.Student;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class LessonPostViewer extends JPanel {
    private JPanel post;
    private JLabel proName;
    private JLabel proPhoto;
    private JTextArea textArea2;
    private JPanel myPanel;
    private JButton postButton;
    private GridBagConstraints g;
    private JLabel messageLabel;
    private JLabel typeLabel;
    private JLabel topicLabel;
    private JLabel acceptance;
    private JPanel topInformationPanel;
    private JPanel daysInformationPanel;
    private ArrayList<DayButtons> dayButtonsList;
    private static final ImageIcon envelope = IconCreator.getIconWithSize(IconCreator.messageIcon,25,22);
    private static final ImageIcon comment = IconCreator.getIconWithSize(IconCreator.commentIcon,25,22);
    private LessonPost lesPost;
    private String postSendeName;
    private Student sender;
    private JLabel commentLabel;
    private Color informationBackground;
    private JButton requestButton;
    public LessonPostViewer(){

    }
    public LessonPostViewer(LessonPost p){
        this.lesPost = p;

        setUp();
        contentSetUp();

        g.fill = GridBagConstraints.NONE;
        g.gridy +=1;
        g.gridx = 1;
        setUpInformationPanel();

        g.gridy += 1;
        g.gridx =1;
        addDaysWithBinaryBoolean();
        setUpDays();
    }
    public void setUp(){
        g = new GridBagConstraints();
        requestButton = new JButton("Send Request");
        requestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sender = lesPost.getSender();
        setMinimumSize(new Dimension(300,30));
        setLayout(new GridBagLayout());
        //lesPost.getSender().setName("profile name");
        setBorder(new SectionItemBorder());
        informationBackground = new Color(194, 175, 151);
        proName = new JLabel();
        proPhoto = new JLabel();
        textArea2 = new JTextArea();

    }
    public void contentSetUp(){
        proName.setText(lesPost.getSender().getName());
        g.gridx = 0;
        g.gridy = 0;
        g.insets = new Insets(10,0,0,10);
        add(proPhoto,g);
        g.gridx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.ipady = 0;
        g.insets = new Insets(10,0,10,0);
        //add(proName,g);
        g.gridy = 1;
        g.insets = new Insets(0,0,0,0);
        g.fill = GridBagConstraints.BOTH;
        textArea2.setMargin(new Insets(0,0,10,0));
        textArea2.setBackground(getBackground());
        textArea2.setEditable(false);
        textArea2.setColumns(55);
        textArea2.setLineWrap(true);
        textArea2.setText("p.getPostDescription()");
        add(textArea2,g);
    }
    public void setUpInformationPanel(){
        topInformationPanel = new JPanel();
        g.anchor = GridBagConstraints.WEST;
        messageLabel = new JLabel(envelope);
        commentLabel = new JLabel(comment);

        topInformationPanel.add(commentLabel);
        topInformationPanel.add(messageLabel);
        // will insert p.getType() here
        typeLabel = new JLabel( lesPost.getRequestType()? "LESSON REQUEST": "LESSON GIVE");
        typeLabel.setBackground(informationBackground);
        typeLabel.setOpaque(true);
        addPadding(typeLabel);
        topInformationPanel.add(typeLabel);
        // will insert p.getTopic() here
        topicLabel = new JLabel(lesPost.getTypeFilter());
        topicLabel.setBackground(informationBackground);
        addPadding(topicLabel);
        topicLabel.setOpaque(true);
        topInformationPanel.add(topicLabel,g);
        add(topInformationPanel,g);
        requestButton.addActionListener(new RequestActionListener());
        topInformationPanel.add(requestButton);

    }
    public void setUpDays(){
        daysInformationPanel = new JPanel();
        for (DayButtons d :
                dayButtonsList) {
            d.removeActionListener(d);
            daysInformationPanel.add(d);
        }
        add(daysInformationPanel,g);

    }
    private class SectionItemBorder implements Border {
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 10, 0);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.gray);
            g2d.drawLine(x,y+height,x+width+10,y+height);
        }
    }
    public static void addPadding(JComponent comp){
        comp.setBorder(new EmptyBorder(3,3,3,3));
    }
    public static void addPadding(JComponent comp, int top, int left, int bottom, int right) {
        comp.setBorder(new EmptyBorder(top, left, bottom, right));
    }
    private void addDaysWithBinaryBoolean(){
        int binaryboolean = lesPost.getRequestGiveBinaryBoolean();

        dayButtonsList = new ArrayList<>();
        boolean[] daysAvailable = lesPost.getDaysAvailable();
        String day = "";
        for (int i = 0; i < daysAvailable.length; i++) {
            if (daysAvailable[i]){
                switch (i){
                    case 0:
                        day = "Monday";
                        break;
                    case 1:
                        day = "Tuesday";
                        break;
                    case 2:
                        day = "Wednesday";
                        break;
                    case 3:
                        day = "Thursday";
                        break;
                    case 4:
                        day = "Friday";
                        break;
                    case 5:
                        day = "Saturday";
                        break;
                    case 6:
                        day = "Sunday";
                        break;
                }
                dayButtonsList.add(new DayButtons(day));
            }
        }


    }
}
