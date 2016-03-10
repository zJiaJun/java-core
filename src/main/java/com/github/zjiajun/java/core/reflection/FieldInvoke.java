package com.github.zjiajun.java.core.reflection;

import java.lang.reflect.Field;

/**
 * Created by zhujiajun
 * 15/5/20 21:49
 */
public class FieldInvoke {

    public static void main(String[] args) {

        Class<UserForReflect> userForReflectClass = UserForReflect.class;

        try {
            UserForReflect userForReflect = userForReflectClass.newInstance();

            setFieldValue(userForReflectClass, userForReflect);

            getFieldValue(userForReflectClass,userForReflect);

        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    private static void getFieldValue(Class<UserForReflect> userForReflectClass, UserForReflect userForReflect) throws NoSuchFieldException, IllegalAccessException {
        Field school = userForReflectClass.getField("school");
        Object o = school.get(userForReflect);
        System.out.println(o);

        Field name = userForReflectClass.getDeclaredField("name");
        name.setAccessible(true);
        Object o1 = name.get(userForReflect);
        System.out.println(o1);

        Field age = userForReflectClass.getDeclaredField("age");
        age.setAccessible(true);
        int anInt = age.getInt(userForReflect);
        System.out.println(anInt);


    }

    private static void setFieldValue(Class<UserForReflect> userForReflectClass, UserForReflect userForReflect) throws NoSuchFieldException, IllegalAccessException {
        Field school = userForReflectClass.getField("school");
        school.set(userForReflect,"inter");

        Field name = userForReflectClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(userForReflect,"leon");

        Field age = userForReflectClass.getDeclaredField("age");
        age.setAccessible(true);
        age.setInt(userForReflect,88);
    }
}
