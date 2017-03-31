package com.github.zjiajun.java.core.desgin.observer;

/**
 * @author zhujiajun
 * @since 2017/3/31
 */
public class App {

    public static void main(String[] args) {
        SystemObservable systemObservable = new SystemObservable();

        SystemObserver systemObserver = new SystemObserver();

        systemObservable.addObserver(systemObserver);

        systemObservable.beginRunning();

        systemObservable.stopRunning();
    }
}
