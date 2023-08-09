package Request.RequestsAndViewers;

import Posts.LessonPost;
import UserRelated.Student;

import javax.swing.*;

public class RequestMiddlePanelAccepted {
    private JPanel panel1;
    private JPanel middlePanel;
    private JLabel requestLabel;
    private JPanel buttonPanel;
    private JButton backButton;
    private JButton unansweredButton;
    private JButton deniedButton;
    private JButton acceptedButton;
    private JPanel insideScrollPanel;

    public RequestMiddlePanelAccepted() {
        Student tutor = new Student("Tutor", null, 22203112, null, null, null, null);

        Student student1 = new Student("Jack", null, 10, null, null, null, null);
        Student student2 = new Student("Saul", null, 11, null, null, null, null);
        Student student3 = new Student("Heisenberg", null, 12, null, null, null, null);
        Student student4 = new Student("Jesse", null, 13, null, null, null, null);
        Student student5 = new Student("Hank", null, 14, null, null, null, null);
        Student student6 = new Student("Fring", null, 15, null, null, null, null);
        student1.setAverageRating(2);
        student2.setAverageRating(3);
        student3.setAverageRating(4);

        LessonPost lpTest1 = new LessonPost(101, tutor, null, null, 1, true, null);
        LessonPost lpTest2 = new LessonPost(102, tutor, null, null, 1, true, null);
        LessonPost lpTest3 = new LessonPost(103, tutor, null, null, 1, true, null);

        lpTest1.addRequest(new UnansweredRequest(student1.getId()));
        lpTest1.addRequest(new UnansweredRequest(student2.getId()));
        lpTest1.addRequest(new UnansweredRequest(student3.getId()));

        for (int i = 0; i < lpTest1.pullTheRequestsFromDB().size(); i++) {
            Request request = lpTest1.pullTheRequestsFromDB().get(i);
            if (request instanceof AcceptedRequest) {
                insideScrollPanel.add(new AcceptedViewer(request));
            }
        }
    }

    public JPanel getInsideScrollPanel() {
        return insideScrollPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RequestMiddlePanelAccepted");
        frame.setContentPane(new RequestMiddlePanelAccepted().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}