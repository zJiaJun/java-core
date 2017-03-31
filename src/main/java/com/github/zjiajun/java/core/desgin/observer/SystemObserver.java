package com.github.zjiajun.java.core.desgin.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author zhujiajun
 * @since 2017/3/31
 *
 * 观察者
 */
public class SystemObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(this.getClass().getSimpleName() + " 开始处理具体的反应");
        System.out.println(o.getClass().getSimpleName() + "运行状态: " + arg);
    }
}
