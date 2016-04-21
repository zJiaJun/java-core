package com.github.zjiajun.java.core.java8;

import java.util.function.Function;

/**
 * Created by zhujiajun
 * 16/4/21 21:21
 */
public class FunctionExample {

    static public void adapter(Function<String,String> function) {
        String msg = "Hello Function";
        System.out.println(function.apply(msg));
    }

    public static void main(String[] args) {
        Function<String,String> function1 = String::toLowerCase;
        Function<String,String> function2 = String::toUpperCase;

        adapter(function1);
        adapter(function2);
    }
}
