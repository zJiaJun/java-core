package com.github.zjiajun.java.core.design.factory;

/**
 * Created by zhujiajun
 * 15/2/5 09:29
 */
public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is smsSender");
    }
}
