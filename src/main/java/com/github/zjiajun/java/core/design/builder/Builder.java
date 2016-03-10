package com.github.zjiajun.java.core.design.builder;

import com.github.zjiajun.java.core.design.factory.MailSender;
import com.github.zjiajun.java.core.design.factory.Sender;
import com.github.zjiajun.java.core.design.factory.SmsSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujiajun
 * 15/2/5 10:08
 */
public class Builder {
    
    private List<Sender> list = new ArrayList<>();
    
    public void produceMailSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new MailSender());
        }
    }
    
    public void produceSmsSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new SmsSender());
        }
        
    }
}
