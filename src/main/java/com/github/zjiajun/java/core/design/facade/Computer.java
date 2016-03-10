package com.github.zjiajun.java.core.design.facade;

/**
 * Created by zhujiajun
 * 15/2/5 10:59
 */
public class Computer {
    
    private Cpu cpu;
    private Memory memory;
    private Disk disk;

    public Computer() {
        this.cpu = new Cpu();
        this.memory = new Memory();
        this.disk = new Disk();
    }
    
    public void startup() {
        System.out.println("start computer");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("start computer finished");
    }
    
    public void shutdown() {
        System.out.println("shutdown computer");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("shutdown computer finished");
        
    }
}
