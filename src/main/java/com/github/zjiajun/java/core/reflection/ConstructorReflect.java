package com.github.zjiajun.java.core.reflection;

import java.lang.reflect.Constructor;

/**
 * Created by zhujiajun
 * 15/5/13 21:00
 *
 * 获取构造器
 */
public class ConstructorReflect {

    private static final String HR = "-------------------------";

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {

        //获取class方式三种
        Class<UserForReflect> userForReflectClass = UserForReflect.class;

        //Class<?> aClass = Class.forName("com.zjiajun.java.reflect.UserForReflect");

        //UserForReflect u = new UserForReflect();
        //Class<? extends UserForReflect> aClass1 = u.getClass();

        Constructor<?>[] constructors = userForReflectClass.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println(HR);

        Constructor<?>[] declaredConstructors = userForReflectClass.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            System.out.println(constructor);
        }
        System.out.println(HR);

        Constructor<UserForReflect> constructor = userForReflectClass.getConstructor(String.class);
        System.out.println(constructor);
        System.out.println(HR);

        Constructor<UserForReflect> constructor1 = userForReflectClass.getDeclaredConstructor(String.class, Integer.class);
        System.out.println(constructor1);


    }
}
