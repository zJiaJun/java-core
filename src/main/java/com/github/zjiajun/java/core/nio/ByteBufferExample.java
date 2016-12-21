package com.github.zjiajun.java.core.nio;

import java.nio.ByteBuffer;

/**
 * @author zhujiajun
 * @since 2016/12/21
 */
public class ByteBufferExample {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(15);
        //pos=0,lim=15,cap=15
        System.out.println(byteBuffer);

        for (int i = 0;i < 10;i++) {
            byteBuffer.put((byte)i);
        }
        //pos=10,lim=15,cap=15
        System.out.println(byteBuffer);

        //pos=0,lim=10,cap=15
        byteBuffer.flip();
        System.out.println(byteBuffer);

        for (int i = 0; i < 5; i++) {
            byteBuffer.get();
        }
        //pos=5,lim=10,cap=15
        System.out.println(byteBuffer);

    }
}
