package com.github.zjiajun.java.core.reflection;

/**
 * Created by zhujiajun
 * 15/5/18 20:29
 */
public class SuperUserForReflect {

    private String field1;
    public String field2;

    public SuperUserForReflect() {}

    public SuperUserForReflect(String name) {}

    public void addSuperUser(){}

    public boolean updateSuperUser(String name){
        return true;
    }

    private String querySuperUser(String name,Integer age){
        return "User";
    }
}
