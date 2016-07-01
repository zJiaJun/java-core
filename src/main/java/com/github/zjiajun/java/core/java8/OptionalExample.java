package com.github.zjiajun.java.core.java8;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by zhujiajun
 * 16/6/30 20:58
 *
 * refence: http://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html
 */
public class OptionalExample {

    public Optional<String> getNull() {
        return Optional.ofNullable(null);
    }

    public Optional<String> getInstance() {
        return Optional.ofNullable("option value");
    }

    public static void main(String[] args) {
        OptionalExample optionalExample = new OptionalExample();
        Optional<String> aStr = optionalExample.getInstance();
        Optional<String> aNull = optionalExample.getNull();
        if (aStr.isPresent()) {
            System.out.println(aStr.get());
        }


        try {
            System.out.println(aNull.get());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        aStr.ifPresent(v -> System.out.println(v.toUpperCase()));

        System.out.println(aNull.orElse("default value"));

        System.out.println(aNull.orElseGet(()-> "default value from supplier"));

        try {
            System.out.println(aNull.orElseThrow(IllegalArgumentException::new));
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        /*
         * flatMap方法与map方法类似，区别在于mapping函数的返回值不同。
         * map方法的mapping函数返回值可以是任何类型T，而flatMap方法的mapping函数必须是Optional。
         */
        Optional<String> aStrMap = aStr.map(String::toUpperCase);
        System.out.println(aStrMap.orElse("not null"));
        Optional<String> aNullMap = aNull.map(String::toUpperCase);
        System.out.println(aNullMap.orElse("null map"));


        Optional<String> s = aStr.flatMap(v -> Optional.of(v.toUpperCase()));
        System.out.println(s.orElse("not null"));

        //filter方法检查给定的Option值是否满足某些条件
        //如果满足则返回同一个Option实例，否则返回空Optional
        Optional<String> name = Optional.of("Sanaulla");
        Optional<String> longName = name.filter((value) -> value.length() > 6);
        System.out.println(longName.orElse("The name is less than 6 characters"));//输出Sanaulla

        //另一个例子是Optional值不满足filter指定的条件
        Optional<String> anotherName = Optional.of("Sana");
        Optional<String> shortName = anotherName.filter((value) -> value.length() > 6);
        //输出：name长度不足6字符
        System.out.println(shortName.orElse("The name is less than 6 characters"));






    }
}
