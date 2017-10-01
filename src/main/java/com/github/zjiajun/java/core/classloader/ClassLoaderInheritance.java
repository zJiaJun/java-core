package com.github.zjiajun.java.core.classloader;

import com.sun.nio.zipfs.ZipInfo;

/**
 * Created by zhujiajun
 * 16/4/29 22:09
 * <p>
 * classLoader 层级关系
 * <p>
 * BootStrapClassLoader  启动类加载器(JAVA_HOME/lib),可以通过-Xbootclasspath选项指定的jar包到内存
 * -Xbootclasspath/a: 把路径添加到已存在BootStrapClassLoader搜索路径的后面
 * -Xbootclasspath/p: 把路径添加到已存在BootStrapClassLoader搜索路径的前面
 * <p>
 * ExtClassLoader 扩展类加载器(JAVA_HOME/lib/ext),可以通过-Djava.ext.dirs指定位置中的类库加载到内存,如指定了则原有的ext下的java包不会被引入
 * <p>
 * AppClassLoader 系统类加载器(classpath),可以通过-Djava.class.path,-classpath,-cp
 */
public class ClassLoaderInheritance {

    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

        ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("Thread.currentThread().getContextClassLoader() " + threadClassLoader);

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("ClassLoader.getSystemClassLoader() " + systemClassLoader);

        ClassLoader classLoader = ClassLoaderInheritance.class.getClassLoader();
        System.out.println("ClassLoaderInheritance.class.getClassLoader() " + classLoader);

        System.out.println(systemClassLoader == classLoader);

        ClassLoader classLoader1 = ZipInfo.class.getClassLoader();//ZipInfo类在ext下,属于ExtClassLoader加载器
        System.out.println("ZipInfo.class.getClassLoader() " + classLoader1);


        while (systemClassLoader != null) {
            System.out.println(systemClassLoader.getClass().getCanonicalName());
            systemClassLoader = systemClassLoader.getParent();
        }

    }
}
