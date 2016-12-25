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

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            byte [] buffer = new byte[1024 * 4];
            while (true) {
                int nread = dataInputStream.read(buffer , 0, 4096);
                if (0 == nread)
                    break;
            }
            socket.close();
        }
    }
}
