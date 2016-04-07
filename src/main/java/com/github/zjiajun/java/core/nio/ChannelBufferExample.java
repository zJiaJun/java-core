package com.github.zjiajun.java.core.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhujiajun
 * 16/4/7 20:35
 */
public class ChannelBufferExample {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/lines.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(50);
        int read = fileChannel.read(byteBuffer);
        while (read != -1) {
            System.out.println("Read " + read);
            byteBuffer.flip(); //写模式变读模式
            while (byteBuffer.hasRemaining()) {
                System.out.print((char)byteBuffer.get());
            }
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);
        }
        randomAccessFile.close();
    }
}
