package com.github.zjiajun.java.core.reflection;

import java.lang.reflect.Field;

/**
 * Created by zhujiajun
 * 15/5/19 21:40
 */
public class FieldReflect {

    public static void main(String[] args) {
        Class<UserForReflect> userForReflectClass = UserForReflect.class;

        Field[] fields = userForReflectClass.getFields();
        for (Field f : fields) {
            System.out.println(f);
        }

        System.out.println("-------------------");

        Field[] declaredFields = userForReflectClass.getDeclaredFields();
        for (Field f : declaredFields) {
            System.out.println(f);
        }

        System.out.println("-------------------");


        try {
            Field field = userForReflectClass.getField("school");
            System.out.println(field);
            System.out.println("-------------------");
            field = userForReflectClass.getDeclaredField("name");
            System.out.println(field);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }
}
