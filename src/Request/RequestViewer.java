package Request;

import Posts.RequestablePost;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RequestViewer extends JPanel {

    private JPanel panel1;
    private JPanel toppestPanel;
    private JTextArea somePostTextTextArea;

    private JLabel namelabel;
    private JLabel lessonlabel;
    private JTextArea secondtextarea;
    private JPanel parentpanel1;
    private JPanel selectedPanel;
    private JButton activitiesButton;
    private JPanel addablePanel;
    private JPanel postPanel;
    private JPanel namePanel;
    private JPanel requestNumberPanel;
    private JPanel lessonRequestPanel;
    private JPanel coursePanel;
    private JLabel requestNumberLabel;
    private JLabel courseLabel;
    private JLabel request;

    private static JFrame f;

    public RequestViewer(){
        toppestPanel = new JPanel();
        //toppestPanel.setBackground(myColor);
        //toppestPanel.setLayout(new GridBagLayout());
        toppestPanel.add(activitiesButton);
        toppestPanel.add(addablePanel);
        toppestPanel.add(request);
somePostTextTextArea = new JTextArea();
somePostTextTextArea.setEditable(false);
somePostTextTextArea.setFocusable(false);
somePostTextTextArea.setLineWrap(true);
somePostTextTextArea.setColumns(50);
courseLabel = new JLabel();
courseLabel.setText("MUSIC");
        courseLabel.setBackground(Color.YELLOW);

lessonlabel = new JLabel();
lessonlabel.setText("LESSON REQUEST");
lessonlabel.setBackground(Color.GREEN);

requestNumberLabel = new JLabel();
requestNumberLabel.setText("1 Request");;
requestNumberLabel.setBackground(Color.RED);
requestNumberLabel.setForeground(Color.WHITE);
        postPanel = new JPanel();
        parentpanel1 = new JPanel();
        parentpanel1.add(somePostTextTextArea);
        selectedPanel = new JPanel();
        coursePanel = new JPanel();
        coursePanel.add(courseLabel);
        lessonRequestPanel = new JPanel();
        lessonRequestPanel.add(lessonlabel);
        requestNumberPanel = new JPanel();
        requestNumberPanel.add(requestNumberLabel);
        selectedPanel.add(requestNumberPanel);
        selectedPanel.add(lessonRequestPanel);
        selectedPanel.add(coursePanel);
        parentpanel1.add(selectedPanel);
        postPanel.add(parentpanel1);






        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        RequestViewer r =new RequestViewer();
    }

}
