package com.github.zjiajun.java.core.other;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Manifest;

/**
 * @author zhujiajun
 * @since 2017/1/8
 *
 * 通过JarURLConnection加载jar包
 * 动态加载类，反射调用
 */
public class JarURLConnectionExample {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String url = "jar:file:/Users/zhujiajun/Work/maven-repository/org/mybatis/mybatis/3.3.1/mybatis-3.3.1.jar!/";
        URL jarURL = new URL(url);
        java.net.JarURLConnection connection = (java.net.JarURLConnection) jarURL.openConnection();
        Manifest manifest = connection.getManifest();
        manifest.getMainAttributes().forEach((k,v)->System.out.println(k + "***" +v));

        System.out.println(manifest.getMainAttributes().getValue("Bundle-License"));

        URLClassLoader urlClassLoader = new URLClassLoader(
                new URL[]{new URL("file:/Users/zhujiajun/Work/maven-repository/org/mybatis/mybatis/3.3.1/mybatis-3.3.1.jar")});

        Class<?> aClass = urlClassLoader.loadClass("org.apache.ibatis.type.TypeAliasRegistry");

        Object instance = aClass.newInstance();

        System.out.println(instance);

        Method resolveAlias = aClass.getMethod("resolveAlias", String.class);

        System.out.println(resolveAlias);

        Object string = resolveAlias.invoke(instance, "list");

        System.out.println(string);

    }
}
