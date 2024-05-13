package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String host;
    private final int port;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Socket socket;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 8000);
        client.start(); // Call start method here

        System.out.println("Please enter text:");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            client.writeMessage(message);
            client.readMessage();
        }
}

    private void start() {
        try {
            socket = new Socket(host, port);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Socket and streams initialized successfully.");
        } catch (IOException e) {
            System.err.println("Error initializing socket and streams: " + e.getMessage());
        }
    }

    private void writeMessage(String message) {
        if (printWriter != null) {
            printWriter.println(message);
            printWriter.flush();
        }
    }

    private void readMessage() {
        try {
            String receivedMessage = bufferedReader.readLine();
            if (receivedMessage != null) {
                System.out.println("Server response: " + receivedMessage);
            }
        } catch (IOException e) {
            System.err.println("Error reading from server: " + e.getMessage());
        }
    }
}
