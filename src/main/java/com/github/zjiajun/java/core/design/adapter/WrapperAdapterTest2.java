package com.github.zjiajun.java.core.design.adapter;

/**
 * Created by zhujiajun
 * 15/2/5 10:29
 */
public class WrapperAdapterTest2 {
    public static void main(String[] args) {
        Sourceable sourceable1 = new SourceSub1();
        Sourceable sourceable2 = new SourceSub2();
        
        sourceable1.method1();
        sourceable1.method2();
        sourceable2.method1();
        sourceable2.method2();
    }
}
