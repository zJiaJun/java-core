package com.github.zjiajun.java.core.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhujiajun
 * 15/11/4 21:55
 */
public class LambdaExample {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("zJiaJun","Leon");

        //基本写法
        List<String> lowercaseName1 = names.stream().map((String name) -> {return name.toLowerCase();}).collect(Collectors.toList());
        //省略参数类型,lambda绝大多数情况可以从上下文环境推断出
        List<String> lowercaseName2 = names.stream().map(name -> {return  name.toLowerCase();}).collect(Collectors.toList());
        //只包含一条语句是,可以省略大括号,return和结尾的分号
        List<String> lowercaseName3 = names.stream().map(name -> name.toLowerCase()).collect(Collectors.toList());
        //使用Method Reference
        List<String> lowercaseNames4 = names.stream().map(String::toLowerCase).collect(Collectors.toList());

        String [] array = {"a","b","c"};
        for (Integer i : Arrays.asList(1,2,3)) {
            Stream.of(array).map(item -> item + "@" + i).forEach(System.out::println);
        }

        //编译错误 lambda表达式访问外部变量限制,变量不可变(指的是引用不可变,并不是真正的不可变)
        //在java8之前匿名内部类在访问外部变量时,外部变量必须用final修饰
        //在java8这个限制做了优化,可以不显示使用final修饰,但是编译器会隐式的当成final处理
        for (int i = 0; i < 4; i++) {
//            Stream.of(array).map(item -> item + "@" + i).forEach(System.out::println);
        }

    }
}
