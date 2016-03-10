package com.github.zjiajun.java.core.design.abstractfactory;


import com.github.zjiajun.java.core.design.factory.Sender;
import com.github.zjiajun.java.core.design.factory.SmsSender;

/**
 * Created by zhujiajun
 * 15/2/5 09:55
 */
public class SendSmsFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
