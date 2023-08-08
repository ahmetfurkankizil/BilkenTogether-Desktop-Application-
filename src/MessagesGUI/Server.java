package MessagesGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private ServerSocket server;
    private Socket client;
    private ArrayList<ConnectionHandler> connections;
    private ConnectionHandler handler;
    private boolean done;
    private ExecutorService pool;

    public Server() {
        connections = new ArrayList<>();
        done = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(9999);
            pool = Executors.newCachedThreadPool();
            while (!done) {
                client = server.accept();
                handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void broadCast(String message , ConnectionHandler cHandler) {
        for (ConnectionHandler connectionHandler : connections) {
            if (connectionHandler != null && !connectionHandler.equals(cHandler)) {
                connectionHandler.sendMessage(message);
            }
        }
    }

    public void shutDown() {
        done = true;
        if (!server.isClosed()) {
            try {
                server.close();
                for (ConnectionHandler ch : connections) {
                    ch.shutDown();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public class ConnectionHandler implements Runnable {
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        public static int clientNumCounter = 0;
        private int clientNum;
        public ConnectionHandler(Socket client) {
            this.client = client;
            clientNumCounter += 1;
            clientNum = clientNumCounter;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String message;
                while ((message = in.readLine()) != null) {
                    broadCast(message,this);
                }
            } catch (IOException e) {
                // TODO: handle
            }
        }
        

        public void sendMessage(String message) {
            out.println(message);
        }

        public void shutDown() {
            try {
                in.close();
                out.close();
                if (!client.isClosed()) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    public static void main(String[] args) {
        Server ser1 = new Server();
        ser1.run();
    }
}