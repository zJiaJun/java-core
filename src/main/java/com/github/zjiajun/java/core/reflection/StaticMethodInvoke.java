package com.github.zjiajun.java.core.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhujiajun
 * 15/5/19 20:52
 */
public class StaticMethodInvoke {

    public static void main(String[] args) {
        Class<UserForReflect> userForReflectClass = UserForReflect.class;

        try {
            Method method = userForReflectClass.getMethod("staticMethod");
            Object invoke = method.invoke(null);
            System.out.println(invoke);

            method = userForReflectClass.getMethod("staticMethodByAge",int[].class);
            invoke = method.invoke(null,new int[]{1,2,3});
            System.out.println(invoke);

            method = userForReflectClass.getDeclaredMethod("staticMethodByName", int.class,String[].class);
            method.setAccessible(true);
            invoke = method.invoke(null,new Object[]{888,new String[]{"a","b","c"}});
            System.out.println(invoke);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
