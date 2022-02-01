package com.gorshkov.echo;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

//client -> ${content}
//        server -> echo ${content}
//        client -> echo ${content}
//
//        1. Input/Output stream
//        2. Reader/Writer

public class Client {

    private static final String content = "Hello from client";

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 3000);
             OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
             InputStream inputStream = new BufferedInputStream(socket.getInputStream());
        ) {
            outputStream.write(content.getBytes());

            byte[] buffer = new byte[20];
            int count = inputStream.read(buffer);

            String contentFromServer = new String(buffer, 0, count);
            System.out.println(contentFromServer);

            outputStream.write(contentFromServer.getBytes());
        }
    }
}