package com.github.zjiajun.java.core.reflection;

import java.lang.reflect.Array;

/**
 * Created by zhujiajun
 * 16/3/18 21:38
 *
 * 关于数组反射
 */
public class ArrayReflection {

    public static void main(String[] args) throws ClassNotFoundException {
        int [] array = (int[]) Array.newInstance(int.class, 2);
        System.out.println(array.getClass());
        System.out.println(array.getClass().getComponentType());
        System.out.println(array.getClass().isArray());

        Array.set(array,0,1);
        Array.set(array,1,2);

        System.out.println("int Array[0]= " + Array.get(array,0));
        System.out.println("int Arrau[1]= " + Array.get(array,1));

        Class intArray = Class.forName("[I");
        System.out.println(intArray);

        Class stringArrayClass = Class.forName("[Ljava.lang.String;");
        System.out.println(stringArrayClass);

        String[] strings = (String[]) Array.newInstance(String.class, 3);
        System.out.println(strings.length);
        System.out.println(strings.getClass());
        System.out.println(strings.getClass().isArray());

        char[] chars = (char[]) Array.newInstance(getClass("char"), 2);
        System.out.println(chars.length);
        Array.set(chars,0,'5');
        Array.set(chars,1,'6');
        System.out.println(Array.get(chars,0));
        System.out.println(Array.get(chars,1));

        boolean[] booleen = (boolean[]) Array.newInstance(getClass("boolean"), 2);
        Array.set(booleen,0,true);
        Array.set(booleen,1,false);
        System.out.println(Array.get(booleen,0));
        System.out.println(Array.get(booleen,1));
    }

    private static Class getClass(String className) {
        switch (className) {
            case "byte"     : return byte.class;
            case "int"      : return int.class;
            case "long"     : return long.class;
            case "double"   : return double.class;
            case "float"    : return float.class;
            case "short"    : return short.class;
            case "boolean"  : return boolean.class;
            case "char"     : return char.class;
            case "string"   : return String.class;
            default         : return null;
        }

    }
}
