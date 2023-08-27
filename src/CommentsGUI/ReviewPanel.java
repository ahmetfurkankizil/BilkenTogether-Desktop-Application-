package CommentsGUI;

import HomePages.HomeMain.HomeMain;
import HomePages.HomeMain.MainInterface;
import Other.Icons.IconCreator;
import UserRelated.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class ReviewPanel extends JPanel {
    private static final int starwidth = 20;
    private ImageIcon emptyStar = IconCreator.getIconWithSize(IconCreator.emptyStarIcon, starwidth, starwidth);
    private ImageIcon halfStar = IconCreator.getIconWithSize(IconCreator.halfStarIcon, starwidth, starwidth);
    private ImageIcon fullStar = IconCreator.getIconWithSize(IconCreator.starIcon, starwidth, starwidth);
    private int review;
    private StarPanel starPanel;

    public ReviewPanel(MainInterface main, Student currentUser) {
        setLayout(new FlowLayout());
        //add(new StarPanel());
        add(new JLabel("Enter Review: "));
        review = 0;
        starPanel = new StarPanel();
        add(starPanel);
    }


    public int getRating() {
        return starPanel.getRating();
    }

    private class StarPanel extends JPanel {
        private ArrayList<JLabel> stars;
        private int selectedNumOfStars;
        private boolean exited;

        public StarPanel() {
            stars = new ArrayList<>();
            stars.add(new Star());
            stars.add(new Star());
            exited = false;
            stars.add(new Star());
            stars.add(new Star());
            stars.add(new Star());
            selectedNumOfStars = 0;
            for (int i = 0; i < stars.size(); i++) {
                add(stars.get(i));
            }
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedNumOfStars = e.getX() / (starwidth);
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
                    for (int i = 0; (i < selectedNumOfStars + 1 && i < stars.size()); i++) {
                        stars.get(i).setIcon(fullStar);
                    }
                    for (int i = stars.size()-1; i > selectedNumOfStars-1; i--) {
                        stars.get(i).setIcon(emptyStar);
                    }
                }
            });
            addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                }
                @Override
                public void mouseMoved(MouseEvent e) {
                    int num = e.getX() / (starwidth);
                    for (int i = 0; (i < num + 1 && i < stars.size()); i++) {
                        stars.get(i).setIcon(fullStar);
                    }
                    for (int i = stars.size()-1; i > num-1; i--) {
                        stars.get(i).setIcon(emptyStar);
                    }
                }
            });
        }
        public int getRating(){
            return selectedNumOfStars;
        }
    }

    private class Star extends JLabel {
        public Star() {
            super(emptyStar);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setForeground(Color.BLUE);
        }

    }
}
