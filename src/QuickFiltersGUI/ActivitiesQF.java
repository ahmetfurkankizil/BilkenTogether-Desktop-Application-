package QuickFiltersGUI;

import javax.swing.*;

public class ActivitiesQF extends JFrame {
    private JPanel mainPanel;
    private JLabel quickFiltersLabel;
    private JComboBox typeComboBox;
    private JLabel typeLabel;
    private JLabel peopleCountLabel;
    private JSpinner peopleCountSpinner;
    private JLabel dateIntervalLabel;
    private JTextField dateIntervalTextField;
    private JButton submitButton;

    public ActivitiesQF() {
        setTitle("Activities Quick Filter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new ActivitiesQF();

    }
}
