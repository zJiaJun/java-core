package com.github.zjiajun.java.core.design.decorator;

/**
 * Created by zhujiajun
 * 15/2/5 10:38
 */
public class DecoratorTest {
    /**
     * 装饰器模式 
     * 应用场景：需要扩展一个类的功能
     * @param args
     */
    public static void main(String[] args) {
        DecoratorSourceable decoratorSourceable = new DecoratorSource();
        DecoratorSourceable obj = new Decorator(decoratorSourceable);
        obj.method();
    }
}
