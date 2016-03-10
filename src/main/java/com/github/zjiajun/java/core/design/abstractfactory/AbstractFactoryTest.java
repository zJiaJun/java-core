package com.github.zjiajun.java.core.design.abstractfactory;


import com.github.zjiajun.java.core.design.factory.Sender;

/**
 * Created by zhujiajun
 * 15/2/5 09:56
 */
public class AbstractFactoryTest {
    /**
     * 抽象工厂方法模式，无需改动现场的代码，扩展性好。
     * 如现在增加一个功能，则只需做一个实现类,实现Sender接口，同时做一个工厂类，实现Provider接口
     * @param args
     */
    public static void main(String[] args) {
        Provider provider = new SendMailFactory();
        Sender produce = provider.produce();
        produce.send();
    }
}
