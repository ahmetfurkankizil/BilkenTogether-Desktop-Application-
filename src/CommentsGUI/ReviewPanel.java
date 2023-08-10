package CommentsGUI;

import HomePage.Main.Main;
import Icons.IconCreator;
import UserRelated.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class ReviewPanel extends JPanel {
    private static final int starwidth = 20;
    private ImageIcon emptyStar = IconCreator.getIconWithSize(IconCreator.emptyStarIcon, starwidth, starwidth);
    private ImageIcon halfStar = IconCreator.getIconWithSize(IconCreator.halfStarIcon, starwidth, starwidth);
    private ImageIcon fullStar = IconCreator.getIconWithSize(IconCreator.starIcon, starwidth, starwidth);
    private JTextField textField;
    private int review;
    public ReviewPanel(Main main, Student currentUser){
        setLayout(new FlowLayout());
        //add(new StarPanel());
        add(new JLabel("Enter Review: "));
        textField = new JTextField(3);
        review = 0;
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!textField.getText().isBlank()){
                    try {
                        if (Integer.parseInt(textField.getText()) < 6) {
                            review = Integer.parseInt(textField.getText());
                        }
                    } finally {

                    }
                }

            }
        });
        add(textField);
    }

    public JTextField getTextField() {
        return textField;
    }

    public int getReview() {
        return review;
    }

    private class StarPanel extends JPanel{
        private ArrayList<JLabel> stars;
        public StarPanel(){
            stars = new ArrayList<>();
            stars.add(new Star());
            stars.add(new Star());
            stars.add(new Star());
            stars.add(new Star());
            stars.add(new Star());
            for (int i = 0; i < stars.size(); i++) {
                add(stars.get(i));
            }
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    int num = e.getX()/(starwidth);
                    System.out.println(num);
                    for (int i = 0; (i < num+1 && i< stars.size()) ; i++) {
                        stars.get(i).setIcon(fullStar);
                    }
                    for (int i = (stars.size() -num -1); i >-1 ; i--) {
                        stars.get(i).setIcon(emptyStar);
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
    }
    private class Star extends JLabel{
        public Star(){
            super(emptyStar);
            setOpaque(false);
        }

    }
    public static void main(String[] args) {
        JFrame frame= new JFrame();
        frame.setSize(new Dimension(500,500));
        frame.add(new ReviewPanel(null,null));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
