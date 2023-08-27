package PostsGUI;

import HomePages.HomeMain.HomeMain;
import HomePages.HomeMain.MainInterface;
import Posts.ActivityPost;
import Request.RequestsAndViewers.AcceptedRequest;
import Request.RequestsAndViewers.DeniedRequest;
import Request.RequestsAndViewers.Request;
import Request.RequestsAndViewers.UnansweredRequest;
import UserProfileGUI.PPImageHandler;
import UserRelated.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ActivitiesPostViewer extends PostViewer {
    public static final Font dateFont = new Font("Times New Roman",Font.PLAIN,12);

    private JLabel dateLabel;
    private JLabel typeLabel;
    private JLabel topicLabel;

    private ArrayList<LessonPostViewer.DayButtons> dayButtonsList;
    private ActivityPost lesPost;
    private Student sender;
    private JButton requestButton;

    public ActivitiesPostViewer(ActivityPost p, MainInterface main){
        super(main);
        this.lesPost = p;
        setUp();
        contentSetUp();

        g.fill = GridBagConstraints.NONE;
        g.gridy +=1;
        g.gridx = 1;
        setUpInformationPanel();

        g.gridy += 1;
        g.gridx =1;
    }
    public void setUp(){
        super.setUp();
        proPhoto = new PPImageHandler(lesPost.getSender());
        requestButton = new JButton("Send Request");
        requestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sender = lesPost.getSender();
        proName.setText(lesPost.getSender().getName());
        if (main.getCurrentUser().getId() == sender.getId()) {
            requestButton.setVisible(false);
        }
    }

    @Override
    public ActivityPost getPost() {
        return lesPost;
    }

    public void contentSetUp(){

        g.gridx = 0;
        g.gridy = 0;
        g.insets = new Insets(10,0,0,10);
        add(proPhoto,g);

        g.gridx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;

        g.insets = new Insets(10,0,10,0);
        add(proName,g);

        g.gridy = 1;
        g.insets = new Insets(0,0,0,0);
        g.fill = GridBagConstraints.BOTH;
        textArea2.setText(lesPost.getPostDescription());
        add(textArea2,g);
        Request userRequest = lesPost.isUserInRequests((Student) main.getCurrentUser());
        if ( userRequest != null){
            if (userRequest instanceof UnansweredRequest){
                requestButton.setText("Request Sent!");
                requestButton.setBackground(new Color(228, 232, 183));
                requestButton.setOpaque(true);
                requestButton.setBorder(new EmptyBorder(5,5,5,5));
            }else if (userRequest instanceof DeniedRequest){
                requestButton.setText("Request Denied!");
                requestButton.setBackground(new Color(200,130,130));
                requestButton.setOpaque(true);
                requestButton.setBorder(new EmptyBorder(5,5,5,5));
            } else if (userRequest instanceof AcceptedRequest){
                requestButton.setText("Request Accepted!");
                requestButton.setBackground(new Color(129, 161, 231));
                requestButton.setOpaque(true);
                requestButton.setBorder(new EmptyBorder(5,5,5,5));
            }
        } else {
            requestButton.addActionListener(new RequestActionListener(lesPost,(Student) main.getCurrentUser()));
        }
    }
    public void setUpInformationPanel(){
        topInformationPanel = new JPanel();
        g.anchor = GridBagConstraints.WEST;
        messageLabel = new JLabel(envelope);
        topInformationPanel.add(commentLabel);
        topInformationPanel.add(messageLabel);

        typeLabel = new JLabel( lesPost.getNumberOfAttendants()+"");
        typeLabel.setBackground(informationBackground);
        typeLabel.setOpaque(true);
        addPadding(typeLabel);
        topInformationPanel.add(typeLabel);

        topicLabel = new JLabel(lesPost.getTypeFilter());
        topicLabel.setBackground(informationBackground);
        addPadding(topicLabel);
        topicLabel.setOpaque(true);
        topInformationPanel.add(topicLabel,g);
        add(topInformationPanel,g);

        g.gridy +=1;
        topInformationPanel.add(requestButton);
        dateLabel = new JLabel("Date: "+lesPost.getActivityDate());
        dateLabel.setFont(dateFont);
        dateLabel.setForeground(new Color(40,40,40));
        bottomIformationPanel = new JPanel();
        bottomIformationPanel.add(dateLabel);
        add(bottomIformationPanel,g);
    }

}
