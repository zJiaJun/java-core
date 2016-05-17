package com.github.zjiajun.java.core.other;

/**
 * Created by zhujiajun
 * 16/5/17 22:05
 */
public class CallbackExample {

    public interface Someone {

        void doSomething(String result);
    }

    private String name;

    public CallbackExample(String name) {
        this.name = name;
    }

    public void doAny() {
        new Any().dodoAny(new Someone() {
            @Override
            public void doSomething(String result) {
                System.out.println(result);
            }
        });
    }

    public class Any {

        public void dodoAny(Someone someone) {
            String result = "this is result";
            someone.doSomething(result);
        }
    }

    public static void main(String[] args) {
        CallbackExample callbackExample = new CallbackExample("callback");
        callbackExample.doAny();

    }
}
