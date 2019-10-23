package org.academiadecodigo.splicegirls36.simplechat.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

class ChatClient {

    public static String CHARSET = "UTF-8";

    private String remoteHostName;
    private int remotePortNumber;
    private Socket clientSocket;
    private BufferedReader fromServer;
    private PrintWriter toServer;
    private BufferedReader fromTerminal;

    ChatClient(String remoteHostName, int remotePortNumber) {

        this.remoteHostName = remoteHostName;
        this.remotePortNumber = remotePortNumber;
        try {
            System.out.println("Establishing connection to chat server: " + remoteHostName + " on port " + remotePortNumber + ".");
            this.clientSocket = new Socket(remoteHostName, remotePortNumber);
            System.out.println("Connection established to: " + clientSocket);
            this.fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), CHARSET));
            this.toServer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), CHARSET)), true);
            this.fromTerminal = new BufferedReader(new InputStreamReader(System.in));

        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

    private String readMessageFromTerminal() {
        String message;
        try {
            while(true) {
                message = fromTerminal.readLine();
                if (message.equals("")) {
                    System.out.println("Please type something. Try again!");
                    continue;
                } else {
                    return message;
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private void sendMessage(String message) {

        toServer.println(message);
    }

    private String readAnswerFromServer() {

        String answer;
        try {
            answer = fromServer.readLine();
            return answer;
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void run() {
        String message = "";
        String answer = "";
        while (!message.equals("\\quit")) {
            System.out.print("Client: ");
            message = readMessageFromTerminal();
            System.out.println();
            sendMessage(message);
            /** answer = readAnswerFromServer();
            System.out.println("Server: " + answer);*/
        }
        try {
            fromServer.close();
            toServer.close();
            fromTerminal.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
