package com.github.zjiajun.java.core.design.staticfactory;


import com.github.zjiajun.java.core.design.factory.Sender;

/**
 * Created by zhujiajun
 * 15/2/5 09:48
 */
public class StaticFactoryTest {
    /**
     *  静态工厂方法模式，不需要创建实例，直接调用。
     * @param args
     */
    public static void main(String[] args) {
        Sender produceMail = StaticSendFactory.produceMail();
        Sender produceSms = StaticSendFactory.produceSms();
        produceMail.send();
        produceSms.send();
    }
}
