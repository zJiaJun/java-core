package com.github.zjiajun.java.core.java8;

import java.util.function.Supplier;

/**
 * Created by zhujiajun
 * 16/7/1 15:00
 */
public class SupplierFunctionExample {

    private static class Person {
        public void run() {
            System.out.println("Person run");
        }
    }

    private static class Tom extends Person {
        public void run() {
            System.out.println("Tom run");
        }
    }

    private void runMethod(Supplier<? extends Person> fiSupplier) {
        Person person = fiSupplier.get();
        person.run();
    }

    public static void main(String[] args) {
        SupplierFunctionExample example = new SupplierFunctionExample();

        //java8 之前使用内部类
        example.runMethod(new Supplier<Person>() {
            @Override
            public Person get() {
                return new Person();
            }
        });
        example.runMethod(() -> new Person());//lambda

        example.runMethod(Person::new);//method reference
        example.runMethod(Tom::new);


    }
}
