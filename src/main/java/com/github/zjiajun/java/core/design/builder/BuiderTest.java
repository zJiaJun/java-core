package com.github.zjiajun.java.core.design.builder;

/**
 * Created by zhujiajun
 * 15/2/5 10:13
 */
public class BuiderTest {
    public static void main(String[] args) {
        Builder builder = new Builder();
        builder.produceMailSender(5);
    }
}
