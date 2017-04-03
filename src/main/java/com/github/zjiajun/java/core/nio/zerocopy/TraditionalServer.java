package com.github.zjiajun.java.core.nio.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhujiajun
 * @since 2016/12/25
 */
public class TraditionalServer {


    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            byte [] buffer = new byte[BUFFER_SIZE];
            while (true) {
                int nread = dataInputStream.read(buffer , 0, BUFFER_SIZE);
                if (0 == nread)
                    break;
            }
            socket.close();
        }
    }
}
