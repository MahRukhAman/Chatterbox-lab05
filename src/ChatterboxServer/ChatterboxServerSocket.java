package ChatterboxServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatterboxServerSocket {
    private final int port;

    public ChatterboxServerSocket(int port) {
        this.port = port;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chatterbox server started on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Create input and output streams for communication with the client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read messages from the client and echo them back
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Message from client: " + message);
                    // Echo the message back to the client
                    out.println("Server received message: " + message);
                }

                // Close resources
                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
}
