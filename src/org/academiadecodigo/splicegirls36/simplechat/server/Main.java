package org.academiadecodigo.splicegirls36.simplechat.server;

public class Main {

    public static void main(String[] args) {

        int portNumber = 8080;

        if (args.length != 1) {
            throw new IllegalArgumentException("Exactly one argument required: chatclient ServerName PortNumber");
        }

        try {

            portNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }

        ChatServer client = new ChatServer(portNumber);

        client.run();
    }
}
