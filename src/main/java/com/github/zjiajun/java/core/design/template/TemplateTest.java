package com.github.zjiajun.java.core.design.template;

/**
 * Created by zhujiajun
 * 15/2/5 11:19
 */
public class TemplateTest {
    public static void main(String[] args) {
        AbstractTemplate template = new Somethind();
        int result = template.doSomething();
        System.out.println(result);
    }
}
