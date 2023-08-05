package QuickFiltersGUI;

import javax.swing.*;

public class LessonsQF extends JFrame{
    private JButton givenButton;
    private JButton requestedButton;
    private JLabel quickFiltersLabel;
    private JLabel lessonsTypesLabel;
    private JComboBox courseComboBox;
    private JLabel artSportsLabel;
    private JComboBox artSportComboBox;
    private JButton mondayButton;
    private JButton tuesdayButton;
    private JButton wednesdayButton;
    private JButton thursdayButton;
    private JButton fridayButton;
    private JButton saturdayButton;
    private JButton sundayButton;
    private JButton submitButton;
    private JLabel courseLabel;
    private JPanel mainPanel;

    public LessonsQF(){
        setTitle("Lessons QF");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        add(mainPanel);

    }

    public static void main(String[] args) {
        JFrame frame = new LessonsQF();
        frame.setVisible(true);
    }

}
