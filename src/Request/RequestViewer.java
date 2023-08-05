package Request;

import javax.swing.*;
import java.awt.*;

public class RequestViewer extends JFrame {

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

    private static JFrame f;

    public RequestViewer(){
        somePostTextTextArea.setRows(3);
        somePostTextTextArea.setOpaque(false);
        //lessonsButton.setText("umarım bitecek");
        add(panel1);
    //namelabel.setText("no new");
    namelabel.setBackground(Color.GREEN);

        setSize(new Dimension(1000,800));
        setBackground(Color.BLUE);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        RequestViewer r =new RequestViewer();
    }

}
