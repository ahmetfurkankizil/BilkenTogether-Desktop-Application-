package NotificationRelated;

import javax.swing.*;
import java.awt.*;

public class NotificationsViewer extends JFrame {

    private JPanel MainPanel;
    private JPanel TopLabel;
    private JPanel MidPanel;
    private JPanel BottomPanel;
    private JPanel MidTopPanel;
    private JPanel MidBottomPanel;
    private JPanel BottomTopPanel;
    private JPanel BottomBottomPanel;
    private JLabel BottomBottomLabel;
    private JLabel BottomTopLabel;
    private JLabel MidTopLabel;
    private JLabel MidBottomLabel;
    private JLabel TopTopLabel;
    private JPanel addablePanel;
    private GridBagConstraints gridBagConstraints;
    public NotificationsViewer()
    {
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        addablePanel.add(new GeneralNotificationPanel(),gridBagConstraints);
        setContentPane(MainPanel);
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        NotificationsViewer myFrame = new NotificationsViewer();
    }
}
