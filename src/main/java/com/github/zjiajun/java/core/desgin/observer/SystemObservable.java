package com.github.zjiajun.java.core.desgin.observer;

import java.util.Observable;

/**
 * @author zhujiajun
 * @since 2017/3/31
 *
 * 观察目标
 */
public class SystemObservable extends Observable {

    private boolean running;

    public void beginRunning() {
        this.running = true;
        super.setChanged();
        notifyObservers(running);
    }

    public void stopRunning() {
        this.running = false;
        super.setChanged();
        notifyObservers(running);
    }
}
