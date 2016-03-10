package com.github.zjiajun.java.core.design.factory;

/**
 * Created by zhujiajun
 * 15/2/5 09:32
 */
public class FactoryTest {
    /**
     * 工厂模式，对实现了同一接口的一些类进行实例的创建。
     * @param args
     */
    public static void main(String[] args) {
        SendFactory sendFactory = new SendFactory();
        Sender sender = sendFactory.produce("mail");
        sender.send();
    }
}
