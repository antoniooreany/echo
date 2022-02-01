package com.gorshkov.echo;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

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
             OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());) {
            outputStream.write(content.getBytes());
        }
    }
}