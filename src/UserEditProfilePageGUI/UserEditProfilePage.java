package UserEditProfilePageGUI;

import javax.swing.*;

public class UserEditProfilePage extends JFrame {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextField nameSurnameTextField;
    private JTextField nameSurnameBilkentEduTextField;
    private JTextArea textArea1;
    private JTextField a22001734TextField;
    private JComboBox comboBox1;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JButton saveChangesButton;
    private JPanel mainPanel;

    public UserEditProfilePage() {
        setTitle("Edit User Profile");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
    }

    public static void main(String[] args) {
        JFrame frame = new UserEditProfilePage();

        frame.setVisible(true);
    }
}
