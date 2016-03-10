package com.github.zjiajun.java.core.design.multifactory;


import com.github.zjiajun.java.core.design.factory.Sender;

/**
 * Created by zhujiajun
 * 15/2/5 09:41
 */
public class MultiFactoryTest {
    /**
     * 对普通工厂的改进，在普通工厂方法模式中，如果字符串出错，则不能正确创建对象。
     * 而多个工厂模式是提供多个工厂方法，分别创建对象。
     * @param args
     */
    public static void main(String[] args) {
        MultiSendFactory multiSendFactory = new MultiSendFactory();
        Sender produceMail = multiSendFactory.produceMail();
        Sender produceSms = multiSendFactory.produceSms();
        produceMail.send();
        produceSms.send();
    }
}
