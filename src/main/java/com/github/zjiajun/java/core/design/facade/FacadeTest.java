package com.github.zjiajun.java.core.design.facade;

/**
 * Created by zhujiajun
 * 15/2/5 11:02
 */
public class FacadeTest {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }
}
