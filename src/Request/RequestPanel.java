package Request;

import HomePage.Main.HomeMain;
import Posts.LessonPost;
import Posts.RequestablePost;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RequestPanel extends JPanel {

    private RequestablePost requestablePost;
    private HomeMain main;

    public RequestPanel(RequestablePost requestablePost, HomeMain main) {

        this.main = main;
        this.requestablePost = requestablePost;
        setLayout(new GridBagLayout());
        addComponents();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.extendRequest(requestablePost);
            }
        });
    }
    public JPanel getThis(){
        return this;
    }
    private void addComponents() {
        GridBagConstraints c = new GridBagConstraints();

        // Profile Photo Label
        JLabel profilePhotoLabel = new JLabel("");//IconCreator.getIconWithSize(UserProfilePage.byteToImageIcon(main.getCurrentUser().getProfilePhoto()),40,40));
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,5,5,5);
        add(profilePhotoLabel, c);

        // Name and Surname Label
        JLabel nameSurnameLabel = new JLabel(requestablePost.getSender().getName());
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(nameSurnameLabel, c);

        // Request Text Area
        JTextArea requestTextArea = new JTextArea();
        requestTextArea.setColumns(50);
        requestTextArea.setText(requestablePost.getPostDescription());
        requestTextArea.setLineWrap(true);
        requestTextArea.setEditable(false);
        requestTextArea.setFocusable(false);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        add(requestTextArea, c);



        // Request Status Label
        JLabel requestStatusLabel = new JLabel();
        requestStatusLabel.setBackground(Color.CYAN);
        requestStatusLabel.setOpaque(true);
        JLabel typeFiletLabel = new JLabel(requestablePost.getTypeFilter());
        typeFiletLabel.setOpaque(true);
        typeFiletLabel.setBackground(Color.PINK);
        c.fill = GridBagConstraints.NONE;
        if (requestablePost instanceof LessonPost) {
            LessonPost lesPost = (LessonPost) requestablePost;
            requestStatusLabel.setText(lesPost.getRequestType() ? "LESSON REQUEST" : "LESSON GIVE");
            c.gridx += 1;

            add(requestStatusLabel, c);
        }
        add(typeFiletLabel,c);
        c.gridx += 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;

        // Type Name Label
        JLabel typeNameLabel = new JLabel(requestablePost.getTypeFilter());
        typeNameLabel.setBackground(Color.LIGHT_GRAY);
        c.gridx = 2;
        add(typeNameLabel, c);

        // Request Number Label
        JLabel requestNumberLabel = new JLabel(requestablePost.getRequestCollection().size()+"");
        requestNumberLabel.setBackground(Color.RED);
        requestNumberLabel.setOpaque(true);
        c.gridx = 3;
        add(requestNumberLabel, c);
    }

}
