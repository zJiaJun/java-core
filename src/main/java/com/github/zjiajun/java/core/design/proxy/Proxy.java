package com.github.zjiajun.java.core.design.proxy;

/**
 * Created by zhujiajun
 * 15/2/5 10:41
 */
public class Proxy implements ProxySourceable {
    
    private ProxySource proxySource;

    public Proxy() {
        super();
        this.proxySource = new ProxySource();
    }

    @Override
    public void method() {
        before();
        proxySource.method();
        after();
        
    }

    private void after() {
        System.out.println("after proxy");
    }

    private void before() {
        System.out.println("before proxy");
    }
}
