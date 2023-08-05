package MessagesServer;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Client implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;
    private String inMessage;
    private MessagesPanel messagesPanel;
    private MessagesFrame frame;
    
    public Client(MessagesPanel mPanel, MessagesFrame frame) {
        done = false;
        this.frame = frame;
        messagesPanel = mPanel;
    }
    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1", 9999);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            inputHandler iHandler = new inputHandler();
            Thread t = new Thread(iHandler);
            t.start();

            while (true) {
                if ((inMessage = in.readLine()) != null) {
                    addAsASender(inMessage);
                }

            }

        } catch (IOException e) {
            shutDown();
        }
    }

    public String setInput(String s) {
        inMessage = s;
        return s;
    }

    public void shutDown() {
        done = true;
        try {
            in.close();
            out.close();
            if (!client.isClosed()) {
                client.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    class inputHandler implements Runnable {
        BufferedReader inReader;
        String message;

        @Override
        public void run() {
            try {
                inReader = new BufferedReader(new InputStreamReader(System.in));
                while (!done) {
                    
                    if (frame.getTextFieldText() != "" && frame.getButtonPressed()) {
                        message = frame.getTextFieldText();
                        out.println(message);
                        frame.revalidate();
                        frame.setButtonPressed(false);
                    }

                }

            } catch (Exception e) {
                shutDown();
            }
        }

    }

    public static void main(String[] args) {
        MessagesFrame myFrame = new MessagesFrame();
        Client client = new Client(myFrame.getmPanel(), myFrame);
        client.run();
    }

    public void addAsASender(String message) {
        frame.getmPanel().addSenderMessage(new UIMessage(message,true));
        frame.revalidate();
    }
}
