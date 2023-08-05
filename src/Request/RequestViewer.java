package Request;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RequestViewer extends JPanel {

    private JPanel panel1;
    private JPanel toppestPanel;
    private JTextArea somePostTextTextArea;
    private JPanel firstPost;
    private JLabel namelabel;
    private JLabel lessonlabel;
    private JTextArea secondtextarea;
    private JPanel parentpanel1;
    private JPanel pleasePanel;
    private JButton activitiesButton;
    private JButton lessonsButton;
    private JPanel addablePanel;

    private static JFrame f;

    public RequestViewer()
    {

        setLayout(new GridBagLayout());
        createComponents();

    }

    private void createComponents()
    {


        innerPanel1 = new JPanel();
        innerPanel1.setBackground(myColor);
        innerPanel1.setLayout(new GridBagLayout());

        innerPanel2 = new JPanel();
        innerPanel2.setBackground(myColorTwo);
        innerPanel2.setLayout(new GridBagLayout());

        innerPanel2.setBorder(new LineBorder(myBorderColor,1));

        label1 = new JLabel("Gülferiz Made A Comment On Your Post:  Great perspective! ");

        label1.setFont(myFont);

        label2 = new JLabel("Did you know that the world is turning around really fast. Is there a way we could...");
        label1.setAlignmentX(0);


        innerPanel1.add(label1,gridBagConstraints);
        innerPanel2.add(label2,gridBagConstraints);


        setBorder(new LineBorder(myBorderColor,1));
        setBackground(myColor);
        gridBagConstraints.insets = new Insets(5,10,3,10);
        add(innerPanel1,gridBagConstraints);
        gridBagConstraints.insets = new Insets(0,10,5,10);

        add(innerPanel2,gridBagConstraints);
    }

    public static void main(String[] args) {
        RequestViewer r =new RequestViewer();
    }


}
