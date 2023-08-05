package PostComponents;

import javax.swing.*;
import java.awt.*;

import static javax.swing.text.StyleConstants.setForeground;

public class DayButtons extends GeneralButton {
    public static final Font daysButtonFont = new Font("Times New Roman",Font.PLAIN,10);
    public DayButtons(){
        setFont(daysButtonFont);
        setOpaque(true);
        setForeground(Color.black);
    }
    public DayButtons(String str){
        super(str);
        setFont(daysButtonFont);
        setOpaque(true);
        setForeground(Color.black);

    }

}
