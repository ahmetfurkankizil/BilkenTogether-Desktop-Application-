package PostComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequestGiveButtons extends GeneralButton implements ActionListener {
        public static int isRequest =0;
        public RequestGiveButtons(){

        }
        @Override
        public void actionPerformed(ActionEvent event){
            super.actionPerformed(event);
            if (getText().equalsIgnoreCase("Give")){
                isRequest++;
                if (isRequest == 2){
                    setSelected(false);
                    isRequest-=2;
                }else if (!isSelected()) {
                    isRequest--;
                }
            }
            if (getText().equalsIgnoreCase("Request")){
                isRequest++;
                if (isRequest == 2){
                    setSelected(false);
                    isRequest-=2;
                } else if (!isSelected()) {
                    isRequest--;
                }
            }

        }
}
