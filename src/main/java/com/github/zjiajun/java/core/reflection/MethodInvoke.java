package com.github.zjiajun.java.core.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhujiajun
 * 15/5/18 21:26
 */
public class MethodInvoke {

    public static void main(String[] args) {

        Class<UserForReflect> userForReflectClass = UserForReflect.class;

        try {
            Method method = userForReflectClass.getMethod("addUser");
            Object invoke = method.invoke(userForReflectClass.newInstance());
            System.out.println(invoke);
            System.out.println("-------------------");

            method = userForReflectClass.getMethod("updateUser",String.class);
            Class<?> returnType = method.getReturnType();
            System.out.println(returnType);
            Constructor<UserForReflect> constructor = userForReflectClass.getConstructor(String.class);
            UserForReflect userForReflect = constructor.newInstance("leon");
            invoke = method.invoke(userForReflect,"123");
            if (returnType == boolean.class) {
                boolean b = (Boolean) invoke;
                System.out.println(b);
            }

            System.out.println("-------------------");
            constructor = userForReflectClass.getDeclaredConstructor(String.class,Integer.class);
            constructor.setAccessible(true);
            userForReflect = constructor.newInstance("leon",30);
            method = userForReflectClass.getDeclaredMethod("queryUser", String.class, Integer.class);
            System.out.println(method);
            method.setAccessible(true);
            invoke = method.invoke(userForReflect, "123",50);
            System.out.println(invoke);

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
