package com.github.zjiajun.java.core.design.strategy;

/**
 * Created by zhujiajun
 * 15/2/5 11:06
 */
public abstract class AbstractCalculator {
    
    public int[] split(String exp,String opt) {
        String [] array  = exp.split(opt);
        int [] arrayInt = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return  arrayInt;
    }
}
