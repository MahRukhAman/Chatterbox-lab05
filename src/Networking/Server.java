package Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int port ;

    public Server(int port) {
        this.port = port;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server started on port " + 8000);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                // Handle client connection (e.g., create a new thread or process)
            }
        } catch (IOException e) {
            System.err.println("Error starting the server: " + e.getMessage());
        }
    }

    // Add more methods and fields here as needed

    public static void main(String[] args) {
        int port = 8000; // Default port
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number, using default port 8000");
            }
        }
        Server server = new Server(port);
        server.startServer();
    }
}
