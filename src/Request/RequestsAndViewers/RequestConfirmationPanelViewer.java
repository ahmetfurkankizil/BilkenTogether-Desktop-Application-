package Request.RequestsAndViewers;

import DatabaseRelated.DatabaseConnection;
import Other.Icons.IconCreator;
import Posts.RequestablePost;
import UserRelated.Student;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

public class RequestConfirmationPanelViewer extends JPanel {
    private static final int starwidth = 20;
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
    private DatabaseConnection databaseConnection;
    private final Font PROFILENAMEFONT = new Font("default",Font.BOLD,25);
    public RequestConfirmationPanelViewer(Request request) {
        this.databaseConnection = new DatabaseConnection();
        Student student = (Student) this.databaseConnection.pullUserByIdFromDB(request.getRequesterID());
        //student = new Student("1","1",45,"1","1","1","1");
        //This student can't be null because it is already added to the database

        nameLabel.setText(student.getName());
        nameLabel.setFont(PROFILENAMEFONT);
        deniedPanel.add(nameLabel);
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            student.addRating(rand.nextInt(6));
        }
        addStars((int) student.getAverageRating());
        tickButton = new JButton(check);
        crossButton = new JButton(cross);
        buttonsPanel = new JPanel();
        setBorder(new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(Color.gray);
                g.drawLine(x,y+height,x+width,y+height);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(0,0,0,0);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });
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

