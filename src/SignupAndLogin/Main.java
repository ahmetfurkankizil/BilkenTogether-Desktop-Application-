package SignupAndLogin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SignUpHandler sh = new SignUpHandler();
        LoginFrame loginFrame = new LoginFrame(sh);
        loginFrame.setTitle("BilkenTogether");
        loginFrame.setSize(1500,1000);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
    }
}