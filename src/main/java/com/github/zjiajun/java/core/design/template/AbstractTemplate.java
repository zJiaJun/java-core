package com.github.zjiajun.java.core.design.template;

/**
 * Created by zhujiajun
 * 15/2/5 11:15
 */
public abstract class AbstractTemplate {
    
    public final int doSomething() {
       return doWork();
    }
    
    public abstract int doWork();
}
