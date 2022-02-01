package com.gorshkov.echo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

//client -> ${content}
//        server -> echo ${content}
//        client -> echo ${content}
//
//        1. Input/Output stream
//        2. Reader/Writer

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);

        //listen
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = new BufferedInputStream(socket.getInputStream());
            byte[] buffer = new byte[20];
            int count = inputStream.read(buffer);
            System.out.println(new String(buffer, 0, count));

            socket.close();
            inputStream.close();
        }
        serverSocket.close();


    }
}