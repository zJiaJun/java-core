package com.github.zjiajun.java.core.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author zhujiajun
 * @since 2016/12/25
 */
public class TraditionalClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8000);
        String file = "/Users/zhujiajun/Documents/temp/test.rar";
        FileInputStream fileInputStream = new FileInputStream(file);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        long t1 = System.currentTimeMillis();
        byte [] buffer = new byte[1024 * 4];
        long read,total = 0;
        while ((read = fileInputStream.read(buffer)) >= 0) {
            total = total + read;
            dataOutputStream.write(buffer);
        }
        long t2 = System.currentTimeMillis();
        System.out.println("use read/write-- "+total+" and time " +(t2 - t1) + " ms");

        dataOutputStream.close();
        socket.close();
        fileInputStream.close();

    }
}
