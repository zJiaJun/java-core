package com.github.zjiajun.java.core.design.abstractfactory;


import com.github.zjiajun.java.core.design.factory.Sender;

/**
 * Created by zhujiajun
 * 15/2/5 09:53
 */
public interface Provider {
    
    Sender produce();
}
