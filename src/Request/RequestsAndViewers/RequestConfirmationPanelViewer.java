package Request.RequestsAndViewers;

import Icons.IconCreator;
import Posts.RequestablePost;
import UserRelated.Student;

import javax.swing.*;
import java.awt.*;

public class RequestConfirmationPanelViewer extends JPanel {
    private static final int starwidth = 10;
    public ImageIcon emptyStar = IconCreator.getIconWithSize(IconCreator.emptyStarIcon, starwidth, starwidth);
    public ImageIcon halfStar = IconCreator.getIconWithSize(IconCreator.halfStarIcon, starwidth, starwidth);
    public ImageIcon fullStar = IconCreator.getIconWithSize(IconCreator.starIcon, starwidth, starwidth);
    public ImageIcon cross = IconCreator.getIconWithSize(IconCreator.deniedIcon, starwidth, starwidth);
    public ImageIcon check = IconCreator.getIconWithSize(IconCreator.checkIcon, starwidth, starwidth);

    protected JPanel deniedPanel = new JPanel();
    protected JLabel halfstarLabel = new JLabel(halfStar);
    protected JLabel fullstarLabel = new JLabel(fullStar);
    protected JLabel emptystarLabel = new JLabel(emptyStar);
    protected JPanel buttonsPanel = new JPanel();
    protected JLabel nameLabel = new JLabel();
    protected JButton tickButton, crossButton;
    public RequestConfirmationPanelViewer(Request request) {

        nameLabel.setText(request.getRequesterName());
        deniedPanel.add(nameLabel);
        addStars((int) Math.round(request.getAverageRating()));
        tickButton = new JButton(check);
        crossButton = new JButton(cross);
        buttonsPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE; // Start from the current row and add panels vertically
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    public void addStars(double rating) {
        double temp = rating % 2;
        double star = rating / 2;
        double empty = (10 - rating) / 2;
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

