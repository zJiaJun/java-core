package com.github.zjiajun.java.core.design.strategy;

/**
 * Created by zhujiajun
 * 15/2/5 11:08
 */
public class Plus extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int [] arrayInt = split(exp,"\\+");
        return  arrayInt[0] + arrayInt[1];
    }
}
