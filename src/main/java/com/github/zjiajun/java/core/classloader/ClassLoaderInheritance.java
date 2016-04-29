package com.github.zjiajun.java.core.classloader;

import com.sun.nio.zipfs.ZipInfo;

/**
 * Created by zhujiajun
 * 16/4/29 22:09
 *
 * classLoader 层级关系
 *
 * BootStrapClassLoader  启动类加载器(JAVA_HOME/lib),可以通过-Xbootclasspath选项指定的jar包到内存
 *
 *      ExtClassLoader 扩展类加载器(JAVA_HOME/lib/ext),可以通过-Xjava.ext.dir指定位置中的类库加载到内存,如指定了则原有的ext下的java包不会被引入
 *
 *          AppClassLoader 系统类加载器(classpath),可以通过-Xjava.class.path或-classpath
 */
public class ClassLoaderInheritance {

    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("ClassLoader.getSystemClassLoader() " + systemClassLoader);

        ClassLoader classLoader = ClassLoaderInheritance.class.getClassLoader();
        System.out.println("ClassLoaderInheritance.class.getClassLoader() " + classLoader);

        System.out.println(systemClassLoader == classLoader);

        ClassLoader classLoader1 = ZipInfo.class.getClassLoader();//ZipInfo类在ext下,属于ExtClassLoader加载器
        System.out.println("ZipInfo.class.getClassLoader() " + classLoader1);


        System.out.println(systemClassLoader.getParent());
        System.out.println(systemClassLoader.getParent().getParent());

    }
}
