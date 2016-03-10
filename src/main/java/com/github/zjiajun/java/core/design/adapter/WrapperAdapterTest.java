package com.github.zjiajun.java.core.design.adapter;

/**
 * Created by zhujiajun
 * 15/2/5 10:23
 */
public class WrapperAdapterTest {
    public static void main(String[] args) {
        Source source = new Source();
        Targetable targetable = new Wrapper(source);
        targetable.method1();
        targetable.method2();
    }
}
