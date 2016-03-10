package com.github.zjiajun.java.core.design.adapter;

/**
 * Created by zhujiajun
 * 15/2/5 10:17
 */
public class Adapter extends Source implements Targetable {
    
    @Override
    public void method2() {
        System.out.println("this is targetable method");
    }
}
