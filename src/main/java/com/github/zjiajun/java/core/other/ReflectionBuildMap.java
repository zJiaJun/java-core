package com.github.zjiajun.java.core.other;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

/**
 * @author zhujiajun
 * @since 2017/5/8
 */
public class ReflectionBuildMap {

    public void test(String name, Integer age, Long other) {

        System.out.println(name + age + other);

    }

    public void buildMap() throws NoSuchMethodException {
        Class<ReflectionBuildMap> aClass = ReflectionBuildMap.class;
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if ("test".equals(method.getName())) {
                Type[] genericParameterTypes = method.getGenericParameterTypes();
                Parameter[] parameters = method.getParameters();

                for (Parameter parameter : parameters) {
                    System.out.println(parameter);

                    System.out.println(parameter.getName());
                }
            }
        }

    }


    public static void main(String[] args) throws NoSuchMethodException {
        ReflectionBuildMap buildMap = new ReflectionBuildMap();
        buildMap.buildMap();

    }



}
