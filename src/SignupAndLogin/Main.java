package SignupAndLogin;

import Posts.*;
import CommentsRelated.*;
import DatabaseInterfaces.*;
import Icons.*;
import MessagesServer.*;
import Other.*;
import PostComponents.*;

import javax.swing.*;
import java.awt.*;

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