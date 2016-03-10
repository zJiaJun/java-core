package com.github.zjiajun.java.core.design.factory;

/**
 * Created by zhujiajun
 * 15/2/5 09:30
 */
public class SendFactory {
    
    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if("sms".equals(type)) {
            return  new SmsSender();
        } else {
            System.out.println("error");
            return null;
        }
         
    }
}
