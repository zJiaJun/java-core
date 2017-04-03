package com.github.zjiajun.java.core.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhujiajun
 * 16/5/24 21:51
 *
 * TCP/IP + BIO
 */
public class SocketClientExample {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",5555);

        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        out.print("this is from client request");
        out.close();
    }
}
