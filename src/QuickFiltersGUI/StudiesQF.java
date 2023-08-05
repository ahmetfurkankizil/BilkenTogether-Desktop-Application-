package QuickFiltersGUI;

import javax.swing.*;

public class StudiesQF extends JFrame{
    private JLabel quickFiltersLabel;
    private JLabel topicLabel;
    private JComboBox topicComboBox;
    private JButton instructorButton;
    private JButton studentButton;
    private JButton submitButton;
    private JPanel mainPanel;

    public StudiesQF() {
        setTitle("Studies Quick Filters");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new StudiesQF();
    }
}
