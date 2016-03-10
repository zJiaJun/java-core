package com.github.zjiajun.java.core.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by zhujiajun
 * 15/5/13 21:17
 *
 * 获取构造器 创建对象
 */
public class ConstructorInvoke {

    public static void main(String[] args) {

        Class<UserForReflect> userForReflectClass = UserForReflect.class;
        Constructor<UserForReflect> constructor = null;
        UserForReflect userForReflect = null;
        try {
            constructor = userForReflectClass.getConstructor();
            userForReflect = constructor.newInstance();
            System.out.println(userForReflect);

            constructor = userForReflectClass.getConstructor(String.class);
            userForReflect = constructor.newInstance("zjiajun");
            System.out.println(userForReflect);

            constructor = userForReflectClass.getDeclaredConstructor(String.class, Integer.class);
            constructor.setAccessible(true);
            userForReflect = constructor.newInstance("zjiajun", 30);
            System.out.println(userForReflect );


            userForReflect = userForReflectClass.newInstance();
            System.out.println(userForReflect);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
