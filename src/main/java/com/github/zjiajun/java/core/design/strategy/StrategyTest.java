package com.github.zjiajun.java.core.design.strategy;

/**
 * Created by zhujiajun
 * 15/2/5 11:11
 */
public class StrategyTest {
    public static void main(String[] args) {
        String exp  = "2+8";
        ICalculator calculator = new Plus();
        int result = calculator.calculate(exp);
        System.out.println(result);
    }
}
