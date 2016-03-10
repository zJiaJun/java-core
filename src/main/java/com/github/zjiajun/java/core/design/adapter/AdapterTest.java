package com.github.zjiajun.java.core.design.adapter;

/**
 * Created by zhujiajun
 * 15/2/5 10:18
 */
public class AdapterTest {
    public static void main(String[] args) {
        Targetable targetable = new Adapter();
        targetable.method1();
        targetable.method2();
    }
}
