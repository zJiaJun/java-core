package com.github.zjiajun.java.core.reflection;

import java.lang.reflect.Method;

/**
 * Created by zhujiajun
 * 15/5/18 20:27
 */
public class MethodReflect {

    public static void main(String[] args) {

        Class<UserForReflect> userForReflectClass = UserForReflect.class;

        Method[] methods = userForReflectClass.getMethods();

        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("------------");

        Method[] declaredMethods = userForReflectClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method);
        }

        System.out.println("------------");
        try {
            Method method = userForReflectClass.getMethod("addUser");
            System.out.println(method);
            method = userForReflectClass.getMethod("updateUser",String.class);
            System.out.println(method);
            method = userForReflectClass.getDeclaredMethod("queryUser", String.class, Integer.class);
            System.out.println(method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
