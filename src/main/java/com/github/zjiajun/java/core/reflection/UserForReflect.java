package com.github.zjiajun.java.core.reflection;

import java.util.Arrays;

/**
 * Created by zhujiajun
 * 15/5/13 20:58
 */
public class UserForReflect extends SuperUserForReflect {

    private String name;
    private int age;
    public String school;

    public UserForReflect() {
        System.out.println("--UserForReflect()--");
    }

    public UserForReflect(String name){
        System.out.println("--UserForReflect(String name)--" + name);
    }

    private UserForReflect(String name,Integer age){
        System.out.println("--UserForReflect(String name,Integer age)--" + name + "," + age);
    }

    public void addUser() {
        System.out.println("addUser()");
    }

    public boolean updateUser(String name) {
        System.out.println("updateUser " + name);
        return true;
    }

    private String queryUser(String name,Integer age) {
        System.out.println("queryUser " + name + ", " + age);
        return "User";
    }

    public static void staticMethod() {
        System.out.println("staticMethod()");
    }

    public static void staticMethodByAge(int... age) {
        System.out.println("staticMethodByAge()" + Arrays.toString(age));
    }

    private static String staticMethodByName(int age,String...name) {
        System.out.println("staticMethodByName()" + Arrays.toString(name) + age);
        return "1";
    }

}
