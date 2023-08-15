package MessagesGUI;

import HomePages.HomeMain.HomeMain;
import UserRelated.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class Client implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;
    private String inMessage;
    private ConversationPanel messagesPanel;
    private HomeMain frame;
    private Server server;
    
    public Client(ConversationPanel mPanel, HomeMain frame, Server server) {
        done = false;
        try {
            System.out.println(Inet4Address.getLocalHost());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        this.frame = frame;
        messagesPanel = mPanel;
        this.server = server;
        //Thread t2 = new Thread(server);
        //t2.start();
    }
    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1",22);
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
                    
                    if (!frame.getTextFieldText().isEmpty()&& frame.getButtonPressed()) {
                        message = frame.getTextFieldText();
                        out.println(message);
                        messagesPanel.repaint();
                        messagesPanel.revalidate();
                        frame.setButtonPressed(false);
                        System.out.println("pressed");

                    }

                }

            } catch (Exception e) {
                shutDown();
            }
        }

    }
    private User recipient;
    public void setCurrentRecipient(User recipient){this.recipient = recipient; }


    public void addAsASender(String message) {
        Message temp = new Message(frame.getCurrentUser(), recipient,message,new Date().toString());
        messagesPanel.getMessage(temp);
        messagesPanel.repaint();
        messagesPanel.revalidate();
        System.out.println("pressed");
    }
}
