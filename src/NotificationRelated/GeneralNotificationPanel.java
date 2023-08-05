package NotificationRelated;
import javax.sql.rowset.WebRowSet;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
public class GeneralNotificationPanel extends JPanel
{
    // önce 1 panel
    // panel içinde 2 panel daha
    // her bir panelin içine birer label ekle
    //rengini ayarla
    //en son addablePanel olarak oluşturduğumuz yere ekle
    private JPanel innerPanel1;
    private static final Font myFont = new Font("Arial",Font.BOLD,14);
    private static final Color myColor = new Color(190, 255, 220); // açık renk
    private static final Color myColorTwo = new Color(38, 255, 242); // koyu renk
    private static final Color myBorderColor = new Color(81, 82, 84); //frame rengi
    private JPanel innerPanel2;
    private JLabel label1;
    private JLabel label2;
    private GridBagConstraints gridBagConstraints;

    public GeneralNotificationPanel()
    {

        setLayout(new GridBagLayout());
        createComponents();
    }
    private void createComponents()
    {
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.WEST;


        innerPanel1 = new JPanel();
        innerPanel1.setBackground(myColor);
        innerPanel1.setLayout(new GridBagLayout());

        innerPanel2 = new JPanel();
        innerPanel2.setBackground(myColorTwo);
        innerPanel2.setLayout(new GridBagLayout());

        innerPanel2.setBorder(new LineBorder(myBorderColor,1));

        label1 = new JLabel("Gülferiz Made A Comment On Your Post:  Great perspective! ");

        label1.setFont(myFont);

        label2 = new JLabel("Did you know that the world is turning around really fast. Is there a way we could...");
        label1.setAlignmentX(0);


        innerPanel1.add(label1,gridBagConstraints);
        innerPanel2.add(label2,gridBagConstraints);


        setBorder(new LineBorder(myBorderColor,1));
        setBackground(myColor);
        gridBagConstraints.insets = new Insets(5,10,3,10);
        add(innerPanel1,gridBagConstraints);
        gridBagConstraints.insets = new Insets(0,10,5,10);

        add(innerPanel2,gridBagConstraints);
    }
















}
