package com.gorshkov.echo;

import java.io.*;
import java.net.Socket;

//client -> ${content}
//        server -> echo ${content}
//        client -> echo ${content}
//
//        1. Input/Output stream
//        2. Reader/Writer

public class Client {

    private static final String CONTENT = "Hello from client";
    private static final String URI = "localhost";
    private static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(URI, PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            writer.write(CONTENT.toCharArray());
            writer.flush();
            String line = reader.readLine();

            String contentFromServer = new String(line.toCharArray(), 0, line.length());
            System.out.println(contentFromServer);

            writer.write(contentFromServer.toCharArray());
        }
    }
}