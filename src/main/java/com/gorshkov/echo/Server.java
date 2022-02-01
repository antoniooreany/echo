package com.gorshkov.echo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

//client -> ${content}
//        server -> echo ${content}
//        client -> echo ${content}
//
//        1. Input/Output stream
//        2. Reader/Writer

public class Server {

    private static final int BUFFER_SIZE = 512;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(3000);
        ) { //listen
            while (true) {
                try (Socket socket = serverSocket.accept();
                     InputStream inputStream = new BufferedInputStream(socket.getInputStream());
                     OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());) {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int count = inputStream.read(buffer);
                    String content = new String(buffer, 0, count);
                    System.out.println(content);
                    String message = "echo " + content;
                    outputStream.write(message.getBytes());
//                    count = inputStream.read(buffer);
//                    content = new String(buffer, 0, count);
//                    System.out.println(content);
                }
            }
        }
    }
}