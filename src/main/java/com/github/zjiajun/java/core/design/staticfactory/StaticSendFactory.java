package com.github.zjiajun.java.core.design.staticfactory;


import com.github.zjiajun.java.core.design.factory.MailSender;
import com.github.zjiajun.java.core.design.factory.Sender;
import com.github.zjiajun.java.core.design.factory.SmsSender;

/**
 * Created by zhujiajun
 * 15/2/5 09:48
 */
public class StaticSendFactory {
    
    public static Sender produceSms() {
        return new SmsSender();
    }
    
    public static Sender produceMail() {
        return new MailSender();
    }
}
