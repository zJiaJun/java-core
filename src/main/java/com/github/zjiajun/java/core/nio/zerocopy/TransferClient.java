package com.github.zjiajun.java.core.nio.zerocopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zhujiajun
 * @since 2016/12/25
 */
public class TransferClient {

    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress("localhost",8001);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(address);
        socketChannel.configureBlocking(true);

        File file = new File("/Users/zhujiajun/Documents/temp/test.rar");

        FileChannel fileChannel = new FileInputStream(file).getChannel();
        long t1 = System.currentTimeMillis();
        long curnset = fileChannel.transferTo(0,file.length(),socketChannel);
        long t2 = System.currentTimeMillis();
        System.out.println("user transfer-- " + curnset + " and time " + (t2 - t1) + " ms");
    }
}
