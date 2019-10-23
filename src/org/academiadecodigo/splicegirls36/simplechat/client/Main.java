package org.academiadecodigo.splicegirls36.simplechat.client;

public class Main {

    public static void main(String[] args) {

        String remoteHostName = null;
        int remotePortNumber = 8080;

        if (args.length != 2) {
            throw new IllegalArgumentException("Exactly two arguments required: chatclient server name Port Number");
        }

        remoteHostName = args[0];
        try {

            remotePortNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }

        ChatClient client = new ChatClient(remoteHostName, remotePortNumber);

        client.run();
    }
}
