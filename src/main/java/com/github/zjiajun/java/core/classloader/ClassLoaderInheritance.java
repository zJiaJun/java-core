package com.github.zjiajun.java.core.classloader;

import com.sun.nio.zipfs.ZipInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(ClassLoaderInheritance.class);

    public static void main(String[] args) {
        logger.info(System.getProperty("sun.boot.class.path"));
        logger.info(System.getProperty("java.ext.dirs"));
        logger.info(System.getProperty("java.class.path"));

        ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
        logger.info("Thread.currentThread().getContextClassLoader() " + threadClassLoader);

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        logger.info("ClassLoader.getSystemClassLoader() " + systemClassLoader);

        ClassLoader classLoader = ClassLoaderInheritance.class.getClassLoader();
        logger.info("ClassLoaderInheritance.class.getClassLoader() " + classLoader);

        logger.info((systemClassLoader == classLoader)+"");

        ClassLoader classLoader1 = ZipInfo.class.getClassLoader();//ZipInfo类在ext下,属于ExtClassLoader加载器
        logger.info("ZipInfo.class.getClassLoader() " + classLoader1);


        while (systemClassLoader != null) {
            logger.info(systemClassLoader.getClass().getCanonicalName());
            systemClassLoader = systemClassLoader.getParent();
        }

    }
}
