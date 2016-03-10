package com.github.zjiajun.java.core.design.decorator;

/**
 * Created by zhujiajun
 * 15/2/5 10:36
 */
public class Decorator implements DecoratorSourceable {
    
    private DecoratorSourceable decoratorSourceable;

    public Decorator(DecoratorSourceable decoratorSourceable) {
        super();
        this.decoratorSourceable = decoratorSourceable;
    }

    @Override
    public void method() {
        System.out.println("before decorator");
        decoratorSourceable.method();
        System.out.println("after decorator");
        
    }
}
