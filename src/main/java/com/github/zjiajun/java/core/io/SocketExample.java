package com.github.zjiajun.java.core.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhujiajun
 * 16/5/24 21:48
 *
 * TCP/IP + BIO
 */
public class SocketExample {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        Socket accept = serverSocket.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        System.out.println(in.readLine());
    }
}
