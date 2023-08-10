package Request;

import Posts.LessonPost;
import Posts.RequestablePost;
import UserRelated.Student;

import javax.swing.*;

public class RequestPanelTester {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Request Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 400);


        Student stu = new Student("Ali", "ali@gmial.com", 123, "Male", "cs", "1"
        ,"1/1/2001",null,null);
        RequestablePost post = new LessonPost(1, stu , "asdfasdfasdfasdfaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaf", "Requested",
                61, true, "2/1/2001");
        RequestPanel requestPanel = new RequestPanel(post);
        frame.add(requestPanel);
        frame.setVisible(true);
    }
}
