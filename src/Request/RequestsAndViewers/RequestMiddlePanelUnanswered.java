package Request.RequestsAndViewers;

import HomePage.Main.Main;
import Posts.LessonPost;
import UserRelated.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequestMiddlePanelUnanswered {
    private JPanel panel1;
    private JPanel buttonPanel;
    private JLabel requestLabel;
    private JPanel middlePanel;
    private JButton unansweredButton;
    private JButton deniedButton;
    private JPanel insideScrollPanel;
    private JButton acceptedButton;
    private JButton backButton;
    private JPanel holdingPanel;
    private  RequestMiddlePanelDenied deniedPanel;
    private RequestMiddlePanelAccepted acceptedPanel;

    public RequestMiddlePanelUnanswered(){

        Student tutor = new Student("Tutor", null, 22203112, null, null, null, null);

        Student student1 = new Student("Jack", null, 22203112, null, null, null, null);
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
        UnansweredRequest u1 = new UnansweredRequest(student1.getId());
        lpTest1.addRequest(u1);
        //lpTest1.addRequest(new UnansweredRequest(student2.getId()));
        //lpTest1.addRequest(new UnansweredRequest(student3.getId()));
        //lpTest1.acceptRequest(new UnansweredRequest(student2.getId()));
        lpTest1.denyRequest(u1);


        GridBagConstraints g2 = new GridBagConstraints();
        g2.gridx = 0;
        g2.anchor = GridBagConstraints.NORTHWEST;
        g2.ipadx = 100;
        g2.ipady  = 10;

        for (int i = 0; i < lpTest1.pullTheRequestsFromDB().size(); i++) {
            Request request = lpTest1.pullTheRequestsFromDB().get(i);
            if (request instanceof UnansweredRequest) {
                holdingPanel.add(new UnansweredViewer(request),g2);
                System.out.println("grdi");

            }
        }


        //holdingPanel.add(new UnansweredViewer(new UnansweredRequest(10)),g2);
        //holdingPanel.add(new UnansweredViewer(new UnansweredRequest(10)),g2);




        holdingPanel.add(new JLabel("ekleniyor"),g2);
        deniedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideScrollPanel.removeAll();
                insideScrollPanel.add(deniedPanel.getInsideScrollPanel(),g2);
                insideScrollPanel.repaint();
                insideScrollPanel.revalidate();
            }
        });
        unansweredButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideScrollPanel.removeAll();
                insideScrollPanel.add(holdingPanel,g2);
                insideScrollPanel.repaint();
                insideScrollPanel.revalidate();
            }
        });
        acceptedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideScrollPanel.removeAll();
                insideScrollPanel.add(acceptedPanel.getInsideScrollPanel(),g2);
                insideScrollPanel.repaint();
                insideScrollPanel.revalidate();
            }
        });

        setUpPages();
    }

    private void setUpPages() {
        acceptedPanel = new RequestMiddlePanelAccepted();
        deniedPanel = new RequestMiddlePanelDenied();
    }
    public JPanel getMiddlePanel(){
        return middlePanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RequestMiddlePanel");
        frame.setContentPane(new RequestMiddlePanelUnanswered().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getInsideScrollPanel() {
        return holdingPanel;
    }
    private Main main;
    public void setMain(Main main) {
        this.main = main;
    }
}
