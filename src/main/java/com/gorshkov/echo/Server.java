package com.gorshkov.echo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
                     Reader reader = new InputStreamReader(socket.getInputStream());
                     Writer writer = new OutputStreamWriter(socket.getOutputStream());) {
                    char[] buffer = new char[BUFFER_SIZE];
                    int count = reader.read(buffer);
                    String content = new String(buffer, 0, count);
                    System.out.println(content);
                    String message = "echo " + content;
                    writer.write(message.toCharArray());
//                    count = inputStream.read(buffer);
//                    content = new String(buffer, 0, count);
//                    System.out.println(content);
                }
            }
        }
    }
}