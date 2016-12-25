package com.github.zjiajun.java.core.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zhujiajun
 * @since 2016/12/25
 */
public class TransferServer {

    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress(8001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(address);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 4);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);
            int read;
            while ((read = socketChannel.read(byteBuffer)) != -1) {
                System.out.println("read: " + read);
                byteBuffer.rewind();
            }
        }
    }
}
