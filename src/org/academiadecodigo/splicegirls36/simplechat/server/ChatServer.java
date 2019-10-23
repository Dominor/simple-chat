package org.academiadecodigo.splicegirls36.simplechat.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

class ChatServer {

    public static String CHARSET = "UTF-8";

    private int portNumber;
    private ServerSocket socket;
    private Socket clientSocket;
    private BufferedReader fromClient;
    private PrintWriter toClient;

    ChatServer(int portNumber) {

        this.portNumber = portNumber;
        try {
            System.out.println("Binding to port " + portNumber);
            this.socket = new ServerSocket(portNumber);
            System.out.println("Server started on: " + socket.getInetAddress().getHostName() + "/" + socket.getInetAddress().getHostAddress() + " local port: " + socket.getLocalPort() + "]");

        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

    private void sendMessage(String message) {

        toClient.println(message);
    }

    private String readMessageFromClient() {

        String message;
        try {
            message = fromClient.readLine();
            return message;
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void run() {
        String message = "";
        String answer = "";
        System.out.println("Waiting for connections.");
        try {
            this.clientSocket= socket.accept();
            this.fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), CHARSET));
            this.toClient = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), CHARSET)), true);
            System.out.println("Client accepted: " + clientSocket);
            while (socket.isBound()) {
                System.out.println("Client: " + (message = fromClient.readLine()));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
