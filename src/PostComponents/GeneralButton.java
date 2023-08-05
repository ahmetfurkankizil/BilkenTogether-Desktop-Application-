package PostComponents;

import Icons.IconCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GeneralButton extends JButton implements ActionListener {
    public GeneralButton() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFocusPainted(false);
        addActionListener(this);
    }
    public GeneralButton(String str){
        super(str);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFocusPainted(false);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof DayButtons || e.getSource() instanceof RequestGiveButtons) {
            setSelected(!isSelected());
        }
    }

}
