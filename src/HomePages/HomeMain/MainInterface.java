package HomePages.HomeMain;

import Other.Icons.IconCreator;
import Posts.RequestablePost;
import PostsGUI.PostViewer;
import UserRelated.User;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public interface MainInterface extends ImageObserver, MenuContainer, Serializable, Accessible, WindowConstants, RootPaneContainer{
    ImageIcon back = IconCreator.getIconWithSize(IconCreator.backIcon, 30, 30);

    static byte[] readPDFToByteArray(String filePath) throws IOException {
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            fileInputStream = new FileInputStream(filePath);
            byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            return byteArrayOutputStream.toByteArray();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
    }

    static void main(String[] args) {
        //Main lessonsPage = new Main();
    }

    void update();

    void setUpPastMessages();

    void refreshProfilePhotos();

    void setCurrentUser(User user);

    void generalSetup();

    void goBack(PostViewer viewer);

    void expandPost(PostViewer p);

    String getTextFieldText();

    User getCurrentUser();

    void goBacTokRequests();

    void extendRequest(RequestablePost panel);

    boolean getButtonPressed();

    void setButtonPressed(boolean b);
}
