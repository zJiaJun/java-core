package com.github.zjiajun.java.core.design.proxy;

/**
 * Created by zhujiajun
 * 15/2/5 10:42
 */
public class ProxySource implements ProxySourceable {
    @Override
    public void method() {
        System.out.println("the original method");
    }
}
