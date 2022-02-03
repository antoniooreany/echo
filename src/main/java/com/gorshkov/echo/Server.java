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
    private static final String ECHO = "echo ";

    private static int clientCount;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(3000);
        ) { //listen
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
                    String line = reader.readLine();
                    String content = new String(line.toCharArray(), 0, line.length());
                    System.out.println(content + " " + clientCount++);
                    String message = ECHO + content;
                    writer.write(message.toCharArray());
                }
            }
        }
    }
}