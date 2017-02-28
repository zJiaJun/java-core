package com.github.zjiajun.java.core.concurrent.book1;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @since 2017/2/28
 *
 * 使用管道进行线程通信
 */
public class PipedData {

    public static void main(String[] args) throws IOException, InterruptedException {

        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream  = new PipedOutputStream();

//        pipedInputStream.connect(pipedOutputStream); 使两个stream建立通信连接，两种方法使一样的
        pipedOutputStream.connect(pipedInputStream);


        Thread writeThread = new Thread(() -> {
            System.out.println("begin write data");
            try {
                for (int i = 0; i < 10; i++) {
                    pipedOutputStream.write(("data-" + i).getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pipedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread readThread = new Thread(() -> {
            System.out.println("begin read data");
            try {
                byte [] buffer = new byte[1024];
                int length;
                //read会阻塞，等待write写入数据
                while ((length = pipedInputStream.read(buffer, 0, buffer.length)) != -1) {
                    String data = new String(buffer, 0, length);
                    System.out.println("read : " + data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pipedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        readThread.start();

        TimeUnit.SECONDS.sleep(2);

        writeThread.start();



    }
}
