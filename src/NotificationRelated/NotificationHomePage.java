package NotificationRelated;

import MessagesRelated.Notification;
import UserRelated.Student;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class NotificationHomePage extends JFrame {
    private JPanel mainPanel;
    private Student currentUser;
    private GridBagConstraints g;
    private JPanel middlePanel;
    private JPanel insideScrollPanePanel;
    private Notification notification;
    private JPanel TopLabel;
    private ArrayList<JButton> sectionButtons;

    public NotificationHomePage() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(1500, 800);
        currentUser = new Student("Erdem", "erdem.p", 1, "l", "d", "p", "b");
        notification = new Notification(currentUser, currentUser, 1);
        notification.setContent("Sample notification content");
        g = new GridBagConstraints();
        g.gridx = 0;
        g.fill = GridBagConstraints.VERTICAL;
        g.anchor = GridBagConstraints.NORTHWEST;
        g.insets = new Insets(0, 0, 10, 0);
        insideScrollPanePanel.add(new GeneralNotificationViewer(notification), g);
        insideScrollPanePanel.add(new GeneralNotificationViewer(notification), g);
        insideScrollPanePanel.add(new GeneralNotificationViewer(notification), g);
        insideScrollPanePanel.add(new GeneralNotificationViewer(notification), g);
        insideScrollPanePanel.add(new GeneralNotificationViewer(notification), g);
        insideScrollPanePanel.add(new GeneralNotificationViewer(notification), g);


    }

    public void addNotification(Notification notification) {
        insideScrollPanePanel.add(new GeneralNotificationViewer(notification), g);
    }

    public JPanel getMainPanel() {
        return insideScrollPanePanel;
    }

    public JPanel getTopLabel() {
        return TopLabel;
    }

    private static class GeneralNotificationViewer extends JPanel {
        // önce 1 panel
        // panel içinde 2 panel daha
        // her bir panelin içine birer label ekle
        //rengini ayarla
        //en son addablePanel olarak oluşturduğumuz yere ekle
        private JPanel innerPanel1;
        private static final Font myFont = new Font("Arial", Font.BOLD, 14);
        private static final Color myColor = new Color(190, 255, 220); // açık renk
        private static final Color myColorTwo = new Color(145, 241, 233); // koyu renk
        private static final Color myColorRead = new Color(158, 154, 143);
        private static final Color myBorderColor = new Color(81, 82, 84); //frame rengi
        private JPanel innerPanel2;
        private JTextArea label1;
        private Notification notification;
        private JTextArea label2;
        private GridBagConstraints gridBagConstraints;

        public GeneralNotificationViewer(Notification notification) {
            this.notification = notification;
            setLayout(new GridBagLayout());
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            createComponents();
            addMouseListener(new NotificationMouseListener());
        }

        private void createComponents() {
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.anchor = GridBagConstraints.WEST;
            boolean isRead = notification.getReadCondition();
            String senderName = notification.getSender().getName();
            String receiverName = notification.getReceiver().getName();
            String notificationContent = notification.getContent();

            innerPanel1 = new JPanel();

            innerPanel1.setLayout(new GridBagLayout());
            innerPanel1.setBackground(myColor);
            innerPanel2 = new JPanel();
            innerPanel2.setBackground(myColorTwo);
            innerPanel2.setLayout(new GridBagLayout());

            innerPanel2.setBorder(new LineBorder(myBorderColor, 1));

            label1 = new JTextArea("Gülferiz Made A Comment On Your Post:  Great perspective! ");

            label1.setFont(myFont);

            label2 = new JTextArea("Did you know that the world is turning around really fast. Is there a way we couldcouldcouldcouldcouldcouldcouldcouldcouldcouldcouldcouldcouldcouldcouldcouldcould...");
            disableTextAreas();
            innerPanel1.add(label1);

            innerPanel2.add(label2);


            setBorder(new LineBorder(myBorderColor, 1));
            setBackground(myColor);

            gridBagConstraints.weightx = 2;
            gridBagConstraints.insets = new Insets(5, 10, 3, 10);
            if (!isRead) {
                innerPanel1.add(new CircleNotificationIcon(8));
            } else {
                innerPanel1.setBackground(myColorRead);
            }

            add(innerPanel1, gridBagConstraints);
            gridBagConstraints.insets = new Insets(0, 20, 5, 10);
            gridBagConstraints.gridwidth = 1;

            add(innerPanel2, gridBagConstraints);

        }

        private void disableTextAreas() {
            label1.setEditable(false);
            label1.setFocusable(false);
            label2.setEditable(false);
            label2.setFocusable(false);
            label1.setLineWrap(true);
            label2.setLineWrap(true);
            label1.setColumns(60);
            label2.setColumns(60);
            label1.setOpaque(false);
            label2.setOpaque(false);
            label1.setRows(1);
            label2.setRows(1);
            label1.setMargin(new Insets(5, 5, 5, 5));
            label2.setMargin(new Insets(5, 5, 5, 5));
        }
        private class CircleNotificationIcon extends JComponent {
            int perimeter;

            private CircleNotificationIcon(int radius) {
                perimeter = radius * 2;
                setPreferredSize(new Dimension(radius * 2, radius * 2));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(45, 91, 68));
                g.fillOval(0, 0, perimeter, perimeter);
            }
        }

        private class NotificationMouseListener extends MouseAdapter {
            public void mouseClicked(MouseEvent e) {
                System.out.println("clicked!");
                if (!notification.getReadCondition()) {
                    notification.setReadCondition(true);
                    setOpaque(false);
                    innerPanel1.removeAll();
                    innerPanel1.setOpaque(false);
                    innerPanel1.add(label1);
                    revalidate();
                    repaint();
                }
            }
        }
    }
}
