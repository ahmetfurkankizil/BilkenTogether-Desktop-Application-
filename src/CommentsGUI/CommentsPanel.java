package CommentsGUI;

import CommentsRelated.Comment;
import Icons.IconCreator;
import UserRelated.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CommentsPanel extends JPanel{

    private final Comment COMMENT;
    JLabel hearthLabel;
    boolean liked;
    private final ImageIcon likeIcon = IconCreator.getIconWithSize(IconCreator.emptyLikeIcon,40,30);
    private final ImageIcon solidLikeIcon = IconCreator.getIconWithSize(IconCreator.activeLikeIcon,40,30);
    private User user;
    JLabel likeCountLabel;
    private static final int starwidth = 20;
    private ImageIcon emptyStar = IconCreator.getIconWithSize(IconCreator.emptyStarIcon, starwidth, starwidth);
    private ImageIcon halfStar = IconCreator.getIconWithSize(IconCreator.halfStarIcon, starwidth, starwidth);
    private ImageIcon fullStar = IconCreator.getIconWithSize(IconCreator.starIcon, starwidth, starwidth);
    public CommentsPanel(Comment comment, User user ,boolean isReview, ReviewPanel reviewPanel) {
        super();
        COMMENT = comment;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (isReview)
            addStars(reviewPanel);
        addComponents();
        this.user = user;
        liked = comment.checkIfUserAlreadyLiked(user);
    }

    private void addStars(ReviewPanel reviewPanel) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 1;
        JPanel starPanel = new JPanel(new FlowLayout());
        String text = reviewPanel.getTextField().getText();
        int review = Integer.parseInt(text);
        System.out.println(review);
        for (int i = 0; i < review; i++) {
            starPanel.add(new JLabel(fullStar),c);

        }
        for (int i = 0; i < 5-review; i++) {
            starPanel.add(new JLabel(emptyStar));
        }
        reviewPanel.getTextField().setText("");
        add(starPanel,c);
    }

    private void addComponents() {
        GridBagConstraints c = new GridBagConstraints();

        // Profile Photo Label
        JLabel profilePhotoLabel = new JLabel("Profile Photo");
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,5,5,5);
        add(profilePhotoLabel, c);

        // Name and Surname Label
        JLabel nameSurnameLabel = new JLabel(COMMENT.getCommenter().getName());
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(nameSurnameLabel, c);

        // Comment Text Area
        JTextArea commentTextArea = new JTextArea();
        commentTextArea.setColumns(50);
        commentTextArea.setText(COMMENT.getContent());
        commentTextArea.setLineWrap(true);
        commentTextArea.setEditable(false);
        commentTextArea.setFocusable(false);
        commentTextArea.setMargin(new Insets(5,5,5,5));
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        add(commentTextArea, c);

        // Like Label
        if (liked)
            hearthLabel= new JLabel(solidLikeIcon);
        else
            hearthLabel = new JLabel(likeIcon);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        setUpHearthLabel();
        add(hearthLabel, c);
        // like Count Label
        likeCountLabel = new JLabel(COMMENT.getLikeCount() + "");
        c.gridx = 2;
        add(likeCountLabel, c);
        setBorder(new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(Color.gray);
                g.drawLine(x,y+height,x+width,y+height);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(5,5,5,5);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });
    }

    private void setUpHearthLabel() {
        hearthLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hearthLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!liked){
                    user.likeComment(COMMENT);
                    liked = true;
                    hearthLabel.setIcon(solidLikeIcon);
                    likeCountLabel.setText(COMMENT.getLikeCount() + "");

                }else{
                    user.withDrawLike(COMMENT);
                    hearthLabel.setIcon(likeIcon);
                    liked = false;
                    likeCountLabel.setText(COMMENT.getLikeCount() + "");
                }
            }
        });



    }


}
