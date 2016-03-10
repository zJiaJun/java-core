package com.github.zjiajun.java.core.design.proxy;

/**
 * Created by zhujiajun
 * 15/2/5 10:45
 */
public class ProxyTest {
    public static void main(String[] args) {
        ProxySourceable proxySourceable = new Proxy();
        proxySourceable.method();
    }
}
