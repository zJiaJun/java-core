package com.github.zjiajun.java.core.java8;

import java.util.function.Consumer;

/**
 * Created by zhujiajun
 * 16/7/1 15:10
 */
public class ConsumerFunctionExample {


    private int age;

    private void updateProperty(ConsumerFunctionExample example, Consumer<ConsumerFunctionExample> fiConsumer) {
        fiConsumer.accept(example);
    }

    public static void main(String[] args) {
        ConsumerFunctionExample example = new ConsumerFunctionExample();
        System.out.println(example.age);
        example.updateProperty(example, e -> e.age = 100);
        System.out.println(example.age);

        Consumer<ConsumerFunctionExample> consumer = System.out::println;
        example.updateProperty(example, consumer.andThen(e -> e.age = e.age + 10));
        System.out.println(example.age);
    }

    @Override
    public String toString() {
        return "ConsumerFunctionExample{" +
                "age=" + age +
                '}';
    }
}
