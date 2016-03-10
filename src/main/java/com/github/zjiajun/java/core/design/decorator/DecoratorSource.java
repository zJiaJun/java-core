package com.github.zjiajun.java.core.design.decorator;

/**
 * Created by zhujiajun
 * 15/2/5 10:36
 */
public class DecoratorSource implements DecoratorSourceable {

    @Override
    public void method() {
        System.out.println("the original method");
    }
}
