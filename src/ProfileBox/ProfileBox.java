package ProfileBox;

import javax.swing.*;

public class ProfileBox extends  JFrame {
    private JPanel panel1;
    private JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel MiddlePanel;
    private JPanel BottomPanel;
    private JLabel MiddleLabel;
    private JLabel BottomLabel;


    public ProfileBox()
    {

        setContentPane(MainPanel);
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        ProfileBox myFrame = new ProfileBox();
    }

    private void createUIComponents() {
    }
}

