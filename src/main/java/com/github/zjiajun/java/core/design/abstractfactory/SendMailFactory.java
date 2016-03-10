package com.github.zjiajun.java.core.design.abstractfactory;


import com.github.zjiajun.java.core.design.factory.MailSender;
import com.github.zjiajun.java.core.design.factory.Sender;

/**
 * Created by zhujiajun
 * 15/2/5 09:54
 */
public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
