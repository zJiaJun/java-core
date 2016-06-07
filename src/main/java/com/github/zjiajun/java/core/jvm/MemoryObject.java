package com.github.zjiajun.java.core.jvm;

/**
 * Created by zhujiajun
 * 16/6/7 10:51
 */
public class MemoryObject {

    private byte[] bytes;

    public MemoryObject(int objectSize) {
        this.bytes = new byte[objectSize];
    }
}
