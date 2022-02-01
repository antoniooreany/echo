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
    private static final int BUFFER_SIZE = 512;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 3000);
             Reader reader = new InputStreamReader(socket.getInputStream());
             Writer writer = new OutputStreamWriter(socket.getOutputStream());
        ) {
            writer.write(CONTENT.toCharArray());
            writer.flush();
            char[] buffer = new char[BUFFER_SIZE];
            int count = reader.read(buffer);

            String contentFromServer = new String(buffer, 0, count);
            System.out.println(contentFromServer);

            writer.write(contentFromServer.toCharArray());
        }
    }
}