package Request;

import Icons.IconCreator;

import javax.swing.*;

public class RequestConfirmationPanelViewer extends JPanel {

    private static final int starwidth = 10;
    public ImageIcon emptyStar = IconCreator.getIconWithSize(IconCreator.emptyStarIcon, starwidth, starwidth);
    public ImageIcon halfStar = IconCreator.getIconWithSize(IconCreator.halfStarIcon, starwidth, starwidth);
    public ImageIcon fullStar = IconCreator.getIconWithSize(IconCreator.starIcon, starwidth, starwidth);
    public ImageIcon cross = IconCreator.getIconWithSize(IconCreator.deniedIcon, starwidth, starwidth);
    public JPanel deniedPanel = new JPanel();
    public JLabel halfstarLabel = new JLabel(halfStar);
    public JLabel fullstarLabel = new JLabel(fullStar);
    public JLabel emptystarLabel = new JLabel(emptyStar);
    public JLabel crossLabel = new JLabel(cross);
    public JLabel nameLabel = new JLabel();

    public RequestConfirmationPanelViewer() {

        nameLabel.setText("Name Surname");
        deniedPanel.add(nameLabel);
        addStars(10);
        deniedPanel.add(crossLabel);
        add(deniedPanel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new RequestConfirmationPanelViewer());
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addStars(int rating) {
        int temp = rating % 2;
        int star = rating / 2;
        int empty = (10 - rating) / 2;
        if (rating == 0) {
            for (int i = 0; i < 5; i++) {
                deniedPanel.add(new JLabel(emptyStar));
            }
            return;
        }
        for (int i = 0; i < star; i++) {

            deniedPanel.add(new JLabel(fullStar));

        }
        if (!(temp == 0)) {
            deniedPanel.add(new JLabel(halfStar));
            temp = 0;
        }
        for (int i = 0; i < empty; i++) {
            deniedPanel.add(new JLabel(emptyStar));
        }

    }
}

