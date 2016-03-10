package com.github.zjiajun.java.core.design.multifactory;


import com.github.zjiajun.java.core.design.factory.MailSender;
import com.github.zjiajun.java.core.design.factory.Sender;
import com.github.zjiajun.java.core.design.factory.SmsSender;

/**
 * Created by zhujiajun
 * 15/2/5 09:38
 */
public class MultiSendFactory {
    public Sender produceMail() {
        return new MailSender();
    }
    public Sender produceSms() {
        return new SmsSender();
        
    }
}
