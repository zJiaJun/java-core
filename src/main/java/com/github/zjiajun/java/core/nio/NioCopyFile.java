package com.github.zjiajun.java.core.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhujiajun
 * @since 2016/12/9
 */
public class NioCopyFile {

    private void copyFile(String source,String target) {
        FileChannel fisChannel = null;
        FileChannel fosChannel = null;
        try {
            FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(target);
            fisChannel = fis.getChannel();
            fosChannel = fos.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            while (true) {
                byteBuffer.clear();
                int len = fisChannel.read(byteBuffer);
                if (len == -1) break;
                byteBuffer.flip();
                fosChannel.write(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fisChannel != null) try {
                fisChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (fosChannel != null) try {
                fosChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        NioCopyFile nioCopyFile = new NioCopyFile();
        nioCopyFile.copyFile("src/main/resources/nio-copy1.log","src/main/resources/nio-copy2.log");

    }
}
