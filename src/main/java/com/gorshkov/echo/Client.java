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
             InputStream inputStream = new BufferedInputStream(socket.getInputStream());
             OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
        ) {
            outputStream.write(CONTENT.getBytes());
            outputStream.flush();
            byte[] buffer = new byte[BUFFER_SIZE];
            int count = inputStream.read(buffer);

            String contentFromServer = new String(buffer, 0, count);
            System.out.println(contentFromServer);

            outputStream.write(contentFromServer.getBytes());
        }
    }
}