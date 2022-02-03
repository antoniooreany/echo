package com.gorshkov.echo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

//client -> ${content}
//        server -> echo ${content}
//        client -> echo ${content}
//
//        1. Input/Output stream
//        2. Reader/Writer

public class Client {

    private static final String CONTENT = "Hello from client\n\r";
    private static final String URI = "localhost";
    private static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        while (true) {
            try (Socket socket = new Socket(URI, PORT);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ) {
                Scanner scanner = new Scanner(System.in);
                String nextLine = scanner.nextLine();
                writer.write(nextLine + "\r\n");
                writer.flush();
                String contentFromServer = reader.readLine();
                System.out.println(contentFromServer);
                writer.write(contentFromServer.toCharArray());
            }
        }
    }
}
