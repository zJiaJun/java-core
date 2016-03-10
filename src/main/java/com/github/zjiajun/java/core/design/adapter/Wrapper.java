package com.github.zjiajun.java.core.design.adapter;

/**
 * Created by zhujiajun
 * 15/2/5 10:20
 */
public class Wrapper implements Targetable {
    
    private Source source;

    public Wrapper(Source source) {
        super();
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is targetable method");
    }
}
