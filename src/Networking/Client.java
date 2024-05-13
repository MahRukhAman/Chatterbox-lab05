package Networking;

import java.io.*;
import java.net.Socket;

public class Client {
    private int port;
    private String host;
    private static BufferedReader in;
    private final String serverAddress;
    private final int serverPort;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connectToServer() {
        try (Socket socket = new Socket(serverAddress, serverPort);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            writer.println("Hello, server!");

        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 8000;
        if (args.length > 0) {
            serverAddress = args[0];
            serverPort = Integer.parseInt(args[1]);
        }
        Client client = new Client(serverAddress, serverPort);
        client.connectToServer();
    }
}
